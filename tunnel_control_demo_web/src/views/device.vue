<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
    </el-form>
    <el-select v-model="value" style="margin-bottom: 15px;" ref="select" placeholder="请选择" :loading="loadingSel">
		<el-option
            v-for="item in options"
            :key="item.tunnelId"
            :label="item.tunnelName"
            :value="item.tunnelId">
		</el-option>
	</el-select>
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['base:deviceType:add']"
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
          v-hasPermi="['base:deviceType:edit']"
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
          v-hasPermi="['base:deviceType:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['base:deviceType:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table height="664" v-loading="loading" :data="deviceTypeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="起始位置" align="center" prop="startPosition">
      </el-table-column>
      <el-table-column label="设备间距(米)" align="center" prop="deviceSpace">
      </el-table-column>
      <el-table-column label="设备名称" align="center" prop="dictLabel">
      </el-table-column>
      <el-table-column label="设备图片" align="center" prop="deviceTypeCode">
          <template slot-scope="scope">
              <img style="height: 100px" :src="scope.row.deviceImageAddress" alt="">
          </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="goto('deviceState', scope.row)"
          >{{scope.row.deviceId}}状态管理</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['base:deviceType:edit']"
          >修改</el-button>
          <br>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['base:deviceType:remove']"
          >删除</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-film"
            @click="goto('deviceEdit', scope.row)"
          >{{scope.row.deviceId}}设备管理</el-button>
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
        <el-form ref="form" :model="form" :rules="rules">
            <el-form-item label="设备名称" prop="deviceTypeCode">
                <el-select v-model="form.deviceTypeCode" style="margin-bottom: 15px;" ref="select" placeholder="请选择">
                    <el-option
                        v-for="item in deviceOptions"
                        :key="item.dictValue"
                        :label="item.dictLabel"
                        :value="item.dictValue">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="起始位置" prop="startPosition">
                <el-input v-model="form.startPosition"></el-input>
            </el-form-item>
            <el-form-item label="设备间距(米)" prop="deviceSpace">
                <el-input type="number" v-model="form.deviceSpace"></el-input>
            </el-form-item>
            <el-form-item label="设备图片" prop="deviceCode">
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
import { listData, upImg,listDeviceType, getDeviceType, delDeviceType, addDeviceType, updateDeviceType, exportDeviceType } from "@/api/deviceType";
import { listTunnel } from "@/api/tunnel";
export default {
  name: "DeviceType",
  components: {
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      loadingSel: true,
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
      deviceTypeList: [],
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
      form: {
          deviceTypeCode: '',
          deviceSpace: '',
          deviceImageAddress: '',
          startPosition: ''
      },
      // 表单校验
      rules: {
        deviceTypeCode: [
            { required: true, message: '请输入设备名称', trigger: 'change' }
        ],
        deviceSpace: [
            { required: true, message: '请输入设备间距', trigger: 'blur' },
            { min: 1, max: 15, message: '长度在 1 到 15 个字符', trigger: 'blur' }
        ],
        startPosition: [
            { required: true, message: '请输入起始位置', trigger: 'blur' },
            { min: 1, max: 15, message: '长度在 1 到 15 个字符', trigger: 'blur' }
        ]
      },
      select: {
        pageNum: 1,
        pageSize: 10,
      },
      device: {
            pageNum: 1,
            pageSize: 100,
            dictType: 'tunnel_001_device_type'         
      },
      options: [],
      value: '',
      noMore: false,
    //   图片列表
      imgList: [],
      changeFlag: false,
      deviceOptions: []
    };
  },
  mounted() {
    this.$refs.select.$refs.scrollbar.$refs.wrap.addEventListener('scroll',this.scolling)
  },
  methods: {
    // 文件状态改变时的钩子，添加文件、上传成功和上传失败时都会被调用
    getImg(file, fileList) {
        this.imgList = fileList;
        this.changeFlag = true;
    },
    // 文件选择超出限制
    maxWar() {
        this.$message({
            type: 'warning',
            message: '设备图片只能上传一张'
        });    
    },
    scolling(){
    let e = this.$refs.select.$refs.scrollbar.$refs.wrap
        if(this.noMore) return
        // 到底时触发 loadMore
        let loadMore = e.scrollHeight -  e.scrollTop <=  e.clientHeight
        if(loadMore){
            this.loadMore()
        }
    },
    loadMore(){
        if(this.loadingSel) return
        this.loadingSel = true
        this.select.pageNum++
        listTunnel(this.select)
        .then((res)=>{
            if(this.select.pageNum >= Math.ceil(res.total / this.select.pageSize)){
                // 获取到最后的值时，不再监听滚动条的动作，移除滚动事件
                this.$refs.select.$refs.scrollbar.$refs.wrap.removeEventListener('scroll',this.scolling())
                this.noMore = true
            }
            this.options = this.options.concat(res.rows)
            console.log(this.options);
        }).finally(()=> this.loadingSel = false)
    },
    /** 查询基础数据设备列表 */
    getList() {
        this.loading = true;
        let obj = {...this.queryParams};
        obj.tunnelId = this.value;
        listDeviceType(obj).then(response => {
            this.deviceTypeList = response.rows;
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
          deviceTypeCode: '',
          deviceSpace: '',
          deviceImageAddress: '',
          startPosition: ''
      }
      this.fileList = [];
    //   this.resetForm("form");
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
      this.ids = selection.map(item => item.deviceTypeId);
      this.single = selection.length!==1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加设备类型";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const deviceTypeId = row.deviceTypeId || this.ids
      getDeviceType(deviceTypeId).then(response => {
        this.form = response.data;
        this.fileList = [{
            name: '1',
            url: response.data.deviceImageAddress
        }]
        this.open = true;
        this.title = "修改设备类型";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
            if (this.form.deviceTypeId != null) {
                // 判断图片有没有做改变
                if(this.imgList[0]) {
                    let imgData = new FormData();
                    imgData.append('file', this.imgList[0].raw);
                    upImg(imgData).then(res => {
                        let obj = {...this.form};
                        obj.deviceImageAddress = res.url;
                        obj.tunnelId = this.value;
                        // 上传图片成功后调用添加接口
                        updateDeviceType(obj).then(response => {
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
                            message: '请选择设备图片'
                        });   
                    }else{
                        updateDeviceType(this.form).then(response => {
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
                        obj.deviceImageAddress = res.url;
                        obj.tunnelId = this.value;
                        // 上传图片成功后调用添加接口
                        addDeviceType(obj).then(response => {
                            this.msgSuccess("新增成功");
                            this.open = false;
                            this.getList();
                        });
                    })
                }else {
                    // 没有选择
                    this.$message({
                        type: 'error',
                        message: '请选择设备图片'
                    });    
                }
                
            }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const deviceTypeIds = row.deviceTypeId || this.ids;
      this.$confirm('是否确认删除基础数据设备编号为"' + deviceTypeIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delDeviceType(deviceTypeIds);
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
          return exportDeviceType(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    },
    goto(a, b) {
        // console.log(b);
        this.$router.push({
          name: a,
          query: {
              id: b.deviceTypeId
          }
        })
    },
    getAddList() {
        listData(
            {
                pageNum: 1,
                pageSize: 10,
                dictType: 'tunnel_device_type'          
            }
        ).then(res => {
            this.deviceOptions = res.rows;
            console.log(res);
        })
    }
  },
  created() {
        this.getAddList();
        listTunnel(this.select)
        .then(res => {
            this.loadingSel = false;
            this.options = res.rows;
            this.value = res.rows[0] ? +res.rows[0].tunnelId : null;
        })
  },
  watch: {
      'value': {
          handler(to, from) {
                this.getList();
          }
      }
  }
};
</script>