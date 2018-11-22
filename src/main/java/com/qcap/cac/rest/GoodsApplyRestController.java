package com.qcap.cac.rest;

import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.dto.*;
import com.qcap.cac.service.GoodsApplyRestSrv;
import com.qcap.core.common.CoreConstant;
import com.qcap.core.model.ResParams;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: GoodsApplyRestController
 * @Description: App物品领用和物品发放
 * @author: 曾欣
 * @date: 2018年10月22日 上午9:16:00
 */
@Api(tags = "APP物品领用和物品发放")
@RestController
@RequestMapping(value="/rest/goodsApply",headers="api_version=v1")
public class GoodsApplyRestController {
	
	@Resource
	private GoodsApplyRestSrv goodsApplyRestSrv;

	@RequestMapping(value="getReqList",method=RequestMethod.POST)
	@ApiOperation(value="库管查询领用单",notes="库管查询领用单",response=Map.class,httpMethod="POST")
	@ApiImplicitParam(paramType="header",name="api_version",defaultValue="v1",required=true,dataType="String")
	public Object getReqListByWarehouseManager(
			@ApiParam(value = "登录人主键", required = true) @RequestParam(value = "employeeCode", required = true) String employeeCode,
			@ApiParam(value = "lineNo", required = false) @RequestParam(value = "lineNo", required = false) String lineNo) {
		Map<String,Object> map = new HashMap<>();
		try {
			Map<String,String> paramMap = new HashMap();
			paramMap.put("employeeCode",employeeCode);
			paramMap.put("lineNo",lineNo);
			List<GoodsReqRestReq> list = this.goodsApplyRestSrv.queryReqList(paramMap);
			map.put("reqList",list);
		} catch (Exception e) {
			return ResParams.newInstance(CoreConstant.FAIL_CODE, e.getMessage(),null);
		}
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, "库管查询领用单成功",map);
	}


	@RequestMapping(value="getReqDetailList",method=RequestMethod.POST)
	@ApiOperation(value="库管查询领用单明细",notes="库管查询领用单明细",response=Map.class,httpMethod="POST")
	@ApiImplicitParam(paramType="header",name="api_version",defaultValue="v1",required=true,dataType="String")
	public Object getReqDetailListByWarehouseManager(
			@ApiParam(value = "登录人主键", required = true) @RequestParam(value = "employeeCode", required = true) String employeeCode,
			@ApiParam(value = "领用单主键", required = true) @RequestParam(value = "warehouseRequId", required = true) String warehouseRequId,
			@ApiParam(value = "lineNo", required = false) @RequestParam(value = "lineNo", required = false) String lineNo) {
		Map<String,Object> map = new HashMap<>();
		try {
			Map<String,String> paramMap = new HashMap();
			paramMap.put("employeeCode",employeeCode);
			paramMap.put("warehouseRequId",warehouseRequId);
			paramMap.put("lineNo",lineNo);
			List<GoodsReqDetailReq> list = this.goodsApplyRestSrv.queryReqDetailList(paramMap);
			map.put("reqDetailList",list);
		} catch (Exception e) {
			return ResParams.newInstance(CoreConstant.FAIL_CODE, e.getMessage(),null);
		}
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, "库管查询领用明细成功",map);
	}


	@RequestMapping(value="getDistributionList",method=RequestMethod.POST)
	@ApiOperation(value="主管查询发放明细",notes="主管查询发放明细",response=Map.class,httpMethod="POST")
	@ApiImplicitParam(paramType="header",name="api_version",defaultValue="v1",required=true,dataType="String")
	public Object getDistributionList(
			@ApiParam(value = "登录人工号", required = true) @RequestParam(value = "employeeCode", required = true) String employeeCode,
			@ApiParam(value = "岗位编码", required = false) @RequestParam(value = "positionCode", required = false) String positionCode,
			@ApiParam(value = "lineNo", required = false) @RequestParam(value = "lineNo", required = false) String lineNo) {
		Map<String,Object> map = new HashMap<>();
		try {
			Map<String,String> paramMap = new HashMap();
			paramMap.put("employeeCode",employeeCode);
			//paramMap.put("positionCode",positionCode);
			paramMap.put("lineNo",lineNo);
			List<GoodsDistributionDetailReq> list = this.goodsApplyRestSrv.queryAvailDistributionList(paramMap);
			map.put("distributionList",list);
		} catch (Exception e) {
			return ResParams.newInstance(CoreConstant.FAIL_CODE, e.getMessage(),null);
		}
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, "主管查询发放明细成功",map);
	}


	@RequestMapping(value="updateDelivery",method=RequestMethod.POST)
	@ApiOperation(value="库管出库",notes="库管出库",response=Map.class,httpMethod="POST")
	@ApiImplicitParam(paramType="header",name="api_version",defaultValue="v1",required=true,dataType="String")
	public Object updateDeliveryByManager(@RequestBody  GoodsOutListReq goodsOutListReq) {
		if(null == goodsOutListReq){
			return ResParams.newInstance(CoreConstant.FAIL_CODE, "库管出库物品为空",null);
		}
		if(CollectionUtils.isEmpty(goodsOutListReq.getGoodsOutReqList())){
			return ResParams.newInstance(CoreConstant.FAIL_CODE, "库管出库物品为空",null);
		}
		try {
			this.goodsApplyRestSrv.updateDelivery(goodsOutListReq);
		} catch (Exception e) {
			return ResParams.newInstance(CoreConstant.FAIL_CODE, e.getMessage(),null);
		}
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, "库管出库成功",null);
	}

	@RequestMapping(value="updateDistribution",method=RequestMethod.POST)
	@ApiOperation(value="主管发放",notes="主管发放",response=Map.class,httpMethod="POST")
	@ApiImplicitParam(paramType="header",name="api_version",defaultValue="v1",required=true,dataType="String")
	public Object updateDistribution(@RequestBody GoodsOutDistruListReq goodsOutDistruReqList) {
		if(null == goodsOutDistruReqList){
			return ResParams.newInstance(CoreConstant.FAIL_CODE, "物品发放参数为空",null);
		}
		if(CollectionUtils.isEmpty(goodsOutDistruReqList.getGoodsOutDistruReqList())){
			return ResParams.newInstance(CoreConstant.FAIL_CODE, "物品发放参数为空",null);
		}
		try {
			this.goodsApplyRestSrv.updateDistribution(goodsOutDistruReqList);
		} catch (Exception e) {
			return ResParams.newInstance(CoreConstant.FAIL_CODE, e.getMessage(),null);
		}
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, "主管发放物品成功",null);
	}

}
