<template>
  <!-- <el-card class="box-card" body-style="padding-bottom: 0;"> -->
    <div>
      <el-form ref="form" :model="form" label-width="140px">
        <el-form-item label="请输入L20值：">
          <el-input v-model="form.name" placeholder="默认为3000" style="width:374px;"></el-input>
        </el-form-item>
        <el-form-item label="夜间加强照明：">
          <el-switch
            v-model="form.value"
            active-color="#272C31FF"
            inactive-color="#272C31FF"
          >
          </el-switch>
        </el-form-item>
        <el-form-item label="开始时间-结束时间：">
            <el-time-picker
              v-model="form.value1"
              placeholder="选择时间"
              style="width:160px;"
            >
            </el-time-picker>
          <span style="margin:0 10px"> —— </span>
            <el-time-picker
              v-model="form.value2"
              placeholder="选择时间"
              style="width:160px;"
            >
            </el-time-picker>
        </el-form-item>
        <h3>加强段照明自动设定：</h3>
        <el-form-item label-width="0" >
          <div class="tableTabBox">
            <div class="tableTabItem1">
              <el-table :data="tableData" style="width: 100%;background-color: #191D21;border-radius: 2px;"  :row-class-name="tableRowClassName">
                <el-table-column width="150" prop="name" label="洞外亮度">
                </el-table-column>
                <el-table-column prop="value" label="调光系数">
                </el-table-column>
              </el-table>
            </div>
            <div class="tableTabItem2">
              <el-table :data="tableData1" style="width: 100%;background-color: #191D21;border-radius: 2px;" :row-class-name="tableRowClassName">
                <el-table-column width="100"  prop="date" label="单向交通车流量">
                </el-table-column>
                <el-table-column width="100" prop="name" label="双向交通车流量">
                </el-table-column>
                <el-table-column prop="address" label="折减系数">
                </el-table-column>
              </el-table>
            </div>
          </div>
        </el-form-item>
        <div style="text-align: center;">
          <el-button class="button" type="primary" @click="onSubmit">更新</el-button>
        </div>
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
          name: "大于120",
          address: "1",
        },
        {
          date: "80,650",
          name: "50,200",
          address: "0.7",
        },
        {
          date: "0,180",
          name: "0,350",
          address: "0.5",
        },
        {
          date: "0,180",
          name: "0,350",
          address: "0.5",
        }
      ],
      type: ''
    };
  },
  methods: {
    onSubmit() {},
    tableRowClassName({row, rowIndex}) {
      if (rowIndex === 0) {
        return 'warning-row';
      } else if (rowIndex === 2) {
        return 'success-row';
      }
      return '';
    },
    setType(num) {
      this.type = num;
    }
  }
};
</script>
<style scoped>
/deep/ .el-input__inner{
  background-color: rgba(47,54,61,0.25);
  color: #9f9f9f;
  font-size: 12px;
  border: 0;
}
/deep/ .el-table th{
  background-color:transparent;
  color: #fff;
  font-size: 12px;
  padding: 6px 0;
}
/deep/ .el-table tr {
  background-color:rgba(47,54,61,0.25);
  color: #9f9f9f;
  font-size: 12px;
}
/deep/ .el-form-item{
  margin-bottom: 10px;
}
/deep/ .el-form-item__label {
  color: #9f9f9f;
  font-size: 12px;
  text-align: left;
}
/deep/ .el-table--enable-row-hover .el-table__body tr:hover>td {
  background-color: #545454;
  color: #9f9f9f;
  font-size: 12px;
}
/deep/ .el-table .cell,/deep/ .el-table--border td:first-child .cell,/deep/ .el-table--border th:first-child .cell {
  padding: 0 8px 0 6px;
}
/deep/ .el-table tr th:first-child,/deep/ .el-table tr td:first-child{
  border: 1px solid #262b30;
}
.tableTabItem2 /deep/ .el-table tr th:nth-child(2),.tableTabItem2 /deep/ .el-table tr td:nth-child(2){
  border: 1px solid #262b30;
}
/deep/ .el-table .warning-row {
  background-color: rgba(47,54,61,0.5);
}
/deep/ .el-table .success-row {
  background-color: rgba(47,54,61,0.5);
}
/deep/ .el-table td,/deep/ .el-table th.is-leaf{
  border: 0;
}
/deep/ .el-table::before{
   height: 0;
}
</style>
<style lang="scss" scoped>
.tableTabBox {
  display: flex;
  font-size: 12px;
  justify-content: space-between;
  .tableTabItem1 {
    width: 46%;
    font-size: 12px;
  }
  .tableTabItem2 {
    width: 53%;
    font-size: 12px;
  }
}
h3 {
  color:#9f9f9f;
  margin: 10px 0;
  font-size: 12px; 
  height: 40px;
  line-height: 40px;
}
.button{
  width: 190px;
  background-color: rgba(47,54,61,1);
  border-radius: 2px;
  border: 0;
  padding: 8px 22px;
}
</style>