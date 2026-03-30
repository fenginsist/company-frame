import axios from 'axios'
import { Message, MessageBox } from 'element-ui'
import store from '../store'
import { getToken, removeToken, getRefreshToken, removeRefreshToken } from '@/utils/auth'

// 创建axios实例
const service = axios.create({
  baseURL: process.env.BASE_API, // api的base_url
  timeout: 60000 // 请求超时时间
})

// request拦截器
service.interceptors.request.use(config => {
  if (store.getters.token) {
    // console.log('axios 请求携带 token :' + getToken())
    // console.log('axios 请求携带 刷新 token :' + getRefreshToken())
    config.headers['authorization'] = getToken() // 让每个请求携带自定义token 请根据实际情况自行修改
    config.headers['refresh_token'] = getRefreshToken() 
  }
  return config
}, error => {
  // Do something with request error
  console.log(error) // for debug
  Promise.reject(error)
})

// respone拦截器
service.interceptors.response.use(
  response => {
  /**
  * code为非200是抛错 可结合自己业务进行修改
  */
    const res = response.data
    if (res.code === 4010002) {
      Message({
        message: res.msg,
        type: 'error',
        duration: 3 * 1000
      })
      removeToken()
      removeRefreshToken()
      console.log('token 从 cookie 中删除')
      // 自动刷新网页
      setTimeout(() => {
        window.location.reload()
      }, 1000) // 延迟 1 秒更平滑
      return;
    }
    if (res.code !== 0) { // 默认是200
      console.log("request axios interceptors response error: ", response)
      Message({
        message: res.msg,
        type: 'error',
        duration: 3 * 1000
      })

      // 401:未登录;
      if (res.code === 401) {
        // MessageBox.confirm('你已被登出，可以取消继续留在该页面，或者重新登录', '确定登出', {
        //   confirmButtonText: '重新登录',
        //   cancelButtonText: '取消',
        //   type: 'warning'
        // }).then(() => {
        //   store.dispatch('FedLogOut').then(() => {
        //     location.reload()// 为了重新实例化vue-router对象 避免bug
        //   })
        // })
      }
      return Promise.reject('error')
    } else {
      // console.log("request axios interceptors response success: ", response)
      return response.data
    }
  },
  error => {
    console.log('err' + error)// for debug
    Message({
      message: error.message,
      type: 'error',
      duration: 3 * 1000
    })
    return Promise.reject(error)
  }
)

export default service
