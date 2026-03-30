package com.feng.companyframe.mapper;

import com.feng.companyframe.bean.SysLog;
import com.feng.companyframe.vo.req.LogPageReqVO;

import java.util.List;

public interface SysLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysLog record);

    int insertSelective(SysLog record);

    SysLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysLog record);

    int updateByPrimaryKey(SysLog record);

    /**
     * 获取所有日志数据
     * @param vo
     * @return
     */
    List<SysLog> getAllLog(LogPageReqVO vo);

    /**
     * 批量删除和 单个删除  合二为一
     * @param logIds
     * @return
     */
    int batchDeletedLog(List<String> logIds);
}