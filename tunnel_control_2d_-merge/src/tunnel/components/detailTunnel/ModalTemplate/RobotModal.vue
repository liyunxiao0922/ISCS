<template>
  <el-dialog
    title="机器人控制面板"
    :visible.sync="robotVisible"
    width="25%"
    :before-close="handleClose"
  >
    <videoShow ref="videoShow" />
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane label="自动控制" name="automatic"
        ><AutomaticTab
        @handleClose="handleClose"
        ref="auto"
      /></el-tab-pane>
      <el-tab-pane label="手动控制" name="manual"><ManualTab 
      ref="manual"
      @handleClose="handleClose"
      /></el-tab-pane>
      <el-tab-pane label="充电" name="charge"><ChargeTab
      ref="charge"  @handleClose="handleClose"/></el-tab-pane>
      <el-tab-pane label="维护" name="maintain"><MaintainTab
      ref="maintain"  @handleClose="handleClose"/></el-tab-pane>
    </el-tabs>
    <h3>视频监控</h3>
    <div class="imgBox">
        <video @click="$refs.videoShow.show(videoUrl)" id="demo" controls="controls" :src="videoUrl" autoplay="autoplay" loop muted>
          <!-- <source :src="videoUrl" type="video/mp4"> -->
        </video>
    </div>
  </el-dialog>
</template>

<script>
import AutomaticTab from "./robotTabs/AutomaticTab";
import ChargeTab from "./robotTabs/ChargeTab";
import MaintainTab from "./robotTabs/MaintainTab";
import ManualTab from "./robotTabs/ManualTab";
import {videoInfoById} from '@/tunnel/api/tunnel';
import videoShow from '@/tunnel/components/video/VideoShow'
export default {
  props: {
    robotVisible: {
      type: Boolean,
      default: false,
    },
    type: {
      type: Number
    }
  },
  components: {
    videoShow,
    AutomaticTab,
    ChargeTab,
    MaintainTab,
    ManualTab,
  },
  data() {
    return {
      activeName: "automatic",
      videoUrl: ''
    };
  },
  methods: {
    /**
     * 关闭菜单
     */
    handleClose() {
      this.$emit("robotClickClose");
    },

    handleClick(tab, event) {
      console.log(tab.paneName);
    },
  },
  created() {
    videoInfoById()
    .then(res => {
      this.videoUrl = res.data[1].url;
    })
  },
  updated() {
    this.$refs.auto.setType(this.type)
    this.$refs.manual.setType(this.type)
    this.$refs.charge.setType(this.type)
    this.$refs.maintain.setType(this.type)
  }
};
</script>
<style scoped>
/deep/.el-form-item {
    margin-bottom: 5px;
}
/deep/ .el-dialog__body {
  padding: 6px 20px 15px;
}
/deep/ .el-tabs__item{
  color:#9f9f9f;
}
/deep/ .el-tabs__item:hover,/deep/ .el-tabs__item.is-active{
  color:#fff;
}
/deep/ .el-tabs__nav-wrap::after{
  background: #191D21;
}
/deep/ .el-tabs__active-bar {
  background: #fff;
}
</style>
<style lang="scss" scoped>
.imgBox {
  width: 100%;
  height: 200px;
  & > video {
    width: 100%;
    height: 100%;
  }
}
video::-webkit-media-controls{
  display:none !important;
}
</style>