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
            <el-input v-model="listQuery.username" class="input-width" placeholder="帐号" clearable></el-input>
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
      <el-table ref="userTable"
                :data="list"
                style="width: 100%; min-width: 1200px;"
                v-loading="listLoading" border>
        <el-table-column type="selection" :min-width="60" align="center"></el-table-column>
        <el-table-column label="编号" :min-width="50" align="center">
          <template slot-scope="scope">{{ (listQuery.pageNum - 1) * listQuery.pageSize + scope.$index + 1  }}</template>
        </el-table-column>
        <el-table-column label="id" :min-width="300" align="center">
          <template slot-scope="scope">{{scope.row.id}}</template>
        </el-table-column>
        <el-table-column label="帐号" :min-width="100"  align="center">
          <template slot-scope="scope">{{scope.row.username}}</template>
        </el-table-column>
        <el-table-column label="姓名" :min-width="100" align="center">
          <template slot-scope="scope">{{scope.row.realName}}</template>
        </el-table-column>
        <el-table-column label="邮箱" :min-width="150" align="center">
          <template slot-scope="scope">{{scope.row.email}}</template>
        </el-table-column>
        <el-table-column label="添加时间" :min-width="160" align="center">
          <template slot-scope="scope">{{scope.row.createTime | formatDateTime}}</template>
        </el-table-column>
        <!-- <el-table-column label="最后登录" width="160" align="center">
          <template slot-scope="scope">{{scope.row.loginTime | formatDateTime}}</template>
        </el-table-column> -->
        <el-table-column label="是否启用" :min-width="140" align="center">
          <template slot-scope="scope">
            <el-switch
              @change="handleUserStatusChange(scope.$index, scope.row, scope.row.status === 1 ? 2 : 1)"
              :active-value=1
              :inactive-value=2
              v-model="scope.row.status">
            </el-switch>
          </template>
        </el-table-column>
        <el-table-column label="操作" :min-width="180" align="center">
          <template slot-scope="scope">
            <el-button size="mini"
                       type="text"
                       @click="handleSelectRole(scope.$index, scope.row)">分配角色
            </el-button>
            <el-button size="mini"
                       type="text"
                       @click="handleUpdate(scope.$index, scope.row)">
              编辑
            </el-button>
            <el-button size="mini"
                       type="text"
                       @click="handleDelete(scope.$index, scope.row)">删除
            </el-button>
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
        :page-sizes="[10,15,20]"
        :total="total">
      </el-pagination>
    </div>
    <!-- 添加/编辑 -->
    <el-dialog
      :title="isEdit?'编辑用户':'添加用户'"
      :visible.sync="dialogVisible"
      width="60%">
      <el-form :model="userData"
               ref="userFormRef"
               label-width="150px" size="small">
        <el-form-item 
          label="帐号：" 
          prop="username" 
          :rules="[
            { required: true, message: '请输入用户名', trigger: 'blur' },
            { min: 5, message: '用户名长度必须至少6位', trigger: 'blur' },
            { pattern: /^[A-Za-z0-9]+$/, message: '用户名只能包含英文和数字', trigger: 'blur' }
            ]">
          <el-input v-model="userData.username" style="width: 250px">
            <!-- 右侧提示内容 -->
            <template #append>
              <span style="color: #999; font-size: 12px;">请输入英文</span>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item 
          label="密码：" 
          prop="password" 
          :rules="[
            { required: true, message: '请输入密码', trigger: 'blur' },
            { min: 6, message: '密码长度必须至少6位', trigger: 'blur' }
            ]">
          <el-input type="password" v-model="userData.password" style="width: 250px"></el-input>
        </el-form-item>
        <el-form-item label="再次输入：" prop="checkPass" :rules="[{ required: true, message: '确认密码', trigger: 'blur' },]">
          <el-input type="password" v-model="userData.checkPass" style="width: 250px"></el-input>
        </el-form-item>
        <el-form-item label="姓名：" prop="realName" :rules="[{ required: true, message: '请输入姓名', trigger: 'blur' },]">
          <el-input v-model="userData.realName" style="width: 250px"></el-input>
        </el-form-item>
        <el-form-item label="昵称：" prop="nickName" :rules="[{ required: true, message: '请输入昵称', trigger: 'blur' },]">
          <el-input v-model="userData.nickName" style="width: 250px"></el-input>
        </el-form-item>
        <el-form-item label="手机号：" prop="phone" 
          :rules="[
            { required: true, message: '请输入手机号', trigger: 'blur' },
            { pattern: /^1[3-9]\d{9}$/, message: '手机号格式不正确', trigger: 'blur' }
            ]">
          <el-input v-model="userData.phone" style="width: 250px"></el-input>
        </el-form-item>
        <el-form-item label="邮箱：" prop="email" 
          :rules="[
            { required: true, message: '请输入邮箱', trigger: 'blur' },
            { type: 'email',  message: '邮箱格式不正确',  trigger: ['blur', 'change'] }
            ]">
          <el-input v-model="userData.email" style="width: 250px"></el-input>
        </el-form-item>
        <el-form-item label="性别：" prop="sex" :rules="[{ required: true, message: '请输入性别', trigger: 'blur' },]">
          <el-select v-model="userData.sex" placeholder="请选择">
            <el-option
              v-for="item in sex_options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <!-- <el-form-item label="是否启用：">
          <el-radio-group v-model="userData.status">
            <el-radio :label="1">是</el-radio>
            <el-radio :label="0">否</el-radio>
          </el-radio-group>
        </el-form-item> -->
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false" size="small">取 消</el-button>
        <el-button type="primary" @click="handleDialogConfirm()" size="small">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 分配角色 -->
    <el-dialog
      title="分配角色"
      :visible.sync="allocDialogVisible"
      width="80%">
      <!-- <el-select v-model="allocRoleIds" multiple placeholder="请选择" size="small" style="width: 80%">
        <el-option
          v-for="item in allRoleList"
          :key="item.id"
          :label="item.name"
          :value="item.id">
        </el-option>
      </el-select> -->
      <el-transfer
        filterable
        :filter-method="roleTransferFilterMethod"
        filter-placeholder="请输入城市拼音"
        v-model="roleTransferSelectValue"
        :titles="['所有角色', '已选择角色']"
        :button-texts="['到左边', '到右边']"
        :data="roleTransferShowData">
      </el-transfer>
      <span slot="footer" class="dialog-footer">
        <el-button @click="roleTransferDialogCancel()" size="small">取 消</el-button>
        <el-button type="primary" @click="roleTransferDialogConfirm()" size="small">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
  import {fetchUserList, addUser, updateUser, deleteUsers} from '@/api/user'
  import {fetchRoleList, getRoleListByCurrentUserId, saveUserOwnRole} from '@/api/role';
  import {formatDate} from '@/utils/date';

  const defaultListQuery = {
    pageNum: 1,
    pageSize: 10,
    keyword: null,
    userType: 1,
    username: "",
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
    name: 'adminList',
    data() {
      const generateData = _ => {
        const data = [];
        const cities = ['上海', '北京', '广州', '深圳', '南京', '西安', '成都'];
        const pinyin = ['shanghai', 'beijing', 'guangzhou', 'shenzhen', 'nanjing', 'xian', 'chengdu'];
        cities.forEach((city, index) => {
          data.push({
            label: city,
            key: index,
            pinyin: pinyin[index]
          });
        });
        return data;
      };

      return {
        listQuery: Object.assign({}, defaultListQuery),
        list: null,
        total: null,
        listLoading: false,
        dialogVisible: false,
        userData: Object.assign({}, defaultUserData),
        isEdit: false,
        allocDialogVisible: false,
        allocRoleIds:[],
        allRoleList:[],
        allocAdminId:null,
        sex_options: [{value: 1, label: '男'}, {value: 2, label: '女'}],
        // 穿梭框
        roleTransferShowData: [], // generateData() :展示的数据
        roleTransferSelectValue: [],  // 选择到右边的值
        roleTransferFilterMethod(query, item) {
          return item.label.indexOf(query) > -1;
        },
        roleTransferCurrentUserId: '', // 点击分配角色时，需要提前保存当前的用户ID。
      }
    },
    created() {
      this.getList();
      this.getAllRoleList();
    },
    watch: {
      roleTransferSelectValue(newVal, oldVal) {
        console.log('值变化了！新值:', newVal, '旧值:', oldVal);
      }
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
        this.getList();
      },
      handleSearchList() {
        this.listQuery.pageNum = 1;
        this.getList();
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
      handleAdd() {
        this.dialogVisible = true;
        this.isEdit = false;
        this.userData = Object.assign({},defaultUserData);
      },
      handleUserStatusChange(index, row, oldStatus) {
        console.log('handleUserStatusChange row:', JSON.stringify(row))
        this.$confirm('是否要修改该状态?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let param = {
            id: row.id,
            status: row.status // 1是正常，2是锁定
          }
          console.log('handleUserStatusChange param:', JSON.stringify(param))
          updateUser(param).then(response => {
            this.$message({
              type: 'success',
              message: '修改成功!'
            });
          })
          row.status = row.status;
        }).catch(() => {
          // 用户取消时，恢复原来的状态
          row.status = oldStatus;
          this.$message({
            type: 'info',
            message: '取消修改'
          });
        });
      },
      handleDelete(index, row) {
        this.$confirm('是否要删除该用户?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let param = [row.id]
          console.log('handleDelete param: ', param)
          deleteUsers(param).then(response => {
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
            this.getList();
          })
        });
      },
      handleUpdate(index, row) {
        this.dialogVisible = true;
        this.isEdit = true;
        this.userData = Object.assign({},row);
        this.userData.checkPass = row.password
        console.log('handleUpdate this.userData:', JSON.stringify(this.userData))
      },
      handleDialogConfirm() {
        console.log('handleDialogConfirm: ', JSON.stringify(this.userData))
        this.$refs.userFormRef.validate((valid) => {
          if (valid) {
            // 如果校验通过，则进行注册逻辑
            console.log('校验通过')
            this.$confirm('是否要确认?', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              if (this.isEdit) {
                console.log('handleDialogConfirm updateUser: ', JSON.stringify(this.userData))
                // 修改用户
                updateUser(this.userData).then(response => {
                  this.$message({
                    message: '修改成功！',
                    type: 'success'
                  });
                  this.dialogVisible =false;
                  this.getList();
                })
              } else {
                // 添加用户
                console.log('handleDialogConfirm addUser: ', JSON.stringify(this.userData))
                addUser(this.userData).then(response => {
                  this.$message({
                    message: '添加成功！',
                    type: 'success'
                  });
                  this.dialogVisible =false;
                  this.getList();
                })
              }
            })
          } else {
            // 如果校验不通过，则提示用户
            this.$message.error("请填写完整信息");
            return false;
          }
        });
        
      },
      roleTransferDialogConfirm(){
        this.$confirm('是否要确认?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let param = {
            userId: this.roleTransferCurrentUserId,
            roleIds: this.roleTransferSelectValue
          }
          console.log('roleTransferDialogConfirm muti roleTransferSelectValue:', this.roleTransferSelectValue)
          saveUserOwnRole(param).then(response => {
            if(response.code === 0) {
              this.$message({
                message: '分配成功！',
                type: 'success'
              });
            } else {
              this.$message({
                message: '分配失败！',
                type: 'warning'
              });
            }
            this.roleTransferCurrentUserId = ''
            this.allocDialogVisible = false;
          })
        }).catch(() => {
          // 用户取消时
          this.$message({
            type: 'info',
            message: '取消修改'
          });
          this.roleTransferCurrentUserId = ''
        });
      },
      roleTransferDialogCancel(){
        this.allocDialogVisible = false;
        this.roleTransferSelectValue = []
      },
      handleSelectRole(index,row){
        this.allocAdminId = row.id;
        this.allocDialogVisible = true;
        // 获取当前用户的所拥有的角色，展示在穿梭框的右边。
        this.roleTransferCurrentUserId = row.id;
        this.getRoleListByCurrentUser(row.id);
      },
      getList() {
        console.log('getList this.listQuery:', this.listQuery)
        this.listLoading = true;
        fetchUserList(this.listQuery).then(response => {
          this.listLoading = false;
          this.list = response.data.dataList;
          this.total = response.data.totalRows;
          console.log('getList fetchUserList :', response)
        })
      },
      getAllRoleList() {
        // 获取所有角色，等待分配角色使用
        let param = {
          pageNum: 1,
          pageSize: 1000
        }
        fetchRoleList(param).then(response => {
          this.allRoleList = response.data.dataList;
          this.allRoleList.forEach((item, index) => {
            // console.log('getAllRoleList this.roleTransferShowData: ', JSON.stringify(item))
            this.roleTransferShowData.push({
              label: item.name,
              key: item.id,
            })
          })
          console.log('getAllRoleList this.roleTransferShowData:', this.roleTransferShowData)
        })
      },
      getRoleListByCurrentUser(userId) {
        // 点击分配权限按钮，显示该用户已拥有的角色
        getRoleListByCurrentUserId(userId).then(response => {
          // console.log('getRoleListByCurrentUser: ', response)
          response.data.ownRoles.forEach((item, index) => {
            this.roleTransferSelectValue.push(item)
          })
          // console.log('getRoleListByCurrentUser this.roleTransferSelectValue: ', this.roleTransferSelectValue)
        })
      }
    }
  }
</script>
<style></style>


