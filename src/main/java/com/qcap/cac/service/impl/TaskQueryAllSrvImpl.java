/**   
 * Copyright © 2018 公司名. All rights reserved.
 * 
 * @Title: TaskQueryAllSrvImpl.java 
 * @Prject: cac
 * @Package: com.qcap.cac.service.impl 
 * @Description: TODO
 * @author: 张天培(2017004)   
 * @date: 2018年10月12日 上午10:21:11 
 * @version: V1.0   
 */
package com.qcap.cac.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qcap.cac.dao.TaskQueryAllMapper;
import com.qcap.cac.dto.TaskQueryAllSearchDto;
import com.qcap.cac.service.TaskQueryAllSrv;

/** 
 * @ClassName: TaskQueryAllSrvImpl 
 * @Description: TODO
 * @author: 张天培(2017004)
 * @date: 2018年10月12日 上午10:21:11  
 */
@Service
@Transactional
public class TaskQueryAllSrvImpl implements TaskQueryAllSrv {
	
	@Resource
	private TaskQueryAllMapper taskQueryMapper;

	@Override
	public List<Map> selectAllTask(TaskQueryAllSearchDto taskQueryDto) {
		// TODO Auto-generated method stub
		return taskQueryMapper.selectAllTask(taskQueryDto);
	}


	@Override
	public List<Map> selectStandard() {
		// TODO Auto-generated method stub
		return taskQueryMapper.selectStandard();
	}


	@Override
	public List<Map> selectPosition() {
		// TODO Auto-generated method stub
		return taskQueryMapper.selectPosition();
	}
	
//	@Override
//	public List<Map> selectEmployee() {
//		// TODO Auto-generated method stub
//		return taskQueryMapper.selectEmployee();
//	}

}
