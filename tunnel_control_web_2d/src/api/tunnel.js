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
