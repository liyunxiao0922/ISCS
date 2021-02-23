<template>
  <div class="app-container">
        <el-tabs type="border-card" @tab-click="fn">
            <el-tab-pane v-model="form.deviceTypeCode" v-for="(item, index) in listDataObj" :key="index"  :label="item.dictLabel">
                <template>
                    <dataView ref="dataView" />
                </template>
            </el-tab-pane>
        </el-tabs>
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

  </div>
</template>

<script>
import { listDevice, getDevice, delDevice, addDevice, updateDevice, exportDevice, listData } from "@/api/device";
import dataView from "./table";
export default {
  name: "Device",
  components: {
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 基础数据设备表格数据
      deviceList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
      },
      // 表单参数
      form: {
            deviceCode: '', //设备编号
            deviceGroup: '', //上行1下行0
            deviceTypeCode: '',//设备分类编号
      },
      // 表单校验
      rules: {
      },
      listDataObj: []
    };
  },
  mounted() {
    //   this.$nextTick(() => {
          this.getList();
    //   })
  },
  methods: {
    fn(item) {
      this.getChildList(item.index);
    },
    getChildList(val){
      this.$refs[`dataView`][val].loading = true;
      this.$refs[`dataView`][val].queryParams.tunnelId = this.listDataObj[val].dictType;
      this.$refs[`dataView`][val].queryParams.deviceTypeCode = this.listDataObj[val].dictValue;
      this.$refs[`dataView`][val].getList();
    },
    /** 查询基础数据设备列表 */
    getList() {
      this.loading = true;
      /* listDevice(this.queryParams).then(response => {
          this.deviceList = response.rows;
        this.total = response.total;
        this.loading = false;
      }); */
        listData(
            {
                pageNum: 1,
                pageSize: 10,
                dictType: 'tunnel_001_device_type'          
            }
        ).then(res => {
            this.listDataObj = res.rows;
             this.$nextTick(() => {
                for (const val in this.listDataObj) {
                  this.getChildList(val);
                }
             })
        })
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        deviceCode: null, //设备编号
        deviceGroup: null, //上行1下行0
        deviceTypeCode: null,//设备分类编号
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.deviceId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加基础数据设备";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const deviceId = row.deviceId || this.ids
      getDevice(deviceId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改基础数据设备";
      });
    },
    /** 提交按钮 */
    // submitForm() {
    //     console.log(this.$refs["form"]);
      /* this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.deviceId != null) {
            updateDevice(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDevice(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      }); */
    // },
    /** 删除按钮操作 */
    handleDelete(row) {
      const deviceIds = row.deviceId || this.ids;
      this.$confirm('是否确认删除基础数据设备编号为"' + deviceIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delDevice(deviceIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有基础数据设备数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportDevice(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  },
  components: {
      dataView
  }
};
</script>
