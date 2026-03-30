package com.feng.companyframe.services.impl;

import com.feng.companyframe.bean.SysDept;
import com.feng.companyframe.bean.SysUser;
import com.feng.companyframe.constant.Constant;
import com.feng.companyframe.exception.BusinessException;
import com.feng.companyframe.exception.code.BaseResponseCode;
import com.feng.companyframe.mapper.SysDeptMapper;
import com.feng.companyframe.services.DeptService;
import com.feng.companyframe.services.UserService;
import com.feng.companyframe.utils.CodeUtil;
import com.feng.companyframe.utils.RedisUtil;
import com.feng.companyframe.vo.req.DeptAddReqVO;
import com.feng.companyframe.vo.req.DeptUpdateReqVO;
import com.feng.companyframe.vo.resp.DeptRespNodeVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName: DeptServiceImpl
 * @Description： 描述
 * @createTime: 2020/2/18 19:05
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
@Slf4j
@Service
public class DeptServiceImpl implements DeptService {

    @Resource
    private SysDeptMapper sysDeptMapper;

    @Resource
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 根据 条件 获取所有的 部门
     *
     * @return
     */
    @Override
    public List<SysDept> getAllDept() {
        List<SysDept> allDept = sysDeptMapper.getAllDept();

        for (SysDept dept : allDept) {
            SysDept parentDept = sysDeptMapper.selectByPrimaryKey(dept.getId());
            if (null != parentDept) {
                dept.setPidName(dept.getName());
            }
        }
        return allDept;
    }

    /**
     * 获取 部门树
     * @param deptId
     * @return
     */
    @Override
    public List<DeptRespNodeVO> deptTreeList(String deptId) {
        // 获取所有的部门信息
        List<SysDept> list = sysDeptMapper.getAllDept();
        /**
         * 当 编辑时 要去掉自己所属的部门 则 去掉 部门id 相同的部门id 的部门信息即可
         * 我要想去掉这个部门的叶子节点，直接在数据源移除这个部门就可以了
         */
        if(!StringUtils.isEmpty(deptId) && !list.isEmpty()){
            for (SysDept sysDept : list){
                if(deptId.equals(sysDept.getId())){
                    list.remove(sysDept);
                    break;
                }
            }
        }

        //默认加一个顶级 方便 新增顶级部门
        DeptRespNodeVO respNodeVO = new DeptRespNodeVO();
        respNodeVO.setTitle("默认顶级部门");
        respNodeVO.setId("0");
        respNodeVO.setSpread(true);
        respNodeVO.setChildren(getTree(list));
        List<DeptRespNodeVO> result = new ArrayList<>();
        result.add(respNodeVO);
        return result;
    }

    /**
     * 部门树的 构建方法
     *
     * @param all
     * @return
     */
    private List<DeptRespNodeVO> getTree(List<SysDept> all) {
        List<DeptRespNodeVO> list = new ArrayList<>();
        for (SysDept sysDept : all) {
            // 找到顶级 菜单，顶级菜单的 id 为 0，
            if ("0".equals(sysDept.getPid())) {
                DeptRespNodeVO deptTree = new DeptRespNodeVO();
                BeanUtils.copyProperties(sysDept, deptTree);
                deptTree.setTitle(sysDept.getName());
                deptTree.setSpread(true);
                deptTree.setChildren(getChild(sysDept.getId(), all));
                list.add(deptTree);
            }
        }
        return list;
    }

    /**
     * 部门树的 构建 递归方法
     *
     * @param id
     * @param all
     * @return
     */
    private List<DeptRespNodeVO> getChild(String id, List<SysDept> all) {
        List<DeptRespNodeVO> list = new ArrayList<>();
        for (SysDept sysDept : all) {
            if (sysDept.getPid().equals(id)) {
                DeptRespNodeVO deptTree = new DeptRespNodeVO();
                BeanUtils.copyProperties(sysDept, deptTree);
                deptTree.setTitle(sysDept.getName());
                deptTree.setChildren(getChild(sysDept.getId(), all));
                list.add(deptTree);
            }
        }
        return list;
    }

    /**
     * 添加部门
     * 层级关系 ：认真看下 层级关系 relationCode =  父级部门编码 和 部门的编码
     *
     * @param vo
     * @return
     */
    @Override
    public SysDept addDept(DeptAddReqVO vo) {
        // 层级关系的 变量 ，和表中一至
        String relationCode;
        // 编码用 redis 自增。
        long result = redisUtil.incrby(Constant.DEPT_CODE_KEY, 1);
        // 编码 补位 ，设置为 7 位， 用 0 补位
        String deptCode = CodeUtil.deptCode(String.valueOf(result), 7, "0");
        // 关系编码， 父级的 部门
        SysDept parent = sysDeptMapper.selectByPrimaryKey(vo.getPid());
        if (vo.getPid().equals("0")) {  // 顶级
            relationCode = deptCode;
        } else if (null == parent) { // pid  不合法
            log.error("传入的 pid:{}不合法", vo.getPid());
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        } else {
            // 层级关系 为父级部门编码 和 部门的编码
            relationCode = parent.getRelationCode() + deptCode;
        }
        /*
         * 以上为 判断校验、组合层级编码
         * 以下为新增
         * */
        SysDept sysDept = new SysDept();
        BeanUtils.copyProperties(vo, sysDept);
        sysDept.setId(UUID.randomUUID().toString());
        sysDept.setDeptNo(deptCode);
        // 设置层级关系
        sysDept.setRelationCode(relationCode);
        sysDept.setCreateTime(new Date());
        // 插入 部门信息
        int count = sysDeptMapper.insertSelective(sysDept);
        if (count != 1) {
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
        return sysDept;
    }

    /**
     * 更新部门信息
     * 重点：：维护 层级关系
     * @param vo
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateDept(DeptUpdateReqVO vo) {
        // 1. 校验数据
        SysDept sysDept = sysDeptMapper.selectByPrimaryKey(vo.getId());
        if (null== sysDept){
            log.info("传入的id：{}不合法", vo.getId());
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        // 2. 保存更新的部门数据
        SysDept updateSysDept = new SysDept();
        BeanUtils.copyProperties(vo, updateSysDept);
        updateSysDept.setUpdateTime(new Date());
        int i = sysDeptMapper.updateByPrimaryKeySelective(updateSysDept);
        if (1!=i){
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }

        /**
         * 3. 维护 层级关系
         * 如果说 传过来的部门父级pid 与 数据库中该部门 父级pid  不一致（不相等） 则 层级关系发生了变化
         *
         * 子集的部门层级关系编码 = 父级部门层级关系编码 + 它本身部门编码
         */
        if(!vo.getPid().equals(sysDept.getPid())){
            // 1. 获取 新的 父级部门信息
            SysDept newParent=sysDeptMapper.selectByPrimaryKey(vo.getPid());
            // 校验 newParent
            if(!vo.getPid().equals("0")&&null==newParent){
                log.error("修改后的部门在数据库查找不到{}",vo.getPid());
                throw new BusinessException(BaseResponseCode.DATA_ERROR);
            }
            // 2. 获取 旧的 父级部门信息
            SysDept oldParent=sysDeptMapper.selectByPrimaryKey(sysDept.getPid());

            String oldRelationCode;
            String newRelationCode;
            /**
             * 3.  第一种情况：根目录挂靠到其他目录
             */
            if (sysDept.getPid().equals("0")){
                oldRelationCode=sysDept.getDeptNo();
                newRelationCode=newParent.getRelationCode()+sysDept.getDeptNo();
            }else if(vo.getPid().equals("0")){
                //其他目录升级到跟目录
                oldRelationCode=sysDept.getRelationCode();
                newRelationCode=sysDept.getDeptNo();
            }else {
                oldRelationCode=oldParent.getRelationCode();
                newRelationCode=newParent.getRelationCode();
            }
            sysDeptMapper.updateRelationCode(oldRelationCode,newRelationCode,sysDept.getRelationCode());
        }
    }

    /**
     * 删除部门信息
     * @param deptId
     */
    @Override
    public void deletedDept(String deptId) {
        // 1. 校验
        SysDept sysDept = sysDeptMapper.selectByPrimaryKey(deptId);
        if (null == sysDept){
            log.info("传入的部门的id 在数据库不存在{}", deptId);
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }

        List<String> childIds = sysDeptMapper.getChildIds(sysDept.getRelationCode());
        // 判断 它和它子集的叶子节点 是否关联有用户
        List<SysUser> userInfoByDeptIds = userService.getUserInfoByDeptIds(childIds);
        if (!userInfoByDeptIds.isEmpty()){
            // 如果不为空，则爆出提示：该组织机构下还关联着用户，不允许删除
            throw new BusinessException(BaseResponseCode.NOT_PERMISSION_DELETED_DEPT);
        }

        // 3. 逻辑删除部门数据
        int i = sysDeptMapper.deletedDepts(new Date(), childIds);
        if (0 == i){
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }


    }



}

