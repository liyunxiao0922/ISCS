<template>
  <el-button-group>
    <el-button
      type="text"
      icon="el-icon-back"
      class="backBtn btnCommon"
    ></el-button>
    <el-button
      v-for="item in devTypeList"
      :key="item.deviceTypeId"
      type="text"
      :class="item.isSelect ? 'active btnCommon' : 'btnCommon'"
      @click="navClickFn(item)"
    >
      <img :src="item.deviceImageAddress" alt="设备类型icon丢失了" />
      {{ item.dictLabel }}
    </el-button>
    <el-button type="text" class="btnCommon">
      <i class="iconCommon camera"></i>
      摄像机
    </el-button>
    <el-button type="text" class="btnCommon">
      <i class="iconCommon telephone"></i>
      电话
    </el-button>
    <el-button type="text" class="btnCommon">
      <i class="iconCommon fire"></i>
      火灾
    </el-button>
    <div class="divider"></div>
    <el-button type="text" class="btnCommon">
      <i class="iconCommon strategy"></i>
      群控策略
    </el-button>
    <div class="divider"></div>
    <el-button type="text" class="btnCommon">
      <i class="iconCommon search"></i>
      搜索
    </el-button>
  </el-button-group>
</template>

<script>
export default {
  props: {
    devTypeList: {
      type: Array,
      default: [],
    },
  },
  methods: {
    navClickFn(item) {
      this.$emit("navClickFn", item);
    },
  },
};
</script>

<style lang="scss" scoped>
.divider {
  float: left;
  width: 1px;
  height: 1.5em;
  margin: 10px 0;
  vertical-align: middle;
  position: relative;
  background-color: #06383c;
}
.iconCommon {
  display: inline-block;
  width: 25px;
  height: 25px;
  vertical-align: middle;
  background-image: url(./assets/img/titleIcon.png);
  background-repeat: no-repeat;
  &.telephone {
    background-position: -35px 0px;
  }
  &.camera {
    background-position: 0px 0px;
  }
  &.fire {
    background-position: -70px 0px;
  }
  &.strategy {
    background-position: -105px 0px;
  }
  &.search {
    background-position: -140px 0px;
  }
}
.btnCommon {
  padding: 10px !important;
  margin: 0 5px !important;
  color: #fff;
  &.active {
    background-color: #182327;
    border-radius: 30px;
    overflow: hidden;
  }
  img {
    width: 25px;
    height: 25px;
    vertical-align: middle;
  }
  &.backBtn {
    font-size: 25px;
    margin: 0 !important;
  }
}
</style>