import request from '@/utils/request'

// 查询基础数据设备列表
export function listDevice(query) {
  return request({
    url: '/base/device/list',
    method: 'get',
    params: query
  })
}

// 查询基础数据设备详细
export function getDevice(deviceId) {
  return request({
    url: '/base/device/' + deviceId,
    method: 'get'
  })
}

// 新增基础数据设备
export function addDevice(data) {
  return request({
    url: '/base/device/add',
    method: 'post',
    data: data
  })
}

// 修改基础数据设备
export function updateDevice(data) {
  return request({
    url: '/base/device/edit',
    method: 'put',
    data: data
  })
}

// 删除基础数据设备
export function delDevice(deviceId) {
  return request({
    url: '/base/device/' + deviceId,
    method: 'delete'
  })
}

// 导出基础数据设备
export function exportDevice(query) {
  return request({
    url: '/base/device/export',
    method: 'get',
    params: query
  })
}

