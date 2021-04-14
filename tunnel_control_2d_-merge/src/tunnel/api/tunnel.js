import request from '@/tunnel/utils/request'
import store from '@/tunnel/store/index'
export function getTunnelList(params) {
  return request({
    url: '/Api/Equip/tunnelList',
    method: 'get',
    params,
    type:1,
  })
}

export function getTunnelInfo(params) {
  return request({
    url: '/Api/Equip/equipCurrentStateList',
    method: 'get',
    params,
    type:1,
  })
}

export function getDevTypeList(params) {
  return request({
    url: '/Api/Equip/equipType',
    method: 'get',
    params,
    type:1,
  })
}
export function getLightTypeList(params) {
  return request({
    url: `Api/Equip/equipCurrentStateListByDirectionAndIllumination/${params.sysTunnelDirection}/${params.tunnelIllumination }`,
    method: 'get',
    params,
    type:1,
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
    data: params,
    type:1,
  })
}

/**
 * 通过设备类型id查询设备状态列表
 * @param params deviceTypeId 设备类型id
 */
export function getDevStatusList(params) {
  return request({
    url: `Api/Equip/equipTypeOperationList/`,
    method: 'get',
    params,
    type:1,
  })
}

/**
 * 修改某一类型设备类型的状态
 * @param params deviceTypeId  设备类型id
 * @param params deviceGroup  分组id  
 * @param params workStatus  状态
 */
export function editDevTypeStatus(params) {
  return request({
    url: `/Api/Equip/updateCurrentByTypeAndSysTunnelDirection/${params.sysTunnelDirection}/${params.type}/${params.equipTypeOperationId}/${params.tunnelIllumination}`,
    method: 'put',
    type:1,
  })
}

/**
 * 修改设备状态
 * @param params deviceId  设备id
 * @param params workStatus  设备状态
 */
export function editDevStatus(params) {
  return request({
    url: `/Api/Equip/updateCurrentById/${params.deviceId}/${params.workStatus}`,
    method: 'put',
    type:1,
  })
}

// 车辆信息
export function vehicle() {
  return request({
    url: '/Api/Report/vehicle',
    method: 'get',
    type: 1
  })
}
// 信息板详情
export function infoBoardStatistics() {
  return request({
    url: '/Api/Equip/informationBoardlist',
    method: 'get',
    type: 1
  })
}

// 风机 
export function fanInforStatistics() {
  return request({
    url: '/Api/Report/fanInforStatistics',
    method: 'get',
    type: 1
  })
}
// 照明
export function lightInforStatistics() {
  return request({
    url: '/Api/Report/LightInforStatistics',
    method: 'get',
    type: 1
  })
}

// 设备操作信息
export function equipControlInfor() {
  return request({
    url: '/Api/Report/equipControlInfor',
    method: 'get',
    type: 1
  })
}
// 更改灯光模式
export function updateLightModel(data) {
  return request({
    url: `/Api/Report/updateLightModel/${data}`,
    method: 'put',
    type: 1
  })
}
//更改通风模式
export function updateWindModel(data) {
  return request({
    url: `/Api/Report/updateWindModel/${data}`,
    method: 'put',
    type: 1
  })
}

// 根据桩号查找设备
export function equipCurrentStateListByPile() {
  console.log('请求设备', store.state.stepArr, store.state.step);
  let getAfter = (after) => {
    if ((after + "").length === 1) {
      after = "00" + after;
    } else if ((after + "").length === 2) {
      after = "0" + after;
    }
    return after
  }
  let obj = store.state.stepArr[store.state.step];
  return request({
    url: `/Api/Equip/equipCurrentStateListByPile/${`k${36 + Math.floor(obj.lowerPile / 1000)}+${getAfter(obj.lowerPile % 1000)}`}/${ `k${36 + Math.floor(obj.upperPile / 1000)}+${getAfter(obj.upperPile % 1000)}`}`,
    method: 'get',
    type: 1
  })
}
// 获取隧道长度
export function tunnelLength() {
  return request({
    url: '/Api/Equip/tunnelList',
    method: 'get',
    type: 1
  })
}
// 获取灯光总览
export function lightOverview() {
  return request({
    url: '/Api/Equip/lightOverview',
    method: 'get',
    type: 1
  })
}
// 获取视频地址
export function videoInfoById() {
  return request({
    url: '/Api/Equip/VideoInfoById',
    method: 'get',
    type: 1
  })
}
// 获取异常事件列表
export function eventList() {
  return request({
    url: '/Api/Event/eventList',
    method: 'get',
    type: 1
  })
}
// 更改事件
export function updateEvent(eventId, eventState) {
  return request({
    url: `/Api/Event/UpdateEvent/${eventId}/${eventState}`,
    method: 'put',
    type: 1
  })
}
// 更改事件
export function executeEvent(eventId) {
  return request({
    url: `/Api/Event/ExecuteEvent/${eventId}`,
    method: 'put',
    type: 1
  })
}
// 更改自动获取事件异常（0不获取 1获取
export function updateEventAutoState(eventAutoState) {
  return request({
    url: `/Api/Event/UpdateEventAutoState/${eventAutoState}`,
    method: 'put',
    type: 1
  })
}
// 自动获取事件异常 0不获取 1 获取
export function getEventAutoState() {
  return request({
    url: '/Api/Event/getEventAutoState',
    method: 'get',
    type: 1
  })
}