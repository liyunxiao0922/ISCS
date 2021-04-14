<template>
  <div class="mast-monitoring-container">
    <iframe src="http://121.40.128.159:17015/" frameborder="0" class="iframe-wrapper"></iframe>
  </div>
</template>

<script>
export default {

};
</script>

<style lang="scss" scoped>
.mast-monitoring-container {
  width: 100%;
  height: 100%;
  .iframe-wrapper {
    width: 100%;
    height: 100%;
    // height: calc(100% - 80px);
    // margin-top: 80px;
  }
}
</style>
