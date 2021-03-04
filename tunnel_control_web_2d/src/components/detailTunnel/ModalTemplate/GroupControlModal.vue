<template>
  <el-dialog
    title="群控策略"
    :visible.sync="groupControlVisible"
    width="800px"
    :before-close="handleClose"
    top="30px"
  >
    <el-tabs v-model="activeName" type="border-card" @tab-click="handleClick">
      <el-tab-pane label="交通群控" name="traffic">
        <Traffic :activeDevStatusList="activeDevStatusList" v-on="$listeners" />
      </el-tab-pane>
      <el-tab-pane label="通风群控" name="improveAirCirculation">
        <ImproveAirCirculation
          :activeDevStatusList="activeDevStatusList"
          v-on="$listeners"
        />
      </el-tab-pane>
      <el-tab-pane label="照明群控" name="lighting">
        <Lighting
          :activeDevStatusList="activeDevStatusList"
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

import { getDevStatusList } from "@/api/tunnel";

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
          if (element.deviceTypeCode === "104") {
            this.activeDevTypeId = element.deviceTypeId;
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
      activeDevTypeId: null, // 当前选中的设备类型id
      activeDevStatusList: [], // 当前选中的设备状态列表数据
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
            if (element.deviceTypeCode === "104") {
              this.activeDevTypeId = element.deviceTypeId;
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
            if (element.deviceTypeCode === "118") {
              this.activeDevTypeId = element.deviceTypeId;
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
            if (element.deviceTypeCode === "115") {
              this.activeDevTypeId = element.deviceTypeId;
              this.getDevStatusList();
              break;
            }
          }
          break;
        default:
          break;
      }
    },
    getDevStatusList() {
      getDevStatusList({ deviceTypeId: this.activeDevTypeId })
        .then((response) => {
          this.activeDevStatusList = response.rows ?? [];
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>
<style lang="scss" scoped>
</style>