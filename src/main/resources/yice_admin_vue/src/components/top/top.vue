<template>
  <div class="top">
    <!-- 分栏 -->
    <el-row :gutter="20">
      <!-- 标题 -->
      <el-col :span="20">
        <h3><span class="el-icon-location"></span>宜厕管理系统</h3>
      </el-col>
      <!-- 头像&设置 -->
      <el-col :span="4">
        <el-row :gutter="20">
          <!-- 下拉菜单 -->
          <el-col :span="16">
            <span >{{account}}</span>
            <span @click="loginout" style="font-size: 10px;margin-left: 10px;color: red"> 退出</span>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  data() {
    return {
      account:"用户名"
    }
  },
  mounted() {
    let userdata = JSON.parse(localStorage.getItem("userdata"))
    console.log(userdata)
    if(userdata.classID){
      this.account = userdata.student.name
    }else{
      this.account = userdata.name
    }

  },
  methods: {
    loginout() {
      this.$confirm('此操作将注销登录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        localStorage.removeItem("userdata")
        this.$router.push('/login')
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消操作'
        });
      });
    }
  }
}
</script>

<style lang="less">
@import "./top.less";
</style>


