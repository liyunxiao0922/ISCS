<template>
  <div class="mainBox">
    <Navbar :devTypeList="devTypeList" />
    <Action :tunnelList="tunnelList" @changeTunnel="changeTunnel" />
    <div class="drawingBoardBox" v-if="tunnelInfo">
      <DrawingBoard :tunnelInfo="tunnelInfo" :devObj="devObj" />
      <!-- 前隧道口 -->
      <div class="tunnelEntrance entrance" v-if="scaleCurrent == 1">
        <img src="./assets/img/entrance.png" alt="隧道口icon丢失了" />
      </div>
      <!-- 后隧道口 -->
      <div class="tunnelEntrance export" v-if="scaleTotal === scaleCurrent">
        <img src="./assets/img/export.png" alt="隧道结尾icon丢失了" />
      </div>
    </div>
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
  },
  data() {
    return {
      tunnelList: [], // 隧道列表
      tunnelInfo: null, // 隧道详情
      devTypeList: [], // 设备类型集合
      devTypeIdList: [], // 设备类型id集合
      devInfoList: [], // 设备所有数据集合，未处理
      scaleTotal: 1, // 总层级
      scaleCurrent: 1, // 当前层级
      devObj: {
        tunnelFan: {
          isShow: false,
          upList: [],
          downList: [],
        }, // 隧道风机
        signalLamp: {
          isShow: false,
          upList: [],
          downList: [],
        }, // 信号灯
        lighting: {
          isShow: false,
          upList: [],
          downList: [],
        }, // 灯光
        laneIndicator: {
          isShow: false,
          upList: [],
          downList: [],
        }, // 车道指示器
        informationBoard: {
          isShow: false,
          upList: [],
          downList: [],
        }, // 情报板
        vehicleDetector: {
          isShow: false,
          upList: [],
          downList: [],
        }, // 车辆检测器
        brightnessDetector: {
          isShow: false,
          upList: [],
          downList: [],
        }, // 亮度检测器
        illuminanceDetector: {
          isShow: false,
          upList: [],
          downList: [],
        }, // 照度检测器
        anemoclinograph: {
          isShow: false,
          upList: [],
          downList: [],
        }, // 风速风向仪
      }, // 设备所有数据集合，处理完成
    };
  },
  watch: {
    // 监听设备类型
    devTypeList: {
      immediate: true,
      deep: true,
      handler(newValue) {
        this.devTypeIdList = newValue.map((item) => item.deviceTypeId);
        if (this.devTypeIdList && this.devTypeIdList.length) {
          this.getDevListInfo();
        } else {
          this.devInfoList = [];
          this.devObj = {
            tunnelFan: {
              isShow: false,
              upList: [],
              downList: [],
            }, // 隧道风机
            signalLamp: {
              isShow: false,
              upList: [],
              downList: [],
            }, // 信号灯
            lighting: {
              isShow: false,
              upList: [],
              downList: [],
            }, // 灯光
            laneIndicator: {
              isShow: false,
              upList: [],
              downList: [],
            }, // 车道指示器
            informationBoard: {
              isShow: false,
              upList: [],
              downList: [],
            }, // 情报板
            vehicleDetector: {
              isShow: false,
              upList: [],
              downList: [],
            }, // 车辆检测器
            brightnessDetector: {
              isShow: false,
              upList: [],
              downList: [],
            }, // 亮度检测器
            illuminanceDetector: {
              isShow: false,
              upList: [],
              downList: [],
            }, // 照度检测器
            anemoclinograph: {
              isShow: false,
              upList: [],
              downList: [],
            }, // 风速风向仪
          };
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
          switch (element.deviceTypeCode) {
            case "102":
              devObj.vehicleDetector = {
                isShow: true,
                upList: element.tunnelDeviceUpList,
                downList: element.tunnelDeviceDownList,
              };
              break;
            case "103":
              devObj.signalLamp = {
                isShow: true,
                upList: element.tunnelDeviceUpList,
                downList: element.tunnelDeviceDownList,
              };
              break;
            case "104":
              devObj.laneIndicator = {
                isShow: true,
                upList: element.tunnelDeviceUpList,
                downList: element.tunnelDeviceDownList,
              };
              break;
            case "115":
              devObj.lighting = {
                isShow: true,
                upList: element.tunnelDeviceUpList,
                downList: element.tunnelDeviceDownList,
              };
              break;
            case "116":
              devObj.brightnessDetector = {
                isShow: true,
                upList: element.tunnelDeviceUpList,
                downList: element.tunnelDeviceDownList,
              };
              break;
            case "117":
              devObj.illuminanceDetector = {
                isShow: true,
                upList: element.tunnelDeviceUpList,
                downList: element.tunnelDeviceDownList,
              };
              break;
            case "118":
              devObj.tunnelFan = {
                isShow: true,
                upList: element.tunnelDeviceUpList,
                downList: element.tunnelDeviceDownList,
              };
              break;
            case "119":
              devObj.anemoclinograph = {
                isShow: true,
                upList: element.tunnelDeviceUpList,
                downList: element.tunnelDeviceDownList,
              };
              break;
            case "120":
              devObj.informationBoard = {
                isShow: true,
                upList: element.tunnelDeviceUpList,
                downList: element.tunnelDeviceDownList,
              };
              break;
            default:
              console.log("有新设备加入，请重新开发");
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
          this.scaleTotal = Math.ceil((this.tunnelInfo.tunnelLength - 0) / 100);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    // 获取设备类型列表
    getDevTypeList() {
      getDevTypeList({ tunnelId: this.tunnelId })
        .then((response) => {
          this.devTypeList = response.data;
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
    height: 600px;
    width: 100%;
    position: fixed;
    left: 0;
    top: 50%;
    margin-top: -350px;
    .tunnelEntrance {
      position: absolute;
      top: 35px;
      height: 529px;
      width: 350px;
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