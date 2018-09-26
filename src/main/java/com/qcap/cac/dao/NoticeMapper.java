package com.qcap.cac.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeMapper {
	//删除图片
	void deleteImg(Map param);
	//修改活动图
	void updateImg(Map map);
	//查询图片
	List<Map<String, Object>> getImg(Map param);
	//通用代码档：公告范围
	List<Map<String, Object>> queryNoticeApp();
	//通用代码档：公告类型
	List<Map<String, Object>> queryNoticeType();
	//查询公告信息
	List<Map<String, Object>> query(Map map);
	//新增
	void add(Map map);
	//修改
	void update(Map map);
	//删除
	void delete(String[] map);
}
