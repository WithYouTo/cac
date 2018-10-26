package com.qcap.cac.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.cac.dto.AttenceSearchDto;
import com.qcap.cac.entity.TbAttence;

/**
 *
 * @Description: 考勤Mapper
 * @author huangxiang
 * @date 2018/10/11 20:27
 */
@Repository
public interface AttenceMapper extends BaseMapper<TbAttence> {
	/**
	 *
	 * @Description: 获取考勤列表
	 *
	 *
	 * @MethodName: listAttence
	 * @Parameters: [attenceSearchDto]
	 * @ReturnType: java.util.List<java.util.Map
	 *              <java.lang.String,java.lang.Object>>
	 *
	 * @author huangxiang
	 * @date 2018/10/11 20:27
	 */
	List<Map<String, Object>> listAttence(IPage<Map<String, Object>> page,
			@Param("obj") AttenceSearchDto attenceSearchDto);

}
