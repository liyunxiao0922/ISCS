<template>
  <div>
    <div class="goback" @click="close3d">
    </div>
    <unity src="../../../../static/tunnel3D/Build/tunnal_3D.json" width="100%" height="1019" unityLoader="../../../../static/tunnel3D/Build/UnityLoader.js" ref="unityvue" class="main-unity">
    </unity>
  </div>
</template>

<script>
import Unity from "vue-unity-webgl";
export default {
  data() {
    return {

    }
  },
  components: {
    Unity
  },
  methods: {
    close3d() {
      this.$parent.close3d();
    },
  },
  created() {
    /* let ele = document.createElement('script');
    ele.src = './Build/UnityLoader.js';
    document.appendChild(ele);
    UnityLoader.instantiate("unityContainer", "Build/Tunnel3D.json"); */
  }
}
</script>

<style scoped lang="scss">
.goback {
  width: 78px;
  height: 52px;
  position: fixed;
  background: transparent;
  right: 0;
  top: 68px;
  z-index: 10001;
}
</style>
