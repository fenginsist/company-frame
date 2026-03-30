package com.feng.companyframe.bean;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @ClassName: TesTBean
 * @Description： 描述
 * @createTime: 2020/2/3 13:45
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TesTBean {

    @ApiModelProperty("")
    @NotBlank(message = "名称不能为空")
    private String name;

    @ApiModelProperty(value = "年龄")
    @NotNull(message = "age 不能为空")
    private Integer age;

    @ApiModelProperty(value = "id 集合")
    @NotEmpty(message = "id 集合不能为空")
    private List<String> ids;
}

