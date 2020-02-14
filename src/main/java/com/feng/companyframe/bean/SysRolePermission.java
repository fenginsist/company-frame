package com.feng.companyframe.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysRolePermission implements Serializable {
    private String id;

    private String roleId;

    private String permissionId;

    private Date createTime;

    private static final long serialVersionUID = 1L;

}