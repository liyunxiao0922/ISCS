<template>
  <div>
    <el-form :inline="true" :model="formInline" class="demo-form-inline">
      <el-form-item>
        <el-radio-group v-model="formInline.region">
          <el-radio :label="3">启动充电</el-radio>
          <el-radio :label="6">取消充电</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item style="float: right">
        <div style="text-align: right; margin: 0">
          <el-button type="primary" style="width: 120px; height: 30px; background: #2E373F; border: #2E373F;" size="mini" @click="onSubmit">确定</el-button>
        </div>
      </el-form-item>
    </el-form>
    <el-form>
      <el-form-item label="充电状态">
        <span>正常</span>
      </el-form-item>
      <el-form-item label="剩余电量">
        <span>68%</span>
      </el-form-item>
      <el-form-item label="预计完成时间">
        <span>1h20min后</span>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      formInline: {
        user: "",
        region: "",
        type: ""
      },
    };
  },
  methods: {
    onSubmit() {
      if(this.formInline.region) {
        if(this.formInline.region ==  3) {
          let obj = {
            event: 3,
            formInline: 'k37+300', 
            type: this.type
          }
          this.$store.commit('setEvent', obj)
        }else {
          let obj = {
            event: 4,
            formInline: ``, 
            type: this.type
          }
          this.$store.commit('setEvent', obj)
        }
      }else {
        this.$message.warning('请选择事件');
      }
      this.$emit("handleClose");
    },
    setType(num) {
      this.type = num;
    }
  },
};
</script>
<style scoped>
/deep/ .el-form-item{
  margin-bottom: 10px;
}
/deep/ .el-input__inner{
  background-color: rgba(47,54,61,0.25);
  color: #9f9f9f;
  font-size: 12px;
  border: 0;
}
</style>
<style lang="scss" scoped>
.imgBox {
  width: 100%;
  height: 200px;
  & > img {
    width: 100%;
    height: 100%;
  }
}
</style>