package com.qcap.cac.common.dao;

import java.util.List;
import java.util.Map;

import com.qcap.cac.model.TbSysFile;

public interface TbSysFileMapper {
   

    /**
     * 根据主键删除附件
     * @Title: deleteByPrimaryKey 
     * @Description: TODO
     * @param tbFileId
     * @return
     * @return: int
     */
    void deleteByPrimaryKey(String tbFileId);
    
    /**
     * 根据分组ID删除附件
     * @Title: deleteByPrimaryKey 
     * @Description: TODO
     * @param tbFileId
     * @return
     * @return: int
     */
    void deleteByGroupId(String groupId);

    /**
     * 新增附件
     * @Title: insert 
     * @Description: TODO
     * @param record
     * @return: void
     */
    void insert(TbSysFile record);

    /**
     * 根据主键查询附件
     * @Title: selectByPrimaryKey 
     * @Description: TODO
     * @param tbFileId
     * @return
     * @return: TbSysFile
     */
    TbSysFile selectByPrimaryKey(String tbFileId);

    /**
     * 更新附件
     * @Title: updateByPrimaryKey 
     * @Description: TODO
     * @param record
     * @return: void
     */
    void updateByPrimaryKey(TbSysFile record);
    
    /**
     * 根据  分组ID查询附件
     * @Title: selectFileByGroupId 
     * @Description: TODO
     * @param groupId
     * @return
     * @return: List<Map>
     */
    List<Map> selectFileByGroupId(String groupId);
    
}