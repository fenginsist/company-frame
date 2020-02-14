package com.feng.companyframe.services.impl;

import com.feng.companyframe.bean.SysPermission;
import com.feng.companyframe.mapper.SysPermissionMapper;
import com.feng.companyframe.services.PermissionService;
import com.feng.companyframe.vo.resp.PermissionRespNodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: PermissionServiceImpl
 * @Description： 描述
 * @createTime: 2020/2/11 17:39
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Resource
    private SysPermissionMapper sysPermissionMapper;

    @Override
    public List<SysPermission> getAllPermissions() {
        List<SysPermission> allPermissions = sysPermissionMapper.getAllPermissions();
        // 将上面的集合 遍历，对每一个权限 设置 父级权限名称
        if (!allPermissions.isEmpty()){
            for (SysPermission sysPermission : allPermissions){
                SysPermission parent = sysPermissionMapper.selectByPrimaryKey(sysPermission.getId());
                if (null != parent){
                    sysPermission.setPidName(parent.getName());
                }
            }
        }
        return allPermissions;
    }

    @Override
    public List<PermissionRespNodeVO> getAllMenuByTree() {
        return getTree(sysPermissionMapper.getAllPermissions());
    }
    /*
    * 拉出来单独成一个方法。
    *
    * */
    private List<PermissionRespNodeVO> getTree(List<SysPermission> all){
        List<PermissionRespNodeVO> list= new ArrayList<>();


        return null;
    }


}

