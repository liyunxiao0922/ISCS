<template>
  <div class="main_drawingBox" ref="main_drawingBox">
    <RobotInfo ref="robotInfo" v-show="false" />
    <div @click="test" class="main_drawingContext" ref="main_drawingContext">
      <!-- 开始的隧道地面和隧道口 -->
      <div class="tunnelBackgroundBorder_imgBox">
        <img :src="tunnelindex == 2?tunnelBackgroundBorder:tunnelBackgroundBorder3D" alt="" />
      </div>
      <div class="tunnelEntrance entrance">
        <img src="./assets/img/entrance.png" alt="隧道口icon丢失了" />
      </div>
      <!-- 隧道主体 -->
      <div :class="tunnelindex == 2?'mainContext':'mainContext1'" ref="mainContext">
        <div class="background_imgBox">
          <!-- <img
            v-if="tunnelInfo.tunnelLength - 0 > 500"
            src="./assets/img/tunnelBackground.png"
            alt=""
          />
          <img v-else src="./assets/img/tunnelBackgroundshort.png" alt="" /> -->
        </div>
        <div class="mainOptionBox">
          <!-- 车道指示灯灯 -->
          <div v-show="$store.getters.getState.laneIndicator" v-if="tunnelindex == 2" class="itemBox laneIndicatorUpBox">
            <div
              v-for="(item, index) in devObj.laneIndicator.upList"
              :class="
                item.isSearch
                  ? 'laneIndicatorItem isSearch'
                  : 'laneIndicatorItem'
              "
              :id="'a' + item.equipId"
              :key="index"
              :style="`left: ${returnLeftValue(item, index) + 'px'}; top:${item.trafficLane == 1 ? '111px;' : (item.trafficLane == 2 ? '-30px' : '0')}`"
              @click="laneIndicatorClick(item)"
            >
              <el-tooltip
                placement="top"
                ref="tooltip1"
                :hide-after="20000"
                popper-class="devTooltip"
              >
                <div slot="content" class="vehicleDetectorItemContent">
                  <el-card class="box-card">
                    <el-row >
                      <el-col style="padding:0" :span="12">设备名称：</el-col>
                      <el-col style="padding:0" :span="12">车道指示灯</el-col>
                      <el-col style="padding:0" :span="12">设备位置：</el-col>
                      <el-col style="padding:0" :span="12">{{ item.equipPile }}</el-col>
                    </el-row>
                    <el-row >
                      <el-col style="padding:0" :span="12">设备编号：</el-col>
                      <el-col style="padding:0" :span="12">{{ item.equipId }}</el-col>
                      <el-col style="padding:0" :span="12">设备位置：</el-col>
                      <el-col style="padding:0" :span="12">上行</el-col>
                    </el-row>
                  </el-card>
                </div>
                <img @mouseout="leaver(1,index)" :src="item.imageAddress" alt="车道指示灯icon丢了" />
              </el-tooltip>
            </div>
          </div>
          <div v-show="$store.getters.getState.laneIndicator" v-else class="itemBox laneIndicatorUpBox">
            <div
              v-for="(item, index) in devObj.laneIndicator.upList"
              :class="
                item.isSearch
                  ? 'laneIndicatorItem isSearch'
                  : 'laneIndicatorItem'
              "
              :id="'a' + item.equipId"
              :key="index"
              :style="`left: ${returnLeftValue(item, index) + 'px'}; top:${item.trafficLane == 1 ? '71px;' : (item.trafficLane == 2 ? '-30px' : (item.trafficLane == 3 ? '172px' : '0'))}`"
              @click="laneIndicatorClick(item)"
            >
              <el-tooltip
                placement="top"
                ref="tooltip2"
                :hide-after="20000"
                popper-class="devTooltip"
              >
                <div slot="content" class="vehicleDetectorItemContent">
                  <el-card class="box-card">
                    <el-row >
                      <el-col style="padding:0" :span="12">设备名称：</el-col>
                      <el-col style="padding:0" :span="12">车道指示灯</el-col>
                      <el-col style="padding:0" :span="12">设备位置：</el-col>
                      <el-col style="padding:0" :span="12">{{ item.equipPile }}</el-col>
                    </el-row>
                    <el-row >
                      <el-col style="padding:0" :span="12">设备编号：</el-col>
                      <el-col style="padding:0" :span="12">{{ item.equipId }}</el-col>
                      <el-col style="padding:0" :span="12">设备位置：</el-col>
                      <el-col style="padding:0" :span="12">上行</el-col>
                    </el-row>
                  </el-card>
                </div>
                <img @mouseout="leaver(2,index)" :src="item.imageAddress" alt="车道指示灯icon丢了" />
              </el-tooltip>
            </div>
          </div>
          <div v-show="$store.getters.getState.laneIndicator" v-if="tunnelindex == 2" class="itemBox laneIndicatorDownBox">
            <div
              v-for="(item, index) in devObj.laneIndicator.downList"
              :class="
                item.isSearch
                  ? 'laneIndicatorItem isSearch'
                  : 'laneIndicatorItem'
              "
              :id="'a' + item.equipId"
              :key="index"
              :style="`left: ${returnLeftValue(item, index) + 'px'}; top:${item.trafficLane == 1 ? '68px;' : (item.trafficLane == 2 ? '-69px' : '0')}`"
              @click="laneIndicatorClick(item)"
            >
              <el-tooltip
                placement="top"
                ref="tooltip3"
                :hide-after="20000"
                popper-class="devTooltip"
              >
                <div slot="content" class="vehicleDetectorItemContent">
                  <el-card class="box-card">
                    <el-row >
                      <el-col style="padding:0" :span="12">设备名称：</el-col>
                      <el-col style="padding:0" :span="12">车道指示灯</el-col>
                      <el-col style="padding:0" :span="12">设备位置：</el-col>
                      <el-col style="padding:0" :span="12">{{ item.equipPile }}</el-col>
                    </el-row>
                    <el-row >
                      <el-col style="padding:0" :span="12">设备编号：</el-col>
                      <el-col style="padding:0" :span="12">{{ item.equipId }}</el-col>
                      <el-col style="padding:0" :span="12">设备位置：</el-col>
                      <el-col style="padding:0" :span="12">下行</el-col>
                    </el-row>
                  </el-card>
                </div>
                <img :src="item.imageAddress" @mouseout="leaver(3,index)" alt="车道指示灯icon丢了" />
              </el-tooltip>
            </div>
          </div>
          <div v-show="$store.getters.getState.laneIndicator" v-else class="itemBox laneIndicatorDownBox">
            <div
              v-for="(item, index) in devObj.laneIndicator.downList"
              :class="
                item.isSearch
                  ? 'laneIndicatorItem isSearch'
                  : 'laneIndicatorItem'
              "
              :id="'a' + item.equipId"
              :key="index"
              :style="`left: ${returnLeftValue(item, index) + 'px'}; top:${item.trafficLane == 1 ? '133px' : (item.trafficLane == 2 ? '31px' : (item.trafficLane == 3 ? '-69px' : '0'))}`"
              @click="laneIndicatorClick(item)"
            >
              <el-tooltip
                placement="top"
                ref="tooltip4"
                :hide-after="20000"
                popper-class="devTooltip"
              >
                <div slot="content" class="vehicleDetectorItemContent">
                  <el-card class="box-card">
                    <el-row >
                      <el-col style="padding:0" :span="12">设备名称：</el-col>
                      <el-col style="padding:0" :span="12">车道指示灯</el-col>
                      <el-col style="padding:0" :span="12">设备位置：</el-col>
                      <el-col style="padding:0" :span="12">{{ item.equipPile }}</el-col>
                    </el-row>
                    <el-row >
                      <el-col style="padding:0" :span="12">设备编号：</el-col>
                      <el-col style="padding:0" :span="12">{{ item.equipId }}</el-col>
                      <el-col style="padding:0" :span="12">设备位置：</el-col>
                      <el-col style="padding:0" :span="12">下行</el-col>
                    </el-row>
                  </el-card>
                </div>
                <img :src="item.imageAddress" @mouseout="leaver(4,index)" alt="车道指示灯icon丢了" />
              </el-tooltip>
            </div>
          </div>
          <!-- 隧道风机 -->
          <div v-show="$store.getters.getState.tunnelFan" class="itemBox tunnelFanUpBox">
            <div
              :class="
                item.equipTypeOperationId == '19'
                  ? item.isSearch
                    ? 'tunnelFanItem stop isSearch'
                    : 'tunnelFanItem stop'
                  : item.equipTypeOperationId == '17'
                  ? item.isSearch
                    ? 'tunnelFanItem positive isSearch'
                    : 'tunnelFanItem positive'
                  : item.isSearch
                  ? 'tunnelFanItem reverse isSearch'
                  : 'tunnelFanItem reverse'
              "
              v-for="(item, index) in devObj.tunnelFan.upList"
              :id="'a' + item.equipId"
              :key="index"
              :style="{ left: returnLeftValue(item, index) + 'px' }"
              @click="fanClick(item)"
            >
              <el-tooltip
                placement="top"
                ref="tooltip5"
                :hide-after="20000"
                popper-class="devTooltip"
              >
                <div slot="content" class="vehicleDetectorItemContent">
                  <el-card class="box-card">
                    <el-row >
                      <el-col style="padding:0" :span="12">设备名称：</el-col>
                      <el-col style="padding:0" :span="12">隧道风机</el-col>
                      <el-col style="padding:0" :span="12">设备位置：</el-col>
                      <el-col style="padding:0" :span="12">{{ item.equipPile }}</el-col>
                    </el-row>
                    <el-row >
                      <el-col style="padding:0" :span="12">设备编号：</el-col>
                      <el-col style="padding:0" :span="12">{{ item.equipId }}</el-col>
                      <el-col style="padding:0" :span="12">设备位置：</el-col>
                      <el-col style="padding:0" :span="12">上行</el-col>
                    </el-row>
                  </el-card>
                </div>
                <img :src="item.imageAddress" alt="隧道风机icon丢了" @mouseout="leaver(5,index)"/>
              </el-tooltip>
            </div>
          </div>
          <div v-show="$store.getters.getState.tunnelFan" class="itemBox tunnelFanDownBox">
            <div
              :class="
                item.equipTypeOperationId == '19'
                  ? item.isSearch
                    ? 'tunnelFanItem stop isSearch'
                    : 'tunnelFanItem stop'
                  : item.equipTypeOperationId == '17'
                  ? item.isSearch
                    ? 'tunnelFanItem positive isSearch'
                    : 'tunnelFanItem positive'
                  : item.isSearch
                  ? 'tunnelFanItem reverse isSearch'
                  : 'tunnelFanItem reverse'
              "
              v-for="(item, index) in devObj.tunnelFan.downList"
              :id="'a' + item.equipId"
              :key="index"
              :style="{ left: returnLeftValue(item, index) + 'px' }"
              @click="fanClick(item)"
            >
              <el-tooltip
                placement="top"
                ref="tooltip6"
                :hide-after="20000"
                popper-class="devTooltip"
              >
                <div slot="content" class="vehicleDetectorItemContent">
                  <el-card class="box-card">
                    <el-row >
                      <el-col style="padding:0" :span="12">设备名称：</el-col>
                      <el-col style="padding:0" :span="12">隧道风机</el-col>
                      <el-col style="padding:0" :span="12">设备位置：</el-col>
                      <el-col style="padding:0" :span="12">{{ item.equipPile }}</el-col>
                    </el-row>
                    <el-row >
                      <el-col style="padding:0" :span="12">设备编号：</el-col>
                      <el-col style="padding:0" :span="12">{{ item.equipId }}</el-col>
                      <el-col style="padding:0" :span="12">设备位置：</el-col>
                      <el-col style="padding:0" :span="12">下行</el-col>
                    </el-row>
                  </el-card>
                </div>
                <img :src="item.imageAddress" alt="隧道风机icon丢了"  @mouseout="leaver(6,index)" />
              </el-tooltip>
            </div>
          </div>
          <div class="itemBox tunnelFanUpBox">
            <div v-if="$store.getters.getTips.tunnelDirection == 1 && warningflag" :style="`position: absolute; z-index: 999; left: ${mToPx($store.getters.getTips.pile)}px;`">
              <img @click="showModal" class="flashFont" style="width: 24px; height: 24px;" :src="fireAccident" alt="">
            </div>
          </div>
          <div class="itemBox tunnelFanDownBox">
            <div v-if="$store.getters.getTips.tunnelDirection == 2 && warningflag" :style="`position: absolute; z-index: 999; left: ${mToPx($store.getters.getTips.pile)}px;`">
              <img @click="showModal" class="flashFont" style="width: 24px; height: 24px;" :src="fireAccident" alt="">
            </div>
          </div>
          <!-- 引道照明 -->
          <div v-show="$store.getters.getState.approachLighting" v-if="tunnelindex == 2" class="itemBox lightingUpBox">
            <div
              v-for="(item, index) in devObj.approachLighting.upList"
              :class="
                item.isSearch ? 'signalLampItem isSearch' : 'signalLampItem'
              "
              :id="'a' + item.equipId"
              :key="index"
              :style="`left: ${returnLeftValue(item, index) + 'px'}; top: ${item.trafficLane == 1 ? '-325px; transform: rotate(180deg)' : '0'}`"
              @click="approachLightingClick(item)"
            >
              <el-tooltip
                placement="top"
                :hide-after="20000"
                ref="tooltip7"
                popper-class="devTooltip"
              >
                <div slot="content" class="vehicleDetectorItemContent">
                  <el-card class="box-card">
                    <el-row >
                      <el-col style="padding:0" :span="12">设备名称：</el-col>
                      <el-col style="padding:0" :span="12">引道照明</el-col>
                      <el-col style="padding:0" :span="12">设备位置：</el-col>
                      <el-col style="padding:0" :span="12">{{ item.equipPile }}</el-col>
                    </el-row>
                    <el-row >
                      <el-col style="padding:0" :span="12">设备编号：</el-col>
                      <el-col style="padding:0" :span="12">{{ item.equipId }}</el-col>
                      <el-col style="padding:0" :span="12">设备位置：</el-col>
                      <el-col style="padding:0" :span="12">上行</el-col>
                    </el-row>
                  </el-card>
                </div>
                <img style="height:17px;width:auto" :src="item.imageAddress" alt="引道照明icon丢了" @mouseout="leaver(7,index)" />
              </el-tooltip>
            </div>
          </div>
          <div v-show="$store.getters.getState.approachLighting" v-else class="itemBox lightingUpBox">
            <div
              v-for="(item, index) in devObj.approachLighting.upList"
              :class="
                item.isSearch ? 'signalLampItem isSearch' : 'signalLampItem'
              "
              :id="'a' + item.equipId"
              :key="index"
              :style="`left: ${returnLeftValue(item, index) + 'px'}; top: ${item.trafficLane == 1 ? '-348px; transform: rotate(180deg)' : '0'}`"
              @click="approachLightingClick(item)"
            >
              <el-tooltip
                placement="top"
                :hide-after="20000"
                ref="tooltip7"
                popper-class="devTooltip"
              >
                <div slot="content" class="vehicleDetectorItemContent">
                  <el-card class="box-card">
                    <el-row >
                      <el-col style="padding:0" :span="12">设备名称：</el-col>
                      <el-col style="padding:0" :span="12">引道照明</el-col>
                      <el-col style="padding:0" :span="12">设备位置：</el-col>
                      <el-col style="padding:0" :span="12">{{ item.equipPile }}</el-col>
                    </el-row>
                    <el-row >
                      <el-col style="padding:0" :span="12">设备编号：</el-col>
                      <el-col style="padding:0" :span="12">{{ item.equipId }}</el-col>
                      <el-col style="padding:0" :span="12">设备位置：</el-col>
                      <el-col style="padding:0" :span="12">上行</el-col>
                    </el-row>
                  </el-card>
                </div>
                <img style="height:17px;width:auto" :src="item.imageAddress" alt="引道照明icon丢了" @mouseout="leaver(7,index)" />
              </el-tooltip>
            </div>
          </div>
          <div v-show="$store.getters.getState.approachLighting" v-if="tunnelindex == 2" class="itemBox lightingDownBox">
            <div
              v-for="(item, index) in devObj.approachLighting.downList"
              :class="
                item.isSearch ? 'signalLampItem isSearch' : 'signalLampItem'
              "
              :id="'a' + item.equipId"
              :key="index"
              :style="`left: ${returnLeftValue(item, index) + 'px'}; top: ${item.trafficLane == 1 ? '0;transform: rotate(180deg)' : '320px'}`"
              @click="approachLightingClick(item)"
            >
              <el-tooltip
                placement="top"
                :hide-after="20000"
                ref="tooltip8"
                popper-class="devTooltip"
              >
                <div slot="content" class="vehicleDetectorItemContent">
                  <el-card class="box-card">
                    <el-row >
                      <el-col style="padding:0" :span="12">设备名称：</el-col>
                      <el-col style="padding:0" :span="12">引道照明</el-col>
                      <el-col style="padding:0" :span="12">设备位置：</el-col>
                      <el-col style="padding:0" :span="12">{{ item.equipPile }}</el-col>
                    </el-row>
                    <el-row >
                      <el-col style="padding:0" :span="12">设备编号：</el-col>
                      <el-col style="padding:0" :span="12">{{ item.equipId }}</el-col>
                      <el-col style="padding:0" :span="12">设备位置：</el-col>
                      <el-col style="padding:0" :span="12">下行</el-col>
                    </el-row>
                  </el-card>
                </div>
                <img style="height:17px;width:auto" :src="item.imageAddress" alt="引道照明icon丢了"  @mouseout="leaver(8,index)"/>
              </el-tooltip>
            </div>
          </div>
          <div v-show="$store.getters.getState.approachLighting" v-else class="itemBox lightingDownBox">
            <div
              v-for="(item, index) in devObj.approachLighting.downList"
              :class="
                item.isSearch ? 'signalLampItem isSearch' : 'signalLampItem'
              "
              :id="'a' + item.equipId"
              :key="index"
              :style="`left: ${returnLeftValue(item, index) + 'px'}; top: ${item.trafficLane == 1 ? '0;transform: rotate(180deg)' : '345px'}`"
              @click="approachLightingClick(item)"
            >
              <el-tooltip
                placement="top"
                :hide-after="20000"
                ref="tooltip8"
                popper-class="devTooltip"
              >
                <div slot="content" class="vehicleDetectorItemContent">
                  <el-card class="box-card">
                    <el-row >
                      <el-col style="padding:0" :span="12">设备名称：</el-col>
                      <el-col style="padding:0" :span="12">引道照明</el-col>
                      <el-col style="padding:0" :span="12">设备位置：</el-col>
                      <el-col style="padding:0" :span="12">{{ item.equipPile }}</el-col>
                    </el-row>
                    <el-row >
                      <el-col style="padding:0" :span="12">设备编号：</el-col>
                      <el-col style="padding:0" :span="12">{{ item.equipId }}</el-col>
                      <el-col style="padding:0" :span="12">设备位置：</el-col>
                      <el-col style="padding:0" :span="12">下行</el-col>
                    </el-row>
                  </el-card>
                </div>
                <img style="height:17px;width:auto" :src="item.imageAddress" alt="引道照明icon丢了"  @mouseout="leaver(8,index)"/>
              </el-tooltip>
            </div>
          </div>
          <!-- 灯光 -->
          <div v-show="$store.getters.getState.lighting" class="itemBox lightingUpBox">
            <div
              v-for="(item, index) in lightArr"
              :class="item.isSearch ? 'lightingItem isSearch' : 'lightingItem'
              "
              :id="'a' + item.equipId"
              :key="index"
              :style="`left: ${returnLeftValue(item, index) + 'px'};`"
              @click="lightingClick(item)"
              v-if="item.sysTunnelDirection == 1 && item.type == 4"
            >
              <el-tooltip
                placement="top"
                :hide-after="20000"
                :data-tooltipid="item.equipId"
                ref="tooltip9"
                popper-class="devTooltip"
              >
                <div slot="content" class="vehicleDetectorItemContent">
                  <el-card class="box-card">
                    <el-row >
                      <el-col style="padding:0" :span="12">设备名称：</el-col>
                      <el-col style="padding:0" :span="12">普通照明</el-col>
                      <el-col style="padding:0" :span="12">设备位置：</el-col>
                      <el-col style="padding:0" :span="12">{{ item.equipPile }}</el-col>
                    </el-row>
                    <el-row >
                      <!-- <el-col style="padding:0" :span="12">设备编号：</el-col>
                      <el-col style="padding:0" :span="12">{{ item.equipId }}</el-col> -->
                      <el-col style="padding:0" :span="12">设备位置：</el-col>
                      <el-col style="padding:0" :span="12">上行</el-col>
                    </el-row>
                  </el-card>
                </div>
                <img style="z-index: 99;" :src="item.imageAddress" alt="灯光icon丢了"  @mouseout="leaver(9,index)"/>
                <!-- <img class="projectionlong" src="./assets/img/projection.png" alt=""> -->
              </el-tooltip>
            </div>
          </div>
          <div v-show="$store.getters.getState.lighting" class="itemBox lightingDownBox">
            <div
              v-for="(item, index) in lightArr"
              :class="item.isSearch ? 'lightingItem isSearch' : 'lightingItem'"
              :id="'a' + item.equipId"
              :key="index"
              :style="`left: ${returnLeftValue(item, index) + 'px'}; transform:rotate(180deg);`"
              @click="lightingClick(item)"
              v-if="item.sysTunnelDirection == 2 && item.type == 4"
            >
              <el-tooltip
                placement="top"
                :hide-after="20000"
                :data-tooltipid="item.equipId"
                ref="tooltip9"
                popper-class="devTooltip"
              >
                <div slot="content" class="vehicleDetectorItemContent">
                  <el-card class="box-card">
                    <el-row >
                      <el-col style="padding:0" :span="12">设备名称：</el-col>
                      <el-col style="padding:0" :span="12">普通照明</el-col>
                      <el-col style="padding:0" :span="12">设备位置：</el-col>
                      <el-col style="padding:0" :span="12">{{ item.equipPile }}</el-col>
                    </el-row>
                    <el-row >
                      <!-- <el-col style="padding:0" :span="12">设备编号：</el-col>
                      <el-col style="padding:0" :span="12">{{ item.equipId }}</el-col> -->
                      <el-col style="padding:0" :span="12">设备位置：</el-col>
                      <el-col style="padding:0" :span="12">下行</el-col>
                    </el-row>
                  </el-card>
                </div>
                <img style="z-index: 99;" :src="item.imageAddress" alt="灯光icon丢了" @mouseout="leaver(9,index)"/>
                <!-- <img class="projectionlong"  src="./assets/img/projection.png" alt=""> -->
              </el-tooltip>
            </div>
          </div>
          <!-- 加强照明 -->
          <div v-show="$store.getters.getState.strengthenLighting" class="itemBox lightingUpBox">
            <div
              v-for="(item, index) in lightArr"
              :class="item.isSearch ? 'lightingItem isSearch' : 'lightingItem'
              "
              :id="'a' + item.equipId"
              :key="index"
              :style="`left: ${returnLeftValue(item, index) + 50 + 'px'};`"
              v-if="item.sysTunnelDirection == 1 && item.type == 10"
              @click="lightingClick(item)"
            >
              <el-tooltip
                placement="top"
                :hide-after="20000"
                :data-tooltipid="item.equipId"
                ref="tooltip9"
                popper-class="devTooltip"
              >
                <div slot="content" class="vehicleDetectorItemContent">
                  <el-card class="box-card">
                    <el-row >
                      <el-col style="padding:0" :span="12">设备名称：</el-col>
                      <el-col style="padding:0" :span="12">加强照明</el-col>
                      <el-col style="padding:0" :span="12">设备位置：</el-col>
                      <el-col style="padding:0" :span="12">{{ item.equipPile }}</el-col>
                    </el-row>
                    <el-row >
                      <!-- <el-col style="padding:0" :span="12">设备编号：</el-col>
                      <el-col style="padding:0" :span="12">{{ item.equipId }}</el-col> -->
                      <el-col style="padding:0" :span="12">设备位置：</el-col>
                      <el-col style="padding:0" :span="12">上行</el-col>
                    </el-row>
                  </el-card>
                </div>
                <img :src="item.imageAddress" alt="灯光icon丢了" @mouseout="leaver(9,index)"/>
              </el-tooltip>
            </div>
          </div>
          <div v-show="$store.getters.getState.strengthenLighting" class="itemBox lightingDownBox">
            <div
              v-for="(item, index) in lightArr"
              :class="item.isSearch ? 'lightingItem isSearch' : 'lightingItem'"
              :id="'a' + item.equipId"
              :key="index"
              :style="`left: ${returnLeftValue(item, index) + 50 + 'px'};  transform:rotate(180deg);`"
              v-if="item.sysTunnelDirection == 2 && item.type == 10"
              @click="lightingClick(item)"
            >
              <el-tooltip
                placement="top"
                :hide-after="20000"
                :data-tooltipid="item.equipId"
                ref="tooltip9"
                popper-class="devTooltip"
              >
                <div slot="content" class="vehicleDetectorItemContent">
                  <el-card class="box-card">
                    <el-row >
                      <el-col style="padding:0" :span="12">设备名称：</el-col>
                      <el-col style="padding:0" :span="12">加强照明</el-col>
                      <el-col style="padding:0" :span="12">设备位置：</el-col>
                      <el-col style="padding:0" :span="12">{{ item.equipPile }}</el-col>
                    </el-row>
                    <el-row >
                      <!-- <el-col style="padding:0" :span="12">设备编号：</el-col>
                      <el-col style="padding:0" :span="12">{{ item.equipId }}</el-col> -->
                      <el-col style="padding:0" :span="12">设备位置：</el-col>
                      <el-col style="padding:0" :span="12">下行</el-col>
                    </el-row>
                  </el-card>
                </div>
                <img :src="item.imageAddress" alt="加强照明icon丢了" @mouseout="leaver(9,index)"/>
              </el-tooltip>
            </div>
          </div>
          <!-- 信号灯 -->
          <div v-show="$store.getters.getState.signalLamp" class="itemBox signalLampUpBox">
            <div
              v-for="(item, index) in devObj.signalLamp.upList"
              :class="
                item.isSearch ? 'signalLampItem isSearch' : 'signalLampItem'
              "
              :id="'a' + item.equipId"
              :key="index"
              :style="{ left: returnLeftValue(item, index) + 'px' }"
            >
              <el-tooltip
                placement="top"
                :hide-after="20000"
                popper-class="devTooltip"
              >
                <div slot="content" class="vehicleDetectorItemContent">
                  <el-card class="box-card">
                    <el-row >
                      <el-col style="padding:0" :span="12">设备名称：</el-col>
                      <el-col style="padding:0" :span="12">信号灯</el-col>
                      <el-col style="padding:0" :span="12">设备位置：</el-col>
                      <el-col style="padding:0" :span="12">{{ item.equipPile }}</el-col>
                    </el-row>
                    <el-row >
                      <el-col style="padding:0" :span="12">设备编号：</el-col>
                      <el-col style="padding:0" :span="12">{{ item.equipId }}</el-col>
                      <el-col style="padding:0" :span="12">设备位置：</el-col>
                      <el-col style="padding:0" :span="12">上行</el-col>
                    </el-row>
                  </el-card>
                </div>
                <img style="height:50px;width:auto;" :src="item.imageAddress" alt="信号灯icon丢了" />
              </el-tooltip>
            </div>
          </div>
          <div v-show="$store.getters.getState.signalLamp" class="itemBox signalLampDownBox">
            <div
              v-for="(item, index) in devObj.signalLamp.downList"
              :class="
                item.isSearch ? 'signalLampItem isSearch' : 'signalLampItem'
              "
              :id="'a' + item.equipId"
              :key="index"
              :style="{ left: returnLeftValue(item, index) + 'px' }"
            >
              <el-tooltip
                placement="top"
                :hide-after="20000"
                popper-class="devTooltip"
              >
                <div slot="content" class="vehicleDetectorItemContent">
                  <el-card class="box-card">
                    <el-row >
                      <el-col style="padding:0" :span="12">设备名称：</el-col>
                      <el-col style="padding:0" :span="12">信号灯</el-col>
                      <el-col style="padding:0" :span="12">设备位置：</el-col>
                      <el-col style="padding:0" :span="12">{{ item.equipPile }}</el-col>
                    </el-row>
                    <el-row >
                      <el-col style="padding:0" :span="12">设备编号：</el-col>
                      <el-col style="padding:0" :span="12">{{ item.equipId }}</el-col>
                      <el-col style="padding:0" :span="12">设备位置：</el-col>
                      <el-col style="padding:0" :span="12">下行</el-col>
                    </el-row>
                  </el-card>
                </div>
                <img style="height:50px;width:auto;" :src="item.imageAddress" alt="信号灯icon丢了" />
              </el-tooltip>
            </div>
          </div>
          <!-- 亮度检测器 -->
          <div v-show="$store.getters.getState.brightnessDetector" class="itemBox brightnessDetectorUpBox">
            <div
              v-for="(item, index) in devObj.brightnessDetector.upList"
              :class="
                item.isSearch
                  ? 'brightnessDetectorItem isSearch'
                  : 'brightnessDetectorItem'
              "
              :id="'a' + item.equipId"
              :key="index"
              :style="{
                left: returnLeftValue(item, index) + 'px',
              }"
            >
              <el-tooltip
                placement="top"
                :hide-after="20000"
                popper-class="devTooltip"
              >
                <div slot="content" class="vehicleDetectorItemContent">
                  <el-card class="box-card">
                    <el-row >
                      <el-col style="padding:0" :span="12">设备名称：</el-col>
                      <el-col style="padding:0" :span="12">亮度检测器</el-col>
                      <el-col style="padding:0" :span="12">设备位置：</el-col>
                      <el-col style="padding:0" :span="12">{{ item.equipPile }}</el-col>
                    </el-row>
                    <el-row >
                      <el-col style="padding:0" :span="12">设备编号：</el-col>
                      <el-col style="padding:0" :span="12">{{ item.equipId }}</el-col>
                      <el-col style="padding:0" :span="12">设备位置：</el-col>
                      <el-col style="padding:0" :span="12">上行</el-col>
                    </el-row>
                  </el-card>
                </div>
                <div>
                  <img :src="item.imageAddress" alt="亮度检测器icon丢了" />
                  <p class="brightnessDetectorItemContext">
                    <span>{{item.parameterValue}}</span>
                  </p>
                </div>
              </el-tooltip>
            </div>
          </div>
          <div v-show="$store.getters.getState.brightnessDetector" class="itemBox brightnessDetectorDownBox">
            <div
              v-for="(item, index) in devObj.brightnessDetector.downList"
              :class="
                item.isSearch
                  ? 'brightnessDetectorItem isSearch'
                  : 'brightnessDetectorItem'
              "
              :id="'a' + item.equipId"
              :key="index"
              :style="{
                left: returnLeftValue(item, index) + 'px',
              }"
            >
              <el-tooltip
                placement="top"
                :hide-after="20000"
                popper-class="devTooltip"
              >
                <div slot="content" class="vehicleDetectorItemContent">
                  <el-card class="box-card">
                    <el-row >
                      <el-col style="padding:0" :span="12">设备名称：</el-col>
                      <el-col style="padding:0" :span="12">亮度检测器</el-col>
                      <el-col style="padding:0" :span="12">设备位置：</el-col>
                      <el-col style="padding:0" :span="12">{{ item.equipPile }}</el-col>
                    </el-row>
                    <el-row >
                      <el-col style="padding:0" :span="12">设备编号：</el-col>
                      <el-col style="padding:0" :span="12">{{ item.equipId }}</el-col>
                      <el-col style="padding:0" :span="12">设备位置：</el-col>
                      <el-col style="padding:0" :span="12">下行</el-col>
                    </el-row>
                  </el-card>
                </div>
                <div>
                  <img :src="item.imageAddress" alt="亮度检测器icon丢了" />
                  <p class="brightnessDetectorItemContext">
                    <span>{{item.parameterValue}}</span>
                  </p>
                </div>
              </el-tooltip>
            </div>
          </div>
          <!-- 照度检测器 -->
          <div v-show="$store.getters.getState.illuminanceDetector" class="itemBox illuminanceDetectorUpBox">
            <div
              v-for="(item, index) in devObj.illuminanceDetector.upList"
              :class="
                item.isSearch
                  ? 'illuminanceDetectorItem isSearch'
                  : 'illuminanceDetectorItem'
              "
              :id="'a' + item.equipId"
              :key="index"
              :style="{
                left: returnLeftValue(item, index) + 'px',
              }"
            >
              <el-tooltip
                placement="top"
                :hide-after="20000"
                popper-class="devTooltip"
              >
                <div slot="content" class="vehicleDetectorItemContent">
                  <el-card class="box-card">
                    <el-row >
                      <el-col style="padding:0" :span="12">设备名称：</el-col>
                      <el-col style="padding:0" :span="12">照度检测器</el-col>
                      <el-col style="padding:0" :span="12">设备位置：</el-col>
                      <el-col style="padding:0" :span="12">{{ item.equipPile }}</el-col>
                    </el-row>
                    <el-row >
                      <el-col style="padding:0" :span="12">设备编号：</el-col>
                      <el-col style="padding:0" :span="12">{{ item.equipId }}</el-col>
                      <el-col style="padding:0" :span="12">设备位置：</el-col>
                      <el-col style="padding:0" :span="12">上行</el-col>
                    </el-row>
                  </el-card>
                </div>
                <div>
                  <img :src="item.imageAddress" alt="照度检测器icon丢了" />
                  <p class="illuminanceDetectorItemContext">
                    <span>{{item.parameterValue}}</span>
                  </p>
                </div>
              </el-tooltip>
            </div>
          </div>
          <div v-show="$store.getters.getState.illuminanceDetector" class="itemBox illuminanceDetectorDownBox">
            <div
              v-for="(item, index) in devObj.illuminanceDetector.downList"
              :class="
                item.isSearch
                  ? 'illuminanceDetectorItem isSearch'
                  : 'illuminanceDetectorItem'
              "
              :id="'a' + item.equipId"
              :key="index"
              :style="{
                left: returnLeftValue(item, index) + 'px',
              }"
            >
              <el-tooltip
                placement="top"
                :hide-after="20000"
                popper-class="devTooltip"
              >
                <div slot="content" class="vehicleDetectorItemContent">
                  <el-card class="box-card">
                    <el-row >
                      <el-col style="padding:0" :span="12">设备名称：</el-col>
                      <el-col style="padding:0" :span="12">照度检测器</el-col>
                      <el-col style="padding:0" :span="12">设备位置：</el-col>
                      <el-col style="padding:0" :span="12">{{ item.equipPile }}</el-col>
                    </el-row>
                    <el-row >
                      <el-col style="padding:0" :span="12">设备编号：</el-col>
                      <el-col style="padding:0" :span="12">{{ item.equipId }}</el-col>
                      <el-col style="padding:0" :span="12">设备位置：</el-col>
                      <el-col style="padding:0" :span="12">下行</el-col>
                    </el-row>
                  </el-card>
                </div>
                <div>
                  <img :src="item.imageAddress" alt="照度检测器icon丢了" />
                  <p class="illuminanceDetectorItemContext">
                    <!-- <span :v-html="'3米/秒<br/>12度'"></span> -->
                    <span>{{item.parameterValue}}</span>
                  </p>
                </div>
              </el-tooltip>
            </div>
          </div>
          <!-- 风速风向仪 -->
          <div v-show="$store.getters.getState.anemoclinograph" class="itemBox anemoclinographUpBox">
            <div
              v-for="(item, index) in devObj.anemoclinograph.upList"
              :class="
                item.isSearch
                  ? 'anemoclinographItem isSearch'
                  : 'anemoclinographItem'
              "
              :id="'a' + item.equipId"
              :key="index"
              :style="{
                left: returnLeftValue(item, index) + 'px',
              }"
            >
              <el-tooltip
                placement="top"
                :hide-after="20000"
                popper-class="devTooltip"
              >
                <div slot="content" class="vehicleDetectorItemContent">
                  <el-card class="box-card">
                    <el-row >
                      <el-col style="padding:0" :span="12">设备名称：</el-col>
                      <el-col style="padding:0" :span="12">风速风向仪</el-col>
                      <el-col style="padding:0" :span="12">设备位置：</el-col>
                      <el-col style="padding:0" :span="12">{{ item.equipPile }}</el-col>
                    </el-row>
                    <el-row >
                      <el-col style="padding:0" :span="12">设备编号：</el-col>
                      <el-col style="padding:0" :span="12">{{ item.equipId }}</el-col>
                      <el-col style="padding:0" :span="12">设备位置：</el-col>
                      <el-col style="padding:0" :span="12">上行</el-col>
                    </el-row>
                  </el-card>
                </div>
                <div>
                  <img :src="item.imageAddress" alt="风速风向仪icon丢了" />
                  <p class="anemoclinographItemContext">
                    <span  style="font-size:12px;" v-html="item.parameterValue"></span>
                  </p>
                </div>
              </el-tooltip>
            </div>
          </div>
          <div v-show="$store.getters.getState.anemoclinograph" class="itemBox anemoclinographDownBox">
            <div
              v-for="(item, index) in devObj.anemoclinograph.downList"
              :class="
                item.isSearch
                  ? 'anemoclinographItem isSearch'
                  : 'anemoclinographItem'
              "
              :id="'a' + item.equipId"
              :key="index"
              :style="{
                left: returnLeftValue(item, index) + 'px',
              }"
            >
              <el-tooltip
                placement="top"
                :hide-after="20000"
                popper-class="devTooltip"
              >
                <div slot="content" class="vehicleDetectorItemContent">
                  <el-card class="box-card">
                    <el-row >
                      <el-col style="padding:0" :span="12">设备名称：</el-col>
                      <el-col style="padding:0" :span="12">风速风向仪</el-col>
                      <el-col style="padding:0" :span="12">设备位置：</el-col>
                      <el-col style="padding:0" :span="12">{{ item.equipPile }}</el-col>
                    </el-row>
                    <el-row >
                      <el-col style="padding:0" :span="12">设备编号：</el-col>
                      <el-col style="padding:0" :span="12">{{ item.equipId }}</el-col>
                      <el-col style="padding:0" :span="12">设备位置：</el-col>
                      <el-col style="padding:0" :span="12">下行</el-col>
                    </el-row>
                  </el-card>
                </div>
                <div>
                  <img :src="item.imageAddress" alt="风速风向仪icon丢了" />
                  <p class="anemoclinographItemContext">
                    <span style="font-size:12px;" v-html="item.parameterValue"></span>
                  </p>
                </div>
              </el-tooltip>
            </div>
          </div>
          <!-- 车辆检测器 -->
          <div v-show="$store.getters.getState.vehicleDetector" class="itemBox vehicleDetectorUpBox">
            <div
              v-for="(item, index) in devObj.vehicleDetector.upList"
              :class="
                item.isSearch
                  ? 'vehicleDetectorItem isSearch'
                  : 'vehicleDetectorItem'
              "
              :id="'a' + item.equipId"
              :key="index"
              :style="{ left: returnLeftValue(item, index) + 'px' }"
            >
              <el-tooltip placement="top" :hide-after="20000"  popper-class="devTooltip">
                <div slot="content" class="vehicleDetectorItemContent" style="width:300px;">
                  <el-card class="box-card" >
                    <div style="width:145px">
                      <el-row >
                        <el-col style="padding:0" :span="12">设备名称：</el-col>
                        <el-col style="padding:0" :span="12">车辆检测器</el-col>
                        <el-col style="padding:0" :span="12">设备位置：</el-col>
                        <el-col style="padding:0" :span="12">{{ item.equipPile }}</el-col>
                      </el-row>
                      <el-row >
                        <el-col style="padding:0" :span="12">设备编号：</el-col>
                        <el-col style="padding:0" :span="12">{{ item.equipId }}</el-col>
                        <el-col style="padding:0" :span="12">设备位置：</el-col>
                        <el-col style="padding:0" :span="12">上行</el-col>
                      </el-row>
                    </div>
                    <el-table :data="carData" style="width: 100%;margin-top:10px" border>
                      <el-table-column prop="trafficLane" label="车道" width="50">
                      </el-table-column>
                      <el-table-column prop="avgNum" label="车流量/min" width="70">
                      </el-table-column>
                      <el-table-column
                        prop="carPercent"
                        label="占有率（%）"
                        width="70"
                      >
                      <template slot-scope="scope">
                        {{parseInt(scope.row.carPercent * 100)}} 
                      </template>
                      </el-table-column>
                      <el-table-column prop="avgSpeed" label="平均车速（km/h）">
                      </el-table-column>
                    </el-table>
                  </el-card>
                </div>
                <img :src="item.imageAddress" alt="信号灯icon丢了" />
              </el-tooltip>
            </div>
          </div>
          <div v-show="$store.getters.getState.vehicleDetector" class="itemBox vehicleDetectorDownBox">
            <div
              v-for="(item, index) in devObj.vehicleDetector.downList"
              :class="
                item.isSearch
                  ? 'vehicleDetectorItem isSearch'
                  : 'vehicleDetectorItem'
              "
              :id="'a' + item.equipId"
              :key="index"
              :style="{ left: returnLeftValue(item, index) + 'px' }"
            >
              <el-tooltip placement="top" :hide-after="20000"  popper-class="devTooltip">
                <div slot="content" class="vehicleDetectorItemContent" style="width:300px;">
                  <el-card class="box-card">
                    <div style="width:145px">
                    <el-row >
                      <el-col style="padding:0" :span="12">设备名称：</el-col>
                      <el-col style="padding:0" :span="12">车辆检测器</el-col>
                      <el-col style="padding:0" :span="12">设备位置：</el-col>
                      <el-col style="padding:0" :span="12">{{ item.equipPile }}</el-col>
                    </el-row>
                    <el-row >
                      <el-col style="padding:0" :span="12">设备编号：</el-col>
                      <el-col style="padding:0" :span="12">{{ item.equipId }}</el-col>
                      <el-col style="padding:0" :span="12">设备位置：</el-col>
                      <el-col style="padding:0" :span="12">上行</el-col>
                    </el-row>
                    </div>
                    <el-table :data="carData1" style="width: 100%;margin-top:10px" border>
                      <el-table-column prop="trafficLane" label="车道" width="50">
                      </el-table-column>
                      <el-table-column prop="avgNum" label="车流量/min" width="70">
                      </el-table-column>
                      <el-table-column
                        prop="carPercent"
                        label="占有率（%）"
                        width="70"
                      >
                      <template slot-scope="scope">
                        {{parseInt(scope.row.carPercent * 100)}} 
                      </template>
                      </el-table-column>
                      <el-table-column prop="avgSpeed" label="平均车速（km/h）">
                      </el-table-column>
                    </el-table>
                  </el-card>
                </div>
                <img :src="item.imageAddress" alt="信号灯icon丢了" />
              </el-tooltip>
            </div>
          </div>
          <!-- 情报板 -->
          <div v-show="$store.getters.getState.informationBoard" @click="testinfo()" class="itemBox informationBoardUpBox">
            <div
              v-for="(item, index) in devObj.informationBoard.upList"
              :class="
                item.isSearch
                  ? 'informationBoardItem isSearch'
                  : 'informationBoardItem'
                "
              :id="'a' + item.equipId"
              :key="index"
              :style="`left: ${returnLeftValue(item, index) + 'px'}; transform: rotate(90deg);`"
            >
              <div class="informationBoard">
                <div class="informationContext" :style="`color:${item.fontColor}`">
                  {{item.information}} 
                  <span style="margin-right:20px;"></span>
                </div> 
                <div class="informationContext" :style="`color:${item.fontColor}`">
                  {{item.information}} 
                  <span style="margin-right:20px;"></span>
                </div> 
                <div class="informationContext" :style="`color:${item.fontColor}`">
                  {{item.information}} 
                  <span style="margin-right:20px;"></span>
                </div>
               </div>
              </div>
            </div>
          <div v-show="$store.getters.getState.informationBoard" class="itemBox informationBoardDownBox">
            <div
              v-for="(item, index) in devObj.informationBoard.downList"
              :class="
                item.isSearch
                  ? 'informationBoardItem isSearch'
                  : 'informationBoardItem'
              "
              :id="'a' + item.equipId"
              :key="index"
              :style="`left: ${returnLeftValue(item, index) + 'px'}; transform: rotate(-90deg);`"
            >
              <div class="informationBoard">
                <div class="informationContext" :style="`color:${item.fontColor}`">
                  {{item.information}} <span style="margin-right:20px;"></span>
                </div>
                <div class="informationContext" :style="`color:${item.fontColor}`">
                  {{item.information}} <span style="margin-right:20px;"></span>
                </div>
                <div class="informationContext" :style="`color:${item.fontColor}`">
                  {{item.information}} <span style="margin-right:20px;"></span>
                </div>
              </div>
            </div>
          </div>
          <!-- CO/VI检测器 -->
          <div v-show="$store.getters.getState.coVIDetector" class="itemBox coviUpBox">
            <div
              v-for="(item, index) in devObj.coVIDetector.upList"
              :class="
                item.isSearch ? 'anemoclinographItem  isSearch' : 'anemoclinographItem'
              "
              :id="'a' + item.equipId"
              :key="index"
              :style="{ left: returnLeftValue(item, index) + 'px' }"
            >
              <el-tooltip
                placement="top"
                :hide-after="20000"
                popper-class="devTooltip"
              >
                <div slot="content" class="vehicleDetectorItemContent">
                  <el-card class="box-card">
                    <el-row >
                      <el-col style="padding:0" :span="12">设备名称：</el-col>
                      <el-col style="padding:0" :span="12">CO/VI检测器</el-col>
                      <el-col style="padding:0" :span="12">设备位置：</el-col>
                      <el-col style="padding:0" :span="12">{{ item.equipPile }}</el-col>
                    </el-row>
                    <el-row >
                      <el-col style="padding:0" :span="12">设备编号：</el-col>
                      <el-col style="padding:0" :span="12">{{ item.equipId }}</el-col>
                      <el-col style="padding:0" :span="12">设备位置：</el-col>
                      <el-col style="padding:0" :span="12">上行</el-col>
                    </el-row>
                  </el-card>
                </div>
                <div>
                  <img style="width:12px;" :src="item.imageAddress" alt="CO/VI检测器icon丢了" />
                  <p class="anemoclinographItemContext">
                    <span style="font-size:12px;line-height:12px;" v-html="item.parameterValue"></span>
                  </p>
                </div>
              </el-tooltip>
            </div>
          </div>
          <div v-show="$store.getters.getState.coVIDetector" class="itemBox coviDownBox">
            <div
              v-for="(item, index) in devObj.coVIDetector.downList"
              :class="
                item.isSearch ? 'anemoclinographItem isSearch' : 'anemoclinographItem'
              "
              :id="'a' + item.equipId"
              :key="index"
              :style="{ left: returnLeftValue(item, index) + 'px' }"
            >
              <el-tooltip
                placement="top"
                :hide-after="20000"
                popper-class="devTooltip"
              >
                <div slot="content" class="vehicleDetectorItemContent">
                  <el-card class="box-card">
                    <el-row >
                      <el-col style="padding:0" :span="12">设备名称：</el-col>
                      <el-col style="padding:0" :span="12">CO/VI检测器</el-col>
                      <el-col style="padding:0" :span="12">设备位置：</el-col>
                      <el-col style="padding:0" :span="12">{{ item.equipPile }}</el-col>
                    </el-row>
                    <el-row >
                      <el-col style="padding:0" :span="12">设备编号：</el-col>
                      <el-col style="padding:0" :span="12">{{ item.equipId }}</el-col>
                      <el-col style="padding:0" :span="12">设备位置：</el-col>
                      <el-col style="padding:0" :span="12">下行</el-col>
                    </el-row>
                  </el-card>
                </div>
                <div>
                  <img style="width:12px;" :src="item.imageAddress" alt="CO/VI检测器icon丢了" />
                  <p class="anemoclinographItemContext">
                    <span style="font-size:12px; line-height:12px;" v-html="item.parameterValue"></span>
                  </p>
                </div>
              </el-tooltip>
            </div>
          </div>
          <!-- 摄像头 -->
          <!-- <div class="itemBox coviUpBox">
            <div
              v-for="(item, index) in ((tunnelLeng / 400) + 1)"
              :key="index"
              class="signalLampItem"
              :style="{ left: returnLeftValue(item, false) }"
            >
              <img style="width: 23px; height: 23px;" src="./assets/img/camera.png" alt="车行隧道icon丢了" />
            </div>
          </div>
          <div class="itemBox coviDownBox">
            <div
              v-for="(item, index) in ((tunnelLeng / 400) + 1)"
              :key="index"
              class="signalLampItem"
              :style="{ left: returnLeftValue(item, false) }"
            >
              <img src="./assets/img/camera.png" alt="车行隧道icon丢了" />
            </div>
          </div> -->
          <!-- 机器人 -->
          <div class="itemBox robotUpBox">
            <div
              class="robotItem"
              ref="robotItemUp"
              @click="robotClick({ deviceGroup: 0 })"
            >
              <img v-if="maintainup" class="maintain" src="./assets/img/maintain.png" alt="">
              <img v-if="chargeup" class="maintain" src="./assets/img/charge.png" alt="">
              <img src="./assets/img/WechatIMG12.png" alt="机器人icon丢了" />
            </div>
          </div>
          <div class="itemBox robotDownBox">
            <div
              class="robotItem"
              ref="robotItemBottom"
              @click="robotClick({ deviceGroup: 1 })"
            >
              <img v-if="maintaindown" class="maintain" src="./assets/img/maintain.png" alt="">
              <img v-if="chargedown" class="maintain" src="./assets/img/charge.png" alt="">
              <img src="./assets/img/WechatIMG12.png" alt="机器人icon丢了" />
            </div>
          </div>
          <!-- 车行通道 -->
          <div class="itemBox passagewayBox">
            <div
              v-for="(item, index) in 4"
              class="passagewayItem"
              :key="index"
              :style="`left: ${index != 0 ? ( 320 + (index - 1) * (tunnelLengthPx / 3.3)) : 0}px; z-index: y0;`"
            >
              <div v-if="tunnelindex == 2">
                <img v-if="index != 0" src="./assets/img/passageway.png" alt="车行隧道icon丢了" />
                <div v-else style="width:34px;height:186px;background: rgb(25 29 33);"></div>
              </div>
              <div v-else>
                <img v-if="index != 0" src="./assets/img/passageway.png" alt="车行隧道icon丢了" />
                <div v-else style="width:24px;height:140px;background: rgb(25 29 33);"></div>
              </div>
              <div :class="index != 0 ?'describe':'describe0'">{{index != 0 ? '车行通道' : '人行通道'}}</div>
              <img :style="`width: 24px; height: 22px; position: absolute; left: -60px; top: 40px;`" v-if="index != 0" src="./assets/img/car.png" alt="车行隧道icon丢了" />
              <img :style="`width: 24px; height: 22px; position: absolute; left: 108px; top: 90px;`" v-if="index != 0" src="./assets/img/car.png" alt="车行隧道icon丢了" />
            </div>
          </div>
           <!-- 电话 -->
          <div class="itemBox phoneupBox">
            <div
              v-for="(item, index) in 3"
              class="phoneItem"
              :key="index"
              :style="{ left: +returnLeftValue(item, false).split('px')[0] + 50  + 'px'}"
              v-if="$store.getters.getState.phone"
            >
              <!-- <img v-if="index != 0" src="./assets/img/car.png" alt="车行隧道icon丢了" /> -->
              <i v-if="index != 0" class="iconCommon telephone"></i>
            </div>
          </div>
          <div class="itemBox phonedownBox">
            <div
              v-for="(item, index) in 3"
              class="phoneItem"
              :key="index"
              :style="{ left: +returnLeftValue(item, false).split('px')[0] + 50  + 'px' }"
              v-if="$store.getters.getState.phone"
            >
              <!-- <img v-if="index != 0" src="./assets/img/car.png" alt="车行隧道icon丢了" /> -->
              <i v-if="index != 0" class="iconCommon telephone"></i>
            </div>
          </div>
          <!-- 摄像头 -->
          <div  v-show="$store.getters.getState.camera" class="itemBox cameraUpBox">
            <div class="itemBox coviUpBox" id="cameraUpBox">
              <div v-for="(item, index) in 10" :key="index">
                <el-popover
                  placement="bottom"
                  popper-class="pop-div-me"
                  style="background: #000"
                  title="监控视频"
                  width="300"
                  trigger="click">
                    <!-- @click="visible = !visible" -->
                    <video
                      controls="controls"
                      autoplay="autoplay"
                      loop
                      muted 
                  style="width: 280px" :src="videoUrl[1]"></video>
                  <div
                    slot="reference" 
                    :class="
                      item.isSearch ? 'passagewayItem isSearch' : 'passagewayItem'
                    "
                    :key="index"
                    :style="`left: ${(1820 / 10) * index}px;width:23px;`"
                  >
                    <!-- s@click="showVideo(videoUrl[0])"  -->
                    <img style="width: 23px; height: 23px;" src="./assets/img/camera.png" alt="车行隧道icon丢了" />
                  </div>
                </el-popover>
              </div>
            </div>
          </div>
          <div v-show="$store.getters.getState.camera" class="itemBox cameraDownBox">
            <div class="itemBox passagewayBox" id="cameraDownBox">
              
              <div v-for="(item, index) in 10" :key="index">
                <el-popover
                  placement="bottom"
                  popper-class="pop-div-me"
                  style="background: #000"
                  title="监控视频"
                  width="300"
                  trigger="click">
                    <!-- @click="visible = !visible" -->
                    <video
                      controls="controls"
                      autoplay="autoplay"
                      loop
                      muted 
                  style="width: 280px" :src="videoUrl[1]"></video>
                  <div
                    slot="reference" 
                    :class="
                      item.isSearch ? 'passagewayItem isSearch' : 'passagewayItem'
                    "
                    :key="index"
                    :style="`left: ${(1820 / 10) * index}px;width:23px;`"
                  >
                    <!-- s@click="showVideo(videoUrl[0])"  -->
                    <img style="width: 23px; transform: rotateY(180deg); height: 23px;" src="./assets/img/camera.png" alt="车行隧道icon丢了" />
                  </div>
                </el-popover>
              </div>
            </div>
          </div>
          <div  v-show="$store.getters.getState.camera" class="itemBox cameraDownBox1">
            <div class="itemBox coviUpBox" id="cameraUpBox">
              <div v-for="(item, index) in 10" :key="index">
                <el-popover
                  placement="bottom"
                  popper-class="pop-div-me"
                  style="background: #000"
                  title="监控视频"
                  width="300"
                  trigger="click">
                    <!-- @click="visible = !visible" -->
                    <video
                      controls="controls"
                      autoplay="autoplay"
                      loop
                      muted 
                  style="width: 280px" :src="videoUrl[1]"></video>
                  <div
                    slot="reference" 
                    :class="
                      item.isSearch ? 'passagewayItem isSearch' : 'passagewayItem'
                    "
                    :key="index"
                    :style="`left: ${(1820 / 10) * index}px;width:23px;`"
                  >
                    <!-- s@click="showVideo(videoUrl[0])"  -->
                    <img style="width: 23px; height: 23px;" src="./assets/img/camera.png" alt="车行隧道icon丢了" />
                  </div>
                </el-popover>
              </div>
            </div>
          </div>
          <div v-show="$store.getters.getState.camera" class="itemBox cameraUpBox1">
            <div class="itemBox passagewayBox" id="cameraDownBox">
              <div v-for="(item, index) in 10" :key="index">
                <el-popover
                  placement="bottom"
                  popper-class="pop-div-me"
                  style="background: #000"
                  title="监控视频"
                  width="300"
                  trigger="click">
                    <!-- @click="visible = !visible" -->
                    <video
                      controls="controls"
                      autoplay="autoplay"
                      loop
                      muted 
                  style="width: 280px" :src="videoUrl[1]"></video>
                  <div
                    slot="reference" 
                    :class="
                      item.isSearch ? 'passagewayItem isSearch' : 'passagewayItem'
                    "
                    :key="index"
                    :style="`left: ${(1820 / 10) * index}px;width:23px;`"
                  >
                    <!-- s@click="showVideo(videoUrl[0])"  -->
                    <img style="width: 23px; height: 23px; transform: rotateY(180deg);" src="./assets/img/camera.png" alt="车行隧道icon丢了" />
                  </div>
                </el-popover>
              </div>
            </div>
          </div>
          <!-- <div v-if="warningflag" class="warningtextup">
            <svg t="1617023955961" class="icon" viewBox="0 0 1024 1024" version="1.1" fill="#ff2222" p-id="2154" width="29" height="29"><path d="M559.652811 766.630305c-12.925381 12.961196-28.559453 19.407002-46.729278 19.407002-18.171871 0-34.18252-6.445806-47.176462-19.407002-13.440104-13.026688-19.885909-28.592198-19.885909-47.175439 0-18.171871 6.445806-34.250058 19.885909-47.176462 12.993942-13.473873 29.004591-19.953448 47.176462-19.953448 18.169825 0 33.770128 6.478552 46.729278 19.953448 13.473873 12.926404 19.919678 29.004591 19.919678 47.176462C579.57249 738.038106 573.126684 753.603617 559.652811 766.630305zM464.924333 321.648674c12.514012-13.406335 28.594245-20.331048 47.999201-20.331048 19.473517 0 35.518958 6.479575 48.067762 20.331048 12.135388 13.405311 18.581194 30.308283 18.581194 50.6731 0 17.279548-25.987884 145.847739-35.005258 239.34211l-62.774719 0c-7.371898-93.529163-35.930327-222.097354-35.930327-239.34211C445.862185 352.401072 452.342784 335.499124 464.924333 321.648674zM940.146709 758.813269 590.407256 148.543128c-42.822294-74.432223-112.557542-74.432223-155.344021 0L85.322759 758.813269c-42.787502 74.398454-7.817036 135.426389 77.895091 135.426389l699.44616 0C947.930999 894.239658 983.002772 833.212746 940.146709 758.813269z" p-id="2155"></path></svg>
          </div> -->
          <!-- 临时停车 -->
          <div class="itemBox parkingUpBox">
            <div class="parkingItem" :style="{ left: parkingLeft(1) }">
              <img src="./assets/img/parkingUp.png" alt="机器人icon丢了" />
            </div>
          </div>
          <div class="itemBox parkingDownBox">
            <div class="parkingItem" :style="{ left: parkingLeft(1) }">
              <img src="./assets/img/parkingDown.png" alt="机器人icon丢了" />
            </div>
          </div>
        </div>
      </div>
      <!-- 结尾的地面和隧道口 -->
      <div class="tunnelBackgroundBorder_imgBox">
        <img :src="tunnelindex == 2?tunnelBackgroundBorder:tunnelBackgroundBorder3D" alt="" />
      </div>
      <div class="tunnelEntrance export">
        <img src="./assets/img/export.png" alt="隧道结尾icon丢失了" />
      </div>
      <div class="scaleTop">
          <!-- v-if="scaleData"
          :scaleData="scaleData"
          :devRatio="devRatio" -->
        <transition name="el-fade-in-linear">
          <Scale
            v-show="scaleFlag"
            :devRatio="tunnelLengthPx / tunnelLeng"
            :scaleLeng="tunnelLeng / 40"
            type="top"
          />
        </transition>
      </div>
      <div class="scaleBox">
          <!-- v-if="scaleData"
          :scaleData="scaleData"
          :devRatio="devRatio" -->
        <transition name="el-fade-in-linear">
          <Scale
            v-show="scaleFlag"
            :devRatio="tunnelLengthPx / tunnelLeng"
            :scaleLeng="tunnelLeng / 40"
            type="bottom"
          />
        </transition>
      </div>
    </div>
     <videoShow
      ref="videoShow"
    />
    <!-- 桩号滚动条 -->
    <!-- <div class="scrollBox" ref="scrollBox">
      <div class="mask">
        <div class="robotBottom" ref="robotBottom"></div>
      </div>
      <div class="scrollBtn" ref="scrollBtn">
        <div class="scrollBtnMask">
          <span class="scrollContext">{{ scrollContextValue }}</span>
        </div>
        <div v-if="false" class="tooltipBox">{{ scrollContextValue }}</div>
      </div>
      <div class="robotTop" ref="robotTop"></div>
    </div> -->
  </div>
</template>

<script>
import Scale from "./Scale";
import videoShow from '@/tunnel/components/video/VideoShow'
import RobotInfo from './ConsoleArea/components/RobotInfo'
import {vehicle,videoInfoById} from '@/tunnel/api/tunnel';
export default {
  props: {
    warningflag: {
      type: Boolean
    },
    devObj: {
      type: Object,
      required: true,
    },
    currentStakeNum: {
      type: String,
      default: "k36+000",
    },
    /* scaleData: {
      type: Array,
      default: [],
    }, */
    searchFromData: {
      type: Object || null || undefined,
    },
    devRatio: {
      type: Number
    },
    tunnelLeng: {
      type: Number
    },
    scaleFlag: {
      type: Boolean,
      default: true
    },
    lightArr: {
      type: Array
    },
    tunnelindex: {
      type: Number
    },
    warningflag: {
      type: Boolean,
      default: false,
    },
  },
  components: {
    Scale,
    RobotInfo,
    videoShow
  },
  data() {
    return {
      tunnelBackgroundBorder: require("./assets/img/tunnelBackgroundBorder.png"),
      tunnelBackgroundBorder3D: require("./assets/img/tunnelBackgroundBorder3D.png"),
      fireAccident: require("./assets/img/fireAccident.png"),
      robotIntelTop: null, // 上边的机器人
      robotIntelBottom: null, // 下边的机器人
      tunnelLengthPx: 1650, // 计算出来的隧道在页面长度
      scrollContextValue: 'k36+000', // 滚动条当前所在位置对应距离
      startsign: 'k36+000',
      tunnelInfo: 2000,
      topStr: '',
      botStr: '',
      tableData: [
        {
          name: "行车道",
          num: "59",
          proportion: "0.10",
          speed: "97",
        },
        {
          name: "超车道",
          num: "4",
          proportion: "1.10",
          speed: "73",
        },
      ],
      carData: [], // 车辆指示器
      carData1:[],
      kmHu: 30,
      speedU: 0,
      kmHd: 30,
      speedD: 0,
      maintainup:false,
      chargeup:false,
      maintaindown:false,
      chargedown:false,
      robot: {
        up: {
          direction: 1,
          px: 0
        },
        down: {
          direction: 1,
          px: 0
        },
      },
      timer: null,
      timer2: null,
      eventObj: {

      },
      bournTimer2: null,
      bournTimer: null,
      videoUrl: []
      // bourn: this.$store.getters.getEvent
      /* robot: localStorage.robotInfo ? this.$store.getters.getAuto : {
        section: {
          begin: '',
          end: ''
        },
        up: '',
        down: ''
      } */
    };
  },
  mounted() {
    // this.tunnelLengthPx = 2000 * this.devRatio;
    // this.scrollFn();
    this.getCarData();
    this.getVideo();
    // console.log(this.bourn);
  },
  updated() {
  },
  destroyed() {
    // 清除机器人数据
    clearInterval(this.robotIntelTop);
    clearInterval(this.robotIntelBottom);
  },
  watch: {
    searchFromData: {
      immediate: true,
      deep: true,
      handler(newValue) {
        /**
         * 1. 判断是否有搜索条件，有走下边的计算，没有，位置全部归0。
         * 2. 通过搜索条件给的参数，来判断对应的left值，如果left不等于-100%的话，继续走，否则全部归0。
         * 3. 判断left值是不是小于屏幕宽度，如果小于，页面就不做位移，如果大于，就位移left长度减去页面的一半长度。
         * 4. 下边的值是通过滚动条的计算方式反推的
         */
        if (newValue) {
          let left = newValue.left
          let main_drawingBox = this.$refs.main_drawingBox,
            main_drawingContext = this.$refs.main_drawingContext,
            scrollBox = this.$refs.scrollBox,
            scrollBtn = this.$refs.scrollBtn;
          if (left != "-100%") {
            var baWidth = parseInt(
              scrollBox.offsetWidth - scrollBtn.offsetWidth
            );
            if (left - 0 > baWidth) {
              left = left - baWidth / 2;
              let tunnelLength = this.tunnelLeng - 0;
              let mainContextWidth = tunnelLength * this.devRatio;
              let main_drawingContextWidth = mainContextWidth + 440;
              var conWidth = parseInt(
                main_drawingContextWidth - main_drawingBox.offsetWidth
              );
              let x = (left * baWidth) / conWidth;
              scrollBtn.style.left = x + "px";
              main_drawingContext.style.marginLeft = -left + "px";
              let startSign = this.currentStakeNum;
              let startStr = startSign.replace("k", "");
              let startArr = startStr.split("+");
              this.scrollContextValue = this.formatConversion(
                parseInt((tunnelLength / baWidth) * x),
                startArr
              );
            } else {
              main_drawingContext.style.marginLeft = "0px";
              this.scrollContextValue = this.tunnelInfo.startSign;
              scrollBtn.style.left = "0px";
            }
          } else {
            main_drawingContext.style.marginLeft = "0px";
            this.scrollContextValue = this.tunnelInfo.startSign;
            scrollBtn.style.left = "0px";
            this.$message.info("该设备安装位置有误，未在本隧道展示");
          }
        } else if (newValue === undefined) {
          let main_drawingContext = this.$refs.main_drawingContext,
            scrollBtn = this.$refs.scrollBtn;
          if (main_drawingContext) {
            main_drawingContext.style.marginLeft = "0px";
            this.scrollContextValue = this.tunnelInfo.startSign;
            scrollBtn.style.left = "0px";
          }
          this.$message.info("没有检索到该编号设备");
        }
      },
    },
    devObj: {
      handler(to, from) {
      },
      deep: true
    },
    '$store.state.robot': {
      handler(to, from) {
        // clearInterval(this.timer)
        if(this.$store.getters.getEvent.robotType === 0) {
          this.maintainup = false;
          this.chargeup = false;
          if(this.$store.getters.getEvent.robotEvent !== 4) {
            if(this.$store.getters.getEvent.robotEvent == 1) {
              this.kmHu = 60;
              this.countSpeed(1);
            }else {
              this.kmHu = 30;
              this.countSpeed(1);
            }
            clearInterval(this.timer2);
            clearInterval(this.robotIntelTop);
            clearInterval(this.bournTimer);
            this.goBourn(this.$store.getters.getEvent.bourn,this.$store.getters.getEvent.robotEvent)
          }else {
            clearInterval(this.timer2);
            clearInterval(this.robotIntelTop);
            clearInterval(this.bournTimer);
            this.maintainup = false;
            this.chargeup = false;
            this.robotLeft();
          }
        }else {
          if(this.$store.getters.getEvent.robotEvent !== 4) {
            this.maintaindown = false;
            this.chargedown = false;
            if(this.$store.getters.getEvent.robotEvent == 1) {
              this.kmHd = 60;
              this.countSpeed();
            }else {
              this.kmHd = 30;
              this.countSpeed();
            }
            clearInterval(this.timer);
            clearInterval(this.robotIntelBottom);
            clearInterval(this.bournTimer2);
            this.goBourn2(this.$store.getters.getEvent.bourn,this.$store.getters.getEvent.robotEvent);
          }else {
            clearInterval(this.timer);
            clearInterval(this.robotIntelBottom);
            clearInterval(this.bournTimer2);
            this.maintaindown = false;
            this.chargedown = false;
            this.robotRight();
          }
        }
      },
      deep: true
    },
    '$store.state.autoEvent': {
      handler(to, from) {
        this.robot = to;
      },
      deep: true
    }
  },
  methods: {
      /* kmHu: 30,
      speedU: 0,
      kmHd: 30,
      speedD: 0, */
    sumSpeed(tunnelLeng) {
      this.countSpeed(false, tunnelLeng);
      this.countSpeed(1, tunnelLeng);
    },
    /* fnFireAccident(){
      var data = this.$store.getters.getTips
      this.$store.commit('setForm', data);
      this.warningClick()
    }, */
    warningClick() {
      this.$emit("warningModuleClick");
      // this.$emit("warningflagClick");
    },
    countSpeed(flag, length) {
      if(flag) {
        this.speedU = (this.tunnelLengthPx / (((length||this.tunnelLeng) / (this.kmHu * 1000)) * 60 * 60)) / 10;
      }else {
        this.speedD = (this.tunnelLengthPx / (((length || this.tunnelLeng) / (this.kmHd * 1000)) * 60 * 60)) / 10;
      }
    },
    getVideo() {
      videoInfoById().then((res) => {
        this.videoUrl = [res.data[0].url, res.data[1].url];
      });
    },
    getCarData() {
      vehicle()
      .then(res => {
        if(res.data.length!=0){
          this.carData = [res.data[0]];
          this.carData1 = [res.data[1]];
        } else {
          this.carData = [];
          this.carData1 = [];
        }

      })
      setTimeout(() => {
        this.getCarData();
      }, 60000)
    },
    test(item) {
      // this.scaleFlag = !this.scaleFlag;
    },
    showVideo(str) {
      this.$refs.videoShow.show(str)
    },
    testinfo(){
      console.log(this.devObj.informationBoard.upList)
    },
    www(item){
      console.log(item)
    },
    /**
     * 生成设备在地图的left位置
     * @param dev  设备数据
     * @param index  循环设备的索引
     *
     */
    returnLeftValue(dev, index) {
      // let start = this.twoStakeCompare(this.currentStakeNum, dev.equipPile);
      if(index === false) {
        return (dev - 1) * (this.tunnelLengthPx / 3)  + 'px';
      }else {
        return this.mToPx(dev.equipPile);
      }
      /* if (start || start === 0) {
        return start + dev.space * this.devRatio * index + "px";
      } else {
        return "-100%";
      } */
    },
    /***
     * 获取到设备在地图的起始位置
     * @param start 地图开始桩号
     * @param current 设备当前桩号
     */
    twoStakeCompare(start, current) {
      let startStr = start.replace("k", ""),
        currentStr = current.replace("k", "");
      let startArr = startStr.split("+"),
        currentArr = currentStr.split("+");
      let startK = startArr[0] - 0,
        startV = startArr[1] - 0 || 0;
      let currentK = currentArr[0] - 0,
        currentV = currentArr[1] - 0 || 0;
      // 如果设备桩号小于地图开始桩号，直接隐藏
      if (currentK < startK) {
        return false;
        // 如果设备桩号等于地图开始桩号，判断他们的后边值（+***），如果设备小于地图就返回，如果等于，那么返回0，否则设备的减去地图的
      } else if (currentK == startK) {
        if (currentV < startV) {
          return false;
        } else if (currentV == startV) {
          return 0;
        } else {
          return (currentV - startV) * this.devRatio;
        }
        // 如果设备桩号大于地图开始桩号，判断一下是不是紧邻的桩号，如果不是，直接隐藏，如果是，继续判断，桩号后边的值是否在100以内，如果是，就通过下边的表达式计算，否则直接隐藏
      } else {
        if (currentK === startK + 1) {
          if (1000 - startV < 100 || currentV - 100 < 0) {
            return (1000 - startV + currentV) * this.devRatio;
          } else {
            return false;
          }
        } else {
          return false;
        }
      }
    },
    /* 
      米数转像素
    */
    mToPx(str) {
      let arr = str.split("+");
      /* if(arr[0].toLocaleUpperCase().split('K')[1] == 36) {
        return 0 + arr[1] * this.devRatio;
      }else {
        return (1000 + +arr[1]) * this.devRatio;
      } */
      return ((arr[0].toUpperCase().split('K')[1] - 36) * 1000 + +arr[1]) * (this.tunnelLengthPx / this.tunnelLeng);
    },
    /**
     * 上边机器人移动
     */
    robotLeft() {
      let str = +this.robot.up.px || 0,
        robotWidth = 100,
        shadowleft = 0,
        robotTop = this.$refs.robotTop,
        robotItemUp = this.$refs.robotItemUp;
      let shadowDistance = robotWidth / (this.tunnelLengthPx / 10);
      this.robotIntelTop = setInterval(() => {
        this.autoCruiser2(this.robotIntelTop);
        if (this.robot.up.direction == 1) {
          str = str + this.speedU;
          shadowleft = shadowleft + shadowDistance;
        } else {
          str = str - this.speedU;
          shadowleft = shadowleft - shadowDistance;
        }
        if (str > this.tunnelLengthPx - 120) {
          this.robot.up.direction = 2;
        } else if (str <= 0) {
          this.robot.up.direction = 1;
        }
        if (robotItemUp) {
          this.topStr = str;
          robotItemUp.style.left = str + "px";
          this.robot.up.px = str;
        }
        /* if (robotTop) {
          robotTop.style.left = shadowleft + "px";
        } */
      }, 60);
    },
    // 上行执行自动控制区间巡逻
    autoCruiser2(timer) {
      if(this.$store.getters.getAuto.sectionA) {
        let obj = this.$store.getters.getAuto
        if((new Date()).getTime() > (new Date(obj.sectionA.date[0]).getTime())) {
          // console.log(obj.sectionA.date[0]);
          // console.log("到时间", (new Date()).getTime(), (new Date(obj.section.date[0]).getTime()), obj.section.date[0]);
          clearInterval(timer);
          this.goCruiser2();
        }
      }else {
        return;
      }
    },
    goCruiser2() {
      this.timer2 = setInterval(() => {
        let left = +this.robot.up.px;
        // console.log(left);
          // console.log(left - this.speedU);
        // console.log(left, this.mToPx(this.robot.sectionA.begin));
        let obj = this.$store.getters.getAuto
        if((new Date()).getTime() > (new Date(obj.sectionA.date[1]).getTime())) {
          delete obj['sectionA'];
          this.$store.commit('setAuto', obj)
          // console.log(obj.sectionA.date[0]);
          // console.log("到时间", (new Date()).getTime(), (new Date(obj.section.date[0]).getTime()), obj.section.date[0]);
          clearInterval(this.timer2);
          this.robotLeft();
        }
        if(!this.robot.up.start) {
          if(this.mToPx(this.$store.getters.getAuto.sectionA.begin) > left) {
            left += this.speedU;
            this.robot.up.px = left;
          }else {
            left -= this.speedU;
            this.robot.up.px = left;
          }
          if(Math.abs(this.mToPx(this.$store.getters.getAuto.sectionA.begin) - left) <= (this.speedU * 2)) {
            this.robot.up.start = 1;
          }
        }else {
          if(this.robot.up.direction === 1) {
            left += this.speedU;
            this.robot.up.px = left;
            // console.log(this.robot.up.px);
            if(this.mToPx(this.$store.getters.getAuto.sectionA.end) < left) {
              this.robot.up.direction = 2;
              clearInterval(this.timer2);
              setTimeout(() => {
                this.goCruiser2()
              }, this.$store.getters.getAuto.sectionA.waitTime);
            }
          }else {
            left -= this.speedU;
            this.robot.up.px = left;
            // console.log(this.$store.getters.getAuto.sectionA.begin);
            if(this.$store.getters.getAuto.sectionA && (this.mToPx(this.$store.getters.getAuto.sectionA.begin) > left)) {
              this.robot.up.direction = 1;
              clearInterval(this.timer2);
              setTimeout(() => {
                this.goCruiser2()
              }, this.$store.getters.getAuto.sectionA.waitTime);
            }
          }
        }
        this.$refs.robotItemUp.style.left = left + "px";
      }, 60)
    },
    // 执行自动控制区间巡逻
    autoCruiser(timer) {
      if(this.$store.getters.getAuto.section) {
        let obj = this.$store.getters.getAuto
        if((new Date()).getTime() > (new Date(obj.section.date[0]).getTime())) {
          console.log("到时间", (new Date()).getTime(), (new Date(obj.section.date[0]).getTime()), obj.section.date[0]);
          clearInterval(timer);
          this.goCruiser();
        }
      }else {
        return;
      }
    },
    goCruiser() {
      let obj = this.$store.getters.getAuto
      if((new Date()).getTime() > (new Date(obj.section.date[1]).getTime())) {
        delete obj['sectionA'];
        this.$store.commit('setAuto', obj)
        // console.log(obj.sectionA.date[0]);
        // console.log("到时间", (new Date()).getTime(), (new Date(obj.section.date[0]).getTime()), obj.section.date[0]);
        
        clearInterval(this.timer);
        this.robotRight();
      }
      this.timer = setInterval(() => {
        let left = +this.robot.down.px;
        // console.log(left);
          // console.log(left - this.speedD);
          // console.log((this.tunnelLengthPx - left - 100), left, this.mToPx(this.robot.section.begin));
        if(!this.robot.down.start) {
          if(this.mToPx(this.$store.getters.getAuto.section.begin) > (this.tunnelLengthPx - left - 100)) {
            left -= this.speedD;
            this.robot.down.px = left;
          }else {
            left += this.speedD;
            this.robot.down.px = left;
          }
          if(Math.abs(this.mToPx(this.$store.getters.getAuto.section.begin) - (this.tunnelLengthPx - left - 100)) <= (this.speedD * 2)) {
            this.robot.down.start = 1;
          }
        }else {
          if(this.robot.down.direction === 1) {
            left -= this.speedD;
            this.robot.down.px = left;
            // console.log(this.robot.down.px);
            if(this.mToPx(this.$store.getters.getAuto.section.end) < (this.tunnelLengthPx - left - 100)) {
              this.robot.down.direction = 2;
              clearInterval(this.timer);
              setTimeout(() => {
                this.goCruiser()
              }, this.$store.getters.getAuto.section.waitTime);
            }
          }else {
            left += this.speedD;
            this.robot.down.px = left;
            // console.log(this.robot.down.px);
            if(this.mToPx(this.$store.getters.getAuto.section.begin) > (this.tunnelLengthPx - left - 100)) {
              this.robot.down.direction = 1;
              clearInterval(this.timer);
              setTimeout(() => {
                this.goCruiser()
              }, this.$store.getters.getAuto.section.waitTime);
            }
          }
        }
        this.$refs.robotItemBottom.style.left = this.tunnelLengthPx - left - 100 + "px";
      }, 60)
    },
    /**
     * 下边机器人移动
     */
    robotRight() {
      let str = this.robot.down.px || 0,
        robotWidth = 100,
        shadowleft = 0,
        robotItemBottom = this.$refs.robotItemBottom,
        robotBottom = this.$refs.robotBottom;
      let shadowDistance = robotWidth / ((this.tunnelLengthPx) / 10);
      this.robotIntelBottom = setInterval(() => {
        this.autoCruiser(this.robotIntelBottom);
        if (this.robot.down.direction === 1) {
          // console.log('计时器');
          str = str + this.speedD;
          shadowleft = shadowleft + shadowDistance;
        } else {
          str = str - this.speedD;
          shadowleft = shadowleft - shadowDistance;
        }
        if (str > this.tunnelLengthPx - 100) {
          this.robot.down.direction = 2;
        } else if (str <= 0) {
          this.robot.down.direction = 1;
        }
        // console.log(str);
        if (robotItemBottom) {
          this.botStr = str;
          robotItemBottom.style.left = this.tunnelLengthPx - str - robotWidth + "px";
          this.robot.down.px = str;
        }
        if (robotBottom) {
          robotBottom.style.left = shadowleft + "px";
        }
        // console.log(this.$refs);
        let k = 'K' + Math.floor((((((this.tunnelLeng * this.devRatio) - this.botStr) / this.devRatio) / 1000) + 36));
        let num = parseInt((((this.tunnelLeng * this.devRatio) - this.botStr) / this.devRatio) % 1000);
        let num2 = parseInt((this.topStr / this.devRatio) % 1000);
        num = num < 100 ? (num < 10 ? '00' + num : '0' + num) : num;
        num2 = num2 < 100 ? (num2 < 10 ? '00' + num2 : '0' + num2) : num2;
        this.$refs.robotInfo.setData({
          botStr: k + '+' + num,
          topStr: 'K' + Math.floor((((this.topStr / this.devRatio) / 1000) + 36)) + '+' + num2
        })
      }, 60);
    },
    /* 
      刷新时的钩子函数
    */
    saveRobotInfo() {
      /* if(localStorage.robotInfo) {
        let obj = this.$store.getters.getAuto;
        obj.up = this.robot.up;
        obj.down = this.robot.down; */
        /* this.robot.section = null;
        this.robot.sectionA = null; */
        clearInterval(this.timer)
        clearInterval(this.timer2)
        clearInterval(this.robotIntelTop)
        clearInterval(this.robotIntelBottom)
        // localStorage.robotInfo = JSON.stringify(this.robot);
      /* }else {
        localStorage.robotInfo = JSON.stringify(this.robot);
      } */
    },
    /**
     * 滚动条实现
     */
    scrollFn() {
      let main_drawingBox = this.$refs.main_drawingBox,
        main_drawingContext = this.$refs.main_drawingContext,
        mainContext = this.$refs.mainContext,
        scrollBox = this.$refs.scrollBox,
        scrollBtn = this.$refs.scrollBtn,
        that = this;
      let tunnelLength = this.tunnelLeng;
      let mainContextWidth = tunnelLength * this.devRatio;
      let main_drawingContextWidth = mainContextWidth + 440;
      main_drawingContext.style.width = main_drawingContextWidth + "px";
      mainContext.style.width = mainContextWidth + "px";
      let width =
        parseInt(main_drawingBox.offsetWidth * scrollBox.offsetWidth) /
        parseInt(main_drawingContextWidth);
      scrollBtn.style.width = width + "px";
      let sumArr = []
      scrollBtn.onmousedown = function (e) {
        var cX = parseInt(e.clientX);
        var oW = parseInt(this.offsetLeft);
        var spaceX = cX - oW;
        let startSign = that.startsign;
        let startStr = startSign.replace("k", "");
        let startArr = startStr.split("+");
        document.onmousemove = function (e) {
          //鼠标移动时，不选中文字
          window.getSelection
            ? window.getSelection().removeAllRanges()
            : document.selection.empty();
          var nowX = e.clientX;
          var x = nowX - spaceX;
          var baWidth = parseInt(scrollBox.offsetWidth - scrollBtn.offsetWidth);
          x = x < 0 ? (x = 0) : x;
          x = x > baWidth ? baWidth : x;
          var conWidth = parseInt(
            main_drawingContextWidth - main_drawingBox.offsetWidth
          );
          // that.stepWatch(x);
          // this.allLeft = x
          var conX = (x * conWidth) / baWidth;
          /* that.scrollContextValue = that.formatConversion(
            parseInt(((that.tunnelLeng - 0) / baWidth) * x),
            startArr
          ); */
          sumArr[0] = parseInt(((that.tunnelLeng - 0) / baWidth) * x);
          sumArr[1] = startArr;
          scrollBtn.style.left = x + "px";
          main_drawingContext.style.marginLeft = -conX + "px";
        };
      };
      document.onmouseup = function () {
        document.onmousemove = null;
        that.scrollContextValue = that.formatConversion(sumArr[0], sumArr[1]);
      }; 
    },
    // 判断是否进入下一步骤
    /* stepWatch(left) {
      let arr = [Math.floor(this.$store.state.stepArr[1].lowerPile / 100), Math.floor(this.$store.state.stepArr[0].upperPile /100), Math.floor(this.$store.state.stepArr[1].upperPile /100)]
      let 
      leftIndex = Math.floor(left / 100) + 2,
      index = arr.indexOf(leftIndex),
      nowStep = this.$store.state.step;
      // console.log(arr, leftIndex, nowStep,index, index != -1 && nowStep != index);
      if(index != -1 && nowStep != index) {
        this.$store.commit('setStep', index)
        this.$parent.getTunnelInfo();
      }
    }, */
    /**
     * 获取当前位置桩号
     * @param rice  当前在多少米
     * @param startArr 隧道开始位置转换后数组
     */
    formatConversion(rice, startArr) {
      if(!rice) {
        return
      }
     /*  let startK = startArr[0] - 0,
        startV = startArr[1] - 0 || 0;

      let returnK = 0,
        returnV = 0;

      let surplus = 1000 - startV;
      let riceSurplus = 0;

      if (rice >= surplus) {
        returnK = startK + 1;
        riceSurplus = rice - surplus;
        if (riceSurplus === 1000) {
          returnK = returnK + 1;
        }
        let len = Math.ceil(riceSurplus / 1000);
        for (let index = 0; index < len; index++) {
          if (index === len - 1) {
            returnV = riceSurplus % 1000;
          } else {
            returnK = startK + 1;
          }
        }
      } else {
        returnK = startK;
        returnV = rice + startV;
      } */
      let after = rice % 1000 + '';
      if ((after + "").length === 1) {
        after = "00" + after;
      } else if ((after + "").length === 2) {
        after = "0" + after;
      }
      // console.log((right && maxRight) || (left && maxLeft));
      // 当前米加100m的屏幕再加40m的下一页(下一页上一页都预加载了50m)大于当前已加载的右边界时
      // let right = (rice + 100 + 40) > this.$store.getters.getStartR;
      // 当前已加载的数据小于右边边界
      // let maxRight = this.$store.getters.getStartR < this.tunnelLeng ;
      // 当前米减40m的上一页(下一页上一页都预加载了50m)大于当前已加载的左边界时
      // let left = (rice - 40) < this.$store.getters.getStartL;
      // 当前已加载的数据大于左边边界
      // let maxLeft = this.$store.getters.getStartL > 0;
      // console.log((rice + 100 + 40), this.$store.getters.getStartR);
      // if((right && maxRight) || (left && maxLeft)) {
        // let arr = this.startAndEnd(rice)
        // this.$parent.getTunnelInfo({
          // lowerPile: arr[0],
          // upperPile: arr[1]
        // })
      // }
      return `k${+startArr[0] + Math.floor(rice / 1000)}+${after}`;
    },
    startAndEnd(num) {
      function fn(num1) {
        let after = num1 % 1000 + '';
        if ((after + "").length === 1) {
          after = "00" + after;
        } else if ((after + "").length === 2) {
          after = "0" + after;
        }
        return 'K' + (36 + Math.floor(num / 1000)) + '+' + after;
      }
      let arr =[num - 50, num + 150]
      
      return [fn(arr[0]), fn(arr[1])]
    },
    /**
     * 鼠标移出隐藏popper信息
     */
    leaver(num,index){
      if(num === 1) this.$refs.tooltip1[index].showPopper = false;
      else if(num === 2) this.$refs.tooltip2[index].showPopper = false;
      else if(num === 3) this.$refs.tooltip3[index].showPopper = false;
      else if(num === 4) this.$refs.tooltip4[index].showPopper = false;
      else if(num === 5) this.$refs.tooltip5[index].showPopper = false;
      else if(num === 6) this.$refs.tooltip6[index].showPopper = false;
      else if(num === 7) this.$refs.tooltip7[index].showPopper = false;
      else if(num === 8) this.$refs.tooltip8[index].showPopper = false;
    },
    /**
     * 临时停车位置定位实现
     * @param index 对应的停车位在同一行上的索引
     */
    parkingLeft(index) {
      return (this.tunnelLengthPx - 246) / 2 + 'px';
    },
    /**
     * 风机控制操作
     * @param item  点击的设备数据
     */
    fanClick(item) {
      this.$emit("fanClick", item);
    },
    /**
     * 风机控制操作
     * @param item  点击的设备数据
     */
    approachLightingClick(item) {
      this.$emit("approachLightingClick", item);
    },
    /**
     * 灯控制操作
     * @param item  点击的设备数据
     */
    lightingClick(item) {
      this.$emit("lightingClick", item);
    },
    /**
     * 车道指示灯操作
     * @param item  点击的设备数据
     */
    laneIndicatorClick(item) {
      this.$emit("laneIndicatorClick", item);
    },
    /**
     * 机器人操作
     * @param item 点击的机器人数据
     */
    robotClick(item) {
      this.$emit("robotClick", item);
    },
    // 随机数
    /* rn(max, min) {
      return Math.round(Math.random() * (max - min) + min);
    } */
    /* sumLeft(num) {
      return Math.abs(this.allLeft - num) < 1920 ? true : false;
    } */
    goBourn(bourn, robotEvent) {
      delete this.robot['sectionA'];
      this.$store.commit('setAuto', this.robot);
      var mToPx
      this.bournTimer = setInterval(() => {
        mToPx = this.mToPx(bourn) -50;
        if(mToPx < this.robot.up.px) {
          this.robot.up.px -= this.speedU;
        }else {
          this.robot.up.px += this.speedU;
        }
        if((Math.abs(mToPx  - this.robot.up.px)) <= (this.speedU * 2)) {
          clearInterval(this.bournTimer);
          if(robotEvent == 5){
            this.maintainup = true;
            this.chargeup = false;
          } else if(robotEvent == 3){
            this.chargeup = true;
            this.maintainup = false;
          } else {
            this.maintainup = false;
            this.chargeup = false;
          }
        }
        this.$refs.robotItemUp.style.left = this.robot.up.px + 'px';
      }, 60) 
    },
    goBourn2(bourn, robotEvent) {
      delete this.robot['section'];
      this.$store.commit('setAuto', this.robot);
      var mToPx;
      this.bournTimer2 = setInterval(() => {
        mToPx = this.mToPx(bourn);
        if(mToPx < (this.tunnelLengthPx - this.robot.down.px - 50)) {
          this.robot.down.px += this.speedD;
          if(Math.abs(mToPx - (this.tunnelLengthPx - this.robot.down.px - 50)) <= (this.speedD * 2)) {
            clearInterval(this.bournTimer2);
            if(robotEvent == 5){
              this.maintaindown = true;
              this.chargedown = false;
            } else if(robotEvent == 3){
              this.chargedown = true;
              this.maintaindown = false;
            } else {
              this.maintaindown = false;
              this.chargedown = false;
            }
          }
        }else {
          this.robot.down.px -= this.speedD;
          if(Math.abs(mToPx - (this.tunnelLengthPx - this.robot.down.px - 50 )) <= (this.speedD * 2)) {
            clearInterval(this.bournTimer2);
            if(robotEvent == 5){
              this.maintaindown = true;
              this.chargedown = false;
            } else if(robotEvent == 3){
              this.chargedown = true;
              this.maintaindown = false;
            } else {
              this.maintaindown = false;
              this.chargedown = false;
            }
          }
        }
        
        this.$refs.robotItemBottom.style.left = this.tunnelLengthPx - this.robot.down.px - 100 + 'px';
      }, 60) 
    },
    showModal() {
      this.$store.commit('setForm', this.$store.getters.getTips);
      this.$parent.warningModuleClick();
    },
  },
  created() {
    // if(localStorage.robotInfo) Object.assign(this.robot, this.$store.getters.getAuto);
    // window.addEventListener('beforeunload', e => this.saveRobotInfo(e));
    /* document.onkeydown = (e) => {
      if(e.key.toUpperCase == "K") {
        delete this.robot.down['start'];
      }
    } */
  }
};
</script>
<style lang="scss">
.el-popover.pop-div-me {
  background: #191d21;
  border: #191d21;
  height: 150px;
  .el-popover__title{
    color:#808080;
  }
  .popper__arrow::after {
    border-bottom-color: #191d21;
  }
  .popper__arrow {
    border-bottom-color: #191d21;
  }
}
</style>
<style scoped>
/deep/ .el-card__body {
  padding: 10px 5px;
}
/deep/ .el-carousel__indicators--horizontal{
  display: none;
}
/deep/ .el-carousel__item.is-animating{
  -webkit-animation-timing-function: linear;
  animation-timing-function: linear;
  transition-timing-function: linear;
  -moz-transition-timing-function: linear; /* Firefox 4 */
  -webkit-transition-timing-function: linear; /* Safari 和 Chrome */
  -o-transition-timing-function: linear; /* Opera */
  /* animation: transform 15s linear infinite; */
  -webkit-transition: -webkit-transform 6s linear infinite;
  transition: -webkit-transform 6s linear infinite;
  transition: transform 6s linear infinite;
  transition: transform 6s linear infinite, -webkit-transform 6s linear infinite;
  transition: transform 6s linear infinite,-webkit-transform 6s linear infinite;
}

</style>
<style lang="scss">
.devTooltip {
  padding: 1px;
  background-color: #2B3137 !important;
  .el-card {
    color:#fff;
    background-color: #191D21;
  }
  .el-table th, .el-table tr{
    background-color: #191D21!important;
    color: #fff;
  }
  .el-table th:hover, .el-table tr:hover{
    background-color:#545454 !important;
    color: #fff;
  }
}
.el-table--enable-row-hover .el-table__body tr:hover>td{
  background-color: #545454 !important;
}
</style>
<style lang="scss" scoped>
// 样式太多了，就把它单独提出去了
@import "./assets/scss/drawingBoard.scss";
.passagewayItem {
  position: absolute;
}
#cameraUpBox {
  z-index: 999;
  top: 0;
}
#cameraDownBox {
  z-index: 999;
  top: 0;
}

.iconCommon {
  display: block;
  width: 25px;
  height: 25px;
  margin: 0 auto;
  background-image: url(./assets/img/titleIcon.png);
  background-repeat: no-repeat;
  &.telephone {
    background-position: -35px 0px;
  }
}
.flashFont {
    animation: boxShadow 1s infinite;
    // transition: .3s;
  }
@keyframes lb {
	from {
		transform: translateX(0);
	}
	to {
		transform: translateX(-100%); /*只需要移动6张图的距离，这时显示的是最后一张图，而最后一张图和第一张图是一样的，造成一种循环的假象*/
	}
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
.informationContext {
  animation: lb 10s linear infinite;
}
/* video::-webkit-media-controls{
  display: inline-block;
} */
// .informationContext:hover{    /*
// 	*/
// 	animation-play-state: paused;
// }
</style>