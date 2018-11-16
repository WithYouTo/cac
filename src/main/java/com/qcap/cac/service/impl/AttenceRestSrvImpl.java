package com.qcap.cac.service.impl;

import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.constant.CommonConstant;
import com.qcap.cac.dao.AttenceMapper;
import com.qcap.cac.dao.AttenceRestMapper;
import com.qcap.cac.dto.*;
import com.qcap.cac.entity.TbAttence;
import com.qcap.cac.exception.BaseException;
import com.qcap.cac.service.AttenceRestSrv;
import com.qcap.cac.service.CommonSrv;
import com.qcap.cac.tools.DateTool;
import com.qcap.cac.tools.EntityTools;
import com.qcap.cac.tools.RedisTools;
import com.qcap.cac.tools.UUIDUtils;
import com.qcap.core.entity.TbOrg;
import com.qcap.core.entity.TbUserInfo;
import com.qcap.core.utils.AppUtils;
import com.qcap.core.warpper.FastDFSClientWrapper;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.*;

@Service
@Transactional
public class AttenceRestSrvImpl implements AttenceRestSrv {

	@Resource
	private AttenceRestMapper attenceRestMapper;

	@Resource
	private AttenceMapper attenceMapper;

	@Resource
	private CommonSrv commonSrv;

	@Resource
	private JdbcTemplate jdbcTemplate;

	@Resource
	private FastDFSClientWrapper dfsClient;

	@Override
	public void attence(AttenceReq req, List<MultipartFile> fileList) throws Exception {
		TbOrg org = commonSrv.getOrgByWorkNo(req.getEmployeeCode());
		TbUserInfo userInfo = commonSrv.getUserInfoByWorkNo(req.getEmployeeCode());

		TbAttence attence = new TbAttence();
		attence.setAttenceId(UUIDUtils.getUUID());
		if (StringUtils.isNotBlank(req.getAttencePlace())) {
			if (checkPositionCode(req.getAttencePlace())) {
				attence.setAttencePlace(req.getAttencePlace());
			} else {
				throw new BaseException(CommonCodeConstant.ERROR_CODE_50501, CommonCodeConstant.ERROR_CODE_50501_MSG);
			}
		}

		attence.setWorkNo(req.getEmployeeCode());
		attence.setAttenceTime(StringUtils.isNotBlank(req.getAttenceTime())
				? CommonConstant.sdf_YMDHMS.parse(req.getAttenceTime()) : new Date());
		attence.setWorkContent(req.getWorkContent());
		if (org != null) {
			attence.setOrgName(org.getName());
		}
		if (userInfo != null) {
			attence.setPersonId(userInfo.getUserId());
			attence.setPersonName(userInfo.getUserName());
			attence.setPersonMobile(userInfo.getMobile());
		}

		if (CollectionUtils.isNotEmpty(fileList)) {
			String url = "";
			for (MultipartFile file : fileList) {
				url += dfsClient.uploadFile(file) + ";";
			}
			attence.setFilesUrl(url);
		}

		//写入项目编码
		List<String>  programCodes = AppUtils.getLoginUserProjectCodes();
		if(!CollectionUtils.isEmpty(programCodes)){
			programCodes.removeAll(Collections.singleton(""));
			attence.setProgramCode(StringUtils.join(programCodes,","));
		}
		attence = EntityTools.setCreateEmpAndTime(attence);

		attenceMapper.insert(attence);
	}

	@Override
	public List<GetAttenceResp> getAttenceList(GetAttenceReq req) throws Exception {
		req.setMonth(DateTool.dateFormat("yyyy-MM", "yyyyMM", req.getMonth()));
		Map<String, Object> taskArrangementMap = this.getTaskArrangement(req);
		Map<String, Object> attenceMap = this.getAttence(req);
		List<GetAttenceResp> ls = new ArrayList<>();
		Calendar now = Calendar.getInstance();
		int currentDay = now.get(Calendar.DAY_OF_MONTH);
		for (int i = 1; i < 32; i++) {
			GetAttenceResp resp = new GetAttenceResp();
			String shift = String.valueOf(taskArrangementMap.get("shift_" + i));
			int attence = Integer.valueOf(String.valueOf(attenceMap.get("attence_" + i)));
			if (!"0".equals(shift) && attence > 0) {
				PropertyUtils.setProperty(resp, "attenceStatus", "Y");
			} else if (!"0".equals(shift) && attence == 0) {
				if (currentDay < i) {
					PropertyUtils.setProperty(resp, "attenceStatus", "");
				} else {
					PropertyUtils.setProperty(resp, "attenceStatus", "N");
				}
			} else if ("0".equals(shift)) {
				if (currentDay < i) {
					PropertyUtils.setProperty(resp, "attenceStatus", "");
				} else {
					PropertyUtils.setProperty(resp, "attenceStatus", "R");

				}
			}
			PropertyUtils.setProperty(resp, "shift", shift);
			PropertyUtils.setProperty(resp, "day", Objects.toString(i));
			ls.add(resp);
		}
		return ls;
	}

	@Override
	public List<GetAttenceDetailsResp> getAttenceDetails(GetAttenceDetailsReq req) throws Exception {
		List<GetAttenceDetailsResp> lsRecord = new ArrayList<>();
		List<Map<String, String>> ls = attenceRestMapper.getAttenceList(req);
		//查询配置管理中存放的文件访问地址前缀
		String addressPrefix = RedisTools.getCommonConfig("CAC_FIPE_PATH_PREFIX");
		if (CollectionUtils.isNotEmpty(ls)) {
			for (Map<String, String> attenceMap : ls) {
				GetAttenceDetailsResp resp = new GetAttenceDetailsResp();
				resp.setAttencePlace(attenceMap.get("attencePlace"));
				resp.setAttencePlaceName(attenceMap.get("attencePlaceName"));
				resp.setAttenceTime(attenceMap.get("attenceTime"));
				resp.setWorkContent(attenceMap.get("workContent"));
				if (StringUtils.isNotBlank(attenceMap.get("filesUrl"))) {
					String [] imgArr = attenceMap.get("filesUrl").split(";");
					for(int i=0;i<imgArr.length;i++) {
						imgArr[i] =addressPrefix + imgArr[i];
					}
					resp.setUrl(imgArr);
				} else {
					resp.setUrl(new String[] {});
				}
				lsRecord.add(resp);
			}
		}
		return lsRecord;
	}

	public Map<String, Object> getAttence(GetAttenceReq req) {
		String currentDate = CommonConstant.sdf_YMDHMS.format(new Date());
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT "
				+ "IFNULL(MAX(attence_1),0) attence_1,IFNULL(MAX(attence_2),0) attence_2,IFNULL(MAX(attence_3),0) attence_3,"
				+ "IFNULL(MAX(attence_4),0) attence_4,IFNULL(MAX(attence_5),0) attence_5,IFNULL(MAX(attence_6),0) attence_6,"
				+ "IFNULL(MAX(attence_7),0) attence_7,IFNULL(MAX(attence_8),0) attence_8,IFNULL(MAX(attence_9),0) attence_9,"
				+ "IFNULL(MAX(attence_10),0) attence_10,"
				+ "IFNULL(MAX(attence_11),0) attence_11,IFNULL(MAX(attence_12),0) attence_12,IFNULL(MAX(attence_13),0) attence_13,"
				+ "IFNULL(MAX(attence_14),0) attence_14,IFNULL(MAX(attence_15),0) attence_15,IFNULL(MAX(attence_16),0) attence_16,"
				+ "IFNULL(MAX(attence_17),0) attence_17,IFNULL(MAX(attence_18),0) attence_18,IFNULL(MAX(attence_19),0) attence_19,"
				+ "IFNULL(MAX(attence_20),0) attence_20,"
				+ "IFNULL(MAX(attence_21),0) attence_21,IFNULL(MAX(attence_22),0) attence_22,IFNULL(MAX(attence_23),0) attence_23,"
				+ "IFNULL(MAX(attence_24),0) attence_24,IFNULL(MAX(attence_25),0) attence_25,IFNULL(MAX(attence_26),0) attence_26,"
				+ "IFNULL(MAX(attence_27),0) attence_27,IFNULL(MAX(attence_28),0) attence_28,IFNULL(MAX(attence_29),0) attence_29,"
				+ "IFNULL(MAX(attence_30),0) attence_30,IFNULL(MAX(attence_31),0) attence_31 FROM(SELECT "
				+ "CASE DATE_FORMAT(ATTENCE_TIME,'%e') WHEN '1' THEN COUNT(1) ELSE 0 END  attence_1,"
				+ "CASE DATE_FORMAT(ATTENCE_TIME,'%e') WHEN '2' THEN COUNT(1) ELSE 0 END  attence_2,"
				+ "CASE DATE_FORMAT(ATTENCE_TIME,'%e') WHEN '3' THEN COUNT(1) ELSE 0 END  attence_3,"
				+ "CASE DATE_FORMAT(ATTENCE_TIME,'%e') WHEN '4' THEN COUNT(1) ELSE 0 END  attence_4,"
				+ "CASE DATE_FORMAT(ATTENCE_TIME,'%e') WHEN '5' THEN COUNT(1) ELSE 0 END  attence_5,"
				+ "CASE DATE_FORMAT(ATTENCE_TIME,'%e') WHEN '6' THEN COUNT(1) ELSE 0 END  attence_6,"
				+ "CASE DATE_FORMAT(ATTENCE_TIME,'%e') WHEN '7' THEN COUNT(1) ELSE 0 END  attence_7,"
				+ "CASE DATE_FORMAT(ATTENCE_TIME,'%e') WHEN '8' THEN COUNT(1) ELSE 0 END  attence_8,"
				+ "CASE DATE_FORMAT(ATTENCE_TIME,'%e') WHEN '9' THEN COUNT(1) ELSE 0 END  attence_9,"
				+ "CASE DATE_FORMAT(ATTENCE_TIME,'%e') WHEN '10' THEN COUNT(1) ELSE 0 END  attence_10,"
				+ "CASE DATE_FORMAT(ATTENCE_TIME,'%e') WHEN '11' THEN COUNT(1) ELSE 0 END  attence_11,"
				+ "CASE DATE_FORMAT(ATTENCE_TIME,'%e') WHEN '12' THEN COUNT(1) ELSE 0 END  attence_12,"
				+ "CASE DATE_FORMAT(ATTENCE_TIME,'%e') WHEN '13' THEN COUNT(1) ELSE 0 END  attence_13,"
				+ "CASE DATE_FORMAT(ATTENCE_TIME,'%e') WHEN '14' THEN COUNT(1) ELSE 0 END  attence_14,"
				+ "CASE DATE_FORMAT(ATTENCE_TIME,'%e') WHEN '15' THEN COUNT(1) ELSE 0 END  attence_15,"
				+ "CASE DATE_FORMAT(ATTENCE_TIME,'%e') WHEN '16' THEN COUNT(1) ELSE 0 END  attence_16,"
				+ "CASE DATE_FORMAT(ATTENCE_TIME,'%e') WHEN '17' THEN COUNT(1) ELSE 0 END  attence_17,"
				+ "CASE DATE_FORMAT(ATTENCE_TIME,'%e') WHEN '18' THEN COUNT(1) ELSE 0 END  attence_18,"
				+ "CASE DATE_FORMAT(ATTENCE_TIME,'%e') WHEN '19' THEN COUNT(1) ELSE 0 END  attence_19,"
				+ "CASE DATE_FORMAT(ATTENCE_TIME,'%e') WHEN '20' THEN COUNT(1) ELSE 0 END  attence_20,"
				+ "CASE DATE_FORMAT(ATTENCE_TIME,'%e') WHEN '21' THEN COUNT(1) ELSE 0 END  attence_21,"
				+ "CASE DATE_FORMAT(ATTENCE_TIME,'%e') WHEN '22' THEN COUNT(1) ELSE 0 END  attence_22,"
				+ "CASE DATE_FORMAT(ATTENCE_TIME,'%e') WHEN '23' THEN COUNT(1) ELSE 0 END  attence_23,"
				+ "CASE DATE_FORMAT(ATTENCE_TIME,'%e') WHEN '24' THEN COUNT(1) ELSE 0 END  attence_24,"
				+ "CASE DATE_FORMAT(ATTENCE_TIME,'%e') WHEN '25' THEN COUNT(1) ELSE 0 END  attence_25,"
				+ "CASE DATE_FORMAT(ATTENCE_TIME,'%e') WHEN '26' THEN COUNT(1) ELSE 0 END  attence_26,"
				+ "CASE DATE_FORMAT(ATTENCE_TIME,'%e') WHEN '27' THEN COUNT(1) ELSE 0 END  attence_27,"
				+ "CASE DATE_FORMAT(ATTENCE_TIME,'%e') WHEN '28' THEN COUNT(1) ELSE 0 END  attence_28,"
				+ "CASE DATE_FORMAT(ATTENCE_TIME,'%e') WHEN '29' THEN COUNT(1) ELSE 0 END  attence_29,"
				+ "CASE DATE_FORMAT(ATTENCE_TIME,'%e') WHEN '30' THEN COUNT(1) ELSE 0 END  attence_30,"
				+ "CASE DATE_FORMAT(ATTENCE_TIME,'%e') WHEN '31' THEN COUNT(1) ELSE 0 END  attence_31 "
				+ "FROM tb_attence WHERE WORK_NO = ? AND DATE_FORMAT(ATTENCE_TIME,'%Y%m') = ? AND ATTENCE_TIME <= ? GROUP BY DATE_FORMAT(ATTENCE_TIME,'%Y%m%d'))A");
		return jdbcTemplate.queryForMap(sql.toString(),
				new Object[] { req.getEmployeeCode(), req.getMonth(), currentDate });
	}

	public Map<String, Object> getTaskArrangement(GetAttenceReq req) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT "
				+ "IFNULL(MAX(shift_1),0) shift_1,IFNULL(MAX(shift_2),0) shift_2,IFNULL(MAX(shift_3),0) shift_3,"
				+ "IFNULL(MAX(shift_4),0) shift_4,IFNULL(MAX(shift_5),0) shift_5,IFNULL(MAX(shift_6),0) shift_6,"
				+ "IFNULL(MAX(shift_7),0) shift_7,IFNULL(MAX(shift_8),0) shift_8,IFNULL(MAX(shift_9),0) shift_9,"
				+ "IFNULL(MAX(shift_10),0) shift_10,"
				+ "IFNULL(MAX(shift_11),0) shift_11,IFNULL(MAX(shift_12),0) shift_12,IFNULL(MAX(shift_13),0) shift_13,"
				+ "IFNULL(MAX(shift_14),0) shift_14,IFNULL(MAX(shift_15),0) shift_15,IFNULL(MAX(shift_16),0) shift_16,"
				+ "IFNULL(MAX(shift_17),0) shift_17,IFNULL(MAX(shift_18),0) shift_18,IFNULL(MAX(shift_19),0) shift_19,"
				+ "IFNULL(MAX(shift_20),0) shift_20,"
				+ "IFNULL(MAX(shift_21),0) shift_21,IFNULL(MAX(shift_22),0) shift_22,IFNULL(MAX(shift_23),0) shift_23,"
				+ "IFNULL(MAX(shift_24),0) shift_24,IFNULL(MAX(shift_25),0) shift_25,IFNULL(MAX(shift_26),0) shift_26,"
				+ "IFNULL(MAX(shift_27),0) shift_27,IFNULL(MAX(shift_28),0) shift_28,IFNULL(MAX(shift_29),0) shift_29,"
				+ "IFNULL(MAX(shift_30),0) shift_30,IFNULL(MAX(shift_31),0) shift_31 FROM(SELECT "
				+ "CASE DAY_1 WHEN '√' THEN SHIFT ELSE 0 END  shift_1,"
				+ "CASE DAY_2 WHEN '√' THEN SHIFT ELSE 0 END  shift_2,"
				+ "CASE DAY_3 WHEN '√' THEN SHIFT ELSE 0 END  shift_3,"
				+ "CASE DAY_4 WHEN '√' THEN SHIFT ELSE 0 END  shift_4,"
				+ "CASE DAY_5 WHEN '√' THEN SHIFT ELSE 0 END  shift_5,"
				+ "CASE DAY_6 WHEN '√' THEN SHIFT ELSE 0 END  shift_6,"
				+ "CASE DAY_7 WHEN '√' THEN SHIFT ELSE 0 END  shift_7,"
				+ "CASE DAY_8 WHEN '√' THEN SHIFT ELSE 0 END  shift_8,"
				+ "CASE DAY_9 WHEN '√' THEN SHIFT ELSE 0 END  shift_9,"
				+ "CASE DAY_10 WHEN '√' THEN SHIFT ELSE 0 END  shift_10,"
				+ "CASE DAY_11 WHEN '√' THEN SHIFT ELSE 0 END  shift_11,"
				+ "CASE DAY_12 WHEN '√' THEN SHIFT ELSE 0 END  shift_12,"
				+ "CASE DAY_13 WHEN '√' THEN SHIFT ELSE 0 END  shift_13,"
				+ "CASE DAY_14 WHEN '√' THEN SHIFT ELSE 0 END  shift_14,"
				+ "CASE DAY_15 WHEN '√' THEN SHIFT ELSE 0 END  shift_15,"
				+ "CASE DAY_16 WHEN '√' THEN SHIFT ELSE 0 END  shift_16,"
				+ "CASE DAY_17 WHEN '√' THEN SHIFT ELSE 0 END  shift_17,"
				+ "CASE DAY_18 WHEN '√' THEN SHIFT ELSE 0 END  shift_18,"
				+ "CASE DAY_19 WHEN '√' THEN SHIFT ELSE 0 END  shift_19,"
				+ "CASE DAY_20 WHEN '√' THEN SHIFT ELSE 0 END  shift_20,"
				+ "CASE DAY_21 WHEN '√' THEN SHIFT ELSE 0 END  shift_21,"
				+ "CASE DAY_22 WHEN '√' THEN SHIFT ELSE 0 END  shift_22,"
				+ "CASE DAY_23 WHEN '√' THEN SHIFT ELSE 0 END  shift_23,"
				+ "CASE DAY_24 WHEN '√' THEN SHIFT ELSE 0 END  shift_24,"
				+ "CASE DAY_25 WHEN '√' THEN SHIFT ELSE 0 END  shift_25,"
				+ "CASE DAY_26 WHEN '√' THEN SHIFT ELSE 0 END  shift_26,"
				+ "CASE DAY_27 WHEN '√' THEN SHIFT ELSE 0 END  shift_27,"
				+ "CASE DAY_28 WHEN '√' THEN SHIFT ELSE 0 END  shift_28,"
				+ "CASE DAY_29 WHEN '√' THEN SHIFT ELSE 0 END  shift_29,"
				+ "CASE DAY_30 WHEN '√' THEN SHIFT ELSE 0 END  shift_30,"
				+ "CASE DAY_31 WHEN '√' THEN SHIFT ELSE 0 END  shift_31 "
				+ "FROM tb_task_arrangement WHERE EMPLOYEE_CODE = ? AND DELETE_FLAG = ? AND MONTH = ? GROUP BY SHIFT,EMPLOYEE_CODE,MONTH)A");
		return jdbcTemplate.queryForMap(sql.toString(),
				new Object[] { req.getEmployeeCode(), CommonConstant.DELETE_FLAG_NORMAL, req.getMonth() });
	}

	public boolean checkPositionCode(String positionCode) throws Exception {
		int i = attenceRestMapper.checkPositionCode(positionCode);
		return i > 0;
	}
}
