package com.qcap.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.core.entity.TbManager;

/**
 * <p>
 * 管理员表 Mapper 接口
 * </p>
 *
 * @author NYJ
 * @since 2018-07-29
 */
@Repository
public interface TbManagerMapper extends BaseMapper<TbManager> {
	List<Map<String, Object>> getTbMangerList(IPage page, @Param("map") Map<String, Object> map);

    void updateManagerPwd(TbManager mgr);

    TbManager getMangerByEmployeeCode(@Param("employeeCode")String employeeCode);

    List<String> getprojectCodesByManagerId(@Param("managerId")String managerId);

    void updateManagerDefaultPwd(TbManager mgr);

    TbManager selectManagerByAccount(@Param("account")String account);
}
