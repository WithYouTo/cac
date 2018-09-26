package com.qcap.core.dao;

import com.qcap.core.model.Dict;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DictMapper {

	Dict selectById(@Param("id") String dictId);

	List<Dict> selectList(@Param("name") String dictName);

	void insert(Dict dict);

	List<Map<String, Object>> list(@Param("condition") String condition);

	void deleteByPid(@Param("id") String dictId);

	void deleteById(@Param("id") String dictId);

	List<Dict> selectListByNameAndPid(@Param("name") String dictName, @Param("pid") String pid);

	List<Dict> selectListByPid(@Param("pid") String id);

	Dict selectByPidAndNum(@Param("pid") String pid, @Param("num") String num);

    List<Dict> selectListByCode(@Param("code") String code);
}
