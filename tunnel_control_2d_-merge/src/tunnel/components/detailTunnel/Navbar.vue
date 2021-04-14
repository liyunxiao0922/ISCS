<template>
  <el-button-group>
    <span 
        v-for="item in devTypeList"
        :key="item.dictCode"
    >
        <!-- v-if="item.selectTunnelDevice == selectTunnelDevice" -->
      <el-button
        :id="'a' + item.dictCode"
        type="text"
        :class="showFlag(item.dictValue) ? 'active btnCommon' : 'btnCommon'"
        @click="navClickFn(item)"
      >
        <img :src="item.remark" alt="设备类型icon丢失了" />
        {{ item.dictLabel }}
      </el-button>
    </span>
   <!--  <el-button type="text" class="btnCommon active" @click="selectTunnelDeviceClick('1')" v-if="selectTunnelDevice === '0'">
      <img src="./assets/img/traffic.png" alt="设备类型icon丢失了" />
      <span>交通控制</span>  
    </el-button>
    <el-button type="text" class="btnCommon active" @click="selectTunnelDeviceClick('2')" v-if="selectTunnelDevice === '0'">
      <img src="./assets/img/light.png" alt="设备类型icon丢失了" />
      
      <span>照明控制</span> 
    </el-button>
    <el-button type="text" class="btnCommon active" @click="selectTunnelDeviceClick('3')" v-if="selectTunnelDevice === '0'">
      <img src="./assets/img/ventilate.png" alt="设备类型icon丢失了" />
      <span>通风控制</span> 
    </el-button>
    <el-button type="text" class="btnCommon active" @click="selectTunnelDeviceClick('4')" v-if="selectTunnelDevice === '0'">
      <img src="./assets/img/other.png" alt="设备类型icon丢失了" />
      <span>其他控制</span> 
    </el-button> -->
    <!-- <el-button type="text" class="btnCommon active" v-if=" selectTunnelDevice === '4'"> -->
    <el-button type="text" 
        :class="camera ? 'active btnCommon' : 'btnCommon'" @click="navClickFn(false, 12)">
      <i class="iconCommon camera"></i>
      摄像机
    </el-button>
    <!-- <el-button type="text" class="btnCommon active" v-if="selectTunnelDevice === '4'"> -->
    <el-button type="text" 
        :class="phone ? 'active btnCommon' : 'btnCommon'" @click="navClickFn(false, 13)">
      <i class="iconCommon telephone"></i>
      电话
    </el-button>
    <!-- <el-button type="text" class="btnCommon active" v-if="selectTunnelDevice === '4'"> -->
    <!-- <el-button type="text" class="btnCommon active">
      <i class="iconCommon fire"></i>
      火灾
    </el-button> -->
    <el-button
      type="text"
      class="btnCommon active"
      @click="$emit('groupControlClick')"
    >
      <i class="iconCommon strategy"></i>
      群控策略
    </el-button>
    
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
    selectTunnelDevice: {
      type: String,
      default: '0',
    },
  },
  data() {
    return {
      dataList: [],
      camera: true,
      phone: true
    };
  },
  methods: {
    // 点击对应的菜单
    navClickFn(item, num) {
      if(item) {
        console.log(item)
        this.$emit("navClickFn", item);
        this.$store.commit('setShow', item.dictValue - 1)
      }else {
        // this.$emit("navClickFn", item);
        this.$store.commit('setShow', num)
        switch(num) {
          case 12:
            this.camera = !this.camera;
            break;
          case 13:
            this.phone = !this.phone;
            break
          default:
            break;
        }
      }
    },
    selectTunnelDeviceClick(item){
      this.$emit('selectTunnelDeviceClickFn', item);
    },
    showFlag(str) {
      return this.$store.getters.getFlag(str);
    }
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
  display: block;
  width: 25px;
  height: 25px;
  margin: 0 auto;
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
  padding:3px 0px !important;
  width: 69px;
  font-size: 12px;
  color: #fff;
  opacity: 0.5;
  &.active {
    opacity: 1;
  }
  img {
    height: 25px;
    display: block;
    margin: 2px auto;
  }
  &.backBtn {
    font-size: 25px;
    margin: 0 !important;
  }
}
</style>