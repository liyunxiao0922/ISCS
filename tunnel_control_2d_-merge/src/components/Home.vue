<template>
  <div class="home-container">
    <v-header></v-header>
    <main>
      <router-view></router-view>
    </main>
  </div>
</template>

<script>
import head from '../pages/Head';
export default {
  components: {
    'v-header': head
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
@import '@/common/scss/mixin.scss';
.home-container {
  position: relative;
  min-width: 1920px;
  min-height: 1080px;
  // background:linear-gradient(-90deg,rgba(8,15,43,1) 3%,rgba(13,27,72,1) 54%,rgba(8,15,41,1) 100%);
  background: rgb(20, 26, 44);
  position: relative;
  height: 1080px;
  background-size: cover;
  // height: 1080px;
  overflow: hidden;
  main {
    width: 100%;
    height: 100%;
  }
}
</style>
