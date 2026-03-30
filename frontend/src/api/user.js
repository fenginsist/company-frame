import request from '@/utils/request'
export function fetchUserList(params) {
  return request({
    url:'/api/users',
    method:'post',
    data:params
  })
}

export function addUser(params) {
  return request({
    url:'/api/addUser',
    method:'post',
    data:params
  })
}


export function updateUser(params) {
  return request({
    url:'/api/updateUser',
    method:'put',
    data:params
  })
}

export function deleteUsers(params) {
  return request({
    url:'/api/deletedUser',
    method:'delete',
    data:params
  })
}