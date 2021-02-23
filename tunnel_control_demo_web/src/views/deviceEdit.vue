<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['base:device:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['base:device:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['base:device:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['base:device:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>
    <el-table v-loading="loading" :data="deviceList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" prop="deviceId" class-name="small-padding fixed-width">
      </el-table-column>
      <el-table-column label="设备编号" align="center" prop="deviceCode" class-name="small-padding fixed-width">
      </el-table-column>
      <el-table-column label="上行/下行" align="center" prop="deviceCode" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          {{scope.row.deviceGroup == 1 ? '上行' : '下行'}}
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['base:device:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['base:device:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改基础数据设备对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="设备编号" prop="deviceCode">
            <el-input v-model="form.deviceCode"></el-input>
          </el-form-item>
          <el-form-item label="上行/下行" prop="deviceGroup">
            <el-select v-model="form.deviceGroup" placeholder="请选择上行或下行">
              <el-option label="上行" value="1"></el-option>
              <el-option label="下行" value="0"></el-option>
            </el-select>
          </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" :loading="btnloading" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listTunnelDevice, getTunnelDevice, delTunnelDevice, addTunnelDevice, updateTunnelDevice, exportTunnelDevice } from "@/api/tunnelDevice";

export default {
  name: "Device",
  components: {
  },
  data() {
    return {
      btnloading: false,
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
        deviceTypeId: this.$route.query.id
      },
      // 表单参数
      form: {
        deviceCode: '', //设备编号
        deviceGroup: '', //上行1下行0
        deviceTypeCode: this.$route.query.id,//设备分类编号
      },
      // 表单校验
      rules: {
        deviceCode: [
          { required: true, message: '请输入设备编号', trigger: 'blur' },
          { min: 1, max: 15, message: '长度在 1 到 15 个字符', trigger: 'blur' }
        ],
        deviceGroup: [
          { required: true, message: '请选择上行或下行', trigger: 'change' }
        ],
      },
      listDataObj: {

      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询基础数据设备列表 */
    getList() {
      this.loading = true;
      listTunnelDevice(this.queryParams).then(response => {
        this.deviceList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        deviceCode: null, // 设备编号
        deviceGroup: null, // 设备上行1/下行0
        deviceTypeId: this.$route.query.id // 类型id
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
      this.title = "添加设备";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const deviceId = row.deviceId || this.ids
      getTunnelDevice(deviceId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改设备";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.deviceId != null) {
            updateTunnelDevice(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTunnelDevice(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const deviceIds = row.deviceId || this.ids;
      this.$confirm('是否确认删除基础数据设备编号为"' + deviceIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delTunnelDevice(deviceIds);
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
          return exportTunnelDevice(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
