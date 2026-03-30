<template>
  <el-card class="form-container" shadow="never" ref="treeCard">
    <el-tree
      ref="treeRef"
      :data="menuTreeList"
      show-checkbox
      default-expand-all
      node-key="id"
      highlight-current
      :default-checked-keys="defaultCheckedKeys"
      :props="defaultProps">
    </el-tree>
    <div style="margin-top: 20px" align="center">
      <el-button type="primary" @click="handleSave()">保存</el-button>
      <!-- 要改成重置比较好 -->
      <el-button @click="handleClear()">清空</el-button>
      <el-button @click="goBack()">返回</el-button>
    </div>

  </el-card>
</template>

<script>
  // import {fetchTreeList} from '@/api/menu';
  // import {listMenuByRole,allocMenu} from '@/api/role';
  import {getAllPermissionTreeList, getPermissionsByRoleId, saveRolePermissions} from '@/api/permission';
  import { Loading } from 'element-ui';
  export default {
    name: "allocMenu",
    data() {
      return {
        menuTreeList: [], // 每个对象都有3个属性：id、label、children
        defaultCheckedKeys: [5], // 默认选中的数据列表，是id列表。
        defaultProps: {
          children: 'children',
          label: 'title'
        },
        roleId: null, // router路由传过来的参数。
        loadingInstance: null,  // 获取树结构时，发请求展示的loading圈圈。
      };
    },
    created() {
      this.roleId = this.$route.query.roleId;
    },
    mounted(){
      // 获取树结构数据
      this.getPermissionList();
    },
    methods: {
      // 保存操作
      handleSave() {
        // 获取树结构选中的数据
        let checkedNodes = this.$refs.treeRef.getCheckedNodes();
        let checkedMenuIds=new Set();
        if(checkedNodes!=null&&checkedNodes.length>0){
          for(let i=0;i<checkedNodes.length;i++){
            let checkedNode = checkedNodes[i];
            checkedMenuIds.add(checkedNode.id);
            if(checkedNode.parentId!==0){
              // 为什么要放parentId，需要思考一下。也可能我的代码不需要添加
              checkedMenuIds.add(checkedNode.parentId);
            }
          }
        }
        this.$confirm('是否分配菜单？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(()=>{
          let params = new URLSearchParams();
          params.append("roleId",this.roleId);
          params.append("menuIds",Array.from(checkedMenuIds));
          console.log('handleSave saveRolePermissions params:', JSON.stringify(params))
          // saveRolePermissions(params).then( response => {
          //   this.$message({
          //     message: '分配成功',
          //     type: 'success',
          //     duration: 1000
          //   });
          //   this.$router.back();
          // })
        })
      },
      // 重置
      handleClear() {
        this.$refs.treeRef.setCheckedKeys([]);
      },
      goBack() {
        this.$router.back(); // 或者 this.$router.go(-1)
      },
      getPermissionList() {
        // 显示 loading，挂载在 el-card 上
        this.loadingInstance = Loading.service({
          target: this.$refs.treeRef.$el,
          text: '加载中...',
          spinner: 'el-icon-loading',
          background: 'rgba(255, 255, 255, 0.8)'
        });

        // 获取菜单的树结构
        getAllPermissionTreeList().then(response => {
          console.log('getPermissionList getAllPermissionTreeList response: ', response)
          console.log('getPermissionList getAllPermissionTreeList response.data: ', response.data)
          this.menuTreeList = response.data
          console.log('this.menuTreeList: ', this.menuTreeList)

          // 获取该角色已有的菜单列表(已有的需要默认选中)，然后赋值给 this.defaultCheckedKeys 属性。
          getPermissionsByRoleId(this.roleId).then(response => {
            console.log('getPermissionList getPermissionsByRoleId response.data: ', response.data)
            this.defaultCheckedKeys = response.data            
          })

          // 关闭 loading
          if (this.loadingInstance) {
            this.loadingInstance.close();
          }
        })
      }
    }
  }
</script>

<style scoped>

</style>
