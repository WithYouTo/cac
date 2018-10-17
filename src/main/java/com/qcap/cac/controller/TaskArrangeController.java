package com.qcap.cac.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.dto.TaskArrangeDto;
import com.qcap.cac.poiEntity.TaskArrangeUploadEntity;
import com.qcap.cac.service.TaskArrangeSrv;
import com.qcap.core.factory.PageFactory;
import com.qcap.core.model.PageResParams;
import com.qcap.core.utils.poi.PoiUtils;

@RestController
@RequestMapping("/taskArrange")
public class TaskArrangeController {
	
	private static final int TITLE_ROWS=1;
	
	private static final int HEAD_ROWS=1;
	
	private static final int HEAD_AND_TITLE_ROWS=TITLE_ROWS+HEAD_ROWS;
	
	@Resource
	private TaskArrangeSrv     taskArrangeSrvImpl;
	
	@RequestMapping("/list")
	public Object listTaskArrange(@Valid TaskArrangeDto taskArrangeDto) {
		new PageFactory<>().defaultPage();
		List<Map<String, Object>> list = this.taskArrangeSrvImpl.listTaskArrange(taskArrangeDto);
		PageInfo<Map<String, Object>> pageInfo =new PageInfo<>(list);
		return PageResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, "", pageInfo.getTotal(), list);
	}
	

	@RequestMapping("/uploadFile")
	public Object uploadFile(MultipartFile file,@Valid TaskArrangeDto taskArrangeDto ) {
		List<TaskArrangeUploadEntity> list= PoiUtils.importExcel(file, TITLE_ROWS, HEAD_ROWS, TaskArrangeUploadEntity.class);
		Object resParams = null;
		try {
			/**
			 * 当前登陆人未完成
			 * TODO
			 */
			taskArrangeDto.setCreateEmp("SYS导入排班表");
			 resParams= this.taskArrangeSrvImpl.importTaskArrange(list,taskArrangeDto, HEAD_AND_TITLE_ROWS);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return resParams;
		
	}
	
	

}
