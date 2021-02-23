<template>
  <ul class="carInfoBox">
    <li>
      <div>
        <span class="tunneliconfont tunneliconfont-cheliuliang"></span>
      </div>
      <div class="dataInfo">
        <p><span class="inputNum">20</span>min</p>
        <p class="triangle-down"></p>
        <p>-车流量-</p>
      </div>
    </li>
    <li>
      <div>
        <span class="tunneliconfont tunneliconfont-zhuangche-"></span>
      </div>
      <div class="dataInfo">
        <p><span class="inputNum">20</span>h</p>
        <p class="triangle-down"></p>
        <p>-平均时速-</p>
      </div>
    </li>
    <li>
      <div>
        <span class="tunneliconfont tunneliconfont-xiaoche"></span>
      </div>
      <div class="dataInfo">
        <p class="inputNum">50%</p>
        <p class="triangle-down"></p>
        <p>-小车占有率-</p>
      </div>
    </li>
    <li>
      <div>
        <span class="tunneliconfont tunneliconfont-cheliuliang"></span>
      </div>
      <div class="dataInfo">
        <p><span class="inputNum">20</span>min</p>
        <p class="triangle-down"></p>
        <p>-车流量-</p>
      </div>
    </li>
    <li>
      <div>
        <span class="tunneliconfont tunneliconfont-zhuangche-"></span>
      </div>
      <div class="dataInfo">
        <p><span class="inputNum">0</span>h</p>
        <p class="triangle-down"></p>
        <p>-平均时速-</p>
      </div>
    </li>
    <li>
      <div>
        <span class="tunneliconfont tunneliconfont-xiaoche"></span>
      </div>
      <div class="dataInfo">
        <p class="inputNum">50%</p>
        <p class="triangle-down"></p>
        <p>-小车占有率-</p>
      </div>
    </li>
  </ul>
</template>

<script>
export default {};
</script>

<style lang="scss" scoped>
.carInfoBox {
  padding: 0;
  margin: 0;
  list-style: none;
  display: flex;
  justify-content: space-around;
  align-items: center;
  flex-wrap: wrap;

  & > li {
    width: 33%;
    text-align: center;
    font-size: 12px;
    height: 50px;
    padding: 5px;
    // line-height: 20px;
    display: flex;
    justify-content: center;
    align-items: center;
    .tunneliconfont {
      font-size: 35px;
    }
    &:nth-child(-n + 3) {
      margin-bottom: 10px;
    }
    p {
      margin: 0;
    }
    .dataInfo {
      color: #b2faff;
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
        border-top: 5px solid red;
      }
    }
  }
}
</style>