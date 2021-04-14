<template>
  <div>
    <el-form :inline="true" :model="formInline" class="demo-form-inline">
      <el-form-item>
        <el-radio-group v-model="formInline.region">
          <el-radio :label="3">启动维护</el-radio>
          <el-radio :label="6">取消维护</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item style="float: right">
        <div style="text-align: right; margin: 0">
        <el-button type="primary" style="width: 100px; height: 30px; background: #2E373F; border: #2E373F;" size="mini" @click="onSubmit">确定</el-button>
      </div>
      </el-form-item>
    </el-form>
    <el-form>
      <el-form-item label="维护状态">
        <span>维护中</span>
      </el-form-item>
      <el-form-item label="预计完成时间">
        <span>20min后</span>
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
        type: ''
      },
    };
  },
  methods: {
    onSubmit() {
      if(this.formInline.region) {
        if(this.formInline.region ==  3) {
          let obj = {
            event: 5,
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