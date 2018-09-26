package com.qcap.cac.common.dao;

import java.util.List;
import java.util.Map;

import com.qcap.cac.model.TbMsg;

/** 
 * @ClassName: WebMsgSendMapper 
 * @Description: TODO
 * @author: baojianxing
 * @date: 2018年8月10日 下午3:12:50  
 */
public interface WebMsgSendMapper {


	 /**
    /*批量新增实体TbMsg   
    */
    void insertTbMsgBatch(List<TbMsg> records);
    
    /**
     * 查询角色下的用户   id
     * @Title: selectUserIdListByRole 
     * @Description: TODO
     * @param param
     * @return
     * @return: List<String>
     */
    List<String>  selectUserIdListByRole(Map param);
}
