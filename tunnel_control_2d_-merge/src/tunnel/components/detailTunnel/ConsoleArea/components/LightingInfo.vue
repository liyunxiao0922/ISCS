<template>
  <div>
    <ul class="lightingInfoBox" v-if="LightingStatistics === 'info'">
      <li>
        <div class="brightnessBox">
          <div>
            <span class="tunneliconfont tunneliconfont-zhaoming"></span>
          </div>
          <div class="dataInfo">

            <p class="inputNum">{{data[0].cdm}}</p>
            <p>cd/m²</p>
          </div>
        </div>
        <div class="brightnessBox">
          <div>
            <span class="tunneliconfont tunneliconfont-zhaoming"></span>
          </div>
          <div class="dataInfo">
            <p class="inputNum">{{data[0].cdm}}</p>
            <p>cd/m²</p>
          </div>
        </div>
      </li>
      <li class="btnMainBox">
        <div class="leftBrace">
          <div class="line"></div>
          <div class="triangle"></div>
        </div>
        <div class="rightBrace">
          <div class="line"></div>
          <div class="triangle"></div>
        </div>
        <div class="btnBox">
          <div class="btnItem">     
            <div class="rel">
            <div class="mask" :style="'height: ' + (100 - (parseInt(data[0].base * 100))) + '%'"></div>
            <p class="iconBox">
              <img class="iconBoximg" src="../../assets/img/lightborder.png" alt="">
            </p>
            </div>
            <p>基本</p>
          </div>
          <div class="btnItem">     
            <div class="rel">
              <div class="mask"  :style="'height: ' + (100 - (parseInt(data[0].enhanced * 100))) + '%'"></div>
              <p class="iconBox">
                <img class="iconBoximg" src="../../assets/img/lightborder.png" alt="">
              </p>
            </div>
            <p>加强</p>
          </div>
          <div class="btnItem">     
            <div class="rel">
            <div class="mask" :style="'height: ' + (100 - (parseInt(data[0].approach * 100))) + '%'"></div>
            <p class="iconBox">
              <img class="iconBoximg" src="../../assets/img/lightborder.png" alt="">
            </p>
            </div>
            <p>引道</p>
          </div>
          <div class="btnItem">     
            <div class="rel">
            <div class="mask" :style="'height: ' + (100 - (parseInt(data[1].base * 100))) + '%'"></div>
            <p class="iconBox">
              <img class="iconBoximg" src="../../assets/img/lightborder.png" alt="">
            </p>
            </div>
            <p>基本</p>
          </div>
          <div class="btnItem">         
            <div class="rel"> 
            <div class="mask"  :style="'height: ' + (100 - (parseInt(data[1].enhanced * 100))) + '%'"></div>
            <p class="iconBox">
              <img class="iconBoximg" src="../../assets/img/lightborder.png" alt="">
            </p>
            </div>
            <p>加强</p>
          </div>
          <div class="btnItem">     
            <div class="rel">
            <div class="mask" :style="'height: ' + (100 - (parseInt(data[1].approach * 100))) + '%'"></div>
            <p class="iconBox">
              <img class="iconBoximg" src="../../assets/img/lightborder.png" alt="">
            </p>
            </div>
            <p>引道</p>
          </div>
        </div>
      </li>
    </ul>
    <ul class="carMonitor" v-else-if="LightingStatistics === 'monitor'">
      <img src="../../assets/img/safetyWarning.png" alt="">
    </ul>
  </div>
</template>

<script>
import {lightInforStatistics} from '@/tunnel/api/tunnel'
export default {
  props: {
    LightingStatistics: {
      type: String
    },
  },
  data() {
    return {
      data: [{}, {}]
    }
  },
  mounted() {
    this.getData()
  },
  methods: {
    getData() {
      lightInforStatistics() 
        .then(res => {
          this.data = res.data;
        })
      setTimeout(() => {
        this.getData();
      }, 5000)
    }
  },
};
</script>

<style lang="scss" scoped>
.carMonitor{
  margin-left: 23px;
  margin-top: -8px;
  text-align: center;
  img{
    height: 100%;
  }
}
p {
  margin: 0;
}
.lightingInfoBox {
  display: flex;
  list-style: none;
  margin: 0;
  margin-top:15px;
  padding: 0 10px 0 0;
  height: 110px;
  font-size: 12px;
  justify-content: center;
  align-items: center;
  li {
    width: 30%;
    .brightnessBox {
      display: flex;
      height: 50px;
      justify-content: center;
      align-items: center;
      .tunneliconfont {
        font-size: 30px;
      }
      .dataInfo {
        width: 40%;
        text-align: center;
        color: #f4df58;
        .inputNum {
          font-size: 20px;
          font-weight: bold;
        }
      }
    }
    &.btnMainBox {
      position: relative;
      width: 60%;
      .leftBrace {
        width: 4px;
        height: 90px;
        // background-color: #fff;
        position: absolute;
        left: 0;
        top: 15px;
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
        //   transform: rotate(-32deg) translate(-38px, 65px);
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
          top: 40px;
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
        height: 90px;
        // background-color: #fff;
        position: absolute;
        right: 0;
        top: 15px;
        // &::before {
        //   display: inline-block;
        //   width: 4px;
        //   height: 10px;
        //   content: "";
        //   background-color: #fff;
        //   transform: rotate(32deg) translate(45px, 76px);
        // }
        // &::after {
        //   display: inline-block;
        //   width: 4px;
        //   height: 10px;
        //   content: "";
        //   background-color: #fff;
        //   transform: rotate(-32deg) translate(10px, -20px);
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
          left: 3px;
          top: 40px;
          content: "";
          z-index: 2;
          height: 0;
          width: 0;
          border-left: 5px solid #2F3438;
          border-top: 5px dashed transparent;
          border-bottom: 5px dashed transparent;
        }
      }
      .btnBox {
        display: flex;
        flex-wrap: wrap;
        justify-content: center;
        .btnItem {
          width: 33%;
          margin-bottom: 5px;
          .tunneliconfont {
            font-size: 30px;
          }
          p {
            text-align: center;
            color: #f4df58;
            &.iconBox {
              color: #b2faff;
              .iconBoximg{
                width: 45px;
              }
            }
          }
        }
      }
    }
  }
}
.mask {
  width: 100%;
  background-color: #191d21;
  opacity: 0.8;
  position: absolute;
  top: 0;
  left: 0;
}
.rel {
  position: relative;
}
</style>