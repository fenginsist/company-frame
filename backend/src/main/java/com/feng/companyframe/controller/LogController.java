package com.feng.companyframe.controller;

import com.feng.companyframe.aop.annotation.MyLog;
import com.feng.companyframe.bean.SysLog;
import com.feng.companyframe.services.LogService;
import com.feng.companyframe.utils.DataResult;
import com.feng.companyframe.vo.req.LogPageReqVO;
import com.feng.companyframe.vo.resp.PageRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: LogController
 * @Description： 描述
 * @createTime: 2020/2/28 19:19
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
@Api(tags = "系统模块-系统操作日志管理")
@RestController
@RequestMapping("/api")
public class LogController {

    @Resource
    private LogService logService;

    @ApiOperation(value = "分页查询系统操作日志接口")
    @MyLog(title = "系统操作日志管理",action = "分页查询系统操作日志")
    @RequiresPermissions("sys:log:list")
    @PostMapping("/getLogs")
    public DataResult<PageRespVO<SysLog>> pageInfo(@RequestBody LogPageReqVO vo){
        PageRespVO<SysLog> sysLogPageVO = logService.getLogPageInfo(vo);
        DataResult<PageRespVO<SysLog>> result=DataResult.success();
        result.setData(sysLogPageVO);
        return result;
    }

    @ApiOperation(value = "删除日志接口")
    @MyLog(title = "系统操作日志管理",action = "删除系统操作日志")
    @RequiresPermissions("sys:log:delete")
    @DeleteMapping("/deleteLogs")
    public DataResult deleted(@RequestBody List<String> logIds){
        DataResult result=DataResult.success();
        result.setData(logService.deleteLog(logIds));
        return result;
    }


}

