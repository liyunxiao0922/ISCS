<template>
  <ul :class="type === 'bottom' ? 'scaleMainBottom' : 'scaleMainTop'">
    <li
      v-for="(item, index) in 41"
      :key="index"
      :style=" `left: ${sumScale(item)};` "
      :class="classChange(item)"
    >
      <div v-if="type === 'bottom'" style="margin: -27px 0 0 -20px;">{{ sumKM(item - 1) }}</div>
      <div v-else style="margin: 8px 0 0 -20px;">{{ sumKM(item - 1) }}</div>
    </li>
  </ul>
</template>

<script>
export default {
  props: {
    /* scaleData: {
      type: Array,
      default: [],
    }, */
    devRatio: { // 每米换算的像素
      type: Number,
      default: 0
    },
    scaleLeng: { // 每刻度换算的米
      type: Number,
      default: 0,
    },
    type: {
      type: String,
      default: "bottom",
    },
  },
  data() {
    return {

    }
  },
  methods: {
    classChange(value) {
      // console.log(value);
      if ((value - 1) % 10 == 0) {
        return "majorStation";
      } else if((value - 1) % 5 == 0) {
        return "midStation";
      }else {
        return 'smallRailwayStation'
      }
    },
    sumScale(value) {
      // 因为刻度有宽度所以减掉宽度的一半
      let num = 0;
      if ((value - 1) % 5 == 0) {
        num = 5;
      } else{
        num = 2
      }
      return (this.scaleLeng * (value - 1)) * this.devRatio - num + 'px';
    },
    // 桩号
    sumKM(value) {
      if (value % 5 == 0) {
        let after = value * this.scaleLeng %1000;
        after = after < 100 ? (after < 10 ? `00${after}` : `0${after}`) : after;
        return `k${36 + Math.floor(value * this.scaleLeng / 1000)}+${after}`;
      }
    }
  }
};
</script>

<style lang="scss" scoped>
.scaleMainTop {
  width: 1650px;
  border-top: 3px solid #484b4d;
  height: 100%;
  margin: 0;
  position: relative;
  color: #3b4045;
  list-style: none;
  font-size: 10px;
  .majorStation {
    position: absolute;
    top: -9px;
    width: 10px;
    height: 20px;
    line-height: 40px;
    background-color: #484b4d;
    vertical-align: bottom;
  }
  .midStation {
    position: absolute;
    top: 0;
    left: 5%;
    width: 10px;
    vertical-align: bottom;
    line-height: 20px;
    height: 7px;
    background-color: #484b4d;

  }
  .smallRailwayStation {
    position: absolute;
    top: 0;
    left: 5%;
    width: 4px;
    vertical-align: bottom;
    line-height: 20px;
    height: 4px;
    background-color: #484b4d;
  }
}
.scaleMainBottom {
  border-bottom: 3px solid #484b4d;
  height: 100%;
  margin: 0;
  width: 1650px;
  position: relative;
  color: #3b4045;
  list-style: none;
  font-size: 10px;

  .majorStation {
    position: absolute;
    bottom: -9px;
    width: 10px;
    height: 20px;
    line-height: 40px;
    background-color: #484b4d;
    vertical-align: bottom;
  }
  .midStation {
    position: absolute;
    bottom: 0;
    left: 5%;
    width: 10px;
    vertical-align: bottom;
    line-height: 33px;
    height: 7px;
    background-color: #484b4d;

  }
  .smallRailwayStation {
    position: absolute;
    bottom: 0;
    left: 5%;
    width: 4px;
    vertical-align: bottom;
    line-height: 20px;
    height: 4px;
    background-color: #484b4d;
  }
}
</style>