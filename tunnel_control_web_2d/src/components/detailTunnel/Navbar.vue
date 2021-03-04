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
    <el-button
      type="text"
      class="btnCommon"
      @click="$emit('groupControlClick')"
    >
      <i class="iconCommon strategy"></i>
      群控策略
    </el-button>
    <div class="divider"></div>
    <div class="searchBox">
      <el-button
        type="text"
        class="btnCommon"
        @click="isShowSearch = !isShowSearch"
      >
        <i class="iconCommon search"></i>
        搜索
      </el-button>
      <div class="searchContext" v-if="isShowSearch">
        <el-form
          :model="searchFrom"
          status-icon
          ref="ruleForm"
          label-width="90px"
          class="demo-ruleForm"
        >
          <el-form-item label="设备类型：" prop="devType">
            <el-select
              v-model="searchFrom.devType"
              placeholder="请选择"
              style="width: 100%"
            >
              <el-option
                v-for="item in devTypeList"
                :key="item.deviceTypeId"
                :label="item.dictLabel"
                :value="item.deviceTypeId"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="设备编号：" prop="devCode">
            <el-input
              type="text"
              v-model="searchFrom.devCode"
              autocomplete="off"
            ></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm">搜索</el-button>
            <el-button @click="closeSearchBox">取消</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </el-button-group>
  <!--  -->
</template>

<script>
export default {
  props: {
    devTypeList: {
      type: Array,
      default: [],
    },
  },
  data() {
    return {
      searchFrom: {
        devType: "",
        devCode: "",
      },
      isShowSearch: false,
    };
  },
  methods: {
    navClickFn(item) {
      this.$emit("navClickFn", item);
    },
    closeSearchBox() {
      this.isShowSearch = false;
      this.searchFrom = {
        devType: "",
        devCode: "",
      };
    },
    submitForm() {
      if (
        this.searchFrom &&
        this.searchFrom.devType &&
        this.searchFrom.devCode
      ) {
        this.$emit("searchDevPosition", this.searchFrom);
        this.closeSearchBox();
      } else {
        this.$message.info("请选择要查看的设备数据");
      }
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
.searchBox {
  position: relative;
  display: inline-block;
  .searchContext {
    position: absolute;
    top: 50px;
    right: 5px;
    width: 400px;
    padding: 20px 20px 0 20px;
    background-color: #fff;
    border-radius: 5px;
    z-index: 999;
    &::after {
      content: "";
      position: absolute;
      right: 20px;
      top: -8px;
      width: 0;
      height: 0;
      border-bottom: 8px solid #fff;
      border-right: 8px solid transparent;
      border-left: 8px solid transparent;
    }
  }
}
</style>