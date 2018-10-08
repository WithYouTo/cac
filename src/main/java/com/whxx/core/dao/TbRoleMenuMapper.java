package com.whxx.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whxx.core.entity.TbRoleMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 角色和菜单关联表 Mapper 接口
 * </p>
 *
 * @author PH
 * @since 2018-07-31
 */
@Repository
public interface TbRoleMenuMapper extends BaseMapper<TbRoleMenu>
{
    List<TbRoleMenu> getRoleMenuByRoleId(@Param("roleId") String roleId);

    void updateRoleMenuByMenuCode(@Param("oldCode") String oldCode, @Param("newCode") String newCode);
}
