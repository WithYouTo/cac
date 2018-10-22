package com.qcap.cac.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.cac.constant.CommonConstant;
import com.qcap.cac.dao.EquipMaintMapper;
import com.qcap.cac.dao.EquipMapper;
import com.qcap.cac.dao.EquipPartsMapper;
import com.qcap.cac.dao.EquipPlanMapper;
import com.qcap.cac.dto.EquipMaintInsertDto;
import com.qcap.cac.dto.EquipMaintSearchDto;
import com.qcap.cac.entity.TbEquip;
import com.qcap.cac.entity.TbEquipMaint;
import com.qcap.cac.entity.TbEquipParts;
import com.qcap.cac.entity.TbEquipPlan;
import com.qcap.cac.service.EquipMaintSrv;
import com.qcap.cac.tools.UUIDUtils;
import com.qcap.core.utils.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class EquipMaintSrvImpl implements EquipMaintSrv {

    @Autowired
    private EquipMapper equipMapper;

    @Autowired
    private EquipMaintMapper equipMaintMapper;

    @Autowired
    private EquipPartsMapper equipPartsMapper;

    @Autowired
    private EquipPlanMapper equipPlanMapper;

    @Override
    public void listEquipMaint(IPage<Map<String, Object>> page, EquipMaintSearchDto equipMaintSearchDto) {
        List<Map<String,Object>> list = this.equipMaintMapper.listEquipMaint(page,equipMaintSearchDto);
        page.setRecords(list);
    }

    @Override
    public void insertEquipMaint(EquipMaintInsertDto equipMaintInsertDto){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        String maintType= equipMaintInsertDto.getMaintType();
        TbEquipMaint equipMaint = new TbEquipMaint();
        TbEquipPlan equipPlan = new TbEquipPlan();
        try {
            //mybatis-plus封装查询条件，根据设备编号获取设备信息
            QueryWrapper<TbEquip> equip = new QueryWrapper();
            equip.eq("equip_No", equipMaintInsertDto.getEquipNo());
            TbEquip equipInfo=this.equipMapper.selectOne(equip);

            if(equipInfo != null){
                //1、重组维保记录对象
                BeanUtils.copyProperties(equipInfo,equipMaint);
                BeanUtils.copyProperties(equipInfo,equipPlan);
                equipMaint.setEquipType(CommonConstant.MAINT_TYPE_EQUIP);
                Date time = format.parse(equipMaintInsertDto.getMaintTime());


                equipMaint.setMaintTime(time);
                equipMaint.setEquipCycle(equipInfo.getMaintCycle());

                //若维保类型是配件，继续查询配件信息
                if((CommonConstant.MAINT_TYPE_PARTS).equals(maintType)){
                    //mybatis-plus封装查询条件，根据设备编号获取设备信息
                    QueryWrapper<TbEquipParts> parts = new QueryWrapper();
                    parts.eq("PARTS_NO", equipMaintInsertDto.getPartsNo());
                    TbEquipParts equipParts = this.equipPartsMapper.selectOne(parts);

                    BeanUtils.copyProperties(equipParts,equipMaint);
                    BeanUtils.copyProperties(equipParts,equipPlan);
                    equipMaint.setEquipType(CommonConstant.MAINT_TYPE_PARTS);
                }

                //2、新增一条维保记录
                equipMaint.setEquipMaintId(UUIDUtils.getUUID());
                this.equipMaintMapper.insert(equipMaint);

                //3、更新维保计划
                updateEquipPlanTime(equipMaintInsertDto.getMaintTime(),equipPlan);
            }
        } catch (ParseException e) {
//                e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     *
     * @Description: 更新最近维保时间和下次维保时间
     *
     *
     * @MethodName: updateEquipPlanTime
     * @Parameters: [maintTime, equipPlan]
     * @ReturnType: void
     *
     * @author huangxiang
     * @date 2018/10/14 17:07
     */
    private void updateEquipPlanTime(String maintTime,TbEquipPlan equipPlan) {
        Date time = DateUtil.parseDate(maintTime);
        Date nextTime = getNewPlanDate(time,equipPlan.getMaintCycle());
        equipPlan.setNextMaintTime(nextTime);
        equipPlan.setLatestMaintTime(time);
//        equipPlan.setLatestMaintDate(time);
//        equipPlan.setNextMaintDate(getNewPlanDate(time,equipPlan.getMaintCycle()));
        this.equipPlanMapper.updateEquipPlan(equipPlan);
    }

    /**
     *
     * @Description: 获取下次维保时间
     *
     *
     * @MethodName: getNewPlanDate
     * @Parameters: [time, maintCycle]
     * @ReturnType: java.util.Date
     *
     * @author huangxiang
     * @date 2018/10/13 10:42
     */
    private Date getNewPlanDate(Date time, String maintCycle){
        Calendar c = Calendar.getInstance();
        //设置时间
        c.setTime(time);
        //日期分钟加1,Calendar.DATE(天),Calendar.HOUR(小时)
        c.add(Calendar.HOUR, Integer.parseInt(maintCycle));
        //结果
        Date date = c.getTime();
        return date;
    }
}
