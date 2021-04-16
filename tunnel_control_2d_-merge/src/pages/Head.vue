<template>
  <div class="head-container">
    <div class="head-left">
      <img src="../../static/image/logo-luqiao.png" alt="">
      <span class="title">智慧高速路段综合管控云平台</span>
      <!-- <span class="title">智慧高速云控平台</span> -->
    </div>
    <!-- <p class="left-border"><span class="border-line"></span><span class="border-line-one"></span><span class="border-line-one"></span><span class="border-line-one"></span></p> -->
    <!-- <div class="img-decorate"><img src="../../static/image/装饰.png" alt=""></div> -->
    <div class="head-center">
      <!-- <div class="btn-group-left">
        <span v-for="(item, index) in routes.slice(0, 3)" :key="index" class="btn kotlin kotlin-5">
          <span ref="btn-left">
            <router-link :to="item.path">{{item.meta.title}}</router-link>
          </span>
          </span>
      </div> -->
      <div class="btn-group-right">
        <span v-for="(item, index) in routes" :key="index" class="btn kotlin kotlin-5" @click="handleClick(item)">
          <router-link :to="item.path">
            <span :class="['iconfont', 'icon-style', `${iconData[index]}`]"></span>
            <span class="btn-title">{{item.meta.title}}</span>
          </router-link>
        </span>
      </div>
    </div>
    <div class="head-right">
      <div class="img"><img src="../../static/image/icon_shijian@2x.png" alt=""></div>
      <span class="time">{{nowTime}}</span>
      <span class="el-icon-full-screen" @click="handleFullScreen" style="cursor:pointer"></span>
      <el-switch
        v-if="flag"
        v-model="value"
        @change="handleChange"
        active-color="#13ce66"
        inactive-color="#ff4949">
      </el-switch>
    </div>
    <!-- <p class="right-border"><span class="border-line-one"></span><span class="border-line-one"></span><span class="border-line-one"></span><span class="border-line"></span></p> -->
  </div>
</template>

<script>
import util from '../common/util';
import eventBus from '../common/Bus';
import screenfull from "screenfull";
export default {
  data () {
    return {
      nowTime: '',
      timer: null,
      routes: [],
      flag: true,
      iconData: ['icon-gaosugonglu', 'icon-shipinqiang', 'icon-wangluoxitong', 'icon-shoufeixitong', 'icon-zongheyingyong', 'icon-suidaojiankongceliang'],
      value: true
    };
  },
  created () {
    this.routes = this.$router.options.routes && this.$router.options.routes[0].children;
  },
  mounted () {
    // this.$refs['btn-right'][0].style = "display:inline-block;color:yellow;";
    if(localStorage.goto == '/tunnelMonitoring') {
      localStorage.goto = '';
    }else {
      this.$router.push('/netMonitoring');
    }
    this.timer = setInterval(() => { this.nowTime = util.clockon(); }, 1000);
  },
  methods: {
    handleChange () {
      eventBus.$emit("status", this.value);
    },
    handleClick (val) {
      this.value = this.flag = Boolean(val.path === '/netMonitoring' ? 1 : 0);
    },
    handleFullScreen () {
      console.log(1212);
      if (!screenfull.isEnabled) {
        // 如果不允许进入全屏，发出不允许提示
        this.$message({
          message: "暂不不支持全屏",
          type: "warning"
        });
        return false;
      }
      screenfull.toggle();
      // this.$message({
      //   message: "全屏开启",
      //   type: "success"
      // });
    }
  },
  beforeDestroy () {
    if (this.timer) {
      clearInterval(this.timer);
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
/deep/ .el-switch__core {
  width: 36px !important;
  height: 16px;
}
/deep/ .el-switch__core:after {
  content: "";
  position: absolute;
  top: 0;
  border-radius: 50%;
  -webkit-transition: all .3s;
  transition: all .3s;
  // width: .625vw;
  width: 13px;
  height:13px;
  background-color: #fff;
}
/deep/ .el-switch.is-checked .el-switch__core:after {
  margin-left: -14px;
}
.head-container {
  position: absolute;
  top: 0;
  // background: url(/static/image/头部.png) no-repeat;
  background-size: contain;
  width: 100%;
  height: 62.25px;
  background: rgb(29, 34, 48);
  border-top: 3px solid rgb(46, 143, 249);
  z-index: 1112;
  a {
    text-decoration:none;
    color:#fff;
    display: inline-block;
    height: 100%;
    width: 106px;
  }
  /deep/ .router-link-active {
    color: rgb(242,209,87);
    border: none;
    background:rgb(46, 143, 249);
  }
  .head-left {
    position: absolute;
    left: 15px;
    height: 62px;
    display: flex;
    align-items: center;
    justify-content: center;
    img {
      width: 36px;
      height: 36px;
      margin-right: 15px;
    }
    .title {
      display: inline-block;
      color: rgb(242,209,87);
      font-size: 20px;
      cursor: pointer;
      font-weight: bold;
      letter-spacing: 2px;
    }
  }
  .head-right {
    position: absolute;
    right: 2px;
    top: 22px;
    .img {
      display: inline-block;
      width: 16px;
      vertical-align: middle;
      margin-right: 8px;
      img {
        width: 100%;
      }
    }
    .time {
      display: inline-block;
      vertical-align: middle;
      color: #43cfff;
      font-size: 13px;
      padding-right: 15px;
    }
    span {
      display: inline-block;
      color: #43cfff;
      font-size: 18px;
      padding-right: 20px;
    }
  }
  .img-decorate {
    width: 630px;
    height: 34px;
    position: absolute;
    left: 34%;
    top: 22px;
    img {
      width: 100%;
      height: 100%;
    }
  }
  .head-center {
    position: absolute;
    left: 50%;
    margin-left: -414px;
    top: 1px;
    width: 700px;
    height: 62px;
    // &::before {
    //   content: '';
    //   width: 100%;
    //   height: 80px;
    //   background-color: rgb(46, 143, 249);
    //   display: inline-block;
    //   position: absolute;
    //   left: 0;
    //   top: -2px;
    //   transform: perspective(185px) rotateX(160deg);
    // }
  }
  .btn-group-left {
    position: absolute;
    left: 12%;
    top: 40px;
    height: 100px;
    width: 22%;
    z-index: 3;
    display: flex;
    font-size: 14px;
    .btn {
      position: relative;
      cursor: pointer;
      text-align: center;
      flex: 1;
      margin: 0 10px;
      height: 40px;
      line-height: 40px;
      color: #409EFF;
      display: inline-block;
      transform: skew(30deg);
      background: linear-gradient(#275297 -50%, #042453 99%, #275297);
      border: 2px solid #275297;
    }
    .btn::before {
      width: 3px;
      height: 20px;
      background: #275297;
      content: '';
      position: absolute;
      top: 50%;
      left: 0;
      margin-top: -10px;
    }
    .btn::after {
      width: 3px;
      height: 20px;
      background: #275297;
      content: '';
      position: absolute;
      top: 50%;
      right: 0;
      margin-top: -10px;
    }
  }
  .btn-group-right {
    position: absolute;
    left: 0;
    top: 14px;
    width: 22%;
    font-size: 14px;
    z-index: 3;
    display: flex;
    .btn {
      position: relative;
      cursor: pointer;
      text-align: center;
      flex: 1;
      margin: 0 10px;
      height: 33.5px;
      line-height: 33.5px;
      // color: #409EFF;
      display: inline-block;
      // transform: skew(-30deg);
      // background: linear-gradient(#275297 -50%, #042453 99%, #275297);
      // border: 2px solid #275297;
    }
    // .btn::before {
    //   width: 3px;
    //   height: 20px;
    //   background: #275297;
    //   content: '';
    //   position: absolute;
    //   top: 50%;
    //   left: 0;
    //   margin-top: -10px;
    // }
    // .btn::after {
    //   width: 3px;
    //   height: 20px;
    //   background: #275297;
    //   content: '';
    //   position: absolute;
    //   top: 50%;
    //   right: 0;
    //   margin-top: -10px;
    // }
    .btn-right {
      display: flex;
      justify-content: center;
      align-items: center;
    }
    .icon-style {
      font-size: 18px;
    }
    .btn-title {
      display: inline-block;
      padding-top: 1px;
      vertical-align: top;
    }
    .kotlin {
      background: none;
      border: 1px solid;
      width: 106px;
      height: 31.5px;
      line-height: 33.5px;
      letter-spacing: inherit;
      text-transform: inherit;
      transition: color 1s;
    }
    .kotlin-5 {
      color: rgb(8, 44, 77);
    }
    .kotlin-5:hover {
      animation: pulse 1s ease-in infinite;
      background: radial-gradient(circle, rgba(64, 158, 255, 0.25) 43%, transparent 50%) 0 0/1em 1em, radial-gradient(circle, rgba(64, 158, 255, 0.25) 43%, transparent 50%) 0.5em 0.5em/2em 2em;
      color: #43cfff;
    }
    @keyframes pulse {
      50% {
        background-position: 0.66em 0.66em, -0.33em -0.33em;
      }
      100% {
        background-size: 2em 2em, 1em 1em;
        background-position: -1.5em -1.5em, -1em -1em;
      }
    }
    .vb:before, .vb:after {
      box-sizing: border-box;
    }
    .vb {
      position: relative;
      width: 158px;
      color: #43cfff;
      height: 40px;
      line-height: 42px;
      border: 2px solid rgb(8, 44, 77);
      border-radius: 14px;
      text-transform: uppercase;
    }
    .dot {
      content: '';
      position: absolute;
      top: 0;
      width: 32px;
      height: 100%;
      border-radius: 50%;
      transition: all 300ms ease;
      display: none;
    }
    .dot:after {
      content: '';
      position: absolute;
      top: -6px;
      height: 5px;
      width: 5px;
      background: #43cfff;
      border-radius: 50%;
      border: 4px solid #43cfff;
      box-shadow: 0 0 .7em #FFF,
      0 0 2em #43cfff;
    }
    .vb:hover .dot,
    .vb:focus .dot {
      animation: atom 2s infinite linear;
      display: block;
    }

  /*calc(160px - 36px)  按钮宽度 - dot宽度 - 边框宽度*/
    @keyframes atom {
      0% {
        transform: translateX(0) rotate(0);
      }
      30% {
        transform: translateX(calc(160px - 36px)) rotate(0);
      }
      50% {
        transform: translateX(calc(160px - 36px)) rotate(180deg);
      }
      80% {
        transform: translateX(0) rotate(180deg);
      }
      100% {
        transform: translateX(0) rotate(360deg);
      }
    }
  }
  .left-border {
    position: absolute;
    left: 0;
    top: 65px;
    // 头部下面样式
    .border-line {
      display: inline-block;
      width: 230px;
      height: 3px;
      background: rgb(8, 44, 77);
    }
    .border-line-one {
      margin-left: 5px;
      display: inline-block;
      width: 10px;
      height: 3px;
      background: rgb(8, 44, 77);
    }
  }
  .right-border {
    position: absolute;
    right: 0;
    top: 65px;
    // 头部下面样式
    .border-line {
      display: inline-block;
      width: 230px;
      height: 3px;
      background: rgb(8, 44, 77);
    }
    .border-line-one {
      margin-right: 5px;
      display: inline-block;
      width: 10px;
      height: 3px;
      background: rgb(8, 44, 77);
    }
  }
}
</style>
