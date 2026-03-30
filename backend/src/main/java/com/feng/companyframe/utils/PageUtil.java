package com.feng.companyframe.utils;

import com.feng.companyframe.vo.resp.PageRespVO;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * @ClassName: PageUtil
 * @Description： 分页查询工具类：通用
 * @createTime: 2020/2/5 15:47
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
public class PageUtil {

    private PageUtil() {
    }

    public static <T> PageRespVO<T> getPageVO(List<T> list) {
        PageRespVO<T> result = new PageRespVO<>();

        if (list instanceof Page) {
            Page<T> page = (Page<T>) list; // 插件的 类
            result.setTotalRows(page.getTotal()); // 总记录数
            result.setTotalPages(page.getPages());// 总页数
            result.setNowPageNum(page.getPageNum());// 当前页数
            result.setCurPageSize(page.getPageSize());// 当前页 记录数
            result.setPageSize(page.size()); // 每页 记录数
            result.setDataList(page.getResult()); // 数据 列表
        }
        return result;
    }
}

