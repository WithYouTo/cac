package com.whxx.core.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.whxx.core.dao.TbOperationLogMapper;
import com.whxx.core.entity.TbOperationLog;
import com.whxx.core.service.ITbOperationLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Map;

/**
 * <p>
 * 操作日志 服务实现类
 * </p>
 *
 * @author PH
 * @since 2018-08-02
 */
@Service
public class TbOperationLogServiceImpl implements ITbOperationLogService
{
    @Resource
    private TbOperationLogMapper tbOperationLogMapper;

    @Override
    public void getOperationLogList(IPage<TbOperationLog> page, Map<String, String> params)
    {
        String startTime = params.get("startTime");
        String endTime = params.get("endTime");
        String logName = params.get("logName");
        String logType = params.get("logType");
        QueryWrapper<TbOperationLog> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotEmpty(startTime))
        {
            wrapper.lambda().ge(TbOperationLog::getCreateTime, startTime);
        }
        if (StrUtil.isNotEmpty(endTime))
        {
            wrapper.lambda().le(TbOperationLog::getCreateTime, endTime);
        }
        if (StrUtil.isNotEmpty(logName))
        {
            wrapper.lambda().like(TbOperationLog::getLogName, logName + "%");
        }
        if (StrUtil.isNotEmpty(logType))
        {
            wrapper.lambda().like(TbOperationLog::getLogType, logType + "%");
        }
        tbOperationLogMapper.selectPage(page, wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertItem(TbOperationLog operationLog)
    {
        tbOperationLogMapper.insert(operationLog);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchDeleteOperationLog(Collection<String> ids)
    {
        tbOperationLogMapper.deleteBatchIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteOperationLogAll()
    {
        tbOperationLogMapper.delete(new UpdateWrapper<>());
    }
}
