import request from '@/utils/request'

// 查询基础数据设备列表
export function listDeviceType(query) {
  return request({
    url: '/baseDeviceType/list',
    method: 'get',
    params: query
  })
}

// 查询基础数据设备详细
export function getDeviceType(deviceTypeId) {
  return request({
    url: '/baseDeviceType/getInfo?deviceTypeId=' + deviceTypeId,
    method: 'get'
  })
}

// 新增基础数据设备
export function addDeviceType(data) {
  return request({
    url: '/baseDeviceType/add',
    method: 'post',
    data: data
  })
}

// 修改基础数据设备
export function updateDeviceType(data) {
  return request({
    url: '/baseDeviceType/edit',
    method: 'put',
    data: data
  })
}

// 删除基础数据设备
export function delDeviceType(deviceTypeId) {
  return request({
    url: '/baseDeviceType/delete?deviceTypeId=' + deviceTypeId,
    method: 'delete'
  })
}

// 导出基础数据设备
export function exportDeviceType(query) {
  return request({
    url: '/baseDeviceType/export',
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

// 查询字典数据列表
export function listData(query) {
  return request({
    url: '/system/dict/data/list',
    method: 'get',
    params: query
  })
}