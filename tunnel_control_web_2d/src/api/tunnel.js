import request from '@/utils/request'

export function getTunnelList(params) {
  return request({
    url: '/base/tunnel/list',
    method: 'get',
    params
  })
}

export function getTunnelInfo(params) {
  return request({
    url: '/base/tunnel/getInfo',
    method: 'get',
    params
  })
}

export function getDevTypeList(params) {
  return request({
    url: '/baseDeviceType/queryWhole',
    method: 'get',
    params
  })
}

/**
 * 通过设备类型id数组来查询设备数据
 * @param params deviceTypeIds   设备类型id数组
 */
export function getDevInfoList(params) {
  return request({
    url: '/baseDeviceType/queryTypeAndSubByDeviceIds',
    method: 'post',
    data: params
  })
}

/**
 * 通过设备类型id查询设备状态列表
 * @param params deviceTypeId 设备类型id
 */
export function getDevStatusList(params) {
  return request({
    url: '/baseStatusIcon/list',
    method: 'get',
    params
  })
}

/**
 * 修改设备类型的状态
 * @param params deviceTypeId  设备类型id
 * @param params deviceGroup  分组id  
 * @param params workStatus  状态
 */
export function editDevTypeStatus(params) {
  return request({
    url: '/baseTunnelDevice/editByDeviceType',
    method: 'put',
    data: params
  })
}

/**
 * 修改设备状态
 * @param params deviceId  设备id
 * @param params workStatus  设备状态
 */
export function editDevStatus(params) {
  return request({
    url: '/baseTunnelDevice/edit',
    method: 'put',
    data: params
  })
}
