<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="事件类型" prop="accidentType">
        <el-select v-model="queryParams.accidentType" placeholder="请选择事件类型" clearable size="small">
            <el-option
                v-for="item in options"
                :key="item.dictValue"
                :label="item.dictLabel"
                :value="item.dictValue">
            </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="位置" prop="location">
        <el-input
          v-model="queryParams.location"
          placeholder="请输入位置"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="方向" prop="direction">
        <el-input
          v-model="queryParams.direction"
          placeholder="请输入方向"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="桩号" prop="stakeNumber">
        <el-input
          v-model="queryParams.stakeNumber"
          placeholder="请输入桩号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="路况" prop="roadConditions">
        <el-input
          v-model="queryParams.roadConditions"
          placeholder="请输入路况"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="事件等级" prop="level">
        <el-input
          v-model="queryParams.level"
          placeholder="请输入事件等级"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="当事人手机号" prop="phoneNum">
        <el-input
          v-model="queryParams.phoneNum"
          placeholder="请输入当事人手机号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
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
          v-hasPermi="['event:accident:add']"
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
          v-hasPermi="['event:accident:edit']"
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
          v-hasPermi="['event:accident:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['event:accident:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="accidentList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="accidentId" />
      <el-table-column label="事件类型" align="center"  prop="accidentType">
            <template slot-scope="scope">
                {{statusFormat(scope.row)}}
            </template>    
      </el-table-column>
      <el-table-column label="位置" align="center" prop="location" />
      <el-table-column label="方向" align="center" prop="direction" />
      <el-table-column label="桩号" align="center" prop="stakeNumber" />
      <el-table-column label="路况" align="center" prop="roadConditions" />
      <el-table-column label="事件等级" align="center" prop="level" />
      <el-table-column label="当事人手机号" align="center" prop="phoneNum" />
      <el-table-column label="简要描述" align="center" prop="message" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="goto('eventLog', scope.row.accidentId)"
            v-hasPermi="['event:accident:edit']"
          >查看事件处理进度</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['event:accident:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['event:accident:remove']"
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

    <!-- 添加或修改对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="事件类型" prop="eventType">
          <el-select v-model="form.accidentType" placeholder="请选择事件类型">
            <el-option
                v-for="item in options"
                :key="item.dictValue"
                :label="item.dictLabel"
                :value="item.dictValue">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="位置" prop="location">
          <el-input v-model="form.location" placeholder="请输入位置" />
        </el-form-item>
        <el-form-item label="方向" prop="direction">
          <el-input v-model="form.direction" placeholder="请输入方向" />
        </el-form-item>
        <el-form-item label="桩号" prop="stakeNumber">
          <el-input v-model="form.stakeNumber" placeholder="请输入桩号" />
        </el-form-item>
        <el-form-item label="路况" prop="roadConditions">
          <el-input v-model="form.roadConditions" placeholder="请输入路况" />
        </el-form-item>
        <el-form-item label="事件等级" prop="level">
          <el-input v-model="form.level" placeholder="请输入事件等级" />
        </el-form-item>
        <el-form-item label="当事人手机号" prop="phoneNum">
          <el-input v-model="form.phoneNum" placeholder="请输入当事人手机号" />
        </el-form-item>
        <el-form-item label="简要描述" prop="message">
          <el-input v-model="form.message" type="textarea" placeholder="请输入内容" />
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
import { listAccident, getAccident, delAccident, addAccident, updateAccident, exportAccident } from "@/api/accident";

export default {
  name: "Accident",
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
      // 表格数据
      accidentList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        accidentType: null,
        location: null,
        direction: null,
        stakeNumber: null,
        roadConditions: null,
        level: null,
        phoneNum: null,
        message: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        /* direction: [
          { required: true, message: "方向不能为空", trigger: "blur" }
        ],
        phoneNum: [
          { required: true, message: "当事人手机号不能为空", trigger: "blur" }
        ], */
      },
      options: []
    };
  },
  created() {
    this.getList();
    this.getDicts('event_type')
    .then(res => {
        this.options = res.data;
    })
  },
  methods: {
    // 字典状态字典翻译
    statusFormat(row, column) {
      return this.selectDictLabel(this.options, row.accidentType);
    },
    /** 查询列表 */
    getList() {
      this.loading = true;
      listAccident(this.queryParams).then(response => {
        this.accidentList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 跳转页面
    goto(path, val) {
      this.$router.push({
        name: path,
        query: {
          id: val
        }
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
        accidentId: null,
        accidentType: null,
        location: null,
        direction: null,
        stakeNumber: null,
        roadConditions: null,
        level: null,
        phoneNum: null,
        message: null
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
      this.ids = selection.map(item => item.accidentId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const accidentId = row.accidentId || this.ids
      getAccident(accidentId).then(response => {
        this.form = response.data;
        this.form.accidentType += '';
        this.open = true;
        this.title = "修改";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.accidentId != null) {
            updateAccident(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAccident(this.form).then(response => {
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
      const accidentIds = row.accidentId || this.ids;
      this.$confirm('是否确认删除编号为"' + accidentIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delAccident(accidentIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportAccident(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
