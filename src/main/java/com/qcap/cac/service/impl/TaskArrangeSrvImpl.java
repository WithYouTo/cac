package com.qcap.cac.service.impl;

import com.mysql.fabric.xmlrpc.base.Array;
import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.constant.CommonConstant;
import com.qcap.cac.dao.TaskArrangeMapper;
import com.qcap.cac.dto.TaskArrangeDto;
import com.qcap.cac.dto.TaskArrangeSearchDto;
import com.qcap.cac.entity.TbTaskArrangement;
import com.qcap.cac.exception.BaseException;
import com.qcap.cac.poiEntity.TaskArrangeUploadEntity;
import com.qcap.cac.service.TaskArrangeSrv;
import com.qcap.cac.tools.EntityTools;
import com.qcap.cac.tools.UUIDUtils;
import com.qcap.core.model.ResParams;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Service
@Transactional
public class TaskArrangeSrvImpl implements TaskArrangeSrv {

    @Resource
    private TaskArrangeMapper taskArrangeMapper;

    @Override
    public Object importTaskArrange(List<TaskArrangeUploadEntity> list, TaskArrangeDto taskArrangeDto, int row) throws IllegalAccessException, InvocationTargetException {

        Map<String, List<Boolean>> map = new HashMap<>();

        String temporaryVal = null;

        String monthNo = taskArrangeDto.getMonth();
        //获取制定月份的天数
        int dateNum = getMonthDaysNum(monthNo);
        //初始化开关List
        List<Boolean> initBooleanList = getBooleanList(monthNo,dateNum);
        //排班表对象list
        List<TbTaskArrangement> taskArrangeList = new ArrayList<>();
        // 1、Excel表格List排序
        sortList(list);
        //遍历Excel表格List
        Map<String,String> empMap = new HashMap<>();
        int count=0;
        for (TaskArrangeUploadEntity entity : list) {
            count ++;
            /**
             * 1、验证岗位
             */
            String positionCode = entity.getPositionCode();
            String positionName = entity.getPositionName();
            Map<String, String> param = new HashMap<>();
            param.put("positionCode", positionCode);
            param.put("positionName", positionName);
            String positionId = this.taskArrangeMapper.queryIfPositionExist(param);
            if (StringUtils.isBlank(positionId)) {
                throw new BaseException(CommonCodeConstant.ERROR_CODE_40402, "岗位：" + positionName + "-" + positionCode + "不存在");
            }

            /**
             * 2、验证工号
             */
            String employeeCode = entity.getEmployeeCode();
            String employeeName = entity.getEmployeeName();
            param.put("employeeCode", employeeCode);
            param.put("employeeName", employeeName);
            String newEmployeeCode = this.taskArrangeMapper.queryIfEmployeeExist(param);
            if (StringUtils.isBlank(newEmployeeCode)) {
                throw new BaseException(CommonCodeConstant.ERROR_CODE_40402, "用户：" + employeeName + "-" + employeeCode + "不存在");
            }

            /**
             * 3、验证该岗位是否每天都有人上班
             */
            //初始化开关Map
            List<Boolean> switchList =  new ArrayList<>();
            switchList.addAll(initBooleanList);
            if (MapUtils.isEmpty(map)) {
                map.put(positionCode, switchList);
            } else if (!map.containsKey(positionCode)) {
                map.put(positionCode, switchList);
            }

            //在对下一个岗位数据进行处理前，先验证上个岗位每天是否有人上班
            if(temporaryVal !=null && !temporaryVal.equals(positionCode)){
                List<Boolean> booleanList = map.get(temporaryVal);
                for (int i = 0; i < booleanList.size(); i++) {
                    if(!booleanList.get(i)){
                        throw new BaseException(CommonCodeConstant.ERROR_CODE_40402,"岗位为：" + positionName + "-" + positionCode + "的记录"+(i+1)+"号没有上班人员");
                    }
                }
            }

            //改变初始booleanList的值
            if (map.containsKey(positionCode)) {
                //暂存变量赋值
                temporaryVal = positionCode;
                Map<String,String> workDayMap = getDayWorkingDetailMap(entity);
                List<Boolean> booleanList = map.get(positionCode);
                for (int i = 0; i < booleanList.size(); i++) {
                    String workingCondition= workDayMap.get("day"+(i+1));
                    if(StringUtils.isNotBlank(workingCondition) && !"休".equals(workingCondition)){
                        booleanList.set(i,true);
                    }

                }
            }


            TbTaskArrangement taskArrangement = new TbTaskArrangement();
            BeanUtils.copyProperties(taskArrangement, entity);
            BeanUtils.copyProperties(taskArrangement, taskArrangeDto);
            taskArrangement.setArrangementId(UUIDUtils.getUUID());
            taskArrangement.setDeleteFlag(CommonConstant.DELETE_FLAG_NORMAL);
            /**设置新增时间和新增人**/
            EntityTools.setCreateEmpAndTime(taskArrangement);
            taskArrangement.setVersion(0);
            taskArrangeList.add(taskArrangement);
            
            //验证同一个人是否导入两条数据
            if(!empMap.containsKey(employeeCode)) {
            	empMap.put(employeeCode, employeeCode);
            }else {
            	throw new BaseException(CommonCodeConstant.ERROR_CODE_40402, "用户：" + employeeName + "-" + employeeCode + "存在多条排版记录");
            }

            //遍历到最后一个岗位时，验证该岗位每天是否有人上班
            if(list.size() == count ){
                List<Boolean> booleanList = map.get(positionCode);
                for (int i = 0; i < booleanList.size(); i++) {
                    if(!booleanList.get(i)){
                        throw new BaseException(CommonCodeConstant.ERROR_CODE_40402,"岗位为：" + positionName + "-" + positionCode + "的记录"+(i+1)+"号没有上班人员");
                    }
                }
            }
        }

        // 2、删除上次导入的数据
        this.taskArrangeMapper.deleteTaskArrange(taskArrangeDto);

        //3、新增排版表到数据库
        this.taskArrangeMapper.insertTaskArrangeBatch(taskArrangeList);

        return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, "导入排班表成功");
    }

    private void sortList(List<TaskArrangeUploadEntity> list) {
        list.sort(new Comparator<TaskArrangeUploadEntity>() {
            @Override
            public int compare(TaskArrangeUploadEntity o1, TaskArrangeUploadEntity o2) {
                return o1.getPositionCode().compareToIgnoreCase(o2.getPositionCode());
            }
        });
    }

    @Override
    public List<Map<String, Object>> listTaskArrange(TaskArrangeSearchDto taskArrangeSearchDto) {
        // TODO Auto-generated method stub
        return taskArrangeMapper.listTaskArrange(taskArrangeSearchDto);
    }

    @Override
    public List<Map<String, String>> selectPositionItem() {
        return this.taskArrangeMapper.selectPositionItem();
    }

    private List<Boolean> getBooleanList(String monthNo,int dateNum) {

        List<Boolean> booleanList = new ArrayList<>();
        for (int i = 0; i < dateNum; i++) {
            booleanList.add(false);
        }
        return booleanList;
    }

    private int getMonthDaysNum(String monthNo) {
        int year = Integer.valueOf(monthNo.substring(0, 4));
        int month = Integer.valueOf(monthNo.substring(4));
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DATE, 1);//设置为下月第一天
        calendar.roll(Calendar.DATE, -1);//回滚一天到当月月底
        return calendar.get(Calendar.DATE);
    }

    /**
     * 获取排班表对象中day1至day31的数据，并转换为Map
     * @param obj
     * @return
     */
    private static Map<String, String> getDayWorkingDetailMap(Object obj) {

        Map<String, String> map = new HashMap<String, String>();
        // System.out.println(obj.getClass());
        // 获取f对象对应类中的所有属性域
        Field[] fields = obj.getClass().getDeclaredFields();
        for (int i = 0, len = fields.length; i < len; i++) {
            String varName = fields[i].getName();
            varName = varName.toLowerCase();//将key置为小写，默认为对象的属性
            try {
                // 获取原来的访问控制权限
                boolean accessFlag = fields[i].isAccessible();
                // 修改访问控制权限
                fields[i].setAccessible(true);
                // 获取在对象f中属性fields[i]对应的对象中的变量
                Object o = fields[i].get(obj);
                if (o != null && varName.indexOf("day") == 0 ){
                    map.put(varName, o.toString());
                }
                // System.out.println("传入的对象中包含一个如下的变量：" + varName + " = " + o);
                // 恢复访问控制权限
                fields[i].setAccessible(accessFlag);
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }
        return map;

    }


}
