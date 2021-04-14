<template>
  <el-dialog
    title="详情处理"
    :visible.sync="warningVisible"
    width="575px"
    :before-close="handleClose"
  >
  <videoShow
    ref="videoShow"
  />
  <!-- tunnelArea: ['一区', '二区', '三区', '四区', '五区', '六区', '七区', '八区', '九区', '十区'],
      tunnelDirection: ['上行', '下行'],
      eventType: ['火灾', '泥石流', '交通'],
      trafficLane: ['无车道', '行车道', '快车道'],
      eventSource: ['手动事件', '自动事件'] -->
    <el-row :gutter="20">
      <el-col :span="16">
        <span style="color: #fff; font-size: 15px" @click="test">事件类型：</span>
        <span>{{
          $store.getters.getEventForm && $store.getters.getEventForm.eventTypeName
        }}</span>
      </el-col>
      <el-col :span="8">
        <span style="color: #fff; font-size: 15px">事件来源：</span>
        <span>{{ $store.getters.getEventForm && $store.getters.getEventForm.eventSourceName }}</span>
      </el-col>
    </el-row>
    <el-row :gutter="20" style="margin-top: 10px">
      <el-col :span="16">
        <span style="color: #fff; font-size: 15px">位置详情：</span>
        <span>{{ $store.getters.getEventForm && 
            tunnelDirection[this.$store.getters.getEventForm.tunnelDirection - 1] + tunnelArea[this.$store.getters.getEventForm.tunnelArea - 1] + trafficLane[this.$store.getters.getEventForm.trafficLane] 
           }}</span>
      </el-col>
      <el-col :span="8">
        <span style="color: #fff; font-size: 15px">事件状态：</span>
        <span>{{
          $store.getters.getEventForm.eventState == 1
            ? "待确认"
            : $store.getters.getEventForm.eventState == 2
            ? "已确认"
            : $store.getters.getEventForm.eventState == 3
            ? "误报"
            : ""
        }}</span>
      </el-col>
    </el-row>
    <el-collapse v-model="activeNames" style="margin-top: 10px">
      <el-collapse-item title="监控画面" name="1">
        <el-row :gutter="20">
          <el-col :span="12">
            <div style="color: #aaa; font-size: 13px; margin: 5px 0 10px">
              1车道001监控画面
            </div>
            <video
              @click="$refs.videoShow.show(videoUrl[1])"
              id="demo"
              :src="videoUrl[1]"
              controls="controls"
              autoplay="autoplay"
              loop
              muted
            />
          </el-col>
          <el-col :span="12">
            <div style="color: #aaa; font-size: 13px; margin: 5px 0 10px">
              1车道002监控画面
            </div>
            <video
              @click="$refs.videoShow.show(videoUrl[1])"
              id="demo"
              controls="controls"
              :src="videoUrl[1]"
              autoplay="autoplay"
              loop
              muted
            />
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <div style="color: #aaa; font-size: 13px; margin: 5px 0 10px">
              2车道001监控画面
            </div>
            <video
              @click="$refs.videoShow.show(videoUrl[1])"
              id="demo"
              controls="controls"
              :src="videoUrl[1]"
              autoplay="autoplay"
              loop
              muted
            />
          </el-col>
          <el-col :span="12">
            <div style="color: #aaa; font-size: 13px; margin: 5px 0 10px">
              2车道002监控画面
            </div>
            <video
              @click="$refs.videoShow.show(videoUrl[1])"
              id="demo"
              controls="controls"
              :src="videoUrl[1]"
              autoplay="autoplay"
              loop
              muted
            />
          </el-col>
        </el-row>
      </el-collapse-item>
    </el-collapse>
    <el-steps
      v-if="$store.getters.getEventForm.eventState == 2"
      style="margin-top: 10px"
      :active="$store.getters.getEventForm.planPeriod - 1"
      simple
    >
      <el-step title="逃生阶段" icon="null"></el-step>
      <!-- <i class="el-step__arrow el-icon-d-arrow-right"></i> -->
      <el-step title="救援阶段" icon="null"></el-step>
      <!-- <i class="el-step__arrow el-icon-d-arrow-right"></i> -->
      <el-step title="恢复阶段" icon="null"></el-step>
    </el-steps>
    <div
      v-if="$store.getters.getEventForm.eventState != 2"
      style="text-align: center; margin-top: 10px"
    >
      <el-button
        type="info"
        @click="editState($store.getters.getEventForm.eventId, 2)"
        >确认</el-button
      >
      <el-button
        type="info"
        @click="editState($store.getters.getEventForm.eventId, 3)"
        >误报</el-button
      >
    </div>
    <div v-else style="text-align: center; margin: 10px 0">
      <el-button
        type="info"
        v-if="($store.getters.getEventForm.planPeriod - 1) < 3"
        @click="goExecuteEvent($store.getters.getEventForm.eventId, 2)"
        >执行</el-button
      >
      <el-button type="info" v-else disabled>执行</el-button>
    </div>
  </el-dialog>
</template>

<script>
import { videoInfoById, updateEvent, executeEvent } from "@/tunnel/api/tunnel";
import videoShow from '@/tunnel/components/video/VideoShow'

export default {
  components: {
    videoShow
  },
  props: {
    warningVisible: {
      type: Boolean,
      default: false,
    },
  },
  watch: {},
  data() {
    return {
      activeName: "toBeConfirmed", // 当前选中的tab的key主要用来做切换标记
      videoUrl: [],
      activeNames: "1",
      tunnelArea: ['一区', '二区', '三区', '四区', '五区', '六区', '七区', '八区', '九区', '十区'],
      tunnelDirection: ['上行', '下行'],
      eventType: ['火灾', '泥石流', '交通'],
      trafficLane: ['无车道', '行车道', '快车道'],
      eventSource: ['手动事件', '自动事件']
    };
  },
  methods: {
    /**
     * 关闭菜单
     */
    handleClose() {
      this.$emit("warningModuleClick");
    },
    /**
     * tabs切换
     */
    handleClick(tab) {},
    getVideo() {
      videoInfoById().then((res) => {
        this.videoUrl = [res.data[0].url, res.data[1].url];
      });
    },
    editState(eventId, eventState) {
      updateEvent(eventId, eventState).then((res) => {
        this.$parent.updateAction();
        this.toggleClass();
      });
    },
    goExecuteEvent(eventId) {
      executeEvent(eventId).then((res) => {
        this.$parent.updateAction();
        this.toggleClass();
      });
    },
    toggleClass() {
      console.log(document.querySelectorAll('.is-simple')[this.$store.getters.getEventForm.planPeriod - 1 - 1]);
      document.querySelector('.step_current') && document.querySelector('.step_current').classList.remove('step_current');
      document.querySelectorAll('.is-simple')[this.$store.getters.getEventForm.planPeriod - 1 - 1] && document.querySelectorAll('.is-simple')[this.$store.getters.getEventForm.planPeriod - 1 - 1].children[1].children[1].classList.add('step_current');
    },
    test() {
      console.log(this.$store.getters.getEventForm);
    }
  },
  created() {
    /* 
      eventState 1未确认 2已确认 3已排除
      planPeriod 步骤 1 2 3
    */
    this.getVideo();
  },
  updated() {
    if(this.$store.getters.getEventForm.eventState == 2) {
      this.toggleClass();
    }
  },
};
</script>
<style scoped>
/deep/ .el-tabs__header .el-tabs__item.is-active {
  background: #191d21;
  color: #fff;
}
/deep/ .el-tabs__item {
  color: #9f9f9f;
}
/deep/ .el-dialog__body {
  padding: 6px 20px;
}
.groupControltabs /deep/ .el-tabs__nav-wrap::after {
  background: #191d21;
}
.groupControltabs /deep/ .el-tabs__nav-wrap::after {
  background: #191d21;
}
.groupControltabs /deep/ .el-tabs__active-bar {
  background: #fff;
}
/deep/ .el-collapse-item__header {
  background-color: #191d21;
  color: #fff;
  font-size: 15px;
  width: 85px;
  height: 30px;
  border: 0;
}
/deep/ .el-collapse {
  border: 0;
}
/deep/ .el-collapse-item__wrap {
  border: 0;
  background-color: #191d21;
}
/deep/ .el-button--info {
  width: 190px;
  background-color: #2f363d;
  border-radius: 2px;
  border: 0;
  padding: 8px 22px;
}
.el-steps--simple {
  background: none;
}
/deep/.is-process {
  color: #666;
  font-size: 12px;
}
/deep/.is-wait {
  color: #333;
  font-size: 12px;
}
/deep/.el-steps--simple {
  padding: 13px 15px;
  border: 1px solid #444;
}
/deep/.el-step.is-simple .el-step__arrow::before {
  content: '>>>';
  transform: none;
  background: none;
  width: 30px;
  margin-left: 2px;
  font-weight: bolder;
}
/deep/.current::before {
  animation: current .75s infinite linear;
}
/deep/.el-step.is-simple .el-step__arrow::after {
  /* content: '>>>';
  width: 25px;
  transform: none;
  background: none; */
  display: none;
}
/deep/.el-step__main {
  width: 100px;
  overflow: hidden;
}
/deep/.el-step__head {
  display: none;
}
</style>
<style lang="scss" scoped>
#demo {
  width: 100%;
}
video::-webkit-media-controls {
  display: none !important;
}
</style>
<style>
.el-step__head {
  display: none;
}
.el-step__title.is-finish {
  color: #f00;
  font-size: 12px;
}
@keyframes current {
  0% {
    background-image: linear-gradient(to right , #a00000, #999);
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
  }
  100% {
    background-image: linear-gradient(to right , #999, #a00000);
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
  }
  /* 100% {
    background-image: linear-gradient(to right , #a00000, #eee);
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
  } */
}
.step_current::before {
  animation: current .75s infinite linear;
}
.el-step__title.is-process {
  color: #666;
  font-size: 12px;
}
.el-step__title.is-wait {
  color: #333;
  font-size: 12px;
}
</style>