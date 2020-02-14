package com.feng.companyframe.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName: PageVO
 * @Description： 分页 POJO 返回的响应数据
 * @createTime: 2020/2/5 15:45
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
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
}

