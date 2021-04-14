<template>
    <transition name="el-fade-in">
        <div v-show="showFlag" class="videoShow">
            <i @click="showFlag = false" class="el-icon-close"></i>
            <video  
              :src="url"
              autoplay="autoplay"
              loop
              muted>
            </video>
        </div>
    </transition>
</template>

<script>
export default {
  name: 'videoShow',
  data() {
    return {
        url: '',
        showFlag: false
    }
  },
  components: {

  },
  methods: {
    show(src) {
        this.url = src;
        this.showFlag = true;
    }
  }
}
</script>

<style scoped lang="scss">
    .videoShow {
        width: 100%;
        height: 100%;
        position: fixed;
        left: 0;
        top: 0;
        z-index: 9999;
        background: rgba(0, 0, 0, .9);
        video {
            width: 65%;
            position: absolute;
            top: 0;
            bottom: 0;
            right: 0;
            left: 0;
            margin: auto;
        }
        i {
          font-size: 30px;
          position: absolute;
          text-align: right;
          right: 331px;
          top: 170px;
          width: 100%;
          color: #e2d6d6;
          cursor: pointer;
          z-index: 10000;
        }
    }
</style>