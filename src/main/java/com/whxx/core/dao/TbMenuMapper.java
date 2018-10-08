package com.whxx.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whxx.core.entity.TbMenu;
import com.whxx.core.model.MenuTree;
import com.whxx.core.model.ZTreeNode;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author PH
 * @since 2018-07-30
 */
@Repository
public interface TbMenuMapper extends BaseMapper<TbMenu>
{
    List<MenuTree> getMenuTreeListByManagerId(@Param("managerId") String managerId);

    List<ZTreeNode> getMenuTreeList();

    List<ZTreeNode> getMenuTreeListByMenuCode(List<Long> list);


    @Select("select max(t.num + 1) from tb_menu t")
    Integer selectMaxNum();

    @Select("select max(code) from tb_menu WHERE parent_code = #{parentCode}")
    String selectMaxCodeByParentCode(@Param("parentCode") String parentCode);

    @Select("select max(t.seq) from tb_menu t where t.parent_code=#{parentCode}")
    Integer selectMaxSeqByParentCode(@Param("parentCode") String parentCode);
}
