package com.feng.companyframe.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysDept implements Serializable {
    private String id;

    private String deptNo;

    private String name;

    private String pid;

    private Integer status;

    private String relationCode;

    private String deptManagerId;

    private String managerName;

    private String phone;

    private Date createTime;

    private Date updateTime;

    private Integer deleted;

    private static final long serialVersionUID = 1L;

    /**
     * 新增 父级部门名称
     */
    private String pidName;

}