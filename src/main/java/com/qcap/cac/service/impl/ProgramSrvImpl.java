package com.qcap.cac.service.impl;

import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.dao.AreaMapper;
import com.qcap.cac.dao.ProgramMapper;
import com.qcap.cac.dto.DeleteProgramSysFileDto;
import com.qcap.cac.dto.ProgramAddDto;
import com.qcap.cac.dto.ProgramSearchDto;
import com.qcap.cac.entity.TbArea;
import com.qcap.cac.entity.TbProgram;
import com.qcap.cac.exception.BaseException;
import com.qcap.cac.service.CommonSrv;
import com.qcap.cac.service.ProgramSrv;
import com.qcap.cac.tools.EntityTools;
import com.qcap.cac.tools.ToolUtil;
import com.qcap.cac.tools.UUIDUtils;
import com.qcap.core.model.ResParams;
import com.qcap.core.utils.DateUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ProgramSrvImpl implements ProgramSrv {

    @Resource
    private ProgramMapper programMapper;

    @Resource
    private CommonSrv commonSrvImpl;

    @Resource
    private AreaMapper areaMapper;

    @Override
    public List<Map<String, Object>> selectProgram(ProgramSearchDto programSearchDto) {
        // TODO Auto-generated method stub
        return programMapper.selectProgram(programSearchDto);
    }

    @Override
    public void addProgram(ProgramAddDto programAddDto) throws IllegalAccessException, InvocationTargetException {

        //查询项目编号是否被占用
        Map<String, Object> map = new HashMap<>();
        String programCode = programAddDto.getProgramCode();
        map.put("programCode", programCode);
        String programId = this.programMapper.selectIfCodeExist(map);

        if (StringUtils.isEmpty(programId)) {
            //文件
            String fileIds = programAddDto.getFileIds();
            String fileUrls = programAddDto.getFileUrls();

            TbProgram program = new TbProgram();
            BeanUtils.copyProperties(program, programAddDto);
            program.setProgramId(UUIDUtils.getUUID());
            program.setStartTime(DateUtil.stringToDate(programAddDto.getStartTimeStr()));
            program.setEndTime(DateUtil.stringToDate(programAddDto.getEndTimeStr()));
            program.setEffectDate(DateUtil.stringToDate(programAddDto.getEffectDateStr()));
            program.setVersion(0);
            program.setContractFile(fileUrls);
            /**设置新增时间和新增人**/
            EntityTools.setCreateEmpAndTime(program);

            this.programMapper.insertProgram(program);

            //新增区域父级
            insertParentArea(programAddDto);

            //更新文件groupId
            Map<String, Object> param = new HashMap<>();
            param.put("fileIds", fileIds);
            param.put("groupId", programCode);
            commonSrvImpl.updateFileGroupId(param);

        } else {
            throw new BaseException(CommonCodeConstant.ERROR_CODE_40402, "项目编号已存在，请更换项目编号");
        }


    }


    /**
     * 新增区域父级
     *
     * @param
     * @param programAddDto
     * @return void
     * @author 曾欣
     * @date 2018/11/13 19:28
     */
    void insertParentArea(ProgramAddDto programAddDto) {
        TbArea area = new TbArea();
        area.setAreaId(UUIDUtils.getUUID());
        //区域编码
        Integer max = this.areaMapper.selectParentLevelMaxNum();
        area.setAreaCode(ToolUtil.toStr(max));
        area.setAreaName(programAddDto.getProgramName());
        area.setLevel("-1");
        area.setProgramCode(programAddDto.getProgramCode());
        Integer seqNo = this.areaMapper.selectParentLevelMaxSeqNo();
        area.setSeqNo(ToolUtil.toStr(seqNo));
        this.areaMapper.insert(EntityTools.setCreateEmpAndTime(area));

    }

    @Override
    public void editProgram(ProgramAddDto programAddDto) throws IllegalAccessException, InvocationTargetException {

        //查询项目编号是否被占用
        Map<String, Object> map = new HashMap<>();
        String programCode = programAddDto.getProgramCode();
        map.put("programCode", programCode);
        map.put("programId", programAddDto.getProgramId());
        String programId = this.programMapper.selectIfCodeExist(map);

        if (StringUtils.isEmpty(programId)) {
            //文件
            String fileIds = programAddDto.getFileIds();
            String fileUrls = programAddDto.getFileUrls();

            TbProgram program = new TbProgram();
            BeanUtils.copyProperties(program, programAddDto);
            program.setStartTime(DateUtil.stringToDate(programAddDto.getStartTimeStr()));
            program.setEndTime(DateUtil.stringToDate(programAddDto.getEndTimeStr()));
            program.setEffectDate(DateUtil.stringToDate(programAddDto.getEffectDateStr()));
            /**设置更新时间和更新人**/
            EntityTools.setUpdateEmpAndTime(program);
            program.setContractFile(fileUrls);

            this.programMapper.updateProgramByKey(program);


            //先删除文件信息
            if (!StringUtils.isEmpty(fileIds)) {
//				commonSrvImpl.deleteSavedFile(programAddDto.getContractFile());
                commonSrvImpl.deleteSysFileByGroupId(programCode);
                //更新文件groupId
                Map<String, Object> param = new HashMap<>();
                param.put("fileIds", fileIds);
                param.put("groupId", programCode);
                commonSrvImpl.updateFileGroupId(param);
            }
        } else {
            throw new BaseException(CommonCodeConstant.ERROR_CODE_40402, "该项目编号已经被占用，请换个项目编号");
        }


    }

    @Override
    public Object deleteProgram(String programId) {
        if (StringUtils.isEmpty(programId)) {
            return ResParams.newInstance(CommonCodeConstant.PARAM_EMPTY_CODE, "参数为空");
        }
        int areas = this.programMapper.selectIfProgramHaveAreas(programId);
        if(areas >1){
            return ResParams.newInstance(CommonCodeConstant.ERROR_CODE_40402, "该项目下面有子级区域，不允许删除");
        }
        this.programMapper.deleteProgramByKey(programId);
        return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE,CommonCodeConstant.SUCCESS_DELETE_DESC);
    }

    @Override
    public void deleteSysFile(DeleteProgramSysFileDto deleteProgramSysFileDto) {
        // TODO Auto-generated method stub
        this.commonSrvImpl.deleteSysFileByKey(deleteProgramSysFileDto.getFileId());
        this.programMapper.updateContractFile(deleteProgramSysFileDto);
    }

}
