<template>
  <div class="face-recognition-container" ref="temWrapper">
    <!-- <div class="content" v-for="(item, ind) in value" :key="ind">
      <p class="title">
        <span>{{item}}</span>
        <el-dropdown @command="handleCommand" trigger="click">
          <span class="icon-set el-icon-s-tools"></span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item v-for="(item, index) in temData" :key="index" :class="{active: value === item}" :command="item">{{item}}</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </p>
    </div>
    <dataContent :value="value"></dataContent> -->
    <div ref="wrapper" class="wrapper">
      <div class="content" ref="content">
          <p class="title">
            <span>{{value}}</span>
            <el-dropdown @command="handleCommand" trigger="click">
              <span class="icon-set el-icon-s-tools"></span>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item v-for="(item, index) in temData" :key="index" :class="{active: currentType.includes(temData[index])}" :command="item">{{item}}</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </p>
          <dataContent :value="value" :collapse="collapse" :allData="allData"></dataContent>
      </div>
      <span v-if="ind === 0 || ind === 1 || ind === 2" class="left-btn" ref="leftBtn">
        <i class="iconfont icon-shouqi-copy" v-if="collapse" @click="handleFold(ind)"></i>
        <i class="iconfont icon-shouqi" v-else @click="handleExpand(ind)"></i>
      </span>
      <span v-if="ind === 3 || ind === 4 || ind === 5" class="center-btn">
        <i class="iconfont icon-shouqi-bottom-copy" v-if="collapse" @click="handleFold(ind)"></i>
        <i class="iconfont icon-shouqi-top-copy" v-else @click="handleExpand(ind)"></i>
      </span>
      <span v-if="ind === 6 || ind === 7 || ind === 8" class="right-btn">
        <i class="iconfont icon-shouqi" v-if="collapse" @click="handleFold(ind)"></i>
        <i class="iconfont icon-shouqi-copy" v-else @click="handleExpand(ind)"></i>
      </span>
    </div>
  </div>
</template>

<script>
import dataContent from '../../pages/data/index';
export default {
  props: ['value', 'ind', 'allData'],
  data () {
    return {
      collapse: true
    };
  },
  computed: {
    temData () {
      return this.allData.allType;
    },
    currentType () {
      return this.allData.currentType;
    }
  },
  created () {
    console.log(this.currentType[0]);
  },
  components: {
    dataContent
  },
  methods: {
    async handleCommand (val) {
      this.allData.currentType.indexOf(val) > -1 ? this.$message({message: '当前模块已存在', type: 'warning'}) : this.$emit('selectChange', val, this.ind);
    },
    handleExpand (val) {
      if (val === 0 || val === 1 || val === 2) {
        this.$refs.content.style = 'display:block; width: 100%;';
        this.$refs.wrapper.style = 'width: 100%';
        this.$refs.temWrapper.style = 'width: 375px;border-right: none; border-left: 1px solid rgb(88, 207, 255)';
        this.collapse = true;
        this.$emit('handlePos', {collapse: this.collapse, ind: val});
      } else if (val === 3 || val === 4 || val === 5) {
        this.$refs.content.style = 'display:block; height: 100%;';
        this.$refs.wrapper.style = 'height: 100%';
        this.$refs.temWrapper.style = 'height: 326px;border-bottom: none; border-left: 1px solid rgb(88, 207, 255)';
        this.collapse = true;
        this.$emit('handlePos', {collapse: this.collapse, ind: val});
      } else if (val === 6 || val === 7 || val === 8) {
        this.$refs.content.style = 'display:block; width: 100%;';
        this.$refs.wrapper.style = 'width: 100%';
        this.$refs.temWrapper.style = 'width: 100%;border-right: none; border-left: 1px solid rgb(88, 207, 255)';
        this.collapse = true;
        this.$emit('handlePos', {collapse: this.collapse, ind: val});
      }
    },
    handleFold (val) {
      console.log(this.$refs.temWrapper);
      if (val === 0 || val === 1 || val === 2) {
        this.$refs.content.style = 'display:none;z-index: 1';
        this.$refs.wrapper.style = 'width: 10px;';
        this.collapse = false;
        this.$emit('handlePos', {collapse: this.collapse, ind: val});
      } else if (val === 3 || val === 4 || val === 5) {
        this.$refs.content.style = 'display:none;z-index: 1';
        this.$refs.wrapper.style = 'height: 10px';
        this.$refs.temWrapper.style = 'height: 10px;border-left: none; border-bottom: 1px solid rgb(88, 207, 255)';
        this.collapse = false;
        this.$emit('handlePos', {collapse: this.collapse, ind: val});
      } else if (val === 6 || val === 7 || val === 8) {
        this.$refs.content.style = 'display:none;z-index: 1';
        this.$refs.wrapper.style = 'width: 10px';
        this.$refs.temWrapper.style = 'width: 10px;border-left: none; border-right: 1px solid rgb(88, 207, 255)';
        this.collapse = false;
        this.$emit('handlePos', {collapse: this.collapse, ind: val});
      }
    }
  }
};
</script>

<style  scoped lang="scss">
.el-popper[x-placement^=bottom] .popper__arrow, .el-popper[x-placement^=bottom] .popper__arrow:after {
  border-bottom-color: #095379;
}
.el-popper[x-placement^=top] .popper__arrow, .el-popper[x-placement^=top] .popper__arrow:after {
  border-top-color: #095379;
}
</style>

<style lang="scss" scoped>
@import '@/common/scss/mixin.scss';
.el-dropdown-menu {
  background:#095379;
  border: none;
}
.el-dropdown-menu__item {
  position: relative;
  font-size: 12px;
  line-height: 26px;
  color: #fff;
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
  color: yellow;
  background:  #409EFF;
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
.face-recognition-container {
  position: relative;
  width: 100%;
  height: 100%;
  z-index: 1;
  border-left: 1px solid rgb(88, 207, 255);
  // background: #0c1943;
  .title {
    position: relative;
    height: 36px;
    line-height: 36px;
    padding: 0 10px;
    color: rgba(244, 223, 88, 1);
    background: linear-gradient(90deg, #0d4071 60%, rgba(46, 143, 249, .1));
    font-size: 15px;
    /deep/ .el-dropdown {
      position: absolute;
      top: 0;
      right: 0;
      .icon-set {
        margin-right: 10px;
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
  .wrapper {
    position: relative;
    // width: 100%;
    // height: 100%;
    width: 375px;
    height: 326px;
    .content {
      position: relative;
      width: 100%;
      height: 100%;
      color:white;
      padding: 1px;
      // box-shadow: 0 0 10px rgba(100,200,255,1) inset;
      background: rgba(28,38,65,.9);
      box-sizing: border-box;
      &:before {
        content: '';
        position: absolute;
        top: 10px;
        left: 0;
        width: 4px;
        height: 20px;
        background: rgb(244, 223, 88);
      }
      &::after {
        content: '';
        position: absolute;
        right: 0;
        bottom: 0;
        width: 0;
        height: 0;
        border-bottom: 12px solid rgb(244, 223, 88);
        border-left: 12px solid transparent;
      }
    }
  }
  @keyframes huerotate {
    0% {
      filter: hue-rotate(0deg)
    }
    100% {
      filter: hue-rotate(360deg);
    }
  }
  .left-btn {
    width: 22px;
    position: absolute;
    left: calc(100% - 25px);
    top: 50%;
    cursor: pointer;
    i {
      color: #17abe3;
      font-size: 40px;
    }
    // color:white;
  }
  .center-btn {
    height: 22px;
    font-size: 22px;
    position: absolute;
    left: 50%;
    bottom: calc(100% - 6px);
    cursor: pointer;
    i {
      color: #17abe3;
      font-size: 40px;
    }
  }
  .right-btn {
    width: 22px;
    font-size: 22px;
    position: absolute;
    right: calc(100% - 5px);
    top: 50%;
    cursor: pointer;
    i {
      color: #17abe3;
      font-size: 40px;
    }
  }
}
</style>
