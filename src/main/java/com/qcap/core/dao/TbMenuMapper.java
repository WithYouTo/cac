package com.qcap.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qcap.core.entity.TbMenu;
import com.qcap.core.model.MenuTree;
import com.qcap.core.model.ZTreeNode;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author PH
 * @since 2018-07-30
 */
@Repository
public interface TbMenuMapper extends BaseMapper<TbMenu> {
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
