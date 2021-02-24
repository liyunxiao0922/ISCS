<template>
  <ul class="devoptionBox">
    <li class="organizationBox">
      <span>控制机构：</span>
      <el-select v-model="value" clearable placeholder="请选择" size="mini">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        >
        </el-option>
      </el-select>
    </li>
    <li class="devBox">
      <span>通风模式：</span>
      <el-radio-group v-model="value1" size="small" style="margin-right: 10px">
        <el-radio-button label="自动"></el-radio-button>
        <el-radio-button label="手动"></el-radio-button>
      </el-radio-group>
      <span>照明模式：</span>
      <el-radio-group v-model="value2" size="mini">
        <el-radio-button label="自动"></el-radio-button>
        <el-radio-button label="手动"></el-radio-button>
      </el-radio-group>
    </li>
  </ul>
</template>

<script>
export default {
  data() {
    return {
      options: [
        {
          value: "1",
          label: "1分中心",
        },
        {
          value: "2",
          label: "1中心",
        },
        {
          value: "3",
          label: "2分中心",
        },
        {
          value: "4",
          label: "2中心",
        },
        {
          value: "5",
          label: "3分中心",
        },
      ],
      value: "",
      value1: "自动",
      value2: "自动",
    };
  },
};
</script>
<style scoped>
.devoptionBox /deep/ .el-input__inner {
  background-color: transparent;
  border: 0;
  border-bottom: 1px solid #19292e;
  width: 100%;
}
.devoptionBox /deep/ .el-select {
  width: calc(100% - 80px);
}
.devoptionBox /deep/ .el-radio-button__inner {
  padding: 5px 10px;
}
</style>
<style lang="scss" scoped>
.devoptionBox {
  margin: 0;
  padding: 0 10px 10px 20px;
  list-style: none;
  color: #b2faff;
  .organizationBox {
    line-height: 40px;
    font-size: 12px;
  }
  .devBox {
    line-height: 40px;
    font-size: 12px;
    margin-top: 10px;
  }
}
</style>