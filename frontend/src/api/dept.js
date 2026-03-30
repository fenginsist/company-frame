import request from '@/utils/request'

export function fetchDeptList(params) {
  return request({
    url:'/api/depts',
    method:'get',
    data:params
  })
}

export function updateDept(params) {
  return request({
    url:'/api/updateDept',
    method:'put',
    data:params
  })
}

export function deleteDepts(params) {
  return request({
    url:'/api/deleteDept/' + params,
    method:'delete',
  })
}