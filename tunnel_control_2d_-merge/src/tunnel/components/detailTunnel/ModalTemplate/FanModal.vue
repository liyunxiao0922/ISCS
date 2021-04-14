<template>
  <el-dialog
    title="隧道风机控制面板"
    :visible.sync="fanVisible"
    width="18%"
    :before-close="handleClose"
  >
    <el-tabs v-model="activeName">
      <el-tab-pane label="手动控制" name="manual">
        <el-form label-width="65px">
          <!-- <p> -->
          <div>
            <el-radio-group v-model="form.equipTypeOperationId" style="margin-top: 5px">
              <el-radio 
                v-for="item in activeDevStatusList"
                :key="item.equipTypeOperationId"
                :label="+item.equipTypeOperationId"
                >{{ item.equipTypeOperationName }}</el-radio>
            </el-radio-group>
          </div>
          <!-- </p> -->
          
        </el-form>
      </el-tab-pane>
      <el-tab-pane label="自动控制" name="automatic">
        <el-form ref="form" :model="form" label-width="100px">
          <el-col :span="12">
            <el-form-item label="CO参考值上限">
              <el-input v-model="form.name"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="VI参考值上限">
              <el-input v-model="form.name"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="CO参考值下限">
              <el-input v-model="form.name"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="VI参考值下限">
              <el-input v-model="form.name"></el-input>
            </el-form-item>
          </el-col>
        </el-form>
      </el-tab-pane>
    </el-tabs>
    <div style="text-align: center; margin-top: 12px;margin-bottom:14px">
      <el-button type="primary" style="width: 190px; height: 30px; background: #2E373F; border: #2E373F;" size="mini" @click="saveWorkStatusFn">确定</el-button>
    </div>
  </el-dialog>
</template>

<script>
export default {
  props: {
    fanVisible: {
      type: Boolean,
      default: false,
    },
    fanActiveRow: {
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
      activeName: "manual",
    };
  },
  watch: {
    fanActiveRow: {
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
      this.$emit("fanClickClose");
    },
    /**
     * 修改状态接口
     */
    saveWorkStatusFn() {
      if (this.activeName === "manual") {
        if (this.fanActiveRow.equipTypeOperationId == "17") {
          if (this.form.equipTypeOperationId == "17") {
            this.$emit("fanClickClose");
          } else if (this.form.equipTypeOperationId == "18") {
            this.$message.error("操作不允许,必须停止才可以反向转动");
          } else {
            this.$emit("fanChangeData", this.form.equipTypeOperationId + '');
          }
        } else if (this.fanActiveRow.equipTypeOperationId == "18") {
          if (this.form.equipTypeOperationId == "17") {
            this.$message.error("操作不允许,必须停止才可以正向转动");
          } else if (this.form.equipTypeOperationId == "18") {
            this.$emit("fanClickClose");
          } else {
            this.$emit("fanChangeData", this.form.equipTypeOperationId + '');
          }
        } else {
          this.$emit("fanChangeData", this.form.equipTypeOperationId + '');
        }
      } else if (this.activeName === "automatic") {
        this.$message.info("自动控制保存")
      }
    },
  },
};
</script>
<style scoped>
/deep/ .el-dialog__header .el-dialog__title{
  font-size: 15px;
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
/deep/ .el-dialog__footer{
  text-align: center;
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
/deep/ .el-radio-button__inner{
  background-color: #191D21;
  color: #fff;
  border: 1px solid #66b1ff;
}
/deep/ .el-radio-button--small .el-radio-button__inner{
  padding: 9px 12px;
}
/deep/ .el-tabs__header .el-tabs__item.is-active{
  background: #191D21;
  color: #fff;
}
/deep/ .el-tabs__header .el-tabs__item{
  color: #9f9f9f;
}
/deep/ .el-dialog__body {
  padding: 6px 20px;
}
/deep/ .el-tabs__nav-wrap::after{
  background: #191D21;
}
/deep/ .el-tabs__active-bar{
  background: #fff;
}
</style>
<style lang="scss" scoped>
h3 {
  color:#fff;
}
</style>