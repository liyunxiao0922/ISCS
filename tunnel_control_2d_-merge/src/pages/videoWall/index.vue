<template>
  <div class="video-wall-container">
    <div class="region">
      <el-input
        suffix-icon="el-icon-search"
        placeholder="输入关键字进行过滤"
        v-model="filterText">
      </el-input>
      <el-tree
        class="tree"
        :data="data"
        node-key="id"
        :indent="0"
        default-expand-all
        :filter-node-method="filterNode"
        ref="tree">
        <span class="custom-tree-node" slot-scope="{ node, data }">
          <span class="title">
            <i :class="data.icon"></i>{{ node.label }}<i></i>
          </span>
        </span>
      </el-tree>
    </div>
    <div class="video-container">
      <div v-for="item in videoData" :key="item" class="video-wrapper">
        <video muted width="99%" height="100%" autoplay="autoplay" loop :src="item" controls style="border:none"></video>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      filterText: '',
      data: [{
        id: 1,
        label: '监控中心',
        icon: 'el-icon-s-home',
        children: [{
          id: 2,
          label: '测试收费站',
          icon: 'el-icon-s-home',
          children: [{
            id: 3,
            label: '测试服务区',
            icon: 'el-icon-s-home'
          }, {
            id: 4,
            label: '测试隧道',
            icon: 'el-icon-location-outline',
            children: [{
              id: 5,
              label: '视频1',
              icon: 'el-icon-video-camera'
            }, {
              id: 6,
              label: '视频2',
              icon: 'el-icon-video-camera'
            }, {
              id: 7,
              label: '视频3',
              icon: 'el-icon-video-camera'
            }, {
              id: 8,
              label: '视频4',
              icon: 'el-icon-video-camera'
            }, {
              id: 9,
              label: '视频5',
              icon: 'el-icon-video-camera'
            }, {
              id: 10,
              label: '视频6',
              icon: 'el-icon-video-camera'
            }, {
              id: 11,
              label: '视频7',
              icon: 'el-icon-video-camera'
            }, {
              id: 12,
              label: '视频8',
              icon: 'el-icon-video-camera'
            }, {
              id: 13,
              label: '视频9',
              icon: 'el-icon-video-camera'
            }, {
              id: 14,
              label: '视频10',
              icon: 'el-icon-video-camera'
            }, {
              id: 15,
              label: '视频11',
              icon: 'el-icon-video-camera'
            }, {
              id: 16,
              label: '视频12',
              icon: 'el-icon-video-camera'
            }]
          }]
        }]
      }],
      defaultProps: {
        children: 'children',
        label: 'label'
      },
      videoData: [
        'http://121.40.128.159:24001/video/13.mp4',
        'http://121.40.128.159:24001/video/2.mp4',
        'http://121.40.128.159:24001/video/3.mp4',
        'http://121.40.128.159:24001/video/4.mp4',
        'http://121.40.128.159:24001/video/5.mp4',
        'http://121.40.128.159:24001/video/6.mp4',
        'http://121.40.128.159:24001/video/7.mp4',
        'http://121.40.128.159:24001/video/8.mp4',
        'http://121.40.128.159:24001/video/9.mp4',
        'http://121.40.128.159:24001/video/10.mp4',
        'http://121.40.128.159:24001/video/11.mp4',
        'http://121.40.128.159:24001/video/12.mp4',
        'http://121.40.128.159:24001/video/13.mp4',
        'http://121.40.128.159:24001/video/14.mp4',
        'http://121.40.128.159:24001/video/15.mp4',
        'http://121.40.128.159:24001/video/16.mp4',
        'http://121.40.128.159:24001/video/17.mp4',
        'http://121.40.128.159:24001/video/18.mp4',
        'http://121.40.128.159:24001/video/19.mp4',
        'http://121.40.128.159:24001/video/20.mp4'
      ]
    };
  },
  watch: {
    filterText (val) {
      this.$refs.tree.filter(val);
    }
  },

  methods: {
    filterNode (value, data) {
      if (!value) return true;
      return data.label.indexOf(value) !== -1;
    }
  }

};
</script>

<style lang="scss" scoped>
.region /deep/ .el-tree > .el-tree-node:after {
  border-top: none;
}
// .region .tree /deep/ .el-tree-node .is-expanded .is-current .is-focusable {
//   background: red;
// }
.tree {
  width: 100%;
  /deep/ .el-tree-node {
    position: relative;
    padding-left: 15px;
  }
  /deep/ .el-tree-node__children {
    padding-left: 15px;
  }
  /deep/ .el-tree-node :last-child:before {
    height: 38px;
    line-height: 38px;
  }
  /deep/ .el-tree > .el-tree-node:before {
    border-left: none;
  }
  /deep/ .el-tree > .el-tree-node:after {
    border-top: none;
  }
  /deep/ .el-tree-node:before {
    content: "";
    left: -8px;
    position: absolute;
    right: auto;
    border-width: 1px;
  }
  /deep/ .el-tree-node:after {
    content: "";
    left: -4px;
    position: absolute;
    right: auto;
    border-width: 1px;
  }
  /deep/ .el-tree-node:before {
    border-left: 1px dashed #4386c6;
    bottom: 0px;
    height: 100%;
    top: -26px;
    width: 1px;
  }
  /deep/ .el-tree-node:after {
    border-top: 1px dashed #4386c6;
    height: 20px;
    top: 12px;
    width: 24px;
  }
  /deep/ .el-tree-node__content {
    margin: 0 0;
    &:hover {
      background: #2c3e50;
    }
    /deep/ .is-current {
      background: #2c3e50;
    }
  }
  /deep/ .el-tree-node__expand-icon {
    margin-top: 2px;
    color: #409EFF;
  }
}
.video-wall-container {
  width: 100%;
  height: 100%;
  margin-top: 67.5px;
  display: flex;
  .region {
    margin-top: 10px;
    width: 225px;
    height: 100%;
    background: rgba(28,38,65,.9);
    /deep/ .el-tree {
      background: rgba(28,38,65,.9);
    }
    /deep/ .el-input__inner {
      background: rgba(28,38,65,.9);
      border: none;
      border-bottom: 1px solid #2e8ff9;
      height: 30px;
    }
    /deep/ .el-input__icon {
      line-height: 30px;
    }
    .custom-tree-node {
      flex: 1;
      display: inline-block;
      color: #1366BC;
      font-size: 13.5px;
      line-height: 28px;
      .title {
        display: inline-block;
        margin-top: 3.7px;
        i {
          margin-top: 2px;
          margin-left: -5px;
          margin-right: 5px;
        }
      }
    }
  }
  .video-container {
    width: calc(100% - 225px);
    .video-wrapper {
      cursor: pointer;
      box-sizing: content-box;
      display: inline-block;
      margin-left: 10px;
      margin-top: 10px;
      width: calc(20% - 15px);
      height: calc(25% - 40px);
      border: 1px solid rgb(46, 143, 249);
    }
  }
  video:focus{
    outline: -webkit-focus-ring-color auto 0px;  // 去除video获得焦点的边框
  }
}
</style>
