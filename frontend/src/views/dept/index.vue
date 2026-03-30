<template> 
    <div class="app-container">
      <el-card class="operate-container" shadow="never">
        <i class="el-icon-tickets"></i>
        <span>数据列表</span>
        <el-button
          class="btn-add"
          @click="handleAddDept()"
          size="mini">
          添加
        </el-button>
      </el-card>

      <!-- 数据展示 -->
      <div class="table-container">
        <el-table ref="deptTable"
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
          <el-table-column label="编号" width="120" align="center">
            <template slot-scope="scope">{{ scope.row.deptNo }}</template>
          </el-table-column>
          <el-table-column label="负责人"  width="100" align="center">
            <template slot-scope="scope">{{scope.row.managerName}}</template>
          </el-table-column>
          <el-table-column label="手机号"  width="100" align="center">
            <template slot-scope="scope">{{scope.row.phone}}</template>
          </el-table-column>
          <el-table-column label="项目" width="110" align="center">
            <template slot-scope="scope"><p>{{scope.row.name}}</p></template>
          </el-table-column>
          <el-table-column label="状态" width="100" align="center">
            <template slot-scope="scope"><p>{{scope.row.status === 1 ? '发布' : '未发布'}}</p></template>
          </el-table-column>
          <el-table-column label="创建时间" width="100" align="center">
            <template slot-scope="scope"><p>{{formatDateStr(scope.row.createTime)}}</p></template>
          </el-table-column>
          <el-table-column label="操作" width="160" align="center">
            <template slot-scope="scope">
              <p>
                <el-button
                  size="mini"
                  @click="handleUpdateDeptStatus(scope.$index, scope.row, 1)">发布
                </el-button>
                <el-button
                  size="mini"
                  @click="handleUpdateDeptStatus(scope.$index, scope.row, 0)">撤销
                </el-button>
              </p>
              <p>
                <el-button
                  size="mini"
                  type="danger"
                  @click="handleDeleteDept(scope.$index, scope.row)">删除
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
    </div>
  </template>
  <script>
    import { fetchDeptList, updateDept, deleteDepts } from '@/api/dept'
    import { formatDateStr } from '@/utils/date'
  
    const defaultListQuery = {
      pageNum: 1,
      pageSize: 5,
      username: null,
      phone: null,
    };
    export default {
      name: "deptList",
      data() {
        return {
          listQuery: Object.assign({}, defaultListQuery),
          list: null,
          total: 0,
          listLoading: true,
          selectProductCateValue: null,
          multipleSelection: [],
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
          fetchDeptList(this.listQuery).then(response => {
            this.listLoading = false;
            console.log('fetchDeptList :', response)
            this.list = response.data
            this.total = response.data.length
            console.log('fetchDeptList this.total :' + this.total)
          })
        },
        handleSearchList() {
          this.listQuery.pageNum = 1;
          this.getList();
        },
        handleAddDept() {
          this.$router.push({path:'/dept/addDept'});
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
        handleDeleteDept(index, row){
          this.$confirm('是否要进行删除操作?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            let param = row.id;
            deleteDepts(param).then(response =>{
              console.log('handleDeleteDept row:', JSON.stringify(row))
              if (response.code === 0) {
                this.$message({
                  type: 'success',
                  message: '删除成功!'
                });
              }
              this.getList()
            })
          });
        },
        handleUpdateDeptStatus(index,row, status_code){
          let confirmText = ''
          console.log('handleUpdateDeptStatus row:', JSON.stringify(row))
          if (status_code === 1) {
            confirmText = '是否对：' + row.name +' 进行发布操作?'
          } else if (status_code === 0) {
            confirmText = '是否对：' + row.name +' 进行撤销操作?'
          }
          this.$confirm(confirmText, '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            let param = {
              id: row.id,
              pid: row.pid,
              status: status_code // 1是正常，0是舍弃
            }
            console.log('handleUpdateDeptStatus param:', JSON.stringify(param))
            // 业务逻辑层次有问题。
            updateDept(param).then(response => {
              console.log('handleUpdateDeptStatus response:', response)
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
      }
    }
  </script>
  <style></style>
  
  
  