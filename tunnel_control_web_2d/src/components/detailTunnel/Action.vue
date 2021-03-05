<template>
  <div class="actionBox">
    <el-form :inline="true" :model="formInline" class="demo-form-inline">
      <el-form-item>
        <el-select
          v-model="formInline.tunnelId"
          placeholder="请选择"
          size="mini"
          @change="changeTunnel"
        >
          <el-option
            v-for="item in tunnelList"
            :label="item.tunnelName"
            :value="item.tunnelId"
            :key="item.tunnelId"
          ></el-option>
        </el-select>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      formInline: {
        tunnelId: "", // 选中的隧道id
      },
    };
  },
  props: {
    tunnelList: {
      type: Array,
      default: [],
    },
  },
  watch: {
    tunnelList: {
      immediate: true,
      deep: true,
      handler(newValue = []) {
        if (newValue.length) {
          this.formInline.tunnelId = newValue[0].tunnelId;
        } else {
          this.formInline.tunnelId = "";
        }
      },
    },
  },
  methods: {
    /**
     * 把选中的隧道id传出去
     * @param value 隧道id
     */
    changeTunnel(value) {
      this.$emit("changeTunnel", value);
    },
  },
};
</script>
<style scoped>
.actionBox /deep/ .el-input__inner {
  background-color: transparent;
  border-color: #19292e;
  box-shadow:inset 0px 0px 15px 1px #75A6AC;
  border-radius: 0;
  color: #b2faff;
}
</style>
<style lang="scss" scoped>
.actionBox {
  padding-left: 20px;
  .el-form-item {
    margin-bottom: 0;
  }
}
</style>