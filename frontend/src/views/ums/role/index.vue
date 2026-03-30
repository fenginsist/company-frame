<template> 
  <div class="app-container">
    <el-card class="filter-container" shadow="never">
      <div>
        <i class="el-icon-search"></i>
        <span>筛选搜索</span>
        <el-button
          style="float:right"
          type="primary"
          @click="handleSearchList()"
          size="small">
          查询搜索
        </el-button>
        <el-button
          style="float:right;margin-right: 15px"
          @click="handleResetSearch()"
          size="small">
          重置
        </el-button>
      </div>
      <div style="margin-top: 15px">
        <el-form :inline="true" :model="listQuery" size="small" label-width="140px">
          <el-form-item label="输入搜索：">
            <el-input v-model="listQuery.keyword" class="input-width" placeholder="角色名称" clearable></el-input>
          </el-form-item>
        </el-form>
      </div>
    </el-card>
    <el-card class="operate-container" shadow="never">
      <i class="el-icon-tickets"></i>
      <span>数据列表</span>
      <el-button size="mini" class="btn-add" @click="handleAdd()" style="margin-left: 20px">添加</el-button>
    </el-card>
    <div class="table-container">
      <el-table ref="roleTable"
                :data="list"
                style="width: 100%;"
                v-loading="listLoading" border>
        <el-table-column type="selection" :min-width="60" align="center"></el-table-column>
        <el-table-column label="编号" :min-width="50" align="center">
          <template slot-scope="scope">{{ (listQuery.pageNum - 1) * listQuery.pageSize + scope.$index + 1  }}</template>
        </el-table-column>
        <el-table-column label="id" :min-width="300" align="center">
          <template slot-scope="scope">{{scope.row.id}}</template>
        </el-table-column>
        <el-table-column label="角色名称" :min-width="100" align="center">
          <template slot-scope="scope">{{scope.row.name}}</template>
        </el-table-column>
        <el-table-column label="描述" :min-width="100" align="center">
          <template slot-scope="scope">{{scope.row.description}}</template>
        </el-table-column>
        <el-table-column label="用户数" :min-width="100"  width="100" align="center">
          <template slot-scope="scope">{{scope.row.adminCount}}</template>
        </el-table-column>
        <el-table-column label="添加时间" :min-width="100" width="160" align="center">
          <template slot-scope="scope">{{scope.row.createTime | formatDateTime}}</template>
        </el-table-column>
        <el-table-column label="是否启用" :min-width="100" width="140" align="center">
          <template slot-scope="scope">
            <el-switch
              @change="handleStatusChange(scope.$index, scope.row)"
              :active-value="1"
              :inactive-value="0"
              v-model="scope.row.status">
            </el-switch>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="160" align="center">
          <template slot-scope="scope">
            <el-row>
              <el-button size="mini"
                         type="text"
                         @click="handleSelectMenu(scope.$index, scope.row)">分配菜单
              </el-button>
              <el-button size="mini"
                         type="text"
                         @click="handleSelectResource(scope.$index, scope.row)">分配资源
              </el-button>
            </el-row>
            <el-row>
            <el-button size="mini"
                       type="text"
                       @click="handleUpdate(scope.$index, scope.row)">
              编辑
            </el-button>
            <el-button size="mini"
                       type="text"
                       @click="handleDelete(scope.$index, scope.row)">删除
            </el-button>
            </el-row>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <!-- 分页 -->
    <div class="pagination-container">
      <el-pagination
        background
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        layout="total, sizes,prev, pager, next,jumper"
        :current-page.sync="listQuery.pageNum"
        :page-size="listQuery.pageSize"
        :page-sizes="[5,10,15]"
        :total="total">
      </el-pagination>
    </div>
    <!-- 添加角色/编辑角色 -->
    <el-dialog
      :title="isEdit?'编辑角色':'添加角色'"
      :visible.sync="roleDialogVisible"
      width="40%">
      <el-form :model="roleData"
               ref="roleForm"
               label-width="150px" size="small">
        <el-form-item label="角色名称：">
          <el-input v-model="roleData.name" style="width: 250px"></el-input>
        </el-form-item>
        <el-form-item label="描述：">
          <el-input v-model="roleData.description"
                    type="textarea"
                    :rows="5"
                    style="width: 250px"></el-input>
        </el-form-item>
        <el-form-item label="是否启用：">
          <el-radio-group v-model="roleData.status">
            <el-radio :label="1">是</el-radio>
            <el-radio :label="0">否</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="roleDialogVisible = false" size="small">取 消</el-button>
        <el-button type="primary" @click="handleDialogConfirm()" size="small">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
  import {fetchRoleList, addRole, updateRole, deleteRole, updateStatus} from '@/api/role';

  import {formatDate} from '@/utils/date';

  const defaultListQuery = {
    pageNum: 1,
    pageSize: 5,
    keyword: null
  };
  const defaultRole = {
    id: null,
    name: null,
    description: null,
    adminCount: 0,
    status: 1
  };
  export default {
    name: 'roleList',
    data() {
      return {
        listQuery: Object.assign({}, defaultListQuery),
        list: null,
        total: null,
        listLoading: false,
        roleDialogVisible: false,
        roleData: Object.assign({}, defaultRole),
        isEdit: false,
        permissionList: [],

      }
    },
    created() {
      this.getRoleList();
    },
    filters: {
      formatDateTime(time) {
        if (time == null || time === '') {
          return 'N/A';
        }
        let date = new Date(time);
        return formatDate(date, 'yyyy-MM-dd hh:mm:ss')
      }
    },
    methods: {
      handleResetSearch() {
        this.listQuery = Object.assign({}, defaultListQuery);
      },
      handleSearchList() {
        this.listQuery.pageNum = 1;
        this.getRoleList();
      },
      handleSizeChange(val) {
        this.listQuery.pageNum = 1;
        this.listQuery.pageSize = val;
        this.getRoleList();
      },
      handleCurrentChange(val) {
        this.listQuery.pageNum = val;
        this.getRoleList();
      },
      handleAdd() {
        this.roleDialogVisible = true;
        this.isEdit = false;
        this.roleData = Object.assign({},defaultRole);
      },
      handleStatusChange(index, row) {
        this.$confirm('是否要修改该状态?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          updateStatus(row.id, {status: row.status}).then(response => {
            this.$message({
              type: 'success',
              message: '修改成功!'
            });
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '取消修改'
          });
          this.getRoleList();
        });
      },
      handleDelete(index, row) {
        this.$confirm('是否要删除该角色?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteRole(row.id).then(response => {
            if (response.code === 0) {
              this.$message({
                type: 'success',
                message: '删除成功!'
              });
              this.getRoleList();
            } else {
              this.$message({
                type: 'success',
                message: '删除失败！原因：' + response.message,
              });
            }

            
          });
        });
      },
      handleUpdate(index, row) {
        this.roleDialogVisible = true;
        this.isEdit = true;
        this.roleData = Object.assign({}, row);
      },
      handleDialogConfirm() {
        console.log('handleDialogConfirm this.roleData: ', this.roleData)
        this.$confirm('是否要确认?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          if (this.isEdit) {
            // 修改
            updateRole(this.roleData).then(response => {
              this.$message({
                message: '修改成功！',
                type: 'success'
              });
              this.roleDialogVisible =false;
              this.getRoleList();
            })
          } else {
            // 添加
            addRole(this.roleData).then(response => {
              this.$message({
                message: '添加成功！',
                type: 'success'
              });
              this.roleDialogVisible =false;
              this.getRoleList();
            })
          }
        })
      },
      handleSelectMenu(index,row){
        this.$router.push({path:'/ums/allocMenu',query:{roleId:row.id}})
      },
      handleSelectResource(index,row){
        this.$router.push({path:'/ums/allocResource',query:{roleId:row.id}})
      },
      getRoleList() {
        this.listLoading = true;
        fetchRoleList(this.listQuery).then(response => {
          this.listLoading = false;
          console.log('fetchRoleList response', response)
          this.list = response.data.dataList;
          this.total = response.data.totalRows;
        })
      },
    }
  }
</script>
<style></style>


