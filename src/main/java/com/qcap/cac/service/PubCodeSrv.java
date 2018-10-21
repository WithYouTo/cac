package com.qcap.cac.service;

import java.util.List;
import java.util.Map;

import com.qcap.cac.entity.Tbpubcode;
import com.qcap.cac.entity.Tbpubcode01;

@SuppressWarnings("rawtypes")
public interface PubCodeSrv {

	/**
	 * 获取合同模板存储基础路径
	 * 
	 * @return
	 */
    String getFileBasePath();

	/**
	 * 查询一级编码 分页
	 * 
	 * @Title: selectPubCodeByPage
	 * @param param
	 * @return
	 * @return: List<Map>
	 */
    List<Map> selectPubCodeByPage(Map param);

	/**
	 * 查询二级编码分页
	 * 
	 * @Title: selectPubCode01ByPage
	 * @param param
	 * @return
	 * @return: List<Map>
	 */
    List<Map> selectPubCode01ByPage(Map param);

	/**
	 * 新增一级编码
	 * 
	 * @param map
	 * @throws Exception
	 */
    void insertPubCode(Tbpubcode tbpubCode);

	/**
	 * 新增二级编码
	 * 
	 * @param map
	 * @throws Exception
	 */
    void insertPubCode01(Tbpubcode01 tbpubCode01);

	/**
	 * 查询编码下拉框
	 * 
	 * @param map
	 * @throws Exception
	 */
    List<Map> selectConfigCode(Map map);

	/**
	 * 查询App编码下拉框
	 * 
	 * @param map
	 * @throws Exception
	 */
    List<Map> selectConfigCodeApp(Map map);

	/**
	 * 查询一级项目是否有二级项目
	 * 
	 * @param map
	 * @throws Exception
	 */
    List<Map> selectIfDelete(String tbpubcodeId);

	/**
	 * 查询二级项目顺序号重复
	 * 
	 * @param map
	 * @throws Exception
	 */
    String selectRepeatSeq(Map param);

	/**
	 * 根据查询编码重复
	 * 
	 * @param map
	 * @throws Exception
	 */
    String selectRepeatCode(Map param);

	/**
	 * 根据id查询一级编码
	 * 
	 * @param map
	 * @throws Exception
	 */
    Map selectPubCodeById(String tbpubcodeId);

	/**
	 * 根据id查询二级编码
	 * 
	 * @param map
	 * @throws Exception
	 */
    Map selectPubCode01ById(String tbpubcode01Id);

	/**
	 * 修改一级编码
	 * 
	 * @param map
	 * @throws Exception
	 */
    void updatePubCode(Tbpubcode tbpubCode);

	/**
	 * 修改二级编码
	 * 
	 * @param map
	 * @throws Exception
	 */
    void updatePubCode01(Tbpubcode01 tbpubCode01);

	/**
	 * 删除一级编码
	 * 
	 * @param map
	 * @throws Exception
	 */
    void deletePubCode(String tbpubcodeId);

	/**
	 * 删除二级编码
	 * 
	 * @param map
	 * @throws Exception
	 */
    void deletePubCode01(String tbpubcode01Id);

}