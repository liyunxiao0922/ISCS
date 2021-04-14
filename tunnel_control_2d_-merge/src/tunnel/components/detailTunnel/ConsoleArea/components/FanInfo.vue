<template>
  <div>
    <ul class="fanInfoBox" v-if="fanAndCovi === 'info'">
      <li>
        <div class="fanNumBox">
          <div v-if="data[0].positiveTurnNum > data[0].reverseTurn" class="positive">
            <span class="tunneliconfont tunneliconfont-fengji"></span>
          </div>
          <div v-else-if="data[0].positiveTurnNum < data[0].reverseTurn" class="reverse">
            <span class="tunneliconfont tunneliconfont-fengji"></span>
          </div>
          <div v-else-if="data[0].positiveTurnNum == 0 && data[0].reverseTurn == 0" class="stop">
            <span class="tunneliconfont tunneliconfont-fengji"></span>
          </div>
          <div v-else class="positive">
            <span class="tunneliconfont tunneliconfont-fengji"></span>
          </div>
          <div class="dataInfo">
            <p class="inputNum">{{data[0].positiveTurnNum + data[0].reverseTurn}}</p>
            <p style="font-size: 20px;">{{data[0].allNum}}</p>
          </div>
        </div>
        <div class="fanNumBox">
          <div v-if="data[1].positiveTurnNum > data[1].reverseTurn" class="positive">
            <span class="tunneliconfont tunneliconfont-fengji"></span>
          </div>
          <div v-else-if="data[1].positiveTurnNum < data[1].reverseTurn" class="reverse">
            <span class="tunneliconfont tunneliconfont-fengji"></span>
          </div>
          <div v-else-if="data[1].positiveTurnNum == 0 && data[1].reverseTurn == 0" class="stop">
            <span class="tunneliconfont tunneliconfont-fengji"></span>
          </div>
          <div v-else class="reverse">
            <span class="tunneliconfont tunneliconfont-fengji"></span>
          </div>
          <div class="dataInfo">
            <p class="inputNum">{{data[1].positiveTurnNum + data[1].reverseTurn}}</p>
            <p style="font-size: 20px;">{{data[1].allNum}}</p>
          </div>
        </div>
      </li>
      <li class="braceBox">
        <div class="leftBrace">
          <div class="line"></div>
          <div class="triangle"></div>
        </div>
        <div class="rightBrace">
          <div class="line"></div>
          <div class="triangle"></div>
        </div>
        <div class="dataContent">
          <p class="title">CO·VI</p>
          <span>{{data[0].ppm}}ppm {{data[0].km}}km-1</span>
        </div>
        <div class="dataContent">
          <p class="title">CO·VI</p>
          <span>{{data[1].ppm}}ppm {{data[1].km}}km-1</span>
        </div>
      </li>
    </ul>
    <ul class="carMonitor" v-else-if="fanAndCovi === 'monitor'">
     <img src="../../assets/img/CO_VI.png" alt="">
    </ul>
  </div>
    <!-- <li>
      <p class="statusBox">
        <i class="tunneliconfont tunneliconfont-fanxiangdianneng"></i>
        <span class="statusName">反向</span>
      </p>
      <p class="statusBox">
        <i class="tunneliconfont tunneliconfont-tingzhi"></i>
        <span class="statusName">停止</span>
      </p>
      <p class="statusBox">
        <i class="tunneliconfont tunneliconfont-zhengxiangdianneng"></i>
        <span class="statusName">正向</span>
      </p>
    </li> -->
</template>

<script>
import {fanInforStatistics} from '@/tunnel/api/tunnel'
export default {
  props: {
    fanAndCovi: {
      type: String
    },
  },
  data() {
    return {
      data: [{}, {}]
    }
  },
  mounted() {
    this.init();
  },
  methods: {
    init() {
      fanInforStatistics()
      .then(res => {
        this.data = res.data;
      })
      setTimeout(() => {
        this.init();
      }, 10000)
    }
  },
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
p {
  margin: 0;
}
.fanInfoBox {
  display: flex;
  list-style: none;
  margin: 0;
  margin-top: 16px;
  padding: 0 26px;
  height: 110px;
  font-size: 12px;
  justify-content: center;
  align-items: center;
  li {
    width: 50%;
    display: flex;
    flex-direction:column;
    justify-content: space-between;
    height: 100%;
    .fanNumBox {
      display: flex;
      height: 50px;
      justify-content: center;
      align-items: center;
      .tunneliconfont {
        font-size: 30px;
      }
      .dataInfo {
        width: 60%;
        text-align: center;
        color: #f4df58;
        .inputNum {
          font-size: 20px;
          font-weight: bold;
          border-bottom: 1px solid #2F3438;
          margin: 0 25px 5px;
        }
      }
    }
    .dataContent {
      height: 50px;
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      color: #f4df58;
      .title {
        font-size: 20px;
        font-weight: bold;
        // border-bottom: 1px solid #f00;
        padding: 0 10px 5px 10px;
        margin-bottom: 5px;
      }
    }
    .statusBox {
      line-height: 35px;
      text-align: center;
      .tunneliconfont {
        margin-right: 10px;
        font-size: 25px;
        vertical-align: middle;
      }
      .statusName {
        color: #f4df58;
      }
    }
    &.braceBox {
      position: relative;
      .leftBrace {
        width: 4px;
        height: 100px;
      //   background-color: #fff;
        position: absolute;
        left: 0;
        top: 0;
        // &::before {
        //   display: inline-block;
        //   width: 4px;
        //   height: 10px;
        //   content: "";
        //   background-color: #fff;
        //   transform: rotate(32deg) translate(-2px, -9px);
        // }
        // &::after {
        //   display: inline-block;
        //   width: 4px;
        //   height: 10px;
        //   content: "";
        //   background-color: #fff;
        //   transform: rotate(-32deg) translate(-43px, 74px);
        // }
        .line {
          position: absolute;
          left: 2px;
          top: 0;
          width: 3px;
          height: 100px;
          background-color: #2F3438;
          z-index: 1;
        }
        .triangle {
          position: absolute;
          left: -1px;
          top: 45px;
          content: "";
          z-index: 2;
          height: 0;
          width: 0;
          border-right: 5px solid #2F3438;
          border-top: 5px dashed transparent;
          border-bottom: 5px dashed transparent;
        }
      }
      .rightBrace {
        width: 4px;
        height: 100px;
        // background-color: #fff;
        position: absolute;
        right: 0;
        top: 0;
      //   &::before {
      //     display: inline-block;
      //     width: 4px;
      //     height: 10px;
      //     content: "";
      //     background-color: #fff;
      //     transform: rotate(32deg) translate(51px, 85px);
      //   }
      //   &::after {
      //     display: inline-block;
      //     width: 4px;
      //     height: 10px;
      //     content: "";
      //     background-color: #fff;
      //     transform: rotate(-32deg) translate(10px, -20px);
      //   }
        .line {
          position: absolute;
          left: 0px;
          top: 0;
          width: 3px;
          height: 100px;
          background-color: #2F3438;
          z-index: 1;
        }
        .triangle {
          position: absolute;
          left: 0px;
          top: 45px;
          content: "";
          z-index: 2;
          height: 0;
          width: 0;
          border-left: 5px solid #2F3438;
          border-top: 5px dashed transparent;
          border-bottom: 5px dashed transparent;
        }
      }
    }
  }
}
@keyframes positiveRotate {
  from {
    transform: rotate(0deg);
  }

  to {
    transform: rotate(360deg);
  }
}
@keyframes rotateReverse {
  from {
    transform: rotate(360deg);
  }

  to {
    transform: rotate(0deg);
  }
}
.positive {
  animation: positiveRotate 3s linear infinite;
}
.reverse {
  animation: rotateReverse 3s linear infinite;
}
.stop {
  animation: none;
  color: #8e8e8e
}
</style>