package com.whxx.core.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.whxx.core.common.CoreConstant;
import com.whxx.core.entity.TbCommonConfig;
import com.whxx.core.exception.BizExceptionEnum;
import com.whxx.core.exception.BussinessException;
import com.whxx.core.model.PageResParams;
import com.whxx.core.model.ResParams;
import com.whxx.core.service.impl.TbCommonConfigServiceImpl;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 *  CommonConfigController
 * @author 硅谷架构师聂银军
 * @date 2018年5月21日 上午11:04:14
 */
@RestController
@RequestMapping("/commconfig")
public class CommonConfigController {

    private TbCommonConfigServiceImpl commonConfigService;

	/**
	 * 下拉菜单类型
	 *
	 * @return 下拉菜单类型
	 */
    @PostMapping("/types")
    public ResParams getTypes()
    {
        List<String> list = commonConfigService.selectTypes();
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "", list);
    }

    /**
     * 分页查询配置信息
     *
     * @param config 查询参数对象
     * @param page   分页对象
     * @return PageResParams 响应对象
     */
    @PostMapping(value = "/listPage")
    public PageResParams selectPageList(TbCommonConfig config, IPage<TbCommonConfig> page)
    {
        commonConfigService.getCommonConfigList(page, config);
        return PageResParams.newInstance(CoreConstant.SUCCESS_CODE, "", page.getTotal(), page.getRecords());
	}


	/**
	 * 添加配置信息
	 *
	 * @param config  TbCommonConfig实体
	 * @param result  数据绑定结果
	 * @return 返回操作结果
	 */
    @PostMapping("/insert")
    public Object insert(@Valid TbCommonConfig config, BindingResult result)
    {
		if (result.hasErrors()) {
			throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
		}
		if (config.getType().contains(StrUtil.DOT))
		{
			return ResParams.newInstance(CoreConstant.FAIL_CODE, "类型中不能包含'.'符号", null);
		}
        try
        {
            commonConfigService.insertItem(config);
            return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.ADD_SUCCESS, null);
        } catch (Exception e)
        {
            e.printStackTrace();
            return ResParams.newInstance(CoreConstant.SUCCESS_CODE, e.getMessage(), null);
        }
	}


	/**
	 * 修改配置信息
	 *
	 * @param config  TbCommonConfigs实体
	 * @return 返回操作结果
	 */
    @PostMapping("/update")
    public ResParams update(@Valid TbCommonConfig config)
    {
        commonConfigService.updateItem(config);
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.EDIT_SUCCESS, null);
	}

	/**
	 * 批量删除配置信息
	 * @param ids ids字符串
	 * @return  返回操作结果
	 */
    @PostMapping("/batchDelete")
    public ResParams deleteBatch(@RequestParam(required = true) String ids)
    {
        commonConfigService.batchDeleteCommonConfig(Arrays.asList(ids.split(",")));
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.DELETE_SUCCESS, null);
   }


    /**
	 * 类型菜单树查询
	 * @return 返回操作结果
	 */

    @PostMapping("/select")
    public Object selectTree()
    {
        List<String> types = commonConfigService.selectTypes();
        List<Map<String, String>> list = types.stream().map(e -> {
            Map<String, String> map = new HashMap<>();
            map.put("type", e);
            return map;
        }).collect(Collectors.toList());
        Map<String, Object> map = new HashMap<>();
        map.put("type", "类型");
        map.put("open", true);
        map.put("mark", "abcdefg:");
        map.put("children", list);
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "", map);
   }

	/**
	 * 刷新redis缓存信息
	 *
	 * @return 返回操作结果
	 */
    @PostMapping("/refreshCache")
    public Object refreshConfigCache()
    {
        try
        {
            commonConfigService.initialConfigCache();
            return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "刷新成功！", null);
        } catch (Exception e)
        {
            e.printStackTrace();
            return ResParams.newInstance(CoreConstant.FAIL_CODE, "刷新失败！", null);
        }
    }

    @Inject
    public void setCommonConfigService(TbCommonConfigServiceImpl commonConfigService)
    {
        this.commonConfigService = commonConfigService;
    }
}
