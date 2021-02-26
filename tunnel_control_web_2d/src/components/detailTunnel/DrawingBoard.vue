<template>
  <div class="main_drawingBox" ref="main_drawingBox">
    <div class="main_drawingContext" ref="main_drawingContext">
      <!-- 开始的隧道地面和隧道口 -->
      <div class="tunnelBackgroundBorder_imgBox">
        <img src="./assets/img/tunnelBackgroundBorder.png" alt="" />
      </div>
      <div class="tunnelEntrance entrance">
        <img src="./assets/img/entrance.png" alt="隧道口icon丢失了" />
      </div>
      <!-- 隧道主体 -->
      <div class="mainContext" ref="mainContext">
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
              <img :src="item.imageAddress" alt="车道指示灯icon丢了" />
            </div>
          </div>
          <div class="itemBox laneIndicatorDownBox">
            <div
              class="laneIndicatorItem"
              v-for="(item, index) in devObj.laneIndicator.downList"
              :key="item.deviceId"
              :style="{ left: returnLeftValue(devObj.laneIndicator, index) }"
            >
              <img :src="item.imageAddress" alt="车道指示灯icon丢了" />
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
              <img :src="item.imageAddress" alt="隧道风机icon丢了" />
            </div>
          </div>
          <div class="itemBox tunnelFanDownBox">
            <div
              class="tunnelFanItem"
              v-for="(item, index) in devObj.tunnelFan.downList"
              :key="item.deviceId"
              :style="{ left: returnLeftValue(devObj.tunnelFan, index) }"
            >
              <img :src="item.imageAddress" alt="隧道风机icon丢了" />
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
              <img :src="item.imageAddress" alt="灯光icon丢了" />
            </div>
          </div>
          <div class="itemBox lightingDownBox">
            <div
              class="lightingItem"
              v-for="(item, index) in devObj.lighting.downList"
              :key="item.deviceId"
              :style="{ left: returnLeftValue(devObj.lighting, index) }"
            >
              <img :src="item.imageAddress" alt="灯光icon丢了" />
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
              <img :src="item.imageAddress" alt="信号灯icon丢了" />
            </div>
          </div>
          <div class="itemBox signalLampDownBox">
            <div
              class="signalLampItem"
              v-for="(item, index) in devObj.signalLamp.downList"
              :key="item.deviceId"
              :style="{ left: returnLeftValue(devObj.signalLamp, index) }"
            >
              <img :src="item.imageAddress" alt="信号灯icon丢了" />
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
              <img :src="item.imageAddress" alt="亮度检测器icon丢了" />
              <p class="brightnessDetectorItemContext">
                <span>0cd/m²</span>
              </p>
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
              <img :src="item.imageAddress" alt="亮度检测器icon丢了" />
              <p class="brightnessDetectorItemContext">
                <span>0cd/m²</span>
              </p>
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
              <img :src="item.imageAddress" alt="照度检测器icon丢了" />
              <p class="illuminanceDetectorItemContext">
                <span>Oppm</span><br />
                <span>Okm-1</span>
              </p>
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
              <img :src="item.imageAddress" alt="照度检测器icon丢了" />
              <p class="illuminanceDetectorItemContext">
                <span>Oppm</span><br />
                <span>Okm-1</span>
              </p>
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
              <img :src="item.imageAddress" alt="风速风向仪icon丢了" />
              <p class="anemoclinographItemContext">
                <span>20米/秒</span>
              </p>
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
              <img :src="item.imageAddress" alt="风速风向仪icon丢了" />
              <p class="anemoclinographItemContext">
                <span>20米/秒</span>
              </p>
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
              <img :src="item.imageAddress" alt="信号灯icon丢了" />
            </div>
          </div>
          <div class="itemBox vehicleDetectorDownBox">
            <div
              class="vehicleDetectorItem"
              v-for="(item, index) in devObj.vehicleDetector.downList"
              :key="item.deviceId"
              :style="{ left: returnLeftValue(devObj.vehicleDetector, index) }"
            >
              <img :src="item.imageAddress" alt="信号灯icon丢了" />
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
          <!-- 机器人 -->
          <div class="itemBox robotUpBox">
            <div class="robotItem" ref="robotItemUp">
              <img src="./assets/img/WechatIMG12.png" alt="机器人icon丢了" />
            </div>
          </div>
          <div class="itemBox robotDownBox">
            <div class="robotItem" ref="robotItemBottom">
              <img src="./assets/img/WechatIMG12.png" alt="机器人icon丢了" />
            </div>
          </div>
          <!-- 车行通道 -->
          <div class="itemBox passagewayBox">
            <div
              class="passagewayItem"
              v-for="(item, index) in devObj.passageway.upList"
              :key="item.deviceId"
              :style="{ left: returnLeftValue(devObj.passageway, index) }"
            >
              <img :src="item.imageAddress" alt="车行隧道icon丢了" />
            </div>
          </div>
          <!-- 临时停车 -->
          <div class="itemBox parkingUpBox">
            <div class="parkingItem" :style="{ left: parkingLeft(1) }">
              <img src="./assets/img/parkingUp.png" alt="机器人icon丢了" />
            </div>
            <div class="parkingItem" :style="{ left: parkingLeft(2) }">
              <img src="./assets/img/parkingUp.png" alt="机器人icon丢了" />
            </div>
          </div>
          <div class="itemBox parkingDownBox">
            <div class="parkingItem" :style="{ left: parkingLeft(1) }">
              <img src="./assets/img/parkingDown.png" alt="机器人icon丢了" />
            </div>
            <div class="parkingItem" :style="{ left: parkingLeft(2) }">
              <img src="./assets/img/parkingDown.png" alt="机器人icon丢了" />
            </div>
          </div>
        </div>
      </div>
      <!-- 结尾的地面和隧道口 -->
      <div class="tunnelBackgroundBorder_imgBox">
        <img src="./assets/img/tunnelBackgroundBorder.png" alt="" />
      </div>
      <div class="tunnelEntrance export">
        <img src="./assets/img/export.png" alt="隧道结尾icon丢失了" />
      </div>
      <div class="scaleTop">
        <Scale
          v-if="scaleData"
          :scaleData="scaleData"
          :devRatio="devRatio"
          type="top"
        />
      </div>
      <div class="scaleBox">
        <Scale
          v-if="scaleData"
          :scaleData="scaleData"
          :devRatio="devRatio"
          type="bottom"
        />
      </div>
    </div>

    <!-- 桩号滚动条 -->
    <div class="scrollBox" ref="scrollBox">
      <div class="mask">
        <div class="robotBottom" ref="robotBottom"></div>
      </div>
      <div class="scrollBtn" ref="scrollBtn">
        <div class="scrollBtnMask"></div>
      </div>
      <div class="robotTop" ref="robotTop"></div>
    </div>
  </div>
</template>

<script>
import Scale from "./Scale";
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
    scaleData: {
      type: Array,
      defqult: [],
    },
  },
  components: {
    Scale,
  },
  data() {
    return {
      robotIntelTop: null, // 上边的机器人
      robotIntelBottom: null, // 下边的机器人
      devRatio: 17, // 默认比例  1米===**px
      tunnelLengthPx: 0, // 计算出来的隧道在页面长度
    };
  },
  mounted() {
    this.tunnelLengthPx = this.tunnelInfo.tunnelLength * this.devRatio;
    this.robotLeft();
    this.robotRight();
    this.scrollFn();
  },
  destroyed() {
    clearInterval(this.robotIntelTop);
    clearInterval(this.robotIntelBottom);
  },
  methods: {
    /**
     * 生成设备在地图的left位置
     * @param dev  设备数据
     * @param index  循环设备的索引
     *
     */
    returnLeftValue(dev, index) {
      let start = this.twoStakeCompare(this.currentStakeNum, dev.startPosition);
      if (start || start === 0) {
        return start + dev.space * this.devRatio * index + "px";
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
        // 如果设备桩号等于地图开始桩号，判断他们的后边值（+***），如果设备小于地图就返回，如果等于，那么返回0，否则设备的减去地图的
      } else if (currentK == startK) {
        if (currentV < startV) {
          return false;
        } else if (currentV == startV) {
          return 0;
        } else {
          return (currentV - startV) * this.devRatio;
        }
        // 如果设备桩号大于地图开始桩号，判断一下是不是紧邻的桩号，如果不是，直接隐藏，如果是，继续判断，桩号后边的值是否在100以内，如果是，就通过下边的表达式计算，否则直接隐藏
      } else {
        if (currentK === startK + 1) {
          if (1000 - startV < 100 || currentV - 100 < 0) {
            return (1000 - startV + currentV) * this.devRatio;
          } else {
            return false;
          }
        } else {
          return false;
        }
      }
    },
    /**
     * 上边机器人移动
     */
    robotLeft() {
      let str = 0,
        direction = 1,
        robotWidth = this.$refs.scrollBox.offsetWidth,
        shadowleft = 0,
        robotTop = this.$refs.robotTop,
        robotItemUp = this.$refs.robotItemUp;
      let shadowDistance = robotWidth / (this.tunnelLengthPx / 10);

      this.robotIntelTop = setInterval(() => {
        if (direction === 1) {
          str = str + 10;
          shadowleft = shadowleft + shadowDistance;
        } else {
          str = str - 10;
          shadowleft = shadowleft - shadowDistance;
        }
        if (str > this.tunnelLengthPx) {
          direction = 2;
        } else if (str <= 0) {
          direction = 1;
        }
        if (robotItemUp) {
          robotItemUp.style.left = str + "px";
        }
        if (robotTop) {
          robotTop.style.left = shadowleft + "px";
        }
      }, 60);
    },
    /**
     * 下边机器人移动
     */
    robotRight() {
      let str = 0,
        direction = 1,
        robotWidth = this.$refs.scrollBox.offsetWidth,
        shadowleft = 0,
        robotItemBottom = this.$refs.robotItemBottom,
        robotBottom = this.$refs.robotBottom;
      let shadowDistance = robotWidth / (this.tunnelLengthPx / 10);

      this.robotIntelBottom = setInterval(() => {
        if (direction === 1) {
          str = str + 10;
          shadowleft = shadowleft + shadowDistance;
        } else {
          str = str - 10;
          shadowleft = shadowleft - shadowDistance;
        }
        if (str > this.tunnelLengthPx) {
          direction = 2;
        } else if (str <= 0) {
          direction = 1;
        }
        if (robotItemBottom) {
          robotItemBottom.style.right = str + "px";
        }
        if (robotBottom) {
          robotBottom.style.right = shadowleft + "px";
        }
      }, 60);
    },
    /**
     * 滚动条实现
     */
    scrollFn() {
      let main_drawingBox = this.$refs.main_drawingBox,
        main_drawingContext = this.$refs.main_drawingContext,
        mainContext = this.$refs.mainContext,
        scrollBox = this.$refs.scrollBox,
        scrollBtn = this.$refs.scrollBtn;
      let tunnelLength = this.tunnelInfo.tunnelLength - 0;
      let mainContextWidth = tunnelLength * this.devRatio;
      let main_drawingContextWidth = mainContextWidth + 440;
      main_drawingContext.style.width = main_drawingContextWidth + "px";
      mainContext.style.width = mainContextWidth + "px";
      let width =
        parseInt(main_drawingBox.offsetWidth * scrollBox.offsetWidth) /
        parseInt(main_drawingContextWidth);
      scrollBtn.style.width = width + "px";
      scrollBtn.onmousedown = function (e) {
        var cX = parseInt(e.clientX);
        var oW = parseInt(this.offsetLeft);
        var spaceX = cX - oW;
        document.onmousemove = function (e) {
          //鼠标移动时，不选中文字
          window.getSelection
            ? window.getSelection().removeAllRanges()
            : document.selection.empty();
          var nowX = e.clientX;
          var x = nowX - spaceX;
          var baWidth = parseInt(scrollBox.offsetWidth - scrollBtn.offsetWidth);

          x = x < 0 ? (x = 0) : x;
          x = x > baWidth ? baWidth : x;

          var conWidth = parseInt(
            main_drawingContextWidth - main_drawingBox.offsetWidth
          );

          var conX = (x * conWidth) / baWidth;
          scrollBtn.style.left = x + "px";
          main_drawingContext.style.marginLeft = -conX + "px";
        };
      };
      document.onmouseup = function () {
        document.onmousemove = null;
      };
    },
    /**
     * 临时停车
     */
    parkingLeft(index) {
      return this.devRatio * index * 700 + "px";
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
  height: 690px;
  overflow: hidden;
  .main_drawingContext {
    display: flex;
    width: 20440px;
    height: 100%;
    position: relative;
    .tunnelBackgroundBorder_imgBox {
      width: 220px;
      height: 600px;
      margin-top: 25px;
      flex-shrink: 0;
      & > img {
        width: 100%;
        height: 100%;
      }
    }
    .mainContext {
      width: 20000px;
      margin-top: 25px;
      height: 600px;
      position: relative;
      flex-shrink: 0;
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
          // 车行通道
          &.passagewayBox {
            top: 259px;
            .passagewayItem {
              width: 50px;
              height: 80px;
              position: absolute;
              top: 0;
              img {
                width: 100%;
                height: 100%;
              }
            }
          }
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
            width: 50px;
            height: 20px;
            position: absolute;
            top: 0;
            .brightnessDetectorItemContext {
              display: inline-block;
              color: #fff;
              font-size: 10px;
              margin: 0;
              width: 30px;
              margin-left: 5px;
              line-height: 20px;
              vertical-align: middle;
            }
            img {
              width: 15px;
              height: 100%;
              vertical-align: middle;
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
            width: 60px;
            height: 20px;
            position: absolute;
            top: 0;
            .illuminanceDetectorItemContext {
              display: inline-block;
              color: #fff;
              font-size: 10px;
              margin: 0;
              width: 40px;
              margin-left: 5px;
              line-height: 1;
              vertical-align: middle;
            }
            img {
              width: 15px;
              height: 100%;
              vertical-align: middle;
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
            width: 70px;
            height: 20px;
            position: absolute;
            top: 0;
            .anemoclinographItemContext {
              display: inline-block;
              color: #fff;
              font-size: 10px;
              margin: 0;
              width: 50px;
              margin-left: 5px;
              line-height: 20px;
              vertical-align: middle;
            }
            img {
              width: 15px;
              height: 100%;
              vertical-align: middle;
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
            width: 25px;
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
          // 机器人
          .robotItem {
            width: 120px;
            height: 30px;
            position: absolute;
            top: 0;
            img {
              width: 100%;
              height: 100%;
            }
          }
          &.robotUpBox {
            top: 68px;
          }
          &.robotDownBox {
            bottom: 100px;
          }
          // 临时停车
          .parkingItem {
            width: 246px;
            height: 50px;
            position: absolute;
            top: 0;
            img {
              width: 100%;
              height: 100%;
            }
          }
          &.parkingUpBox {
            top: 0;
          }
          &.parkingDownBox {
            bottom: 52px;
          }
        }
      }
    }
    .tunnelEntrance {
      position: absolute;
      top: 60px;
      height: 529px;
      width: 350px;
      opacity: 0.5;
      img {
        width: 100%;
        height: 100%;
      }
      &.entrance {
        left: 0;
      }
      &.export {
        right: 0;
      }
    }
    .scaleBox {
      position: absolute;
      bottom: 40px;
      padding: 0 220px;
      height: 50px;
      width: 100%;
    }
    .scaleTop {
      position: absolute;
      top: 0px;
      padding: 0 220px;
      height: 50px;
      width: 100%;
    }
  }
  .scrollBox {
    height: 20px;
    width: 100%;
    position: absolute;
    bottom: 10px;
    left: 0;
    border-radius: 20px;
    background: linear-gradient(to right, #515254 50%, #6c7073 50%);
    background-size: 30px 100%;
    .mask {
      width: 100%;
      height: 10px;
      background-color: rgba(255, 255, 255, 0.2);
      position: absolute;
      left: 0;
      bottom: 0;
      border-bottom-right-radius: 10px;
      border-bottom-left-radius: 10px;
      .robotBottom {
        position: absolute;
        right: 0;
        top: 0;
        width: 10px;
        height: 10px;
        background-color: #f00;
        border-radius: 50%;
        z-index: 1;
      }
    }
    .robotTop {
      position: absolute;
      left: 0;
      top: 0;
      width: 10px;
      height: 10px;
      background-color: #f00;
      border-radius: 50%;
      z-index: 1;
    }
    .scrollBtn {
      width: 40px;
      height: 40px;
      background-color: rgba(122, 122, 122, 0.2);
      position: absolute;
      left: 0;
      top: -10px;
      border-radius: 3px;
      cursor: pointer;
      .scrollBtnMask {
        width: 100%;
        height: 20px;
        margin: 10px 0;
        background-color: #3d3f3e;
      }
    }
  }
}
</style>