<template>
  <div class="app-container">
    <el-card class="operate-container" shadow="never">
      <i class="el-icon-tickets" style="margin-top: 5px"></i>
      <span style="margin-top: 5px">数据列表</span>
      <el-button
        class="btn-add"
        @click="handleAddMenu()"
        size="mini">
        添加
      </el-button>
    </el-card>
    <!-- 数据列表 -->
    <div class="table-container">
      <el-table ref="menuTable"
                style="width: 100%"
                :default-expand-all="true"
                :data="permissionList"
                row-key="id"
                v-loading="listLoading" border
                :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
                >
        <!-- <el-table-column label="编号" :min-width="50" align="center">
          <template slot-scope="scope">{{ (listQuery.pageNum - 1) * listQuery.pageSize + scope.$index + 1  }}</template>
        </el-table-column> -->
        <el-table-column label="菜单名称" :min-width="260" align="left">
          <template slot-scope="scope">
            <i 
              v-if="scope.row.type === 1" 
              class="el-icon-folder-opened" 
              style="margin-right: 8px; color: #409EFF;"
            ></i>
            <i 
              v-else-if="scope.row.type === 2" 
              class="el-icon-menu" 
              style="margin-right: 8px; color: #67C23A;"
            ></i>
            <i 
              v-else-if="scope.row.type === 3" 
              class="el-icon-pear" 
              style="margin-right: 8px; color: #E6A23C;"
            ></i>
            {{ scope.row.title }}
          </template>
        </el-table-column>
        <el-table-column label="url" :min-width="180" align="center">
          <template slot-scope="scope">{{scope.row.url}}</template>
        </el-table-column>
        <el-table-column label="请求方式" :min-width="100" align="center">
          <template slot-scope="scope">
            <el-tag
              v-if="scope.row.method"
              :type="
                scope.row.method === 'GET'
                  ? 'success'
                  : scope.row.method === 'POST'
                  ? 'primary'
                  : scope.row.method === 'PUT'
                  ? 'warning'
                  : 'danger'
              "
              size="small"
            >
              {{ scope.row.method }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="类型" align="center">
          <template slot-scope="scope">
            <!-- {{scope.row.url === "" ? '目录' : '菜单'}} -->
            <el-tag v-if="scope.row.type === 1" type="success">目录</el-tag>
            <el-tag v-if="scope.row.type === 2" type="primary">菜单</el-tag>
            <el-tag v-if="scope.row.type === 3" type="warning">按钮</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="父级名称" :min-width="200" align="center">
          <template slot-scope="scope">{{scope.row.title}}</template>
        </el-table-column>
        <el-table-column label="排序" :min-width="60" align="center">
          <template slot-scope="scope">{{scope.row.orderNum }}</template>
        </el-table-column>
        <el-table-column label="资源标识" :min-width="200" align="center">
          <template slot-scope="scope">{{scope.row.perms }}</template>
        </el-table-column>
        <el-table-column label="前后端分离按钮控制标识" :min-width="200" align="center">
          <template slot-scope="scope">{{scope.row.code }}</template>
        </el-table-column>
        <el-table-column label="创建时间" :min-width="200" align="center">
          <template slot-scope="scope">{{formatDateStr(scope.row.createTime) }}</template>
        </el-table-column>
        <el-table-column label="状态" :min-width="100" align="center">
          <template slot-scope="scope">
            <el-switch
              @change="handleUserStatusChange(scope.$index, scope.row, scope.row.status === 1 ? 2 : 1)"
              :disabled="true"
              :active-value=1
              :inactive-value=0
              v-model="scope.row.status">
            </el-switch>
          </template>
        </el-table-column>
        <!-- <el-table-column label="前端图标" width="100" align="center">
          <template slot-scope="scope"><svg-icon :icon-class="scope.row.icon"></svg-icon></template>
        </el-table-column> -->
        <!-- <el-table-column label="是否显示" width="100" align="center">
          <template slot-scope="scope">
            <el-switch
              @change="handleHiddenChange(scope.$index, scope.row)"
              :active-value="1"
              :inactive-value="0"
              v-model="scope.row.hidden">
            </el-switch>
          </template>
        </el-table-column> -->

        <el-table-column label="操作" width="200" align="center">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              @click="handleUpdate(scope.$index, scope.row)">编辑
            </el-button>
            <el-button
              size="mini"
              type="text"
              @click="handleDelete(scope.$index, scope.row)">删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="pagination-container">
      <el-pagination
        background
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        layout="total, sizes,prev, pager, next,jumper"
        :page-size="listQuery.pageSize"
        :page-sizes="[10,15,20]"
        :current-page.sync="listQuery.pageNum"
        :total="total">
      </el-pagination>
    </div>
  </div>
</template>

<script>
  import {getAllPermissionTreeList, getAllPermissionTreeExchangeBtn, deletePermission} from '@/api/permission'
  import { formatDateStr } from '@/utils/date'

  export default {
    name: "menuList",
    data() {
      return {
        permissionList: [],
        total: null,
        listLoading: true,
        listQuery: {
          pageNum: 1,
          pageSize: 5
        },
        parentId: 0,
      }
    },
    created() {
      // this.resetParentId();
      this.getAllPermissionList();
    },
    watch: {
      // $route(route) {
      //   this.resetParentId();
      //   this.getAllPermissionList();
      // }
    },
    methods: {
      formatDateStr,
      resetParentId(){
        this.listQuery.pageNum = 1;
        if (this.$route.query.parentId != null) {
          this.parentId = this.$route.query.parentId;
        } else {
          this.parentId = 0;
        }
      },
      handleAddMenu() {
        this.$router.push('/ums/addMenu');
      },
      getAllPermissionList() {
        this.listLoading = true;
        getAllPermissionTreeList().then(response => {
          this.listLoading = false;
          // console.log('getAllPermissionList getAllPermissionTreeExchangeBtn: ', response)
          this.permissionList = response.data
          // console.log('getAllPermissionList this.permissionList: ', this.permissionList)
        })
      },
      handleSizeChange(val) {
        this.listQuery.pageNum = 1;
        this.listQuery.pageSize = val;
        this.getAllPermissionList();
      },
      handleCurrentChange(val) {
        this.listQuery.pageNum = val;
        this.getAllPermissionList();
      },
      handleHiddenChange(index, row) {
        // updateHidden(row.id,{hidden:row.hidden}).then(response=>{
        //   this.$message({
        //     message: '修改成功',
        //     type: 'success',
        //     duration: 1000
        //   });
        // });
      },
      handleUpdate(index, row) {
        // 解析的讲解，在 MenuDetail 页面
        this.$router.push({path:'/ums/updateMenu',query:{id:row.id, row: encodeURIComponent(JSON.stringify(row))}});
      },
      handleDelete(index, row) {
        this.$confirm('是否要删除该菜单', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          console.log('handleDelete: ', row.id)
          deletePermission(row.id).then(response => {
            this.$message({
              message: '删除成功',
              type: 'success',
              duration: 1000
            });
            this.getAllPermissionList();
          });
        });
      },
      handleUserStatusChange(){

      },

    },
    filters: {
      levelFilter(value) {
        if (value === 0) {
          return '一级';
        } else if (value === 1) {
          return '二级';
        }
      },
      disableNextLevel(value) {
        if (value === 0) {
          return false;
        } else {
          return true;
        }
      }
    }
  }
</script>

<style scoped>
</style>
