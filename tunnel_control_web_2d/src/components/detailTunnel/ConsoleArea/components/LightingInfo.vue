<template>
  <ul class="lightingInfoBox">
    <li>
      <div class="brightnessBox">
        <div>
          <span class="tunneliconfont tunneliconfont-zhaoming"></span>
        </div>
        <div class="dataInfo">
          <p class="inputNum">20</p>
          <p>cd/m²</p>
        </div>
      </div>
      <div class="brightnessBox">
        <div>
          <span class="tunneliconfont tunneliconfont-zhaoming"></span>
        </div>
        <div class="dataInfo">
          <p class="inputNum">20</p>
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
          <p class="iconBox">
            <span class="tunneliconfont tunneliconfont-liubianxing"></span>
          </p>
          <p>基本</p>
        </div>
        <div class="btnItem">
          <p class="iconBox">
            <span class="tunneliconfont tunneliconfont-liubianxing"></span>
          </p>
          <p>加强</p>
        </div>
        <div class="btnItem">
          <p class="iconBox">
            <span class="tunneliconfont tunneliconfont-liubianxing"></span>
          </p>
          <p>引道</p>
        </div>
        <div class="btnItem">
          <p class="iconBox">
            <span class="tunneliconfont tunneliconfont-liubianxing"></span>
          </p>
          <p>基本</p>
        </div>
        <div class="btnItem">
          <p class="iconBox">
            <span class="tunneliconfont tunneliconfont-liubianxing"></span>
          </p>
          <p>加强</p>
        </div>
        <div class="btnItem">
          <p class="iconBox">
            <span class="tunneliconfont tunneliconfont-liubianxing"></span>
          </p>
          <p>引道</p>
        </div>
      </div>
    </li>
  </ul>
</template>

<script>
export default {};
</script>

<style lang="scss" scoped>
p {
  margin: 0;
}
.lightingInfoBox {
  display: flex;
  list-style: none;
  margin: 0;
  padding: 0 10px;
  height: 110px;
  font-size: 12px;
  justify-content: center;
  align-items: center;
  li {
    width: 40%;
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
        color: #b2faff;
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
        background-color: #fff;
        position: absolute;
        left: 0;
        top: 15px;
        &::before {
          display: inline-block;
          width: 4px;
          height: 10px;
          content: "";
          background-color: #fff;
          transform: rotate(32deg) translate(-2px, -9px);
        }
        &::after {
          display: inline-block;
          width: 4px;
          height: 10px;
          content: "";
          background-color: #fff;
          transform: rotate(-32deg) translate(-38px, 65px);
        }
        .line {
          position: absolute;
          left: 2px;
          top: 0;
          width: 2px;
          height: 90px;
          background-color: #f00;
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
          border-right: 5px solid #f00;
          border-top: 5px dashed transparent;
          border-bottom: 5px dashed transparent;
        }
      }
      .rightBrace {
        width: 4px;
        height: 90px;
        background-color: #fff;
        position: absolute;
        right: 0;
        top: 15px;
        &::before {
          display: inline-block;
          width: 4px;
          height: 10px;
          content: "";
          background-color: #fff;
          transform: rotate(32deg) translate(45px, 76px);
        }
        &::after {
          display: inline-block;
          width: 4px;
          height: 10px;
          content: "";
          background-color: #fff;
          transform: rotate(-32deg) translate(10px, -20px);
        }
        .line {
          position: absolute;
          left: 0px;
          top: 0;
          width: 2px;
          height: 90px;
          background-color: #f00;
          z-index: 1;
        }
        .triangle {
          position: absolute;
          left: 0px;
          top: 40px;
          content: "";
          z-index: 2;
          height: 0;
          width: 0;
          border-left: 5px solid #f00;
          border-top: 5px dashed transparent;
          border-bottom: 5px dashed transparent;
        }
      }
      .btnBox {
        display: flex;
        flex-wrap: wrap;
        color: #b2faff;
        justify-content: center;
        .btnItem {
          width: 33%;
          margin-bottom: 5px;
          .tunneliconfont {
            font-size: 30px;
          }
          p {
            text-align: center;
            &.iconBox {
              margin-bottom: 5px;
            }
          }
        }
      }
    }
  }
}
</style>