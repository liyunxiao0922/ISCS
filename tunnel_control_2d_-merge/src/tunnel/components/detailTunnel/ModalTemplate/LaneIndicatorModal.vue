<template>
  <el-dialog
    title="车道指示灯控制面板"
    :visible.sync="laneIndicatorVisible"
    width="16%"
    :before-close="handleClose"
  >
    <el-form label-width="65px">
      <!-- <p> -->
        <div>
          <el-radio-group v-model="form.equipTypeOperationId" style="margin-top: 5px">
            <el-radio 
              v-for="item in activeDevStatusList"
              style="margin: 5px 23px 5px 4px;"
              :key="item.equipTypeOperationId"
              :label="+item.equipTypeOperationId"
            >{{ item.equipTypeOperationName }}</el-radio>
          </el-radio-group>
        </div>
      <!-- </p> -->
      <div style="text-align: center; margin-top: 12px;margin-bottom:14px">
        <el-button type="primary" style="width: 190px; height: 30px; background: #2E373F; border: #2E373F;" size="mini" @click="saveWorkStatusFn">确定</el-button>
      </div>
    </el-form>
  </el-dialog>
</template>

<script>
export default {
  props: {
    laneIndicatorVisible: {
      type: Boolean,
      default: false,
    },
    laneIndicatorActiveRow: {
      type: Object,
    },
    activeDevStatusList: {
      type: Array,
      default: [],
    },
  },
  data() {
    return {
      form: {},
    };
  },
  watch: {
    laneIndicatorActiveRow: {
      immediate: true,
      deep: true,
      handler(newValue) {
        if (newValue) {
          let obj = JSON.parse(JSON.stringify(newValue));
          if (obj.sysTunnelDirection == "2") {
            obj.deviceGroup = "下行";
          } else if (obj.sysTunnelDirection == "1") {
            obj.deviceGroup = "上行";
          } else {
            obj.deviceGroup = "";
          }
          obj.equipTypeOperationId = +obj.equipTypeOperationId;
          this.form = obj;
        }
      },
    },
  },
  methods: {
    /**
     * 关闭菜单
     */
    handleClose() {
      this.$emit("laneIndicatorClickClose");
    },
    /**
     * 修改状态接口
     */
    saveWorkStatusFn() {
      this.$emit("laneIndicatorChangeData", this.form.equipTypeOperationId);
    },
  },
};
</script>
<style scoped>
/deep/ .el-dialog__header{
  padding: 20px 10px 10px;
}
/deep/ .el-dialog__header{
  padding: 20px 10px 10px;
}
/deep/ .el-dialog__header .el-dialog__title{
  font-size: 16px;
  color: #fff;
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
/deep/ .el-form-item{
    margin-bottom: 0px;
}
/deep/ .el-input__inner {
  border: none;
  background: #191D21;
  color:#fff;
}
/deep/ .el-dialog__body {
  padding: 6px 10px;
}
/deep/ .el-dialog__footer{
  text-align: center;
}
/deep/ .el-button--info{
  background-color: #191D21;
  border: 1px solid #66b1ff;
  color: #66b1ff;
}
/deep/ .el-form-item__label {
  color: #fff;
  font-size: 12px;
}
/deep/ .el-button{
  padding: 6px 10px;
  font-size: 12px;
}
/deep/ .el-radio-button__inner{
  background-color: #191D21;
  color: #fff;
  width: 114px;
  border: 1px solid #66b1ff;
}
/deep/.el-radio {
  color: #eee;
  cursor: pointer;
  margin-right: 10px;
  margin-bottom: 10px;
}
/deep/.el-radio__input.is-checked .el-radio__inner {
    border-color: #eee;
    border-width: 2px;
    background: #1F2227;
}
/deep/.el-radio__input.is-checked+.el-radio__label {
    color: #eee;
}
/deep/.el-radio__inner {
  background: #1F2227;
}
/deep/ .el-radio__input.is-checked .el-radio__inner::after {
  /* display: none; */
  width: 5px;
  height: 5px;
}
</style>
<style lang="scss" scoped>
h3 {
  color:#fff;
}
</style>