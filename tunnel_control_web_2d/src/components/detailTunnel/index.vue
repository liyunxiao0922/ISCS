<template>
  <div class="mainBox">
    <Navbar :devTypeList="devTypeList" @navClickFn="navClickFn" />
    <Action :tunnelList="tunnelList" @changeTunnel="changeTunnel" />
    <div class="drawingBoardBox" v-if="tunnelInfo">
      <DrawingBoard
        :tunnelInfo="tunnelInfo"
        :devObj="devObj"
        :currentStakeNum="currentStakeNum"
        :scaleData="scaleData"
      />
    </div>
    <!-- <div style="width: 100%;height: 650px;">
      <iframe src="http://tunnel-three.xaxhl.net/" frameborder="0" width="100%" height="100%"></iframe>
    </div> -->

    <div class="consoleAreaBox">
      <ConsoleArea />
    </div>
  </div>
</template>

<script>
import Navbar from "./Navbar";
import Action from "./Action";
import DrawingBoard from "./DrawingBoard";
import ConsoleArea from "./ConsoleArea/index";
import Scale from "./Scale";

import {
  getTunnelList,
  getTunnelInfo,
  getDevTypeList,
  getDevInfoList,
} from "@/api/tunnel";

export default {
  components: {
    Navbar,
    Action,
    DrawingBoard,
    ConsoleArea,
    Scale,
  },
  data() {
    return {
      tunnelList: [], // 隧道列表
      tunnelInfo: null, // 隧道详情
      devTypeList: [], // 设备类型集合
      devTypeIdList: [], // 设备类型id集合
      devInfoList: [], // 设备所有数据集合，未处理
      devObj: {}, // 设备所有数据集合，处理完成
      scaleData: null, // 刻度尺数据
      currentStakeNum: null, // 当前桩号
    };
  },
  watch: {
    // 监听设备类型
    devTypeList: {
      immediate: true,
      deep: true,
      handler(newValue) {
        this.devTypeIdList = newValue
          .filter((item) => item.isSelect == true)
          .map((item) => item.deviceTypeId);
        let obj = {
          isShow: false,
          upList: [],
          downList: [],
        };
        this.devObj = {
          tunnelFan: {
            ...obj,
          }, // 隧道风机
          signalLamp: {
            ...obj,
          }, // 信号灯
          lighting: {
            ...obj,
          }, // 灯光
          laneIndicator: {
            ...obj,
          }, // 车道指示器
          informationBoard: {
            ...obj,
          }, // 情报板
          vehicleDetector: {
            ...obj,
          }, // 车辆检测器
          brightnessDetector: {
            ...obj,
          }, // 亮度检测器
          illuminanceDetector: {
            ...obj,
          }, // 照度检测器
          anemoclinograph: {
            ...obj,
          }, // 风速风向仪
          passageway: {
            ...obj,
          }, // 车行通道
        };
        if (this.devTypeIdList && this.devTypeIdList.length) {
          this.getDevListInfo();
        } else {
          this.devInfoList = [];
        }
      },
    },
    // 监听设备数据
    devInfoList: {
      immediate: true,
      deep: true,
      handler(newValue = []) {
        let devObj = this.devObj;
        newValue.forEach((element) => {
          let obj = {
            isShow: true,
            space: element.deviceSpace - 0,
            upList: element.tunnelDeviceUpList,
            downList: element.tunnelDeviceDownList,
            startPosition: element.startPosition,
          };
          switch (element.deviceTypeCode) {
            case "102":
              devObj.vehicleDetector = obj;
              break;
            case "103":
              devObj.signalLamp = obj;
              break;
            case "104":
              devObj.laneIndicator = obj;
              break;
            case "115":
              devObj.lighting = obj;
              break;
            case "116":
              devObj.brightnessDetector = obj;
              break;
            case "117":
              devObj.illuminanceDetector = obj;
              break;
            case "118":
              devObj.tunnelFan = obj;
              break;
            case "119":
              devObj.anemoclinograph = obj;
              break;
            case "120":
              devObj.informationBoard = obj;
              break;
            case "121":
              devObj.passageway = obj;
              break;
            default:
              console.log("有新设备加入，请重新开发" + obj);
              break;
          }
        });
        this.devObj = devObj;
      },
    },
  },
  mounted() {
    getTunnelList()
      .then((response) => {
        this.tunnelList = response.rows;
        this.tunnelId = this.tunnelList[0].tunnelId;
        this.getTunnelInfo();
        this.getDevTypeList();
      })
      .catch((error) => {
        console.log(error);
      });
  },
  methods: {
    // 获取隧道详情
    getTunnelInfo() {
      getTunnelInfo({ tunnelId: this.tunnelId })
        .then((response) => {
          this.tunnelInfo = response.data;
          this.currentStakeNum = response.data.startSign;
          this.scaleData =
            this.scaleDataCreate(
              response.data.startSign,
              response.data.tunnelLength
            ) ?? [];
        })
        .catch((error) => {
          console.log(error);
        });
    },
    // 获取设备类型列表
    getDevTypeList() {
      getDevTypeList({ tunnelId: this.tunnelId })
        .then((response) => {
          this.devTypeList = response.data.map((item) => ({
            ...item,
            isSelect: true,
          }));
        })
        .catch((error) => {
          console.log(error);
        });
    },
    // 获取设备详情列表
    getDevListInfo() {
      getDevInfoList(this.devTypeIdList)
        .then((response) => {
          this.devInfoList = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    // 更改隧道
    changeTunnel(value) {
      this.tunnelId = value;
      this.getTunnelInfo();
      this.getDevTypeList();
    },
    // 刻度尺数据生成
    scaleDataCreate(startValue, tunnelLength) {
      if (startValue && tunnelLength) {
        let newValue = startValue.replace("k", "");
        let arr = newValue.split("+");
        // 倍数，5米显示1刻度，多了一个起始，所以加一
        let multiple = tunnelLength / 5 + 1;
        if ((arr.length = 2)) {
          let kNum = arr[0];
          let vNum = arr[1] || 0;
          let returnArr = [];
          let vNumIndex = false;
          for (let index = 0; index < multiple; index++) {
            let str = "";
            // 如果第一次进来，赋值初始值
            if (index == 0) {
              let obj = {
                name: startValue,
              };
              returnArr.push(obj);
              continue;
            }
            // 如果大于等于995，需要记录状态，后续的kNum +1,并且算出它的值。
            if (vNum >= 995) {
              vNumIndex = true;
              str = "k" + (kNum - 0 + 1) + "+00" + (vNum - 0 + 5 - 1000);
              vNum = vNum - 0 + 5 - 1000;
            } else {
              // 正常加5的值
              if (vNumIndex) {
                kNum = arr[0] - 0 + 1;
              }
              vNum = vNum - 0 + 5;
              str =
                "k" +
                kNum +
                "+" +
                (vNum.toString().length == 3
                  ? vNum
                  : vNum.toString().length == 2
                  ? "0" + vNum
                  : "00" + vNum);
            }
            let obj = {
              name: str,
            };
            returnArr.push(obj);
          }
          return returnArr;
        } else {
          console.log("该隧道初始值错误，请在管理端配置");
        }
      } else {
        console.log("该隧道没有初始值，请在管理端配置");
      }
    },
    // 控制设备显示隐藏
    navClickFn(item) {
      this.$set(item, "isSelect", !item.isSelect);
    },
  },
};
</script>

<style lang="scss" scoped>
.mainBox {
  width: 100%;
  height: 100% !important;
  background-color: #000;
  position: relative;
  .drawingBoardBox {
    height: 690px;
    width: 100%;
    position: fixed;
    left: 0;
    top: 50%;
    margin-top: -400px;
  }
  .consoleAreaBox {
    position: fixed;
    left: 0;
    bottom: 0;
    width: 100%;
    height: 200px;
    padding: 20px;
  }
}
</style>