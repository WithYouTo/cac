package com.qcap.cac.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.constant.CommonConstant;
import com.qcap.cac.dao.TaskArrangeMapper;
import com.qcap.cac.dto.TaskArrangeDto;
import com.qcap.cac.entity.TbTaskArrangement;
import com.qcap.cac.poiEntity.TaskArrangeUploadEntity;
import com.qcap.cac.service.TaskArrangeSrv;
import com.qcap.cac.tools.UUIDUtils;
import com.qcap.core.model.ResParams;

@Service
@Transactional
public class TaskArrangeSrvImpl implements TaskArrangeSrv{
	
	@Resource
	private TaskArrangeMapper taskArrangeMapper;
	
	@Override
	public Object importTaskArrange(List<TaskArrangeUploadEntity> list,TaskArrangeDto taskArrangeDto, int row) throws IllegalAccessException, InvocationTargetException {
		
		List<TbTaskArrangement> taskArrangeList = new ArrayList<>();
		
		for(TaskArrangeUploadEntity  entity:list) {
			/**
			 * 1、数据验证
			 * TODO
			 * 
			 */
			
			TbTaskArrangement taskArrangement = new TbTaskArrangement();
			BeanUtils.copyProperties(taskArrangement, entity);
			BeanUtils.copyProperties(taskArrangement, taskArrangeDto);
			taskArrangement.setArrangementId(UUIDUtils.getUUID());
			/**
			 * TODO
			 * 员工电话
			 */
			
			taskArrangement.setDeleteFlag(CommonConstant.DELETE_FLAG_NORMAL);
			/**
			 * TODO
			 * 人员管理
			 */
//			taskArrangement.setCreateEmp("SYS");
			taskArrangement.setCreateDate(new Date());
			taskArrangement.setVersion(0);
			taskArrangeList.add(taskArrangement);
		}
		
		// 2、删除上次导入的数据
		this.taskArrangeMapper.deleteTaskArrange(taskArrangeDto);
		
		//3、新增排版表到数据库
		this.taskArrangeMapper.insertTaskArrangeBatch(taskArrangeList);
		
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, "导入排班表成功");
	}

	@Override
	public List<Map<String, Object>> listTaskArrange(TaskArrangeDto taskArrangeDto) {
		// TODO Auto-generated method stub
		return taskArrangeMapper.listTaskArrange(taskArrangeDto);
	}

}
