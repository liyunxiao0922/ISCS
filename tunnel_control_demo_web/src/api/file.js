import request from '@/utils/request'

// 查询事件处理节点文件列表
export function listFile(query) {
  return request({
    url: '/event/file/list',
    method: 'get',
    params: query
  })
}

// 查询事件处理节点文件详细
export function getFile(id) {
  return request({
    url: '/event/file/' + id,
    method: 'get'
  })
}

// 新增事件处理节点文件
export function addFile(data) {
  return request({
    url: '/event/file',
    method: 'post',
    data: data
  })
}

// 修改事件处理节点文件
export function updateFile(data) {
  return request({
    url: '/event/file',
    method: 'put',
    data: data
  })
}

// 删除事件处理节点文件
export function delFile(id) {
  return request({
    url: '/event/file/' + id,
    method: 'delete'
  })
}

// 导出事件处理节点文件
export function exportFile(query) {
  return request({
    url: '/event/file/export',
    method: 'get',
    params: query
  })
}