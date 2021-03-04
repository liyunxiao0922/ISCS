<template>
  <!-- <el-card class="box-card" body-style="padding-bottom: 0;"> -->
    <div>
      <el-form ref="form" :model="form" label-width="120px">
        <el-form-item label="请输入L20值：">
          <el-input v-model="form.name" placeholder="默认为3000"></el-input>
        </el-form-item>
        <el-form-item label="夜间加强照明：">
          <el-switch v-model="form.value"> </el-switch>
        </el-form-item>
        <el-form-item label="开始时间：">
          <el-col :span="11">
            <el-time-picker
              v-model="form.value1"
              placeholder="选择时间"
            >
            </el-time-picker>
          </el-col>
        </el-form-item>
        <el-form-item label="结束时间：">
          <el-col :span="11">
            <el-time-picker
              v-model="form.value2"
              placeholder="选择时间"
            >
            </el-time-picker>
          </el-col>
        </el-form-item>
        <h3>加强段照明自动设定：</h3>
        <el-form-item label-width="0">
          <div class="tableTabBox">
            <div class="tableTabItem1">
              <el-table :data="tableData" style="width: 100%" stripe border>
                <el-table-column prop="name" label="洞外亮度">
                </el-table-column>
                <el-table-column prop="value" label="调光系数">
                </el-table-column>
              </el-table>
            </div>
            <div class="tableTabItem2">
              <el-table :data="tableData1" style="width: 100%" stripe border>
                <el-table-column prop="date" label="单向交通车流量">
                </el-table-column>
                <el-table-column prop="name" label="双向交通车流量">
                </el-table-column>
                <el-table-column prop="address" label="折减系数">
                </el-table-column>
              </el-table>
            </div>
          </div>
        </el-form-item>
        <el-form-item style="text-align: right">
          <el-button type="primary" @click="onSubmit">更新</el-button>
        </el-form-item>
      </el-form>
    </div>
  <!-- </el-card> -->
</template>

<script>
export default {
  data() {
    return {
      form: {},
      tableData: [
        {
          name: "大于0.5L",
          value: "1",
        },
        {
          name: "大于0.25L，小于0.5L",
          value: "0.5",
        },
        {
          name: "大于0.13L，小于0.25L",
          value: "0.25",
        },
        {
          name: "大于0，小于0.13L",
          value: "0,13",
        },
      ],
      tableData1: [
        {
          date: "大于650",
          name: "大于1200",
          address: "1",
        },
        {
          date: "180,650",
          name: "350,200",
          address: "0.7",
        },
        {
          date: "0,180",
          name: "0,350",
          address: "0.5",
        },
      ],
    };
  },
  methods: {
    onSubmit() {},
  },
};
</script>

<style lang="scss" scoped>
.tableTabBox {
  display: flex;
  justify-content: space-between;
  .tableTabItem1 {
    width: 45%;
  }
  .tableTabItem2 {
    width: 53%;
  }
}
</style>