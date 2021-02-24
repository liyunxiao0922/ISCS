<template>
  <ul class="scaleMain">
    <li
      v-for="(item, index) in scaleData"
      :key="item.name"
      :style="{ left: 5.25 * index + '%' }"
      :class="classChange(item.name)"
    >{{item.name}}</li>
  </ul>
</template>

<script>
export default {
  props: {
    scaleData: {
      type: Array,
      default: [],
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
.scaleMain {
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
    background-color: #082426;
  }
  .smallRailwayStation {
    position: absolute;
    bottom: 0;
    left: 5%;
    width: 3px;
    height: 20px;
    background-color: #082426;
  }
}
</style>