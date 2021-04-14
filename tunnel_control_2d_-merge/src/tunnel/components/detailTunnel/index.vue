<template>
  <div class="mainBox">
    <div v-if="flag3d">
      <!-- <iframe src="http://121.40.128.159:24003/" class="tunnel_3d" frameborder="0"></iframe> -->
      <tunnel3D class="tunnel3D" />
    </div>
    <div
      type="text"
      class="backBtn"
     @click="test"
    >
      <span class="backText"><i class="el-icon-back backIcon"></i>返回</span>
    </div>
    <Action
      :tunnelList="tunnelList"
      :devTypeList="devTypeList"
      :isCollapse="isCollapse"
      :scaleFlag="scaleFlag"
      :warningflag="warningflag"
      @changeTunnel="changeTunnel"
      @warningflagClick="warningflagClick"
      @warningModuleClick="warningModuleClick"
      @searchDevPosition="searchDevPosition"
      @collapseClickOpen="collapseClickOpen"
      ref="action"
    />
    <div class="drawingBoardBox" v-if="tunnelInfo">
      
        <!-- :tunnelInfo="tunnelInfo" -->
      <DrawingBoard
        :devObj="devObj"
        :devRatio="devRatio"
        :currentStakeNum="currentStakeNum"
        :scaleData="scaleData"
        :tunnelindex="tunnelindex"
        :warningflag="warningflag"
        @fanClick="fanClickOpen"
        @warningModuleClick="warningModuleClick"
        @lightingClick="lightingClickOpen"
        @approachLightingClick="approachLightingClickOpen"
        @laneIndicatorClick="laneIndicatorClickOpen"
        @robotClick="robotClickOpen"
        :searchFromData="searchFromData"
        :tunnelLeng="tunnelLeng"
        :scaleFlag="scaleFlag"
        :lightArr="lightArr"
        ref="robot"
      />
    </div>
      <!-- <div class="btn" @click="toNext(100)">
        <svg t="1616405294746" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="928" ><path d="M469.749538 512 737.1371 962.254727 286.863924 512 737.1371 61.745273Z" p-id="929" fill="#C5B54C"></path></svg>
      </div>
      <div class="btn btn2" @click="toNext(-100)">
        <svg t="1616406042431" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1164" width="200" height="200"><path d="M554.250462 512 286.863924 61.745273 737.1371 512 286.863924 962.254727Z" p-id="1165" fill="#C5B54C"></path></svg>
      </div> -->
    <div :class="warningflag?'translate warning':'translateX warning'">
      <svg t="1617023955961" class="icon" viewBox="0 0 1024 1024" version="1.1" fill="#ff2222" p-id="2154" width="29" height="29"><path d="M559.652811 766.630305c-12.925381 12.961196-28.559453 19.407002-46.729278 19.407002-18.171871 0-34.18252-6.445806-47.176462-19.407002-13.440104-13.026688-19.885909-28.592198-19.885909-47.175439 0-18.171871 6.445806-34.250058 19.885909-47.176462 12.993942-13.473873 29.004591-19.953448 47.176462-19.953448 18.169825 0 33.770128 6.478552 46.729278 19.953448 13.473873 12.926404 19.919678 29.004591 19.919678 47.176462C579.57249 738.038106 573.126684 753.603617 559.652811 766.630305zM464.924333 321.648674c12.514012-13.406335 28.594245-20.331048 47.999201-20.331048 19.473517 0 35.518958 6.479575 48.067762 20.331048 12.135388 13.405311 18.581194 30.308283 18.581194 50.6731 0 17.279548-25.987884 145.847739-35.005258 239.34211l-62.774719 0c-7.371898-93.529163-35.930327-222.097354-35.930327-239.34211C445.862185 352.401072 452.342784 335.499124 464.924333 321.648674zM940.146709 758.813269 590.407256 148.543128c-42.822294-74.432223-112.557542-74.432223-155.344021 0L85.322759 758.813269c-42.787502 74.398454-7.817036 135.426389 77.895091 135.426389l699.44616 0C947.930999 894.239658 983.002772 833.212746 940.146709 758.813269z" p-id="2155"></path></svg>
      <!-- 
        tunnelArea: ['一区', '二区', '三区', '四区', '五区', '六区', '七区', '八区', '九区', '十区'],
        tunnelDirection: ['上行', '下行'],
        eventType: ['火灾', '泥石流', '交通'],
        trafficLane: ['无车道', '行车道', '快车道'],
        eventSource: ['手动事件', '自动事件']
       -->
      <div class="warningtext">{{`${$store.getters.getTips.eventSourceName}, ${tunnelDirection[$store.getters.getTips.tunnelDirection - 1] + tunnelArea[$store.getters.getTips.tunnelArea - 1] + trafficLane[$store.getters.getTips.trafficLane] + $store.getters.getTips.pile}`}}
      {{`发生${$store.getters.getTips.eventTypeName}报警 ！ 请立即处理。`}} 
      <br>
      {{$store.getters.getTips.createTime}}
      </div> 
    </div>
    <div class="consoleAreaBox">
      <ConsoleArea 
        :devTypeList="devTypeList"
        @navClickFn="navClickFn"
        @groupControlClick="groupControlClick"
        :isCollapse="isCollapse"
        ref="consoleArea"
      />
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
      @approachLightingClickClose="approachLightingClickClose"
      @approachLightingChangeData="approachLightingChangeData"
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
    <RobotModal
      v-if="robotVisible"
      :robotVisible="robotVisible"
      @robotClickClose="robotClickClose"
      :type="deviceType"
    />
    <GroupControlModal
      v-if="groupControlVisible"
      :groupControlVisible="groupControlVisible"
      @groupControlClick="groupControlClick"
      :groupControlDevTypeIdList="groupControlDevTypeIdList"
      @groupControlSave="groupControlSave"
    />
    <LightInfo
      :dialogVisible="lightInfoFlag"
      :lightFlag="lightFlag"
      :lightInfoList="lightInfoList"
      :sysTunnelDirection="sysTunnelDirection"
      :tunnelIllumination="tunnelIllumination"
      @lightingClickOpen="lightingClickOpen"
      @lightingInfoClose="fnlightingInfoClose"
      ref="lightInfo"
    />
    <WarningModule
      v-if="warningVisible"
      :warningVisible="warningVisible"
      @warningModuleClick="warningModuleClick"
      @warningDetailsClick="warningDetailsClick"
    />
  </div>
</template>

<script>
import Action from "./Action";
import DrawingBoard from "./DrawingBoard";
import ConsoleArea from "./ConsoleArea/index";
import Scale from "./Scale";
import FanModal from "./ModalTemplate/FanModal";
import LightingModal from "./ModalTemplate/LightingModal";
import LaneIndicatorModal from "./ModalTemplate/LaneIndicatorModal";
import RobotModal from "./ModalTemplate/RobotModal";
import GroupControlModal from "./ModalTemplate/GroupControlModal";
import {equipCurrentStateListByPile, tunnelLength, lightOverview} from '@/tunnel/api/tunnel'
import LightInfo from './LightInfo'
import WarningModule from './WarningModule'
import tunnel3D from '../tunnel3D/index.vue'

import {
  getTunnelList,
  getTunnelInfo,
  getDevTypeList,
  getDevInfoList,
  getLightTypeList,
  getDevStatusList,
  infoBoardStatistics,
  editDevStatus,
  updateEventAutoState,
  getEventAutoState,
  eventList
} from "@/tunnel/api/tunnel";

export default {
  components: {
    Action,
    DrawingBoard,
    ConsoleArea,
    Scale,
    FanModal,
    LightingModal,
    LaneIndicatorModal,
    RobotModal,
    GroupControlModal,
    LightInfo,
    WarningModule,
    tunnel3D
  },
  data() {
    return {
      tunnelList: [{"searchValue":null,"createBy":"","createTime":"2021-02-07 22:57:11","updateBy":"","updateTime":"2021-02-26 17:40:54","remark":null,"params":{},"tunnelId":3,"tunnelName":"羊山隧道","tunnelPosition":"西安","startSign":"k36+000","tunnelLength":"2000","tunnelDetail":"宝鸡的","status":"1"}], // 隧道列表
      tunnelInfo: [], // 隧道详情
      lightInfoList:[], //某段区域的灯光集合
      devObj: {}, // 设备所有数据集合，处理完成
      devTypeList: [], // 设备类型集合
      devTypeIdList: [], // 设备类型id集合
      devInfoList: [], // 设备所有数据集合，未处理
      scaleData: null, // 刻度尺数据
      currentStakeNum: null, // 当前桩号
      tunnelIllumination:null,//当前桩号区域值
      searchFromData: null, // 查询条件
      activeDevStatusList: [], // 设备类型状态列表
      devActiveRow: null, // 选中设备的数据
      InfoBoardlist:[],
      tunnelindex: 2,
      // 模态框状态
      fanVisible: false, // 风机模态框是否显示
      lightingVisible: false, // 灯光模态框是否显示
      laneIndicatorVisible: false, // 车道指示灯模态框是否显示
      robotVisible: false, // 机器人模态框是否显示
      groupControlVisible: false, // 群控策略模态框是否显示
      isCollapse:true,//信息板状态
      devRatio: 17,// 默认比例  1米===**px
      flag:true,
      tunnelLeng: 0, // 隧道长度
      scaleFlag: false, // 刻度显示隐藏
      lightArr: [], // 灯光设备数组
      lightInfoFlag: false,
      lightFlag: null,
      informationtextup:'',
      informationtextdown:'',
      informationcolorup:'',
      informationcolordown:'',
      sysTunnelDirection: null, //点击后灯光组所属的方向
      warningVisible:false, // 警告列表框是否显示
      warningDetailsVisible:false, //警告详情框是否显示
      deviceType: 9,
      warningflag:false,
      tunnelArea: ['一区', '二区', '三区', '四区', '五区', '六区', '七区', '八区', '九区', '十区'],
      tunnelDirection: ['上行', '下行'],
      eventType: ['火灾', '泥石流', '交通'],
      trafficLane: ['无车道', '行车道', '快车道'],
      eventSource: ['手动事件', '自动事件'],
      speedFlag: true,
      flag3d: false
    };
  },
  watch: {
    // 监听设备类型
    devTypeList: {
      immediate: true,
      deep: true,
      handler(newValue = []) {
        // debugger
        this.devTypeIdList = newValue
          .filter((item) => item.isSelect == true)
          .map((item) => item.dictCode);
        if(this.flag&&newValue.length != 0){
          this.groupControlDevTypeIdList = newValue.filter(
            (item) =>
              item.dictValue == "3" ||
              item.dictValue == "4" ||
              item.dictValue == "7" ||
              item.dictValue == "10"
          );
          
          this.flag = false;
        }
        let obj = {
          isShow: false,
          upList: [],
          downList: []
        };
        if(!this.devObj.tunnelFan) {
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
          coVIDetector: {
            ...obj,
          }, // CO/VI检测器
          strengthenLighting: {
            ...obj,
          }, // 加强照明
          approachLighting: {
            ...obj,
          }, // 引道照明
        };
        }
        if (this.devTypeIdList && this.devTypeIdList.length) {
          //this.getDevTypeList();
        } else {
          this.devInfoList = [];
        }
      },
    },
    // 监听设备数据
    devInfoList: {
      immediate: true,
      deep: true,
      handler(newValue) {
        let devObj = this.devObj;
        newValue.forEach((element) => {
          if(element.tunnelDeviceUpList.length !== 0 || element.tunnelDeviceDownList.length !== 0) {
            let obj = {
              isShow: true,
              upList: element.tunnelDeviceUpList,
              downList: element.tunnelDeviceDownList,
              dictCode: element.dictCode,
            };
            switch (element.dictValue) {
              case "1":
                devObj.vehicleDetector = obj;
                break;
              case "2":
                devObj.signalLamp = obj;
                break;
              case "3":
                devObj.laneIndicator = obj;
                break;
              case "4":
                devObj.lighting = obj;
                break;
              case "5":
                devObj.brightnessDetector = obj;
                break;
              case "6":
                devObj.illuminanceDetector = obj;
                break;
              case "7":
                devObj.tunnelFan = obj;
                break;
              case "8":
                devObj.anemoclinograph = obj;
                break;
              case "9":
                devObj.informationBoard = obj;
                break;
              case "10":
                devObj.strengthenLighting = obj;
                break;
              case "11":
                devObj.approachLighting = obj;
                break;
              case "12":
                devObj.coVIDetector = obj;
                break;
              default:
                console.log("有新设备加入，请重新开发" + obj);
                break;
            }
            this.devObj = devObj;
          }
        });
      },
    },
    InfoBoardlist: {
      immediate: true,
      deep: true,
      handler(newValue) {
        if(newValue.length != 0){
          newValue.forEach((element) => {
            if(element.sysTunnelDirection === 1){
              if(this.informationtextup != element.information || this.informationcolorup != element.fontColor){
                this.informationtextup = element.information;
                this.informationcolorup = element.fontColor;
                this.devObj.informationBoard.upList[0].information = element.information
                this.devObj.informationBoard.upList[0].fontColor = element.fontColor
              } else {
                this.devObj.informationBoard.upList[0].information = this.informationtextup
                this.devObj.informationBoard.upList[0].fontColor = this.informationcolorup
              }
            } else {
              if(this.informationtextdown != element.information || this.informationcolordown != element.fontColor){
                this.informationtextdown = element.information;
                this.informationcolordown = element.fontColor;
                this.devObj.informationBoard.downList[0].information = element.information
                this.devObj.informationBoard.downList[0].fontColor = element.fontColor
              } else {
                this.devObj.informationBoard.downList[0].information = this.informationtextdown
                this.devObj.informationBoard.downList[0].fontColor = this.informationcolordown
              }
            }
          })
        }
      }
    },
  },
  mounted() {
    // 一进入页面查询隧道列表
    this.getTunnelList();
    /* this.$nextTick(() => {
      console.log(11111111)
      this.$refs.robot.robotRight();
      this.$refs.robot.robotLeft();
    }) */
    // 请求隧道长度
    //this.getTunnelLength();
    // 计算请求距离
    // this.sumDistance();
    this.getAutoState()
    setInterval(() => {
      this.getAutoState()
    }, 5000)
  },
  methods: {
    getAutoState() {
        getEventAutoState()
        .then(res => {
          if(res.data.eventAutoState == 1) {
            if(this.$store.getters.getTips.eventType) {
              this.warningflag = true;
            }else {
              eventList()
              .then(res => {
                this.$store.commit('setTips', res.data[0]);
                this.warningflag = true;
              })
            }
          }else {
            this.warningflag = false;
          }
        })
    },
    // 分批请求的距离计算
    /* sumDistance() {
      console.log('计算完毕');
      // 页面进入时请求的第一阶段从0到一半, 到达一半时进入第二阶段
      let step1 = {
        lowerPile: this.tunnelLeng * .0,
        upperPile: this.tunnelLeng * .5
      }
      // 在页面中间时请求第二阶段从.3到.7, 到达.3时进入第一阶段, 到达.7时进入第二阶段
      let step2 = {
        lowerPile: this.tunnelLeng * .3,
        upperPile: this.tunnelLeng * .7
      }
      // 在页面尾部时请求的第三阶段从一半到结尾, 到达一半时进入第二阶段
      let step3 = {
        lowerPile: this.tunnelLeng * .5,
        upperPile: this.tunnelLeng
      }
      this.$store.commit('setStepArr', [step1, step2, step3])
      // this.getTunnelInfo()
    }, */
    // 翻页
    toNext(num) {
      let main_drawingContext =  document.querySelector(".main_drawingContext");
      let scrollBox =  document.querySelector(".scrollBox");
      let scrollBtn =  document.querySelector(".scrollBtn");
      let nowPos = +(main_drawingContext.style.marginLeft.split('px')[0]);
      if(num < 0){
        // if(nowPos == 0){
        //   nowPos -= 220;
        // }
      } else {
        if(nowPos == 0) return;
      }
      nowPos -= (scrollBox.offsetWidth - scrollBtn.offsetWidth)/2
      let obj = {
        left: -(nowPos + ( num * this.devRatio )),
      }
      this.searchFromData = obj;
      // main_drawingContext.style.marginLeft = nowPos + (num*17) + "px";
    },
    // 返回按钮
    goBack() {
      console.log('go back');
    },
    getVideo() {
      videoInfoById().then((res) => {
        this.videoUrl = [res.data[0].url, res.data[1].url];
      });
    },
    // 隧道列表
    getTunnelList() {
      getTunnelList()
        .then((response) => {
          if(!localStorage.getItem('tunnelId')){
            console.log(2222222)
            localStorage.setItem("tunnelId", response.data[0].tunnelId);
            this.tunnelId = response.data[0].tunnelId;
            this.tunnelLeng = response.data[0].tunnelLength;
            this.tunnelindex = response.data[0].trafficLaneNum;
            if(this.speedFlag) {
              this.speedFlag = false
              this.$refs.robot.sumSpeed(this.tunnelLeng);
              this.$refs.robot.robotRight();
              this.$refs.robot.robotLeft();
            }
          } else {
            response.data.forEach(v=>{
              this.tunnelId = localStorage.getItem('tunnelId');
              if(this.tunnelId == v.tunnelId){
                this.tunnelLeng = v.tunnelLength;
                this.tunnelindex = v.trafficLaneNum;
                if(this.speedFlag) {
                  this.speedFlag = false
                  this.$refs.robot.sumSpeed(this.tunnelLeng);
                  this.$refs.robot.robotRight();
                  this.$refs.robot.robotLeft();
                }
                return false;
              }
            })
          }
          this.tunnelList = response.data;
          this.getLightOverview()
          this.getDevTypeList();
          this.getTunnelInfo();
          setTimeout(() => {
            this.getTunnelList()
          }, 10000)
        })
        .catch((error) => {
          console.log(error);
        });
    },
    // 获取隧道详情
    /* getTunnelInfo() {
      // if(data.lowerPile) this.$store.commit('setStart', data);
      equipCurrentStateListByPile()
        .then((response) => {
          this.tunnelInfo = [];
          this.tunnelInfo = response.data;
          this.getDevListInfo()
          setTimeout(() => {
            this.currentStakeNum = 'k36+000';
            this.scaleData =
              this.scaleDataCreate(
                this.currentStakeNum,
                2000
              ) || [];
          });
          
        })
        .catch((error) => {
          console.log(error);
        });
    }, */
    getTunnelInfo() {
      getTunnelInfo()
        .then((response) => {
          this.tunnelInfo = [];
          this.tunnelInfo = response.data;
          this.getDevListInfo();
          this.getInformationBoardlist();
          //setTimeout(() => {
            this.currentStakeNum = 'k36+000';
            /* this.scaleData =
              this.scaleDataCreate(
                this.currentStakeNum,
                2000
              ) || []; */
          //});
          
        })
        .catch((error) => {
          console.log(error);
        });
    },
    // 获取设备类型列表
    getDevTypeList() {
      getDevTypeList({ tunnelId: this.tunnelId })
        .then((response) => {
          this.devTypeList = [];
          this.devInfoList = [];
          this.devTypeList = response.data.map((item) => ({
            ...item,
            isSelect: true,
            selectTunnelDevice: '0',
          }));
          let arr = [102, 103, 104,168, 169, 115, 116, 117, 118, 119, 120, 172]
          let arr2 = [];
          arr.map((item2, index2) => {
            this.devTypeList.map((item, index) => {
              /* if(item.dictCode === 102 || item.dictCode === 103 || item.dictCode === 104) {
                item.selectTunnelDevice = '1';
              } else if(item.dictCode === 168 || item.dictCode === 169 || item.dictCode === 115 || item.dictCode === 116 || item.dictCode === 117) {
                item.selectTunnelDevice = '2';
              } else if(item.dictCode === 118 || item.dictCode === 119) {
                item.selectTunnelDevice = '3';
              } else {
                item.selectTunnelDevice = '4';
              } */
              if(item2 == item.dictCode) {
                arr2.push(item)
              }
            })
          })
          this.devTypeList = arr2;
          this.devInfoList = response.data.map((item) => ({
            ...item,
            tunnelDeviceDownList:[],
            tunnelDeviceUpList:[],
          }));
          //this.getDevListInfo()
        })
        .catch((error) => {
          console.log(error);
        });
    },
    // 获取设备详情列表
    getDevListInfo() {
      if(this.devInfoList.length != 0 && this.tunnelInfo.length != 0) {
        this.devInfoList.map((item) => {
          this.tunnelInfo.map((v) => {
            if(item.dictValue == v.type) {
              if(v.sysTunnelDirection === 1) {
                if(v.type == '9'){
                  if(this.informationtextup != '' ||this.informationcolorup != ''){
                    v.information = this.informationtextup
                    v.fontColor = this.informationcolorup
                  }
                }
                item.tunnelDeviceUpList.push(v);
              } else if(v.sysTunnelDirection === 2) {
                if(v.type == '9'){
                  if(this.informationtextdown != '' ||this.informationcolordown != ''){
                    v.information = this.informationtextdown
                    v.fontColor = this.informationcolordown
                  }
                }
                item.tunnelDeviceDownList.push(v);
              }
            }
          })
        })
      }
      // getDevInfoList(this.devTypeIdList)
      //   .then((response) => {
      //     this.devInfoList = response.data;
      //   })
      //   .catch((error) => {
      //     console.log(error);
      //   });
    },
    // 获取信息板详情  /Api/Equip/informationBoardlist

    getInformationBoardlist() {
      infoBoardStatistics()
        .then((response) => {
          this.InfoBoardlist = response.data;
          
        })
        .catch((error) => {
          console.log(error);
        });
    },
    //
    /**
     * 更改隧道
     * @param obj 隧道数据
     */
    changeTunnel(obj) {
      localStorage.setItem("tunnelId", obj.tunnelId);
      localStorage.goto = '/tunnelMonitoring';
      this.tunnelLeng = obj.tunnelLength;
      this.tunnelindex = obj.trafficLaneNum;
      location.href = location.href;
    },
    // 刻度尺数据生成
    /* scaleDataCreate(startValue, tunnelLength) {
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
    }, */
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
     * 警告列表框打开
     */
    warningModuleClick() {
      this.warningVisible = !this.warningVisible;
    },
    /**
     * 警告状态打开
     */
    warningflagClick(arr) {
      this.warningflag = !this.warningflag;
      if(!this.$store.getters.getTips.eventType) this.$store.commit('setTips', arr[0]);
      updateEventAutoState(this.warningflag ? 1 : 0)
      .then(res => {
        console.log(res);
      })
    },
    /**
     * 警告详情框打开
     */
    warningDetailsClick() {
      this.warningDetailsVisible = !this.warningDetailsVisible;
    },
    /**
     * 群控策略保存
     * @param devObj 设备状态修改后返回修改的数据做页面更新
     */
    groupControlSave(devObj) {
      if(devObj.type !== '4' && devObj.type !== '10'){
         this.devInfoList.forEach(item => {
          if (item.dictValue === devObj.type) {
            // this.$set(this.devInfoList, index, devObj);
            if(devObj.sysTunnelDirection == '1') {
              item.tunnelDeviceUpList.forEach( v => {
                v.equipTypeOperationId = devObj.devObj.equipTypeOperationId;
                v.imageAddress = devObj.devObj.imageAddress;
                v.equipTypeOperationName = devObj.devObj.equipTypeOperationName;
              })
            } else if(devObj.sysTunnelDirection == '2') {
              item.tunnelDeviceDownList.forEach( v => {
                v.equipTypeOperationId = devObj.devObj.equipTypeOperationId;
                v.imageAddress = devObj.devObj.imageAddress;
                v.equipTypeOperationName = devObj.devObj.equipTypeOperationName;
              })
            } else {
              item.tunnelDeviceUpList.forEach( v => {
                v.equipTypeOperationId = devObj.devObj.equipTypeOperationId;
                v.imageAddress = devObj.devObj.imageAddress;
                v.equipTypeOperationName = devObj.devObj.equipTypeOperationName;
              })
              item.tunnelDeviceDownList.forEach( v => {
                v.equipTypeOperationId = devObj.devObj.equipTypeOperationId;
                v.imageAddress = devObj.devObj.imageAddress;
                v.equipTypeOperationName = devObj.devObj.equipTypeOperationName;
              })
            }
          }
        });
      } else {
        this.$refs.consoleArea.lightInit();
        this.getLightOverview();
      }
    },
    /**
     * 风机控制打开
     * @param item 选中的设备数据
     */
    fanClickOpen(item) {
      this.getDevStatusList(item);
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
      this.changeDevTypeStatusFn(this.devActiveRow.equipId, status, "fan");
      this.$refs.consoleArea.childInit()
    },
    /**
     * 引道灯光控制打开
     * @param item 选中的设备数据
     */
    approachLightingClickOpen(item) {
      this.getDevStatusList(item);
      this.devActiveRow = item;
      this.lightingVisible = true;
    },
    // 引道灯光控制关闭
    approachLightingClickClose() {
      this.devActiveRow = null;
      this.lightingVisible = false;
    },
    /**
     *  引道灯光修改状态
     * @param status 要修改的风机状态
     */
    approachLightingChangeData(status) {
      this.changeDevTypeStatusFn(this.devActiveRow.equipId, status, "approachLighting");
    },
    /**
     * 灯光控制打开
     * @param item 选中的设备数据
     */
    lightingClickOpen(item) {
      this.lightFlag = item;
      this.tunnelIllumination = item.tunnelIllumination;
      this.sysTunnelDirection = item.sysTunnelDirection;
      getLightTypeList(item).then((response) => {
        this.lightInfoList = response.data;
        if(this.lightInfoList.length !== 0 ){
          this.lightInfoFlag = true;
        } else {
          this.$message.error("该区域段暂无灯光");
        }
      })
      .catch((error) => {
        console.log(error);
      });
      
    },
    // 灯光控制关闭
    lightingClickClose() {
      /* this.devActiveRow = null;
      this.lightingVisible = false; */
      this.$refs.lightInfo.handleClose();
      this.fnlightingInfoClose()
    },
    /**
     *  灯光修改状态
     * @param status 要修改的灯光状态
     */
    lightingChangeData(id, status) {
      this.changeDevTypeStatusFn(
        id,
        status,
        "lighting"
      );
    },
    /**
     * 车道指示灯打开
     * @param item 选中的设备数据
     */
    laneIndicatorClickOpen(item) {
      this.getDevStatusList(item);
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
        this.devActiveRow.equipId,
        status,
        "laneIndicator"
      );
    },
    /**
     * 机器人弹出框打开
     * @param item 机器人数据
     */
    robotClickOpen(item) {
      this.robotVisible = true;
      // 上行或下行
      this.deviceType = item.deviceGroup;
    },
    test() {
      // this.getDevStatusList(item);
      // this.devActiveRow = item;
      // this.lightingVisible = true;
      console.log(this.devObj);
    },

    /**
     * 机器人弹出框关闭
     */
    robotClickClose() {
      this.robotVisible = false;
    },
    /**
     * 设备信息弹出框的显示与关闭
     */
    collapseClickOpen() {
      this.isCollapse = !this.isCollapse;
    },
    scaleToggle() {
      this.scaleFlag = !this.scaleFlag;
    },
    /**
     * 设备信息搜索条件，实现定位到对应的设备位置
     * @param searchFrom 用来定位设备的搜索条件
     */
    searchDevPosition(searchFrom) {
      let ele = document.querySelector("#a" + searchFrom)
      /* let obj = {
        left:　+ele.style.left.split("px")[0]
      } */
      // Object.assign(obj, {...searchFrom})
      // this.searchFromData = Object.assign(obj, searchFrom);
      if(ele) {
        ele.classList.add("isSearch");
        // document.querySelector(".main_drawingContext").style.marginLeft = (300 - ele.style.left.split("px")[0]) + "px"
        setTimeout(() => {
          ele.classList.remove("isSearch");
        }, 1000)
      }else {
        this.$message.warning("没有此编号的设备");
      }
      /* for (const key in this.devObj) {
        if (Object.hasOwnProperty.call(this.devObj, key)) {
          const element = this.devObj[key];
          if (searchFrom.devType === element.dictCode) {
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
      } */
    },
    /**
     * 查询设备类型状态
     */
    getDevStatusList(dataList) {
      getDevStatusList(dataList.dictCode)
        .then((response) => {
          this.activeDevStatusList = response.data.filter((item) => item.equipType == dataList.type) || [];
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
        .then((response) => {
          if(source !== "lighting") {
            this.activeDevStatusList.forEach(item=>{
              if(item.equipTypeOperationId == workStatus){
                this.$set(
                  this.devActiveRow,
                  "imageAddress",
                  item.imageAddress
                );
                this.$set(this.devActiveRow, "equipTypeOperationId", workStatus);
              }
            })
          }
          
          if (source === "fan") {
            this.fanClickClose();
          } else if (source === "lighting") {
            //this.lightingClickClose();
            this.$refs.lightInfo.propClose()
            this.lightingClickOpen(this.lightFlag);
            this.getLightOverview();
          } else if (source === "laneIndicator") {
            this.laneIndicatorClickClose();
          } else if (source === "approachLighting") {
            this.approachLightingClickClose();
          } 
        })
        .catch((error) => {
          console.log(error);
        });
    },
    // 获取隧道长度
    getTunnelLength() {
      /* tunnelLength()
      .then(res => { */
        this.tunnelLeng = 2000;
        this.getLightOverview()
        this.$refs.robot.robotRight();
        this.$refs.robot.robotLeft();
      // })
    },
    getLightOverview() {
      lightOverview()
      .then(res => {
        this.lightArr = res.data;

        /* this.devObj.strengthenLighting = {
          upList:this.filterLight(res.data, 10, 1),
          isShow: true,
          downList: this.filterLight(res.data, 10, 2)
        }
        this.devObj.lighting = {
          upList:this.filterLight(res.data, 4, 1),
          isShow: true,
          downList: this.filterLight(res.data, 4, 2)
        } */
      })
    },
    fnlightingInfoClose(){
      this.lightInfoFlag = false;
    },
    // 过滤出照明和强照明的上下行
    filterLight(a, b, c) {
      return a.filter((item) => item.type == b && item.sysTunnelDirection == c)
    },
    // 模态框调用此事件更新action数据
    updateAction() {
      this.$refs.action.dataInit();
    },
    close3d() {
      localStorage.goto = '/tunnelMonitoring';
      location.href = location.href;
      this.flag3d = false;
    },
    open3d() {
      this.flag3d = true;
    }
  },
};
</script>
<style scoped>
.mainBox /deep/ .switchingTunnels:hover {
    color: #909090;
}
.mainBox /deep/ .switchingTunnels{
    color: #909090;
}
</style>
<style lang="scss" scoped>
.mainBox {
  width: 100% !important;
  height: 100%;
  background-color: #232629FF;
  position: relative;
  overflow-y: auto;
  overflow-x: hidden;
  .btn {
    cursor: pointer;
    position: fixed;
    z-index: 999;
    width: 20px;
    height: 40px;
    bottom: 0px;
    >* {
      width: 100%;
      height: 100%;
    }
    &.btn2 {
      right: 0;
    }
  }
  .backBtn {
    width: 96px;
    height:34px;
    background-image: url("./assets/img/goback.png");
    background-size: 100% 100%;
    position: fixed;
    left:0;
    right:0;
    z-index: 999;
    font-size: 12px;
    cursor: pointer;
    .backText {
      color:#fff;
      .backIcon {
        font-size: 16px;
        line-height: 32px;
        margin-left: 7px;
        margin-right: 7px;
      }
    }
  }
  
  .drawingBoardBox {
    height: 1000px;
    width: 100%;
    position: relative;
    left: 0;
    top: 28px;
  }
}
.warning{    
  width: 335px;
  height: 38px;
  background-color: rgba(25,29,33,0.9);
  border: 1px solid rgba(255,34,34,1);
  position: fixed;
  top: 85%;
  z-index: 9999;
  right: 15px;
  transition:transform 1s ease-out;
  -moz-transition: -moz-transform 1s ease-out; /* Firefox 4 */
  -webkit-transition: -webkit-transform 1s ease-out; /* Safari and Chrome */
  -o-transition:-o-transform 1s ease-out; /* Opera */
  box-shadow: inset 0px 0px 15px 1px rgba(255,34,34,0.5);
  border-radius: 4px;
  padding: 25px 15px;
  svg {
    float: left;
  }
  .warningtext {
    color:rgba(255,34,34,1);
    width: 247px;
    line-height: 18px;
    margin-left: 18px;
    font-size: 15px;
    float: left;
    margin-top: -8px;
  }
}
video::-webkit-media-controls{
  display:none;
}
.translateX{
  transform: translateX(120%);
}
.mainBox .tunnel_3d {
  position: fixed;
  left: -8px;
  top: -8px;
  width: 101%;
  height: 101vh;
  z-index: 10000;
}
.translate{
  transform: translateX(0);
}
.tunnel3D {
  position: fixed;
  top: 0;
  left: 0;
  z-index: 10000;
  width: 100%;
  height: 100%;
  box-sizing: border-box;
  margin-top: 61px;
}
</style>
