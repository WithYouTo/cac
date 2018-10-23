package com.qcap.cac.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.qcap.cac.dto.TempTaskSearchParam;
import com.qcap.cac.entity.TbTask;

@Repository
public interface TempTaskMapper {
	/**
	 * 分页查询任务
	 * 
	 * @Title: listTask
	 * @Description: TODO
	 * @param paramDto
	 * @return
	 * @return: List<Map>
	 */
	List<Map<String,Object>> listTask(TempTaskSearchParam paramDto);

	/**
	 * 新增任务
	 * 
	 * @Title: insertTempTask
	 * @Description: TODO
	 * @param task
	 * @return: void
	 */
	void insertTempTask(TbTask task);

	/**
	 * 批量新增任务
	 * 
	 * @Title: insertTaskBatch
	 * @Description: TODO
	 * @param list
	 * @return: void
	 */
	void insertTaskBatch(List<TbTask> list);

	/**
	 * 修改任务
	 * 
	 * @Title: updateTempTask
	 * @Description: TODO
	 * @param task
	 * @return: void
	 */
	void updateTempTask(TbTask task);

	/**
	 * 取消任务前查询任务状态
	 * 
	 * @Title: selectTaskStatus
	 * @Description: TODO
	 * @param taskCode
	 * @return
	 * @return: String
	 */
	String selectTaskStatus(String taskCode);

	/**
	 * 将临时任务状态更改为取消
	 * 
	 * @Title: deleteTempTask
	 * @Description: TODO
	 * @param taskCode
	 * @return: void
	 */
	void deleteTempTask(String taskCode);

	/**
	 * 查询标准下拉列表
	 * 
	 * @Title: selectStandardItem
	 * @Description: TODO
	 * @param standardCode
	 * @return
	 * @return: List<Map>
	 */
	List<Map<String,Object>> selectStandardItem(@Param("standardCode") String standardCode);

	/**
	 * 查询区域树形下拉列表
	 * 
	 * @Title: selectAreaItem
	 * @Description: TODO
	 * @return
	 * @return: List<Map>
	 */
	List<Map<String, Object>> selectAreaItem();

	/**
	 * 根据区域编码查询岗位信息
	 * 
	 * @Title: selectPositionInfoByAreaCode
	 * @Description: TODO
	 * @param areaCode
	 * @return
	 * @return: Map
	 */
	Map<String,Object> selectPositionInfoByAreaCode(String areaCode);

	/**
	 * 查询当班值班人员
	 * 
	 * @Title: selectWorkingEmployee
	 * @Description: TODO
	 * @param map
	 * @return
	 * @return: List<Map>
	 */
	List<Map<String,Object>> selectWorkingEmployee(Map<String,Object> map);

	/**
	 * 查询班次:**注意传入时间格式严格为：hh:mm:ss
	 * 
	 * @Title: selectShiftByTime
	 * @Description: TODO
	 * @param startTime
	 * @return
	 * @return: Map<String,String>
	 */
	Map<String, String> selectShiftByTime(String startTime);
	/**
	 * 查询所有人员
	 * @Title: selectAllEmployee 
	 * @Description: TODO
	 * @param monthNo
	 * @return
	 * @return: List<Map<String,Object>>
	 */
	List<Map<String, Object>> selectAllEmployee (@Param("monthNo") String monthNo);

	List<Map<String, Object>> selectCurrountWorkingEmployee(Map<String, Object> map);
}
