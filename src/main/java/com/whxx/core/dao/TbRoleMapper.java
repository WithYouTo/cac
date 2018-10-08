package com.whxx.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whxx.core.entity.TbRole;
import com.whxx.core.model.ZTreeNode;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author PH
 * @since 2018-07-31
 */
@Repository
public interface TbRoleMapper extends BaseMapper<TbRole>
{
    /**
     * num最大值
     *
     * @return int
     */
    @Select("SELECT MAX(num+1) from tb_role")
    int selectMaxNum();

    List<ZTreeNode> getRoleTreeListByManagerId(@Param("managerId") String managerId);

    List<TbRole> getRoleListByManagerId(@Param("managerId") String managerId);

}
