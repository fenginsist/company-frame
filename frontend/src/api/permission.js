import request from '@/utils/request'


export function getAllPermissionTreeList(params) {
  return request({
    url: '/api/permission/tree/all',
    method: 'get',
  })
}

/**
 * 权限管理页面，点击新增后，点击所属菜单后显示的：树结构，只展示到菜单页面即可。
 * @param {*} params 
 * @returns 
 */
export function getAllPermissionTreeExchangeBtn(params) {
  return request({
    url: '/api/permission/tree',
    method: 'get',
  })
}

export function addPermission(params) {
  return request({
    url: '/api/addPermission',
    method: 'post',
    data: params
  })
}

export function updatePermission(params) {
  return request({
    url: '/api/updatePermission',
    method: 'put',
    data: params
  })
}

export function deletePermission(params) {
  return request({
    url: '/api/deletePermission/' + params,
    method: 'delete'
  })
}

/**
 * role permission
 */

export function getPermissionsByRoleId(params) {
  return request({
    url: '/role/permission/getPermissionsByRoleId',
    method: 'get',
    param: params
  })
}

export function saveRolePermissions(params) {
  return request({
    url: '/role/role/permission/save',
    method: 'post',
    data: params
  })
}