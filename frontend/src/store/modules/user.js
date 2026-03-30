import { login, logout, getUserInfo } from '@/api/login'
import { getToken, getRefreshToken, setToken, setRefreshToken, removeToken, removeRefreshToken } from '@/utils/auth'

const user = {
  state: {
    token: getToken(),
    refreshToken: getRefreshToken(),
    name: '',
    avatar: '',
    roles: []
  },

  mutations: {
    SET_TOKEN: (state, token) => {
      state.token = token
    },
    SET_REFRESH_TOKEN: (state, token) => {
      state.token = token
    },
    SET_NAME: (state, name) => {
      state.name = name
    },
    SET_AVATAR: (state, avatar) => {
      state.avatar = avatar
    },
    SET_ROLES: (state, roles) => {
      state.roles = roles
    }
  },

  actions: {
    // 登录
    Login({ commit }, userInfo) {
      console.log('login userInfo: ', userInfo)
      const username = userInfo.username.trim()
      return new Promise((resolve, reject) => {
        login(username, userInfo.password).then(response => {
          console.log('login response: ', response)
          const data = response.data
          const tokenStr = data.accessToken
          const refreshTokenStr = data.refreshToken
          setToken(tokenStr)
          setRefreshToken(refreshTokenStr)
          commit('SET_TOKEN', tokenStr)
          commit('SET_REFRESH_TOKEN', tokenStr)
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 获取用户信息
    GetInfo({ commit, state }) {
      console.log('src/store/modules/user.js 发请求: GetInfo' )
      return new Promise((resolve, reject) => {
        commit('SET_ROLES', '超级管理员')
        commit('SET_NAME', '超级管理员')
        commit('SET_AVATAR', 'https://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5ad83a4fN6ff67ecd.jpg!cc_350x449.jpg')
        resolve()
        // getUserInfo().then(response => {
        //   const data = response.data
        //   console.log('GetInfo: ' + data)
        //   if (data && data.length > 0) { // 验证返回的roles是否是一个非空数组
        //     commit('SET_ROLES', data)
        //   } else {
        //     reject('getInfo: roles must be a non-null array !')
        //   }
        //   commit('SET_NAME', '')
        //   commit('SET_AVATAR', '')
        //   resolve(response)
        // }).catch(error => {
        //   console.error('Error in getUserInfo:', error);
        //   reject(error)
        // })
      })
    },

    // 登出
    LogOut({ commit, state }) {
      return new Promise((resolve, reject) => {
        logout().then(() => { // state.token
          commit('SET_TOKEN', '')
          commit('SET_REFRESH_TOKEN', '')
          commit('SET_ROLES', [])
          removeToken()
          removeRefreshToken()
          resolve()
        }).catch(error => {
          console.log('退出问题: ' +error)
          reject(error)
        })
      })
    },

    // 前端 登出
    FedLogOut({ commit }) {
      return new Promise(resolve => {
        commit('SET_TOKEN', '')
        commit('SET_REFRESH_TOKEN', '')
        removeToken()
        removeRefreshToken()
        resolve()
      })
    }
  }
}

export default user
