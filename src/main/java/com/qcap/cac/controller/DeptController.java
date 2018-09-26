package com.qcap.cac.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qcap.core.common.CoreConstant;
import com.qcap.core.factory.PageFactory;
import com.qcap.core.utils.CommUtil;
import com.qcap.core.utils.TenpayUtil;
import com.qcap.core.utils.jwt.JwtProperties;
import com.qcap.core.utils.jwt.JwtTokenUtil;
import com.qcap.core.warpper.LogWarpper;
import com.qcap.cac.common.service.PropertyCommonSrv;
import com.qcap.cac.model.Department;
import com.qcap.cac.service.DeptSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.qcap.core.controller.BaseController;
import com.qcap.core.model.PageResParams;
import com.qcap.core.model.ResParams;
import com.qcap.core.utils.RedisUtil;

/** 
 * @ClassName: DeptController 
 * @Description: TODO
 * @author: baojianxing
 * @date: 2018年4月16日 上午10:03:21  
 */
@Controller
@RequestMapping("/dept")
public class DeptController extends BaseController {
	
	
	private String PREFIX = "/system/department/";
	

	@Autowired
	private JwtProperties jwtProperties;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
    private RedisUtil redisUtil;
	
	/**
     * 跳转到部门管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "dept";
    }

    @Autowired
    private DeptSrv deptSrv;
	
    
    @Autowired
	private PropertyCommonSrv propertyCommonSrv;
    
    
    /**
	 * 获取登录人的账户
	 * @Title: getLoginUserAccount 
	 * @Description: TODO
	 * @param request
	 * @return
	 * @return: String
	 */
	String getLoginUserWorkId( HttpServletRequest request) {
		String token = request.getHeader(jwtProperties.getTokenHeader());
	    //String userId = jwtTokenUtil.getUsernameFromToken(token);
	    String userId = redisUtil.getUserId(token);
	    String workId=propertyCommonSrv.selectAccountByUserId(userId);
	    return workId;
	}
    
	/**
	 * 查询所有的组织结构信息
	 * @Title: selectAllDept 
	 * @Description: TODO
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @return: Map
	 */
	@SuppressWarnings({ "rawtypes", "static-access", "unchecked" })
	@RequestMapping(value = "/select", method = RequestMethod.POST)
	@ResponseBody
	public Object selectAllDept(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			String workId = this.getLoginUserWorkId(request);  
			String companyCode = this.propertyCommonSrv.selectCompanyCodeByWorkId(workId);

			Map param = new HashMap<>();
			if (!"0".equals(companyCode)) {
				param.put("companyCode", companyCode);
				//如果companyCode==009标识该登录用户属于江西信贷平台，则只需要按照公司别查询
				if(!"009".equals(companyCode)) {
					param.put("workId", workId);
				}
			}
			List<Map<String, Object>> deptList = this.deptSrv.selectAll(param);
			resultMap.put("result", "1");
			resultMap.put("deptList", deptList);
			
			return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "", deptList);
			//log.info("显示子部门成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultMap.put("result", "0"); // 失败信息返回
			//log.info("显示子部门失败");
			return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "", resultMap);
		}
		//return resultMap;
	}

	/**
	 * 分页查询部门信息
	 * @Title: selectBb04ByPage 
	 * @Description: TODO
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @return: Map
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "static-access" })
	@RequestMapping(value = "/selectBb04ByPage", method = RequestMethod.POST)
	@ResponseBody
	public Object selectBb04ByPage(Model model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
			Map params=new HashMap<>(); 
			
			String parentTbbb04Id = TenpayUtil.toString(request.getParameter("parentTbbb04Id"))  ;
			if (parentTbbb04Id != null && !parentTbbb04Id.isEmpty()) {
				params.put("parentTbbb04Id", parentTbbb04Id);
			}

			String workId = this.getLoginUserWorkId(request);
			String companyCode = this.propertyCommonSrv.selectCompanyCodeByWorkId(workId);

			if (companyCode != null && !companyCode.isEmpty() && !"0".equals(companyCode)) {
				params.put("companyCode", companyCode);
				params.put("workId", workId);
			}
			
			
			new PageFactory<Map>().defaultPage();
			//查询部门信息
	        List<Map<String,Object>> list = this.deptSrv.selectPp01ByPage(params);
	        
	       
	        PageInfo pageInfo = new PageInfo( (List<Map>) new LogWarpper(list).warp());
	        Page pageList = (Page) list;
	        Map<String,Object> result = new HashMap<String,Object>();
	        result.put("total",pageInfo.getTotal());
	        result.put("rows", pageList);
	        
	        return PageResParams.newInstance(CoreConstant.SUCCESS_CODE,"",pageInfo.getTotal(),list);
	       // return result;
		
	}

	/**
	 * 增加组织结构名称
	 * @Title: insert 
	 * @Description: TODO
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @return: String
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@ResponseBody
	public Object insert(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			String createEmp = TenpayUtil.toString(this.getLoginUserWorkId(request));
			String deptName = TenpayUtil.toString(request.getParameter("deptName")) ;
			String shortName = TenpayUtil.toString(request.getParameter("shortName")) ;
			String code = TenpayUtil.toString(request.getParameter("code")) ;
			String parentTbbb04Id = TenpayUtil.toString(request.getParameter("parentTbbb04Id")) ;

			String companyCode = this.deptSrv.selectByPrimaryKey(parentTbbb04Id).getCompanyCode();
			String tbbb04Id = deptSrv.selectMaxId(parentTbbb04Id);
			if (tbbb04Id == null) {
				tbbb04Id = parentTbbb04Id + "000";
			}
			Integer layer = deptSrv.selectByPrimaryKey(parentTbbb04Id).getLayer() + 1;
			
			String num = tbbb04Id.substring(tbbb04Id.length()-2);
			//Int类型存储范围是-2,147,483,648 --2,147,483,647(超过此范围Integer.parseInt会报异常)
			int i = Integer.valueOf(num) + 1;
			String end = String.format("%02d", i);
			String id = tbbb04Id.substring(0,tbbb04Id.length()-2) + end;
			/*if (layer == 1) {
				id = String.format("%06d", data);
			} else if (layer == 2) {
				id = String.format("%09d", data);
			} else if (layer == 3) {
				id = String.format("%012d", data);
			}*/
		//	int n = deptSrv.selectByName(deptName);
			//加上父级节点一起判断
			Map param=new HashMap<>();
			param.put("name", deptName);
			param.put("parentId", parentTbbb04Id);
			int n=this.deptSrv.selectByNameAndParentId(param);
			
			if (n > 0) {
				resultMap.put("result", "2");
				return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.EDIT_SUCCESS, resultMap);
			} else {
				Department bb04 = new Department();
				bb04.setDepartmentId(id);
				bb04.setDeptName(deptName);
				bb04.setShortName(shortName);
				bb04.setCode(code);
				bb04.setLayer(layer);
				bb04.setDeptCode(id);
				bb04.setParentDeptId(parentTbbb04Id);
				bb04.setCompanyCode(companyCode);
				bb04.setDeleteFlag("0");
				bb04.setCreateEmp(createEmp);
				bb04.setCreateDate(new Date());
				bb04.setUpdateDate(new Date());
				bb04.setHaveChild("0");
				bb04.setVersion(0);
				String remark1 = TenpayUtil.toString(request.getParameter("remark1"));
				if(remark1 !=null && !"".equals(remark1)) {
					bb04.setRemark1(remark1);
				}
				deptSrv.insert(bb04);

				//修改上级部门的HaveChild状态
				map.put("tbbb04Id", parentTbbb04Id);
				map.put("haveChild", "1");
				deptSrv.updateChild(map);

				resultMap.put("result", "1");
				resultMap.put("tbbb04Id", id);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultMap.put("result", "0"); // 失败信息返回
		}
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.ADD_SUCCESS, resultMap);
		//return resultMap;
	}

	/**
	 * 更新组织结构名称
	 * @Title: updateTbbb04 
	 * @Description: TODO
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @return: Map
	 */
	@SuppressWarnings({ "rawtypes", "static-access" })
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Object updateTbbb04(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {
			String tbbb04Id = TenpayUtil.toString(request.getParameter("tbbb04Id"));
			String deptName = TenpayUtil.toString(request.getParameter("deptName"));
			String parentTbbb04Id=TenpayUtil.toString(request.getParameter("parentTbbb04Id"));
			//String createEmp = TenpayUtil.toString(this.getLoginUserAccount());
			Department bb04 = deptSrv.selectByPrimaryKey(tbbb04Id);
			
			String remark1 = TenpayUtil.toString(request.getParameter("remark1"));
			if(remark1 !=null && !"".equals(remark1)) {
				bb04.setRemark1(remark1);
			}
			int length = tbbb04Id.length();
			if(length == 0 || length == 3){
				resultMap.put("result", "-1");
			//	return resultMap;
				return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "", resultMap);
			}
			bb04.setUpdateDate(new Date());
			//int n = deptSrv.selectByName(deptName);
			//加上父级节点一起判断
			Map param=new HashMap<>();
			param.put("name", deptName);
			param.put("parentId", parentTbbb04Id);
			int n=this.deptSrv.selectByNameAndParentId(param);
			if (n > 0) {
				bb04.setDeptName(bb04.getDeptName());
				resultMap.put("result", "2");//当重名时返回2
				return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.EDIT_SUCCESS, resultMap);
			} else {
				bb04.setDeptName(deptName);
				
			}
			deptSrv.updateByPrimaryKey(bb04);
			resultMap.put("result", "1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultMap.put("result", "0"); // 失败信息返回
		}
		//return resultMap;
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.EDIT_SUCCESS, resultMap);
	}

	/**
	 * 删除(修改删除标识)
	 * @Title: deleteTbbb04 
	 * @Description: TODO
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @return: Map
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteTbbb04(Model model, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String tbbb04Id = request.getParameter("tbbb04Id");
			if(tbbb04Id == null ||tbbb04Id.equals("")){
				resultMap.put("result", "-1");
				return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "", resultMap);
			}
			String parentTbbb04Id = deptSrv.selectByPrimaryKey(tbbb04Id).getParentDeptId();
			
			String num = this.deptSrv.selectIfUsed(tbbb04Id);
			if(!"0".equals(num)){//部门已经被使用，无法删除
				resultMap.put("result", "2");
				return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "", resultMap);
			}
			
			String i = this.deptSrv.queryChildDept(tbbb04Id + "%");
			if(!"1".equals(i)){//有子部门，无法删除
				resultMap.put("result", "3");
				return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "", resultMap);
			}
			
			this.deptSrv.deleteByPrimaryKey(tbbb04Id);
			int n = this.deptSrv.selectChild(tbbb04Id);
			if (n == 0) {
				map.put("haveChild", "0");
				map.put("tbbb04Id", parentTbbb04Id);
				deptSrv.updateChild(map);

			}

			resultMap.put("result", "1");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			resultMap.put("result", "0");
		}
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.DELETE_SUCCESS, resultMap);
	}
	
	@RequestMapping(value = "/getManagementUnitList")
    @ResponseBody
    public Object getManagementUnitList(HttpServletRequest reuqest){
		List<Map> list = new ArrayList<Map>();
		try {
			String token = reuqest.getHeader(jwtProperties.getTokenHeader());
		    String userId = redisUtil.getUserId(token);
        	list = this.deptSrv.getManagementUnitList(userId);
    	}catch(Exception e) {
    		e.printStackTrace();
    		return CommUtil.setMessage(500, "查询失败", null);
    	}
    	return ResParams.newInstance(CoreConstant.SUCCESS_CODE, null, list);
    }

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/getBuildingList")
    @ResponseBody
    public Object getBuildingList(HttpServletRequest reuqest){
		List<Map> list = new ArrayList<Map>();
		try {
			String token = reuqest.getHeader(jwtProperties.getTokenHeader());
		    String userId = redisUtil.getUserId(token);
		    String flag=TenpayUtil.toString(reuqest.getParameter("flag"));
		    Map param=new HashMap<>();
		    if(!flag.equals("A")) {
		    	param.put("flag", "B");
		    }else {
		    	param.put("flag", "A");
		    }
		    param.put("userId",userId );
        	list = this.deptSrv.getBuildingList(param);
    	}catch(Exception e) {
    		e.printStackTrace();
    		return CommUtil.setMessage(500, "查询失败", null);
    	}
    	return ResParams.newInstance(CoreConstant.SUCCESS_CODE, null, list);
    }
}
