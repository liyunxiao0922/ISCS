<template>
  <div>
    <ul class="carInfoBox" v-if="trafficFlowStatistics === 'info'">
      <li>
        <div>
          <span class="tunneliconfont tunneliconfont-cheliuliang"></span>
        </div>
        <div class="dataInfo">
          <p><span class="inputNum">{{data[0] && data[0].avgNum}}</span>/min</p>
          <p class="triangle-down"></p>
          <p><span style="color:#b2faff">-</span> 车流量 <span style="color:#b2faff">-</span></p>
        </div>
      </li>
      <li>
        <div>
          <span class="tunneliconfont tunneliconfont-zhuangche-"></span>
        </div>
        <div class="dataInfo">
          <p><span class="inputNum">{{data[0] && data[0].avgSpeed}}</span>km</p>
          <p class="triangle-down"></p>
          <p><span style="color:#b2faff">-</span> 平均时速 <span style="color:#b2faff">-</span></p>
        </div>
      </li>
      <li>
        <div>
          <span class="tunneliconfont tunneliconfont-xiaoche"></span>
        </div>
        <div class="dataInfo">
          <p class="inputNum">{{parseInt((data[0] && data[0].carPercent) * 100)}}%</p>
          <p class="triangle-down"></p>
          <p><span style="color:#b2faff">-</span> 小车占有率 <span style="color:#b2faff">-</span></p>
        </div>
      </li>
      <li>
        <div>
          <span class="tunneliconfont tunneliconfont-cheliuliang"></span>
        </div>
        <div class="dataInfo">
          <p><span class="inputNum">{{data[1] && data[1].avgNum}}</span>/min</p>
          <p class="triangle-down"></p>
          <p><span style="color:#b2faff">-</span> 车流量 <span style="color:#b2faff">-</span></p>
        </div>
      </li>
      <li>
        <div>
          <span class="tunneliconfont tunneliconfont-zhuangche-"></span>
        </div>
        <div class="dataInfo">
          <p><span class="inputNum">{{data[1] && data[1].avgSpeed}}</span>km</p>
          <p class="triangle-down"></p>
          <p><span style="color:#b2faff">-</span> 平均时速 <span style="color:#b2faff">-</span></p>
        </div>
      </li>
      <li>
        <div>
          <span class="tunneliconfont tunneliconfont-xiaoche"></span>
        </div>
        <div class="dataInfo">
          <p class="inputNum">{{parseInt((data[1] && data[1].carPercent) * 100)}}%</p>
          <p class="triangle-down"></p>
          <p><span style="color:#b2faff">-</span> 小车占有率 <span style="color:#b2faff">-</span></p>
        </div>
      </li>
    </ul>
    <ul class="carMonitor" v-else-if="trafficFlowStatistics === 'monitor'">
     <img src="../../assets/img/trafficVolume.png" alt="">
    </ul>
  </div>
</template>

<script>
import {vehicle} from '@/tunnel/api/tunnel';
export default {
  props: {
    trafficFlowStatistics: {
      type: String
    },
  },
  data() {
    return {
      data: [{}, {}]
    }
  },
  created() {
    this.getData()
  },
  methods: {
    getData() {
      vehicle()
      .then(res => {
        this.data = res.data;
      })
      setTimeout(() => {
        this.getData();
      }, 10000)
    }
  }
};
</script>

<style lang="scss" scoped>
.carMonitor{
  margin-left: 0;
  margin-top: -8px;
  text-align: center;
  img{
    height: 100%;
  }
}
.carInfoBox {
  padding: 0;
  margin: 0;
  list-style: none;
  display: flex;
  justify-content: space-around;
  align-items: center;
  flex-wrap: wrap;

  & > li {
    width: 32%;
    text-align: center;
    font-size: 12px;
    height: 50px;
    padding: 2px;
    // line-height: 20px;
    display: flex;
    justify-content: center;
    align-items: center;
    .tunneliconfont {
      font-size: 33px;
    }
    &:nth-child(-n + 3) {
      margin-bottom: 20px;
      margin-top: 12px;
    }
    p {
      margin: 0;
    }
    .dataInfo {
      color: #f4df58;
      .inputNum {
        font-size: 20px;
        font-weight: bold;
      }
      .triangle-down {
        display: inline-block;
        margin: 0 auto;
        width: 0;
        height: 0;
        border-left: 6px solid transparent;
        border-right: 6px solid transparent;
        border-top: 5px solid #b2faff;
      }
    }
  }
}
</style>