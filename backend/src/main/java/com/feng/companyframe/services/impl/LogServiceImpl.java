package com.feng.companyframe.services.impl;

import com.feng.companyframe.bean.SysLog;
import com.feng.companyframe.mapper.SysLogMapper;
import com.feng.companyframe.services.LogService;
import com.feng.companyframe.utils.PageUtil;
import com.feng.companyframe.vo.req.LogPageReqVO;
import com.feng.companyframe.vo.resp.PageRespVO;
import com.github.pagehelper.PageHelper;
import java.lang.String;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: LogServiceImpl
 * @Description： 描述
 * @createTime: 2020/2/28 19:17
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
@Slf4j
@Service
public class LogServiceImpl implements LogService {

    @Resource
    private SysLogMapper sysLogMapper;

    /**
     * 获取日志分页信息
     * @param vo
     * @return
     */
    @Override
    public PageRespVO<SysLog> getLogPageInfo(LogPageReqVO vo) {
        PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
        List<SysLog> allLog = sysLogMapper.getAllLog(vo);
        return PageUtil.getPageVO(allLog);
    }

    /**
     * 批量删除和 单个删除  合二为一
     * @param logIds
     * @return
     */
    @Override
    public int deleteLog(List<String> logIds) {
        return sysLogMapper.batchDeletedLog(logIds);
    }
}

