package com.whxx.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whxx.core.entity.TbOrg;
import com.whxx.core.model.ZTreeNode;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author PH
 * @since 2018-08-01
 */
@Repository
public interface TbOrgMapper extends BaseMapper<TbOrg>
{
    List<ZTreeNode> getOrgTreeList();

    List<ZTreeNode> getOrgTreeListByManagerId(@Param("managerId") String managerId);


    @Select("SELECT max(t.num+0) FROM tb_org t")
    int selectMaxNum();

    @Select("select max(t.seq+1) from tb_org t where t.parent_code=#{parentCode}")
    int getMaxSeqByParentCode(String parentCode);

    @Select("select max(t.code) from tb_org t where t.parent_code=#{parentCode}")
    String getMaxCodeByParentCode(String parentCode);
}
