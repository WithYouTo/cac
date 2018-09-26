package com.qcap.cac.common.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qcap.core.exception.BizExceptionEnum;
import com.qcap.core.exception.BussinessException;
import com.qcap.core.utils.jwt.JwtProperties;
import com.qcap.cac.common.service.CommonSrv;
import com.qcap.cac.common.service.SysFileSrv;
import com.qcap.cac.model.TbSysFile;
import com.qcap.cac.service.WebUserSrv;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.qcap.core.common.CoreConstant;
import com.qcap.core.controller.BaseController;
import com.qcap.core.model.ResParams;
import com.qcap.core.utils.AppUtils;
import com.qcap.core.utils.DateChangeUtil;
import com.qcap.core.utils.RedisUtil;
import com.qcap.core.utils.SftpUtil;
import com.qcap.core.utils.TenpayUtil;
import com.qcap.core.utils.ToolUtil;
import com.qcap.core.utils.UUIDUtil;

@Controller
@RequestMapping("/common")
public class CommonComtroller extends BaseController {
	
	@Resource
	private CommonSrv commonSrvImpl;
	
	@Resource
	private SysFileSrv sysFileSrvImpl;
	
    @Autowired
    private JwtProperties jwtProperties;
    
    @Autowired
    private RedisUtil redisUtil;
    
	@Resource
	private WebUserSrv webUserSrv;
	
	//sftp服务器配置
	@Value(value = "${SFTP_HOST}")
	private String sftpHost;

	@Value(value = "${SFTP_PORT}")
	private int sftpPort;

	@Value(value = "${SFTP_USERNAME}")
	private String sftpUserName;

	@Value(value = "${SFTP_PASSWORD}")
	private String sftpPassword;

	@Value(value = "${SFTP_BASE_PATH}")
	private String sftpBaseUrl;
	
	@Value(value = "${SFTP_DOMAIN_PATH}")
	private String sftpDomainUrl;//域名
	
	@Value(value="${CONTRACT_PATH}")
	private String contractPath;
	
	private Logger log=AppUtils.getLogger("commonController", true);
	
	@RequestMapping("/selectBuilding")
	@ResponseBody
	public Object selectBuildingByCode(HttpServletRequest req) {
		String managementId=ToolUtil.toStr(req.getParameter("managementId"));
		List<Map<String, Object>>list=null;
		try {
			list=commonSrvImpl.selectBuildingByCode(managementId);
		} catch (Exception e) {
			// TODO: handle exception
			log.info("==============查询楼宇出现异常============"+e.getMessage());
		}
		
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "查询楼宇成功", list);
	}
	
	@RequestMapping("/uploadFile")
	@ResponseBody
	public Object uploadSysFiles(Model model, HttpServletRequest request, HttpServletResponse response) {
		MultipartHttpServletRequest rm = (MultipartHttpServletRequest) request;
		MultiValueMap<String,MultipartFile> map=rm.getMultiFileMap();
		MultipartFile file=map.getFirst("file");
		String fileName=file.getOriginalFilename();
		//文件后缀
		String suffix=fileName.substring(fileName.lastIndexOf("."));
		String newName=DateChangeUtil.dateTimeToStringForLineNo(new Date());
		InputStream in =null;
		String storeUrl="";
		String downloadUrl="";
		//返回上传图片的路径
		Map<String, Object>resultMap=new HashMap<>();
		try {
			in = file.getInputStream();
			SftpUtil sftp = new SftpUtil(sftpUserName, sftpHost, sftpPort, sftpPassword);
			/**
			 * TODO
			 * 在更换图片时，先更具Id查询到原来的图片真实路径，进行删除后，再新增
			 */
			sftp.upload(sftpBaseUrl, contractPath, newName+suffix, in);
			storeUrl=sftpBaseUrl+contractPath+"/"+newName+suffix;
			downloadUrl=sftpDomainUrl+contractPath+"/"+newName+suffix;
			
			//获取当前用户名和所属公司
			String token = request.getHeader(jwtProperties.getTokenHeader());
			String userId=redisUtil.getUserId(token);
			Map<String,Object> userMap=webUserSrv.selectByPrimaryKey(userId);
			String account=ToolUtil.toStr(userMap.get("account"));
			
			String groupId= TenpayUtil.toString(request.getParameter("groupId"));
			TbSysFile picFile=new TbSysFile();
			String fileId= UUIDUtil.getUUID();
			picFile.setTbFileId(fileId);
			picFile.setFileName(fileName);
			picFile.setFilePath(storeUrl);
			picFile.setRemark(downloadUrl);
			picFile.setCreateDate(new Date());
			picFile.setCreateEmp(account);
			picFile.setVersion(0);
			picFile.setGroupId(groupId);
			sysFileSrvImpl.insert(picFile);
			
			resultMap.put("storeUrl", storeUrl);
			resultMap.put("downloadUrl", downloadUrl);
			resultMap.put("fileId", fileId);
			resultMap.put("flag", "1");
			resultMap.put("message", "上传文件成功！");
		} catch (IOException e) {
			resultMap.put("flag", "-1");
			resultMap.put("message", "上传文件失败！");
			throw new BussinessException(BizExceptionEnum.SERVER_ERROR);
		}finally {
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				resultMap.put("flag", "-1");
				resultMap.put("message", "上传文件失败！");
			}
		}
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "", resultMap);
		
	}
	
	@RequestMapping("/deleteFile")
	@ResponseBody
	public Object deleteSysFiles(HttpServletRequest request, HttpServletResponse response) {
		String tbFileId=ToolUtil.toStr(request.getParameter("tbFileId"));
		String filePath=ToolUtil.toStr(request.getParameter("filePath"));
		//返回上传图片的路径
		Map<String, Object>resultMap=new HashMap<>();
		if(ToolUtil.isOneEmpty(tbFileId,filePath)){
			resultMap.put("flag", "-1");
			resultMap.put("message", "参数为空！");
			throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
		}
		String directory=filePath.substring(0, filePath.lastIndexOf("/"));
		String fileName=filePath.substring(filePath.lastIndexOf("/")+1);
		SftpUtil sftp = new SftpUtil(sftpUserName, sftpHost, sftpPort, sftpPassword);
		try {
			sftp.delete(directory, fileName);
			this.sysFileSrvImpl.deleteByPrimaryKey(tbFileId);
			resultMap.put("flag", "1");
			resultMap.put("message", "删除文件成功！");
		} catch (Exception e) {
			resultMap.put("flag", "-1");
			resultMap.put("message", "删除文件失败！");
		}
		
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "", resultMap);
		
	}
	
	/**
	 * 根据当前登录人的Id查询其所在部门下的楼宇或公寓小区
	 * @Title: getBuildingOrArea 
	 * @Description: TODO
	 * @param request
	 * @return
	 * @return: Object
	 */
	@RequestMapping(value="/getBuildingOrArea",method=RequestMethod.POST)
	@ResponseBody
	public Object getBuildingOrArea(HttpServletRequest request) {
		String aORb=TenpayUtil.toString(request.getParameter("aORb"));
		if(ToolUtil.isEmpty(aORb)) {
			throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
		}
		List<Map<String, Object>>list=null;
		try {
			
			String token = request.getHeader(jwtProperties.getTokenHeader());
			String userId=redisUtil.getUserId(token);
			Map<String, Object>map=new HashMap<>();
			map.put("userId", userId);
			map.put("aORb", aORb);
			list=commonSrvImpl.selectBuildingOrAreaByUserId(map);
		} catch (Exception e) {
			// TODO: handle exception
			log.info("根据用户Id查询其所在部门下的小区或楼栋信息出现异常:"+e.getMessage());
		}
		
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "", list);
	}
}
