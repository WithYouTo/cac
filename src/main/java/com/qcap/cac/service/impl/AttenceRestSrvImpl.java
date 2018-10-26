package com.qcap.cac.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.qcap.cac.constant.CommonConstant;
import com.qcap.cac.dao.AttenceMapper;
import com.qcap.cac.dao.AttenceRestMapper;
import com.qcap.cac.dto.AttenceReq;
import com.qcap.cac.dto.GetAttenceDetailsReq;
import com.qcap.cac.dto.GetAttenceDetailsResp;
import com.qcap.cac.dto.GetAttenceReq;
import com.qcap.cac.dto.GetAttenceResp;
import com.qcap.cac.entity.TbAttence;
import com.qcap.cac.service.AttenceRestSrv;
import com.qcap.cac.service.CommonSrv;
import com.qcap.cac.tools.EntityTools;
import com.qcap.cac.tools.UUIDUtils;
import com.qcap.core.entity.TbOrg;
import com.qcap.core.entity.TbUserInfo;
import com.qcap.core.warpper.FastDFSClientWrapper;

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
			attence.setPersonMoBile(userInfo.getMobile());
		}

		if (CollectionUtils.isNotEmpty(fileList)) {
			String url = "";
			for (MultipartFile file : fileList) {
				url += dfsClient.uploadFile(file) + ";";
			}
			attence.setFilesUrl(url);
		}

		attence = EntityTools.setCreateEmpAndTime(attence);

		attenceMapper.insert(attence);
	}

	@Override
	public List<GetAttenceResp> getAttenceList(GetAttenceReq req) throws Exception {
		Map<String, Object> taskArrangementMap = this.getTaskArrangement(req);
		Map<String, Object> attenceMap = this.getAttence(req);
		List<GetAttenceResp> ls = new ArrayList<>();
		Calendar now = Calendar.getInstance();
		int currentDay = now.get(Calendar.DAY_OF_MONTH);
		for (int i = 1; i < 32; i++) {
			GetAttenceResp resp = new GetAttenceResp();
			int attence = Integer.valueOf(String.valueOf(attenceMap.get("attence_" + i)));
			String shift = String.valueOf(taskArrangementMap.get("shift_" + i));
			if (!"0".equals(shift) && attence > 0) {
				PropertyUtils.setProperty(resp, "attenceStatus", "Y");
				PropertyUtils.setProperty(resp, "shift", CommonConstant.SHIFT.get(shift));
			} else if (!"0".equals(shift) && attence == 0) {
				if (currentDay < i) {
					PropertyUtils.setProperty(resp, "attenceStatus", "");
					PropertyUtils.setProperty(resp, "shift", CommonConstant.SHIFT.get(shift));
				} else {
					PropertyUtils.setProperty(resp, "attenceStatus", "N");
					PropertyUtils.setProperty(resp, "shift", CommonConstant.SHIFT.get(shift));
				}
			} else if ("0".equals(shift)) {
				if (currentDay < i) {
					PropertyUtils.setProperty(resp, "attenceStatus", "");
					PropertyUtils.setProperty(resp, "shift", CommonConstant.SHIFT.get(shift));
				} else {
					PropertyUtils.setProperty(resp, "attenceStatus", "R");
					PropertyUtils.setProperty(resp, "shift", CommonConstant.SHIFT.get(shift));
				}
			}
			PropertyUtils.setProperty(resp, "day", Objects.toString(i));
			ls.add(resp);
		}
		return ls;
	}

	@Override
	public List<GetAttenceDetailsResp> getAttenceDetails(GetAttenceDetailsReq req) throws Exception {
		List<GetAttenceDetailsResp> lsRecord = new ArrayList<>();
		List<TbAttence> ls = attenceRestMapper.getAttenceList(req);
		if (CollectionUtils.isNotEmpty(ls)) {
			for (TbAttence attence : ls) {
				GetAttenceDetailsResp resp = new GetAttenceDetailsResp();
				BeanUtils.copyProperties(resp, attence);
				if (StringUtils.isNotBlank(attence.getFilesUrl())) {
					resp.setUrl(attence.getFilesUrl().split(";"));
				}
				lsRecord.add(resp);
			}
		}
		return lsRecord;
	}

	public Map<String, Object> getAttence(GetAttenceReq req) {
		String currentDate = CommonConstant.sdf_YMDHMS.format(new Date());
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT " + "MAX(attence_1) attence_1, MAX(attence_2) attence_2,MAX(attence_3) attence_3,"
				+ "MAX(attence_4) attence_4,MAX(attence_5) attence_5,MAX(attence_6) attence_6,"
				+ "MAX(attence_7) attence_7,MAX(attence_8) attence_8,MAX(attence_9) attence_9,"
				+ "MAX(attence_10) attence_10,"
				+ "MAX(attence_11) attence_11, MAX(attence_12) attence_12,MAX(attence_13) attence_13,"
				+ "MAX(attence_14) attence_14,MAX(attence_15) attence_15,MAX(attence_16) attence_16,"
				+ "MAX(attence_17) attence_17,MAX(attence_18) attence_18,MAX(attence_19) attence_19,"
				+ "MAX(attence_20) attence_20,"
				+ "MAX(attence_21) attence_21, MAX(attence_22) attence_22,MAX(attence_23) attence_23,"
				+ "MAX(attence_24) attence_24,MAX(attence_25) attence_25,MAX(attence_26) attence_26,"
				+ "MAX(attence_27) attence_27,MAX(attence_28) attence_28,MAX(attence_29) attence_29,"
				+ "MAX(attence_30) attence_30, MAX(attence_31) attence_31 FROM(SELECT "
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
		sql.append("SELECT " + " MAX(shift_1) shift_1,MAX(shift_2) shift_2,MAX(shift_3) shift_3,"
				+ "MAX(shift_4) shift_4,MAX(shift_5) shift_5,MAX(shift_6) shift_6,"
				+ "MAX(shift_7) shift_7,MAX(shift_8) shift_8,MAX(shift_9) shift_9," + "MAX(shift_10) shift_10,"
				+ "MAX(shift_11) shift_11, MAX(shift_12) shift_12,MAX(shift_13) shift_13,"
				+ "MAX(shift_14) shift_14,MAX(shift_15) shift_15,MAX(shift_16) shift_16,"
				+ "MAX(shift_17) shift_17,MAX(shift_18) shift_18,MAX(shift_19) shift_19," + "MAX(shift_20) shift_20,"
				+ "MAX(shift_21) shift_21, MAX(shift_22) shift_22,MAX(shift_23) shift_23,"
				+ "MAX(shift_24) shift_24,MAX(shift_25) shift_25,MAX(shift_26) shift_26,"
				+ "MAX(shift_27) shift_27,MAX(shift_28) shift_28,MAX(shift_29) shift_29,"
				+ "MAX(shift_30) shift_30, MAX(shift_31) shift_31 FROM(SELECT "
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
				new Object[] { req.getEmployeeCode(), CommonConstant.DELETE_FLAG_DELETE, req.getMonth() });
	}
}
