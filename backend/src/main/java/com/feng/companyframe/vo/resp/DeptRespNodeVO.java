package com.feng.companyframe.vo.resp;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Objects;

/**
 * @ClassName: DeptRespNodeVO
 * @Description： 部门树的 节点信息
 * @createTime: 2020/2/18 19:42
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
public class DeptRespNodeVO {

    /**
     * id 很重要，需要告诉后端，选择哪个
     */
    @ApiModelProperty(value = "部门id")
    private String id;

    @ApiModelProperty(value = "部门名称")
    private String title;

    @ApiModelProperty(value = "是否展开 默认展开(true)")
    private boolean spread = true;

    @ApiModelProperty(value = "子集叶子节点")
    private List<?> children;

    public DeptRespNodeVO() {
    }

    public DeptRespNodeVO(String id, String title, boolean spread, List<?> children) {
        this.id = id;
        this.title = title;
        this.spread = spread;
        this.children = children;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSpread() {
        return spread;
    }

    public void setSpread(boolean spread) {
        this.spread = spread;
    }

    public List<?> getChildren() {
        return children;
    }

    public void setChildren(List<?> children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeptRespNodeVO that = (DeptRespNodeVO) o;
        return spread == that.spread && Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(children, that.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, spread, children);
    }

    @Override
    public String toString() {
        return "DeptRespNodeVO{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", spread=" + spread +
                ", children=" + children +
                '}';
    }
}
