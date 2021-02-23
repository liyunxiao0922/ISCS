<template>
  <div class="main_drawingBox">
    <div class="background_imgBox">
      <img src="./assets/img/tunnelBackground.png" alt="" />
    </div>
    <div class="mainContext">
      <div class="mainOptionBox">
        <!-- 信号灯 -->
        <div class="itemBox signalLampUpBox">
          <div
            class="signalLampItem"
            v-for="item in devObj.signalLamp.upList"
            :key="item.deviceId"
          >
            <img :src="item.imageAddress" alt="信号灯icon丢了" />
          </div>
        </div>
        <div class="itemBox signalLampDownBox">
          <div
            class="signalLampItem"
            v-for="item in devObj.signalLamp.downList"
            :key="item.deviceId"
          >
            <img :src="item.imageAddress" alt="信号灯icon丢了" />
          </div>
        </div>
        <!-- 隧道风机 -->
        <div class="itemBox tunnelFanUpBox">
          <div
            class="tunnelFanItem"
            v-for="item in devObj.tunnelFan.upList"
            :key="item.deviceId"
          >
            <img :src="item.imageAddress" alt="隧道风机icon丢了" />
          </div>
        </div>
        <div class="itemBox tunnelFanDownBox">
          <div
            class="tunnelFanItem"
            v-for="item in devObj.tunnelFan.downList"
            :key="item.deviceId"
          >
            <img :src="item.imageAddress" alt="隧道风机icon丢了" />
          </div>
        </div>
        <!-- 灯光 -->
        <div class="itemBox lightingUpBox">
          <div
            class="lightingItem"
            v-for="item in devObj.lighting.upList"
            :key="item.deviceId"
          >
            <img :src="item.imageAddress" alt="灯光icon丢了" />
          </div>
        </div>
        <div class="itemBox lightingDownBox">
          <div
            class="lightingItem"
            v-for="item in devObj.lighting.downList"
            :key="item.deviceId"
          >
            <img :src="item.imageAddress" alt="灯光icon丢了" />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    tunnelInfo: {
      type: Object,
      required: true,
    },
    devObj: {
      type: Object,
      required: true,
    },
  },
  data() {
    return {};
  },
  mounted() {
    console.log(this.devObj);
  },
  methods: {},
};
</script>

<style lang="scss" scoped>
.main_drawingBox {
  width: 100%;
  height: 100%;
  position: relative;
  .background_imgBox {
    width: 100%;
    height: 100%;
    position: absolute;
    left: 0;
    top: 0;
    z-index: -1;
    & > img {
      width: 100%;
      height: 100%;
    }
  }

  .mainContext {
    width: calc(100% - 300px);
    height: 100%;
    position: absolute;
    left: 200px;
    .mainOptionBox {
      width: 100%;
      height: 600px;
      position: relative;
      .itemBox {
        position: absolute;
        left: 0;
        width: 100%;
        display: flex;
        justify-content: space-around;
        // 信号灯
        .signalLampItem {
          width: 36px;
          height: 20px;
          img {
            width: 100%;
            height: 100%;
          }
        }
        &.signalLampUpBox {
          top: 98px;
        }
        &.signalLampDownBox {
          top: 398px;
          .signalLampItem {
            transform: rotate(180deg);
          }
        }
        // 隧道风机
        &.tunnelFanUpBox {
          top: 120px;
        }
        &.tunnelFanDownBox {
          top: 454px;
        }
        .tunnelFanItem {
          width: 23px;
          height: 23px;
          img {
            width: 100%;
            height: 100%;
          }
        }
        // 灯光
        &.lightingUpBox {
          top: 230px;
        }
        &.lightingDownBox {
          top: 354px;
        }
        .lightingItem {
          width: 40px;
          height: 15px;
          img {
            width: 100%;
            height: 100%;
          }
        }
      }
    }
  }
}
</style>