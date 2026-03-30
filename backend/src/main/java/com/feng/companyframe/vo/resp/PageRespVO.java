package com.feng.companyframe.vo.resp;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Objects;

/**
 * @ClassName: PageVO
 * @Description： 分页 POJO 返回的响应数据
 * @createTime: 2020/2/5 15:45
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
public class PageRespVO<T> {
    /**
     * 总 记录数
     */
    @ApiModelProperty(value = "总记录数")
    private Long totalRows;

    /**
     * 总 页数
     */
    @ApiModelProperty(value = "总页数")
    private Integer totalPages;

    /**
     * 当前 第几页
     */
    @ApiModelProperty(value = "当前第几页")
    private Integer nowPageNum;
    /**
     * 每页 记录数
     */
    @ApiModelProperty(value = "每页记录数")
    private Integer pageSize;
    /**
     * 当前页 记录数
     */
    @ApiModelProperty(value = "当前页记录数")
    private Integer curPageSize;
    /**
     * 数据 列表
     */
    @ApiModelProperty(value = "数据列表")
    private List<T> dataList;

    public PageRespVO() {
    }

    public PageRespVO(Long totalRows, Integer totalPages, Integer nowPageNum, Integer pageSize, Integer curPageSize, List<T> dataList) {
        this.totalRows = totalRows;
        this.totalPages = totalPages;
        this.nowPageNum = nowPageNum;
        this.pageSize = pageSize;
        this.curPageSize = curPageSize;
        this.dataList = dataList;
    }

    public Long getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Long totalRows) {
        this.totalRows = totalRows;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getNowPageNum() {
        return nowPageNum;
    }

    public void setNowPageNum(Integer nowPageNum) {
        this.nowPageNum = nowPageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurPageSize() {
        return curPageSize;
    }

    public void setCurPageSize(Integer curPageSize) {
        this.curPageSize = curPageSize;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PageRespVO<?> that = (PageRespVO<?>) o;
        return Objects.equals(totalRows, that.totalRows)
                && Objects.equals(totalPages, that.totalPages)
                && Objects.equals(nowPageNum, that.nowPageNum)
                && Objects.equals(pageSize, that.pageSize)
                && Objects.equals(curPageSize, that.curPageSize)
                && Objects.equals(dataList, that.dataList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalRows, totalPages, nowPageNum, pageSize, curPageSize, dataList);
    }

    @Override
    public String toString() {
        return "PageRespVO{" +
                "totalRows=" + totalRows +
                ", totalPages=" + totalPages +
                ", nowPageNum=" + nowPageNum +
                ", pageSize=" + pageSize +
                ", curPageSize=" + curPageSize +
                ", dataList=" + dataList +
                '}';
    }
}
