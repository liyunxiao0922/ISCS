<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="事件主键" prop="accidentId">
        <el-input
          v-model="queryParams.accidentId"
          disabled
          placeholder="请输入事件主键"
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="处理阶段" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择处理阶段" clearable size="small">
          <el-option
            v-for="dict in statusOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
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
          v-hasPermi="['event:log:add']"
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
          v-hasPermi="['event:log:edit']"
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
          v-hasPermi="['event:log:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['event:log:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- <el-table v-loading="loading" :data="logList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="id" />
      <el-table-column label="事件主键" align="center" prop="accidentId" />
      <el-table-column label="处理阶段" align="center" prop="status" :formatter="statusFormat" />
      <el-table-column label="处理详情" align="center" prop="description" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['event:log:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['event:log:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table> -->
    <el-steps :active="stepId">
      <el-step :title="item.dictLabel" v-for="item in statusOptions" :key="item.dictValue">
        <template slot="description">
          <span v-for="(item1, index) in logList" :key="index">
            <span v-if="item1.status == item.dictValue">{{item1.description}}
              <div v-for="(item2, index2) in item1.eventLogFiles" :key="index2">
                <video width="100%" @error='hidden' controls="controls" :src="item2.uri" />
                <img width="100%" @error='hidden' :src="item2.uri" />
              </div>
            </span>
          </span>
        </template>
      </el-step>
    </el-steps>
    <!-- <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    /> -->

    <!-- 添加或修改事件处置节点对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="事件主键" prop="accidentId">
          <el-input disabled v-model="queryParams.accidentId" placeholder="请输入事件主键" />
        </el-form-item>
        <el-form-item label="处理阶段">
          <span v-for="dict in statusOptions" :key="dict.dictValue">
            <span v-if="dict.dictValue == form.status">{{dict.dictLabel}}</span>
          </span>
          <!-- <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in statusOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
              disabled
            >{{dict.dictLabel}}</el-radio>
          </el-radio-group> -->
        </el-form-item>
        <el-form-item label="处理详情" prop="description">
          <el-input v-model="form.description" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="事故资料" label-width="100" prop="deviceCode">
                <el-upload
                    class="upload-demo"
                    action="https://jsonplaceholder.typicode.com/posts/"
                    :file-list.sync="fileList"
                    :auto-upload="false"
                    :on-change="getImg"
                    :on-remove="getImg"
                    list-type="picture">
                    <!-- :limit="1" -->
                    <el-button size="small" type="primary">点击上传</el-button>
                    <!-- <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div> -->
                </el-upload>
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
import { upImg, listLog, getLog, delLog, addLog, updateLog, exportLog } from "@/api/log";


export default {
  name: "Log",
  components: {
  },
  data() {
    return {
      stepId: 0,
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
      // 事件处置节点表格数据
      logList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 处理阶段字典
      statusOptions: [],
      // 查询参数
      queryParams: {
        // pageNum: 1,
        // pageSize: 10,
        accidentId: this.$route.query.id,
        status: null,
        description: null,
      },
      // 表单参数
      form: {
        accidentId: this.$route.query.id,
        status: '',
        description: ''
      },
      
      // 表单校验
      rules: {
        status: [
          { required: true, message: "处理阶段不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "处理时间不能为空", trigger: "blur" }
        ],
        description:[
          { required: true, message: "处理详情不能为空", trigger: "blur" }
        ],
      },
      fileList:[],
      changeFlag: false,
      imgList: []
    };
  },
  created() {
    this.getList();
    this.getDicts("event_node").then(response => {
      this.statusOptions = response.data;
    });
  },
  methods: {

    // 文件状态改变时的钩子，添加文件、上传成功和上传失败时都会被调用
    getImg(file, fileList) {
        this.imgList = fileList;
        // 用来判断是否删除过图片
        this.changeFlag = true;
    },
    /** 查询事件处置节点列表 */
    getList() {
      this.loading = true;
      listLog(this.queryParams).then(response => {
        this.logList = response.rows;
        this.total = response.total;
        this.loading = false;
        this.stepId = +this.logList[this.logList.length - 1].status;
        this.form.status = this.stepId + 1 + '';
        // console.log(this.logList);
      });
    },
    // 处理阶段字典翻译
    statusFormat(row, column) {
      return this.selectDictLabel(this.statusOptions, row.status);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      Object.assign(this.form, {
        id: null,
        accidentId: this.$route.query.id,
        description: null,
        createTime: null,
        status: this.stepId + 1 + '',
        eventLogFiles: []
      })
      /* this.form = {
        id: null,
        accidentId: null,
        description: null,
        createTime: null
      }; */
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
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加事件处置节点";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getLog(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改事件处置节点";
      });
    },
    hidden(e) {
      console.log(e.target.style.display = 'none');
    },
    /** 提交按钮 */
    submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            /* if (this.form.id != null) {
              updateLog(this.form).then(response => {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else { */
            if(this.changeFlag) {
              let imgData = new FormData();
              imgData.append('file', this.imgList[0].raw);
              upImg(imgData)
              .then(res => {
                this.form.eventLogFiles.push({
                  uri: res.url,
                  fileName: this.imgList[0].raw.name
                })
                addLog(this.form).then(response => {
                  this.msgSuccess("新增成功");
                  this.open = false;
                  this.getList();
                });
              })
            }else {
              addLog(this.form).then(response => {
                this.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              });
            }
            /* } */
          }
        });
      
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除事件处置节点编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delLog(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有事件处置节点数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportLog(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  },
  watch: {
    'logList': {
      handler(to, from) {
        console.log(to, from);
      },
      deep: true
    }
  }
};
</script>
