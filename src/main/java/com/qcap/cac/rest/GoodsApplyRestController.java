/**   
 * Copyright © 2018 公司名. All rights reserved.
 * 
 * @Title: EventTaskRestController.java 
 * @Prject: cac
 * @Package: com.qcap.cac.rest 
 * @Description: TODO
 * @author: 张天培(2017004)   
 * @date: 2018年10月11日 上午9:16:00 
 * @version: V1.0   
 */
package com.qcap.cac.rest;

import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.dto.EventTaskRestDto;
import com.qcap.cac.service.EventTaskRestSrv;
import com.qcap.core.model.ResParams;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * @ClassName: GoodsApplyRestController
 * @Description: TODO
 * @author: 曾欣
 * @date: 2018年10月22日 上午9:16:00
 */
@Api(description="物品领用")
@RestController
@RequestMapping(value="/rest/goodsApply",headers="api_version=v1")
public class GoodsApplyRestController {
	
	@Resource
	private EventTaskRestSrv eventTaskSrvImpl;

	@RequestMapping(value="genEventTask",method=RequestMethod.POST)
	@ApiOperation(value="航班数据录入及事件性任务生成",notes="航班数据录入及事件性任务生成",response=Map.class,httpMethod="POST")
	@ApiImplicitParam(paramType="header",name="api_version",defaultValue="v1",required=true,dataType="String")
	public Object geneEventTask(@Valid EventTaskRestDto eventTaskDto) {
			this.eventTaskSrvImpl.geneEventTask(eventTaskDto);
			return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, "新增航班数据成功",null);

	}

}
