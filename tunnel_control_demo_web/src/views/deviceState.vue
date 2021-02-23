<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
        
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['base:statusIcon:add']"
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
          v-hasPermi="['base:statusIcon:edit']"
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
          v-hasPermi="['base:statusIcon:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['base:statusIcon:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table height="664" v-loading="loading" :data="statusIconList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="图标序号" align="center" prop="iconCode"/>
      <el-table-column label="图标" align="center" >
          <template slot-scope="scope">
              <img style="height: 100px" :src="scope.row.imageAddress" alt="">
          </template>
      </el-table-column>
      <el-table-column label="图标描述" align="center" prop="iconDescription"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['base:statusIcon:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['base:statusIcon:remove']"
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
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
            <el-form-item label="状态图标描述" label-width="100" prop="iconDescription">
                <el-input v-model="form.iconDescription"></el-input>
            </el-form-item>
            <el-form-item label="设备图片" label-width="100" prop="deviceCode">
                <el-upload
                    class="upload-demo"
                    action="https://jsonplaceholder.typicode.com/posts/"
                    :file-list.sync="fileList"
                    :auto-upload="false"
                    :limit="1"
                    :on-exceed="maxWar"
                    :on-change="getImg"
                    :on-remove="getImg"
                    list-type="picture">
                    <el-button size="small" type="primary">点击上传</el-button>
                    <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
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
import { upImg, listStatusIcon, getStatusIcon, delStatusIcon, addStatusIcon, updateStatusIcon, exportStatusIcon } from "@/api/statusIcon";

export default {
  name: "StatusIcon",
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
      statusIconList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
      },
      fileList: [],
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        iconDescription: [
            { required: true, message: '请输入图标描述', trigger: 'blur' },
            { min: 1, max: 15, message: '长度在 1 到 15 个字符', trigger: 'blur' }
        ]
      },
      imgList: [],
      changeFlag: false
    };
  },
  created() {
    this.getList();
  },
  methods: {
    // 文件状态改变时的钩子，添加文件、上传成功和上传失败时都会被调用
    getImg(file, fileList) {
        this.imgList = fileList;
        // 用来判断是否删除过图片
        this.changeFlag = true;
    },
    /** 查询基础数据设备列表 */
    getList() {
      this.loading = true;
      this.queryParams.deviceTypeId = this.$route.query.id;
      listStatusIcon(this.queryParams).then(response => {
        this.statusIconList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 文件选择超出限制
    maxWar() {
        this.$message({
            type: 'warning',
            message: '设备图片只能上传一张'
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
          iconDescription: '', // 描述
          deviceTypeId: '', // 设备类别ID
          imageAddress: '' // 图片url
      };
      this.fileList = [];
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
      this.ids = selection.map(item => item.statusIconId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加设备状态";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const statusIconId = row.statusIconId || this.ids
      getStatusIcon(statusIconId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改设备状态";
        this.fileList = [{
            name: response.data.iconCode,
            url: response.data.imageAddress
        }]
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.statusIconId != null) {
            // 判断图片有没有做改变
            if(this.imgList[0]) {
                let imgData = new FormData();
                imgData.append('file', this.imgList[0].raw);
                upImg(imgData).then(res => {
                    let obj = {...this.form};
                    obj.imageAddress = res.url;
                    obj.deviceTypeId = this.$route.query.id;
                    // 上传图片成功后调用添加接口
                    updateStatusIcon(obj).then(response => {
                        this.msgSuccess("修改成功");
                        this.open = false;
                        this.getList();
                    });
                })
            }else {
                if(this.changeFlag) {
                    // 没有选择
                    this.$message({
                        type: 'error',
                        message: '请选择设备状态图片'
                    });   
                }else{
                    updateStatusIcon(this.form).then(response => {
                        this.msgSuccess("修改成功");
                        this.open = false;
                        this.getList();
                    });
                } 
            }
          } else {
                // 判断是否选择图片
                if(this.imgList[0]) {
                    let imgData = new FormData();
                    imgData.append('file', this.imgList[0].raw);
                    upImg(imgData).then(res => {
                        let obj = {...this.form};
                        obj.imageAddress = res.url;
                        obj.deviceTypeId = this.$route.query.id;
                        // 上传图片成功后调用添加接口
                        addStatusIcon(obj).then(response => {
                            this.msgSuccess("新增成功");
                            this.open = false;
                            this.getList();
                        });
                    })
                }else {
                    // 没有选择
                    this.$message({
                        type: 'error',
                        message: '请选择设备状态图片'
                    });    
                }
            }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const statusIconIds = row.statusIconId || this.ids;
      this.$confirm('是否确认删除基础数据设备编号为"' + statusIconIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delStatusIcon(statusIconIds);
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
          return exportStatusIcon(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
