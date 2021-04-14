<template>
  <div class="road-inspection-container">
    <mapGis :param="param" :mapData="mapData" v-if="mapData && mapData.highWayDot" :flag="flag" @handleChange="handleChange"></mapGis>
    <main>
     <!-- <div class="content-wrapper" ref="content" v-if="flag">
        <tempart @selectChange="selectChange" @handlePos="handlePos" :allData="allData"></tempart>
      </div> -->
      <div class="content-wrapper" v-for="(item, ind) in arr" :key="item" ref="content" v-if="flag">
        <tempart :value="item" :ind="ind" @selectChange="selectChange" @handlePos="handlePos" :allData="allData"></tempart>
      </div>
      <!-- <div class="content-wrapper" >
        <tempart :value="arr" @selectChange="selectChange" draggable="false"></tempart>
      </div> -->
      <el-dialog
        @open="handleOpen"
        :visible.sync="dialogVisible"
        width="30%"
        :modal="false"
        :lock-scroll="false"
        :close-on-click-modal="false"
        :before-close="handleClose">
        <video muted width="99%" height="100%" autoplay="autoplay" loop :src="videoUrl">
        </video>
      </el-dialog>
    </main>
  </div>
</template>

<script>
// import faceRecognition from '../../pages/faceRecognition/index';
// import securityCheck from '../../pages/security/index';
// import droneInterception from '../../pages/drone/index';
import tempart from '../../pages/tempart/index';
import mapGis from '../../pages/drone/droneInterception';
import { Login, equipMaintain, getPoints, equipChange } from '../../common/api/api';
import Cookies from 'js-cookie';
import eventBus from '../../common/Bus';
// import centerPart from '../../pages/center/index';
export default {
  data () {
    return {
      arr: [],
      param: {},
      allData: {},
      mapData: {},
      videoUrl: '',
      flag: true,
      dialogVisible: false
    };
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
    this.Login();
    this.getPoints();
    this.initData();
    eventBus.$on('status', status => {
      this.flag = status;
    });
    eventBus.$on('url', data => {
      this.handleOpen(data);
      this.videoUrl = data.url;
      console.log(this.videoUrl);
      this.dialogVisible = data.flag;
    });
  },
  methods: {
    async Login () {
      let param = {
        username: 'api',
        password: 'hollysys',
        rememberMe: true
      };
      let res = await Login(param);
      if (res && res.code === 0) {
        // let token = res.token.split('rememberMe=')[1];
        Cookies.set('token', res.token);
      }
    },
    async initData () {
      let res = await equipMaintain();
      if (res && res.code === 0) {
        this.allData = res.data;
        this.arr = res.data.currentType;
      }
    },
    async getPoints () {
      let res = await getPoints();
      if (res && res.code === 0) {
        this.mapData = res.data.highWayData;
      }
    },
    async selectChange (val, ind) {
      console.log(val);
      let param = {
        afterName: val,
        index: ind
      };
      let res = await equipChange(param);
      if (res && res.code === 0) {
        this.initData();
      }
    },
    handlePos (param) {
      console.log(param);
      this.param = param;
      if (!param.collapse) {
        if ([0, 1, 2].includes(param.ind)) {
          this.$refs.content[param.ind].style = 'width: 10px;';
        } else if ([3, 4, 5].includes(param.ind)) {
          this.$refs.content[param.ind].style = 'height: 10px;border-left: none; border-bottom: 1px solid rgb(88, 207, 255)';
        } else if ([6, 7, 8].includes(param.ind)) {
          this.$refs.content[param.ind].style = 'width: 10px;border-left: none; border-right: 1px solid rgb(88, 207, 255)';
        }
      } else {
        if ([0, 1, 2].includes(param.ind)) {
          this.$refs.content[param.ind].style = 'display: block;width: 375px;';
        } else if ([3, 4, 5].includes(param.ind)) {
          this.$refs.content[param.ind].style = 'display: block;height: 326px;';
        } else if ([6, 7, 8].includes(param.ind)) {
          this.$refs.content[param.ind].style = 'display: block; width: 375px;';
        }
      }
    },
    handleClose () {
      this.dialogVisible = false;
    },
    handleOpen () {
      console.log(this.videoUrl);
    },
    handleChange (val) {
      if (val) {
        this.getPoints();
      }
    }
  },
  components: {
    // faceRecognition,
    // securityCheck,
    // droneInterception,
    mapGis,
    tempart
    // centerPart
  }
};
</script>

<style lang="scss" scoped>
/deep/ .el-dialog__header,/deep/ .el-dialog__body {
  background: rgba(28,38,65,.9);
}
/deep/ .el-dialog__wrapper {
  z-index: 100!important;
}
.road-inspection-container {
  position: relative;
  width: 100%;
  height: 100%;
  main {
    width: 100%;
    height: 100%;
    font-size: 0;
    display: flex;
    justify-content: space-around;
    // .left-part {
    //   position: absolute;
    //   width: 360px;
    //   height: 100%;
    //   left: 10px;
    //   .content-wrapper {
    //     width: 100%;
    //     // cursor: move;
    //     position: relative;
    //     margin-bottom: 20px;;
    //   }
    // }
    // .center-part {
    //   height: 315px;
    //   display: flex;
    //   position: absolute;
    //   bottom: 15px;;
    //   .content-wrapper {
    //     position: relative;
    //     margin-left: 20px;
    //     &:first-child {
    //       margin-left: 0;
    //     }
    //   }
    // }
    // .right-part {
    //   position: absolute;
    //   width: 360px;
    //   height: 100%;
    //   right: 10px;
    //   .content-wrapper {
    //     width: 100%;
    //     // cursor: move;
    //     position: relative;
    //     margin-bottom: 20px;;
    //   }
    // }
    .content-wrapper {
      // cursor: move;
      width: 375px;
      height: 326.25px;
      position: absolute;
      margin-top: 30px;
      z-index: 999;
      // border-left: 1px solid rgb(88, 207, 255);
      &:nth-child(1) {
        position: absolute;
        left: 10px;
        top: 45px;
      }
      &:nth-child(2) {
        position: absolute;
        left: 10px;
        top: 380px;
      }
      &:nth-child(3) {
        position: absolute;
        left: 10px;
        top: 715px;
      }
      &:nth-child(4) {
        position: absolute;
        left: 390px;
        bottom: 9px;
      }
      &:nth-child(5) {
        position: absolute;
        left: 770px;
        bottom: 9px;
      }
      &:nth-child(6) {
        position: absolute;
        left: 1152px;
        bottom: 9px;
      }
      &:nth-child(7) {
        position: absolute;
        top: 45px;
        right: 10px;
      }
      &:nth-child(8) {
        position: absolute;
        right: 10px;
        top: 380px;
      }
      &:nth-child(9) {
        position: absolute;
        right: 10px;
        top: 715px;
      }
    }
  }
}
.list-enter-active, .list-leave-active {
  transition: all 1s;
}
.list-enter, .list-leave-to
/* .list-leave-active for below version 2.1.8 */ {
  opacity: 0;
  // transform: translateY(10px);
  // transform: translateX(10px);
}
</style>
