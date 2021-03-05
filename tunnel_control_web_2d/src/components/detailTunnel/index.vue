<template>
  <div class="mainBox">
    <Navbar
      :devTypeList="devTypeList"
      @navClickFn="navClickFn"
      @searchDevPosition="searchDevPosition"
      @groupControlClick="groupControlClick"
    />
    <Action :tunnelList="tunnelList" @changeTunnel="changeTunnel" />
    <div class="drawingBoardBox" v-if="tunnelInfo">
      <DrawingBoard
        :tunnelInfo="tunnelInfo"
        :devObj="devObj"
        :currentStakeNum="currentStakeNum"
        :scaleData="scaleData"
        @fanClick="fanClickOpen"
        @lightingClick="lightingClickOpen"
        @laneIndicatorClick="laneIndicatorClickOpen"
        :searchFromData="searchFromData"
      />
    </div>
    <!-- <div style="width: 100%;height: 650px;">
      <iframe src="http://tunnel-three.xaxhl.net/" frameborder="0" width="100%" height="100%"></iframe>
    </div> -->

    <div class="consoleAreaBox">
      <ConsoleArea />
    </div>
    <FanModal
      v-if="fanVisible"
      :fanVisible="fanVisible"
      :fanActiveRow="devActiveRow"
      @fanClickClose="fanClickClose"
      @fanChangeData="fanChangeData"
      :activeDevStatusList="activeDevStatusList"
    />
    <LightingModal
      v-if="lightingVisible"
      :lightingVisible="lightingVisible"
      :lightingActiveRow="devActiveRow"
      @lightingClickClose="lightingClickClose"
      @lightingChangeData="lightingChangeData"
      :activeDevStatusList="activeDevStatusList"
    />
    <LaneIndicatorModal
      v-if="laneIndicatorVisible"
      :laneIndicatorVisible="laneIndicatorVisible"
      :laneIndicatorActiveRow="devActiveRow"
      @laneIndicatorClickClose="laneIndicatorClickClose"
      @laneIndicatorChangeData="laneIndicatorChangeData"
      :activeDevStatusList="activeDevStatusList"
    />
    <GroupControlModal
      v-if="groupControlVisible"
      :groupControlVisible="groupControlVisible"
      @groupControlClick="groupControlClick"
      :groupControlDevTypeIdList="groupControlDevTypeIdList"
      @getDevTypeList="getDevTypeList"
    />
  </div>
</template>

<script>
import Navbar from "./Navbar";
import Action from "./Action";
import DrawingBoard from "./DrawingBoard";
import ConsoleArea from "./ConsoleArea/index";
import Scale from "./Scale";
import FanModal from "./ModalTemplate/FanModal";
import LightingModal from "./ModalTemplate/LightingModal";
import LaneIndicatorModal from "./ModalTemplate/LaneIndicatorModal";
import GroupControlModal from "./ModalTemplate/GroupControlModal";

import {
  getTunnelList,
  getTunnelInfo,
  getDevTypeList,
  getDevInfoList,
  getDevStatusList,
  editDevStatus,
} from "@/api/tunnel";

export default {
  components: {
    Navbar,
    Action,
    DrawingBoard,
    ConsoleArea,
    Scale,
    FanModal,
    LightingModal,
    LaneIndicatorModal,
    GroupControlModal,
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
      searchFromData: null, // 查询条件
      activeDevStatusList: [], // 设备类型状态列表
      devActiveRow: null, // 选中设备的数据

      // 模态框状态
      fanVisible: false, // 风机模态框是否显示
      lightingVisible: false, // 灯光模态框是否显示
      laneIndicatorVisible: false, // 车道指示灯模态框是否显示
      groupControlVisible: false, // 群控策略模态框是否显示
    };
  },
  watch: {
    // 监听设备类型
    devTypeList: {
      immediate: true,
      deep: true,
      handler(newValue = []) {
        this.devTypeIdList = newValue
          .filter((item) => item.isSelect == true)
          .map((item) => item.deviceTypeId);
        this.groupControlDevTypeIdList = newValue.filter(
          (item) =>
            item.deviceTypeCode == "104" ||
            item.deviceTypeCode == "115" ||
            item.deviceTypeCode == "118"
        );
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
            deviceTypeId: element.deviceTypeId,
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
    // 一进入页面查询隧道列表
    this.getTunnelList();
  },
  methods: {
    // 隧道列表
    getTunnelList() {
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
    // 获取隧道详情
    getTunnelInfo() {
      getTunnelInfo({ tunnelId: this.tunnelId })
        .then((response) => {
          this.tunnelInfo = null;
          setTimeout(() => {
            this.tunnelInfo = response.data;
            this.currentStakeNum = response.data.startSign;
            this.scaleData =
              this.scaleDataCreate(
                response.data.startSign,
                response.data.tunnelLength
              ) ?? [];
          });
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
    /**
     * 更改隧道
     * @param value 隧道id
     */
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
    /**
     * 设备类型控制设备显示隐藏
     * @param item 选中的设备类型数据
     */
    navClickFn(item) {
      this.$set(item, "isSelect", !item.isSelect);
    },
    /**
     * 群控策略模态框打开
     */
    groupControlClick() {
      this.groupControlVisible = !this.groupControlVisible;
    },
    /**
     * 风机控制打开
     * @param item 选中的设备数据
     */
    fanClickOpen(item) {
      this.getDevStatusList(item.deviceTypeId);
      this.devActiveRow = item;
      this.fanVisible = true;
    },
    // 风机控制关闭
    fanClickClose() {
      this.devActiveRow = null;
      this.fanVisible = false;
    },
    /**
     *  风机修改运行状态
     * @param status 要修改的风机状态
     */
    fanChangeData(status) {
      this.changeDevTypeStatusFn(this.devActiveRow.deviceId, status, "fan");
    },
    /**
     * 灯光控制打开
     * @param item 选中的设备数据
     */
    lightingClickOpen(item) {
      this.getDevStatusList(item.deviceTypeId);
      this.devActiveRow = item;

      this.lightingVisible = true;
    },
    // 灯光控制关闭
    lightingClickClose() {
      this.devActiveRow = null;
      this.lightingVisible = false;
    },
    /**
     *  灯光修改状态
     * @param status 要修改的灯光状态
     */
    lightingChangeData(status) {
      this.changeDevTypeStatusFn(
        this.devActiveRow.deviceId,
        status,
        "lighting"
      );
    },
    /**
     * 车道指示灯打开
     * @param item 选中的设备数据
     */
    laneIndicatorClickOpen(item) {
      this.getDevStatusList(item.deviceTypeId);
      this.devActiveRow = item;
      this.laneIndicatorVisible = true;
    },
    // 车道指示灯关闭
    laneIndicatorClickClose() {
      this.devActiveRow = null;
      this.laneIndicatorVisible = false;
    },
    /**
     *  车道指示灯修改状态
     * @param status 要修改的指示灯状态
     */
    laneIndicatorChangeData(status) {
      this.changeDevTypeStatusFn(
        this.devActiveRow.deviceId,
        status,
        "laneIndicator"
      );
    },
    /**
     * 设备信息搜索条件，实现定位到对应的设备位置
     * @param searchFrom 用来定位设备的搜索条件
     */
    searchDevPosition(searchFrom) {
      for (const key in this.devObj) {
        if (Object.hasOwnProperty.call(this.devObj, key)) {
          const element = this.devObj[key];
          if (searchFrom.devType === element.deviceTypeId) {
            let upList = element.upList,
              downList = element.downList;
            let flag = true;
            for (let index = 0; index < upList.length; index++) {
              const item = upList[index];
              if (item.deviceCode === searchFrom.devCode) {
                this.searchFromData = {
                  dev: item,
                  key,
                  index,
                };
                flag = false;
                this.$set(element.upList[index], "isSearch", true);
                setTimeout(() => {
                  this.$set(element.upList[index], "isSearch", false);
                }, 3000);
                break;
              } else {
                this.searchFromData = undefined;
              }
            }
            if (flag) {
              for (let index = 0; index < downList.length; index++) {
                const item = downList[index];
                if (item.deviceCode === searchFrom.devCode) {
                  this.searchFromData = {
                    dev: item,
                    key,
                    index,
                  };
                  this.$set(element.downList[index], "isSearch", true);
                  setTimeout(() => {
                    this.$set(element.upList[index], "isSearch", false);
                  }, 1000);
                  break;
                } else {
                  this.searchFromData = undefined;
                }
              }
            }
            break;
          }
        }
      }
    },
    /**
     * 查询设备类型状态
     */
    getDevStatusList(deviceTypeId) {
      getDevStatusList({ deviceTypeId })
        .then((response) => {
          this.activeDevStatusList = response.rows ?? [];
        })
        .catch((error) => {
          console.log(error);
        });
    },
    /**
     * 修改设备的状态
     * @param deviceId   设备id
     * @param workStatus  设备状态
     * @param source 来源
     */
    changeDevTypeStatusFn(deviceId, workStatus, source) {
      editDevStatus({
        deviceId,
        workStatus,
      })
        .then(() => {
          this.$message.success("更改成功");
          if (source === "fan") {
            this.fanClickClose();
          } else if (source === "lighting") {
            this.lightingClickClose();
          } else if (source === "laneIndicator") {
            this.laneIndicatorClickClose();
          }
          this.getDevTypeList();
        })
        .catch((error) => {
          console.log(error);
        });
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