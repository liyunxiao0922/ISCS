<template>
  <div>
    <el-form ref="form" :model="form" label-width="88px">
      <el-form-item label="桩号区间：">
        <el-col :span="8">
          <el-input v-model="form.begin"></el-input>
        </el-col>
        <el-col class="line" :span="2" style="text-align: center">-</el-col>
        <el-col :span="8">
          <el-input v-model="form.end"></el-input>
        </el-col>
      </el-form-item>
      <el-form-item label="时间区段：">
        <el-col :span="16">
          <el-date-picker
            :picker-options="option"
            v-model="form.date"
            type="datetimerange"
            value-format="yyyy-MM-dd HH:mm:ss"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期">
          </el-date-picker>
        </el-col>
        <!-- <el-col class="line" :span="2" style="text-align: center">-</el-col>
        <el-col :span="8">
          <el-time-picker
            placeholder="选择时间"
            v-model="form.date2"
            style="width: 100%"
          ></el-time-picker>
        </el-col> -->
      </el-form-item>
      <el-form-item label="巡检间隔：">
        <el-select v-model="form.waitTime" placeholder="请选择巡检间隔">
          <el-option label="1分钟" :value="60000"></el-option>
          <el-option label="2分钟" :value="120000"></el-option>
        </el-select>
      </el-form-item>
      <div style="text-align: right; margin: 0">
        <el-button type="primary" style="width: 100px; height: 30px; background: #2E373F; border: #2E373F;" size="mini" @click="onSubmit">确定</el-button>
      </div>
    </el-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      type: '',
      form: {
        begin: "",
        end: "",
        waitTime: "",
        date: "",
        /* type: [],
        resource: "",
        desc: "", */
      },
      option: {
        disabledDate(time) {
          return time.getTime() < Date.now() - 8.64e7;
        }
      }
      /* rules: {
        begin: [
          { required: true, message: '请输入桩号', trigger: 'blur' },
          { min: 7, max: 8, message: '长度在 7 到 8 个字符', trigger: 'blur' }
        ],
        end: [
          { required: true, message: '请输入桩号', trigger: 'blur' },
          { min: 7, max: 8, message: '长度在 7 到 8 个字符', trigger: 'blur' }
        ],
        waitTime: [
          { required: true, message: '请选择巡检间隔', trigger: 'change' }
        ],
        date: [
          { type: 'date', required: true, message: '请选择时间区段', trigger: 'change' }
        ],
      } */
    };
  },
  methods: {
    onSubmit() {
      if(this.form.begin && this.form.end && this.form.waitTime && this.form.date.length) {
        if(localStorage.robotInfo) {
          // let obj = JSON.parse(localStorage.robotInfo);
          let key;
          if(this.type === 0) {
            key = 'sectionA'
          }else {
            key = 'section'
          }
          // localStorage.robotInfo = JSON.stringify(obj)
          this.$store.commit('setAuto', {
            key: key,
            value: {...this.form}
          })
        }else {
          if(this.type === 0) {
            this.$store.commit('setAuto', {
              key: 'sectionA',
              value: {...this.form}
            })
            // localStorage.robotInfo = JSON.stringify({sectionA: obj});
          }else {
            this.$store.commit('setAuto', {
              key: 'section',
              value: {...this.form}
            })
            // localStorage.robotInfo = JSON.stringify({section: obj});
          }
        }
      }else {
        this.$message.warning('请完善内容')
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
/deep/ .el-input__inner{
  background-color: rgba(47,54,61,0.25);
  color: #9f9f9f;
  height: 35px;
  font-size: 12px;
  border: 0;
}
/deep/input {
  background: transparent;
}
</style>
<style lang="scss" scoped>
.imgBox{
    width: 100%;
    height: 200px;
    &>img{
        width: 100%;
        height: 100%;
    }
}
</style>