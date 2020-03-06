package com.feng.companyframe.services;

import com.feng.companyframe.bean.SysLog;
import com.feng.companyframe.vo.req.LogPageReqVO;
import com.feng.companyframe.vo.resp.PageRespVO;
import java.lang.String;

import java.util.List;

/**
 * @ClassName: LogService
 * @Description： 描述
 * @createTime: 2020/2/28 19:14
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
public interface LogService {

    /**
     * 获取日志分页信息
     * @param vo
     * @return
     */
    PageRespVO<SysLog> getLogPageInfo(LogPageReqVO vo);

    /**
     * 批量删除和 单个删除  合二为一
     * @param logIds
     * @return
     */
    int deleteLog(List<String> logIds);
}

