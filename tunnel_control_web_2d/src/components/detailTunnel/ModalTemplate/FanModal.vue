<template>
  <el-dialog
    title="隧道风机控制面板"
    :visible.sync="fanVisible"
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
      <el-form-item label="运行状态:">
        <el-radio-group v-model="form.workStatus" size="small">
          <el-radio-button label="0000">正向</el-radio-button>
          <el-radio-button label="0001">反向</el-radio-button>
          <el-radio-button label="0011">停止</el-radio-button>
        </el-radio-group>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button size="smail" @click="$emit('fanClickClose')">取 消</el-button>
      <el-button size="smail" type="primary" @click="saveWorkStatusFn"
        >确 定</el-button
      >
    </span>
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
  },
  data() {
    return {
      form: {},
    };
  },
  watch: {
    fanActiveRow: {
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
      this.$emit("fanClickClose");
    },
    /**
     * 修改状态接口
     */
    saveWorkStatusFn() {
      if (this.fanActiveRow.workStatus === "0000") {
        if (this.form.workStatus === "0000") {
          this.$emit("fanClickClose");
        } else if (this.form.workStatus === "0001") {
          this.$message.error("操作不允许,必须停止才可以反向转动");
        } else {
          this.$emit("fanChangeData", this.form.workStatus);
        }
      } else if (this.fanActiveRow.workStatus === "0001") {
        if (this.form.workStatus === "0000") {
          this.$message.error("操作不允许,必须停止才可以正向转动");
        } else if (this.form.workStatus === "0001") {
          this.$emit("fanClickClose");
        } else {
          this.$emit("fanChangeData", this.form.workStatus);
        }
      } else {
        this.$emit("fanChangeData", this.form.workStatus);
      }
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