<template>
  <ul>
    <li>
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>上行</span>
        </div>
        <div class="cardContextBox">
          <div
            class="btnItem"
            v-for="item in activeDevStatusList"
            :key="item.iconCode"
            @click="changeDevTypeStatusFn(item, 1)"
          >
            <img :src="item.imageAddress" alt="" />
            <span>{{ item.iconDescription }}</span>
          </div>
        </div>
      </el-card>
    </li>
    <li>
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>下行</span>
        </div>
        <div class="cardContextBox">
          <div
            class="btnItem"
            v-for="item in activeDevStatusList"
            :key="item.iconCode"
            @click="changeDevTypeStatusFn(item, 0)"
          >
            <img :src="item.imageAddress" alt="" />
            <span>{{ item.iconDescription }}</span>
          </div>
        </div>
      </el-card>
    </li>
    <li>
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>全隧道</span>
        </div>
        <div class="cardContextBox">
          <div
            class="btnItem"
            v-for="item in activeDevStatusList"
            :key="item.iconCode"
            @click="changeDevTypeStatusFn(item, undefined)"
          >
            <img :src="item.imageAddress" alt="" />
            <span>{{ item.iconDescription }}</span>
          </div>
        </div>
      </el-card>
    </li>
  </ul>
</template>

<script>
import { editDevTypeStatus } from "@/api/tunnel";
export default {
  props: {
    activeDevStatusList: {
      type: Array,
      default: [],
    },
  },
  methods: {
    /**
     * 修改设备类型的状态
     * @param dev   设备类型状态数据
     * @param groupType  分组状态
     */
    changeDevTypeStatusFn(dev, groupType) {
      editDevTypeStatus({
        deviceTypeId: dev.deviceTypeId,
        deviceGroup: groupType,
        workStatus: dev.iconCode,
      })
        .then((response) => {
          this.$message.success("更改成功");
          this.$emit("groupControlClick");
          this.$emit("getDevTypeList");
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>

<style lang="scss" scoped>
ul,
li {
  margin: 0;
  padding: 0;
  list-style: none;
}
ul {
  display: flex;
  justify-content: space-around;
  li {
    width: 32%;
    .cardContextBox {
      display: flex;
      justify-content: space-around;
      flex-wrap: wrap;
      .btnItem {
        display: flex;
        flex-direction: column;
        cursor: pointer;
        text-align: center;
        width: 43%;
        margin-bottom: 10px;
        line-height: 30px;
      }
    }
  }
}
</style>