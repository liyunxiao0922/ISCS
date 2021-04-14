<template>
  <div id="app">
    <div class="merge">
      <el-dialog :visible.sync="dialogVisible"
                width="30%"
                :modal="false"
                :lock-scroll="false"
                :close-on-click-modal="false"
                :before-close="handleClose">
        <div class="construction-wrapper">
          <div>
            <p><span class="iconfont icon-ziyuanxhdpi"></span><span></span></p>
            <p></p>
            <p><span></span><span></span></p>
          </div>
        </div>
      </el-dialog>
    </div>
    <router-view />
  </div>
</template>

<script>
export default {
  name: 'App',
  data () {
    return {
      dialogVisible: false
    };
  },
  methods: {
    handleClose () {
      this.dialogVisible = false;
    }
  }
};
</script>

<style lang="scss" scoped>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  height: 100%;
  .merge {
    /deep/ .el-dialog__header,
    /deep/ .el-dialog__body {
      background: rgb(34, 32, 27);
      width: 100%;
      height: 100%;
      border: 1px solid rgb(207, 146, 0);
      // box-shadow: 0 0 10px rgb(207, 146, 0) inset;
    }
    /deep/ .el-dialog__header {
      border-bottom: none;
    }
    /deep/ .el-dialog__body {
      border-top: none;
    }
    .construction-wrapper {
      width: 490px;
      height: 380px;
      // display: flex;
      // flex-direction: column;
      div {
        position: relative;
        margin: 0 10px;
        left: 5px;
        &:first-child {
          width: 100%;
          height: 100%;
          // background: rgb(34, 32, 27);
          // border: 1px solid rgb(207, 146, 0);
          // box-shadow: 0 0 10px rgb(207, 146, 0) inset;
          display: flex;
          flex-direction: column;
          p {
            margin: 10px;
            font-size: 14px;
            line-height: 20px;
            &:first-child {
              color: #e6a23c;
              span {
                color: rgb(255, 234, 0);
                font-size: 22px;
                &:first-child {
                  margin: 5px 20px;
                }
              }
            }
            &:nth-child(2) {
              flex: 1;
            }
            &:last-child {
              color: rgb(255, 234, 0);
              display: flex;
              justify-content: space-between;
            }
          }
        }
      }
    }
  }
}
</style>
