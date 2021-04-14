<template>
  <div class="tunnel-monitoring-container">
    <!-- <iframe src="http://tunnel.xaxhl.net/#/dashboard" frameborder="0" class="iframe-wrapper" allowFullScreen></iframe> -->
    <template>
      <tunnel2D />
    </template>
  </div>
</template>

<script>
import tunnel2D from '@/tunnel/views/dashboard/index'
export default {
  components: {
    tunnel2D
  },
  data () {
    return {};
  },
  created () {
    // location.href = 'http://tunnel.xaxhl.net/#/dashboard';
  }
};
</script>

<style lang="scss" scoped>
.tunnel-monitoring-container {
  width: 100%;
  height: 100%;
  .iframe-wrapper {
    width: 100%;
    height: calc(100% - 50px);
    margin-top: 80px;
  }
}
</style>
