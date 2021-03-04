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
          <img
            v-if="tunnelInfo.tunnelLength - 0 > 500"
            src="./assets/img/tunnelBackground.png"
            alt=""
          />
          <img v-else src="./assets/img/tunnelBackgroundshort.png" alt="" />
        </div>
        <div class="mainOptionBox">
          <!-- 车道指示灯灯 -->
          <div class="itemBox laneIndicatorUpBox">
            <div
              v-for="(item, index) in devObj.laneIndicator.upList"
              :class="
                item.isSearch
                  ? 'laneIndicatorItem isSearch'
                  : 'laneIndicatorItem'
              "
              :key="item.deviceId"
              :style="{ left: returnLeftValue(devObj.laneIndicator, index) }"
            >
              <img :src="item.imageAddress" alt="车道指示灯icon丢了" />
            </div>
          </div>
          <div class="itemBox laneIndicatorDownBox">
            <div
              v-for="(item, index) in devObj.laneIndicator.downList"
              :class="
                item.isSearch
                  ? 'laneIndicatorItem isSearch'
                  : 'laneIndicatorItem'
              "
              :key="item.deviceId"
              :style="{ left: returnLeftValue(devObj.laneIndicator, index) }"
            >
              <img :src="item.imageAddress" alt="车道指示灯icon丢了" />
            </div>
          </div>
          <!-- 隧道风机 -->
          <div class="itemBox tunnelFanUpBox">
            <div
              :class="
                item.workStatus === '0019'
                  ? item.isSearch
                    ? 'tunnelFanItem stop isSearch'
                    : 'tunnelFanItem stop'
                  : item.workStatus === '0011'
                  ? item.isSearch
                    ? 'tunnelFanItem positive isSearch'
                    : 'tunnelFanItem positive'
                  : item.isSearch
                  ? 'tunnelFanItem reverse isSearch'
                  : 'tunnelFanItem reverse'
              "
              v-for="(item, index) in devObj.tunnelFan.upList"
              :key="item.deviceId"
              :style="{ left: returnLeftValue(devObj.tunnelFan, index) }"
              @click="fanClick(item)"
            >
              <img :src="item.imageAddress" alt="隧道风机icon丢了" />
            </div>
          </div>
          <div class="itemBox tunnelFanDownBox">
            <div
              :class="
                item.workStatus === '0019'
                  ? item.isSearch
                    ? 'tunnelFanItem stop isSearch'
                    : 'tunnelFanItem stop'
                  : item.workStatus === '0011'
                  ? item.isSearch
                    ? 'tunnelFanItem positive isSearch'
                    : 'tunnelFanItem positive'
                  : item.isSearch
                  ? 'tunnelFanItem reverse isSearch'
                  : 'tunnelFanItem reverse'
              "
              v-for="(item, index) in devObj.tunnelFan.downList"
              :key="item.deviceId"
              :style="{ left: returnLeftValue(devObj.tunnelFan, index) }"
              @click="fanClick(item)"
            >
              <img :src="item.imageAddress" alt="隧道风机icon丢了" />
            </div>
          </div>
          <!-- 灯光 -->
          <div class="itemBox lightingUpBox">
            <div
              v-for="(item, index) in devObj.lighting.upList"
              :class="item.isSearch ? 'lightingItem isSearch' : 'lightingItem'"
              :key="item.deviceId"
              :style="{ left: returnLeftValue(devObj.lighting, index) }"
              @click="lightingClick(item)"
            >
              <img :src="item.imageAddress" alt="灯光icon丢了" />
            </div>
          </div>
          <div class="itemBox lightingDownBox">
            <div
              v-for="(item, index) in devObj.lighting.downList"
              :class="item.isSearch ? 'lightingItem isSearch' : 'lightingItem'"
              :key="item.deviceId"
              :style="{ left: returnLeftValue(devObj.lighting, index) }"
              @click="lightingClick(item)"
            >
              <img :src="item.imageAddress" alt="灯光icon丢了" />
            </div>
          </div>
          <!-- 信号灯 -->
          <div class="itemBox signalLampUpBox">
            <div
              v-for="(item, index) in devObj.signalLamp.upList"
              :class="
                item.isSearch ? 'signalLampItem isSearch' : 'signalLampItem'
              "
              :key="item.deviceId"
              :style="{ left: returnLeftValue(devObj.signalLamp, index) }"
            >
              <img :src="item.imageAddress" alt="信号灯icon丢了" />
            </div>
          </div>
          <div class="itemBox signalLampDownBox">
            <div
              v-for="(item, index) in devObj.signalLamp.downList"
              :class="
                item.isSearch ? 'signalLampItem isSearch' : 'signalLampItem'
              "
              :key="item.deviceId"
              :style="{ left: returnLeftValue(devObj.signalLamp, index) }"
            >
              <img :src="item.imageAddress" alt="信号灯icon丢了" />
            </div>
          </div>
          <!-- 亮度检测器 -->
          <div class="itemBox brightnessDetectorUpBox">
            <div
              v-for="(item, index) in devObj.brightnessDetector.upList"
              :class="
                item.isSearch
                  ? 'brightnessDetectorItem isSearch'
                  : 'brightnessDetectorItem'
              "
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
              v-for="(item, index) in devObj.brightnessDetector.downList"
              :class="
                item.isSearch
                  ? 'brightnessDetectorItem isSearch'
                  : 'brightnessDetectorItem'
              "
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
              v-for="(item, index) in devObj.illuminanceDetector.upList"
              :class="
                item.isSearch
                  ? 'illuminanceDetectorItem isSearch'
                  : 'illuminanceDetectorItem'
              "
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
              v-for="(item, index) in devObj.illuminanceDetector.downList"
              :class="
                item.isSearch
                  ? 'illuminanceDetectorItem isSearch'
                  : 'illuminanceDetectorItem'
              "
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
              v-for="(item, index) in devObj.anemoclinograph.upList"
              :class="
                item.isSearch
                  ? 'anemoclinographItem isSearch'
                  : 'anemoclinographItem'
              "
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
              v-for="(item, index) in devObj.anemoclinograph.downList"
              :class="
                item.isSearch
                  ? 'anemoclinographItem isSearch'
                  : 'anemoclinographItem'
              "
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
              v-for="(item, index) in devObj.vehicleDetector.upList"
              :class="
                item.isSearch
                  ? 'vehicleDetectorItem isSearch'
                  : 'vehicleDetectorItem'
              "
              :key="item.deviceId"
              :style="{ left: returnLeftValue(devObj.vehicleDetector, index) }"
            >
              <img :src="item.imageAddress" alt="信号灯icon丢了" />
            </div>
          </div>
          <div class="itemBox vehicleDetectorDownBox">
            <div
              v-for="(item, index) in devObj.vehicleDetector.downList"
              :class="
                item.isSearch
                  ? 'vehicleDetectorItem isSearch'
                  : 'vehicleDetectorItem'
              "
              :key="item.deviceId"
              :style="{ left: returnLeftValue(devObj.vehicleDetector, index) }"
            >
              <img :src="item.imageAddress" alt="信号灯icon丢了" />
            </div>
          </div>
          <!-- 情报板 -->
          <div class="itemBox informationBoardUpBox">
            <div
              v-for="(item, index) in devObj.informationBoard.upList"
              :class="
                item.isSearch
                  ? 'informationBoardItem isSearch'
                  : 'informationBoardItem'
              "
              :key="item.deviceId"
              :style="{ left: returnLeftValue(devObj.informationBoard, index) }"
            >
              <div class="informationContext">限速80，请开车灯</div>
            </div>
          </div>
          <div class="itemBox informationBoardDownBox">
            <div
              v-for="(item, index) in devObj.informationBoard.downList"
              :class="
                item.isSearch
                  ? 'informationBoardItem isSearch'
                  : 'informationBoardItem'
              "
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
              v-for="(item, index) in devObj.passageway.upList"
              :class="
                item.isSearch ? 'passagewayItem isSearch' : 'passagewayItem'
              "
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
        <div class="scrollBtnMask">
          <span class="scrollContext">{{ scrollContextValue }}米</span>
        </div>
        <div class="tooltipBox">{{ scrollContextValue }}米</div>
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
    searchFromData: {
      type: Object || null || undefined,
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
      scrollContextValue: 0, // 滚动条当前所在位置对应距离
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
  watch: {
    searchFromData: {
      immediate: true,
      deep: true,
      handler(newValue) {
        /**
         * 1. 判断是否有搜索条件，有走下边的计算，没有，位置全部归0。
         * 2. 通过搜索条件给的参数，来判断对应的left值，如果left不等于-100%的话，继续走，否则全部归0。
         * 3. 判断left值是不是小于屏幕宽度，如果小于，页面就不做位移，如果大于，就位移left长度减去页面的一半长度。
         * 4. 下边的值是通过滚动条的计算方式反推的
         */
        if (newValue) {
          let left = this.returnLeftValue(
            this.devObj[newValue.key],
            newValue.index
          );
          let main_drawingBox = this.$refs.main_drawingBox,
            main_drawingContext = this.$refs.main_drawingContext,
            scrollBox = this.$refs.scrollBox,
            scrollBtn = this.$refs.scrollBtn;
          if (left != "-100%") {
            left = left.replace("px", "");
            var baWidth = parseInt(
              scrollBox.offsetWidth - scrollBtn.offsetWidth
            );
            if (left - 0 > baWidth) {
              left = left - baWidth / 2;
              let tunnelLength = this.tunnelInfo.tunnelLength - 0;
              let mainContextWidth = tunnelLength * this.devRatio;
              let main_drawingContextWidth = mainContextWidth + 440;
              var conWidth = parseInt(
                main_drawingContextWidth - main_drawingBox.offsetWidth
              );
              let x = (left * baWidth) / conWidth;

              scrollBtn.style.left = x + "px";
              main_drawingContext.style.marginLeft = -left + "px";
              this.scrollContextValue = parseInt((tunnelLength / baWidth) * x);
            } else {
              main_drawingContext.style.marginLeft = "0px";
              this.scrollContextValue = 0;
              scrollBtn.style.left = "0px";
            }
          } else {
            main_drawingContext.style.marginLeft = "0px";
            this.scrollContextValue = 0;
            scrollBtn.style.left = "0px";
            this.$message.info("该设备安装位置有误，未在本隧道展示");
          }
        } else if (newValue === undefined) {
          let main_drawingContext = this.$refs.main_drawingContext,
            scrollBtn = this.$refs.scrollBtn;
          if (main_drawingContext) {
            main_drawingContext.style.marginLeft = "0px";
            this.scrollContextValue = 0;
            scrollBtn.style.left = "0px";
          }
          this.$message.info("没有检索到该编号设备");
        }
      },
    },
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
        if (str > this.tunnelLengthPx-120) {
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
        if (str > this.tunnelLengthPx-120) {
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
        scrollBtn = this.$refs.scrollBtn,
        that = this;
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
          that.scrollContextValue = parseInt(
            ((that.tunnelInfo.tunnelLength - 0) / baWidth) * x
          );
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
    /**
     * 风机控制操作
     */
    fanClick(item) {
      this.$emit("fanClick", item);
    },
    /**
     * 灯控制操作
     */
    lightingClick(item) {
      this.$emit("lightingClick", item);
    },
  },
};
</script>

<style lang="scss" scoped>
// 样式太多了，就把它单独提出去了
@import "./assets/scss/drawingBoard.scss";
</style>