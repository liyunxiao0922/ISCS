<template>
  <el-dialog
    title="群控策略"
    :visible.sync="groupControlVisible"
    :width="this.activeName == 'timer' ?'30%':'23%'"
    :before-close="handleClose"
  >
    <el-tabs v-model="activeName" @tab-click="handleClick" class="groupControltabs">
      <el-tab-pane label="交通群控" name="traffic">
        <Traffic 
          :activeDevStatusList="activeDevStatusList" 
          :equipType="equipType" 
          v-on="$listeners"
        />
      </el-tab-pane>
      <el-tab-pane label="通风群控" name="improveAirCirculation">
        <ImproveAirCirculation
          :activeDevStatusList="activeDevStatusList"
          :equipType="equipType"
          v-on="$listeners"
        />
      </el-tab-pane>
      <el-tab-pane label="照明群控" name="lighting">
        <Lighting
          :activeDevStatusList="activeDevStatusList"
          :strengthenactiveDevStatusList="strengthenactiveDevStatusList"
          :equipType="equipType"
          :strengthenequipType="strengthenequipType"
          v-on="$listeners"
        />
      </el-tab-pane>
      <el-tab-pane label="照明自动调节" name="timer">
        <TimerLighting />
      </el-tab-pane>
    </el-tabs>
  </el-dialog>
</template>

<script>
import Traffic from "./tabs/Traffic";
import ImproveAirCirculation from "./tabs/ImproveAirCirculation";
import Lighting from "./tabs/Lighting";
import TimerLighting from "./tabs/TimerLighting";

import { getDevStatusList } from "@/tunnel/api/tunnel";

export default {
  props: {
    groupControlVisible: {
      type: Boolean,
      default: false,
    },
    groupControlDevTypeIdList: {
      type: Array,
      default: [],
    },
  },
  watch: {
    groupControlDevTypeIdList: {
      immediate: true,
      deep: true,
      handler(newValue = []) {
        for (let index = 0; index < newValue.length; index++) {
          const element = newValue[index];
          if (element.dictCode == 104) {
            this.equipType = element.dictValue;
            this.getDevStatusList();
            break;
          }
        }
      },
    },
  },
  components: {
    Traffic,
    ImproveAirCirculation,
    Lighting,
    TimerLighting,
  },
  data() {
    return {
      activeName: "traffic", // 当前选中的tab的key主要用来做切换标记
      equipType: '3', // 当前选中的设备类型id
      activeDevStatusList: [], // 当前选中的设备状态列表数据
      strengthenactiveDevStatusList: [],//加强灯光数组
      strengthenequipType:'',
    };
  },
  methods: {
    /**
     * 关闭菜单
     */
    handleClose() {
      this.$emit("groupControlClick");
    },
    /**
     * tabs切换
     */
    handleClick(tab) {
      switch (tab.name) {
        case "traffic":
          for (
            let index = 0;
            index < this.groupControlDevTypeIdList.length;
            index++
          ) {
            const element = this.groupControlDevTypeIdList[index];
            if (element.dictValue === '3') {
              this.equipType = element.dictValue;
              this.getDevStatusList();
              break;
            }
          }
          break;
        case "improveAirCirculation":
          for (
            let index = 0;
            index < this.groupControlDevTypeIdList.length;
            index++
          ) {
            const element = this.groupControlDevTypeIdList[index];
            if (element.dictValue === "7") {
              this.equipType = element.dictValue;
              this.getDevStatusList();
              break;
            }
          }
          break;
        case "lighting":
          for (
            let index = 0;
            index < this.groupControlDevTypeIdList.length;
            index++
          ) {
            const element = this.groupControlDevTypeIdList[index];
            console.log('22',element.dictValue)
            if (element.dictValue === '4') {
              console.log('11',element.dictValue)
              this.equipType = element.dictValue;
              this.getDevStatusList();
            } else if(element.dictValue === '10') {
              this.strengthenequipType = element.dictValue;
              this.getDevStatusList(1);
            }
          }
          break;
        default:
          break;
      }
    },
    getDevStatusList(flag) {
      getDevStatusList()
        .then((response) => {
          if(flag){
            this.strengthenactiveDevStatusList = response.data.filter((item) => item.equipType == this.strengthenequipType) || [];
          }else {
            console.log('1111',this.equipType)
            this.activeDevStatusList = response.data.filter((item) => item.equipType == this.equipType) || [];
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>
<style scoped>
/deep/ .el-tabs__header .el-tabs__item.is-active{
  background: #191D21;
  color: #fff;
}
/deep/ .el-tabs__item{
  color: #9f9f9f;
}
/deep/ .el-dialog__body {
  padding: 6px 20px;
}
.groupControltabs /deep/ .el-tabs__nav-wrap::after{
  background: #191D21;
}
.groupControltabs /deep/ .el-tabs__nav-wrap::after{
  background: #191D21;
}
.groupControltabs /deep/ .el-tabs__active-bar{
  background: #fff;
}
</style>
<style lang="scss" scoped>

</style>