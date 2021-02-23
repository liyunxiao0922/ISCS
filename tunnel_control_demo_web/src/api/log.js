import request from '@/utils/request'

// 查询事件处置节点列表
export function listLog(query) {
  return request({
    url: '/event/log/list',
    method: 'get',
    params: query
  })
}

// 查询事件处置节点详细
export function getLog(id) {
  return request({
    url: '/event/log/' + id,
    method: 'get'
  })
}

// 新增事件处置节点
export function addLog(data) {
  return request({
    url: '/event/log',
    method: 'post',
    data: data
  })
}

// 修改事件处置节点
export function updateLog(data) {
  return request({
    url: '/event/log',
    method: 'put',
    data: data
  })
}

// 删除事件处置节点
export function delLog(id) {
  return request({
    url: '/event/log/' + id,
    method: 'delete'
  })
}

// 导出事件处置节点
export function exportLog(query) {
  return request({
    url: '/event/log/export',
    method: 'get',
    params: query
  })
}