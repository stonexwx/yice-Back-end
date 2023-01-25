<template>
  <div class="asideNav">
    <!-- logo -->
    <div class="logo">
      <span style="font-size: 20px;color: white;font-weight: bold">宜厕后台管理系统</span>
    </div>
    <!-- 导航目录 -->
    <el-menu
        :default-active="$route.path"
        class="el-menu-vertical-demo"
        @open="handleOpen"
        @close="handleClose"
        background-color="#32446e"
        text-color="#fff"
        active-text-color="#ffd04b"
        router
        unique-opened
    >
      <!-- 工作台 -->
      <el-menu-item index="/home/workspace"><span class="el-icon-menu"></span>欢迎页面</el-menu-item>
      <!-- 子菜单模板 -->
      <el-submenu :index="(index+1)+''" v-for="(menu,index) in menus" :key="index">
        <template slot="title">
          <i :class="menu.icoClass"></i>
          <span>{{ menu.title }}</span>
        </template>
        <el-menu-item :index="submenu.path" v-for="(submenu,i) in menu.children" :key="i">{{ submenu.subtitle }}
        </el-menu-item>
      </el-submenu>
    </el-menu>

  </div>
</template>
<script>
export default {
  data() {
    return {
      menus: [],
      leftImg: require("@/assets/img/rabbit.gif"),
      TeacherMenus: [
        //用户管理(用户管理）
        // 用户管理管理
        {
          icoClass: "el-icon-bell",
          title: "用户管理",
          children: [
            {path: "/home/usermanage", subtitle: "用户管理"},
          ]
        },
        // 文章管理
        {
          icoClass: "el-icon-view",
          title: "文章管理",
          children: [
            {path: "/home/articlemanage", subtitle: "文章管理"},
            {path: "/home/articleadd", subtitle: "新增文章"}
          ]
        },
        // 广告管理
        {
          icoClass: "el-icon-tickets",
          title: "广告管理",
          children: [
            {path: "/home/advertisingmanage", subtitle: "广告管理"},
            {path: "/home/advertisingadd", subtitle: "添加广告"}
          ]
        },
      ],
    }
  },
  created() {
    const userdata = localStorage.getItem("user");
    console.log(userdata)
    //与后端联动时请注释下面的userdata 放出上面一行
    // var userdata = {
    //   utype: "1"
    // }
    //若角色为学生
    this.menus = this.TeacherMenus
  },
  methods: {
    handleOpen(key, keyPath) {
      // console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      // console.log(key, keyPath);
    }
  }
}
</script>

<style lang="less">
@import "./asideNav.less";
</style>
