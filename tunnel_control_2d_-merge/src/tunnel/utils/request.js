import axios from 'axios'
import {
  MessageBox,
  Message
} from 'element-ui'
import store from '@/tunnel/store'
import {approve} from "@/tunnel/api/user"
import {
  getToken
} from '@/tunnel/utils/auth'
import Cookies from 'js-cookie'

// create an axios instance
const service = axios.create({
  baseURL: `http://121.40.128.159:24001/`, // url = base url + request url
  // withCredentials: true, // send cookies when cross-domain requests
})

var  reType = 1;
// request interceptor
service.interceptors.request.use(
  
  config => {
    if (config.type == 1) {
      config.headers = {
        'Content-Type': 'application/json',
      }
    } else {
      config.headers = {
        'Content-Type': 'application/x-www-form-urlencoded'
      }
    }

    // if(config.url.indexOf('/login') != -1) {
      /* if (!Cookies.get('token')) {
        tokenGet(1);
      } */
      if( !rqFlag ) tokenGet();
          
    // }
    return config
  },
  error => {
    // do something with request error
    return Promise.reject(error)
  }
)
let rqFlag = true;
function tokenGet(flag) {
  rqFlag = false;
  var bodyFormData = new FormData();
      bodyFormData.set('username', 'api');
      bodyFormData.set('password', 'hollysys');
      bodyFormData.set('rememberMe', true);
    approve(bodyFormData).then(res=>{
    rqFlag = true;
    Cookies.set(
      'token', res.token
    )
    location.reload();
    if(flag) return Cookies.get(token);
  })
  .catch((error) => {
    console.log(error);
  });
}
// response interceptor
service.interceptors.response.use(
  /**
   * If you want to get http information such as headers or status
   * Please return  response => response
   */

  /**
   * Determine the request status by custom code
   * Here is just an example
   * You can also judge the status by HTTP Status Code
   */
  response => {
    const res = response.data
    // if the custom code is not 20000, it is judged as an error.
    // if(res.code === 1) {
      if(res.code == 403) {
        tokenGet();
      }
    // }
  
    if (res.code !== 200 && res.code !== 0) {
      Message({
        message: res.msg || 'Error',
        type: 'error',
      })

      // 50008: Illegal token; 50012: Other clients logged in; 50014: Token expired;
      if (res.code === 50008 || res.code === 50012 || res.code === 50014) {
        // to re-login
        MessageBox.confirm('You have been logged out, you can cancel to stay on this page, or log in again', 'Confirm logout', {
          confirmButtonText: 'Re-Login',
          cancelButtonText: 'Cancel',
          type: 'warning'
        }).then(() => {
          store.dispatch('user/resetToken').then(() => {
            location.reload()
          })
        })
      }
      return Promise.reject(new Error(res.message || 'Error'));
    } else {
      return res;
    }
  },
  error => {
    console.log('err' + error) // for debug
    Message({
      message: error.msg,
      type: 'error',
    })
    return Promise.reject(error)
  }
)

export default service
