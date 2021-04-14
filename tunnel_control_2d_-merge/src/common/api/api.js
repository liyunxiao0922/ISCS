import http from '../http.js';

// 登录Login
export const Login = (param) => {
  return http.login('/login', param);
};
// 模块切换
export const equipChange = (param) => {
  // param.afterName = param.afterName.replace(/\//g, '%2F');
  param.afterName = param.afterName === '门架交易/牌识成功率统计' ? 'menjiajiaoyi' : param.afterName;
  // let afterName = encodeURIComponent(param.afterName);
  // console.log(afterName);
  return http.get(`/Api/Report/updateArray/${param.index}/${param.afterName}`);
};

// 初始化数据
export const equipMaintain = (param) => {
  return http.get('/Api/Report/equipMaintain', param);
};

// 获取高德地图点数据
export const getPoints = (param) => {
  return http.get('/Api/Report/getHighwayArray/', param);
};

// 获取高德地图点数据
export const updatePoints = (param) => {
  return http.get(`/Api/Report/updateHighwayArray/${param.toString()}`);
};
