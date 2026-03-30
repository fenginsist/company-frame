package com.feng.companyframe.bean;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName: TesTBean
 * @Description： 描述
 * @createTime: 2020/2/3 13:45
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
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

    public TesTBean() {
    }

    public TesTBean(String name, Integer age, List<String> ids) {
        this.name = name;
        this.age = age;
        this.ids = ids;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TesTBean tesTBean = (TesTBean) o;
        return Objects.equals(name, tesTBean.name) && Objects.equals(age, tesTBean.age) && Objects.equals(ids, tesTBean.ids);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, ids);
    }

    @Override
    public String toString() {
        return "TesTBean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", ids=" + ids +
                '}';
    }
}
