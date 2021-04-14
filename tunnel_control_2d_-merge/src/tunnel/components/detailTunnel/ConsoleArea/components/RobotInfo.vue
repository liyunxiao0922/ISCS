<template>
  <div>
    <div class="robotinfoBox" v-if="robotTab === 'info'">
      <div class="robotImgBox">
        <img src="../../assets/img/WechatIMG12.png" alt="机器人跑路啦" />
      </div>
      <ul class="robotinfo">
        <li><span style="color: #f4df58;" @click="test">当前位置:</span>
          <span v-if="select == 1" id="topStr"></span>
          <span v-else-if="select == 3" id="botStr"></span>
        </li>
        <li><span style="color: #f4df58;">剩余巡航时间:</span><span>1h</span></li>
        <li><span style="color: #f4df58;">环境数据:</span><span>5度，85%</span></li>
        <li><span style="color: #f4df58;">机器人状态:</span><span>正常</span></li>
        <li><span style="color: #f4df58;">隧道状态:</span><span>正常</span></li>
      </ul>
    </div>
    <ul class="robotmonitorBox" v-else-if="robotTab === 'monitor'">
      <li v-if="robotType == 'up'">
        <video @click="showVideo(videoUrl[1])" id="demo" controls="controls" :src="videoUrl[1]" autoplay="autoplay" loop muted />
      </li>
      <li v-else-if="robotType == 'down'">
        <video @click="showVideo(videoUrl[1])" id="demo" controls="controls" :src="videoUrl[1]" autoplay="autoplay" loop muted />
      </li>
    </ul>
  </div>
</template>

<script>
import {videoInfoById} from '@/tunnel/api/tunnel';
export default {
  props: {
    robotTab: {
      type: String
    },
    robotType: {
      type: String
    },
  },
  data() {
    return {
      robotData: {
        pos: {

        }
      },
      select: 1,
      videoUrl: []
    }
  },
  watch: {
    robotData: {
      handler(to) {
      }
    }
  },
  methods: {
    // 为机器人界面赋值
    setData(obj) {
      if(document.getElementById('topStr')) {
        document.getElementById('topStr').innerText = obj.topStr;
      }
      if(document.getElementById('botStr')) {
        document.getElementById('botStr').innerText = obj.botStr;
      }
    },
    setSelect(num) {
      this.select = num;
    },
    showVideo(str) {
      this.$parent.showVideo(str);
    },
    test() {
      console.log(this.select);
    }
  },
  created() {
    videoInfoById()
    .then(res => {
      this.videoUrl = [res.data[0].url, res.data[1].url];
    })
  }
};
</script>

<style lang="scss" scoped>
.robotinfoBox {
  display: flex;
  justify-content: space-between;
  height: 125px;
  padding: 0 10px 10px 10px;
  .robotImgBox {
    width: 49%;
    height: 100%;
    text-align: center;
    line-height: 115px;
    font-size: 60px;
    border-right: 2px solid #2F3438;
    & > img {
      width: 90%;
    }
  }
  .robotinfo {
    margin: 0;
    padding: 0;
    list-style: none;
    font-size: 12px;
    width: 49%;
    color: #fff;
    & > li {
      border: 1px solid #384b4f;
      padding: 3px 5px;
      margin: 3px 0;
      &:first-child {
        margin: 0;
      }
      &:last-child {
        margin: 0;
      }
    }
  }
}
.robotmonitorBox{
  list-style: none;
  padding: 0 12px 10px 15px;
  margin: 0;
  display: flex;
  justify-content: space-between;
  li{
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