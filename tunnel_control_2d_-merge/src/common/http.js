import axios from 'axios'; // 引入axios
import Vue from 'vue';
// import Cookies from 'js-cookie';
// import { Message } from "element-ui"; // 弹框组件
// import store from '@/store';
// import { getToken } from '@/utils/auth';
axios.defaults.withCredentials = true;
// 设置默认请求头
axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8';
// axios.defaults.timeout = BDConfig.timeout;
// const service = axios.create();
// console.log(service);
axios.interceptors.request.use(config => {
  // 是否需要设置 token
  // const isToken = (config.headers || {}).isToken === false;
  // console.log(Cookies.get('token'));
  // if (Cookies.get('token')) {
  //   config.headers.common['Cookie'] = Cookies.get('token'); // 让每个请求携带自定义token 请根据实际情况自行修改
  // }
  return config;
}, error => {
  console.log(error);
  Promise.reject(error);
});
// 响应拦截器即异常处理
axios.interceptors.response.use(response => {
  let data;
  // IE9时response.data是undefined，因此需要使用response.request.responseText(Stringify后的字符串)
  if (response.data === undefined) {
    data = response.request.responseText;
  } else {
    data = response.data;
  }
  return data;
}, err => {
  if (err && err.response) {
    switch (err.response.status) {
      case 400:
        err.message = '错误请求';
        break;
      case 401:
        err.message = '未授权，请重新登录';
        break;
      case 403:
        err.message = '拒绝访问';
        break;
      case 404:
        err.message = '请求错误,未找到该资源';
        break;
      case 405:
        err.message = '请求方法未允许';
        break;
      case 408:
        err.message = '请求超时';
        break;
      case 500:
        err.message = '服务器端出错';
        break;
      case 501:
        err.message = '网络未实现';
        break;
      case 502:
        err.message = '网络错误';
        break;
      case 503:
        err.message = '服务不可用';
        break;
      case 504:
        err.message = '网络超时';
        break;
      case 505:
        err.message = 'http版本不支持该请求';
        break;
      default:
        err.message = `连接错误${err.response.status}`;
    }
    // Message({
    //   showClose: true,
    //   message: err.message,
    //   type: "error"
    // });
  } else {
    // Message({
    //   showClose: true,
    //   message: '连接到服务器失败',
    //   type: "error"
    // });
  };
  return Promise.resolve(err.response);
});
export default {
  get (url, param) {
    return new Promise((resolve, reject) => {
      axios({
        method: 'get',
        url,
        params: param,
        baseURL: this.getBaseUrl()
      }).then((res) => {
        resolve(res);
      }).catch((err) => {
        reject(err);
      });
    });
  },
  // post请求
  post (url, param) {
    return new Promise((resolve, reject) => {
      axios({
        method: 'post',
        url,
        data: param,
        baseURL: this.getBaseUrl()
      }).then(res => {
        resolve(res);
      }).catch((error) => {
        reject(error);
      });
    });
  },
  login (url, param) {
    return new Promise((resolve, reject) => {
      axios({
        method: 'post',
        url,
        data: this.formData(param),
        baseURL: this.getBaseUrl()
      }).then(res => {
        resolve(res);
      }).catch((error) => {
        reject(error);
      });
    });
  },
  formData (param) {
    let formData = new FormData();
    for (let key in param) {
      if (param.hasOwnProperty(key)) {
        let ele = param[key];
        formData.append(key, ele);
      }
    }
    return formData;
  },
  // getUrl (url, baseURL, port) {
  //   console.log(baseURL);
  //   if (baseURL) {
  //     url = baseURL + url;
  //     return url;
  //   } else if (port) {
  //     url = `http://10.0.31.236:${port}/` + url;
  //     return url;
  //   } else {
  //     // return BDConfig.api + url;
  //     return url;
  //   }
  // },
  getBaseUrl () {
    return Vue.prototype.BASE_URL;
  }
  // setHeader (headers) {
  //   if (headers) {
  //     for (const key in headers) {
  //       headers[key] = encodeURI(headers[key]);
  //     }
  //   }
  //   return headers;
  // }
};
