import router from './router'
import store from './store'
import NProgress from 'nprogress' // Progress 进度条
import 'nprogress/nprogress.css'// Progress 进度条样式
import { Message } from 'element-ui'
import { getToken } from '@/utils/auth' // 验权
/**
 * 路由的过滤器
 */
// 临时代码
import { getRefreshToken, setToken, setRefreshToken, removeToken, removeRefreshToken } from '@/utils/auth'
// removeToken()
// removeRefreshToken()
// console.log('token 从 cookie 中删除')

const whiteList = ['/login', '/first', '/my'] // 不重定向白名单
router.beforeEach((to, from, next) => {
  NProgress.start()
  if (getToken()) {
    if (to.path === '/login') {
      next({ path: '/' })
      NProgress.done() // if current page is dashboard will not trigger	afterEach hook, so manually handle it
    } else {
      if (store.getters.roles.length === 0) {
        console.log('permission.js  执行: GetInfo' )
        store.dispatch('GetInfo').then(res => { // 拉取用户信息
          // let menus=res.data.menus;
          // let username=res.data.username;
          console.log('GetInfo 请求完')
          store.dispatch('GenerateRoutes', { "menus": "menus","username": 'admin' }).then(() => { // 生成可访问的路由表
            router.addRoutes(store.getters.addRouters); // 动态添加可访问路由表
            next({ ...to, replace: true })
          })
        }).catch((err) => {
          store.dispatch('FedLogOut').then(() => {
            Message.error(err || 'Verification failed, please login again')
            next({ path: '/' })
          }) 
        })
        next({ ...to, replace: true })
      } else {
        console.log('router_permission to: ' + to)
        console.log('router_permission to path: ' + to.path)
        next()
      }
      // console.log('router_permission to: ' + to)
      // console.log('router_permission to path: ' + to.path)
      // next()
    }
  } else {
    if (whiteList.indexOf(to.path) !== -1) {
      next()
    } else {
      next('/login')
      NProgress.done()
    }
  }
})

router.afterEach(() => {
  NProgress.done() // 结束Progress
})
