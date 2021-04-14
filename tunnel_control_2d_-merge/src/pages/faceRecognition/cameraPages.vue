<template>
  <div class="cameraPage-container">
    <div class="camera-btn">
      <span class="btn" :class="{active: isActive}" @click="cameraClick">摄像头组</span>
      <span class="btn" :class="{active: !isActive}" @click="captureClick" style="left: 110px">抓拍机组</span>
      <div class="btn-group">
        <button v-for="i in 5" :class="{activeBtn: isNum === i}" @click="btnClick(i)" :key="i"><span class="btn-num" :class="{activeNum: isNum === i}">{{i}}</span><span>Camera{{i}}</span><span class="danger-circle" v-show="isNum === i"></span></button>
      </div>
    </div>
    <div class="cameraPage" v-for="num in 5" :key="num" v-if="num === isNum">
      <div class="camera-container">
        <div class="camera-video">
          <div class="modal"><span>大厅安检通道画面</span><span>10:33:55</span><span>{{`Camera${isNum}`}}</span></div> <!--蒙版文字-->
          <img :src="ImgData" alt="" style="width: 100%">
        </div>
        <div class="face-warp">
          <vue-seamless-scroll :class-option="optionSwitch" class="seamless-warp">
            <span slot="left-switch" class="left-arrow"></span>
            <span slot="right-switch" class="right-arrow"></span>
            <ul class="item" :style="{width:listData.length * 60 + 'px'}">
              <li v-for="(item, index) in listData" :key="index">{{item}}</li>
            </ul>
          </vue-seamless-scroll>
        </div>
      </div>
      <div class="check-face">
        <div class="nav-text">
          <nav v-for="(item, index) in navData" :key="index" :class="{activeStyle: index === activeNav}"  @click="setActiveNav(index)">
            <div class="nav-title" :class="{activeText: index === activeNav}">{{item}}</div>
          </nav>
        </div>
        <div class="check-detail">
          <ul class="face-container clear-fix">
            <li v-for="(list, index) in 4" :key="index">
              <div class="face-img" :data-corner="index % 2 ? '比对' : '抓拍'">
                <img src="" alt="" >
              </div>
              <div class="result" v-show="!(index % 2)">
                <p class="name">周晨</p>
                <p class="percent">88.2%</p>
                <p class="position">
                  安检A区
                  <i>12:20:11</i>
                </p>
              </div>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
export default {
  data () {
    return {
      activeNav: 0,
      websock: null,
      videoData: '',
      navData: ['比对结果', '黑名单库'],
      listData: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12],
      isActive: true,
      isNum: 1
    };
  },
  created () {
    this.initWebsocket();
  },
  destroyed () {
    this.websock.close(); // 离开路由之后断开websocket连接
  },
  computed: {
    optionSwitch () {
      return {
        autoPlay: false,
        switchSingleStep: 52,
        switchOffset: 10
      };
    },
    ImgData () {
      return 'data:image/jpeg;base64,' + this.videoData;
    }
  },
  methods: {
    setActiveNav (val) {
      this.activeNav = val;
    },
    initWebsocket () {
      const wsurl = 'ws://localhost:3368';
      this.websock = new WebSocket(wsurl);
      console.log(this.websock);
      this.websock.onmessage = this.websocketonmessage;
    },
    websocketonmessage (e) {
      this.videoData = e.data;
      // console.log(this.videoData);
    },
    captureClick () {
      this.isActive = false;
    },
    cameraClick () {
      this.isActive = true;
    },
    btnClick (val) {
      this.isNum = val;
    }
  }
};
</script>

<style scoped lang="scss">
.cameraPage-container {
  display: flex;
  padding: 30px;
  .camera-btn {
    flex: 0 0 200px;
    .btn {
      width: 96px;
      height:36px;
      line-height: 36px;
      background: rgba(0,0,0,.3);
      position: absolute;
      left: 40px;
      font-size: 12px;
      text-align: center;
      color: #44caf9;
      opacity: 0.6;
      cursor: pointer;
      border: 1px solid rgb(18, 102, 153);
      box-shadow: 0 0 10px rgba(100,200,255,.5) inset;
    }
    .btn:before {
      content: ' ';
      border: solid transparent;
      position: absolute;
      border-width: 7px;
      border-top-color: #0f5486;
      border-right-color: #0f5486;
      right: 0px;
      top: 0.5px;
    }
    .btn:after {
      content: ' ';
      border: solid transparent;
      position: absolute;
      border-width: 7px;
      border-top-color: #05103c;
      border-right-color: #05103c;
      top: -1px;
      right: -1px;
    }
    .active {
      color: #fff;
    }
    .btn-group {
      display: flex;
      flex-direction: column;
      margin-top: 60px;
      button {
        position: relative;
        right: 25px;
        width: 180px;
        height: 40px;
        background-color:#1a4495;
        border-radius: 30px;
        border:none;
        outline: none;
        margin-bottom: 40px;
        font-size: 22px;
        color: #fff;
        cursor: pointer;
      }
      .btn-num {
        border-radius: 50%;
        background-color: #030a26;
        position: absolute;
        left: 10px;
        width: 26px;
        height: 26px;
        line-height: 26px;
        color:#43cfff;
      }
      span:nth-child(2) {
        margin-left: 25px;
      }
      .activeNum {
        background-color:#a21b26;
        color: #fff;
      }
      .activeBtn {
        background-color: #37a7d2;
      }
    }
    .danger-circle {
      position: absolute;
      right: 5px;
      top: -4px;
      width: 15px;
      height: 15px;
      border-radius: 50%;
      background: #a21b26;
    }
  }
  .cameraPage {
    display: flex;
    .camera-container {
      display: flex;
      flex-direction: column;
      width: 550px;
      .camera-video {
        width: 100%;
        height: 530px;
        overflow: hidden;
        background-color: red;
        position: relative;
        .modal {
          position: absolute;
          height: 10%;
          width: 100%;
          top: 0;
          color: #fff;
          font-size: 10px;
          background: rgba(0,0,0,.3);
          display: flex;
          align-items: center;
          padding: 0 10px;
          span {
            flex: 1;
            text-align: center;
          }
        }
      }
      .face-warp {
        margin: 20px auto;
        .seamless-warp {
          height: 60px;
          width: 440px;
          margin: 0 auto;
          overflow: hidden;
          .left-arrow, .right-arrow {
            position: relative;
            display: inline-block;
            top: 0;
            width: 26px;
            height: 44px;
            background-color: #00a0e9;
            cursor: pointer;
            &:hover {
              background-color: #0f39fa;
            }
            &::before {
              position: absolute;
              content: '';
              width: 10px;
              left: 10px;
              height: 10px;
              top: 16px;
              border: 2px solid #fff;
            }
          }
          .left-arrow::before {
            border-right: 0;
            border-bottom: 0;
            transform: rotate(-45deg);
          }
          .right-arrow::before {
            border-left: 0;
            border-top: 0;
            left: 5px;
            transform: rotate(-45deg);
          }
          .item {
            width: 720px;
            li {
              float: left;
              display: inline-block;
              width: 60px;
              margin-right: 16px;
              line-height: 60px;
              background-color: #999;
              color: #fff;
              text-align: center;
              font-size: 12px;
            }
          }
        }
      }
    }
    .check-face {
      position: relative;
      left: 20px;
      .nav-text {
        position: relative;
        width: fit-content;
        margin: 0 auto;
        text-align: center;
        nav {
          display: inline-block;
          padding-bottom: 8px;
          border-bottom: 2px solid #fff;
          .nav-title {
            cursor: pointer;
            display: inline-block;
            font-size: 14px;
            color: #44caf9;
          }
          .activeText {
            color: #fff;
          }
        }
        nav:first-child{
          padding-right: 50px
        }
        nav:nth-child(2){
          padding-left: 50px
        }
        .activeStyle {
          border-bottom: 1px solid #44caf9;
        }
      }
      .check-detail {
        width: 120px;
        .face-container {
          width: 330px;
          li {
            width: 50%;
            float:left;
            margin-top: 10px;
            box-sizing: border-box;
            display: flex;
            .face-img {
              width: 80px;
              height: 98px;
              background-color: #1a4495;
              position: relative;
              border: 1px solid #44caf9;
              img {
                width: 100%
              }
            }
            .face-img:after {
              content: attr(data-corner);
              display: block;
              position: absolute;
              top: 0;
              right: 0;
              width: 42px;
              height: 20px;
              font-size: 10px;
              overflow: hidden;
              text-align: right;
              color: #bbeafa;
              background: linear-gradient(45deg, transparent 5px, #a21b26 0)
                    bottom left;
            }
            .result {
              width: 70px;
              padding-left: 5px;
              font-size: 12px;
              line-height: 25px;
              .name {
                color: #fff;
              }
              .percent {
                font-size: 22px;
                color: #a21b26;
              }
              .position {
                color: #fff;
                i {
                  display: block;
                  font-size: 10px;
                }
              }
            }
          }
        }
      }
    }
  }
}
</style>
