package com.feng.companyframe.controller;

import com.feng.companyframe.aop.annotation.MyLog;
import com.feng.companyframe.bean.SysDept;
import com.feng.companyframe.services.DeptService;
import com.feng.companyframe.utils.DataResult;
import com.feng.companyframe.vo.req.DeptAddReqVO;
import com.feng.companyframe.vo.req.DeptUpdateReqVO;
import com.feng.companyframe.vo.resp.DeptRespNodeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName: DeptController
 * @Description： 描述
 * @createTime: 2020/2/18 19:13
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
@Api(tags = "组织管理-部门管理", value = "部门管理相关接口")
@RestController
@RequestMapping(value = "/api")
public class DeptController {

    @Resource
    private DeptService deptService;

    @ApiOperation(value = "查询所有部门数据接口")
    @MyLog(title = "组织管理-部门管理",action = "查询所有部门数据接口")
    @RequiresPermissions("sys:dept:list")
    @GetMapping(value = "/depts")
    public DataResult<List<SysDept>> getAllDept(){
        DataResult result = DataResult.success();
        result.setData(deptService.getAllDept());
        return result;
    }

    @ApiOperation(value = "树型部门列表接口")
    @MyLog(title = "组织管理-部门管理",action = "树型部门列表接口")
    @RequiresPermissions(value = {"sys:user:update","sys:user:add","sys:dept:add","sys:dept:update"},logical = Logical.OR)
    @GetMapping("/dept/tree")
    public DataResult<List<DeptRespNodeVO>> getTree(@RequestParam(required = false) String deptId){
        DataResult<List<DeptRespNodeVO>> result=DataResult.success();
        result.setData(deptService.deptTreeList(deptId));
        return result;
    }

    @ApiOperation(value = "新增部门接口")
    @MyLog(title = "组织管理-部门管理",action = "新增部门接口")
    @RequiresPermissions("sys:dept:add")
    @PostMapping("/addDept")
    public DataResult<SysDept> addDept(@RequestBody @Valid DeptAddReqVO vo){
        DataResult<SysDept> result=DataResult.success();
        result.setData(deptService.addDept(vo));
        return result;
    }


    @ApiOperation(value = "更新部门信息接口")
    @MyLog(title = "组织管理-部门管理",action = "更新部门信息接口")
    @RequiresPermissions("sys:dept:update")
    @PutMapping("/updateDept")
    public DataResult updateDept(@RequestBody @Valid DeptUpdateReqVO vo){
        deptService.updateDept(vo);
        return DataResult.success();
    }


    @ApiOperation(value = "删除部门接口")
    @MyLog(title = "组织管理-部门管理",action = "删除部门接口")
    @RequiresPermissions("sys:dept:delete")
    @DeleteMapping("/deleteDept/{id}")
    public DataResult deletedDept(@PathVariable("id") String deptId){
        deptService.deletedDept(deptId);
        return DataResult.success();
    }

}

