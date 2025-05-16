package com.feng.companyframe.vo.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRespVO implements Serializable {
    private String id;

    private String avatar;

    private String username;

    private String salt;

    private String password;

    private String phone;

    private String deptId;

    private String realName;

    private String nickName;

    private String email;

    private Integer status;

    private Integer sex;

    private Integer deleted;

    private String createId;

    private String updateId;

    private Integer createWhere;

    private String createTime;

    private String updateTime;

    // 后添加
    private String deptName;

    private static final long serialVersionUID = 1L;
}
