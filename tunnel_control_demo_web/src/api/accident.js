import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listAccident(query) {
  return request({
    url: '/event/accident/list',
    method: 'get',
    params: query
  })
}

// 查询【请填写功能名称】详细
export function getAccident(accidentId) {
  return request({
    url: '/event/accident/' + accidentId,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addAccident(data) {
  return request({
    url: '/event/accident',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
export function updateAccident(data) {
  return request({
    url: '/event/accident',
    method: 'put',
    data: data
  })
}

// 删除【请填写功能名称】
export function delAccident(accidentId) {
  return request({
    url: '/event/accident/' + accidentId,
    method: 'delete'
  })
}

// 导出【请填写功能名称】
export function exportAccident(query) {
  return request({
    url: '/event/accident/export',
    method: 'get',
    params: query
  })
}