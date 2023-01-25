<template>
  <div class="login">
    <div class="loginbox">
      <!-- 标题 -->
      <div class="title">
        <span class="el-icon-menu"></span>
        <span>宜厕后台管理系统</span>
      </div>
      <el-form ref="loginForm" :model="loginForm" :rules="rules" class="demo-ruleForm" label-width="100px" status-icon>
        <!-- 账号 -->
        <el-form-item label="账号" prop="account">
          <el-input v-model="loginForm.account" auto-complete="off" type="text"></el-input>
        </el-form-item>
        <!-- 密码 -->
        <el-form-item label="密码" prop="password">
          <el-input v-model="loginForm.password" auto-complete="off" type="password"></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="submitForm('loginForm')">提交</el-button>
          <el-button @click="resetForm('loginForm')">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
// 引入验证用户名函数

export default {
  data() {
    // 用户名验证
    const validateAccount = (rule, value, callback) => {
      if (!value) {
        callback(new Error("学号不能为空"));//输出错误提示
      } else {
        callback();//成功回调
      }
    }
    // 密码验证
    const validatePassword = (rule, value, callback) => {
      if (!value) {
        callback(new Error("密码不能为空"));//输出错误提示
      } else {
        callback();//成功回调
      }
    }
    return {
      // 表单数据
      loginForm: {
        account: "",
        password: ""
      },
      // 验证规则
      rules: {
        // 账号验证
        account: [
          {required: true, validator: validateAccount, trigger: 'blur'}
        ],
        // 密码验证
        password: [
          {required: true, validator: validatePassword, trigger: 'blur'}
        ]
      }
    }
  },
  methods: {
    // 重置按钮事件
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    //   提交按钮事件
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        // 如果成功
        if (valid) {

          let params = {
            phone: this.loginForm.account,
            password: this.loginForm.password
          };
          // 提交数据给后端
          this.request.post('/admin/login', params)
              .then(res => {
                if (res.flag) {
                  localStorage.setItem("userdata", JSON.stringify(res.data))
                  this.$message.success("恭喜您登录成功")
                  this.$router.push("/home/workspace")
                } else {
                  this.$message.error("用户名或密码错误")
                }
              })
              .catch(err => {

                this.$message.error("OMG！出错了请联系管理员")
              })
        } else {
          return false;
        }
      });
    },

  }
}
</script>

<style lang="less">
@import "./login.less";
</style>
