package com.qcap.core.controller;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.qcap.core.entity.TbCommonConfig;
import com.qcap.core.utils.AppUtils;

import cn.afterturn.easypoi.entity.vo.BigExcelConstants;
import cn.afterturn.easypoi.entity.vo.MapExcelConstants;
import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.entity.vo.TemplateExcelConstants;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import cn.afterturn.easypoi.view.PoiBaseView;
import lombok.extern.slf4j.Slf4j;

/**
 * easyPoi示例用法
 *
 * <a href="http://www.afterturn.cn/doc/easypoi.html">官方doc</a>
 *
 * @author yandixuan
 */
@Slf4j
@Controller
@RequestMapping("/demo")
public class PoiController {
	
	private Logger log=AppUtils.getLogger(PoiController.class, true);
	/**
	 * 常规导出EXCEL 结合MODEL 相关注解实现快速导入excel功能 orderNum 用于列表排序
	 *
	 * @param modelMap
	 *            ModelUi
	 * @param request
	 *            request
	 * @param response
	 *            response
	 * @return String {@link NormalExcelConstants#EASYPOI_EXCEL_VIEW}
	 */
	@GetMapping("/export")
	public String export(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {

		List<TbCommonConfig> list = new ArrayList<TbCommonConfig>();
		for (int i = 0; i < 100; i++) {
			TbCommonConfig client = new TbCommonConfig();
			client.setId(Integer.toString(i));
			client.setKeyName(Integer.toString(i));
			client.setKeyValue("哈哈");
			client.setRemark("我菜");
			list.add(client);
		}
		// title给NULL就不会有title
		ExportParams params = new ExportParams(null, "测试", ExcelType.XSSF);
		// 冰冻列数
		params.setFreezeCol(0);
		// 文件名
		modelMap.put(TemplateExcelConstants.FILE_NAME, "demo");
		// 数据集
		modelMap.put(NormalExcelConstants.DATA_LIST, list);
		// 实体类
		modelMap.put(NormalExcelConstants.CLASS, TbCommonConfig.class);
		// EXCEL参数
		modelMap.put(NormalExcelConstants.PARAMS, params);
		return NormalExcelConstants.EASYPOI_EXCEL_VIEW;
	}

	/**
	 * Map 类型导出数据
	 *
	 * @param modelMap
	 *            ModelUi
	 * @param request
	 *            request
	 * @param response
	 *            response
	 */
	@GetMapping("/export1")
	public void export1(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
		List<ExcelExportEntity> entity = new ArrayList<ExcelExportEntity>();
		ExcelExportEntity excelentity = new ExcelExportEntity("姓名", "name");
		excelentity.setNeedMerge(false);
		entity.add(excelentity);
		entity.add(new ExcelExportEntity("性别", "sex"));
		excelentity = new ExcelExportEntity(null, "students");
		List<ExcelExportEntity> temp = new ArrayList<ExcelExportEntity>();
		temp.add(new ExcelExportEntity("姓名", "name"));
		temp.add(new ExcelExportEntity("性别", "sex"));
		excelentity.setList(temp);
		entity.add(excelentity);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		for (int i = 0; i < 10; i++) {
			map = new HashMap<String, Object>();
			map.put("sex", "男");
			map.put("name", "张飞" + i);

			List<Map<String, Object>> tempList = new ArrayList<Map<String, Object>>();
			tempList.add(map);
			tempList.add(map);
			map.put("students", tempList);
			list.add(map);
		}
		ExportParams params = new ExportParams(null, "测试", ExcelType.XSSF);
		params.setFreezeCol(0);
		// 数据集合
		modelMap.put(MapExcelConstants.MAP_LIST, list);
		// 注解集合
		modelMap.put(MapExcelConstants.ENTITY_LIST, entity);
		// 参数
		modelMap.put(MapExcelConstants.PARAMS, params);
		// 文件名称
		modelMap.put(MapExcelConstants.FILE_NAME, "demo1");
		PoiBaseView.render(modelMap, request, response, MapExcelConstants.EASYPOI_MAP_EXCEL_VIEW);
	}

	/**
	 * 大数据导出 7.2 大数据导出View的用法
	 *
	 * @param map
	 *            Map
	 * @param request
	 *            request
	 * @param response
	 *            response
	 */

	@GetMapping("/export2")
	public void export2(ModelMap map, HttpServletRequest request, HttpServletResponse response) {
		ExportParams params = new ExportParams(null, "测试", ExcelType.XSSF);
		params.setFreezeCol(0);
		map.put(BigExcelConstants.FILE_NAME, "big data");
		map.put(BigExcelConstants.CLASS, TbCommonConfig.class);
		map.put(BigExcelConstants.PARAMS, params);
		// 就是我们的查询参数,会带到接口中,供接口查询使用
		// map.put(BigExcelConstants.DATA_PARAMS, new HashMap<String,
		// String>());
		// map.put(BigExcelConstants.DATA_INTER, excelExportServer);
		PoiBaseView.render(map, request, response, BigExcelConstants.EASYPOI_BIG_EXCEL_VIEW);
	}

	/**
	 * 大数据测试
	 *
	 * @throws Exception
	 */
	@GetMapping("/export3")
	public void export3() throws Exception {
		// title给NULL就不会有title
		ExportParams params = new ExportParams(null, "测试", ExcelType.XSSF);
		List<TbCommonConfig> list1 = new ArrayList<TbCommonConfig>();
		Workbook workbook = null;
		List<TbCommonConfig> list = new ArrayList<TbCommonConfig>();
		for (int i = 0; i < 1000000; i++) {
			TbCommonConfig client = new TbCommonConfig();
			client.setId(Integer.toString(i));
			client.setKeyName(Integer.toString(i));
			client.setKeyValue("哈哈");
			client.setRemark("我菜");
			list.add(client);

			if (list.size() == 10000) {
				workbook = ExcelExportUtil.exportBigExcel(params, TbCommonConfig.class, list);
				list.clear();
			}
		}

		FileOutputStream fos = new FileOutputStream("/Users/yandixuan/Downloads/big.xlsx");
		workbook.write(fos);
	}

	@PostMapping("/import")
	public void importExcel(@RequestParam("file") MultipartFile file, HttpServletRequest request,
			HttpServletResponse response) {
		ImportParams importParams = new ImportParams();
		// 设置验证
		importParams.setNeedVerfiy(true);
		try {
			ExcelImportResult<TbCommonConfig> result = ExcelImportUtil.importExcelMore(file.getInputStream(),
					TbCommonConfig.class, importParams);
			FileOutputStream fos = new FileOutputStream("/Users/yandixuan/Downloads/demo_result.xlsx");
			result.getFailWorkbook().write(fos);
			fos.close();
			List<TbCommonConfig> successList = result.getList();
			List<TbCommonConfig> failList = result.getFailList();
			log.info("是否存在验证未通过的数据:" + result.isVerfiyFail());
			log.info("验证通过的数量:" + successList.size());
			log.info("验证未通过的数量:" + failList.size());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
