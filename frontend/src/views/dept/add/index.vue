<template>
  <el-card class="form-container" shadow="never">
    <div style="margin-top: 50px">
      <el-form
        :model="deptData"
        :rules="rules"
        ref="userInfoFormRef"
        label-width="120px"
        class="form-inner-container"
        size="small"
      >
        <el-form-item label="编号" prop="deptNo">
          <el-input v-model="deptData.deptNo"></el-input>
        </el-form-item>
        <el-form-item label="负责人" prop="manageName">
          <el-input v-model="deptData.manageName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="手机">
          <el-input v-model="deptData.phone"></el-input>
        </el-form-item>
        <el-form-item label="项目">
          <el-input v-model="deptData.dept"></el-input>
        </el-form-item>
        <el-form-item style="text-align: center">
          <el-button type="primary" size="medium" @click="saveDept()"
            >保存</el-button
          >
        </el-form-item>
      </el-form>
    </div>
  </el-card>
</template>
  
<script>
import { addUser } from "@/api/user";

export default {
  name: "addDept",
  data() {
    var validatePass = (rule, value, callback) => {
      console.log("validatePass:" + value);
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        if (this.deptData.password !== "") {
          this.$refs.userInfoFormRef.validateField("checkPass");
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      console.log("validatePass2:" + value);
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.deptData.password) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
    return {
      deptData: {
        deptNo: "",
        manageName: "",
        phone: "",
        dept: "",
      },
      rules: {
        name: [
          { required: true, message: "请输入名称", trigger: "blur" },
          {
            min: 2,
            max: 140,
            message: "长度在 2 到 140 个字符",
            trigger: "blur",
          },
        ],
        username: [
          { required: true, message: "该项为必填项", trigger: "blur" },
        ],
      },
    };
  },
  created() {},
  computed: {},
  watch: {},
  methods: {
    handleBrandChange() {},
    saveDept() {
      let userParam = this.deptData;
      console.log(JSON.stringify(userParam));

      // 检查表单是否可以通过

      // 给提示，是否确认添加

      this.$confirm("请检查表单信息，是否要确认保存", "确认信息", {
        distinguishCancelAndClose: true,
        confirmButtonText: "保存",
        cancelButtonText: "取消",
      })
        .then(() => {
          addUser(userParam).then((response) => {
            console.log("add User", response);
            if (response.code === 0) {
              this.$message({
                message: "保存成功！",
                type: "success",
              });
              console.log("用户添加成功");
              // 跳转到用户列表
            }
          });
        })
        .catch((action) => {
          this.$message({
            type: "info",
            message: action === "cancel" ? "取消保存" : "停留在当前页面",
          });
        });
    },
  },
};
</script>
  
<style scoped>
</style>
  