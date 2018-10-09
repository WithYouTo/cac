package com.qcap.core.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.qcap.core.model.Dict;

public interface DictSrv {
	Dict selectById(String dictId);

	Dict selectByPidAndNum(@Param("pid") String pid, @Param("num") String num);

	void addDict(String dictName, String dictValues);

	List<Map<String, Object>> list(String condition);

	void editDict(String dictId, String dictName, String dictValues);

	void delteDict(String dictId);

	List<Dict> selectListByPid(String dictId);
}
