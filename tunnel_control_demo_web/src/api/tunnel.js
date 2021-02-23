import request from '@/utils/request'

// 查询基础数据设备列表
export function listTunnel(query) {
  return request({
    url: '/base/tunnel/list',
    method: 'get',
    params: query
  })
}

// 查询基础数据设备详细
export function getTunnel(tunnelId) {
  return request({
    url: '/base/tunnel/getInfo?tunnelId=' + tunnelId,
    method: 'get'
  })
}

// 新增基础数据设备
export function addTunnel(data) {
  return request({
    url: '/base/tunnel/add',
    method: 'post',
    data: data
  })
}

// 修改基础数据设备
export function updateTunnel(data) {
  return request({
    url: '/base/tunnel/edit',
    method: 'put',
    data: data
  })
}

// 删除基础数据设备
export function delTunnel(tunnelId) {
  return request({
    url: '/base/tunnel/delete?tunnelId=' + tunnelId,
    method: 'delete'
  })
}

// 导出基础数据设备
export function exportTunnel(query) {
  return request({
    url: '/base/tunnel/export',
    method: 'get',
    params: query
  })
}