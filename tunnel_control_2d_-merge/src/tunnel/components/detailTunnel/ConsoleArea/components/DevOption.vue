<template>
<div> 
  <ul class="devoptionBox" v-if="equipmentOperation === 'info'">
    <li class="organizationBox">
      <span style="color: #f4df58;">控制机构：</span>
      <el-select v-model="value" disabled clearable placeholder="请选择" size="mini">
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
      <span style="color: #f4df58;">通风模式：</span>
      <el-radio-group text-color="#fff" fill="#3A4147" v-model="value1" @change="change(1)" size="small" style="margin-right: 10px">
        <el-radio-button label="2">自动</el-radio-button>
        <el-radio-button label="1">手动</el-radio-button>
      </el-radio-group>
      <span style="color: #f4df58;">照明模式：</span>
      <el-radio-group text-color="#fff" fill="#3A4147" v-model="value2" @change="change()" size="mini">
        <el-radio-button label="2">自动</el-radio-button>
        <el-radio-button label="1">手动</el-radio-button>
      </el-radio-group>
    </li>
  </ul>
  <ul class="robotmonitorBox" v-else-if="equipmentOperation === 'monitor'">
      <li>
        <span>上行入口</span>
        <video @click="showVideo(videoUrl[0])" id="demo" controls="controls" :src="videoUrl[0]" autoplay="autoplay" loop muted />
      </li>
      <li>
        <span>下行入口</span>
        <video @click="showVideo(videoUrl[1])" id="demo" controls="controls" :src="videoUrl[1]" autoplay="autoplay" loop muted />
      </li>
      <li>
        <span>自定义1</span>
        <video @click="showVideo(videoUrl[0])" id="demo" controls="controls" :src="videoUrl[0]" autoplay="autoplay" loop muted />
      </li>
      <li>
        <span>自定义2</span>
        <video @click="showVideo(videoUrl[1])" id="demo" controls="controls" :src="videoUrl[1]" autoplay="autoplay" loop muted />
      </li>
    </ul>
</div>
</template>

<script>
import {equipControlInfor, updateLightModel, updateWindModel,videoInfoById} from '@/tunnel/api/tunnel';
export default {
  props: {
    equipmentOperation: {
      type: String
    },
  },
  data() {
    return {
      options: [
        {
          value: "1",
          label: "管控中心",
        }
      ],
      value: "管控中心",
      value1: "",
      value2: "",
      videoUrl:[],
    };
  },
  methods: {
    change(flag) {
      if(flag) {
        updateWindModel(this.value1);
      }else {
        updateLightModel(this.value2);
      }
    },
    showVideo(str) {
      this.$parent.showVideo(str);
    },
  },
  created() {
    equipControlInfor()
    .then(res => {
      this.value1 = res.data[0].windModel;
      this.value2 = res.data[1].lightModel;
    })
    videoInfoById()
    .then(res => {
      this.videoUrl = [res.data[0].url, res.data[1].url];
    })
  }
};
</script>
<style scoped>
.devoptionBox /deep/ .el-input__inner {
  background-color: transparent;
  border: 0;
  border-bottom: 1px solid #19292e;
  width: 100%;
  color: #fff;
}
.devoptionBox /deep/ .el-select {
  width: calc(100% - 80px);
}
/deep/ .el-radio-button__inner {
  padding: 5px 10px;
  background-color: transparent;
  color: #999;
  border-color: transparent;
  box-shadow: none;
  border-left: none;
  background: #23282D;
}
/deep/ .el-radio-button__orig-radio:checked+.el-radio-button__inner {
  box-shadow: none;
}
/deep/ .el-radio-button__orig-radio:checked+.el-radio-button__inner {
  background: #3A4147;
  border: #3A4147;
}
/deep/ .el-radio-button:first-child .el-radio-button__inner {
  border-left: none;
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

.robotmonitorBox{
  list-style: none;
  padding: 0 12px 0px 15px;
  margin: 0;
  margin-top: -5px;
  li{
    width: 48%;
    height: 78px;
    float: left;
    margin-right: 5px;
    position: relative;
    span{
      position:absolute;
      top: 5px;
      background: black;
      color: #c4c2c2;
      font-size: 12px;
    }
    &>video{
      width: 100%;
      height: 100%;
    }
  }
}
video::-webkit-media-controls{
  display:none !important;
}
</style>