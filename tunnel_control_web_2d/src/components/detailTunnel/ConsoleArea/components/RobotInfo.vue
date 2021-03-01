<template>
  <div>
    <div class="robotinfoBox" v-if="robotTab === 'info'">
      <div class="robotImgBox">
        <img src="../../assets/img/WechatIMG12.png" alt="机器人跑路啦" />
      </div>
      <ul class="robotinfo">
        <li><span>当前位置:</span><span>K10+100</span></li>
        <li><span>剩余巡航时间:</span><span>1h</span></li>
        <li><span>环境数据:</span><span>5度，85%</span></li>
        <li><span>机器人状态:</span><span>正常</span></li>
        <li><span>隧道状态:</span><span>正常</span></li>
      </ul>
    </div>
    <ul class="robotmonitorBox" v-else-if="robotTab === 'monitor'">
      <li>
        <img src="../../assets/img/monitor1.jpg" alt="">
      </li>
      <li>
        <img src="../../assets/img/monitor2.jpg" alt="">
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  props: {
    robotTab: {
      type: String,
    },
  },
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
    border-right: 1px solid #f00;
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
    color: #b2faff;
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
    width: 49%;
    &>img{
      width: 100%;
      height: 100%;
    }
  }
}
</style>