<template>
  <div class="drone-interception-container">
    <div class="map-container">
        <div class="btn" ref="btn">
          <!-- <p v-for="(item, ind) in btnInfo" :key="ind" :class="{active: ind === selectInd}" @click="handleBtn(ind)">
            <span><i :class="['iconfont', `${item.icon}`]"></i>{{item.title}}</span>
            <span>{{item.num}}</span>
          </p> -->
          <el-checkbox-group v-model="checkedBox" @change="handleCheckChange">
            <el-checkbox-button v-for="(item, ind) in mapData && mapData.sumArray" :label="item.title" :key="ind" @change="handleBtn(item)">
              <!-- <span><i :class="['iconfont', `${item.icon}`]"></i>{{item.title}}</span> -->
              <span><img :src="btnImg[item.title]" />{{item.title}}</span>
              <span>{{item.num}}</span>
            </el-checkbox-button>
          </el-checkbox-group>
        </div>
        <div id="map-canvas" class="allMap"></div>
        <div class="info-wrapper" ref="info" v-drag>
          <p v-for="(item, ind) in infoData" :key="ind">
            <span><i :class="['iconfont', `${item.icon}`]"></i></span>
            <span>{{item.title}}</span>
          </p>
        </div>
    </div>
  </div>
</template>

<script>
import Vue from 'vue';
import vueSeamlessScroll from 'vue-seamless-scroll';
import { updatePoints } from '../../common/api/api';
export default {
  data () {
    return {
      websock: null,
      videoData: "",
      userlocation: { lng: "117.000923", lat: "36.675807" },
      map: null,
      startLong: 117.000923,
      startLat: 36.675807,
      endLong: 117.000923,
      endLat: 36.675807,
      startLongR: 117.000923,
      startLatR: 36.675807,
      endLongR: 117.000923,
      endLatR: 36.675807,
      linesPoints: null,
      infoWindow: null,
      carMk: "",
      myIcon: "",
      myIcon1: "",
      markers: [],
      data: [
        [116.380425, 39.967687],
        [116.381399, 39.978104],
        [116.378741, 39.986844],
        [116.374922, 39.99526],
        [116.369012, 40.003007],
        [116.363221, 40.010826],
        [116.357305, 40.018556],
        [116.351323, 40.026286],
        [116.34533, 40.034006],
        [116.339361, 40.041741],
        [116.33343, 40.049497],
        [116.32745, 40.057218],
        [116.321502, 40.064966],
        [116.315514, 40.072689],
        [116.30952, 40.080415],
        [116.303512, 40.08814],
        [116.297495, 40.095847],
        [116.291953, 40.103744],
        [116.284143, 40.110392],
        [116.275685, 40.116614],
        [116.268984, 40.123906],
        [116.268399, 40.132866],
        [116.26281, 40.140656],
        [116.256594, 40.148268],
        [116.251149, 40.156229],
        [116.245784, 40.164221],
        [116.242772, 40.172871],
        [116.238741, 40.181299],
        [116.234428, 40.189656],
        [116.230257, 40.198053],
        [116.226208, 40.206482],
        [116.22174, 40.214787],
        [116.215954, 40.222567],
        [116.20487, 40.224859],
        [116.19325, 40.225741],
        [116.182173, 40.228772],
        [116.171083, 40.231786],
        [116.162234, 40.237307],
        [116.153706, 40.243146],
        [116.143273, 40.247243],
        [116.13317, 40.251846],
        [116.124139, 40.257485],
        [116.114415, 40.26239],
        [116.107721, 40.269604],
        [116.097245, 40.273439],
        [116.087308, 40.278195],
        [116.079038, 40.284582],
        [116.074811, 40.292663],
        [116.066308, 40.298392],
        [116.058797, 40.304854],
        [116.052098, 40.31124],
        [116.046674, 40.318679],
        [116.040798, 40.326055],
        [116.038436, 40.334617],
        [116.027527, 40.337643],
        [116.022042, 40.345261],
        [116.015004, 40.351816],
        [116.00623, 40.357821],
        [115.995247, 40.360952],
        [115.98514, 40.360581],
        [115.974341, 40.36352],
        [115.962651, 40.364655],
        [115.950887, 40.364548],
        [115.939509, 40.362301],
        [115.92828, 40.359612],
        [115.916601, 40.358466],
        [115.904848, 40.357846],
        [115.893182, 40.358659],
        [115.883346, 40.358338]
      ],
      checkedBox: [],
      // btnInfo: [{
      //   icon: '../../../static/image/camera.png',
      //   title: '摄像头',
      //   num: 20
      // }, {
      //   icon: '../../../static/image/gantry.png',
      //   title: '门架',
      //   num: 10
      // }, {
      //   icon: '../../../static/image/etc1.png',
      //   title: '收费站',
      //   num: 30
      // }, {
      //   icon: '../../../static/image/tunnel.png',
      //   title: '隧道',
      //   num: 38
      // }, {
      //   icon: '../../../static/image/information-board.png',
      //   title: '情报板',
      //   num: 19
      // }],
      btnImg: {'摄像头': '../../../static/image/camera.png', '门架': '../../../static/image/gantry.png', '收费站': '../../../static/image/etc1.png', '隧道': '../../../static/image/tunnel.png', '情报板': '../../../static/image/information-board.png'},
      infoData: [{
        icon: 'icon-icon-test',
        title: '施工'
      }, {
        icon: 'icon-shigujilu',
        title: '事故'
      }, {
        icon: 'icon-ziranzaihai',
        title: '自然灾害'
      }],
      iconData: {1: '../../../static/image/camera1.png', 2: '../../../static/image/gantry1.png', 3: '../../../static/image/etc.png', 4: '../../../static/image/tunnel1.png', 5: '../../../static/image/information-board1.png', 6: '../../../static/image/road-work.png', 7: '../../../static/image/accident.png', 8: '../../../static/image/natural.png'},
      markImg: '../../../static/image/location.png',
      selectInd: 0
    };
  },
  props: ['param', 'mapData', 'flag'],
  computed: {
    ImgData () {
      return "data:image/jpeg;base64," + this.videoData;
    }
  },
  created () {
    this.initWebsocket();
    this.checkedBox = this.mapData && this.mapData.sumArray.filter(item => item.selected).map(item => item.title);
  },
  watch: {
    'param': function (val, oldVal) {
      if (val.ind === 0) {
        if (!val.collapse) {
          this.$refs.btn.style = "position:absolute; left: 30px";
        } else {
          this.$refs.btn.style = "position:absolute; left: 400px";
        }
      }
      // if (val.ind === 5) {
      //   if (!val.collapse) {
      //     console.log(this.$refs.info);
      //     this.$refs.info.style = "position:absolute; top: 930px";
      //   } else {
      //     this.$refs.info.style = "position:absolute; top: 615px";
      //   }
      // }
      // if (val.ind === 7) {
      //   if (!val.collapse) {
      //     console.log(this.$refs.info);
      //     this.$refs.info.style = "position:absolute; right: 20px";
      //   } else {
      //     this.$refs.info.style = "position:absolute; right: 400px";
      //   }
      // }
      // if (val.ind === 8) {
      //   if (!val.collapse) {
      //     console.log(this.$refs.info);
      //     this.$refs.info.style = "position:absolute; right: 20px; bottom: 20px";
      //   } else {
      //     this.$refs.info.style = "position:absolute; right: 400px";
      //   }
      // }
    },
    mapData: {
      handler (val, newVal) {
        if (val !== newVal) {
          this.data = val;
        }
      },
      immediate: true
    },
    'flag': function (val, oldVal) {
      console.log(val);
      if (!val) {
        this.$refs.btn.style = "position:absolute; left: 30px";
        this.$refs.info.style = "position:absolute; top: 930px; right: 10px";
      } else {
        this.$refs.btn.style = "position:absolute; left: 400px";
        this.$refs.info.style = "position:absolute; top: 610px";
      }
    }
  },
  directives: {
    drag (el) {
      let oDiv = el; // 当前元素
      // 禁止选择网页上的文字
      document.onselectstart = function () {
        return false;
      };
      oDiv.onmousedown = function (e) {
        // 鼠标按下，计算当前元素距离可视区的距离
        let disX = e.clientX - oDiv.offsetLeft;
        let disY = e.clientY - oDiv.offsetTop;
        document.onmousemove = function (e) {
          // 通过事件委托，计算移动的距离
          let l = e.clientX - disX;
          let t = e.clientY - disY;
          // 移动当前元素
          oDiv.style.left = l + "px";
          oDiv.style.top = t + "px";
        };
        document.onmouseup = function (e) {
          document.onmousemove = null;
          document.onmouseup = null;
        };
        // return false不加的话可能导致黏连，就是拖到一个地方时div粘在鼠标上不下来，相当于onmouseup失效
        return false;
      };
    }
  },
  mounted () {
    this.drawMap();
  },
  destroyed () {
    this.websock.close(); // 离开路由之后断开websocket连接
  },
  methods: {
    initWebsocket () {
      const wsurl = "ws://10.10.10.248:3370";
      this.websock = new WebSocket(wsurl);
      this.websock.onmessage = this.websocketonmessage;
    },
    websocketonmessage (e) {
      this.videoData = e.data;
    },
    drawMap () {
      this.map = new AMap.Map('map-canvas', {
        center: [115.962654, 40.364655],
        zoom: 16,
        resizeEnable: true,
        // scrollWheel: false,
        mapStyle: 'amap://styles/df59f739d7ad82b87a2233663cf1513d'
      });
      this.map.on('complete', () => {
        // 地图图块加载完成后触发
        this.addTools();
        this.linePath();
        this.initPoint();
        this.data.sumArray.forEach(item => {
          console.log(item);
          this.handleBtn({...item, selected: !item.selected});
        });
      });
    },
    addTools () {
      let map = this.map;
      AMap.plugin([
        'AMap.Scale'
      ], function () {
        // 在图面添加比例尺控件，展示地图在当前层级和纬度下的比例尺
        map.addControl(new AMap.Scale());
      });
    },
    initPoint () {
      this.data.highWayEquips.forEach(item => {
        this.addMarker(item);
      });
    },
    linePath () {
      var drivingOption = {};
      var driving = new AMap.Driving(drivingOption);
      let start = this.data.highWayDot[0];
      let end = this.data.highWayDot[1];
      driving.search(new AMap.LngLat(start[0], start[1]), new AMap.LngLat(end[0], end[1]), (status, result) => {
        // result即是对应的驾车导航信息，相关数据结构文档请参考 https://lbs.amap.com/api/javascript-api/reference/route-search#m_DrivingResult
        if (status === 'complete') {
          if (result.routes && result.routes.length) {
            // 绘制第一条路线，也可以按需求绘制其它几条路线
            this.drawRoute(result.routes[0]);
          }
        } else {
          log.error('获取驾车数据失败：' + result);
        }
      });
    },
    drawRoute (route) {
      var path = this.parseRouteToPath(route);
      var routeLine = new AMap.Polyline({
        path: path,
        isOutline: true,
        outlineColor: '#0091ff',
        borderWeight: 2,
        strokeWeight: 4,
        strokeColor: '#0091ff',
        lineJoin: 'round'
      });
      routeLine.setMap(this.map);
      // 调整视野达到最佳显示区域
      this.map.setFitView([ routeLine ]);
    },
    parseRouteToPath (route) {
      var path = [];
      for (var i = 0, l = route.steps.length; i < l; i++) {
        var step = route.steps[i];
        for (var j = 0, n = step.path.length; j < n; j++) {
          path.push(step.path[j]);
        }
      }
      return path;
    },
    infoDialog (marker, val) {
      let that = this;
      // 实例化信息窗体
      var Component = Vue.extend({
        data () {
          return {
            percent: 100,
            equipmentTitle: ['序号', '设备名称', '事件类型'],
            equipmentData: val && val.apiEquipUnnormalEvents,
            classOption: {
              hoverStop: true,
              step: 0.5
            },
            videoData: val && val.videoUrl,
            customColor: 'rgb(10, 160, 128)',
            name: val && val.typeName,
            status: val && val.stateName,
            process: val && val.stationProvinceTrans,
            stumb: val && val.stumb,
            softwareParamName: val && val.softwareParamName,
            equipStateName: val && val.equipStateName,
            gatherTime: val && val.gatherTime,
            typeName: val && val.typeName,
            video: val && val.videoSrc,
            title: val && val.name,
            stateName: val && val.stateName,
            hashMapList: val && val.hashMapList,
            stationProvinceTrans: val && val.stationProvinceTrans,
            stringList: val && val.stringList,
            ip: val && val.ip,
            direction: val && val.direction,
            equipNumber: val && val.equipNumber,
            equipTypeName: val && val.equipTypeName
          };
        },
        components: {
          vueSeamlessScroll
        },
        template: `<div class="info-container" v-if="name === '摄像头' || name === '收费站' || name === '门架' || name === '情报板'">
          <p class="close-wrapper"><span class="icon-close el-icon-close" @click="closeInfo"></span></p> 
          <div v-if="name === '收费站'">
            <p class="title-wrapper"><span class="title">{{title}}</span><span class="status"><i></i><i>{{status}}</i></span></p>
            <p class="content-wrapper"><span>站省传输</span><el-progress :text-inside="true" :stroke-width="14" :percentage="stationProvinceTrans" :color="customColor" status="success" style="width: 72%;position:absolute;left: 20%;top: 3px"></el-progress></p>
            <p class="station-wrapper"><span>桩号</span><span>{{stumb}}</span></p>
            <p class="param-wrapper"><section><span>设备状态</span><span>{{equipStateName}}</span></section><section><span>软件参数</span><span>{{softwareParamName}}</span></section></p>
            <p class="list-wrapper">
              <section>
                <div class="construction-header">
                  <span v-for="(list, ind) in equipmentTitle" :key="ind">{{list}}</span>
                </div>
                <vue-seamless-scroll :data="equipmentData" :class-option="classOption" class="warp" ref="seamlessScroll" v-if="equipmentData">
                  <ul class="item">
                    <li v-for="(item, index) in equipmentData" :key="index">
                      <span class="title" v-text="item.index"></span>
                      <el-tooltip class="item" effect="dark" :content="item.equipName" placement="top-start">
                        <span v-text="item.equipName"></span>
                      </el-tooltip>
                      <el-tooltip class="item" effect="dark" :content="item.eventType" placement="top-start">
                        <span v-text="item.eventType" class="status"></span>
                      </el-tooltip>
                    </li>
                  </ul>
                </vue-seamless-scroll>
              </section>
            </p>
            <p class="foot">
              <span>采集时间：{{gatherTime}}</span>
              <span @click="handleToll">{{typeName}}详情 > </span>
            </p>
          </div>
          <div v-if="name === '摄像头'">
            <p class="title-wrapper"><span class="title">新街收费站</span><span class="status"><i></i><i>{{status}}</i></span></p>
            <p class="station-wrapper"><span>桩号</span><span>{{stumb}}</span></p>
            <p class="video-wrapper">
              <video muted width="99%" height="100%" autoplay="autoplay" loop :src="videoData" controls></video>
            </p>
            <p class="foot">
              <span>采集时间：{{gatherTime}}</span>
              <span @click="handleToll">{{typeName}}详情 > </span>
            </p>
          </div>
          <div v-if="name === '门架'">
            <p class="title-wrapper"><span class="title">{{title}}</span><span class="status"><i></i><i>{{status}}</i></span></p>
            <p class="content-wrapper" v-for="item in hashMapList"><span>{{item.title}}</span><el-progress :text-inside="true" :stroke-width="13" :percentage="item.value" :color="customColor" status="success" style="width: 72%;position:absolute;left: 20%;top: 3px;font-size:12px"></el-progress></p>
            <p class="station-wrapper"><span>桩号</span><span>{{stumb}}</span></p>
            <p class="param-wrapper"><section><span>设备状态</span><span>{{stateName}}</span></section><section><span>软件参数</span><span>{{softwareParamName}}</span></section></p>
            <p class="list-wrapper">
              <section>
                <div class="construction-header">
                  <span v-for="(list, ind) in equipmentTitle" :key="ind">{{list}}</span>
                </div>
                <vue-seamless-scroll :data="equipmentData" :class-option="classOption" class="warp" ref="seamlessScroll" v-if="equipmentData">
                  <ul class="item">
                    <li v-for="(item, index) in equipmentData" :key="index">
                      <span class="title" v-text="item.index"></span>
                      <el-tooltip class="item" effect="dark" :content="item.equipName" placement="top-start">
                        <span v-text="item.equipName"></span>
                      </el-tooltip>
                      <el-tooltip class="item" effect="dark" :content="item.eventType" placement="top-start">
                        <span v-text="item.eventType" class="status"></span>
                      </el-tooltip>
                    </li>
                  </ul>
                </vue-seamless-scroll>
              </section>
            </p>
            <p class="foot">
              <span>采集时间：{{gatherTime}}</span>
              <span @click="handleToll">{{typeName}}详情 > </span>
            </p>
          </div>
          <div v-if="name === '情报板'">
            <p class="title-wrapper"><span class="title">{{title}}</span><span class="status"><i></i><i>{{status}}</i></span></p>
            <p class="info-wrapper" style="background: #000; color: #F56C6C; font-size: 22px; text-align: center">
              <el-carousel arrow="never" style="height: 70px">
                <el-carousel-item v-for="item in stringList" :key="item">
                  <div class="info-content">{{item}}</div>
                </el-carousel-item>
              </el-carousel>
            </p>
            <p class="station-wrapper" style="margin-top: 20px"><span>桩号</span><span>{{stumb}}</span></p>
            <p class="station-wrapper" style="margin-top: 10px"><span>设备类型</span><span>{{equipTypeName}}</span></p>
            <p class="station-wrapper" style="margin-top: 10px"><span>IP地址</span><span>{{ip}}</span></p>
            <p class="param-wrapper" style="margin-top: 10px"><section><span>设备方向</span><span>{{direction}}</span></section><section><span>设备编号</span><span>{{equipNumber}}</span></section></p>
            <p class="foot">
              <span>采集时间：{{gatherTime}}</span>
              <span @click="handleToll">{{typeName}}详情 > </span>
            </p>
          </div>
        </div>`,
        methods: {
          closeInfo () {
            infoWindow.close();
          },
          handleToll () {
            switch (val && val.type) {
              case 2: that.$router.push('/networkSystem');
                break;
              case 3: that.$router.push('/tollSystem');
                break;
              case 4: that.$router.push('/tunnelMonitoring');
                break;
            }
          }
        }
      });
      var component = new Component().$mount();
      var infoWindow = new AMap.InfoWindow({
        isCustom: true, // 使用自定义窗体
        content: component.$el,
        offset: new AMap.Pixel(16, -45)
      });
      console.log(val);
      infoWindow.open(that.map, marker.getPosition());
    },
    // closeInfo () {
    //   this.map.clearInfoWindow();
    // },
    handleBtn (val) {
      let markers = [];
      this.markers.forEach(item => {
        markers = this.markers.filter(item => item.getExtData().title === val.title);
      });
      if (val.selected) {
        this.map.remove(markers);
      } else {
        this.map.add(markers);
      }
    },
    addMarker (val) {
      // 创建一个 Icon
      let startIcon = new AMap.Icon({
        // 图标尺寸
        size: new AMap.Size(29, 34),
        // 图标的取图地址
        image: this.iconData[val.type],
        // 图标所用图片大小
        // imageSize: new AMap.Size(40, 40),
        // 图标取图偏移量
        imageOffset: new AMap.Pixel(0, 0)
      });
      let marker = new AMap.Marker({
        position: new AMap.LngLat(val.lat, val.lng),
        icon: startIcon,
        offset: new AMap.Pixel(-13, -30),
        extData: {
          title: val.typeName
        },
        clickable: true
      });
      this.markers.push(marker);
      this.map.add(marker);
      marker.on('click', () => {
        this.infoDialog(marker, val);
      });
    },
    async handleCheckChange (val) {
      console.log(val);
      let res = await updatePoints(val);
      if (res && res.code === 0) {
        this.$emit('handleChange', true);
      }
    }
  }
};
</script>

<style lang="scss">
.info-container {
  position: relative;
  width: 350px;
  height: 400px;
  background: rgba(45, 60, 99, 1);
  display: flex;
  flex-direction: column;
  font-size: 14px;
  border-left: 1px solid rgb(88, 207, 255);
  &:before {
    content: '';
    position: absolute;
    top: 22px;
    left: 0;
    width: 4px;
    height: 18px;
    background: rgb(244, 223, 88);
  }
  &::after {
    content: '';
    position: absolute;
    right: 0;
    bottom: 0;
    width: 0;
    height: 0;
    border-bottom: 12px solid rgb(244, 223, 88);
    border-left: 12px solid transparent;
  }
  .close-wrapper {
    position: relative;
    display: inline-block;
    margin: 10px 0 2px 0;
    .icon-close {
      position: absolute;
      right: 10px;
      color: salmon;
      cursor: pointer;
    }
  }
  .title-wrapper {
    display: inline-block;
    width: 100%;
    height: 20px;
    padding: 12px;
    border-bottom: 1px solid rgb(22, 30, 52);
    margin-bottom: 5px;
    .title {
      color: rgb(244, 223, 88);
    }
    .status {
      width: 50px;
      margin-left: 30px;
      i {
        margin-right: 10px;
        &:first-child {
          display: inline-block;
          width: 12px;
          height: 12px;
          border-radius: 7px;
          background: chartreuse;
          color: chartreuse;
        }
        &:nth-child(2) {
          font-size: 12px;
          color: chartreuse;
        }
      }
    }
  }
  .content-wrapper {
    width: 100%;
    padding: 0 10px 5px 10px;
    height: 20px;
    position: relative;
    span {
      font-size: 12px;
      color: #fff;
    }
    /deep/ .el-progress {
      display: inline-block;
      width: 76%;
      display: flex;
      position: absolute;
      left: 20%;
      top: 5px;
      .el-progress__text {
        font-size: 12px;
        color: #fff;
      }
    }
  }
  .station-wrapper {
    margin: 2px 10px;
    height: 26px;
    line-height: 26px;
    border: 1px solid rgb(22, 30, 52);
    display: flex;
    justify-content: space-between;
    span {
      padding: 0 10px;
      font-size: 12px;
      color: #fff;
    }
  }
  .param-wrapper {
    margin: 5px 10px;
    height: 26px;
    line-height: 26px;
    display: flex;
    justify-content: space-between;
    section {
      border: 1px solid rgb(22, 30, 52);
      flex: 1;
      padding: 0 10px;
      font-size: 12px;
      color: #fff;
      display: flex;
      justify-content: space-between;
      span {
        &:nth-child(2) {
          color: chocolate;
        }
      }
      &:first-child {
        margin-right: 5px;
        width: calc(50% - 5px);
      }
      &:nth-child(2) {
        margin-left: 5px;
        width: calc(50% - 5px);
      }
    }
  }
  .list-wrapper {
    position: relative;
    .construction-header {
      position: absolute;
      top: 0;
      left: 1px;
      display: inline-block;
      width: calc(100% - 2px);
      height: 30px;
      line-height: 30px;
      background: rgba(13, 64, 113, 1);
      display: flex;
      flex-direction: row;
      justify-content: space-between;
      span {
        font-size: 12px;
        color: #fff;
        padding: 0 10px;
        &:first-child {
          width: 40px;
          text-align: center;
        }
        &:nth-child(2) {
          width: 260px;
          text-align: center;
          white-space: nowrap;
          overflow: hidden;
          text-overflow: ellipsis;
        }
        &:nth-child(3) {
          white-space: nowrap;
          overflow: hidden;
          text-overflow: ellipsis;
          width: 100px;
          text-align: center;
        }
      }
    }
    .warp {
      position: relative;
      top: 33px;
      height: 200px;
      width: 100%;
      overflow: hidden;
      ul {
        width: 100%;
        list-style: none;
        padding: 0;
        margin: 0 auto;
        background: rgba(28,38,65,1);
        color: rgb(70, 188, 238);
        li, a {
          width: 100%;
          display: block;
          height: 30px;
          line-height: 30px;
          display: flex;
          justify-content: space-between;
          font-size: 12px;
          span {
            padding: 0 5px;
            &:first-child {
              width: 40px;
              text-align: center;
            }
            &:nth-child(2) {
              width: 260px;
              text-align: center;
              white-space: nowrap;
              overflow: hidden;
              text-overflow: ellipsis;
            }
            &:nth-child(3) {
              white-space: nowrap;
              overflow: hidden;
              text-overflow: ellipsis;
              width: 100px;
              text-align: center;
            }
          }
        }
        li:nth-child(2n) {
          background: rgb(6, 28, 51);
        }
      }
      .status {
        color: #F56C6C;
      }
    }
  }
  .video-wrapper {
    margin: 10px;
    width: 96.5%;
    height: 100%;
    video:focus{
      outline: -webkit-focus-ring-color auto 0px;  // 去除video获得焦点的边框
    }
  }
  .info-wrapper {
    margin: 10px auto;
    width: 80%;
    height: 60px;
    line-height: 60px;
    border: 1px solid rgb(60, 60, 60);
    /deep/ .el-carousel__container {
      height: 90px;
    }
    .el-carousel::-webkit-scrollbar {
        display: none;
    }
    /deep/ .el-carousel__indicator /deep/ .el-carousel__indicator--horizontal /deep/ .is-active {
      background: #409EFF;
    }
    .info-content {
      text-align: center;
      font-size: 20px;
      background: #000;
      color: #F56C6C;
    }
  }
  .no-data {
    width: 260px;
    height: 100px;
    line-height: 100px;
    border: 1px dashed #409EFF;
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);
    p {
      font-size: 24px;
      color: #409EFF;
      text-align: center;
    }
  }
  .foot {
    margin: 0 10px;
    position: absolute;
    bottom: 10px;
    width: 96%;
    height: 20px;
    display: flex;
    justify-content: space-between;
    span {
      font-size: 12px;
      color: rgb(244, 223, 88);
      &:nth-child(2) {
        color: rgb(88, 207, 255);
        cursor: pointer;
      }
    }
  }
}
</style>

<style lang="scss" scoped>
.drone-interception-container {
  position: relative;
  width: 100%;
  height: 100%;
  .title {
    color: #43cfff;
    font-size: 20px;
    position: absolute;
    top: -20px;
    left: 40px;
  }
  .map-container {
    width: 100%;
    height: 100%;
    min-height: 100%;
    overflow: hidden;
    position: relative;
    box-sizing: border-box;
    .allMap {
      position: absolute;
      width: 100%;
      height: 100%;
      // position: absolute;
      // .anchorBL {
      //   display: none;
      // }
    }
    .btn {
      position: absolute;
      top: 72px;
      left: 398px;
      width: 120px;
      height: 200px;
      z-index: 11;
      /deep/ .el-checkbox-group .el-checkbox-button {
        width: 94px;
        padding: 0 1px;
        height: 32px;
        line-height: 34px;
        margin: 3.5px 0;
        color: #43cfff;
        border: 1px solid rgb(24, 43, 66);
        background: rgb(24, 43, 66);
        box-shadow: 0 0 8px #43cfff inset;
        box-sizing: border-box;
        span {
          display: inline-block;
          &:first-child {
            width: 60px;
            text-align: left;
          }
          img {
            display: inline-block;
            vertical-align: middle;
            margin-right: 7.5px;
          }
        }
      }
      /deep/ .el-checkbox-button.is-checked {
        display: inline-block;
        color: #fff;
        border: 1px solid #43cfff;
      }
      /deep/ .el-checkbox-button__inner {
        background: none;
        border: none;
        box-shadow: none;
        color: #43cfff;
        padding: 0 10px;
        font-size: 12px;
      }
      /deep/ .el-checkbox-button.is-checked .el-checkbox-button__inner {
        color: #fff;
      }
      p {
        // display: flex;
        // justify-content: space-between;
        // margin: 10px 0;
        // padding: 0 10px;
        // font-size: 14px;
        // color: #43cfff;
        // width: 100%;
        // height: 35px;
        // line-height: 35px;
        // background: rgb(24, 43, 66);
        // border: 1px solid rgb(24, 43, 66);
        // box-shadow: 0 0 8px #43cfff inset;
        // cursor: pointer;
        span {
          i {
            margin-right: 20px;
          }
        }
      }
      // .active {
      //   color: #fff;
      //   border: 1px solid #43cfff;
      // }
    }
    .info-wrapper {
      cursor: move;
      position: absolute;
      top: 610px;
      right: 393px;
      width: 126px;
      height: 126px;
      background: rgb(17, 23, 35);
      box-shadow: 0 0 38px rgb(30, 60, 99) inset;
      display: flex;
      flex-direction: column;
      z-index: 1300;
      justify-content: center;
      align-items: center;
      p {
        width: 80%;
        z-index: 12;
        flex: 1;
        vertical-align: top;
        display: flex;
        align-items: center;
        span {
          font-size: 15px;
          color: rgb(40, 93, 169);
          &:first-child {
            display: inline-block;
            box-sizing: border-box;
            width: 19.5px;
            height: 19.5px;
            line-height: 19.5px;
            text-align: center;
            border-radius: 13px;
            border: 1px solid rgb(231, 116, 5);
            background: rgb(252, 251, 209);
            margin-right: 20px;
            i {
              font-size: 15px;
              color: rgb(255, 48, 0);
            }
          }
        }
      }
    }
  }
}
</style>
