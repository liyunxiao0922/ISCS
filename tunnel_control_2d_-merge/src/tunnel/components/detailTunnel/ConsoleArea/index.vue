<template>
  <div class="consoleAreaBox">
    <div class="consoleItem card1" :class="isCollapse?'translate':'translateY'">
      <div class="backgroundimg">
        <!-- <img src="../assets/img/border.png" alt="" /> -->
        <div class="content-trigon-zhijiao"></div>
      </div>
      <div class="labelBox">
        <span>
          <i class="tunnelIcon"></i>
          <span class="tunnelText">{{trafficFlowStatisticstext}}</span>
        </span>
        <span class="robotIcon">
          <el-dropdown @command="selectTunnelCommand3">
            <span class="el-dropdown-link">
              <i class="el-icon-s-tools" style="color:#fff;font-size:20px;"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item command="1">车辆信息</el-dropdown-item>
              <el-dropdown-item command="2">车流量</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </span>
      </div>
      <CarInfo :trafficFlowStatistics="trafficFlowStatistics"/>
    </div>
    <div class="consoleItem card2" :class="isCollapse?'translate':'translateY'">
      <div class="backgroundimg">
        <!-- <img src="../assets/img/border.png" alt="" /> -->
        <div class="content-trigon-zhijiao"></div>
      </div>
      <div class="labelBox">
        <span>
          <i class="tunnelIcon"></i>
          <span class="tunnelText">{{fanAndCovitext}}</span>
        </span>
        <span class="robotIcon">
          <el-dropdown @command="selectTunnelCommand4">
            <span class="el-dropdown-link">
              <i class="el-icon-s-tools" style="color:#fff;font-size:20px;"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item command="1">风机信息</el-dropdown-item>
              <el-dropdown-item command="2">CO/VI检测器</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </span>
      </div>
      <FanInfo ref="fanInfo"  :fanAndCovi="fanAndCovi"/>
    </div>
    <div class="consoleItem card3" :class="isCollapse?'translate':'translateY'">
      <div class="backgroundimg">
        <!-- <img src="../assets/img/border.png" alt="" /> -->
        <div class="content-trigon-zhijiao"></div>
      </div>
      <div class="labelBox">
        <span>
          <i class="tunnelIcon"></i>
          <span class="tunnelText">{{LightingStatisticstext}}</span>
        </span>
        <span class="robotIcon">
          <el-dropdown @command="selectTunnelCommand5">
            <span class="el-dropdown-link">
              <i class="el-icon-s-tools" style="color:#fff;font-size:20px;"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item command="1">照明信息</el-dropdown-item>
              <el-dropdown-item command="2">安全预警</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </span>
      </div>
      <LightingInfo ref="lightingInfo" :LightingStatistics="LightingStatistics"/>
    </div>
    <div class="consoleItem card4" :class="isCollapse?'translate':'translateX'">
      <div class="backgroundimg">
        <!-- <img src="../assets/img/border.png" alt="" /> -->
        <div class="content-trigon-zhijiao"></div>
      </div>
      <div class="labelBox">
        <span>
          <i class="tunnelIcon"></i>
          <span class="tunnelText">设备模块区域</span>
        </span>
        <span v-if="selectTunnelDevice !== '0'" class="robotIcon tunnelcancel" @click="selectTunnelCommand">
          <i class="el-icon-right" style="color:#fff"></i>
        </span>
        <!-- <span class="robotIcon">
          <el-dropdown @command="selectTunnelCommand">
            <span class="el-dropdown-link">
              <i class="el-icon-s-tools" style="color:#fff"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item command="1">交通控制</el-dropdown-item>
              <el-dropdown-item command="2">照明控制</el-dropdown-item>
              <el-dropdown-item command="3">通风控制</el-dropdown-item>
              <el-dropdown-item command="4">其他控制</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </span> -->
      </div>
        <Navbar
          :devTypeList="devTypeList"
          @navClickFn="navClickFn"
          @groupControlClick="groupControlClick"
          @selectTunnelDeviceClickFn="selectTunnelDeviceClickFn"
          :groupControlVisible="groupControlVisible"
          :selectTunnelDevice="selectTunnelDevice" 
        />
    </div>
    <div class="consoleItem card5" :class="isCollapse?'translate':'translateX'">
      <div class="backgroundimg">
        <!-- <img src="../assets/img/border.png" alt="" /> -->
        <div class="content-trigon-zhijiao"></div>
      </div>
      <div class="labelBox">
        <span>
          <i class="tunnelIcon"></i>
          <span class="tunnelText">机器人</span>
        </span>
        <span class="robotIcon">
          <el-dropdown @command="selectTunnelCommand2">
            <span class="el-dropdown-link">
              <i class="el-icon-s-tools" style="color:#fff;font-size:20px;"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item command="1">上行机器人信息</el-dropdown-item>
              <el-dropdown-item command="2">上行机器人监控</el-dropdown-item>
              <el-dropdown-item command="3">下行机器人信息</el-dropdown-item>
              <el-dropdown-item command="4">下行机器人监控</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </span>
      </div>
      <div v-if="robotTab == 'info'" class="labelBox robotLabel">
        <span v-if="robotType == 'up'"
          style="padding-top: 12px; cursor: pointer;font-size:12px;"
          >上行机器人</span
        >
        <span v-if="robotType == 'down'"
          style="padding-top: 12px; cursor: pointer;font-size:12px;"
          >下行机器人</span
        >
        <span
          style="padding-top: 12px; cursor: pointer;font-size:12px;"
          >机器人信息</span
        >
      </div>
      <RobotInfo 
      :robotTab="robotTab"
      :robotType="robotType"
      ref="robotinfo"
      />
    </div>
    <div class="consoleItem card6" :class="isCollapse?'translate':'translateX'">
      <div class="backgroundimg">
        <!-- <img src="../assets/img/border.png" alt="" /> -->
        <div class="content-trigon-zhijiao"></div>
      </div>
      <div class="labelBox">
        <span>
          <i class="tunnelIcon"></i>
          <span class="tunnelText">{{equipmentOperationtext}}</span>
        </span>
        <span class="robotIcon">
          <el-dropdown @command="selectTunnelCommand6">
            <span class="el-dropdown-link">
              <i class="el-icon-s-tools" style="color:#fff;font-size:20px;"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item command="1">设备操作信息</el-dropdown-item>
              <el-dropdown-item command="2">高速监控画面</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </span>
      </div>
      <DevOption :equipmentOperation="equipmentOperation"/>
    </div>
    <videoShow ref="videoShow" />
  </div>
</template>

<script>
import Navbar from "../Navbar";
import CarInfo from "./components/CarInfo";
import FanInfo from "./components/FanInfo";
import LightingInfo from "./components/LightingInfo";
import RobotInfo from "./components/RobotInfo";
import DevOption from "./components/DevOption";
import videoShow from '@/tunnel/components/video/VideoShow'
export default {
  props: {
    devTypeList: {
      type: Array,
      default: [],
    },
    groupControlVisible: {
      type: Boolean,
      default: false,
    },
    isCollapse: {
      type: Boolean,
      default: true,
    }
  },
  components: {
    CarInfo,
    FanInfo,
    LightingInfo,
    RobotInfo,
    DevOption,
    Navbar,
    videoShow
  },
  data() {
    return {
      robotTab: "info", // 机器人显示信息切换  monitor：监控画面   info：机器人信息
      selectTunnelDevice: '0', //设备筛选后的值
      select: 1,
      robotType: 'up', //判断机器人上下行，up：上行  down：下行
      trafficFlowStatistics:'info',//车流量显示信息切换  monitor：监控画面   info：机器人信息
      fanAndCovi:'info',//风机显示信息切换  monitor：监控画面   info：机器人信息
      LightingStatistics:'info',//照明与安全显示信息切换  monitor：监控画面   info：机器人信息
      equipmentOperation:'info',//设备操作与监控显示信息切换  monitor：监控画面   info：机器人信息
      robotTypetext:'',
      trafficFlowStatisticstext:'车辆信息',
      fanAndCovitext:'风机信息',//风机显示信息切换  monitor：监控画面   info：机器人信息
      LightingStatisticstext:'照明信息',//照明与安全显示信息切换  monitor：监控画面   info：机器人信息
      equipmentOperationtext:'设备操作信息',//设备操作与监控显示信息切换  monitor：监控画面   info：机器人信息
    };
  },
  methods:{
    /**
     * 设备类型控制设备显示隐藏
     * @param item 选中的设备类型数据
     */
    childInit() {
      this.$refs.fanInfo.init()
    },
    lightInit() {
      this.$refs.lightingInfo.getData()
    },
    navClickFn(item) {
      this.$set(item, "isSelect", !item.isSelect);
      // this.$parent.editDev(item);
    },
    showVideo(str) {
      this.$refs.videoShow.show(str)
    },
    selectTunnelDeviceClickFn(item) {
      this.selectTunnelDevice = item;
    },
    /**
     * 群控策略模态框打开
     */
    groupControlClick() {
      this.$emit("groupControlClick");
    },
    /**
     * 返回信息板中的设备
     */
    selectTunnelCommand(){
      this.selectTunnelDevice = '0';
    },
    selectTunnelCommand2(command){
      if(command == 1 || command == 3) {
        this.$refs.robotinfo.setSelect(command);
        this.robotTab = 'info';
        if(command == 1){
          this.robotType = 'up';
        } else {
          this.robotType = 'down';
        }
      }else {
        this.robotTab = 'monitor';
        if(command == 2){
          this.robotType = 'up';
        } else {
          this.robotType = 'down';
        }
      }
    },
    selectTunnelCommand3(command){
      if(command == 1) {
        this.trafficFlowStatistics = 'info';
        this.trafficFlowStatisticstext = '车辆信息';
      }else {
        this.trafficFlowStatistics = 'monitor';
        this.trafficFlowStatisticstext = '车流量';
      }
    },
    selectTunnelCommand4(command){
      if(command == 1) {
        this.fanAndCovi = 'info';
        this.fanAndCovitext = '风机信息';
      }else {
        this.fanAndCovi = 'monitor';
        this.fanAndCovitext = 'CO/VI检测器';
      }
    },
    selectTunnelCommand5(command){
      if(command == 1) {
        this.LightingStatistics = 'info';
        this.LightingStatisticstext = '照明信息';
      
      }else {
        this.LightingStatistics = 'monitor';
        this.LightingStatisticstext = '安全预警';
      }
    },
    selectTunnelCommand6(command){
      if(command == 1) {
        this.equipmentOperation = 'info';
        this.equipmentOperationtext = '设备操作信息';
      }else {
        this.equipmentOperation = 'monitor';
        this.equipmentOperationtext = '高速监控画面';
      }
    },
  }
};
</script>
<style scoped>
.el-dropdown-menu__item:focus, .el-dropdown-menu__item:not(.is-disabled):hover {
    background-color: #545454;
    color: #fff;
}
</style>
<style lang="scss" scoped>
.el-dropdown-menu {
  background-color: rgba(47,54,61,1);
  .el-dropdown-menu__item {
    color:#fff;
  }
  a {
    display: block
  }
}
.consoleAreaBox {
  color: #fff;
  display: flex;
  justify-content: space-between;
  .consoleItem {
    height: 200px;
    width: 351px;
    position: fixed;
    z-index: 999;
    border-radius: 3px;
    background-color: #191d21;
    border-left: 1px solid #58cfff;
    padding: 10px 0;
    opacity: 0.9;
    transition:transform 1s ease-out;
    -moz-transition: -moz-transform 1s ease-out; /* Firefox 4 */
    -webkit-transition: -webkit-transform 1s ease-out; /* Safari and Chrome */
    -o-transition:-o-transform 1s ease-out; /* Opera */
    .backgroundimg {
      width: 100%;
      height: 100%;
      position: absolute;
      left: 0;
      top: 0;
      z-index: -1;
      .content-trigon-zhijiao {
				width: 0px;
				height: 0px;
				border-color:   #191d21 #f4df58 #f4df58 #191d21;
				border-width: 6px 6px 6px 6px;
				border-style: solid;
        position: absolute;
        bottom: 0;
        right: 0;
			}
    }
    .labelBox {
      height: 35px;
      padding-left: 10px;
      color: #fff;
      text-align: left;
      &.robotLabel {
        height: 28px;
        line-height: 0px;
        display: flex;
        justify-content: space-around;
      }
      .robotIcon {
        float: right;
        margin-right: 10px;
        margin-top: 7px;
        &.tunnelcancel{
          margin-right: 35px;
          margin-top: 6px;
          font-size: 20px;
          cursor:pointer;
        }
      }
      .tunnelIcon {
        width: 4px;
        height: 20px;
        background-color: #f4df58;
        float: left;
        margin-left: -10px;
        margin-top: 5px;
      }
      .tunnelText {
        float: left;
        margin-top: 7px;
        color: #f4df58;
      }
    }
  }
  .card1 {
    left: 0;
    top: 18%;
  }
  .card2 {
    left: 0;
    top: 40.5%;
  }
  .card3 {
    left: 0;
    top: 63%;
  }
  .card4 {
    right: 0;
    top: 18%;
  }
  .card5 {
    right: 0;
    top: 40.5%;
  }
  .card6 {
    right: 0;
    top: 63%;
  }
  .translateX{
    transform: translateX(100%);
  }
  .translateY{
    transform: translateX(-100%);
  }
  .translate{
    transform: translateX(0);
  }
}
</style>