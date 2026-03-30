package com.feng.companyframe.services;

import com.feng.companyframe.bean.SysDept;
import com.feng.companyframe.vo.req.DeptAddReqVO;
import com.feng.companyframe.vo.req.DeptUpdateReqVO;
import com.feng.companyframe.vo.resp.DeptRespNodeVO;

import java.util.List;

public interface DeptService {

    /**
     * 根据 条件 获取所有的 部门
     * @return
     */
    List<SysDept> getAllDept();

    /**
     * 获取 部门树
     * @return
     */
    List<DeptRespNodeVO> deptTreeList(String deptId);

    /**
     *  添加部门
     * @param vo
     * @return
     */
    SysDept addDept(DeptAddReqVO vo);

    /**
     * 更新部门信息
     * @param vo
     */
    void updateDept(DeptUpdateReqVO vo);

    /**
     * 删除部门信息
     * @param deptId
     */
    void deletedDept(String deptId);
}
