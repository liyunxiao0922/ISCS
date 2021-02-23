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
            v-hasPermi="['base:tunnel:add']"
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
            v-hasPermi="['base:tunnel:edit']"
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
            v-hasPermi="['base:tunnel:remove']"
            >删除</el-button>
        </el-col>
        <el-col :span="1.5">
            <el-button
            type="warning"
            plain
            icon="el-icon-download"
            size="mini"
            @click="handleExport"
            v-hasPermi="['base:tunnel:export']"
            >导出</el-button>
        </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="tunnelList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="隧道名称" align="center" prop="tunnelName" class-name="small-padding fixed-width">
        </el-table-column>
        <el-table-column label="起始位置" align="center" prop="startSign" class-name="small-padding fixed-width">
        </el-table-column>
        <el-table-column label="隧道位置" align="center" prop="tunnelPosition" class-name="small-padding fixed-width">
        </el-table-column>
        <el-table-column label="隧道长度(米)" align="center" prop="tunnelLength" class-name="small-padding fixed-width">
        </el-table-column>
        <el-table-column label="隧道详情" align="center" prop="tunnelDetail" class-name="small-padding fixed-width">
        </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['base:tunnel:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['base:tunnel:remove']"
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
        <el-form-item label="隧道名称" prop="tunnelName">
            <el-input v-model="form.tunnelName"></el-input>
        </el-form-item>
        <el-form-item label="隧道长度(米)" prop="tunnelLength">
            <el-input type="number" v-model="form.tunnelLength"></el-input>
        </el-form-item>
        <el-form-item label="起始位置" prop="startSign">
            <el-input v-model="form.startSign"></el-input>
        </el-form-item>
        <el-form-item label="隧道位置" prop="tunnelPosition">
            <el-input v-model="form.tunnelPosition"></el-input>
        </el-form-item>
        <el-form-item label="隧道详情" prop="tunnelDetail">
            <el-input type="textarea" v-model="form.tunnelDetail"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listTunnel, getTunnel, delTunnel, addTunnel, updateTunnel, exportTunnel } from "@/api/tunnel";

export default {
  name: "Tunnel",
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
      tunnelList: [],
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
        tunnelDetail: '', //隧道详情
        tunnelLength: '', // 隧道长度
        tunnelName: '', // 隧道名称
        tunnelPosition: '', // 隧道位置
        startSign: '' // 起始位置
      },
      // 表单校验
      rules: {
        tunnelDetail: [
          { required: false, message: '请输入设备编号', trigger: 'blur' },
          { min: 1, max: 50, message: '长度在 1 到 50 个字符', trigger: 'blur' }
        ],
        tunnelLength: [
          { required: true, message: '请输入隧道长度', trigger: 'blur' },
          { min: 1, max: 15, message: '长度在 1 到 15 个字符', trigger: 'blur' }
        ],
        tunnelName: [
          { required: true, message: '请输入隧道名称', trigger: 'blur' },
          { min: 1, max: 15, message: '长度在 1 到 15 个字符', trigger: 'blur' }
        ],
        tunnelPosition: [
          { required: true, message: '请输入隧道位置', trigger: 'blur' },
          { min: 1, max: 15, message: '长度在 1 到 15 个字符', trigger: 'blur' }
        ],
        startSign: [
          { required: true, message: '请输入起始位置', trigger: 'blur' },
          { min: 1, max: 15, message: '长度在 1 到 15 个字符', trigger: 'blur' }
        ],
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
      listTunnel(this.queryParams).then(response => {
        this.tunnelList = response.rows;
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
        tunnelId: null,
        tunnelName: null,
        tunnelPosition: null,
        startSign: null,
        tunnelLength: null,
        tunnelDetail: null,
        status: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
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
      this.ids = selection.map(item => item.tunnelId)
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
      const tunnelId = row.tunnelId || this.ids
      getTunnel(tunnelId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改基础数据设备";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.tunnelId != null) {
            updateTunnel(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTunnel(this.form).then(response => {
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
      const tunnelIds = row.tunnelId || this.ids;
      this.$confirm('是否确认删除基础数据设备编号为"' + tunnelIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delTunnel(tunnelIds);
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
          return exportTunnel(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
