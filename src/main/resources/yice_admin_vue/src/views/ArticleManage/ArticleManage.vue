<template>
  <div class="ClassManage">
    <el-card class="box-card">
      <div slot="header" class="clearfix title">
        <span class="iconfont icon-guanli"></span>
        <span>文章管理</span>
      </div>
      <div class="text item">
        <!-- 筛选器 -->
        <div class="filterBox">
          <!-- 关键字 -->
          文章id：
          <el-input v-model="keyword" auto-complete="off" size="small" type="text"></el-input>
          <el-button size="small" type="primary">查询</el-button>
        </div>
        <el-dialog :visible.sync="dialogFormVisible"
                   modal-append-to-body
                   title="编辑文章信息"
                   top="5vh"
                   width="450px"
        >
          <el-form ref="articleFormData" :model="articleFormData" :rules="rules" size="small">

            <el-form-item label="外部文章链接" prop="url">
              <el-input v-model="articleFormData.url" auto-complete="off" type="text"></el-input>
            </el-form-item>
            <el-form-item label="内部文章内容" prop="content">
              <el-input autosize v-model="articleFormData.content" auto-complete="off" type="textarea"></el-input>
            </el-form-item>
            <el-form-item label="标题" prop="title">
              <el-input v-model="articleFormData.title" auto-complete="off" type="text"></el-input>
            </el-form-item>
          </el-form>
          <!-- 尾部 -->
          <div slot="footer" class="dialog-footer">
            <el-button size="small" @click="dialogFormVisible = false">取 消</el-button>
            <el-button size="small" type="success" @click="saveEdit">确认修改</el-button>
          </div>
        </el-dialog>
        <!-- 表格 -->
        <el-table
            ref="multipleTable"
            :data="articleTableData"
            header-align="center"
            style="width: 100%"
            tooltip-effect="dark"
        >

          <el-table-column
              align="center"
              label="文章id"
              prop="id"
          >
          </el-table-column>
          <!--管理员姓名  -->
          <el-table-column
              align="center"
              label="管理员姓名"
              prop="adminId"
              show-overflow-tooltip>
          </el-table-column>
          <!--外部文章链接  -->
          <el-table-column
              align="center"
              label="外部文章链接"
              prop="url"
              show-overflow-tooltip>
          </el-table-column>
          <!--内部文章内容  -->
          <el-table-column
              align="center"
              label="内部文章内容"
              prop="content"
              show-overflow-tooltip>
          </el-table-column>
          <el-table-column
              align="center"
              label="标题"
              prop="title"
              show-overflow-tooltip>
          </el-table-column>
          <el-table-column
              align="center"
              label="添加时间"
              prop="addtime"
              :formatter="timeFormatter"
              show-overflow-tooltip>
          </el-table-column>
          <!-- 编辑 -->
          <el-table-column align="center" label="操作" width="250">
            <template slot-scope="scope">
              <el-button
                  size="mini"
                  type="success"
                  @click="handleEdit(scope.$index, scope.row)">
                <span class="el-icon-edit"></span>
                编辑
              </el-button>
              <el-button
                  size="mini"
                  type="danger"
                  @click="handleDelete(scope.$index, scope.row)">
                <span class="el-icon-delete"></span>
                删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
        <!-- 分页 -->
        <el-pagination
            :current-page="currentPage"
            :page-size="size"
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
      dialogFormVisible: false,
      articleTableData:
          [
            {
              "id": 7,
              "adminId": "陆强",
              "url": "http://hezjgezszi.ws/pbdcrois",
              "content": "龙间清做广收单出然至走家济题何。其织接八知支件包回利团代提品发议。细派细构打反术高美以理选亲认革理易。术易太些者记制三我花济问被直形价。根比调育总值用立效存次老党。证然统公求就不石电门界之使属才议。",
              "title": "用问市",
              "addtime": "1651191413"
            },
            {
              "id": 8,
              "adminId": "周勇",
              "url": "http://clg.info/pulux",
              "content": "二山本教任格看低四次七林适。动回题四三听志石太解增而查老作等养何。是需结团计他通领利委心已花严量化。却大严名没制然受争也期能特很表单万。市更八机素需地即总毛山院究。",
              "title": "看接快求然",
              "addtime": "1651191413"
            },
            {
              "id": 9,
              "adminId": "廖娟",
              "url": "http://nyldiz.ev/kkeyjapm",
              "content": "又做指天地无连算八命道查压。知党整划此算他次合石先直照常。己西公到下验面使名住支点务。行过段与造张根青器持解可节通史中。往目主说专每资信做东快人值律联育水此。",
              "title": "就物相没",
              "addtime": "1651191413"
            }
          ],

      ary: [],
      size: 5,
      total: Number,
      multipleSelection: [],
      // 默认页
      currentPage: 1,
      keyword: "",
      // 回填数据
      articleFormData: {
        id: Number,
        adminId: "",
        url: "",
        content: "",
        title: "",
        addtime: ""
      },
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
  // 生命周期钩子函数 vue实例对象创建完成 dom还没有生成
  created() {
    this.getArticleList()
  },
  methods: {
    add0(m){return m<10?'0'+m:m },
    timeFormatter(row, index) {
      var time = new Date(Number.parseInt(row.addtime));
      var y = time.getFullYear();
      var m = time.getMonth()+1;
      var d = time.getDate();
      var h = time.getHours();
      var mm = time.getMinutes();
      var s = time.getSeconds();
      return y+'-'+this.add0(m)+'-'+this.add0(d)+' '+this.add0(h)+':'+this.add0(mm)+':'+this.add0(s);
    },

    getArticleList() {
      //向后端发送请求查询所有班级数据
      let params = {
        "page": this.currentPage,
        "limit": this.size
      }
      this.request.get('/admin/article/select', params)
          .then(res => {
            // 接收传过来的参数
            if(res.flag){
              this.articleTableData = res.data;
              this.ary = res.data
              this.total = res.count
              this.$message.success("数据获取成功")
            }else {
              this.$message.error("数据获取失败请联系管理员")
            }

          })
          .catch(err => {
            this.$message.error("请求失败请联系管理员")
          })
    },

    // 保存数据
    saveEdit() {
      // 判断：表单通过验证后发送请求到后台
      this.$refs.classFormData.validate(valid => {
        if (valid) {
          const user = JSON.parse(localStorage.getItem("userdata"))
          let params = {
            id: this.articleFormData.id,
            adminId: user.id,
            url: this.articleFormData.url,
            content: this.articleFormData.content,
            title: this.articleFormData.title,
            addtime: this.articleFormData.addtime
          };
          // 发送请求
          this.request.post('/class/updateAndInsert', params)
              .then(res => {
                if (res.flag) {
                  this.$message.success("保存成功")
                  this.dialogFormVisible = false;
                  this.getArticleList();
                } else {
                  this.$message.error("保存失败请联系管理员")
                }
              })
              .catch(err => {
                this.$message.error("请求失败请联系管理员")
              })
        } else {
          this.$message.error("请仔细检查您的数据")
          return false;

        }
      })
    },
    handleEdit(index, row) {
      // 出现模态框
      this.dialogFormVisible = true;
      this.articleFormData.id = row.id;
      this.articleFormData.adminId = row.adminId;
      this.articleFormData.url = row.url;
      this.articleFormData.content = row.content;
      this.articleFormData.title = row.title;
      this.articleFormData.addtime=row.addtime;
    },
    handleDelete(index, row) {
      let params = {
        "id": row.id
      }
      //向后端发送请求
      this.request.get('/admin/article/delete', params)
          .then(res => {
            if (res.flag) {
              this.$message.success("删除成功")
              this.getArticleList()
            } else {
              this.$message.error("删除失败请联系管理员")
            }
          })
          .catch(err => {
            this.$message.error("请求失败请联系管理员")
          })
    },

    handleCurrentChange(val) {
      this.currentPage = val
      this.getClassList()
    }
  },
}
</script>

<style lang="less">
@import "ArticleManage.less";
</style>
