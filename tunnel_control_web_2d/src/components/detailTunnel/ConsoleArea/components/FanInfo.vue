<template>
  <ul class="fanInfoBox">
    <li>
      <div class="fanNumBox">
        <div>
          <span class="tunneliconfont tunneliconfont-fengji"></span>
        </div>
        <div class="dataInfo">
          <p class="inputNum">19</p>
          <p>风机数量</p>
        </div>
      </div>
      <div class="fanNumBox">
        <div>
          <span class="tunneliconfont tunneliconfont-fengji"></span>
        </div>
        <div class="dataInfo">
          <p class="inputNum">19</p>
          <p>风机数量</p>
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
        <span>Oppm Okm-1</span>
      </div>
      <div class="dataContent">
        <p class="title">CO·VI</p>
        <span>Oppm Okm-1</span>
      </div>
    </li>
    <li>
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
.fanInfoBox {
  display: flex;
  list-style: none;
  margin: 0;
  padding: 0 10px;
  height: 110px;
  font-size: 12px;
  justify-content: center;
  align-items: center;
  li {
    width: 33%;
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
        color: #b2faff;
        .inputNum {
          font-size: 20px;
          font-weight: bold;
          border-bottom: 1px solid #f00;
          margin: 0 10px 5px;
        }
      }
    }
    .dataContent {
      height: 50px;
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      color: #b2faff;
      .title {
        font-size: 20px;
        font-weight: bold;
        border-bottom: 1px solid #f00;
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
        color: #b2faff;
      }
    }
    &.braceBox {
      position: relative;
      .leftBrace {
        width: 4px;
        height: 100%;
        background-color: #fff;
        position: absolute;
        left: 0;
        top: 0;
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
          transform: rotate(-32deg) translate(-43px, 74px);
        }
        .line {
          position: absolute;
          left: 2px;
          top: 0;
          width: 2px;
          height: 100%;
          background-color: #f00;
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
          border-right: 5px solid #f00;
          border-top: 5px dashed transparent;
          border-bottom: 5px dashed transparent;
        }
      }
      .rightBrace {
        width: 4px;
        height: 100%;
        background-color: #fff;
        position: absolute;
        right: 0;
        top: 0;
        &::before {
          display: inline-block;
          width: 4px;
          height: 10px;
          content: "";
          background-color: #fff;
          transform: rotate(32deg) translate(51px, 85px);
        }
        &::after {
          display: inline-block;
          width: 4px;
          height: 10px;
          content: "";
          background-color: #fff;
          transform: rotate(-32deg) translate(9px, -18px);
        }
        .line {
          position: absolute;
          left: 0px;
          top: 0;
          width: 2px;
          height: 100%;
          background-color: #f00;
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
          border-left: 5px solid #f00;
          border-top: 5px dashed transparent;
          border-bottom: 5px dashed transparent;
        }
      }
    }
  }
}
</style>