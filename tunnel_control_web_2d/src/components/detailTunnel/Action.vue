<template>
  <div class="actionBox">
    <el-form :inline="true" :model="formInline" class="demo-form-inline">
      <el-form-item>
        <el-select
          v-model="formInline.region"
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
      <!-- <el-form-item label="设备名称">
        <el-input
          v-model="formInline.user"
          placeholder="审批人"
          size="mini"
        ></el-input>
      </el-form-item>
      <el-form-item label="设备编号">
        <el-input
          v-model="formInline.user"
          placeholder="审批人"
          size="mini"
        ></el-input>
      </el-form-item> -->
      <!-- <el-form-item>
      <el-button type="primary" @click="onSubmit" size="mini">查询</el-button>
    </el-form-item> -->
    </el-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      formInline: {
        user: "",
        region: "",
      },
      // tunnelList: [
      //   {
      //     tunnelName: "xx",
      //     tunnelId: "1",
      //   },
      //   {
      //     tunnelName: "xx",
      //     tunnelId: "2",
      //   },
      // ],
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
          this.formInline.region = newValue[0].tunnelId;
        } else {
          this.formInline.region = "";
        }
      },
    },
  },
  methods: {
    onSubmit() {
      console.log("submit!");
    },
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