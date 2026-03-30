<template>
  <div>
    <el-card class="form-container" shadow="never">
      <el-form :model="menuForm"
              :rules="rules"
              ref="menuFromRef"
              label-width="150px">
        <!-- 单选框部分 -->
        <el-form-item label="类型">
          <el-radio-group v-model="menuForm.type" @change="handleTypeChange">
            <el-radio :label="1">目录</el-radio>
            <el-radio :label="2">菜单</el-radio>
            <el-radio :label="3">按钮</el-radio>
          </el-radio-group>
        </el-form-item>
        <!-- 动态输入框部分 -->
        <el-form-item label="菜单名称" >
          <el-input v-model="menuForm.name" placeholder="请输入菜单名称"></el-input>
        </el-form-item>
        
        <el-form-item label="所属菜单">
          <el-input v-model="menuForm.pidNameTemp" 
            readonly 
            @click.native="handlePidClick" 
            placeholder="请点击选择 所属菜单"></el-input>
        </el-form-item>
        
        <el-form-item label="接口URL">
          <el-input v-model="menuForm.url" placeholder="请输入接口URL"></el-input>
        </el-form-item>
        
        <el-form-item label="授权标识" v-show="showInput">
          <el-input v-model="menuForm.perms" placeholder="请输入授权标识，如sys:user:list"></el-input>
        </el-form-item>
        
        <el-form-item label="请求方式" v-show="showInput">
          <el-input v-model="menuForm.method" placeholder="请输入请求方式，如GET、POST"></el-input>
        </el-form-item>
        
        <el-form-item label="按钮标识" v-show="showInput">
          <el-input v-model="menuForm.code" placeholder="请输入前后端分离控制按钮，如btn-permission-list"></el-input>
        </el-form-item>

        <el-form-item label="排序码">
          <el-input v-model="menuForm.orderNum" placeholder="请输入内容"></el-input>
        </el-form-item>
        
        <el-form-item label="是否启用" >
          <el-switch
            :active-value=1
            :inactive-value=0
            v-model="menuForm.status">
          </el-switch>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="onSubmit('menuFromRef')">提交</el-button>
          <el-button v-if="!isEdit" @click="resetForm('menuFromRef')">重置</el-button>
          <el-button @click="backLastPage()">返回</el-button>
        </el-form-item>
      </el-form>
    </el-card>

    <!-- 菜单树状图 -->
    <el-dialog
      title="选择所属菜单"
      :visible.sync="dialogVisible"
      width="50%"
      :before-close="handleClose">

      <el-input
        placeholder="输入关键字进行过滤"
        v-model="filterText">
      </el-input>
      <el-tree
        class="filter-tree"
        :data="menuData"
        :props="defaultProps"
        default-expand-all
        :filter-node-method="filterNode"
        @node-click="handleNodeClick"
        ref="tree">
      </el-tree>

      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogCancel">取 消</el-button>
        <el-button type="primary" @click="dialogConfirm">确 定</el-button>
      </span>
    </el-dialog>

  </div>
</template>

<script>
  // import {fetchList, createMenu, updateMenu, getMenu} from '@/api/menu';
  import {getAllPermissionTreeExchangeBtn, addPermission, updatePermission} from '@/api/permission'

  const defaultMenu = {
    type: 1, // 默认选中类型1
    name: '',
    pid: '',
    pidNameTemp: '', // 仅仅是为了展示
    url: '',
    perms: '',
    method: '',
    code: '',
    orderNum: '',
    status: 1
  };
  export default {
    name: "MenuDetail",
    props: {
      isEdit: {
        type: Boolean,
        default: false
      }
    },
    data() {
      return {
        menuForm: Object.assign({}, defaultMenu),
        showInput: false, // 控制额外输入框是否显示
        selectMenuList: [],
        rules: 
        {
          title: [
            {required: true, message: '请输入菜单名称', trigger: 'blur'},
            {min: 2, max: 140, message: '长度在 2 到 140 个字符', trigger: 'blur'}
          ],
          name: [
            {required: true, message: '请输入前端名称', trigger: 'blur'},
            {min: 2, max: 140, message: '长度在 2 到 140 个字符', trigger: 'blur'}
          ],
          icon: [
            {required: true, message: '请输入前端图标', trigger: 'blur'},
            {min: 2, max: 140, message: '长度在 2 到 140 个字符', trigger: 'blur'}
          ]
        },
        dialogVisible: false,
        filterText: '',
        menuData: [], // 数据菜单的数据
        defaultProps: { children: 'children', label: 'label' },
        menuSelectData: null, // 存放 树形菜单点击后选中的数据
      }
    },
    created() {
      if (this.isEdit) {
        console.log('created this.$route.query.id: ', this.$route.query.id )
        /**
         * 如果不进行编码和解码，刷新后this.$route.query.row是无值的。
         * 原因：
         * this.$route.query 的参数是通过 URL 查询字符串传递的，例如：http://localhost:8080/edit?id=123&row=xxxxx
         * id=123 是基本数据类型（字符串/数字）✅能正常传递。row=xxxxx 是一个对象，不是基本类型，传递失败，row 会被序列化成字符串 [object Object]
         *    进入地址栏的是：/edit?id=123&row=[object Object]
         *    所以页面刷新时，浏览器只能还原成字符串 " [object Object] "，而不是原来的对象
         * 方法一：把 row 存进 Vuex 或 pinia 或 localStorage 中（推荐）
         * 方法二（当前使用该方法）：手动序列化对象（不推荐用于复杂对象）
         */
        console.log('created this.$route.query.row: ', JSON.parse(decodeURIComponent(this.$route.query.row)));
        this.menuForm = JSON.parse(decodeURIComponent(this.$route.query.row));
        this.menuForm.pidNameTemp = this.menuForm.pidName
      } else {
        this.menu = Object.assign({}, defaultMenu);
      }
      this.getSelectPermissionListExchangeBtn();
    },
    mounted(){
      this.handleTypeChange(this.menuForm.type); // 初始化时处理一次
    },
    watch: {
      // 监听简单值
      // showInput(newVal, oldVal){
      //   console.log(`showInput 从 "${oldVal}" 变为 "${newVal}", showInput 当前值： ${this.showInput}`);
      // }
      filterText(val) {
        this.$refs.tree.filter(val);
      }
    },
    methods: {
      convertTitleToLabel(nodes) {
        nodes.forEach(node => {
          node.label = node.title; // 新建 name 字段
          delete node.title;      // 删除原有 label 字段

          // 如果有子节点，递归处理
          if (node.children && node.children.length) {
            this.convertTitleToLabel(node.children);
          }
        });
      },
      getSelectPermissionListExchangeBtn() {
        // 获取权限列表
        getAllPermissionTreeExchangeBtn().then(response => {
          // console.log('getSelectPermissionListExchangeBtn getAllPermissionTreeExchangeBtn response.data: ', response.data)
          this.menuData = response.data
          // 转移，将title转成label
          // console.log('转移之前: getSelectPermissionListExchangeBtn this.menuData: ', this.menuData)
          this.convertTitleToLabel(this.menuData);
          // console.log('转移之后: getSelectPermissionListExchangeBtn this.menuData: ', this.menuData)
        })
      },
      onSubmit(refName) {
        this.$refs[refName].validate((valid) => {
          if (valid) {
            this.$confirm('是否保存数据', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              if (this.isEdit) {
                // 修改
                console.log('onSubmit updatePermission this.menuForm: ', JSON.stringify(this.menuForm))
                updatePermission(this.menuForm).then(response => {
                  this.$message({
                    message: '修改成功',
                    type: 'success',
                    duration: 1000
                  });
                  this.$router.back();
                });
              } else {
                // 新增
                console.log('onSubmit addPermission this.menuForm: ', JSON.stringify(this.menuForm))
                addPermission(this.menuForm).then(response => {
                  // 清除添加表单的数据
                  this.$refs[refName].resetFields();
                  this.resetForm(refName);
                  this.menuSelectData = null;

                  this.$message({
                    message: '提交成功',
                    type: 'success',
                    duration: 1000
                  });
                  this.$router.back();
                });
              }
            });

          } else {
            this.$message({
              message: '验证失败',
              type: 'error',
              duration: 1000
            });
            return false;
          }
        });
      },
      resetForm(refName) {
        this.$refs[refName].resetFields();
        this.menuForm = Object.assign({}, defaultMenu);
        this.getSelectPermissionListExchangeBtn();
        // 树形选中的数据也要清空
        this.menuSelectData = null;
      },
      handleTypeChange(value) {
        this.showInput = value === '2' || value === '3';
        // 主动移除当前聚焦（防止 aria-hidden 冲突）
        document.activeElement && document.activeElement.blur();
        /**
         * 上面这行解决控制台警告问题：
         * Blocked aria-hidden on an element because its descendant retained focus. The focus must not be 
         * hidden from assistive technology users. Avoid using aria-hidden on a focused element or its 
         * ancestor. Consider using the inert attribute instead, which will also prevent focus. For more 
         * details, see the aria-hidden section of the WAI-ARIA specification at https://w3c.github.io/aria/#aria-hidden.
         * Element with focus: <input.el-radio__original>Ancestor with aria-hidden: 
         * <input.el-radio__original> <input type=​"radio" aria-hidden=​"true" tabindex=​"-1" autocomplete=​"off" class=​"el-radio__original" value=​"3">​
         * 
         */
      },
      backLastPage() {
        this.$router.back(); // 或者 this.$router.go(-1)
      },
      handlePidClick() {
        console.log('handlePidClick')
        this.dialogVisible = true
        console.log('handlePidClick this.menuData: ', this.menuData)
      },
      filterNode(value, data) {
        if (!value) return true;
        return data.label.indexOf(value) !== -1;
      },
      // 树形结构，选中节点时的事件
      handleNodeClick(data){
        console.log('handleNodeClick: ', data)
        this.menuSelectData = data
      },
      handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
      },
      dialogCancel() {
        this.dialogVisible = false
      },
      dialogConfirm() {
        this.dialogVisible = false
        this.menuForm.pid = this.menuSelectData.id
        this.menuForm.pidNameTemp = this.menuSelectData.label
        // console.log('dialogConfirm :', this.menuForm)
      },
    }
  }
</script>

<style scoped>

</style>
