<template>
  <el-collapse v-model="activeNames" style="border: 0">
    <el-collapse-item title="上行" name="1">
      <div>
        <div class="cardContextBox">
          <span>普通灯光：</span>
          <span
            class="btnItem"
            v-for="item in activeDevStatusList"
            :key="item.equipTypeOperationId"
            @click="changeDevTypeStatusFn(item, 1)"
          >
            <img :src="item.imageAddress" alt="" />
            <div>{{ item.equipTypeOperationName }}</div>
          </span>
        </div>
        <div class="cardContextBox">
          <span>加强灯光：</span>
          <span
            class="btnItem"
            v-for="item in strengthenactiveDevStatusList"
            :key="item.equipTypeOperationId"
            @click="changeStrengthenDevTypeStatusFn(item, 1)"
          >
            <img :src="item.imageAddress" alt="" />
            <div>{{ item.equipTypeOperationName }}</div>
          </span>
        </div>
      </div>
    </el-collapse-item>
    <el-collapse-item title="下行" name="2">
      <div>
        <div class="cardContextBox">
          <span>普通灯光：</span>
          <span
            class="btnItem"
            v-for="item in activeDevStatusList"
            :key="item.equipTypeOperationId"
            @click="changeDevTypeStatusFn(item, 2)"
          >
            <img :src="item.imageAddress" alt="" />
            <div>{{ item.equipTypeOperationName }}</div>
          </span>
        </div>
        <div class="cardContextBox">
          <span>加强灯光：</span>
          <span
            class="btnItem"
            v-for="item in strengthenactiveDevStatusList"
            :key="item.equipTypeOperationId"
            @click="changeStrengthenDevTypeStatusFn(item, 2)"
          >
            <img :src="item.imageAddress" alt="" />
            <div>{{ item.equipTypeOperationName }}</div>
          </span>
        </div>
      </div>
    </el-collapse-item>
    <el-collapse-item title="全隧道" name="3">
      <div>
        <div class="cardContextBox">
          <span>普通灯光：</span>
          <span
            class="btnItem"
            v-for="item in activeDevStatusList"
            :key="item.equipTypeOperationId"
            @click="changeDevTypeStatusFn(item, 3)"
          >
            <img :src="item.imageAddress" alt="" />
            <div>{{ item.equipTypeOperationName }}</div>
          </span>
        </div>
        <div class="cardContextBox">
          <span>加强灯光：</span>
          <span
            class="btnItem"
            v-for="item in strengthenactiveDevStatusList"
            :key="item.equipTypeOperationId"
            @click="changeStrengthenDevTypeStatusFn(item, 3)"
          >
            <img :src="item.imageAddress" alt="" />
            <div>{{ item.equipTypeOperationName }}</div>
          </span>
        </div>
      </div>
    </el-collapse-item>
  </el-collapse>
</template>

<script>
import { editDevTypeStatus } from "@/tunnel/api/tunnel";
export default {
  props: {
    activeDevStatusList: {
      type: Array,
      default: [],
    },
    strengthenactiveDevStatusList: {
      type: Array,
      default: [],
    },
    equipType: {
      type: String,
    },
    strengthenequipType: {
      type: String,
    },
  },
  data() {
    return {
      type: '',
      activeNames: ['1','2','3']
    };
  },
  methods: {
    /**
     * 修改设备类型的状态
     * @param dev   设备类型状态数据
     * @param groupType  分组状态
     */
    changeDevTypeStatusFn(dev, sysTunnelDirection) {
      var tunnelIllumination = 0;
      editDevTypeStatus({
        type: this.equipType,
        sysTunnelDirection: sysTunnelDirection,
        equipTypeOperationId: dev.equipTypeOperationId,
        tunnelIllumination: tunnelIllumination
      })
        .then((response) => {
          this.$emit("groupControlSave", {devObj:dev,sysTunnelDirection,type: this.equipType});
        })
        .catch((error) => {
          console.log(error);
        });
    },
    changeStrengthenDevTypeStatusFn(dev, sysTunnelDirection) {
      var tunnelIllumination = 0;
      editDevTypeStatus({
        type: this.strengthenequipType,
        sysTunnelDirection: sysTunnelDirection,
        equipTypeOperationId: dev.equipTypeOperationId,
        tunnelIllumination: tunnelIllumination
      })
        .then((response) => {
          this.$emit("groupControlSave", {devObj:dev,sysTunnelDirection,type: this.equipType});
        })
        .catch((error) => {
          console.log(error);
        });
    },
    setType(num) {
      this.type = num;
    }
  },
};
</script>

<style scoped>
/deep/ .el-collapse-item__header{
  background-color: #191D21;
  color:#fff;
  width: 66px;
  height: 30px;
  border: 0;
}
/deep/ .el-dialog__body {
  padding: 6px 20px;
}
/deep/ .el-dialog__headerbtn {
    position: absolute;
    top: 11px;
    right: 20px;
    padding: 0;
    background: 0 0;
    border: none;
    outline: 0;
    cursor: pointer;
    font-size: 16px;
}
/deep/ .el-collapse-item__wrap{
    border: 0;
    background-color: #191D21;
}
/deep/ .el-form-item__label {
  color: #fff;
  font-size: 12px;
}
/deep/ .el-collapse-item__content{
  padding-bottom: 10px;
}
</style>
<style lang="scss" scoped>
.cardContextBox{
  display: flex;
  justify-content: start;
  text-align: center;
  color: #fff;
  margin-bottom: 10px;
  .btnItem{
    cursor: pointer;
    margin-right: 75px;
  }
  .btnItem:last-of-type{
    margin-right: 0;
  }
}
</style>