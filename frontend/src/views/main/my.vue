<template>
    <div>
        <el-header class="navbar">
            <div class="logo">乒乓球比赛主页</div>
            <div class="nav-right">
                <el-button type="primary" size="mini" @click="dialogVisible = true">退出</el-button>
            </div>
        </el-header>

        <div class="my-page">
        
            <h2>乒乓球比赛报名表</h2>
            <!-- 报名状态提示：仅在 submitted 为 true 时显示 -->
            <div>
            <span>您的报名状态：{{ submitted ? '已提交，请等待审核通过' : '待提交'  }}</span>
            </div>
          
            <el-form :model="form" label-width="100px" class="form-container">
            <el-form-item label="姓名">
                <el-input v-model="form.name" />
            </el-form-item>
            <el-form-item label="性别">
                <el-radio-group v-model="form.gender">
                <el-radio label="男">男</el-radio>
                <el-radio label="女">女</el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item label="年龄组别">
                <el-select v-model="form.group" placeholder="请选择">
                <el-option label="青年组（18-30）" value="youth" />
                <el-option label="成人组（31-50）" value="adult" />
                <el-option label="老年组（51+）" value="senior" />
                </el-select>
            </el-form-item>
            <el-form-item label="联系方式">
                <el-input v-model="form.contact" />
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitForm">
                {{ submitted ? '已提交请勿重复提交' : '报名提交' }}    
            </el-button>
            </el-form-item>
            </el-form>
      </div>
    </div>
    
  </template>
  
  <script>
  export default {
    name: 'my',
    data() {
      return {
        form: {
          name: '',
          gender: '',
          group: '',
          contact: ''
        },
        submitted: false
      }
    },
    methods: {
      submitForm() {
        if (this.form.name && this.form.gender && this.form.group && this.form.contact) {
            this.submitted = true
          this.$message.success('报名成功！')
        } else {
          this.$message.error('请填写完整信息')
        }
      }
    }
  }
  </script>
  
  <style scoped>
  .navbar {
    display: flex;
    align-items: center;
    justify-content: space-between;
    /* position: fixed; */
    top: 0;
    left: 0;
    right: 0;
    height: 60px;
    background: #409EFF;
    color: white;
    padding: 0 30px;
    z-index: 1000;
    }

    .logo {
    font-size: 20px;
    font-weight: bold;
    }

    .nav-right {
    display: flex;
    align-items: center;
    }

  .my-page {
    max-width: 600px;
    margin: 50px auto;
    padding: 30px;
    border: 1px solid #eee;
    border-radius: 8px;
    background-color: #fafafa;
  }
  .form-container {
    margin-top: 20px;
  }
  </style>
  