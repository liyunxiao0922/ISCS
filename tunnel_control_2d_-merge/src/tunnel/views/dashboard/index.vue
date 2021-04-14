<template>
<div class="dashboard">
  <DetailTunnel />
</div>
</template>

<script>
// import { mapGetters } from "vuex";
import DetailTunnel from "@/tunnel/components/detailTunnel/index";
export default {
  name: "Dashboard",
  // computed: {
  //   ...mapGetters(["name"]),
  // },
  components: {
    DetailTunnel,
  },
  methods: {},
};
</script>

<style lang="scss" scoped>
.dashboard{
  width: 100%;
  height: 100%;
  margin-top: 3.125vw;
  
}
/deep/ .el-dialog {
  transform: none;
  background: #191D21;
  border: 1px solid #2B3137;
  box-shadow: 0px 9px 32px 0px #0D0E0E;
  border-radius: 4px;
  left: 0;
  position: relative;
  margin: 0 auto;
}
/deep/ .el-dialog__title {
  font-size: 18px;
  font-family: Microsoft YaHei;
  font-weight: 400;
  color: #FFFFFF;
}
// dropdown
/deep/ .el-dropdown-menu {
  background-color: rgba(47,54,61,1);
  .el-dropdown-menu__item {
    color:#fff;
  }
  a {
    display: block
  }
}
</style>
