<template>
  <ul :class="type === 'bottom' ? 'scaleMainBottom' : 'scaleMainTop'">
    <li
      v-for="(item, index) in scaleData"
      :key="item.name"
      :style="{ left: devRatio * 5 * index + 'px' }"
      :class="classChange(item.name)"
    >
      {{ item.name }}
    </li>
  </ul>
</template>

<script>
export default {
  props: {
    scaleData: {
      type: Array,
      default: [],
    },
    devRatio: {
      type: Number,
      default: 0,
    },
    type: {
      type: String,
      default: "bottom",
    },
  },
  methods: {
    classChange(value) {
      if (value.includes("000")) {
        return "majorStation";
      } else {
        return "smallRailwayStation";
      }
    },
  },
};
</script>

<style lang="scss" scoped>
.scaleMainTop {
  border-top: 3px solid #082426;
  height: 100%;
  margin: 0;
  position: relative;
  color: #b2faff;
  list-style: none;
  font-size: 10px;

  .majorStation {
    position: absolute;
    top: 0;
    line-height: 20px;
    width: 3px;
    height: 30px;
    background-color: #082426;
  }
  .smallRailwayStation {
    position: absolute;
    top: 0;
    left: 5%;
    width: 3px;
    line-height: 20px;
    height: 20px;
    background-color: #082426;
  }
}
.scaleMainBottom {
  border-bottom: 3px solid #082426;
  height: 100%;
  margin: 0;
  position: relative;
  color: #b2faff;
  list-style: none;
  font-size: 10px;

  .majorStation {
    position: absolute;
    bottom: 0;
    width: 3px;
    height: 30px;
    line-height: 40px;
    background-color: #082426;
    vertical-align: bottom;
  }
  .smallRailwayStation {
    position: absolute;
    bottom: 0;
    left: 5%;
    width: 3px;
    vertical-align: bottom;
    line-height: 20px;
    height: 20px;
    background-color: #082426;
  }
}
</style>