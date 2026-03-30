<template> 
    <div class="app-container">
      <!-- 搜索 -->
      <el-card class="filter-container" shadow="never">
        <div>
          <i class="el-icon-search"></i>
          <span>筛选搜索</span>
          <el-button
            style="float: right"
            @click="handleSearchList()"
            type="primary"
            size="small">
            查询结果
          </el-button>
          <el-button
            style="float: right;margin-right: 15px"
            @click="handleResetSearch()"
            size="small">
            重置
          </el-button>
        </div>
        <div style="margin-top: 15px">
          <el-form :inline="true" :model="listQuery" size="small" label-width="140px">
            <el-form-item label="用户名查询：">
              <el-input style="width: 203px" v-model="listQuery.username" placeholder="用户名"></el-input>
            </el-form-item>
            <el-form-item label="手机号查询：">
              <el-input style="width: 203px" v-model="listQuery.phone" placeholder="手机号"></el-input>
            </el-form-item>
            
            <!-- <el-form-item label="审核状态：">
              <el-select v-model="listQuery.verifyStatus" placeholder="全部" clearable>
                <el-option
                  v-for="item in verifyStatusOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item> -->
          </el-form>
        </div>
      </el-card>
      <el-card class="operate-container" shadow="never">
        <i class="el-icon-tickets"></i>
        <span>数据列表</span>
        <el-button
          class="btn-add"
          @click="handleAddUser()"
          size="mini">
          添加
        </el-button>
      </el-card>

      <!-- 数据展示 -->
      <div class="table-container">
        <el-table ref="productTable"
                  :data="list"
                  style="width: 100%"
                  @selection-change="handleSelectionChange"
                  v-loading="listLoading"
                  border>
          <el-table-column type="selection" width="60" align="center"></el-table-column>
          <el-table-column label="编号" width="50" align="center">
            <template slot-scope="scope">{{ (listQuery.pageNum - 1) * listQuery.pageSize + scope.$index + 1  }}</template>
          </el-table-column>
          <el-table-column label="ID" width="200" align="center">
            <template slot-scope="scope">{{scope.row.id}}</template>
          </el-table-column>
          <el-table-column label="用户头像" width="120" align="center">
            <template slot-scope="scope"><img style="height: 80px" :src="scope.row.avatar"></template>
          </el-table-column>
          <el-table-column label="用户名"  width="100" align="center">
            <template slot-scope="scope">{{scope.row.username}}</template>
          </el-table-column>
          <el-table-column label="手机号" width="110" align="center">
            <template slot-scope="scope"><p>{{scope.row.phone}}</p></template>
          </el-table-column>
          <el-table-column label="性别" width="50" align="center">
            <template slot-scope="scope"><p>{{scope.row.sex === 1 ? '男' : '女'}}</p></template>
          </el-table-column>
          <el-table-column label="姓名" width="80" align="center">
            <template slot-scope="scope">{{scope.row.realName}}</template>
          </el-table-column>
          <el-table-column label="邮箱" width="130" align="center">
            <template slot-scope="scope">{{scope.row.email}}</template>
          </el-table-column>
          <el-table-column label="状态" width="100" align="center">
            <template slot-scope="scope">{{scope.row.status == 1 ? '未审核' : '审核通过'}}</template>
          </el-table-column>
          <el-table-column label="创建时间" width="100" align="center">
            <template slot-scope="scope"><p>{{formatDateStr(scope.row.createTime)}}</p></template>
          </el-table-column>
          <el-table-column label="操作" width="160" align="center">
            <template slot-scope="scope">
              <p>
                <el-button
                  size="mini"
                  type="success"
                  plain
                  @click="handleUpdateStatus(scope.$index, scope.row, 3)">审核
                </el-button>
                <el-button
                  size="mini"
                  type="danger"
                  plain
                  @click="handleUpdateStatus(scope.$index, scope.row, 1)">撤销
                </el-button>
              </p>
              <p>
                <el-button
                  size="mini"
                  plain
                  @click="handleShowProduct(scope.$index, scope.row)">查看
                </el-button>
                <el-button
                  size="mini"
                  type="danger"
                  plain
                  @click="handleDelete(scope.$index, scope.row)">删除
                </el-button>
              </p>
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
          layout="total, sizes, prev, pager, next, jumper"
          :page-size="listQuery.pageSize"
          :page-sizes="[5,10,15]"
          :current-page.sync="listQuery.pageNum"
          :total="total">
        </el-pagination>
      </div>

      <!--查看用户详细信息 -->
      <el-dialog
      title="用户信息'"
      :visible.sync="viewUserInfodialogVisible"
      width="60%">
      <el-descriptions
        :column="2"
        border
        size="small"
        :label-style="{ width: '150px', fontWeight: 'bold' }"
        :content-style="{ color: '#555' }">
        <el-descriptions-item label="帐号">{{ userData.username }}</el-descriptions-item>
        <el-descriptions-item label="姓名">{{ userData.realName }}</el-descriptions-item>
        <el-descriptions-item label="昵称">{{ userData.nickName }}</el-descriptions-item>
        <el-descriptions-item label="手机号">{{ userData.phone }}</el-descriptions-item>
        <el-descriptions-item label="邮箱">{{ userData.email }}</el-descriptions-item>
        <el-descriptions-item label="性别">
          <!-- {{ getSexLabel(userData.sex) }} -->
        </el-descriptions-item>
        <el-descriptions-item label="状态"> {{ userData.status == 1 ? '未审核' : '审核通过' }} </el-descriptions-item>
      </el-descriptions>
      <span slot="footer" class="dialog-footer">
        <el-button @click="viewUserInfodialogVisible = false" size="small">取 消</el-button>
      </span>
    </el-dialog>

    </div>
  </template>
  <script>
    import { fetchUserList, updateUser, deleteUsers } from '@/api/user'
    import { formatDateStr } from '@/utils/date'
  
    const defaultListQuery = {
      pageNum: 1,
      pageSize: 5,
      username: null,
      phone: null,
      userType: 2,
    };
    const defaultUserData = {
      username: null,
      avatar: null, 
      password: null,
      checkPass: null,
      phone: null,
      realName: null,
      nickName: null,
      email: null,
      sex: null,
      deptId: null,
      createWhere: null,
      userType: 1,
      deptId: 'no'
    };
    export default {
      name: "productList",
      data() {
        return {
          listQuery: Object.assign({}, defaultListQuery),
          list: null,
          total: 0,
          listLoading: true,
          selectProductCateValue: null,
          multipleSelection: [],
          viewUserInfodialogVisible: false,
          userData: Object.assign({}, defaultUserData),
        }
      },
      created() {
        this.getList();
      },
      watch: {
        selectProductCateValue: function (newValue) {
          if (newValue != null && newValue.length == 2) {
            this.listQuery.productCategoryId = newValue[1];
          } else {
            this.listQuery.productCategoryId = null;
          }
  
        }
      },
      filters: {
        verifyStatusFilter(value) {
          if (value === 1) {
            return '审核通过';
          } else {
            return '未审核';
          }
        }
      },
      methods: {
        formatDateStr,
        getList() {
          this.listLoading = true;
          console.log('getList() this.listQuery:', JSON.stringify(this.listQuery))
          fetchUserList(this.listQuery).then(response => {
            this.listLoading = false;
            console.log('getList :', response)
            this.list = response.data.dataList
            this.total = response.data.totalRows
            console.log('getList this.total :' + this.total)
          })
        },
        handleSearchList() {
          this.listQuery.pageNum = 1;
          this.getList();
        },
        handleAddUser() {
          this.$router.push({path:'/user/addUser'});
        },
        handleSizeChange(val) {
          this.listQuery.pageNum = 1;
          this.listQuery.pageSize = val;
          this.getList();
        },
        handleCurrentChange(val) {
          this.listQuery.pageNum = val;
          this.getList();
        },
        handleSelectionChange(val) {
          this.multipleSelection = val;
        },
        
        handleResetSearch() {
          this.selectProductCateValue = [];
          this.listQuery = Object.assign({}, defaultListQuery);
        },
        handleDelete(index, row){
          this.$confirm('是否要进行删除操作?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            let param = [];
            param.push(row.id);
            deleteUsers(param).then(response => {
              this.$message({
                type: 'success',
                message: '删除成功!'
              });
              this.getList();
            })
          });
        },
        handleUpdateStatus(index,row, status_code) {
          console.log('handleUpdateStatus row: ', JSON.stringify(row))
          let confirmText = ''
          if (status_code === 1) {
            if (row.status === 1) { // 验证当前状态
                this.$message({type: 'warning',message: '已撤销，请勿重复撤销!'});
              return
            }
            confirmText = '是否对用户：' + row.username +' 进行撤销操作?'
          } else if (status_code === 3) {
            if (row.status === 3) { // 验证当前状态
                this.$message({type: 'warning',message: '已通过，请勿重复审核!'});
              return
            }
            confirmText = '是否对用户：' + row.username +' 审核通过?'
          }
          this.$confirm(confirmText, '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            let param = {
              id: row.id,
              status: status_code // 1是正常，2是锁定，3是通过
            }
            console.log('handleUpdateStatus param:', JSON.stringify(param))
            updateUser(param).then(response => {
              console.log('handleUpdateStatus response:', response)
              if (response.code == 0) {
                this.$message({
                  type: 'success',
                  message: '更新成功!'
                });
                this.getList();
              } else {
                this.$message({
                  type: 'warning',
                  message: '更新失败!'
                });
              }
            })
          });
        },
        handleShowProduct(index,row){
          console.log("handleShowProduct",row);
          this.viewUserInfodialogVisible = true
          this.userData = Object.assign({},row);
        },
      }
    }
  </script>
  <style></style>
  
  
  