<template>
  <div class="ClassAdd">
    <!-- 面板 -->
    <el-card class="box-card">
      <div slot="header" class="clearfix title">
        <span class="el-icon-d-arrow-right"></span>
        <span>新增文章</span>
      </div>
      <div class="text item">
        <el-form
            ref="articleFormData"
            :model="articleFormData"
            :rules="rules"
            class="demo-ruleForm"
            label-width="100px"
            size="small"
            status-icon
            style="width:400px"
        >
          <el-form-item label="外部文章链接" prop="url">
            <el-input v-model="articleFormData.url" auto-complete="off" type="text"></el-input>
          </el-form-item>
          <el-form-item label="内部文章内容" prop="content">
            <el-input autosize v-model="articleFormData.content" auto-complete="off" type="textarea"></el-input>
          </el-form-item>
          <el-form-item label="标题" prop="title">
            <el-input v-model="articleFormData.title" auto-complete="off" type="text"></el-input>
          </el-form-item>
          <!-- 按钮组 -->
          <el-form-item>
            <el-button type="primary" @click="submitForm('studentAddForm')">添加</el-button>
            <el-button @click="resetForm('studentAddForm')">重置</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  // 注入reloa依赖
  inject: ['reload'],
  data() {
    return {
      // 表单数据
      articleFormData: {
        adminId: "",
        url: "",
        content: "",
        title: "",
        addtime: ""
      },
      // 验证规则
      rules: {
        url: [
          {required: true, message: '请输入链接', trigger: 'blur'}
        ],
        content: [
          {required: false, message: '请输入内容', trigger: 'blur'}
        ],
        title: [
          {
            required: true, message: '请输入标题', trigger: 'blur'
          }
        ],
      },
    }
  },
  methods: {
    // 重置按钮事件
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    add0(m){return m<10?'0'+m:m },
    //   提交按钮事件
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        // 如果成功
        if (valid) {
          let time = new Date()
          var y = time.getFullYear();
          var m = time.getMonth()+1;
          var d = time.getDate();
          var h = time.getHours();
          var mm = time.getMinutes();
          var s = time.getSeconds();
          let timeString = y+'-'+this.add0(m)+'-'+this.add0(d)+' '+this.add0(h)+':'+this.add0(mm)+':'+this.add0(s);
          const user = JSON.parse(localStorage.getItem("userdata"))
          let params = {
            adminId: user.id,
            url: this.articleFormData.url,
            content: this.articleFormData.content,
            title: this.articleFormData.title,
            addtime: timeString
          };
          // 发送请求
          this.request.post('/admin/article/insert_update', params)
              .then(res => {
                if (res.flag) {
                  this.$message.success("保存成功")
                } else {
                  this.$message.error("保存失败请联系管理员")
                }
              })
              .catch(err => {
                this.$message.error("请求失败请联系管理员")
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
@import "ArticleAdd.less";
</style>
