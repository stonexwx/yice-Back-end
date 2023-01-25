import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)


const router = new Router({
  // mode: 'history',
  // linkActiveClass: 'active',
  routes: [
    {
      path:"/",
      redirect:"/login"
    },
    // 登录界面
    {
      path:"/login",
      name:"login",
      component:()=>import("./views/Login/Login.vue"),
    },
    // 后台主界面
    {
      path:"/home",
      name:"home",
      component:()=>import("./views/Home/home.vue"),
      redirect:"/home/systeminfo",
      children:[
        // 工作台
        {
          path:"/home/workspace",
          name:"workspace",
          component:()=>import("./views/workspace/workspace.vue")
        },
        // 文章管理
        {
          path:"/home/articlemanage",
          name:"articlemanage",
          component:()=>import("./views/ArticleManage/ArticleManage.vue")
        },
        {
          path:"/home/articleadd",
          name:"articleadd",
          component:()=>import("./views/ArticleAdd/ArticleAdd.vue")
        },
        // 广告管理
        {
          path:"/home/advertisingmanage",name:"advertisingmanage",
          component:()=>import("./views/AdvertisingManage/AdvertisingManage.vue")
        },
        {
          path:"/home/advertisingadd",name:"advertisingadd",
          component:()=>import("./views/AdvertisingAdd/AdvertisingAdd.vue")
        },
        // 用户管理
        {
          path:"/home/usermanage",name:"usermanage",
          component:()=>import("./views/UserManage/UserManage.vue")
        },
      ]
    }
  ]
})
// router.beforeEach((to, from, next) => {
//   //全局全局前置守卫
//   const token = localStorage.getItem("userdata");
//   if(to.path !== '/login'){
//     if (token){
//       return next();
//     }else{
//       return next('/login')
//     }
//   }else{
//     if(token){
//       return next('/home')
//     }else{
//       return next();
//     }
//   }
  // if (to.path === '/'||to.path === '/login') {
  //   next();
  // } else {
  //   next({path: '/home/workspace'});
  // }
// })

export default router
