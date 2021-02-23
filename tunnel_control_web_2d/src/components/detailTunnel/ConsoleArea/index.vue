<template>
  <div class="consoleAreaBox">
    <div class="consoleItem">
      <div class="backgroundimg">
        <img src="../assets/img/border.png" alt="" />
      </div>
      <div class="labelBox">
        <span
          ><span class="tunneliconfont tunneliconfont-dot1"></span
          >车辆信息</span
        >
      </div>
      <CarInfo />
    </div>
    <div class="consoleItem">
      <div class="backgroundimg">
        <img src="../assets/img/border.png" alt="" />
      </div>
      <div class="labelBox">
        <span
          ><span class="tunneliconfont tunneliconfont-dot1"></span
          >风机信息</span
        >
      </div>
      <FanInfo />
    </div>
    <div class="consoleItem">
      <div class="backgroundimg">
        <img src="../assets/img/border.png" alt="" />
      </div>
      <div class="labelBox">
        <span
          ><span class="tunneliconfont tunneliconfont-dot1"></span
          >照明信息</span
        >
      </div>
      <LightingInfo />
    </div>
    <div class="consoleItem">
      <div class="backgroundimg">
        <img src="../assets/img/border.png" alt="" />
      </div>
      <div class="labelBox">
        <span
          ><span class="tunneliconfont tunneliconfont-dot1"></span
          >机器人信息</span
        >
      </div>
      <RobotInfo />
    </div>
    <div class="consoleItem">
      <div class="backgroundimg">
        <img src="../assets/img/border.png" alt="" />
      </div>
      <div class="labelBox">
        <span
          ><span class="tunneliconfont tunneliconfont-dot1"></span
          >设备操作信息</span
        >
      </div>
      <DevOption />
    </div>
  </div>
</template>

<script>
import CarInfo from "./components/CarInfo";
import FanInfo from "./components/FanInfo";
import LightingInfo from "./components/LightingInfo";
import RobotInfo from "./components/RobotInfo";
import DevOption from "./components/DevOption";
export default {
  components: {
    CarInfo,
    FanInfo,
    LightingInfo,
    RobotInfo,
    DevOption,
  },
};
</script>

<style lang="scss" scoped>
.consoleAreaBox {
  color: #fff;
  display: flex;
  justify-content: space-between;
  .consoleItem {
    height: 100%;
    width: 19.5%;
    position: relative;
    border-radius: 3px;
    .backgroundimg {
      width: 100%;
      height: 100%;
      position: absolute;
      left: 0;
      top: 0;
      z-index: -1;
      img {
        width: 100%;
        height: 100%;
      }
    }
    .labelBox {
      height: 35px;
      padding-left: 10px;
      color: #fff;
      text-align: center;
    }
  }
}
</style>