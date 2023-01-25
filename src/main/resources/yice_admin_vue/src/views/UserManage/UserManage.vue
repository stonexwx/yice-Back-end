<template>
  <div class="StudentManage">
    <el-card class="box-card">
      <div slot="header" class="clearfix title">
        <span class="iconfont icon-guanli"></span>
        <span>用户管理</span>
      </div>
      <div class="text item">
        <!-- 筛选器 -->
        <div class="filterBox">
          <!-- 关键字 -->
          微信id：
          <el-input v-model="keyword" auto-complete="off" size="small" type="text"></el-input>
          <el-button size="small" type="primary" @click="selectId">查询</el-button>
        </div>
        <!-- 表格 -->
        <el-table
            ref="multipleTable"
            :data="userTableData"
            header-align="center"
            style="width: 100%"
            tooltip-effect="dark"
            @selection-change="handleSelectionChange"
        >
          <el-table-column

              align="center"
              width="55">
          </el-table-column>
          <!-- id -->
          <el-table-column
              align="center"
              label="id"
              prop="id"
          >
          </el-table-column>
          <!-- 姓名 -->
          <el-table-column
              align="center"
              label="微信id"
              prop="wxid"
          >
          </el-table-column>
          <!-- 密码 -->
          <el-table-column
              align="center"
              label="用户名"
              prop="name"

          >
          </el-table-column>
          <!-- 性别 -->
          <el-table-column
              :formatter="timeFormatter"
              align="center"
              label="添加时间"
              prop="addtime"
          >
          </el-table-column>
          <!-- 编辑 -->
          <el-table-column align="center" label="操作" width="250">
            <template slot-scope="scope">
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
      //分页
      total: Number,
      pageSize: 5,
      currentPage: 1,
      userTableData: [
        {
          "id": 5,
          "name": "魏娟",
          "addtime": "1651132636",
          "wxid": "28891"
        },
        {
          "id": 6,
          "name": "卢娜",
          "addtime": "1651132636",
          "wxid": "9040545573"
        },
        {
          "id": 7,
          "name": "江秀英",
          "addtime": "1651132636",
          "wxid": "27827047"
        },
        {
          "id": 8,
          "name": "彭霞",
          "addtime": "1651132636",
          "wxid": "924361"
        },
        {
          "id": 9,
          "name": "张秀兰",
          "addtime": "1651132636",
          "wxid": "3337124"
        }
      ],
      ary: [],
      keyword: "",
    }
  },
  created() {
    this.getUserList()
  },
  methods: {
    //学生数据
    getUserList() {
      let params = {
        size: this.pageSize,
        current: this.currentPage
      }
      //向后端发送请求查询所有数据
      this.request.get('/admin/user/selectAll', params)
          .then(res => {
            // 接收传过来的参数
            this.userTableData = res.data;
            this.ary = res.data
            this.total = Number.parseInt(res.count);
          })
          .catch(err => {
            console.log(err);
          })
    },

    selectId() {
      //先判断值是否为空
      if (this.keyword == "") {
        this.getUserList()
      } else {

        this.userTableData = this.ary.filter((value) => {
          if (this.keyword == value.wxid) {
            return value
          }
        })
        this.total = this.userTableData.length
      }
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    handleDelete(index, row) {
      let params = {
        "id": row.id
      }
      //向后端发送请求
      this.request.get('/admin/user/delete', params)
          .then(res => {
            if (res.flag) {
              this.$message.success("删除成功")
              this.getUserList()
            } else {
              this.$message.error("删除失败请联系管理员")
            }
          })
          .catch(err => {
            this.$message.error("请求失败请联系管理员")
          })
    },
    // 分页函数
    handleCurrentChange(val) {
      this.currentPage = val
      this.getUserList()
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
    }
  },
}
</script>

<style lang="less">
@import "UserManage.less";
</style>
