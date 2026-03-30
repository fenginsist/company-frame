import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '../views/layout/Layout'

/**
 * hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
 *                                if not set alwaysShow, only more than one route under the children
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noredirect           if `redirect:noredirect` will no redirct in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
  }
 **/
export const constantRouterMap = [
  {path: '/login', component: () => import('@/views/login/index'), hidden: true},
  {path: '/404', component: () => import('@/views/404'), hidden: true},
  {path: '/first', component: () => import('@/views/main/first'), hidden: true},  // 官网首页
  {path: '/my', component: () => import('@/views/main/my'), hidden: true},        // 登录后的页面
  {
    path: '',
    component: Layout,
    name: 'Main',
    redirect: '/home',
    meta: {title: '首页', icon: 'home', alwaysShow: true},
    children: [{
        path: 'home',
        name: 'home',
        component: () => import('@/views/home/index'),
        meta: {title: '首页', icon: 'dashboard'}
      },
    ]
  }
]

export const asyncRouterMap = [
  {
    path: '/user',
    component: Layout,
    redirect: '/user/list',
    name: 'user',
    meta: {title: '用户管理', icon: 'product'},
    children: [
      {
        path: 'user',
        name: 'user',
        component: () => import('@/views/user/index'),
        meta: {title: '用户列表', icon: 'product-list'}
      },
      {
        path: 'addUser',
        name: 'addUser',
        component: () => import('@/views/user/add/index'),
        meta: {title: '添加用户', icon: 'product-list'}
      },
    ]
  },
  {
    path: '/dept',
    component: Layout,
    redirect: '/dept/list',
    name: 'dept',
    meta: {title: '球类管理', icon: 'product'},
    children: [
      {
        path: 'dept',
        name: 'dept',
        component: () => import('@/views/dept/index'),
        meta: {title: '球类列表', icon: 'product-list'}
      },
      {
        path: 'addDept',
        name: 'addDept',
        component: () => import('@/views/dept/add/index'),
        meta: {title: '添加项目', icon: 'product-list'}
      },
    ]
  },
  {
    path: '/test',
    component: Layout,
    redirect: '/test/list',
    name: 'test',
    meta: {title: '赛事管理', icon: 'product'},
    children: [
      {
        path: 'test',
        name: 'test',
        component: () => import('@/views/test/index'),
        meta: {title: '赛事列表(静态版)', icon: 'product-list'}
      },
      // {
      //   path: 'addTest',
      //   name: 'addTest',
      //   component: () => import('@/views/test/add/index'),
      //   meta: {title: '添加项目', icon: 'product-list'}
      // },
    ]
  },
  {
    path:'/ums',
    component: Layout,
    redirect: '/ums/admin',
    name: 'ums',
    meta: {title: '权限管理', icon: 'ums'},
    children: [
      {
        path: 'admin',
        name: 'admin',
        component: () => import('@/views/ums/admin/index'),
        meta: {title: '用户列表', icon: 'ums-admin'}
      },
      {
        path: 'role',
        name: 'role',
        component: () => import('@/views/ums/role/index'),
        meta: {title: '角色列表', icon: 'ums-role'}
      },
      {
        path: 'allocMenu',
        name: 'allocMenu',
        component: () => import('@/views/ums/role/allocMenu'),
        meta: {title: '分配菜单'},
        hidden: true
      },
      {
        path: 'allocResource',
        name: 'allocResource',
        component: () => import('@/views/ums/role/allocResource'),
        meta: {title: '分配资源'},
        hidden: true
      },
      {
        path: 'menu',
        name: 'menu',
        component: () => import('@/views/ums/menu/index'),
        meta: {title: '菜单列表', icon: 'ums-menu'}
      },
      {
        path: 'addMenu',
        name: 'addMenu',
        component: () => import('@/views/ums/menu/add'),
        meta: {title: '添加菜单'},
        hidden: true
      },
      {
        path: 'updateMenu',
        name: 'updateMenu',
        component: () => import('@/views/ums/menu/update'),
        meta: {title: '修改菜单'},
        hidden: true
      },
      // {
      //   path: 'resource',
      //   name: 'resource',
      //   component: () => import('@/views/ums/resource/index'),
      //   meta: {title: '资源列表', icon: 'ums-resource'}
      // },
      // {
      //   path: 'resourceCategory',
      //   name: 'resourceCategory',
      //   component: () => import('@/views/ums/resource/categoryList'),
      //   meta: {title: '资源分类'},
      //   hidden: true
      // }
    ]
  },
  {
    path: '/sys',
    component: Layout,
    redirect: '/sys/list',
    name: 'sys',
    meta: {title: '系统管理', icon: 'product'},
    children: [
      {
        path: 'log',
        name: 'log',
        component: () => import('@/views/sys/log/index'),
        meta: {title: '日志管理', icon: 'product-list'}
      },
      {
        path: 'interface',
        name: 'interface',
        component: () => import('@/views/sys/interface/index'),
        meta: {title: '接口管理', icon: 'product-list'}
      },
      {
        path: 'sqlMonitor',
        name: 'sqlMonitor',
        component: () => import('@/views/sys/sql/index'),
        meta: {title: 'SQL监控', icon: 'product-list'}
      },
    ]
  },
  {path: '*', redirect: '/404', hidden: true}
]

export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({y: 0}),
  routes: constantRouterMap
})

