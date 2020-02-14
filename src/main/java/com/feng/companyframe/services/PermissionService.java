package com.feng.companyframe.services;

import com.feng.companyframe.bean.SysPermission;
import com.feng.companyframe.vo.resp.PermissionRespNodeVO;

import java.util.List;

public interface PermissionService {
    List<SysPermission> getAllPermissions();

    /*
    * 获取所有的树形菜单
    * */
    List<PermissionRespNodeVO> getAllMenuByTree();
}
