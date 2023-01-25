<template>
  <div class="CourseAdd">
    <!-- 面板 -->
    <el-card class="box-card">
      <div slot="header" class="clearfix title">
        <span class="el-icon-d-arrow-right"></span>
        <span>新增广告</span>
      </div>
      <div class="text item">
        <el-form ref="AdvertisingFormData" :model="AdvertisingFormData" :rules="rules" size="small">
          <el-upload
              :file-list="fileList"
              :on-preview="handlePreview"
              :on-remove="handleRemove"
              accept=".jpg;.png"
              action="http://112.74.86.183:9000/admin/advertising/image_upload"
              class="upload-demo"
              limit=1
              list-type="picture"
              name="image">
            <el-button size="small" type="primary">点击上传</el-button>
            <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
          </el-upload>
          <el-form-item label="商品链接" prop="content">
            <el-input v-model="AdvertisingFormData.shopUrl" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="标题" prop="title">
            <el-input v-model="AdvertisingFormData.title" auto-complete="off" type="text"></el-input>
          </el-form-item>

          <!-- 按钮组 -->
          <el-form-item>
            <el-button type="primary" @click="submitForm('courseAddForm')">添加</el-button>
            <el-button @click="resetForm('courseAddForm')">重置</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-card>
  </div>
</template>

<script>

export default {
  // 注入reload依赖
  inject: ['reload'],
  data() {
    return {
      AdvertisingFormData: {
        id: Number,
        adminId: "",
        imageUrl: "",
        shopUrl: "",
        title: "",
        addtime: ""
      },
      fileList: [],

    }
  },

  methods: {

    // 重置按钮事件
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    //   提交按钮事件
    submitForm(formName) {
      // console.log(formName.courseClass)
      this.$refs[formName].validate((valid) => {
        // 如果成功
        if (valid) {
          // 发送请求
          const user = JSON.parse(localStorage.getItem("userdata"))
          let params = {
            adminId: user.id,
            imageUrl:this.fileList[0].url,
            shopUrl: this.AdvertisingFormData.shopUrl,
            title: this.AdvertisingFormData.title,
            addtime: new Date()
          }
          this.request.post('/admin/advertising/insert_update', params)
              .then(res => {
                // 回填数据
                if (res.flag) {
                  this.getArticleList()
                  this.$message.success("修改成功")
                  this.dialogFormVisible = false;
                } else {
                  this.$message.error("修改失败")
                }
              })
              .catch(err => {
                console.log(err);
              })
        } else {
          return false;
        }
      });
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },
  }
}
</script>

<style lang="less">
@import "AdvertisingAdd.less";
</style>
