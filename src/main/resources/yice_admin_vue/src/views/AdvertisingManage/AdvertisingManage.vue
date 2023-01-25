<template>
  <div class="CourseManage">
    <el-card class="box-card">
      <div slot="header" class="clearfix title">
        <span class="iconfont icon-guanli"></span>
        <span>广告管理</span>
      </div>
      <div class="text item">
        <!-- 弹出编辑模态框 -->
        <el-dialog :visible.sync="dialogFormVisible"
                   modal-append-to-body
                   title="编辑广告信息"
                   top="5vh"
                   width="450px"
        >
          <el-form ref="editFormData" :model="AdvertisingFormData" :rules="rules" size="small">
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
          </el-form>

          <div slot="footer" class="dialog-footer">
            <el-button size="small" @click="dialogFormVisible = false">取 消</el-button>
            <el-button size="small" type="success" @click="saveEdit">发 布</el-button>
          </div>
        </el-dialog>
        <!-- 筛选器 -->
        <div class="filterBox">
          <!-- 关键字 -->
          输入编号:
          <el-input v-model="keyword" auto-complete="off" size="small" type="text"></el-input>
          <el-button size="small" type="primary" @click="selectId">查询</el-button>
        </div>
        <!-- 表格 -->
        <el-table
            ref="multipleTable"
            :data="advertisingTableData"
            header-align="center"
            style="width: 100%"
            tooltip-effect="dark"
        >
          <!--广告编号-->
          <el-table-column
              align="center"
              label="广告id"
              prop="id"
          >
          </el-table-column>
          <!-- 管理员姓名 -->
          <el-table-column
              align="center"
              label="管理员姓名"
              prop="adminId"
          >
          </el-table-column>

          <!-- 图片地址 -->
          <el-table-column
              align="center"
              label="图片"
              show-overflow-tooltip
          >
            <template slot-scope="scope">
              <el-popover placement="top-start" title="" trigger="hover">
                <el-image
                    :src="scope.row.imageUrl"
                    style="width: 300px; height: 300px">
                </el-image>
                <img slot="reference" :src="scope.row.imageUrl" style="width: 100px; height:100px;"/>
              </el-popover>
            </template>
          </el-table-column>
          <!--商品地址-->
          <el-table-column
              align="center"
              label="商品地址"
              prop="shopUrl"
              show-overflow-tooltip>
          </el-table-column>

          <!--标题-->
          <el-table-column
              align="center"
              label="标题"
              prop="title"
              show-overflow-tooltip>
          </el-table-column>

          <el-table-column
              :formattet="timeFormatter"
              align="center"
              label="创建时间"
              prop="addtime">
          </el-table-column>
          <!-- 编辑 -->
          <el-table-column align="center" label="操作">
            <template slot-scope="scope">
              <!-- 编辑 -->
              <el-button
                  size="mini"
                  type="success"
                  @click="handleEdit(scope.row)">
                <span class="el-icon-edit"></span>
                编辑
              </el-button>
              <!-- 删除 -->
              <el-button
                  size="mini"
                  type="danger"
                  @click="handleDelete(scope.row)">
                <span class="el-icon-delete"></span>
                删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
        <!-- 分页 -->
        <el-pagination
            :current-page="currentPage"
            :page-size="pageSize"
            :total="total"
            layout="total, prev, pager, next, jumper"
            @current-change="handleCurrentChange">
        </el-pagination>
      </div>
    </el-card>
  </div>
</template>

<script>

export default {
  data() {
    return {
      inputVisible: false,
      advertisingTableData: [
        {
          "id": 14,
          "adminId": "韩明",
          "imageUrl": "http://dummyimage.com/250x250",
          "shopUrl": "http://thrqhkocm.fi/pfiuujl",
          "title": "年队能图",
          "addtime": "1998-07-01  23:55:38"
        },
        {
          "id": 15,
          "adminId": "曾洋",
          "imageUrl": "http://dummyimage.com/240x400",
          "shopUrl": "http://etrmtxpfe.no/mvkuinqmm",
          "title": "干分动",
          "addtime": "2002-04-22  08:19:44"
        },
        {
          "id": 16,
          "adminId": "何明",
          "imageUrl": "http://dummyimage.com/120x600",
          "shopUrl": "http://fjrvqmli.is/rlsbjhts",
          "title": "养特十果提",
          "addtime": "1981-01-25  12:12:57"
        }
      ],
      fileList: [],
      // 回填数据
      AdvertisingFormData: {
        id: Number,
        adminId: "",
        imageUrl: "",
        shopUrl: "",
        title: "",
        addtime: ""
      },
      resultList: [],
      // 验证规则
      rules: {
        // 课程名称验证
        courseId: [
          {required: true, message: '请输入课程编号', trigger: 'blur'}
        ],
        // 课程名称验证
        courseName: [
          {required: true, message: '请输入课程名称', trigger: 'blur'}
        ],
        // 开启课程验证
        initiateMode: [
          {required: true, message: '请选择是否开启课程', trigger: 'change'}
        ],
        // 课程教师验证
        courseTeacher: [
          {required: true, message: '请选择课程教师', trigger: 'change'}
        ],
        // 课程专业验证
        courseMajor: [
          {required: true, message: '请选择专业', trigger: 'change'}
        ],
        // 课程班级验证
        courseClass: [
          {required: true, message: '请选择班级', trigger: 'blur'}
        ],
        // 课时验证
        courseLength: [
          {required: true, message: '请选择课时', trigger: 'change'}
        ],
        // 课程描述验证
        courseDesc: [
          {required: true, message: '请输入课程描述', trigger: 'blur'}
        ]
      },
      // 模态框是否可见
      dialogFormVisible: false,
      dialogFormVisibleSign: false,
      formLabelWidth: "80px",//模态框标签宽度
      multipleSelectionId: [],
      currentPage: 1,//当前页码
      total: 0,//总页数
      pageSize: 5,//每页显示数量
      keyword: "",
      // 保存id
      editId: "",
      cId: "",
      ary: []
    }
  },
  // 创建周期，钩子函数
  created() {
    this.getAdvertisingList();
  },
  methods: {
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },
    add0(m) {
      return m < 10 ? '0' + m : m
    },
    timeFormatter(row, index) {
      var time = new Date(Number.parseInt(row.addtime));
      var y = time.getFullYear();
      var m = time.getMonth() + 1;
      var d = time.getDate();
      var h = time.getHours();
      var mm = time.getMinutes();
      var s = time.getSeconds();
      return y + '-' + this.add0(m) + '-' + this.add0(d) + ' ' + this.add0(h) + ':' + this.add0(mm) + ':' + this.add0(s);
    },
    handleClose(tag) {
      this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1);
    },

    showInput() {
      this.inputVisible = true;
      this.$nextTick(_ => {
        this.$refs.saveTagInput.$refs.input.focus();
      });
    },
    //课程数据
    getAdvertisingList() {
      //向后端发送请求查询所有课程数据
      let params = {
        size: this.pageSize,
        current: this.currentPage
      }
      this.request.get('/admin/advertising/insert_update', params)
          .then(res => {
            // 接收传过来的参数
            this.advertisingTableData = res.data;
            this.ary = res.data
            this.total = res.count
          })
          .catch(err => {
            console.log(err);
          })
    },
    selectId() {
      //先判断值是否为空
      if (this.keyword == "") {
        this.getCourseList()
      } else {
        this.advertisingTableData = this.ary.filter((value) => {

          if (this.keyword == value.id) {
            return value
          }
        })
        this.total = this.advertisingTableData.length
      }
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },

    // 修改回填
    handleEdit(row) {
      // 出现模态框
      this.dialogFormVisible = true;
      this.AdvertisingFormData.id = row.id;
      this.AdvertisingFormData.adminId = row.adminId;
      this.AdvertisingFormData.imageUrl = row.imageUrl;
      this.AdvertisingFormData.shopUrl = row.shopUrl;
      this.AdvertisingFormData.title = row.title;
      this.AdvertisingFormData.addtime = row.addtime;

    },
    // 保存数据
    saveEdit() {
      // 发送请求
      const user = JSON.parse(localStorage.getItem("userdata"))
      let params = {
        id: this.AdvertisingFormData.id,
        adminId: user.id,
        imageUrl:this.fileList[0].url,
        shopUrl: this.AdvertisingFormData.shopUrl,
        title: this.AdvertisingFormData.title,
        addtime: this.AdvertisingFormData.addtime
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
    },
    // 删除
    handleDelete(row) {
      let params = {
        "id": row.id
      }
      //向后端发送请求
      this.request.get('/admin/advertising/delete', params)
          .then(res => {
            if (res.flag) {
              this.$message.success("删除成功")
              this.getCourseList()
            } else {
              this.$message.error("删除失败请联系管理员")
            }
          })
          .catch(err => {
            this.$message.error("请求失败请联系管理员")
          })
    },
    handleCurrentChange(val) {
      // 赋值
      this.currentPage = val;
      this.getCourseList();
    }
  }
}
</script>

<style lang="less">
@import "AdvertisingManage.less";
</style>
