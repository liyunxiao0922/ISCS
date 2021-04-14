<template>
  <div class="action">
    <div class="actionBox" style="float: left; margin-left: 9px">
      <el-form :model="formInline">
        <el-form-item>
          <el-select
            v-model="formInline.tunnelId"
            placeholder="羊山隧道"
            style="width: 100px"
            size="mini"
            popper-class="select"
            @change="changeTunnel"
          >
            <el-option
              v-for="item in tunnelList"
              :label="item.tunnelName"
              :value="item.tunnelId"
              :key="item.tunnelId"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
    </div>
    <!-- @click="scaleToggle" -->
    <!-- <el-button class="emergencyLinkage"  type="info" plain>触发异常</el-button>
    <el-button class="emergencyLinkage"  type="info" plain>紧急联动</el-button> -->
    <el-popover
      popper-class="pop-div-my"
      placement="bottom"
      width="564"
      :visible-arrow="false"
      trigger="manual"
      v-model="visible"
    >
      <div class="arrow"></div>
      <div style="font-size: 16px; color: #fff;">异常事件列表
        <span style="cursor: pointer; float: right;" @click="visible = false"><i class="el-icon-close"></i></span>
      </div>
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="待确认" :name="'0'"></el-tab-pane>
        <el-tab-pane label="已确认" :name="'1'"></el-tab-pane>
        <el-tab-pane label="误报" :name="'2'"></el-tab-pane>
      </el-tabs>
      <el-table
        ref="singleTable"
        :data="data"
        highlight-current-row
        @row-click="handleCurrentChange"
        :style="`width: 100%; ${activeName == '0' ? 'color: rgb(255, 34, 34);' : ''}`"
        max-height="128"
      >
      <!-- tunnelArea: ['一区', '二区', '三区', '四区', '五区', '六区', '七区', '八区', '九区', '十区'],
      tunnelDirection: ['上行', '下行'],
      eventType: ['火灾', '泥石流', '交通'],
      trafficLane: ['无车道', '行车道', '快车道'],
      eventSource: ['手动事件', '自动事件'] -->
        <el-table-column width="130" property="planName" label="位置">
          <template slot-scope="scope">
            {{ tunnelDirection[scope.row.tunnelDirection - 1] + tunnelArea[scope.row.tunnelArea - 1] + trafficLane[scope.row.trafficLane] }}
          </template>
        </el-table-column>
        <el-table-column width="80" property="eventTypeName" label="类型">
          <!-- <template slot-scope="scope">
            {{ eventType[scope.row.eventType] }}
          </template> -->
        </el-table-column>
        <el-table-column width="80" property="pile" label="桩号">
          <!-- <template slot-scope="scope">
            {{ eventType[scope.row.eventType] }}
          </template> -->
        </el-table-column>
        <el-table-column width="90" property="eventSourceName" label="事件来源">
          <!-- <template slot-scope="scope">
            {{ eventSource[scope.row.eventSource] }}
          </template> -->
        </el-table-column>
        <el-table-column width="160" property="createTime" label="时间"> </el-table-column>
      </el-table>
        <!-- @click="warningClick()" -->
      <div
        :class="`switchingTunnels ${warningflag ? 'emergencyLinkage' : ''}`"
        style="margin-left: 9px"
        @click="visible = true"
        slot="reference"
      >
        <div class="switchingTunnelsIcon">
          <!-- <span :class="`${warningflag ? 'flashFont' : ''}`" style="widht: 24px; height: 18px; font-size: 16px">{{tableData[0] && tableData[0].length}}</span> -->
          <i style="font-size: 14px; margin-top: 2px;" class="el-icon-bell"></i>
        </div>
        <div :class="`${warningflag ? 'flashFont messageTips' : 'messageTips'}`"><span style="margin-left: -2px;">{{tableData[0] && tableData[0].length}}</span></div>
        <div class="transform">紧急联动</div>
      </div> </el-popover
    >
    <div class="switchingTunnels" style="margin-left: 9px" @click="blinklink">
      <div class="switchingTunnelsIcon">
        <svg
          t="1617023291233"
          class="icon"
          viewBox="0 0 1024 1024"
          version="1.1"
          xmlns="http://www.w3.org/2000/svg"
          p-id="1263"
          width="24"
          height="18"
        >
          <path
            d="M739.475397 914.697813h-312.445457c-35.729688 0-69.154895-19.156254-87.189607-49.912683L195.799017 619.145061c-25.954063-44.29679-16.656322-99.996259 21.938617-133.534029l3.201919-3.060702c37.552193-30.928345 95.473254-29.803733 131.764736 1.122564v-272.771955c0-56.00953 45.559548-101.598753 101.5701-101.598752 27.471623 0 52.989759 10.674086 72.147036 29.971555 19.241188 19.436639 29.69117 45.25051 29.436368 72.637199l-1.600448 165.416093 210.275701 14.521709c45.027429 4.465699 78.310397 41.402886 78.310396 85.89615l-2.105959 335.943591c-0.364296 55.673886-45.784674 101.00933-101.262086 101.009329zM259.141544 525.693856l-2.613519 2.414998c-18.509526 15.252348-23.172723 41.319998-11.066011 61.934461l144.01164 245.640068c7.894792 13.45338 21.909965 21.460735 37.556286 21.460735h312.445457c23.96169 0 43.594804-19.63516 43.734996-43.791278l2.10903-335.749163c0-14.689531-11.012799-26.991694-25.647072-28.453995l-209.515386-14.465427c-30.223288-2.078331-53.720398-27.500276-53.441036-57.921062l1.600448-165.41507c0.112563-11.937866-4.409418-23.145093-12.780046-31.54335-8.257041-8.342999-19.269841-12.949914-30.952904-12.949914-24.605348 0-44.353072 19.7467-44.353072 44.045057v450.4063l-87.834288-126.400576c-14.015174-19.295423-43.564104-24.098813-63.254523-9.211784z"
            p-id="1264"
          ></path>
        </svg>
      </div>
      <div class="transform">触发异常</div>
    </div>
    <div
      class="switchingTunnels"
      style="margin-left: 9px"
      @click="show = !show"
    >
      <div class="switchingTunnelsIcon">
        <svg
          t="1616757588061"
          class="icon"
          viewBox="0 0 1024 1024"
          version="1.1"
          p-id="2773"
          width="24"
          height="18"
        >
          <path
            d="M439.488 128c172 0 311.456 139.296 311.456 311.104a309.44 309.44 0 0 1-55.84 177.824L896 820 851.936 864l-197.536-199.68a310.624 310.624 0 0 1-214.912 85.92C267.456 750.24 128 610.944 128 439.104 128 267.264 267.456 128 439.488 128z m0 62.208c-137.632 0-249.184 111.456-249.184 248.896 0 137.472 111.552 248.896 249.184 248.896 137.6 0 249.152-111.424 249.152-248.896 0-137.44-111.552-248.896-249.152-248.896z"
            p-id="2774"
          ></path>
        </svg>
      </div>
      <div class="transform">设备搜索</div>
    </div>
    <!-- 搜索框 -->

    <transition name="el-fade-in">
      <div v-show="show" class="searchWrap">
        <el-input
          v-model="input"
          type="number"
          placeholder="请输入设备编号"
        ></el-input>
        <el-button type="info" @click="search">点击搜索</el-button>
      </div>
    </transition>
    <!-- <span class="searchBox "
        style="margin-left:9px;"
        @click="isShowSearch = !isShowSearch">
      <el-button
        type="text"
        class="btnCommon switchingTunnels"
      >
        <div class="switchingTunnelsIcon search el-icon-search"></div>

        <div class="transform">搜索</div>
      </el-button>
      <div class="searchContext" v-if="isShowSearch">
        <el-form
          :model="searchFrom"
          status-icon
          ref="ruleForm"
          label-width="90px"
          class="demo-ruleForm"
        >
          <el-form-item label="设备类型：" prop="devType">
            <el-select
              v-model="searchFrom.devType"
              placeholder="请选择"
              :popper-append-to-body="false"
              popper-class="select-down"
              style="width: 100%"
            >
              <el-option
                v-for="item in devTypeList"
                :key="item.dictValue"
                style="color: #b2faff;"
                :label="item.dictLabel"
                :value="item.dictValue"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="设备编号：" prop="devCode">
            <el-input
              type="text"
              v-model="searchFrom.devCode"
              autocomplete="off"
            ></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm">搜索</el-button>
            <el-button type="info" @click="closeSearchBox">取消</el-button>
          </el-form-item>
        </el-form>
      </div>
    </span> -->
    <div class="switchingTunnels" @click="scaleToggle" style="margin-left: 9px">
      <div class="switchingTunnelsIcon">
        <svg
          v-if="scaleFlag"
          t="1616757620962"
          class="icon"
          viewBox="0 0 1024 1024"
          version="1.1"
          p-id="2912"
          width="24"
          height="18"
        >
          <path
            d="M512 224c184.736 0 340 122.368 384 288-44 165.632-199.264 288-384 288S172 677.632 128 512c44-165.632 199.264-288 384-288z m0 64c-147.008 0-274.464 92.544-317.28 224 42.816 131.456 170.24 224 317.28 224 147.008 0 274.464-92.544 317.28-224-42.816-131.456-170.24-224-317.28-224z m0 64a160 160 0 1 1 0 320 160 160 0 0 1 0-320z m0 64a96 96 0 1 0 0 192 96 96 0 0 0 0-192z"
            p-id="2913"
          ></path>
        </svg>
        <svg
          v-else
          t="1616757672625"
          class="icon"
          viewBox="0 0 1024 1024"
          version="1.1"
          p-id="3051"
          width="24"
          height="18"
        >
          <path
            d="M512 224c86.784 0 167.072 27.008 232.384 72.832l80.576-60.736 38.528 51.104-68.608 51.712A380.576 380.576 0 0 1 896 512c-44 165.632-199.264 288-384 288-86.784 0-167.072-27.008-232.384-72.832L199.04 787.904l-38.528-51.104 68.608-51.712A380.576 380.576 0 0 1 128 512c44-165.632 199.264-288 384-288z m144.512 219.2a160 160 0 0 1-250.432 188.704l-72.128 54.368A341.12 341.12 0 0 0 512 736c147.008 0 274.464-92.544 317.28-224-15.68-48.128-45.472-93.536-86.144-134.08l-86.624 65.28zM512 288c-147.008 0-274.464 92.544-317.28 224a317.504 317.504 0 0 0 86.144 134.08l86.624-65.28a160 160 0 0 1 250.432-188.704l72.128-54.368A341.12 341.12 0 0 0 512 288z m-53.024 304.032A96 96 0 0 0 608 512c0-7.552-2.016-20.672-4.448-28.928L458.976 592zM512 416a96 96 0 0 0-91.552 124.928l144.576-108.96A95.552 95.552 0 0 0 512 416z"
            p-id="3052"
          ></path>
        </svg>
      </div>
      <div class="transform" v-if="scaleFlag">隐藏刻度</div>
      <div class="transform" v-else>显示刻度</div>
    </div>
    <div
      class="switchingTunnels"
      @click="collapseClickOpen"
      style="margin-left: 9px"
    >
      <div class="switchingTunnelsIcon">
        <svg
          v-if="isCollapse"
          t="1616757620962"
          class="icon"
          viewBox="0 0 1024 1024"
          version="1.1"
          p-id="2912"
          width="24"
          height="18"
        >
          <path
            d="M512 224c184.736 0 340 122.368 384 288-44 165.632-199.264 288-384 288S172 677.632 128 512c44-165.632 199.264-288 384-288z m0 64c-147.008 0-274.464 92.544-317.28 224 42.816 131.456 170.24 224 317.28 224 147.008 0 274.464-92.544 317.28-224-42.816-131.456-170.24-224-317.28-224z m0 64a160 160 0 1 1 0 320 160 160 0 0 1 0-320z m0 64a96 96 0 1 0 0 192 96 96 0 0 0 0-192z"
            p-id="2913"
          ></path>
        </svg>
        <svg
          v-else
          t="1616757672625"
          class="icon"
          viewBox="0 0 1024 1024"
          version="1.1"
          p-id="3051"
          width="24"
          height="18"
        >
          <path
            d="M512 224c86.784 0 167.072 27.008 232.384 72.832l80.576-60.736 38.528 51.104-68.608 51.712A380.576 380.576 0 0 1 896 512c-44 165.632-199.264 288-384 288-86.784 0-167.072-27.008-232.384-72.832L199.04 787.904l-38.528-51.104 68.608-51.712A380.576 380.576 0 0 1 128 512c44-165.632 199.264-288 384-288z m144.512 219.2a160 160 0 0 1-250.432 188.704l-72.128 54.368A341.12 341.12 0 0 0 512 736c147.008 0 274.464-92.544 317.28-224-15.68-48.128-45.472-93.536-86.144-134.08l-86.624 65.28zM512 288c-147.008 0-274.464 92.544-317.28 224a317.504 317.504 0 0 0 86.144 134.08l86.624-65.28a160 160 0 0 1 250.432-188.704l72.128-54.368A341.12 341.12 0 0 0 512 288z m-53.024 304.032A96 96 0 0 0 608 512c0-7.552-2.016-20.672-4.448-28.928L458.976 592zM512 416a96 96 0 0 0-91.552 124.928l144.576-108.96A95.552 95.552 0 0 0 512 416z"
            p-id="3052"
          ></path>
        </svg>
      </div>
      <div class="transform" v-if="isCollapse">隐藏面板</div>
      <div class="transform" v-else>显示面板</div>
    </div>
    <!-- <el-button
      type="text"
      style="float:right;margin-right:25px;z-index:99999;"
    >
      <i
        class="el-icon-phone"
      ></i>
      <span>报警</span>
    </el-button> -->
      <!-- href="http://tunnel-three.xaxhl.net/" -->
    <el-link
      class="switchingTunnelsLink"
      :underline="false"
      target="_self"
      @click="$parent.open3d()"
    >
      <div class="switchingTunnels">
        <div class="switchingTunnelsIcon">
          <svg
            t="1616757763942"
            class="icon"
            viewBox="0 0 1024 1024"
            version="1.1"
            p-id="2751"
            width="24"
            height="18"
          >
            <path
              d="M303.45 658.71c-8.84 0-16 7.16-16 16s7.16 16 16 16h160.54c8.84 0 16-7.16 16-16 0-0.05-0.01-0.1-0.01-0.15 0-0.05 0.01-0.1 0.01-0.15V357.12c0-0.62-0.04-1.22-0.11-1.82 0.07-0.6 0.11-1.2 0.11-1.82 0-8.84-7.16-16-16-16H303.45c-8.84 0-16 7.16-16 16s7.16 16 16 16h144.54V498.1H303.45c-8.84 0-16 7.16-16 16s7.16 16 16 16h144.54v128.62H303.45zM623.66 337.51c-0.15 0-0.29-0.02-0.43-0.02h-97.84c-0.42 0-0.84 0.03-1.25 0.06-0.41-0.03-0.83-0.06-1.25-0.06-8.84 0-16 7.16-16 16v317.28c0 8.84 7.16 16 16 16 0.42 0 0.84-0.03 1.25-0.06 0.41 0.03 0.83 0.06 1.25 0.06h97.84c0.15 0 0.29-0.02 0.43-0.02 32.18-0.46 61.87-19.53 83.71-53.85 20.69-32.5 32.08-75.39 32.08-120.77 0-45.38-11.39-88.27-32.08-120.77-21.85-34.32-51.53-53.39-83.71-53.85z m56.71 278.2c-16.03 25.19-36.6 39.06-57.91 39.06h-83.58V369.48h83.58c21.31 0 41.87 13.87 57.91 39.06 17.46 27.43 27.07 64.21 27.07 103.58s-9.61 76.16-27.07 103.59z"
              fill="#fff"
              p-id="2752"
            ></path>
            <path
              d="M534.64 935.69c0.19-0.23 0.36-0.46 0.55-0.69 0.14-0.18 0.28-0.35 0.41-0.53 0.2-0.26 0.38-0.53 0.57-0.8 0.11-0.16 0.22-0.31 0.33-0.47 0.19-0.28 0.36-0.56 0.54-0.84l0.3-0.48c0.17-0.28 0.33-0.56 0.49-0.85 0.1-0.17 0.2-0.35 0.29-0.52l0.42-0.81 0.3-0.6c0.12-0.25 0.23-0.5 0.34-0.75 0.1-0.23 0.21-0.47 0.31-0.71 0.09-0.22 0.18-0.45 0.26-0.67 0.1-0.27 0.21-0.54 0.31-0.82 0.07-0.19 0.13-0.39 0.2-0.59 0.1-0.3 0.2-0.61 0.3-0.92l0.15-0.52c0.09-0.33 0.19-0.66 0.27-1 0.04-0.16 0.07-0.32 0.11-0.49 0.08-0.35 0.16-0.69 0.23-1.04l0.09-0.49 0.18-1.04c0.03-0.19 0.05-0.38 0.07-0.56 0.04-0.32 0.09-0.65 0.12-0.98l0.06-0.75 0.06-0.81c0.02-0.47 0.03-0.94 0.04-1.41v-0.17-0.17c0-0.47-0.01-0.94-0.04-1.41-0.01-0.27-0.04-0.54-0.06-0.81-0.02-0.25-0.03-0.5-0.06-0.75-0.03-0.33-0.08-0.65-0.12-0.98-0.02-0.19-0.04-0.38-0.07-0.56-0.05-0.35-0.11-0.7-0.18-1.04l-0.09-0.49c-0.07-0.35-0.15-0.69-0.23-1.04-0.04-0.16-0.07-0.32-0.11-0.49-0.08-0.33-0.18-0.67-0.27-1l-0.15-0.52c-0.09-0.31-0.19-0.61-0.3-0.92-0.07-0.2-0.13-0.39-0.2-0.59-0.1-0.27-0.2-0.54-0.31-0.82-0.09-0.22-0.17-0.45-0.26-0.67-0.1-0.24-0.2-0.47-0.31-0.71-0.11-0.25-0.22-0.5-0.34-0.75l-0.3-0.6c-0.14-0.27-0.27-0.54-0.42-0.81-0.09-0.18-0.19-0.35-0.29-0.52-0.16-0.28-0.32-0.57-0.49-0.85l-0.3-0.48c-0.18-0.28-0.35-0.56-0.54-0.84-0.11-0.16-0.22-0.32-0.33-0.47-0.19-0.27-0.37-0.53-0.57-0.8-0.13-0.18-0.27-0.35-0.41-0.53-0.18-0.23-0.36-0.47-0.55-0.69-0.2-0.24-0.4-0.47-0.6-0.7-0.14-0.16-0.27-0.32-0.42-0.48-0.36-0.4-0.73-0.79-1.11-1.17L442 802.24c-12.5-12.5-32.76-12.5-45.25 0-12.5 12.5-12.5 32.76 0 45.25l25.46 25.46c-65.12-15.79-124.86-49.14-173.44-97.73C179.02 705.48 140.6 612.74 140.6 514.1c0-17.67-14.33-32-32-32s-32 14.33-32 32c0 58.48 11.46 115.23 34.06 168.67 21.83 51.6 53.06 97.94 92.85 137.72s86.12 71.02 137.72 92.85c53.44 22.6 110.19 34.06 168.67 34.06 0.53 0 1.06-0.01 1.58-0.04 0.25-0.01 0.5-0.04 0.75-0.05l0.81-0.06c0.31-0.03 0.62-0.07 0.93-0.11 0.2-0.03 0.41-0.05 0.61-0.08 0.34-0.05 0.67-0.11 1-0.17l0.53-0.09c0.34-0.07 0.68-0.14 1.01-0.22l0.51-0.12c0.33-0.08 0.65-0.17 0.97-0.26 0.18-0.05 0.37-0.1 0.55-0.16 0.3-0.09 0.6-0.19 0.89-0.29 0.21-0.07 0.41-0.14 0.61-0.21 0.27-0.09 0.53-0.2 0.79-0.3 0.23-0.09 0.46-0.18 0.7-0.27 0.23-0.09 0.45-0.2 0.68-0.3 0.26-0.11 0.52-0.23 0.77-0.35 0.2-0.09 0.39-0.19 0.58-0.29 0.28-0.14 0.56-0.28 0.83-0.43 0.17-0.09 0.33-0.19 0.5-0.28 0.29-0.16 0.58-0.33 0.87-0.5 0.15-0.09 0.3-0.19 0.46-0.29 0.29-0.18 0.58-0.36 0.86-0.55 0.15-0.1 0.3-0.21 0.46-0.32 0.27-0.19 0.55-0.38 0.81-0.58 0.17-0.13 0.34-0.27 0.51-0.4 0.24-0.18 0.48-0.37 0.71-0.56 0.23-0.19 0.46-0.39 0.69-0.59 0.17-0.14 0.33-0.28 0.5-0.43 0.79-0.71 1.54-1.46 2.25-2.25l0.42-0.48c0.21-0.21 0.41-0.44 0.6-0.68zM909.14 345.43c-21.83-51.6-53.06-97.94-92.85-137.72s-86.12-71.02-137.72-92.85C625.13 92.26 568.39 80.8 509.91 80.8c-0.53 0-1.07 0.01-1.6 0.04-0.23 0.01-0.46 0.03-0.69 0.05-0.29 0.02-0.58 0.04-0.87 0.07-0.3 0.03-0.6 0.07-0.9 0.11-0.21 0.03-0.43 0.05-0.64 0.08-0.33 0.05-0.65 0.11-0.98 0.17-0.18 0.03-0.37 0.06-0.55 0.1-0.33 0.07-0.66 0.14-0.99 0.22l-0.54 0.12c-0.32 0.08-0.63 0.17-0.95 0.26-0.19 0.05-0.38 0.1-0.57 0.16-0.29 0.09-0.59 0.19-0.88 0.28l-0.63 0.21c-0.26 0.09-0.52 0.19-0.78 0.29-0.24 0.09-0.47 0.18-0.71 0.28-0.23 0.09-0.45 0.19-0.67 0.29-0.26 0.12-0.52 0.23-0.78 0.35-0.19 0.09-0.38 0.19-0.57 0.28-0.28 0.14-0.56 0.28-0.84 0.43-0.17 0.09-0.33 0.18-0.49 0.27-0.29 0.16-0.59 0.33-0.88 0.5-0.15 0.09-0.3 0.19-0.45 0.28-0.29 0.18-0.58 0.37-0.87 0.56-0.15 0.1-0.3 0.21-0.44 0.31-0.28 0.19-0.55 0.38-0.83 0.59-0.17 0.12-0.33 0.26-0.5 0.38-0.24 0.19-0.49 0.38-0.73 0.57-0.22 0.18-0.43 0.37-0.65 0.56-0.18 0.15-0.36 0.3-0.53 0.46-0.79 0.71-1.53 1.46-2.25 2.25-0.15 0.17-0.29 0.34-0.44 0.51-0.19 0.22-0.39 0.45-0.58 0.68-0.19 0.23-0.37 0.47-0.55 0.7l-0.4 0.52c-0.2 0.26-0.38 0.53-0.57 0.8-0.11 0.16-0.22 0.31-0.33 0.47-0.19 0.28-0.36 0.56-0.54 0.84l-0.3 0.48c-0.17 0.28-0.33 0.56-0.49 0.84-0.1 0.17-0.2 0.35-0.29 0.52-0.14 0.27-0.28 0.54-0.41 0.81-0.1 0.2-0.2 0.4-0.3 0.61-0.12 0.25-0.23 0.49-0.33 0.74-0.1 0.24-0.21 0.47-0.31 0.71-0.09 0.22-0.18 0.44-0.26 0.66-0.11 0.27-0.21 0.55-0.31 0.82-0.07 0.19-0.13 0.39-0.2 0.58-0.1 0.31-0.21 0.61-0.3 0.93l-0.15 0.51c-0.09 0.33-0.19 0.67-0.27 1-0.04 0.16-0.07 0.32-0.11 0.48-0.08 0.35-0.16 0.69-0.23 1.05l-0.09 0.48c-0.06 0.35-0.13 0.7-0.18 1.05-0.03 0.19-0.05 0.37-0.07 0.56-0.04 0.33-0.09 0.65-0.12 0.98l-0.06 0.75-0.06 0.81c-0.02 0.47-0.03 0.93-0.04 1.4v0.34c0 0.47 0.01 0.94 0.04 1.41 0.01 0.27 0.04 0.54 0.06 0.81 0.02 0.25 0.03 0.5 0.06 0.75 0.03 0.33 0.08 0.65 0.12 0.97 0.02 0.19 0.04 0.38 0.07 0.57 0.05 0.35 0.11 0.69 0.18 1.04l0.09 0.49c0.07 0.35 0.15 0.69 0.23 1.04 0.04 0.16 0.07 0.33 0.11 0.49 0.08 0.33 0.18 0.67 0.27 1l0.15 0.52c0.09 0.31 0.19 0.61 0.3 0.92 0.07 0.2 0.13 0.39 0.2 0.59 0.1 0.27 0.2 0.54 0.31 0.81 0.09 0.22 0.17 0.45 0.27 0.67 0.1 0.24 0.2 0.47 0.31 0.7 0.11 0.25 0.22 0.5 0.34 0.75l0.3 0.6c0.14 0.27 0.27 0.55 0.42 0.82 0.09 0.17 0.19 0.34 0.29 0.52 0.16 0.29 0.32 0.57 0.49 0.85 0.1 0.16 0.2 0.31 0.29 0.47 0.18 0.28 0.36 0.57 0.54 0.85 0.1 0.16 0.22 0.31 0.32 0.46l0.57 0.81c0.13 0.17 0.26 0.34 0.4 0.51 0.18 0.24 0.37 0.48 0.56 0.71 0.19 0.23 0.38 0.45 0.57 0.67 0.15 0.17 0.29 0.35 0.45 0.51 0.36 0.4 0.73 0.78 1.1 1.16l90.52 90.52c6.25 6.25 14.44 9.37 22.63 9.37s16.38-3.12 22.63-9.37c12.5-12.5 12.5-32.76 0-45.25l-25.46-25.46c65.12 15.79 124.86 49.14 173.44 97.73 69.76 69.76 108.17 162.5 108.17 261.14 0 17.67 14.33 32 32 32s32-14.33 32-32c0-58.48-11.46-115.23-34.06-168.67z"
              fill="#fff"
              p-id="2753"
            ></path>
          </svg>
        </div>
        <div class="transform">切换隧道</div>
      </div>
    </el-link>
  </div>
</template>
<script>
import { eventList, updateEventAutoState } from "@/tunnel/api/tunnel";
export default {
  data () {
    return {
      formInline: {
        tunnelId: null // 选中的隧道id
      },
      timer: null,
      searchFrom: {
        devType: "",
        devCode: ""
      }, // 搜索条件
      isShowSearch: false, // 是否显示搜索框
      input: "",
      show: false,
      tableData: [],
      data: [],
      activeName: "0",
      visible: false,
      countCase: 0,
      tunnelArea: ['一区', '二区', '三区', '四区', '五区', '六区', '七区', '八区', '九区', '十区'],
      tunnelDirection: ['上行', '下行'],
      eventType: ['火灾', '泥石流', '交通'],
      trafficLane: ['无车道', '行车道', '快车道'],
      eventSource: ['手动事件', '自动事件'],
      must: []
    };
  },
  props: {
    tunnelList: {
      type: Array,
      default: []
    },
    devTypeList: {
      type: Array,
      default: []
    },
    isCollapse: {
      type: Boolean,
      default: true
    },
    scaleFlag: {
      type: Boolean,
      default: false
    },
    warningflag: {
      type: Boolean,
      default: false
    }
  },
  watch: {
    tunnelList: {
      immediate: true,
      deep: true,
      handler (newValue = []) {
        if (newValue.length) {
          if (!localStorage.getItem('tunnelId')) {
            this.formInline.tunnelId = +newValue[0].tunnelId;
          } else {
            this.formInline.tunnelId = +localStorage.getItem('tunnelId');
          }
        } else {
          this.formInline.tunnelId = null;
        }
      }
    },
    'visible': {
      handler (to) {
        if (to == false) {
        }
      }
    }
  },
  methods: {
    /**
     * 设备信息弹出框的显示与关闭
     */
    collapseClickOpen () {
      this.$emit("collapseClickOpen");
    },
    /**
     * 把选中的隧道id传出去
     * @param value 隧道id
     */
    changeTunnel (value) {
      this.tunnelList.forEach(v => {
        if (value == v.tunnelId) {
          this.$emit("changeTunnel", v);
        }
      });
    },
    // 切换刻度状态
    scaleToggle () {
      this.$parent.scaleToggle();
    },
    // 隐藏搜索框
    closeSearchBox () {
      this.isShowSearch = false;
      this.searchFrom = {
        devType: "",
        devCode: ""
      };
    },
    blinklink () {
      this.$emit("warningflagClick", this.must);
    },
    stoptimer () {
      clearTimeout(this.timer);
    },
    warningClick () {
      this.$emit("warningModuleClick");
      // this.$emit("warningflagClick");
    },
    // 提交搜索条件
    submitForm () {
      if (
        this.searchFrom &&
        this.searchFrom.devType &&
        this.searchFrom.devCode
      ) {
        this.$emit("searchDevPosition", this.searchFrom);
        this.closeSearchBox();
      } else {
        this.$message.info("请选择要查看的设备数据");
      }
    },
    // 立即搜索按钮点击事件
    search () {
      this.$parent.searchDevPosition(this.input);
    },
    handleCurrentChange (item) {
      this.$store.commit('setForm', item);
      this.warningClick();
    },
    handleClick () {
      // this.data = this.tableData[]
      this.data = this.tableData[this.activeName];
    },
    dataClassify (arr = []) {
      return [
        arr.filter((item) => item.eventState == 1),
        arr.filter((item) => item.eventState == 2),
        arr.filter((item) => item.eventState == 3)
      ];
    },
    dataInit () {
      eventList().then((res) => {
        this.must = res.data;
        if (this.$store.getters.getEventForm.eventId) {
          this.$store.commit('setForm', res.data.filter((item) => item.eventId == this.$store.getters.getEventForm.eventId)[0]);
        }
        this.tableData = this.dataClassify(res.data);
        // if(this.tableData[0][0]) {
        this.$store.commit('setTips', this.tableData[0][0] || this.must[0]);
        // }else {
        //   updateEventAutoState(0)
        // }
        this.data = this.tableData[this.activeName];
      });
    }
    /* auto() {
      updateEventAutoState()
      .then(res =>　{

      })
    } */
  },
  created () {
    /*
        eventState 1未确认 2已确认 3已排除
        planPeriod 步骤 1 2 3
        tunnelDirection 方向 1 2
        tunnelArea 区域 1
        trafficLane 1行车道2超车道0
      */
    // console.log(this.dataClassify(res.data));
    // console.log(res.data);
    this.dataInit();
    // this.countCase = this.tableData[0].length;
  }
};
</script>
<style scoped>
.actionBox /deep/ .el-input__inner {
  background-color: #191d21;
  border-color: #19292e;
  box-shadow: inset 0px 0px 15px 1px #404a55;
  border-radius: 0;
  color: #fff;
  width: 100%;
}
.demo-ruleForm /deep/ .el-form-item__label {
  color: #fff;
}
/deep/ .el-popper[x-placement^=bottom] .popper__arrow, .el-popper[x-placement^=bottom] .popper__arrow:after{
 border-bottom-color:#191d21;
}
/deep/ .el-select-dropdown__item.hover,
/deep/ .el-select-dropdown__item:hover {
  background-color: #545454;
}
/deep/ .el-input__inner {
  background: #23282d;
  opacity: 0.75;
  width: 95%;
  margin: 0 auto;
  width: 190px;
  font-size: 14px;
  height: 30px;
  border: none;
}
/deep/ .el-button--info {
  width: 190px;
  background-color: #2f363d;
  border-radius: 2px;
  border: 0;
  padding: 8px 22px;
}
/deep/ input::-webkit-outer-spin-button,
/deep/ input::-webkit-inner-spin-button {
  -webkit-appearance: none !important;
}

/* /deep/ .has-gutter tr{
  background-color:#191d21 ;
} */
/* /deep/ .el-table--scrollable-x .el-table__body-wrapper{
  background-color:#191d21 ;
} */
/* /deep/ .el-table th,
/deep/ .el-table tr {
  background-color: #191d21;
  cursor: pointer;
} */
/* .actionBox /deep/ .el-table td,.actionBox /deep/ .el-table th.is-leaf{
  background-color:#191d21;
  border: 0;
}*/
/deep/ .el-table__empty-block {
  background-color: #191d21;
}
/* /deep/ .popper__arrow {
  display: none !important;
} */

/deep/ .el-tabs__item {
  color: #9f9f9f;
}
/deep/ .el-tabs__item:hover,
/deep/ .el-tabs__item.is-active {
  color: #fff;
}
/deep/ .el-tabs__nav-wrap::after {
  background: #191d21;
}
/deep/ .el-tabs__active-bar {
  background: #fff;
}
/deep/ .el-tabs__header {
  margin: 0;
}
.el-table--fit {
 background: #191d21;
}
/deep/.el-table__body tr.current-row>td {
  background: #1f2328;
}
/deep/ .el-table--scrollable-y .el-table__body-wrapper{
  background: #1f2328;
}
 .el-table /deep/ .cell {
  padding-right: 9px;
}
</style>
<style lang="scss" scoped>
.action {
  position: absolute;
  right: 20px;
  top: 5px;
  z-index:2000;

  .switchingTunnels{
    cursor: pointer;
    float: left;
    position: relative;
    font-size: 10px;
    width: 62px;
    border-radius: 5px;
    margin-left: 9px;
    background-color: #323537;
    padding: 5px 6px 4px 6px;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    text-align: center;
    height: 40px;
    .transform {
      color: #fff;
      font-weight: 0;
    }
    .switchingTunnelsIcon {
      width: 18px;
      height: 14px;
      color: #fff;
      fill: #fff;
      margin: 0 auto;
      margin-bottom: 4px;
    }
  }
  .emergencyLinkage {
    box-sizing: border-box;
    /* animation: boxShadow 500ms infinite;
    -webkit-animation: boxShadow 500ms infinite; */
    color: rgba(255, 34, 34, 1);
    .switchingTunnelsIcon {
      // color: rgba(255, 34, 34, 1);
    }
    .transform {
      // color: rgba(255, 34, 34, 1);
    }
  }
  .flashFont {
    animation: boxShadow 1s infinite;
    // transition: .3s;
  }
}
.actionBox {
  padding-left: 9px;
  z-index: 9999;
  .el-form-item {
    margin-bottom: 0;
  }
}
.btnCommon {
  color: #fff;
}
.searchBox {
  position: relative;
  float: left;
  .searchContext {
    position: absolute;
    top: 50px;
    right: 5px;
    width: 400px;
    padding: 20px 20px 0 20px;
    background-color: #1a233e;
    border-radius: 5px;
    z-index: 999;
    &::after {
      content: "";
      position: absolute;
      right: 20px;
      top: -8px;
      width: 0;
      height: 0;
      border-bottom: 8px solid #1a233e;
      border-right: 8px solid transparent;
      border-left: 8px solid transparent;
    }
  }
}
.searchWrap {
  width: 200px;
  height: 90px;
  background: #1f2327;
  position: absolute;
  right: 117px;
  top: 45px;
  border-radius: 3px;
  text-align: center;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-evenly; /* 水平均匀分布 */
  align-items: center; /* 垂直居中 */
  flex-direction: column;
  /* >* {
    margin: 3px auto;
  } */
}
.arrow {
  position: absolute;
  left: 461px;
  top: -10px;
  content: "";
  z-index: 3000;
  height: 0;
  width: 0;
  border-bottom: 5px solid #191d21;
  border-top: 5px dashed transparent;
  border-left: 5px dashed transparent;
  border-right: 5px dashed transparent;
}
@keyframes boxShadow {
  0% {
    // box-shadow: inset 0px 0px 15px 1px rgba(255, 34, 34, 0.5);
    // border: 1px solid rgba(255, 34, 34, 1);
    // opacity: 0.8;
    // flashFont
    // color: rgb(255, 34, 34);
    transform: scale(1.4);
  }
  50% {
    /* box-shadow: none;
    border: 0;
    opacity: 1; */
    transform: scale(.8);
  }
  100% {
    /* box-shadow: none;
    border: 0;
    opacity: 1; */
    transform: scale(1.4);
  }
}
</style>
<style lang="scss">
.el-popover.pop-div-my {
  background: #191d21;
  border: #191d21;
  top: 100px !important;
  left: 1048px !important;
  height: 188px;
}
.el-popover.pop-div-my .el-table th.is-leaf {
  padding: 0;
}
</style>
<style>
.el-table th,
.el-table tr {
  background-color: #191d21;
  /* border: 0; */
}
.el-popover.pop-div-my td,
.el-popover.pop-div-my th.is-leaf {
  background-color: #191d21;
  border: 0;
}
.el-table::before {
  height: 0;
}
.el-table td {
  padding: 7px 0;
}
.messageTips {
  width: 18px;
  height: 18px;
  border-radius: 9px;
  position: absolute;
  top: -3px;
  right: -3px;
  background: rgb(194, 0, 0);
  color: #fff;
  font-size: 12px;
  line-height: 18px;
  /* text-align: center; */
  padding-left: 1px;
  transform: scale(1.2);
}
/*定义滚动条高宽及背景 高宽分别对应横竖滚动条的尺寸*/
::-webkit-scrollbar {
  width: 10px;
  height: 16px;
  background-color: #191d21;
}

/*定义滚动条轨道 内阴影+圆角*/
::-webkit-scrollbar-track {
  -webkit-box-shadow: inset 0 0 6px #191d21;
  border-radius: 10px;
  background-color: #191d21;
}

/*定义滑块 内阴影+圆角*/
::-webkit-scrollbar-thumb {
  border-radius: 8px;
  -webkit-box-shadow: inset 0 0 6px #191d21;
  background-color: #262b2f;
}
.select ul{
  background-color: #191d21;
}
.select .el-select-dropdown__item.selected {
    color: #9f9f9f;
    font-weight: 700;
}
.select .el-select-dropdown__item.selected:hover {
    color: #9f9f9f;
}
</style>
