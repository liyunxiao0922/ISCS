import request from '@/utils/request'

// 查询基础数据设备列表
export function listStatusIcon(query) {
  return request({
    url: '/baseStatusIcon/list',
    method: 'get',
    params: query
  })
}

// 上传图片
export function upImg(query) {
  return request({
    url: '/common/upload',
    method: 'post',
    data: query
  })
}

// 查询基础数据设备详细
export function getStatusIcon(statusIconId) {
  return request({
    url: '/baseStatusIcon/getInfo?statusIconId=' + statusIconId,
    method: 'get'
  })
}

// 新增基础数据设备
export function addStatusIcon(data) {
  return request({
    url: '/baseStatusIcon/add',
    method: 'post',
    data: data
  })
}

// 修改基础数据设备
export function updateStatusIcon(data) {
  return request({
    url: '/baseStatusIcon/edit',
    method: 'put',
    data: data
  })
}

// 删除基础数据设备
export function delStatusIcon(statusIconId) {
  return request({
    url: '/baseStatusIcon/remove?statusIconId=' + statusIconId,
    method: 'delete'
  })
}

// 导出基础数据设备
export function exportStatusIcon(query) {
  return request({
    url: '/baseStatusIcon/export',
    method: 'get',
    params: query
  })
}