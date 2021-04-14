<template>
  <div class="face-recognition-container">
    <div class="content">
      <p class="title">
        <span>{{value}}</span>
        <el-dropdown @command="handleCommand" trigger="click">
          <span class="icon-set el-icon-s-tools"></span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item v-for="(item, index) in temData" :key="index" :class="{active: value === item}" :command="item">{{item}}</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </p>
    </div>
    <!-- <div class="shape-top-left"></div>
    <div class="shape-top-right"></div>
    <div class="shape-bottom-left"></div>
    <div class="shape-bottom-right"></div> -->
  </div>
</template>

<script>
export default {
  props: ['value'],
  data () {
    return {
      temData: ['设备异常事件', '设备维护统计', '路网概况']
    };
  },
  created () {
  },
  computed: {
  },
  methods: {
    handleCommand (val) {
      this.$emit('selectChange', val, this.value);
    }
  }
};
</script>

<style lang="scss" scoped>
.el-dropdown-menu {
  background: #409EFF;
  border: none;
}
.el-dropdown-menu__item {
  position: relative;
  font-size: 12px;
  line-height: 26px;
  color: rgba(244, 223, 88, 1);
  &:hover {
    &::before {
      content: '';
      position: absolute;
      top: 0;
      left: 0;
      width: 4px;
      height: 26px;
      background: #67C23A;
    }
  }
}
.active {
  color: aquamarine;
  background: beige;
}
.el-popper[x-placement^=bottom] .popper__arrow, .el-popper[x-placement^=bottom] .popper__arrow:after {
  border-bottom-color: #409EFF;
}
.el-popper[x-placement^=top] .popper__arrow, .el-popper[x-placement^=top] .popper__arrow:after {
  border-top-color: #409EFF;
}
</style>

<style lang="scss" scoped>
@import '@/common/scss/mixin.scss';
.face-recognition-container {
  position: relative;
  width: 100%;
  height: 315px;
  z-index: 2;
  top: 50px;
  // background: #0c1943;
  opacity: 0.8;
  .title {
    position: relative;
    margin: 11px 10px;
    color: rgba(244, 223, 88, 1);
    font-size: 15px;
    /deep/ .el-dropdown {
      position: absolute;
      top: 0;
      right: 0;
      .icon-set {
        color: rgba(244, 223, 88, 1);
        font-size: 15px;
        cursor: pointer;
      }
    }
  }
  // .content {
  //   width: 100%;
  //   height: 100%;
  //   font-size: 20px;
  //   color: #fff;
  //   position: relative;
  //   border: 2px solid;
  //   border-image: linear-gradient(45deg, #033467, #022E5B) 1;
  //   clip-path: inset(0 round 2px);
  //   animation: huerotate 6s infinite linear;
  //   filter: hue-rotate(360deg);
  //   background: inherit;
  // }
  .content {
    width: 100%;
    height: 100%;
    color:white;
    padding: 1px;
    box-shadow: 0 0 10px rgba(100,200,255,1) inset;
    border: 1px solid rgb(88, 207, 255);
    background: rgba(28,38,65,1);
    box-sizing: border-box;
  }
  @keyframes huerotate {
    0% {
      filter: hue-rotate(0deg)
    }
    100% {
      filter: hue-rotate(360deg);
    }
  }
  &:before {
    content: '';
    position: absolute;
    top: 10px;
    left: 0;
    width: 4px;
    height: 18px;
    background: rgb(244, 223, 88);
  }
  &::after {
    content: '';
    position: absolute;
    right: 0;
    bottom: 0;
    width: 0;
    height: 0;
    border-bottom: 10px solid rgb(244, 223, 88);
    border-left: 10px solid transparent;
  }
}
</style>
