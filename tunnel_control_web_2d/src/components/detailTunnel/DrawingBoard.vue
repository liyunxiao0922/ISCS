<template>
  <div class="main_drawingBox">
    <div class="tunnelBackgroundBorder_imgBox leftBorder">
      <img src="./assets/img/tunnelBackgroundBorder.png" alt="" />
    </div>
    <div class="tunnelBackgroundBorder_imgBox rightBorder">
      <img src="./assets/img/tunnelBackgroundBorder.png" alt="" />
    </div>
    <div class="mainContext">
      <div class="background_imgBox">
        <img src="./assets/img/tunnelBackground.png" alt="" />
      </div>
      <div class="mainOptionBox">
        <!-- 车道指示灯灯 -->
        <div class="itemBox laneIndicatorUpBox">
          <div
            class="laneIndicatorItem"
            v-for="(item, index) in devObj.laneIndicator.upList"
            :key="item.deviceId"
            :style="{ left: returnLeftValue(devObj.laneIndicator, index) }"
          >
            <img
              :src="item.imageAddress"
              alt="车道指示灯icon丢了"
              v-if="devObj.laneIndicator.space * index <= 100"
            />
          </div>
        </div>
        <div class="itemBox laneIndicatorDownBox">
          <div
            class="laneIndicatorItem"
            v-for="(item, index) in devObj.laneIndicator.downList"
            :key="item.deviceId"
            :style="{ left: returnLeftValue(devObj.laneIndicator, index) }"
          >
            <img
              :src="item.imageAddress"
              alt="车道指示灯icon丢了"
              v-if="devObj.laneIndicator.space * index <= 100"
            />
          </div>
        </div>
        <!-- 隧道风机 -->
        <div class="itemBox tunnelFanUpBox">
          <div
            class="tunnelFanItem"
            v-for="(item, index) in devObj.tunnelFan.upList"
            :key="item.deviceId"
            :style="{ left: returnLeftValue(devObj.tunnelFan, index) }"
          >
            <img
              :src="item.imageAddress"
              alt="隧道风机icon丢了"
              v-if="devObj.tunnelFan.space * index <= 100"
            />
          </div>
        </div>
        <div class="itemBox tunnelFanDownBox">
          <div
            class="tunnelFanItem"
            v-for="(item, index) in devObj.tunnelFan.downList"
            :key="item.deviceId"
            :style="{ left: returnLeftValue(devObj.tunnelFan, index) }"
          >
            <img
              :src="item.imageAddress"
              alt="隧道风机icon丢了"
              v-if="devObj.tunnelFan.space * index <= 100"
            />
          </div>
        </div>
        <!-- 灯光 -->
        <div class="itemBox lightingUpBox">
          <div
            class="lightingItem"
            v-for="(item, index) in devObj.lighting.upList"
            :key="item.deviceId"
            :style="{ left: returnLeftValue(devObj.lighting, index) }"
          >
            <img
              :src="item.imageAddress"
              alt="灯光icon丢了"
              v-if="devObj.lighting.space * index <= 100"
            />
          </div>
        </div>
        <div class="itemBox lightingDownBox">
          <div
            class="lightingItem"
            v-for="(item, index) in devObj.lighting.downList"
            :key="item.deviceId"
            :style="{ left: returnLeftValue(devObj.lighting, index) }"
          >
            <img
              :src="item.imageAddress"
              alt="灯光icon丢了"
              v-if="devObj.lighting.space * index <= 100"
            />
          </div>
        </div>
        <!-- 信号灯 -->
        <div class="itemBox signalLampUpBox">
          <div
            class="signalLampItem"
            v-for="(item, index) in devObj.signalLamp.upList"
            :key="item.deviceId"
            :style="{ left: returnLeftValue(devObj.signalLamp, index) }"
          >
            <img
              :src="item.imageAddress"
              alt="信号灯icon丢了"
              v-if="devObj.signalLamp.space * index <= 100"
            />
          </div>
        </div>
        <div class="itemBox signalLampDownBox">
          <div
            class="signalLampItem"
            v-for="(item, index) in devObj.signalLamp.downList"
            :key="item.deviceId"
            :style="{ left: returnLeftValue(devObj.signalLamp, index) }"
          >
            <img
              :src="item.imageAddress"
              alt="信号灯icon丢了"
              v-if="devObj.signalLamp.space * index <= 100"
            />
          </div>
        </div>
        <!-- 亮度检测器 -->
        <div class="itemBox brightnessDetectorUpBox">
          <div
            class="brightnessDetectorItem"
            v-for="(item, index) in devObj.brightnessDetector.upList"
            :key="item.deviceId"
            :style="{
              left: returnLeftValue(devObj.brightnessDetector, index),
            }"
          >
            <img
              :src="item.imageAddress"
              alt="亮度检测器icon丢了"
              v-if="devObj.brightnessDetector.space * index <= 100"
            />
          </div>
        </div>
        <div class="itemBox brightnessDetectorDownBox">
          <div
            class="brightnessDetectorItem"
            v-for="(item, index) in devObj.brightnessDetector.downList"
            :key="item.deviceId"
            :style="{
              left: returnLeftValue(devObj.brightnessDetector, index),
            }"
          >
            <img
              :src="item.imageAddress"
              alt="亮度检测器icon丢了"
              v-if="devObj.brightnessDetector.space * index <= 100"
            />
          </div>
        </div>
        <!-- 照度检测器 -->
        <div class="itemBox illuminanceDetectorUpBox">
          <div
            class="illuminanceDetectorItem"
            v-for="(item, index) in devObj.illuminanceDetector.upList"
            :key="item.deviceId"
            :style="{
              left: returnLeftValue(devObj.illuminanceDetector, index),
            }"
          >
            <img
              :src="item.imageAddress"
              alt="照度检测器icon丢了"
              v-if="devObj.illuminanceDetector.space * index <= 100"
            />
          </div>
        </div>
        <div class="itemBox illuminanceDetectorDownBox">
          <div
            class="illuminanceDetectorItem"
            v-for="(item, index) in devObj.illuminanceDetector.downList"
            :key="item.deviceId"
            :style="{
              left: returnLeftValue(devObj.illuminanceDetector, index),
            }"
          >
            <img
              :src="item.imageAddress"
              alt="照度检测器icon丢了"
              v-if="devObj.illuminanceDetector.space * index <= 100"
            />
          </div>
        </div>
        <!-- 风速风向仪 -->
        <div class="itemBox anemoclinographUpBox">
          <div
            class="anemoclinographItem"
            v-for="(item, index) in devObj.anemoclinograph.upList"
            :key="item.deviceId"
            :style="{
              left: returnLeftValue(devObj.anemoclinograph, index),
            }"
          >
            <img
              :src="item.imageAddress"
              alt="风速风向仪icon丢了"
              v-if="devObj.anemoclinograph.space * index <= 100"
            />
          </div>
        </div>
        <div class="itemBox anemoclinographDownBox">
          <div
            class="anemoclinographItem"
            v-for="(item, index) in devObj.anemoclinograph.downList"
            :key="item.deviceId"
            :style="{
              left: returnLeftValue(devObj.anemoclinograph, index),
            }"
          >
            <img
              :src="item.imageAddress"
              alt="风速风向仪icon丢了"
              v-if="devObj.anemoclinograph.space * index <= 100"
            />
          </div>
        </div>
        <!-- 车辆检测器 -->
        <div class="itemBox vehicleDetectorUpBox">
          <div
            class="vehicleDetectorItem"
            v-for="(item, index) in devObj.vehicleDetector.upList"
            :key="item.deviceId"
            :style="{ left: returnLeftValue(devObj.vehicleDetector, index) }"
          >
            <img
              :src="item.imageAddress"
              alt="信号灯icon丢了"
              v-if="devObj.vehicleDetector.space * index <= 100"
            />
          </div>
        </div>
        <div class="itemBox vehicleDetectorDownBox">
          <div
            class="vehicleDetectorItem"
            v-for="(item, index) in devObj.vehicleDetector.downList"
            :key="item.deviceId"
            :style="{ left: returnLeftValue(devObj.vehicleDetector, index) }"
          >
            <img
              :src="item.imageAddress"
              alt="信号灯icon丢了"
              v-if="devObj.vehicleDetector.space * index <= 100"
            />
          </div>
        </div>
        <!-- 情报板 -->
        <div class="itemBox informationBoardUpBox">
          <div
            class="informationBoardItem"
            v-for="(item, index) in devObj.informationBoard.upList"
            :key="item.deviceId"
            :style="{ left: returnLeftValue(devObj.informationBoard, index) }"
          >
            <div class="informationContext">限速80，请开车灯</div>
          </div>
        </div>
        <div class="itemBox informationBoardDownBox">
          <div
            class="informationBoardItem"
            v-for="(item, index) in devObj.informationBoard.downList"
            :key="item.deviceId"
            :style="{ left: returnLeftValue(devObj.informationBoard, index) }"
          >
            <div class="informationContext">限速80，请开车灯</div>
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
    currentStakeNum: {
      type: String,
      default: "k36+000",
    },
  },
  data() {
    return {};
  },
  mounted() {
    // console.log(this.currentStakeNum);
  },
  methods: {
    /**
     * 生成设备在地图的left位置
     * @param dev  设备数据
     * @param index  循环设备的索引
     */
    returnLeftValue(dev, index) {
      let start = this.twoStakeCompare(this.currentStakeNum, dev.startPosition);
      if (start || start === 0) {
        return start + dev.space * index + "%";
      } else {
        return "-100%";
      }
    },
    /***
     * 获取到设备在地图的起始位置
     * @param start 地图开始桩号
     * @param current 设备当前桩号
     */
    twoStakeCompare(start, current) {
      let startStr = start.replace("k", ""),
        currentStr = current.replace("k", "");
      let startArr = startStr.split("+"),
        currentArr = currentStr.split("+");
      let startK = startArr[0] - 0,
        startV = startArr[1] - 0 ?? 0;
      let currentK = currentArr[0] - 0,
        currentV = currentArr[1] - 0 ?? 0;
      // 如果设备桩号小于地图开始桩号，直接隐藏
      if (currentK < startK) {
        return false;
        // 如果设备桩号等于地图开始桩号，判断他们的后边值（+***），如果设备的小于地图的返回，如果等于，那么返回0，否则设备的减去地图的
      } else if (currentK == startK) {
        if (currentV < startV) {
          return false;
        } else if (currentV == startV) {
          return 0;
        } else {
          return currentV - startV;
        }
        // 如果设备桩号大于地图开始桩号，判断一下是不是紧邻的桩号，如果不是，直接隐藏，如果是，继续判断，桩号后边的值是否在100以内，如果是，就通过下边的表达式计算，否则直接隐藏
      } else {
        if (currentK === startK + 1) {
          if (1000 - startV < 100 || currentV - 100 < 0) {
            return 1000 - startV + currentV;
          } else {
            return false;
          }
        } else {
          return false;
        }
      }
    },
  },
};
</script>

<style lang="scss" scoped>
@keyframes rotate {
  from {
    transform: rotate(0deg);
  }
  to {
    transform: rotate(360deg);
  }
}
.main_drawingBox {
  width: 100%;
  height: 100%;
  position: relative;
  .tunnelBackgroundBorder_imgBox {
    position: absolute;
    top: 0;
    z-index: -1;
    width: 220px;
    height: 100%;
    & > img {
      width: 100%;
      height: 100%;
    }
    &.leftBorder {
      left: 0;
    }
    &.rightBorder {
      right: 0;
    }
  }
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
    width: calc(100% - 440px);
    height: 100%;
    position: absolute;
    left: 220px;
    .mainOptionBox {
      width: 100%;
      height: 600px;
      position: relative;
      .itemBox {
        position: absolute;
        left: 0;
        width: 100%;
        // display: flex;
        // justify-content: space-around;
        // 情报板
        .informationBoardItem {
          width: 50px;
          height: 200px;
          position: absolute;
          top: 0;
          background-color: #497785;
          padding: 5px;
          .informationContext {
            width: 100%;
            height: 100%;
            background-color: #2f5b68;
            box-shadow: inset 0 0 7px 1px #152e35;
            writing-mode: tb-rl;
            text-align: center;
            line-height: 40px;
            color: #e99c21;
            font-size: 18px;
            font-weight: bold;
          }
        }
        &.informationBoardUpBox {
          top: 50px;
        }
        &.informationBoardDownBox {
          top: 350px;
        }
        // 车道指示器
        .laneIndicatorItem {
          width: 36px;
          height: 20px;
          position: absolute;
          top: 0;
          img {
            width: 100%;
            height: 100%;
          }
        }
        &.laneIndicatorUpBox {
          top: 98px;
        }
        &.laneIndicatorDownBox {
          top: 398px;
          .laneIndicatorItem {
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
          position: absolute;
          top: 0;
          animation: rotate 3s linear infinite;
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
          position: absolute;
          top: 0;
          img {
            width: 100%;
            height: 100%;
          }
        }
        // 信号灯
        .signalLampItem {
          width: 36px;
          height: 20px;
          position: absolute;
          top: 0;
          img {
            width: 100%;
            height: 100%;
          }
        }
        &.signalLampUpBox {
          top: 50px;
        }
        &.signalLampDownBox {
          top: 527px;
          .signalLampItem {
            transform: rotate(180deg);
          }
        }
        // 亮度检测器
        .brightnessDetectorItem {
          width: 36px;
          height: 20px;
          position: absolute;
          top: 0;
          img {
            width: 100%;
            height: 100%;
          }
        }
        &.brightnessDetectorUpBox {
          top: 74px;
        }
        &.brightnessDetectorDownBox {
          top: 503px;
        }
        // 照度检测器
        .illuminanceDetectorItem {
          width: 36px;
          height: 20px;
          position: absolute;
          top: 0;
          img {
            width: 100%;
            height: 100%;
          }
        }
        &.illuminanceDetectorUpBox {
          top: 74px;
        }
        &.illuminanceDetectorDownBox {
          top: 503px;
        }
        // 风速风向仪
        .anemoclinographItem {
          width: 36px;
          height: 20px;
          position: absolute;
          top: 0;
          img {
            width: 100%;
            height: 100%;
          }
        }
        &.anemoclinographUpBox {
          top: 74px;
        }
        &.anemoclinographDownBox {
          top: 503px;
        }
        // 车辆检测器
        .vehicleDetectorItem {
          width: 36px;
          height: 20px;
          position: absolute;
          top: 0;
          img {
            width: 100%;
            height: 100%;
          }
        }
        &.vehicleDetectorUpBox {
          top: 50px;
        }
        &.vehicleDetectorDownBox {
          top: 527px;
        }
      }
    }
  }
}
</style>