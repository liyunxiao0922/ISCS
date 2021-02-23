import request from '@/utils/request'

// 查询基础数据设备列表
export function listTunnelDevice(query) {
  return request({
    url: '/baseTunnelDevice/list',
    method: 'get',
    params: query
  })
}

// 查询基础数据设备详细
export function getTunnelDevice(deviceId) {
  return request({
    url: '/baseTunnelDevice/getInfo?deviceId=' + deviceId,
    method: 'get'
  })
}

// 新增基础数据设备
export function addTunnelDevice(data) {
  return request({
    url: '/baseTunnelDevice/add',
    method: 'post',
    data: data
  })
}

// 修改基础数据设备
export function updateTunnelDevice(data) {
  return request({
    url: '/baseTunnelDevice/edit',
    method: 'put',
    data: data
  })
}

// 删除基础数据设备
export function delTunnelDevice(deviceId) {
  return request({
    url: '/baseTunnelDevice/remove?deviceId=' + deviceId,
    method: 'delete'
  })
}

// 导出基础数据设备
export function exportTunnelDevice(query) {
  return request({
    url: '/baseTunnelDevice/export',
    method: 'get',
    params: query
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