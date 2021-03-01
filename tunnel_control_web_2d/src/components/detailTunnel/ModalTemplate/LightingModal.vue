<template>
  <el-dialog
    title="灯光控制面板"
    :visible.sync="lightingVisible"
    width="40%"
    :before-close="handleClose"
  >
    <el-form label-width="80px">
      <h3>设备信息</h3>
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="设备编号:">
            <el-input v-model="form.deviceCode" readonly></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="设备区域:">
            <el-input v-model="form.deviceGroup" readonly></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <h3>更改状态</h3>
      <el-form-item label="灯光亮度:">
        <el-radio-group v-model="form.workStatus" size="small">
          <el-radio-button label="0007">100%</el-radio-button>
          <el-radio-button label="0008">50%</el-radio-button>
          <el-radio-button label="0009">关闭</el-radio-button>
        </el-radio-group>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button size="smail" @click="$emit('lightingClickClose')"
        >取 消</el-button
      >
      <el-button size="smail" type="primary" @click="saveWorkStatusFn"
        >确 定</el-button
      >
    </span>
  </el-dialog>
</template>

<script>
export default {
  props: {
    lightingVisible: {
      type: Boolean,
      default: false,
    },
    lightingActiveRow: {
      type: Object,
    },
  },
  data() {
    return {
      form: {},
    };
  },
  watch: {
    lightingActiveRow: {
      immediate: true,
      deep: true,
      handler(newValue) {
        if (newValue) {
          let obj = JSON.parse(JSON.stringify(newValue));
          if (obj.deviceGroup === "0") {
            obj.deviceGroup = "下行";
          } else if (obj.deviceGroup === "1") {
            obj.deviceGroup = "上行";
          } else {
            obj.deviceGroup = "";
          }

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
      this.$emit("lightingClickClose");
    },
    /**
     * 修改状态接口
     */
    saveWorkStatusFn() {
      this.$emit("lightingChangeData", this.form.workStatus);
    },
  },
};
</script>
<style scoped>
/deep/ .el-input__inner {
  border: none;
}
</style>
<style lang="scss" scoped>
</style>