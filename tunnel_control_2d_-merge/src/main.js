// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue';
import App from './App';
import axios from 'axios';
import router from './router';
import store from './tunnel/store/index';
import { Progress, Button, Dropdown, DropdownMenu, DropdownItem, Tooltip, Select, Option, Checkbox, CheckboxButton, CheckboxGroup, Switch, Dialog, Tree, Input, Carousel,
  CarouselItem, Message, Icon } from 'element-ui';

import ElementUI from 'element-ui'
import './common/scss/iconfont.scss';
// import * as socketApi from '../src/common/socket.js';
const hls = require('videojs-contrib-hls');
/***这是引用了iconfont图标 */
import '@/tunnel/components/detailTunnel/assets/font/iconfont.css';
import '@/tunnel/icons' // icon

Vue.use(hls);
// Vue.use(Progress);
// Vue.use(Button);
// Vue.use(Dropdown);
// Vue.use(DropdownMenu);
// Vue.use(DropdownItem);
// Vue.use(Tooltip);
// Vue.use(Select);
// Vue.use(Option);
// Vue.use(Checkbox);
// Vue.use(CheckboxButton);
// Vue.use(CheckboxGroup);
// Vue.use(Switch);
// Vue.use(Dialog);
// Vue.use(Tree);
// Vue.use(Input);
// Vue.use(Carousel);
// Vue.use(CarouselItem);
// Vue.use(Icon);
Vue.use(ElementUI);
Vue.prototype.$message = Message;
// Vue.prototype.socketApi = socketApi;
Vue.config.productionTip = false;
// let screen = () => {
//   if (screenfull.isEnabled) {
//     screenfull.request();
//   }
// };
// screen();
let startApp = () => {
  axios.get('../static/config.json').then(res => {
    console.log(res.data.BASE_URL);
    Vue.prototype.BASE_URL = res.data.BASE_URL;
    /* eslint-disable no-new */
    new Vue({
      el: '#app',
      router,
      store,
      components: { App },
      template: '<App/>'
    });
  });
};
startApp();
