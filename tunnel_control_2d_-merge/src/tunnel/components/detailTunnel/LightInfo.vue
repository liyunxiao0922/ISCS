<template>
  <el-dialog
    :title="tunnelIlluminationindex()"
    :visible.sync="dialogVisible"
    width="30%"
    class="details"
    :before-close="handleClose"
  >
    <el-form style="height: 500px; overflow-y: scroll; padding: 0 20px;" label-width="65px">
      <h3>灯组设置</h3>
      <el-switch
        v-model="form.value"
        @change="changeLightList"
        active-color="#272C31FF"
        inactive-color="#272C31FF">
      </el-switch>
      <el-collapse v-model="activeNames">
        <el-collapse-item title="普通照明" name="1">
          <el-row>
            <el-col style="height:45px;margin-bottom: 10px;" v-if="item.type == 4" v-for="(item, index) in lightInfoList" :key="index" :span="3">
              <el-tooltip
                placement="top"
                ref="tooltip"
                :hide-after="20000"
                popper-class="devTooltip"
              >
                <div slot="content" class="vehicleDetectorItemContent">
                  <el-card class="box-card">
                    <el-row >
                      <el-col style="padding:0" :span="12">设备名称：</el-col>
                      <el-col style="padding:0" :span="12">普通照明</el-col>
                      <el-col style="padding:0" :span="12">设备位置：</el-col>
                      <el-col style="padding:0" :span="12">{{item.equipPile}}</el-col>
                    </el-row>
                    <el-row >
                      <el-col style="padding:0" :span="12">设备编号：</el-col>
                      <el-col style="padding:0" :span="12">{{item.equipId}}</el-col>
                      <el-col style="padding:0" :span="12">设备位置：</el-col>
                      <el-col v-if="item.sysTunnelDirection == 1" style="padding:0" :span="12">上行</el-col>
                      <el-col v-else style="padding:0" :span="12">下行</el-col>
                    </el-row>
                  </el-card>
                </div>
                <div class="itemBox" @click="openPop($event, item)">
                  <img style="width:35px" :src="item.imageAddress" alt="" @mouseout="leaver(index)">
                  <div>
                    {{item.equipPile}}
                  </div>
                </div>
              </el-tooltip>
            </el-col>
          </el-row>
        </el-collapse-item>
        <el-collapse-item title="加强照明" name="2">
          <el-row>
            <el-col v-if="item.type == 10" v-for="(item, index) in lightInfoList" :key="index" :span="3">
              <el-tooltip
                placement="top"
                ref="tooltip"
                :hide-after="20000"
                popper-class="devTooltip"
              >
                <div slot="content" class="vehicleDetectorItemContent">
                  <el-card class="box-card">
                    <el-row >
                      <el-col style="padding:0" :span="12">设备名称：</el-col>
                      <el-col style="padding:0" :span="12">加强照明</el-col>
                      <el-col style="padding:0" :span="12">设备位置：</el-col>
                      <el-col style="padding:0" :span="12">{{item.equipPile}}</el-col>
                    </el-row>
                    <el-row >
                      <el-col style="padding:0" :span="12">设备编号：</el-col>
                      <el-col style="padding:0" :span="12">{{item.equipId}}</el-col>
                      <el-col style="padding:0" :span="12">设备位置：</el-col>
                      <el-col v-if="item.sysTunnelDirection == 1" style="padding:0" :span="12">上行</el-col>
                      <el-col v-else style="padding:0" :span="12">下行</el-col>
                    </el-row>
                  </el-card>
                </div>
                <div class="itemBox" @click="openPop($event, item)" @mouseleave="leaver(index)">
                  <img style="width:35px" :src="item.imageAddress" alt="">
                  <div>
                    {{item.equipPile}}
                  </div>
                </div>
              </el-tooltip>
            </el-col>
          </el-row>
        </el-collapse-item>
      </el-collapse>
    </el-form>
    <el-popover
      placement="top"
      width="200"
      class="lightPop"
      v-model="visible">
      <div style="border-bottom: 1px solid #666; width: 200px; margin-left: -5px; padding: 0 5px; padding-bottom: 5px; font-size: 12px;">
        更改灯光亮度
        <span @click="visible = false"><i class="el-icon-close"></i></span>
      </div>
      <!-- <p> -->
        <div class="setBox">
            <el-radio-group style="margin-top: 10px" v-model="radio">
            <el-radio :label="form.stateArr[0]">照明100%</el-radio>
            <el-radio :label="form.stateArr[1]">照明50%</el-radio>
            <el-radio :label="form.stateArr[2]">照明关闭</el-radio>
          </el-radio-group>
        </div>
      <!-- </p> -->
      <div style="text-align: center; margin: 0"><el-button type="primary" style="width: 190px; height: 30px; background: #2E373F; border: #2E373F;" size="mini" @click="changeModel">确定</el-button></div>
    </el-popover>
  </el-dialog>
</template>

<script>
import {editDevStatus,editDevTypeStatus} from '@/tunnel/api/tunnel';
export default {
  props: {
    dialogVisible: {
      type: Boolean,
      default: false
    },
    lightInfoList: {
      type: Array,
      default: [],
    },
    tunnelIllumination: {
      type: Number
    },
    sysTunnelDirection: {
      type: Number
    },
    lightFlag: {
      type: Object || null || undefined,
    },
  },
  data() {
    return {
      form: {
        stateArr: []
      },
      activeNames: ['1','2'],
      visible: false,
      radio: 0
    };
  },
  watch: {
  },
  methods: {
    tunnelIlluminationindex(){
      if(this.tunnelIllumination === 1) return '灯光详情-入口段1'
      else if(this.tunnelIllumination === 2) return '灯光详情-入口段2'
      else if(this.tunnelIllumination === 3) return '灯光详情-过渡段1'
      else if(this.tunnelIllumination === 4) return '灯光详情-过渡段2'
      else if(this.tunnelIllumination === 5) return '灯光详情-中间段'
      else if(this.tunnelIllumination === 6) return '灯光详情-出口段1'
      else if(this.tunnelIllumination === 7) return '灯光详情-出口段2'
      else return '灯光详情-隧道外区域'
    },
    /**
     * 关闭菜单
     */
    handleClose() {
      this.$emit("lightingInfoClose");
      this.visible = false
    },
    /**
     * 修改整个灯光区域的状态
     * 11 普光 100%   
     * 26 普光 关闭
     * 28 强光 100%
     * 30 强光 关闭
     */
    changeLightList(data){
      let equipTypeOperationId = '';
      let strengthenEquipTypeOperationId = '';
      let equipType = 4;
      let strengthenEquipType = 10;
      if(data){
        equipTypeOperationId = '11';
        strengthenEquipTypeOperationId = '28';
      } else {
        equipTypeOperationId = '26';
        strengthenEquipTypeOperationId = '30';
      }
      this.editDevTypeStatus(equipType,equipTypeOperationId);
      this.editDevTypeStatus(strengthenEquipType,strengthenEquipTypeOperationId);
    },
    editDevTypeStatus(type,equipTypeOperationId) {
       editDevTypeStatus({
        type: type,
        sysTunnelDirection: this.sysTunnelDirection,
        equipTypeOperationId: equipTypeOperationId,
        tunnelIllumination: this.tunnelIllumination
      })
        .then((response) => {
          this.$emit('lightingClickOpen',this.lightFlag)
        })
        .catch((error) => {
          console.log(error);
        });
    },
    /**
     * 修改设备的状态
     * @param deviceId   设备id
     * @param workStatus  设备状态
     * @param source 来源
     */
    changeDevTypeStatusFn(deviceId, workStatus) {
      editDevStatus({
        deviceId,
        workStatus,
      })
        .then((response) => {
          this.visible = false; 
        })
        .catch((error) => {
          console.log(error);
        });
    },
    leaver(index){
      this.$refs.tooltip[index].showPopper = false;
    },
    openPop(event, item) {
      console.log(event.layerX,event.layerY);
      let arr = [['11', '12', '26'], ['28', '29', '30']];
      document.querySelector('.lightPop .el-popover').style.left = event.layerX + 'px';
      document.querySelector('.lightPop .el-popover').style.top = event.layerY + 'px';
      this.form = item;
      if(arr[0].indexOf(item.equipTypeOperationId) != -1) {
        this.form.stateArr = arr[0];
      }else {
        this.form.stateArr = arr[1];
      }
      this.radio = item.equipTypeOperationId;
      this.visible = true;
    },
    changeModel() {
      if(this.radio != this.form.equipTypeOperationId) {
        this.$parent.lightingChangeData(this.form.equipId, this.radio);
      }
    },
    propClose() {
      this.visible = false;
    }
  },
};
</script>
<style scoped>
/deep/ .el-dialog {
  background: #191D21;
}
/deep/ .el-dialog__header .el-dialog__title{
  font-size: 15px;
  color: #ffffff;
  background-color: #191D21;
}
/deep/ .el-collapse-item__header{
  background-color: #191D21;
  color:#fff;
  width: 80px;
  height: 30px;
  border: 0;
}
/deep/ .el-dialog__body {
  padding: 6px 20px;
}
/deep/ .el-collapse {
    border: 0;
}
/deep/ .el-dialog__headerbtn {
    position: absolute;
    top: 11px;
    right: 20px;
    padding: 0;
    background: 0 0;
    border: none;
    outline: 0;
    cursor: pointer;
    font-size: 16px;
}
/deep/ .el-collapse-item__wrap{
    border: 0;
    background-color: #191D21;
}
/deep/ .el-form-item__label {
  color: #fff;
  font-size: 12px;
}
/deep/.el-card__body {
    padding: 10px 5px;
    width: 145px;
}
/deep/.el-dialog__body {
  padding: 6px 0;
}
.el-icon-close {
  float: right;
  cursor: pointer;
}
</style>
<style lang="scss" scoped>
h3 {
  color:#fff;
  margin: 10px 0;
  font-size: 14px; 
}
.itemBox {
  text-align: center;
  color: #fff;
  margin-bottom: 10px;
  cursor: pointer;
}
/*定义滚动条高宽及背景 高宽分别对应横竖滚动条的尺寸*/
::-webkit-scrollbar
{
	width: 10px;
	height: 16px;
	background-color: #191D21;
}
 
/*定义滚动条轨道 内阴影+圆角*/
::-webkit-scrollbar-track
{
	-webkit-box-shadow: inset 0 0 6px #191D21;
	border-radius: 10px;
	background-color: #191D21;
}
 
/*定义滑块 内阴影+圆角*/
::-webkit-scrollbar-thumb
{
	border-radius: 8px;
	-webkit-box-shadow: inset 0 0 6px #191D21;
	background-color: #262B2F;
}
/* .setBox {
  position: fixed;
} */
</style>
<style>
.el-popover {
  background: #1F2227;
  border: #1F2227;
  color: #fff;
  width: 200px;
  height: 114px;
  padding: 12px 5px;
  right: 36px;
  bottom: 96px;
}
.el-radio {
  color: #eee;
  cursor: pointer;
  margin-right: 10px;
  margin-bottom: 10px;
}
.el-radio__input.is-checked .el-radio__inner {
    border-color: #eee;
    border-width: 2px;
    background: #1F2227;
}
.el-radio__input.is-checked+.el-radio__label {
    color: #eee;
}
.el-radio__inner {
  background: #1F2227;
}
/* /deep/.el-radio__inner::after {
  width: 5px;
  height: 5px;
  background: #1F2227;
  display: block;
} */
.el-radio__input.is-checked .el-radio__inner::after {
  /* display: none; */
  width: 5px;
  height: 5px;
}
.el-range-input {
  background: #1F2428;
}
</style>