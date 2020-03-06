package com.feng.companyframe.mapper;

import com.feng.companyframe.bean.SysDept;
import java.lang.String;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SysDeptMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysDept record);

    int insertSelective(SysDept record);

    SysDept selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysDept record);

    int updateByPrimaryKey(SysDept record);

    /**
     * 获取所有的 部门
     * @return
     */
    List<SysDept> getAllDept();

    /**
     * 维护 新的层级关系
     * @param oldStr
     * @param newStr
     * @param relationCode
     * @return
     */
    int updateRelationCode(@Param("oldStr") String oldStr, @Param("newStr") String newStr, @Param("relationCode") String relationCode);


    /**
     * 通过层级关系 查找 所有叶子节点
     * @param relationCode
     * @return
     */
    List<String> getChildIds(String relationCode);

    /**
     * 逻辑删除部门信息
     * @param updateTime
     * @param list
     * @return
     */
    int deletedDepts(Date updateTime , @Param("list") List<String> list);
}