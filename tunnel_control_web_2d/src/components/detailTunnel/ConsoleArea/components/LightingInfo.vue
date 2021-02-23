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
    <li class="btnBox">
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
    &.btnBox {
      width: 60%;
      display: flex;
      flex-wrap: wrap;
      color: #b2faff;
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
</style>