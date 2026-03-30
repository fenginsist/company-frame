import request from '@/utils/request'


export function fetchRoleList(params) {
  return request({
    url: '/api/roles',
    method: 'post',
    data: params
  })
}

export function getRoleListByCurrentUserId(params) {
  return request({
    url: '/api/user/roles/' + params,
    method: 'get',
  })
}

export function saveUserOwnRole(params) {
  return request({
    url: '/api/user/roles/',
    method: 'put',
    data:params
  })
}

export function addRole(params) {
  return request({
    url: '/api/addRole',
    method: 'post',
    data: params
  })
}

export function updateRole(params) {
  return request({
    url: '/api/updateRole',
    method: 'put',
    data: params
  })
}

export function deleteRole(params) {
  return request({
    url: '/api/deleteRole/' + params,
    method: 'delete',
  })
}








export function createRole(data) {
  return request({
    url: '/role/create',
    method: 'post',
    data: data
  })
}




export function updateStatus(id, params) {
  return request({
    url: '/role/updateStatus/' + id,
    method: 'post',
    params: params
  })
}


export function listMenuByRole(roleId) {
  return request({
    url: '/role/listMenu/'+roleId,
    method: 'get'
  })
}

export function listResourceByRole(roleId) {
  return request({
    url: '/role/listResource/'+roleId,
    method: 'get'
  })
}

export function allocMenu(data) {
  return request({
    url: '/role/allocMenu',
    method: 'post',
    data:data
  })
}

export function allocResource(data) {
  return request({
    url: '/role/allocResource',
    method: 'post',
    data:data
  })
}
