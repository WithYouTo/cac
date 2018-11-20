package com.qcap.cac.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.cac.dto.WarehouseEntryDto;
import com.qcap.cac.entity.TbWarehouseEntry;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cindy
 * @since 2018-10-09
 */
public interface WarehouseEntryMapper extends BaseMapper<TbWarehouseEntry> {

    List<Map<String, Object>> getWarehouseEntryList(IPage<Map<String, Object>> page,@Param("obj") @Valid WarehouseEntryDto warehouseEntryDto);


    /**
     *
     * 根据项目编号查询储藏室下拉框
     * @author 曾欣
     * @date 2018/11/12 14:05
     * @param
     * @param paramMap
     * @return java.util.List<java.util.Map>
     */

    List<Map> getStoreRoomList(Map<String,Object> paramMap);

    /**
     * 根据项目编号查询储藏室下拉框
     * @param programCode
     * @return
     */
    List<Map<String,String>> getStoreRoomListByProgramCode(String programCode);

    @Select("select AREA_ID from tb_area WHERE AREA_NAME = #{storeRoom} and PROGRAM_CODE = #{programCode} and AREA_TYPE = #{areaType}")
    String selecStoreRoomId(@Param("storeRoom") String storeRoom,@Param("programCode") String programCode,@Param("areaType") String areaType);


    @Select("select PROGRAM_CODE from tb_program WHERE PROGRAM_NAME = #{programName}")
    String existProgramCodeByName(String programName);

}
