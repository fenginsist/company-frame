import request from '@/utils/request'

export function login(username, password) {
  return request({
    url: '/api/user/login',
    method: 'post',
    data: {
      username,
      password
    }
  })
}

export function logout() {
  return request({
    url: '/api/user/logout',
    method: 'get'
  })
}

export function getUserInfo() {
  return request({
    url: '/api/user/getUserInfo',
    method: 'get',
  })
}

// export function login(username, password) {
//   return request({
//     url: '/admin/login',
//     method: 'post',
//     data: {
//       username,
//       password
//     }
//   })
// }

// export function getInfo() {
//   return request({
//     url: '/admin/info',
//     method: 'get',
//   })
// }

// export function logout() {
//   return request({
//     url: '/admin/logout',
//     method: 'post'
//   })
// }



export function fetchList(params) {
  return request({
    url: '/admin/list',
    method: 'get',
    params: params
  })
}


export function updateStatus(id, params) {
  return request({
    url: '/admin/updateStatus/' + id,
    method: 'post',
    params: params
  })
}


export function getRoleByAdmin(id) {
  return request({
    url: '/admin/role/' + id,
    method: 'get'
  })
}

export function allocRole(data) {
  return request({
    url: '/admin/role/update',
    method: 'post',
    data: data
  })
}
