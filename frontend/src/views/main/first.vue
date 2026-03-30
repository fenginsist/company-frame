<template>
  <div>
    <!-- 固定顶部导航栏 -->
    <el-header class="navbar">
      <div class="logo">乒乓球比赛主页</div>
      <div class="nav-right">
        <el-button type="primary" size="mini" @click="dialogVisible = true"
          >登录/注册</el-button
        >
      </div>
    </el-header>
    <!-- 轮播图 -->
    <el-carousel trigger="click" height="600px">
      <el-carousel-item v-for="(item, index) in carouselImages" :key="index">
        <img :src="item" class="carousel-img" />
      </el-carousel-item>
    </el-carousel>

    <!-- 比赛简介 -->
    <div class="intro">
      <h2>比赛简介</h2>
      <p>
        2025年春季乒乓球锦标赛即将拉开帷幕，诚邀各路乒乓高手与爱好者们共襄盛事！本次赛事面向所有乒乓球爱好者，无论你是初出茅庐的新手，还是久经沙场的老将，都能在这里找到属于你的舞台。为了确保比赛的公平性和趣味性，我们特别设立了多个年龄组别，让不同年龄段的选手都能同场竞技，一展身手。比赛奖品丰厚，除了荣誉证书和奖杯之外，还有专业的乒乓球器材、运动装备等你来拿！我们相信，这场赛事不仅是一场竞技的盛宴，更是一次乒乓球爱好者们交流学习、增进友谊的绝佳机会。心动不如行动，赶快报名参加，让我们一起在球台上挥洒汗水，享受乒乓球的魅力吧！期待你的加入，让我们共同见证这场乒乓球的盛会！
      </p>
    </div>

    <!-- 底部信息 -->
    <el-footer class="footer">© 2025 乒乓球比赛组织委员会</el-footer>

    <!-- 登录/注册弹窗 -->
    <el-dialog title="欢迎登录" :visible.sync="dialogVisible" width="400px">
      <el-tabs v-model="activeTab">
        <el-tab-pane label="登录" name="login">
          <el-form :model="loginForm" label-width="80px">
            <el-form-item label="用户名">
              <el-input v-model="loginForm.username" />
            </el-form-item>
            <el-form-item label="密码">
              <el-input v-model="loginForm.password" show-password />
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="handleLogin">登录</el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>

        <el-tab-pane label="注册" name="register">
          <el-form :model="registerForm" label-width="80px" ref="registerForm">
            <el-form-item
              label="用户名"
              prop="username"
              :rules="[
                { required: true, message: '请输入用户名', trigger: 'blur' },
              ]"
            >
              <el-input v-model="registerForm.username" />
            </el-form-item>
            <el-form-item
              label="密码"
              prop="password"
              :rules="[
                { required: true, message: '请输入密码', trigger: 'blur' },
              ]"
            >
              <el-input type="password" v-model="registerForm.password" />
            </el-form-item>
            <el-form-item
              label="确认密码"
              prop="confirmPassword"
              :rules="[
                { required: true, message: '请再次输入密码', trigger: 'blur' },
                { validator: validateConfirmPassword, trigger: 'blur' },
              ]"
            >
              <el-input
                type="password"
                v-model="registerForm.confirmPassword"
              />
            </el-form-item>
            <el-form-item>
              <el-button type="success" @click="handleRegister">注册</el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>
      </el-tabs>
    </el-dialog>
  </div>
</template>
  
  <script>
import pingpang1 from "@/assets/first/pingpang1.jpeg";
import pingpang2 from "@/assets/first/pingpang2.png";
import pingpang3 from "@/assets/first/pingpang3.png";
export default {
  name: "first",
  data() {
    return {
      dialogVisible: false,
      activeTab: "login",
      loginForm: {
        username: "",
        password: "",
      },
      registerForm: {
        username: "",
        password: "",
      },
      carouselImages: [pingpang1, pingpang2, pingpang3],
    };
  },
  methods: {
    handleLogin() {
      if (this.loginForm.username && this.loginForm.password) {
        this.$message.success("登录成功");
        this.dialogVisible = false;
        this.$router.push("/my");
      } else {
        this.$message.error("请输入用户名和密码");
      }
    },
    handleRegister() {
      this.$refs.registerForm.validate((valid) => {
        if (valid) {
          // 如果校验通过，则进行注册逻辑
          this.$message.success("注册成功，请登录");
          this.activeTab = "login";
        } else {
          // 如果校验不通过，则提示用户
          this.$message.error("请填写完整信息");
          return false;
        }
      });
    },
    validateConfirmPassword(rule, value, callback) {
      if (value !== this.registerForm.password) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    },
  },
};
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
  background: #409eff;
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

.carousel-img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.intro {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 40px 20px;
  text-align: center;
  background-color: #f9f9f9;
}

.intro h2 {
  margin-bottom: 20px;
}

.footer {
  text-align: center;
  padding: 20px;
  background: #f0f0f0;
  font-size: 14px;
  color: #666;
  margin-top: 40px;
}
</style>
  