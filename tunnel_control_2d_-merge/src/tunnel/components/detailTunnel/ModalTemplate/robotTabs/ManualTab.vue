<template>
  <div>
    <el-form :inline="true" :model="formInline" class="demo-form-inline">
      <el-form-item label="桩号">
        <el-input v-model="formInline.m" placeholder="请输入桩号"></el-input>
      </el-form-item>
      <el-form-item>
        <el-checkbox v-model="formInline.region">紧急最高速</el-checkbox>
      </el-form-item>
      <el-form-item style="float: right;">
        <div style="text-align: right; margin: 0">
          <el-button type="primary" style="width: 100px; height: 30px; background: #2E373F; border: #2E373F;" size="mini" @click="onSubmit">确定</el-button>
        </div>
      </el-form-item>
    </el-form>
    <el-form :inline="true" :model="formInline" class="demo-form-inline">
      <el-form-item>
        <div style="text-align: right; margin: 0">
          <el-button type="primary" style="width: 100px; height: 30px; background: #2E373F; border: #2E373F;" size="mini" @click="fireBombRelease">灭火弹释放</el-button>
        </div>
      </el-form-item>
    </el-form>
    <el-dialog
      title="提示"
      :visible.sync="centerDialogVisible"
      width="30%"
      append-to-body
      center>
      <span style="color:#b2faff">此操作将灭火弹释放, 是否继续?</span>
      <span slot="footer" class="dialog-footer">
        <div style="text-align: right; margin: 0">
          <el-button type="primary" style="width: 100px; height: 30px; background: #2E373F; border: #2E373F;" size="mini" @click="centerDialogVisible = false">确定</el-button>
        </div>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      formInline: {
        m: "",
        region: "",
      },
      type: '',
      centerDialogVisible:false,
    };
  },
  methods: {
    onSubmit() {
      if(this.formInline.m) {
        if(this.formInline.region) {
          let obj = {
            event: 1,
            formInline: this.formInline.m, 
            type: this.type
          }
          this.$store.commit('setEvent', obj)
        }else {
          let obj = {
            event: 2,
            formInline: this.formInline.m, 
            type: this.type
          }
          this.$store.commit('setEvent', obj)
        }
      }else {
        this.$message.warning('请输入桩号');
      }
      this.$emit("handleClose");
    },
    fireBombRelease(){
      this.centerDialogVisible = true;
    },
    setType(num) {
      this.type = num;
    }
  },
};
</script>
<style scoped>
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