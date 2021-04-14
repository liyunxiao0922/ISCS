<template>
  <el-collapse v-model="activeNames" style="border: 0">
    <el-collapse-item title="上行" name="1">
      <div class="cardContextBox">
          <div
            class="btnItem"
            v-for="item in activeDevStatusList"
            :key="item.equipTypeOperationId"
            @click="changeDevTypeStatusFn(item, 1)"
          >
            <img v-if="item.equipTypeOperationId == '17'" class="positive" :src="item.imageAddress" alt="" />
            <img v-else-if="item.equipTypeOperationId == '18'" class="reverse" :src="item.imageAddress" alt="" />
            <img v-else :src="item.imageAddress" alt="" />
            <div>{{ item.equipTypeOperationName }}</div>
          </div>
        </div>
    </el-collapse-item>
    <el-collapse-item title="下行" name="2">
      <div class="cardContextBox">
          <div
            class="btnItem"
            v-for="item in activeDevStatusList"
            :key="item.equipTypeOperationId"
            @click="changeDevTypeStatusFn(item, 2)"
          >
            <img v-if="item.equipTypeOperationId == '17'" class="positive" :src="item.imageAddress" alt="" />
            <img v-else-if="item.equipTypeOperationId == '18'" class="reverse" :src="item.imageAddress" alt="" />
            <img v-else :src="item.imageAddress" alt="" />
            <div>{{ item.equipTypeOperationName }}</div>
          </div>
        </div>
    </el-collapse-item>
    <el-collapse-item title="全隧道" name="3">
      <div class="cardContextBox">
          <div
            class="btnItem"
            v-for="item in activeDevStatusList"
            :key="item.equipTypeOperationId"
            @click="changeDevTypeStatusFn(item, 3)"
          >
            <img v-if="item.equipTypeOperationId == '17'" class="positive" :src="item.imageAddress" alt="" />
            <img v-else-if="item.equipTypeOperationId == '18'" class="reverse" :src="item.imageAddress" alt="" />
            <img v-else :src="item.imageAddress" alt="" />
            <div>{{ item.equipTypeOperationName }}</div>
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
    equipType: {
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
</style>
<style lang="scss" scoped>
.cardContextBox{
  display: flex;
  justify-content: space-around;
  flex-wrap: wrap;
  text-align: center;
  color: #fff;
}
@keyframes positiveRotate {
  from {
    transform: rotate(0deg);
  }

  to {
    transform: rotate(360deg);
  }
}
@keyframes rotateReverse {
  from {
    transform: rotate(360deg);
  }

  to {
    transform: rotate(0deg);
  }
}
.positive {
  animation: positiveRotate 3s linear infinite;
}
.reverse {
  animation: rotateReverse 3s linear infinite;
}
</style>