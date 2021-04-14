<template>
  <div class="data-wrapper">
    <!-- 模板1 -->
    <div class="chart-wrapper" v-show="value === '收费金额'">
      <div class="salary-content">
        <p>
          <span>
            <i class="digital color-two">{{allData && allData.fee.current}}</i>
            <!-- <i v-for="(digital, ind) in `${allData && allData.fee.current}`" :key="ind" :class="['iconfont', 'color-one', `icon-shuzi${digital}`]"></i> -->
            <a>实时 / 元</a>
          </span>
        </p>
        <p>
          <span>
            <i class="digital color-two">{{allData && allData.fee.sum}}</i>
            <a>累计 / 元</a>
          </span>
        </p>
      </div>
      <div class="charts">
        <div class="left-part" ref="leftChart"></div>
        <div class="right-part" ref="rightChart"></div>
      </div>
    </div>
    <!-- 模板2 -->
    <div class="chart-wrapper" v-show="value === '车流量'">
      <div class="salary-content">
        <p>
          <span>
            <i class="digital color-two">{{allData && allData.trafficFlow.realTimeNum}}</i>
            <a>实时 / 辆</a>
          </span>
        </p>
        <p>
          <span>
            <i class="digital color-two">{{allData && allData.trafficFlow.realTimeNum}}</i>
            <a>累计 / 辆</a>
          </span>
        </p>
      </div>
      <div class="traffic-chart" ref="trafficFlowChart"></div>
    </div>
    <!-- 模板3 -->
    <div class="chart-wrapper" v-show="value === '数据传输情况'">
      <div class="salary-content">
        <p>
          <span>
            <i class="digital color-three">{{allData && allData.dataTransmission.record}}</i>
            <a>站省传输积压记录数</a>
          </span>
        </p>
        <p>
          <span style="width: 155px; margin-left: -30px">
            <i class="digital color-three">{{allData && allData.dataTransmission.NotUploadedRecord}}</i>
            <a>站省传输预期未上传记录数</a>
          </span>
        </p>
      </div>
      <div class="traffic-chart" ref="transferChart"></div>
    </div>
    <!-- 模板4 -->
    <div class="chart-wrapper" v-show="value === '设备异常事件'">
      <div class="header">
        <span v-for="(list, ind) in title" :key="ind">{{list}}</span>
      </div>
      <vue-seamless-scroll :data="allData && allData.equipException" :class-option="classOption" class="warp" ref="seamlessScroll" v-if="value === '设备异常事件'">
        <ul class="item">
          <li v-for="(item, index) in allData.equipException" :key="index">
            <span class="title" v-text="item.index"></span>
            <el-tooltip class="item" effect="dark" :content="item.place" placement="top-start">
              <span v-text="item.place"></span>
            </el-tooltip>
            <el-tooltip class="item" effect="dark" :content="item.equipName" placement="top-start">
              <span v-text="item.equipName"></span>
            </el-tooltip>
            <el-tooltip class="item" effect="dark" :content="item.eventType" placement="top-start">
              <span v-text="item.eventType"></span>
            </el-tooltip>
          </li>
        </ul>
      </vue-seamless-scroll>
    </div>
    <!-- 模板5 -->
    <div class="chart-wrapper" v-show="value === '设备维护统计'">
      <div class="maintain-chart" ref="maintainChart" v-show="value === '设备维护统计'"></div>
      <div class="maintain-legend">
        <p>设备完好率</p>
        <p>设备总数: {{allData && allData.EquipmentMaintenance.value[0]}}</p>
      </div>
      <div class="process-chart" ref="processChart" v-show="value === '设备维护统计'"></div>
      <ul class="list-content">
        <li v-for="(list, ind) in allData && allData.EquipmentMaintenance.list" :key="ind">
          <span>{{list.name}}</span>
          <span>{{list.value}}</span>
        </li>
      </ul>
    </div>
    <!-- 模板6 -->
    <div class="chart-wrapper" v-show="value === '路网概况'">
      <div class="radar-chart" ref="radarChart"></div>
    </div>
    <!-- 模板7 -->
    <div class="chart-wrapper" v-show="value === '门架交易/牌识成功率统计'">
      <div class="process-bar">
        <div class="wrapper1">
          <span>门架交易成功率</span><el-progress :percentage="allData && allData.gantryDeal.gantryDealSuccess" :text-inside="true" :color="customColor" :stroke-width="12" stroke-linecap="square"></el-progress>
        </div>
        <div class="wrapper2">
          <span>门架牌识成功率</span><el-progress :percentage="allData && allData.gantryDeal.doorCardrecognitionSuccess" :text-inside="true" :color="customColorMethod" :stroke-width="12" class="yel"></el-progress>
        </div>
      </div>
      <div class="gantry-chart" ref="gantryChart"></div>
      <p class="gantry-title">新安所站成功率统计</p>
    </div>
    <!-- 模板8 -->
    <div class="chart-wrapper" v-if="value === '施工和管制'">
      <div class="construction-wrapper">
        <div>
          <p><span class="iconfont icon-ziyuanxhdpi"></span><span>{{allData && allData.constructionRoad.detail.constructionRoad}}</span></p>
          <p>{{allData && allData.constructionRoad.detail.message}}</p>
          <p><span>桩号：{{allData && allData.constructionRoad.detail.stumb}}</span><span>施工时间：{{allData && allData.constructionRoad.detail.time}}</span></p>
        </div>
        <div>
          <div class="construction-header">
            <span v-for="(list, ind) in constructionTitle" :key="ind">{{list}}</span>
          </div>
          <!-- <vue-seamless-scroll :data="constructionData" :class-option="classOption" class="wrap" ref="seamlessScroll">
            <ul class="item">
              <li v-for="(list, index) in constructionData" :key="index">
                <span class="title" v-text="index + 1"></span>
                <el-tooltip class="item" effect="dark" :content="list.local" placement="top-start">
                  <span v-text="list.local"></span>
                </el-tooltip>
                <el-tooltip class="item" effect="dark" :content="list.percent" placement="top-start">
                  <span v-text="list.percent"></span>
                </el-tooltip>
              </li>
            </ul>
          </vue-seamless-scroll> -->
          <vue-seamless-scroll :data="allData && allData.constructionRoad.list" :class-option="classOption" class="warp" ref="seamlessScroll">
            <ul class="item">
              <li v-for="(item, index) in allData.constructionRoad.list" :key="index">
                <span class="title" v-text="item.index"></span>
                <el-tooltip class="item" effect="dark" :content="item.constructionRoad" placement="top-start">
                  <span v-text="item.constructionRoad"></span>
                </el-tooltip>
                <el-tooltip class="item" effect="dark" :content="item.finishPercent" placement="top-start">
                  <span v-text="item.finishPercent"></span>
                </el-tooltip>
              </li>
            </ul>
          </vue-seamless-scroll>
        </div>
      </div>
    </div>
    <!-- 模板9 -->
    <div class="chart-wrapper" v-if="value === '高速监控画面'">
      <ul class="monitor">
        <li class="tunnel-wrapper">
          <el-select v-model="tunnelName" filterable placeholder="请选择">
            <el-option
              v-for="item in tunnelOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </li>
        <li class="monitor-wapper">
          <!-- <div > -->
            <!-- <div id="dplayer" class="dplayer-wrapper" v-for="item in 4" :key="item"></div> -->
          <!-- </div> -->
          <div v-for="(item, ind) in videoData" :key="ind" @click="handleZoomIn(ind)">
            <video muted width="99%" height="105" autoplay="autoplay" loop :src="item">
            </video>
          </div>
        </li>
      </ul>
    </div>
    <!-- 模板10 -->
    <div class="chart-wrapper" v-if="value === '预警因素分析'">
      <div class="warning-wrapper">
        <section class="part-one">
          <p v-for="(list, ind) in allData && allData.EarlyWarningFactors.warningData" :key="ind">
            <span><img :src="warningImg[ind]" alt=""></span>
            <span><i>{{list.title}}</i><i>{{list.data.map(item => item.value).reduce((cur, pre) => cur + pre)}}</i></span>
          </p>
        </section>
        <section class="part-two">
          <div v-for="(list, ind) in allData && allData.EarlyWarningFactors.warningData" :key="ind">
            <p class="title">{{list.title}}</p>
            <p v-for="(item, ind) in list.data" :key="ind">
              <span>{{item.name}}：</span>
              <span>{{item.value}}</span>
            </p>
          </div>
        </section>
      </div>
    </div>
    <!-- 模板11 -->
    <div class="chart-wrapper" v-if="value === '道路基本信息'">
      <div class="basic-wrapper">
        <section class="basic-info">
          <div v-for="(list, ind) in allData && allData.BasicRoadInformation.basicData" :key="ind">
            <p class="img" :style="{'background': `url(${basicImg[ind]})`, 'background-size': '100% 100%'}"><span class="num">{{list.data.map(item => item.value).reduce((cur, pre) => cur + pre)}}</span></p>
            <p class="title">{{list.title}}</p>
            <p v-for="(item, ind) in list.data" :key="ind">
              <span>{{item.name}}：</span>
              <span>{{item.value}} 座</span>
            </p>
          </div>
        </section>
      </div>
    </div>
    <!-- 模板12 -->
    <div class="chart-wrapper" v-if="value === '机电设备故障排名'">
      <div class="equipment-rank-wrapper">
        <ul class="equipment-info">
          <li v-for="(list, ind) in allData && allData.MechanicalndElectricalEquipmentFailureRanking.equipmentRankData" :key="ind">
            <span :class="{active: ind < 3}"><i>{{ind + 1}}</i></span>
            <span>{{list.title}}</span>
            <span>{{list.value}} 次</span>
          </li>
        </ul>
      </div>
    </div>
    <!-- 模板13 -->
    <div class="chart-wrapper" v-if="value === '交通设施风险排名'">
      <div class="equipment-rank-wrapper">
        <ul class="equipment-info">
          <li v-for="(list, ind) in allData && allData.RiskRankingOfTransportationFacilities.trafficDangerRankData" :key="ind">
            <span :class="{active: ind < 3}"><i>{{ind + 1}}</i></span>
            <span>{{list.title}}</span>
            <span>{{list.value}} 次</span>
          </li>
        </ul>
      </div>
    </div>
    <!-- 模板14 -->
    <div class="chart-wrapper" v-show="value === '车流热点区间实时排名'">
      <div class="hot-rank-wrapper">
        <ul class="hot-info">
          <li v-for="(list, ind) in allData && allData.RealTimeRankingOfTrafficHotSpots.trafficHotRankData.type" :key="ind">
            <span :class="{active: ind < 3}"><i>{{ind + 1}}</i></span>
            <el-tooltip class="item" effect="dark" :content="list" placement="top-start">
              <span>{{list}}</span>
            </el-tooltip>
          </li>
        </ul>
        <div class="hot-chart" ref="hotChart"></div>
      </div>
    </div>
    <!-- 模板15 -->
    <div class="chart-wrapper" v-show="value === '机电设备及交通设施监控'">
      <div class="status-wrapper">
        <div class="status-content">
          <div><img src="../../../static/image/shield.png" alt=""></div>
          <div>
            <p v-for="(item, ind) in allData && allData.equipmentAndTrafficRealTimeMonitoring.dataArray" :key="ind">
              <span><img :src="monitorImg[ind - 1]" v-if="ind > 0" alt=""></span>
              <span>
                <i>{{item.value}}</i>
                <i>{{item.name}}</i>
              </span>
            </p>
          </div>
        </div>
        <div class="status-chart" ref="statusChart"></div>
      </div>
    </div>
    <!-- 模板16 -->
    <div class="chart-wrapper" v-show="value === '道路安全'">
      <div class="road-safe-wrapper">
        <div class="road-safe-content">
          <p class="title"><span><i class="digital">{{allData && allData.fee.current}}</i></span><span>累计交通事故起数</span></p>
          <p class="tem-content" v-for="(item, ind) in allData && allData.roadsafe.data" :key="ind"><span><img :src="roadSafeImg[ind]" alt=""></span><span><i :style="{color: roadSafeColor[ind]}">{{item.value}}</i><i>{{item.name}}</i></span></p>
        </div>
      </div>
    </div>
    <!-- 模板17 -->
    <div class="chart-wrapper" v-show="value === '投资收入业务成本及稽查'">
      <div class="invert-cost-check">
        <div>
          <p v-for="(item, ind) in allData && allData.InvestmentIncomeBusinessCostAndAudit.array2" :key="ind">
            <span><i>{{item.title}}</i><i>{{item.value}} 万元</i></span>
            <span v-if="item.title === 'MTC' || item.title === '累计主营业务成本' || item.title === 'MTC'" :style="{'background': `url(${checkImg[item.title]})`, 'background-size': '100% 100%'}">
            </span>
          </p>
        </div>
        <div>
          <img src="../../../static/image/car.png" alt="">
          <p v-for="(item, ind) in allData && allData.InvestmentIncomeBusinessCostAndAudit.array3" :key="ind">
            <span>{{item.title}}</span>
            <span>{{item.value}} <i>{{ind === 0 ? '元' : '辆'}}</i></span>
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
import vueSeamlessScroll from 'vue-seamless-scroll';
import DPlayer from 'dplayer';
import eventBus from '../../common/Bus';
// import echarts from "echarts";
var echarts = require('echarts/lib/echarts');
// 引入柱状图
require('echarts/lib/chart/bar');
// 引入折线图
require('echarts/lib/chart/line');
// 引入饼图
require('echarts/lib/chart/pie');
require('echarts/lib/chart/Gauge');
require('echarts/lib/chart/radar');
require('echarts/lib/chart/Boxplot');
// 引入提示框和标题组件
require('echarts/lib/component/tooltip');
require('echarts/lib/component/title');
require('echarts/lib/component/legend');
// require('echarts/lib/component/Graphic');
export default {
  data () {
    return {
      title: ['序号', '地点', '设备名称', '事件类型'], // 设备异常事件title
      constructionTitle: ['序号', '施工路段', '完成进度'], // 施工和管制title
      classOption: {
        hoverStop: true,
        step: 0.5
      },
      content: [
        {title: '收费系统', value: 102},
        {title: '后备电源系统', value: 112},
        {title: '监控系统', value: 62},
        {title: '隧道系统', value: 33},
        {title: '通信系统', value: 12},
        {title: '通风系统', value: 19},
        {title: '照明系统', value: 20},
        {title: '供配电系统', value: 362},
        {title: '消防与救援系统', value: 122},
        {title: '交安系统', value: 119},
        {title: '防雷接地系统', value: 11}
      ],
      percentageA: 20,
      percentageB: 60,
      customColor: 'rgb(10, 160, 128)',
      customColorMethod: '#E6A23C',
      digitalData: '3234',
      tunnelName: '1',
      tunnelOptions: [{
        value: '1',
        label: '新街段交叉处'
      }, {
        value: '2',
        label: '新街段交叉处1'
      }, {
        value: '3',
        label: '新街段交叉处2'
      }, {
        value: '4',
        label: '新街段交叉处3'
      }, {
        value: '5',
        label: '新街段交叉处4'
      }],
      videoData: ['http://121.40.128.159:24001/video/1.mp4', 'http://121.40.128.159:24001/video/2.mp4', 'http://121.40.128.159:24001/video/3.mp4', 'http://121.40.128.159:24001/video/4.mp4'],
      basicImg: ['../../../static/image/green.png', '../../../static/image/hexagon.png', '../../../static/image/pur.png'],
      warningImg: ['../../../static/image/y.png', '../../../static/image/equ.png', '../../../static/image/g.png'],
      monitorImg: ['../../../static/image/equipment-blue.png', '../../../static/image/equipment-red.png'],
      checkImg: {'MTC': '../../../static/image/circle.png', '累计主营业务成本': '../../../static/image/green.png', 'ETC': '../../../static/image/circle.png'},
      roadSafeImg: ['../../../static/image/red.png', '../../../static/image/yellow.png', '../../../static/image/blue.png'],
      roadSafeColor: ['red', 'yellow', 'rgb(29, 203, 248)']
    };
  },
  props: ["value", 'allData'],
  mounted () {
    this.$nextTick(() => {
      this.drawLeftBar();
      this.drawRightBar();
      this.drawTrafficFlow();
      this.drawTransferChart();
      this.drawMaintainChart();
      this.drawProcessChart();
      this.drawRadarChart();
      this.drawGantryChart();
      this.drawHotRankChart();
      this.drawStatusChart();
    });
    // this.initMonitor();
  },
  components: {
    vueSeamlessScroll
  },
  methods: {
    initMonitor () {
      const dp = new DPlayer({
        container: document.getElementById('dplayer'),
        autoplay: true,
        video: {
          url: 'http://121.40.128.159:24001/video/2.mp4',
          type: 'mp4'
        }
      });
      dp.play();
    },
    handleZoomIn (ind) {
      console.log(ind);
      eventBus.$emit('url', {url: this.videoData[ind], flag: true});
    },
    drawLeftBar () {
      var chartsBar = echarts.init(this.$refs.leftChart);
      var img =
        "data:image/png;base64,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";
      var trafficWay = this.allData && this.allData.fee.trafficWay;
      var data = [];
      var color = [
        "orange",
        "#67C23A",
        "#006ced",
        "#ffe000",
        "#ffa800",
        "#ff5b00",
        "#ff3000"
      ];
      for (var i = 0; i < trafficWay.length; i++) {
        data.push(
          {
            value: trafficWay[i].value,
            name: trafficWay[i].name,
            itemStyle: {
              normal: {
                borderWidth: 5,
                shadowBlur: 20,
                borderColor: color[i],
                shadowColor: color[i]
              }
            }
          },
          {
            value: 2,
            name: "",
            itemStyle: {
              normal: {
                label: {
                  show: true
                },
                labelLine: {
                  show: true
                },
                color: "rgba(0, 0, 0, 0)",
                borderColor: "rgba(0, 0, 0, 0)",
                borderWidth: 0
              }
            }
          }
        );
      }
      var seriesOption = [
        {
          type: "pie",
          clockWise: false,
          radius: [23, 29],
          hoverAnimation: false,
          itemStyle: {
            normal: {
              label: {
                show: true,
                position: "outside",
                color: "#46bcee",
                formatter: function (params) {
                  var percent = 0;
                  var total = 0;
                  for (var i = 0; i < trafficWay.length; i++) {
                    total += trafficWay[i].value;
                  }
                  percent = ((params.value / total) * 100).toFixed(0);
                  if (params.name !== "") {
                    return params.name + "\n" + "\n" + percent + "%";
                  } else {
                    return "";
                  }
                },
                fontSize: 11
              },
              labelLine: {
                length: 12,
                length2: 15,
                show: true,
                color: "#00ffff"
              }
            }
          },
          data: data
        }
      ];
      var option = {
        color: color,
        // title: {
        //   text: '交通方式',
        //   top: '46%',
        //   textAlign: "center",
        //   left: "47%",
        //   textStyle: {
        //     color: '#fff',
        //     fontSize: 12,
        //     fontWeight: '400'
        //   }
        // },
        graphic: {
          elements: [
            {
              type: "image",
              z: 3,
              style: {
                image: img,
                width: 80,
                height: 80
              },
              left: "center",
              top: "center",
              position: [50, 50]
            }
          ]
        },
        tooltip: {
          show: true
        },
        legend: {
          icon: "aquare",
          orient: "vertical",
          // x: 'left',
          data: ["现金", "非现金", "第三方"],
          itemHeight: 8,
          itemWidth: 15,
          right: 70,
          bottom: 10,
          align: "left",
          textStyle: {
            color: "#46bcee",
            fontSize: 11
          },
          itemGap: 5
        },
        toolbox: {
          show: false
        },
        series: seriesOption
      };
      chartsBar.setOption(option);
    },
    drawRightBar () {
      var rightChart = echarts.init(this.$refs.rightChart);
      var option = {
        grid: [{
          height: 150,
          top: 75,
          left: 74,
          width: 75
        },
        {
          height: 150,
          width: 75,
          top: 75,
          left: 84
        }],
        xAxis: [{
          name: '万元',
          max: 5,
          splitNumber: 5,
          axisLine: {
            lineStyle: {
              color: '#46bcee'
            }
          },
          splitLine: {
            show: false
          },
          nameTextStyle: {
            fontSize: 11
          }
        },
        {
          gridIndex: 1,
          show: false,
          offset: 11,
          axisLine: {
            lineStyle: {
              color: '#46bcee'
            }
          }
        }],
        yAxis: [{
          data: this.allData && this.allData.fee.type,
          axisLabel: {
            show: true,
            fontSize: 11
          },
          nameTextStyle: {
            color: '#06D3CD',
            lineHeight: 20
          },
          axisLine: {
            lineStyle: {
              color: '#46bcee'
            }
          },
          axisTick: {
            show: false,
            lineStyle: {
              color: '#46bcee',
              fontSize: 12,
              fontFamily: 'PingFangSC',
              fontWeight: 300
            }
          }
        }],
        series: [{
          name: '累计',
          type: 'bar',
          itemStyle: {
            barBorderRadius: 1,
            color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [{
              offset: 0,
              color: 'rgba(0,222,215,0.2)'
            },
            {
              offset: 1,
              color: '#FFFC00'
            }
            ])
          },
          barWidth: 10,
          data: this.allData && this.allData.fee.data
        }]
      };
      rightChart.setOption(option);
    },
    drawTrafficFlow () {
      var TrafficFlowChart = echarts.init(this.$refs.trafficFlowChart);
      var option = {
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            lineStyle: {
              color: '#57617B'
            }
          }
        },
        legend: {
          icon: 'rect',
          itemWidth: 14,
          itemHeight: 5,
          itemGap: 13,
          data: ['客车', '货车', '轿车'],
          right: '4%',
          top: '10%',
          textStyle: {
            fontSize: 12,
            color: '#F1F1F3'
          }
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '0',
          containLabel: true
        },
        xAxis: [{
          type: 'category',
          boundaryGap: false,
          axisLine: {
            lineStyle: {
              color: '#fff'
            }
          },
          data: this.allData && this.allData.trafficFlow.timeList
        }],
        yAxis: [{
          type: 'value',
          name: '辆',
          axisTick: {
            show: false
          },
          axisLine: {
            lineStyle: {
              color: '#fff'
            }
          },
          axisLabel: {
            margin: 10,
            textStyle: {
              fontSize: 14
            }
          },
          splitLine: {
            lineStyle: {
              color: '#57617B'
            }
          }
        }],
        series: [{
          name: '客车',
          type: 'line',
          smooth: true,
          symbol: 'circle',
          symbolSize: 5,
          showSymbol: false,
          lineStyle: {
            normal: {
              width: 1
            }
          },
          areaStyle: {
            normal: {
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                offset: 0,
                color: 'rgba(137, 189, 27, 0.3)'
              }, {
                offset: 0.8,
                color: 'rgba(137, 189, 27, 0)'
              }], false),
              shadowColor: 'rgba(0, 0, 0, 0.1)',
              shadowBlur: 10
            }
          },
          itemStyle: {
            normal: {
              color: 'rgb(137,189,27)',
              borderColor: 'rgba(137,189,2,0.27)',
              borderWidth: 12
            }
          },
          data: this.allData && this.allData.trafficFlow.bus
        }, {
          name: '货车',
          type: 'line',
          smooth: true,
          symbol: 'circle',
          symbolSize: 5,
          showSymbol: false,
          lineStyle: {
            normal: {
              width: 1
            }
          },
          areaStyle: {
            normal: {
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                offset: 0,
                color: 'rgba(0, 136, 212, 0.3)'
              }, {
                offset: 0.8,
                color: 'rgba(0, 136, 212, 0)'
              }], false),
              shadowColor: 'rgba(0, 0, 0, 0.1)',
              shadowBlur: 10
            }
          },
          itemStyle: {
            normal: {
              color: 'rgb(0,136,212)',
              borderColor: 'rgba(0,136,212,0.2)',
              borderWidth: 12
            }
          },
          data: this.allData && this.allData.trafficFlow.truck
        }, {
          name: '轿车',
          type: 'line',
          smooth: true,
          symbol: 'circle',
          symbolSize: 5,
          showSymbol: false,
          lineStyle: {
            normal: {
              width: 1
            }
          },
          areaStyle: {
            normal: {
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                offset: 0,
                color: 'rgba(219, 50, 51, 0.3)'
              }, {
                offset: 0.8,
                color: 'rgba(219, 50, 51, 0)'
              }], false),
              shadowColor: 'rgba(0, 0, 0, 0.1)',
              shadowBlur: 10
            }
          },
          itemStyle: {
            normal: {
              color: 'rgb(219,50,51)',
              borderColor: 'rgba(219,50,51,0.2)',
              borderWidth: 12
            }
          },
          data: this.allData && this.allData.trafficFlow.car
        }]
      };
      TrafficFlowChart.setOption(option);
    },
    drawTransferChart () {
      var transferChart = echarts.init(this.$refs.transferChart);
      var dataStyle = {
        normal: {
          label: {
            show: false
          },
          labelLine: {
            show: false
          }
        }
      };
      var placeHolderStyle = {
        normal: {
          color: 'rgba(0,0,0,0)',
          label: {
            show: false
          },
          labelLine: {
            show: false
          }
        },
        emphasis: {
          color: 'rgba(0,0,0,0)'
        }
      };
      var data = this.allData && this.allData.dataTransmission.dataMax;
      var initNum = 25;
      var newData = [];
      var dataName = [];
      for (var m in data) {
        var arr = null;
        arr = {
          name: data[m]['name'],
          type: 'pie',
          clockWise: false,
          radius: [initNum, initNum = initNum + 10],
          center: ['30%', '60%'],
          itemStyle: dataStyle,
          textStyle: {
            fontFamily: "微软雅黑",
            fontSize: '12px',
            color: "#fff",
            fontWeight: 'normal'
          },
          data: [{
            value: data[m]['value'],
            name: data[m]['name'] + ' ' + data[m]['value'] + '%'
          },
          {
            value: 100 - data[m]['value'],
            name: 'invisible',
            itemStyle: placeHolderStyle,
            textStyle: {
              fontFamily: "微软雅黑",
              fontSize: '12px',
              color: "#fff",
              fontWeight: 'normal'
            }
          }
          ]
        };
        newData.push(arr);
        dataName.unshift(data[m]['name'] + ' ' + data[m]['value'] + '%');
      }
      var option = {
        tooltip: {
          show: true,
          formatter: "{a} <br/>{b} : {c} ({d}%)",
          textStyle: {
            color: "#fff",
            fontWeight: 'normal',
            fontFamily: '宋体'
          }
        },
        legend: {
          orient: 'vertical',
          left: '50%',
          top: 90,
          itemHeight: 9,
          itemWidth: 10,
          itemGap: 6,
          data: dataName,
          textStyle: {
            fontFamily: "微软雅黑",
            fontSize: 11,
            color: "#fff",
            fontWeight: 'normal'
          }
        },
        series: newData
      };
      transferChart.setOption(option);
    },
    drawMaintainChart () {
      var maintainChart = echarts.init(this.$refs.maintainChart);
      var option = {
        tooltip: {
          formatter: "{a} <br/>{b} : {c}%"
        },
        series: [{
          name: '',
          type: 'gauge',
          center: ['35%', '80%'], // 默认全局居中
          radius: '160%',
          axisLine: {
            show: false,
            lineStyle: { // 属性lineStyle控制线条样式
              color: [
                [((this.allData && this.allData.EquipmentMaintenance.value[1]) / (this.allData && this.allData.EquipmentMaintenance.value[0])), '#0193cf'],
                [1, 'rgba(1, 147, 207, 0.3)']
              ],
              width: 10
            }
          },
          splitLine: {
            show: false
          },
          axisTick: {
            show: false
          },
          axisLabel: {
            show: false
          },
          pointer: {
            show: false,
            length: '0',
            width: '0'
          },
          detail: {
            formatter: '{value}%',
            offsetCenter: [0, '2%'],
            fontSize: 20,
            fontWeight: 'bold'
          },
          data: [{
            value: (((this.allData && this.allData.EquipmentMaintenance.value[1]) / (this.allData && this.allData.EquipmentMaintenance.value[0])) * 100).toFixed(1),
            label: {
              textStyle: {
                fontSize: 12
              }
            }
          }]
        }]
      };
      maintainChart.setOption(option);
    },
    drawProcessChart () {
      var processChart = echarts.init(this.$refs.processChart);
      // import echarts from 'echarts'
      var charts = { // 按顺序排列从大到小
        cityList: this.allData && this.allData.EquipmentMaintenance.type,
        cityData: this.allData && this.allData.EquipmentMaintenance.value
      };
      var top10CityList = charts.cityList;
      var top10CityData = charts.cityData;
      var color = ['rgba(30,144,255', 'rgba(230,162,60', 'rgba(135,183,255', 'rgba(255,255,0'];
      let lineY = [];
      for (var i = 0; i < charts.cityList.length; i++) {
        var x = i;
        if (x > color.length - 1) {
          x = color.length - 1;
        }
        var data = {
          name: charts.cityList[i],
          color: color[x] + ')',
          value: top10CityData[i],
          itemStyle: {
            normal: {
              show: true,
              color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [{
                offset: 0,
                color: color[x] + ', .6)'
              }, {
                offset: 1,
                color: color[x] + ', .8)'
              }], false),
              barBorderRadius: 0
            },
            emphasis: {
              shadowBlur: 15,
              shadowColor: 'rgba(0, 0, 0, 0.1)'
            }
          }
        };
        lineY.push(data);
      }
      var option = {
        title: {
          show: false
        },
        tooltip: {
          trigger: 'item'
        },
        grid: {
          borderWidth: 0,
          top: '10%',
          left: '5%',
          right: '39%',
          bottom: '3%'
        },
        color: color,
        yAxis: [{
          type: 'category',
          axisTick: {
            show: false
          },
          axisLine: {
            show: false
          },
          axisLabel: {
            show: false,
            inside: false
          },
          data: top10CityList
        }, {
          type: 'category',
          axisLine: {
            show: false
          },
          axisTick: {
            show: false
          },
          axisLabel: {
            show: true,
            inside: false,
            textStyle: {
              color: '#b3ccf8',
              fontSize: '12',
              fontFamily: 'PingFangSC-Regular'
            },
            formatter: function (val) {
              if (val > 1000) {
                return `${(val / 1000)}K 个`;
              } else {
                return `${val}个`;
              }
            }
          },
          splitArea: {
            show: false
          },
          splitLine: {
            show: false
          },
          data: top10CityData
        }],
        xAxis: {
          type: 'value',
          axisTick: {
            show: false
          },
          axisLine: {
            show: false
          },
          splitLine: {
            show: false
          },
          axisLabel: {
            show: false
          }
        },
        series: [{
          name: '',
          type: 'bar',
          zlevel: 2,
          barWidth: '10px',
          data: lineY,
          animationDuration: 1500,
          label: {
            normal: {
              color: '#b3ccf8',
              show: true,
              position: [0, '-14px'],
              textStyle: {
                fontSize: 12
              },
              formatter: function (a, b) {
                return a.name;
              }
            }
          }
        }],
        animationEasing: 'cubicOut'
      };
      processChart.setOption(option);
    },
    drawRadarChart () {
      var radarChart = echarts.init(this.$refs.radarChart);
      var dataMax = this.allData && this.allData.roadNetworkOverview.dataMax;
      var option = {
        color: ["rgba(14, 43, 109, 1)"],
        // tooltip: {
        //   trigger: 'item',
        //   backgroundColor: 'rgba(28,38,65,.9)',
        //   fontSize: 12,
        //   label: {
        //     show: false
        //   }
        // },
        radar: {
          splitNumber: 4, // 圈数
          radius: '56%',
          center: ["50%", "50%"], // 中心点
          indicator: dataMax, // 配置各个维度的最大值
          shape: 'polygon', // 配置雷达图最外层的图形 circle polygon
          axisLine: {
            lineStyle: {
              color: "rgb(70, 188, 238)"
            }
          },
          splitLine: {
            lineStyle: {
              color: "rgb(49, 90, 111)",
              width: 1,
              type: "solid"
            }
          },
          splitArea: {
            areaStyle: {
              color: "rgba(28, 38, 65, .3)"
            }
          }
        },
        series: {
          type: 'radar', // radar 此图表时一个雷达图
          symbolSize: 5,
          symbol: 'circle',
          lineStyle: {
            width: 2
          },
          areaStyle: {
            normal: {
              shadowBlur: 13,
              shadowColor: 'rgba(0,0,0,.2)',
              shadowOffsetX: 0,
              shadowOffsetY: 10,
              opacity: '0.8'
            }
          },
          itemStyle: {
            color: "#409EFF"
          },
          label: { // 设置标签的样式
            show: true, // 显示数值
            color: '#fff',
            formatter: "{c} %"
          },
          // 将每一个产品的雷达图形成阴影的面积
          data: [{
            name: '路网概况：',
            value: this.allData && this.allData.roadNetworkOverview.data
          }]
        }
      };
      radarChart.setOption(option);
    },
    drawGantryChart () {
      let gantryChart = echarts.init(this.$refs.gantryChart);
      var option = {
        grid: {
          top: "10%",
          left: '60px',
          bottom: "13%"
        },
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "shadow",
            label: {
              show: false
            }
          },
          formatter: '{a0}:{c0}%<br />{a1}:{c1}%'
        },
        xAxis: {
          data: this.allData && this.allData.gantryDeal.gantryDeal.map((item, index) => index),
          axisLine: {
            show: true, // 隐藏X轴轴线
            lineStyle: {
              width: 2
            }
          },
          axisTick: {
            show: false // 隐藏X轴刻度
          },
          axisLabel: {
            show: false,
            textStyle: {
              color: "rgba(250,250,250,0.6)", // X轴文字颜色
              fontSize: 16
            }
          }
        },
        yAxis: [{
          type: "value",
          /* name: "亿元", */
          nameTextStyle: {
            color: "#ebf8ac",
            fontSize: 16
          },
          splitLine: {
            show: true
          },
          axisTick: {
            show: false
          },
          axisLine: {
            show: false,
            lineStyle: {
              color: '#26D9FF',
              width: 2
            }
          },
          axisLabel: {
            show: true,
            formatter: "{value} %",
            textStyle: {
              color: "rgba(250,250,250,0.6)",
              fontSize: 16
            }
          }

        }
        ],
        series: [
          {
            name: "门架交易成功率",
            type: "bar",
            barWidth: 6,
            itemStyle: {
              normal: {
                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                  offset: 0,
                  color: "rgb(10, 160, 128)"
                },
                {
                  offset: 1,
                  color: "rgb(10, 160, 128)"
                }
                ]),
                borderColor: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                  offset: 0,
                  color: "rgb(10, 160, 128)"
                },
                {
                  offset: 1,
                  color: "rgb(10, 160, 128)"
                }
                ]),
                borderWidth: 2
              }
            },
            data: this.allData && this.allData.gantryDeal.doorCardrecognition
          },
          {
            name: "门牌交易成功率",
            type: "bar",
            barWidth: 6,
            itemStyle: {
              normal: {
                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{offset: 0, color: '#E6A23C'}, {offset: 1, color: '#E6A23C'}]),
                borderColor: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{offset: 0, color: '#E6A23C'}, {offset: 1, color: '#E6A23C'}]),
                borderWidth: 2
              }
            },
            data: this.allData && this.allData.gantryDeal.gantryDeal
          }
        ]
      };
      gantryChart.setOption(option);
    },
    drawHotRankChart () {
      var hotRankChart = echarts.init(this.$refs.hotChart);
      var option = {
        grid: {
          left: '2%',
          right: '0%',
          bottom: '-0.1%',
          top: '0%',
          containLabel: true
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'none'
          },
          formatter: function (params) {
            return params[0].name + '<br/>' +
                "<span style='display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:rgba(36,207,233,0.9)'></span>" +
                params[0].seriesName + ' : ' + Number((params[0].value.toFixed(4) / 10000).toFixed(2)).toLocaleString() + ' 万元<br/>';
          }
        },
        xAxis: {
          show: false,
          type: 'value'
        },
        yAxis: [{
          type: 'category',
          inverse: true,
          axisLabel: {
            show: false,
            textStyle: {
              color: '#fff'
            }
          },
          splitLine: {
            show: false
          },
          axisTick: {
            show: false
          },
          axisLine: {
            show: false
          },
          data: this.allData && this.allData.RealTimeRankingOfTrafficHotSpots.trafficHotRankData.type
        }, {
          type: 'category',
          inverse: true,
          axisTick: 'none',
          axisLine: 'none',
          show: true,
          axisLabel: {
            textStyle: {
              color: '#46bcee',
              fontSize: '12'
            },
            formatter: function (value) {
              if (value >= 10000) {
                return (value / 10000).toLocaleString() + '万辆';
              } else {
                return value.toLocaleString();
              }
            }
          },
          position: 'left',
          data: this.allData && this.allData.RealTimeRankingOfTrafficHotSpots.trafficHotRankData.value
        }],
        series: [{
          name: '金额',
          type: 'bar',
          zlevel: 1,
          itemStyle: {
            normal: {
              barBorderRadius: 20,
              color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [{
                offset: 0,
                color: 'rgb(57,89,255,1)'
              }, {
                offset: 1,
                color: 'rgb(46,200,207,1)'
              }])
            }
          },
          barWidth: 10,
          data: this.allData && this.allData.RealTimeRankingOfTrafficHotSpots.trafficHotRankData.value
        },
        {
          name: '背景',
          type: 'bar',
          barWidth: 10,
          barGap: '-100%',
          data: this.allData && this.allData.RealTimeRankingOfTrafficHotSpots.trafficHotRankData.value,
          itemStyle: {
            normal: {
              color: 'rgba(24,31,68,1)',
              barBorderRadius: 20
            }
          }
        }
        ]
      };
      hotRankChart.setOption(option);
    },
    drawStatusChart () {
      var statusChart = echarts.init(this.$refs.statusChart);
      var colors = ['#3A85D3', 'red', '#5470c6', '#008000', '#61a0a8'];
      var option = {
        color: colors, // 下面这种直接配置演示也行
        // color: ['#3A85D3','#5AD8A6','#5470c6',  '#008000', '#61a0a8'], //依次选择颜色，默认为第一个颜色，多根柱子多个颜色
        tooltip: {
          trigger: 'axis', // 触发类型；轴触发，axis则鼠标hover到一条柱状图显示全部数据，item则鼠标hover到折线点显示相应数据，
          axisPointer: { // 坐标轴指示器，坐标轴触发有效
            type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
          }
        },
        legend: {
          data: ['正常', '异常'], // 这里设置柱状图上面的方块，名称跟series里的name保持一致
          align: 'right', // 上面方块靠右还是居中的设置.不设置则居中
          right: 10,
          itemGap: 2,
          itemHeight: 8,
          bottom: "70%",
          textStyle: {
            color: "#46bcee",
            fontSize: 10
          }
        },
        grid: {
          left: '3%', // 柱状图距离左边的距离，也可以用像素px
          right: '4%', // 柱状图距离右边的距离，也可以用像素px
          bottom: '7%', // 网格图（柱状图、折线图、气泡图等）离底部的距离，也可以用像素比如10px
          containLabel: true // grid 区域是否包含坐标轴的刻度标签。false可能溢出，默认为false
        },

        // 缩放组件
        dataZoom: {
          type: "inside",
          start: 0,
          end: 50 // 缩放组件显示70%的位置
        },
        xAxis: [{
          type: 'category',
          data: this.allData && this.allData.equipmentAndTrafficRealTimeMonitoring.type,
          axisTick: {
            alignWithLabel: true
          },
          axisLabel: {
            fontSize: 8,
            color: '#46bcee',
            rotate: 45 // 控制柱状图X轴label是否倾斜显示
          }
        }],
        yAxis: [{
          type: 'value',
          axisLabel: {
            color: "#46bcee",
            fontSize: 10
          }
        }],
        series: [{ // 柱状图-柱子1
          name: '正常', // 需要跟legend配置项对应
          type: 'bar',
          itemStyle: {
            barBorderRadius: [0, 0, 0, 0] // 控制柱状图的圆角显示弧度，（顺时针左上，右上，右下，左下）
          },
          barWidth: '20%', // barWidth设置每根柱状图的宽度
          data: this.allData && this.allData.equipmentAndTrafficRealTimeMonitoring.equipmentNormalData
        },
        { // 柱状图-柱子2
          name: '异常',
          type: 'bar',
          // itemStyle: {
          //    barBorderRadius: [10, 10, 0, 0] //控制柱状图的圆角显示弧度，（顺时针左上，右上，右下，左下）
          // },
          barWidth: '20%',
          data: this.allData && this.allData.equipmentAndTrafficRealTimeMonitoring.equipmentUnnormalData
        }]
      };
      statusChart.setOption(option);
    }
  }
};
</script>

<style lang="scss" scoped>
/deep/ .el-select-dropdown {
  background: #095379;
  border: none;
}
.el-select-dropdown__item {
  color: #E6A23C;
  &:hover {
    color: aquamarine;
    background: #409EFF;
    &::before {
      content: '';
      position: absolute;
      top: 0;
      left: 0;
      width: 3px;
      height: 33px;
      background: #67C23A;
    }
  }
}
.el-select-dropdown__item.selected {
  background: #409EFF;
  color: #fff;
  &::before {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    width: 4px;
    height: 33px;
    background: #67C23A;
  }
}
// .active {
//   color: aquamarine;
//   background: #409EFF;
// }
</style>

<style scoped lang="scss">
/deep/ .el-popper[x-placement^=bottom] .popper__arrow, .el-popper[x-placement^=bottom] .popper__arrow:after {
  border-bottom-color: #095379;
}
/deep/ .el-popper[x-placement^=top] .popper__arrow, .el-popper[x-placement^=top] .popper__arrow:after {
  border-top-color: #095379;
}
/deep/ .el-progress {
  display: inline-block;
  width: 65%;
  display: flex;
  position: relative;
  left: 30%;
  top: -14px;
  .el-progress__text {
    font-size: 12px;
    color: #fff;
  }
}
.wrapper1 {
  vertical-align: middle;
  span {
    margin-left: 5px;
    display: inline-block;
    width: 30%;
    font-size: 12px;
    color: #46bcee;
  }
  /deep/ .el-progress-bar__outer {
    border-radius: 0;
    border: 1px solid rgb(10, 160, 128);
    background: transparent;
  }
  /deep/ .el-progress-bar__inner {
    background-image: repeating-linear-gradient(-45deg,
    hsla(0,0%,10%,.1),
    hsla(0,0%,10%,.1) 5px,
    transparent 0,transparent 10px);
  }
}
.wrapper2 {
  span {
    margin-left: 5px;
    display: inline-block;
    font-size: 12px;
    color: #46bcee;
  }
  /deep/ .el-progress-bar__outer {
    border-radius: 0;
    border: 1px solid #E6A23C;
    background: transparent;
  }
  /deep/ .el-progress-bar__inner {
    background-image: repeating-linear-gradient(-45deg,
    hsla(255,0%,10%,.1),
    hsla(255,0%,10%,.1) 5px,
    transparent 0,transparent 10px);
  }
}
/deep/ .el-progress-bar__outer {
  border-radius: 0;
  border: 1px solid rgb(10, 160, 128);
  background: transparent;
}
/deep/ .el-progress-bar__inner {
  border-radius: 0;
}
/deep/ .el-select .el-input .el-input__inner{
  background: #095379;
  border: 1px solid rgb(33, 75, 109);
  border-radius: 0;
  color: rgba(244, 223, 88, 1);
  height: 30px;
}
/deep/ .el-select__caret {
  line-height: 30px;
}
.data-wrapper {
  width: 375px;
  .chart-wrapper {
    width: 375px;
    height: 277px;
    margin-left: 5px;
    display: flex;
    flex-direction: column;
    .salary-content {
      margin-top: 10px;
      width: 100%;
      height: 50px;
      display: inline-block;
      display: flex;
      align-items: center;
      p {
        flex: 1;
        font-size: 12px;
        display: flex;
        flex-direction: column;
        align-items: center;
        span {
          width: 127.5px;
          height: 52.5px;
          line-height: 26px;
          display: inline-block;
          // color: rgb(70, 188, 238);
          color: yellow;
          text-align: center;
          background: radial-gradient(rgb(28, 35, 55) 10%, rgb(95, 93, 65));
          // &:first-child {
          //   font-size: 30px;
          //   height: 50px;
          //   line-height: 45px;
          //   text-align: center;
          //   color: #fff;
          //   margin-bottom: 10px;
          // }
          .digital {
            font-weight: 200;
          }
          a {
            display: block;
            font-size: 12px;
          }
        }
        .color-two {
          color: #46bcee;
        }
        .color-three {
          color: #46bcee;
        }
      }
    }
    .charts {
      margin-top: -40px;
      display: flex;
      width: 360px;
      height: 330px;
      .left-part {
        margin-left: -20px;
        width: 195px;
        height: 248px;
      }
      .right-part {
        margin-left: -20px;
        width: 188px;
        height: 248px;
      }
    }
    .traffic-chart {
      margin-top: 35px;
      width: 360px;
      height: 260px;
    }
    .maintain-chart {
      margin-top: 3.5px;
      width: 188px;
      height: 75px;
    }
    .radar-chart {
       width: 360px;
      height: 260px;
    }
    .process-chart {
      position: absolute;
      top: 52%;
      left: 1%;
      width: 188px;
      height: 150px;
    }
    .maintain-legend {
      position: absolute;
      top: 39%;
      left: 8%;
      width: 120px;
      height: 40px;
      display: inline-block;
      p {
        display: inline-block;
        font-size: 12px;
        color: rgb(70, 188, 238);;
        &:first-child {
          color: #C0C4CC;
          margin-bottom: 5px;
          margin-left: 13px;
        }
      }
    }
    .warp {
      height: 322px;
      width: 353px;
      margin: 42px 10px 0 0;
      overflow: hidden;
      ul {
        width: 100%;
        list-style: none;
        padding: 0;
        margin: 0 auto;
        background: rgba(28,38,65,1);
        color: rgb(70, 188, 238);
        li, a {
          width: 100%;
          display: block;
          height: 37.5px;
          line-height: 37.5px;
          display: flex;
          justify-content: space-between;
          font-size: 12px;
          span {
            padding: 0 10px;
            &:first-child {
              width: 20px;
              text-align: center;
            }
            &:nth-child(2) {
              width: 103px;
              text-align: center;
              white-space: nowrap;
              overflow: hidden;
              text-overflow: ellipsis;
            }
            &:nth-child(3) {
              white-space: nowrap;
              overflow: hidden;
              text-overflow: ellipsis;
              width: 230px;
              text-align: center;
            }
            &:nth-child(4) {
              width: 90px;
              text-align: center;
              white-space: nowrap;
              overflow: hidden;
              text-overflow: ellipsis;
            }
          }
        }
        li:nth-child(2n) {
          background: rgb(6, 28, 51);
        }
      }
    }
    .header {
      position: absolute;
      top: 45px;
      left: 1px;
      display: inline-block;
      margin-left: 5px;
      width: calc(100% - 20px);
      height: 30px;
      line-height: 30px;
      background: rgba(133, 81, 0, 1);
      display: flex;
      flex-direction: row;
      justify-content: space-between;
      span {
        font-size: 12px;
        color: #fff;
        padding: 0 5px;
        &:first-child {
          width: 36px;
          text-align: center;
        }
        &:nth-child(2) {
          width: 90px;
          text-align: center;
          white-space: nowrap;
          overflow: hidden;
          text-overflow: ellipsis;
        }
        &:nth-child(3) {
          white-space: nowrap;
          overflow: hidden;
          text-overflow: ellipsis;
          width: 220px;
          text-align: center;
        }
        &:nth-child(4) {
          width: 90px;
          text-align: center;
          white-space: nowrap;
          overflow: hidden;
          text-overflow: ellipsis;
        }
      }
    }
    .list-content {
      position: absolute;
      top: 55px;
      left: 180px;
      width: 172.5px;
      height: 80%;
      display: flex;
      flex-direction: column;
      li {
        flex: 1;
        display: flex;
        border: 1px solid #409EFF;
        border-top: none;
        justify-content: space-between;
        align-items: center;
        padding: 0 5px;
        span {
          font-size: 12px;
          color: rgb(70, 188, 238);
        }
        &:first-child {
          border-top: 1px solid #409EFF;
        }
      }
    }
    .process-bar {
      width: 375px;
      height: 60px;
      margin-top: 15px;
    }
    .gantry-chart {
      width: 390px;
      height: 225px;
    }
    .gantry-title {
      margin-top: -20px;
      text-align: center;
      font-size: 12px;
      color: #fff;
    }
    .monitor {
      display: flex;
      width: 354px;
      flex-wrap: wrap;
      .monitor-wapper {
        cursor: pointer;
        width: 100%;
        margin-top: 60px;
        margin-left: 8px;
        div {
          display: inline-block;
          margin: 0 0 10px 11px;
          width: 46%;
          height: 103px;
          border: 1px solid rgb(88, 207, 255);
          // box-shadow: 0 0 10px rgba(100,200,255,1) inset;
          .dplayer-wrapper {
            box-shadow: none;
            border: none;
            width: 100%;
            height: 100%;
          }
        }
      }
      .tunnel-wrapper {
        position: absolute;    
        right: 2px;
        top: 55px;
        width: 150px;
        background: chartreuse;
      }
    }
    .construction-wrapper {
      width: 360px;
      height: 380px;
      // display: flex;
      // flex-direction: column;
      div {
        position: relative;
        margin: 0 8px;
        left: 5px;
        margin-top: 10px;
        &:first-child {
          height: 88.25px;
          background: rgb(34, 32, 27);
          border: 1px solid rgb(207, 146, 0);
          box-shadow: 0 0 10px rgb(207, 146, 0) inset;
          display: flex;
          flex-direction: column;
          p {
            margin: 7px;
            font-size: 12px;
            line-height: 16px;
            &:first-child {
              color: #E6A23C;
              span {
                color: rgb(255, 234, 0);
                font-size: 18px;
                &:first-child {
                  margin: 4px 8px 0 0;
                }
              }
            }
            &:nth-child(2) {
              flex: 1;
            }
            &:last-child {
              color: rgb(255, 234, 0);
              display: flex;
              justify-content: space-between;
            }
          }
        }
        &:nth-child(2) {
          height: 100%;
          margin: 0 10px;
          position: relative;
          left: -10px;
          .construction-header {
            position: absolute;
            top: 0;
            display: inline-block;
            width: calc(100% - 2px);
            height: 28px;
            line-height: 28px;
            border: none;
            box-shadow: none;
            background: rgba(13, 64, 113, 1);
            display: flex;
            flex-direction: row;
            justify-content: space-between;
            span {
              font-size: 12px;
              color: #fff;
              padding: 0 5px;
              &:first-child {
                width: 40px;
                text-align: center;
              }
              &:nth-child(2) {
                width: 260px;
                text-align: center;
                white-space: nowrap;
                overflow: hidden;
                text-overflow: ellipsis;
              }
              &:nth-child(3) {
                white-space: nowrap;
                overflow: hidden;
                text-overflow: ellipsis;
                width: 100px;
                text-align: center;
              }
            }
          }
          .warp {
            position: relative;
            top: 50px;
            height: 124px;
            width: 100%;
            left: 3px;
            ul {
              width: 100%;
              list-style: none;
              padding: 0;
              margin: 0 auto;
              background: rgba(28,38,65,1);
              color: rgb(70, 188, 238);
              li, a {
                width: 100%;
                display: block;
                height: 30px;
                line-height: 30px;
                display: flex;
                justify-content: space-between;
                font-size: 12px;
                span {
                  padding: 0 5px;
                  &:first-child {
                    width: 40px;
                    text-align: center;
                  }
                  &:nth-child(2) {
                    width: 260px;
                    text-align: center;
                    white-space: nowrap;
                    overflow: hidden;
                    text-overflow: ellipsis;
                  }
                  &:nth-child(3) {
                    white-space: nowrap;
                    overflow: hidden;
                    text-overflow: ellipsis;
                    width: 100px;
                    text-align: center;
                  }
                }
              }
              li:nth-child(2n) {
                background: rgb(6, 28, 51);
              }
            }
          }
        }
      }
    }
    .warning-wrapper {
      width: 100%;
      height: 100%;
      .part-one {
        position: relative;
        width: 360px;
        height: 157.5px;
        background: url('/static/image/m.png');
        background-size: 120px 120px;
        background-repeat: no-repeat;
        background-position: 50% 40%;
        p {
          display: flex;
          align-items: center;
          justify-content: center;
          span {
            &:first-child {
              display: inline-block;
              width: 37.5px;
              height: 37.5px;
              img {
                width: 100%;
                height: 100%;
              }
            }
            &:nth-child(2) {
              display: flex;
              flex-direction: column;
              i {
                margin-left: 10px;
                margin-top: 10px;
                flex: 1;
                font-size: 12px;
              }
            }
          }
          &:first-child {
            position: relative;
            left: -90px;
            top: 80px;
            text-align: center;
            i {
              position: absolute;
              left: 90px;
              top: 0;
              &:nth-child(2) {
                position: absolute;
                left: 90px;
                top: 25px;
              }
            }
          }
          &:nth-child(2) {
            position: absolute;
            left: 240px;
            top: 96px;
          }
          &:nth-child(3) {
            position: absolute;
            right: 10px;
            top: 20px;
          }
        }
      }
      .part-two {
        width: 360px;
        height: 112px;
        display: flex;
        flex-direction: row;
        justify-content: center;
        align-items: center;
        div {
          text-align: center;
          height: 100%;
          flex: 1;
          font-size: 12px;
          background: linear-gradient(0deg, rgba(0,33,36,.1) 0%, rgba(0,212,255,.5) 58%, rgba(9,109,121,.1) 100%);
          margin: 0 8px;
          p {
            line-height: 22px;
          }
          .title {
            margin-top: 5px;
            margin-bottom: 10px;
            font-size: 16px;
          }
        }
      }
    }
    .basic-wrapper {
      position: relative;
      width: 100%;
      height: 100%;
      background: url('/static/image/b.png');
      background-size: 351px 100%;
      background-repeat: no-repeat;
      background-position: 5px 0;
      .basic-info {
        margin-top: 45px;
        width: 360px;
        height: 247.5px;
        display: flex;
        flex-direction: row;
        justify-content: center;
        align-items: center;
        .img {
          position: relative;
          width: 60px;
          height: 67.5px;
          top: -40px;
          margin-left: 25px;
          .num {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            font-size: 26px;
          }
        }
        div {
          text-align: center;
          height: 100%;
          flex: 1;
          font-size: 12px;
          background: linear-gradient(0deg, rgba(0,33,36,.1) 0%, rgba(0,212,255,.1) 58%, rgba(9,109,121,.1) 100%);
          margin: 0 5px;
          p {
            line-height: 30px;
          }
          .title {
            margin: 7.5px 0;
            font-size: 15px;
          }
        }
      }
    }
    .equipment-rank-wrapper {
      width: 100%;
      height: 100%;
      margin-top: 10px;
      .equipment-info {
        width: 367px;
        li {
          height: 27px;
          line-height: 27px;
          span {
            display: inline-block;
            font-size: 12px;
            color: #46bcee;
            margin: 0 10px;
            vertical-align: top;
            &:first-child {
              position: relative;
              top: 5px;
              left: -2px;
              width: 16.5px;
              height: 16.5px;
              border-radius: 11px;
              background: #909399;
              i {
                color: #fff;
                position: absolute;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
              }
            }
            &:nth-child(2) {
              width: 255.59px;
            }
            &.active {
              background: #E6A23C;
            }
          }
          &:nth-child(2n) {
            background: rgb(6, 28, 51);
          }
        }
      }
    }
    .hot-rank-wrapper {
      width: 480px;
      height: 380px;
      display: flex;
      flex-direction: row;
      margin-top: 10px;
      .hot-info {
        display: inline-block;
        width: 180px;
        height: 380px;
        li {
          width: 100%;
          height: 26px;
          line-height: 33px;
          span {
            display: inline-block;
            font-size: 12px;
            color: #46bcee;
            margin: 0 5px;
            &:first-child {
              position: relative;
              top: -9px;
              left: -2px;
              width: 16.5px;
              height: 16.5px;
              border-radius: 11px;
              background: #909399;
              i {
                color: #fff;
                position: absolute;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
              }
            }
            &:nth-child(2) {
              display: inline-block;
              width: 135px;
              overflow: hidden;
              white-space: nowrap;
              text-overflow: ellipsis;
            }
            &.active {
              background: #E6A23C;
            }
          }
        }
      }
      .hot-chart {
        display: inline-block;
        width: 200px;
        height: 286px;
      }
    }
    .status-wrapper {
      width: 360px;
      height: 100%;
      display: flex;
      flex-direction: column;
      .status-content {
        width: 360px;
        height: 150px;
        display: flex;
        div {
          display: inline-block;
          width: 150px;
          height: 112.5px;
          img {
            width: 130%;
          }
          &:nth-child(2) {
            width: 150px;
            p {
              width: 106px;
              height: 80px;
              font-size: 12px;
              color: #46bcee;
              display: flex;
              align-items: center;
              span {
                display: flex;
                flex-direction: column;
                line-height: 18px;
                i {
                  &:first-child {
                    font-size: 12px;
                  }
                  margin-left: 8px;
                }
              }
              img {
                width: 37.5px;
                height: 37.5px;
              }
              &:first-child {
                width: 172.5px;
                position: absolute;
                left: 180px;
                i {
                  &:first-child {
                    font-size: 22px;
                    margin-bottom: 8px;
                  }
                }
              }
              &:nth-child(2) {
                position: absolute;
                right: 120px;
                top: 100px;
              }
              &:nth-child(3) {
                position: absolute;
                right: 16px;
                top: 100px;
              }
            }
          }
        }
      }
      .status-chart {
        width: 360px;
        height: 230px;
      }
    }
    .road-safe-wrapper {
      position: relative;
      width: 100%;
      height: 100%;
      top: 10px;
      background: url('/static/image/b.png');
      background-size: 90% 100%;
      background-repeat: no-repeat;
      background-position: center -30px;
      .road-safe-content {
        .title {
          position: absolute;
          top: 60px;
          left: 36%;
          font-size: 12px;
          display: flex;
          flex-direction: column;
          justify-content: center;
          align-items: center;
          span {
            display: inline-block;
            &:first-child {
              i {
                font-size: 30px;
                // position: relative;
              }
              color: #e0df58;
              margin: 0 0 12px -20px;
            }
          }
        }
        .tem-content {
          width: 101.25px;
          height: 75px;
          background: linear-gradient(0deg, rgba(0,33,36,.1) 0%, rgba(0,212,255,.5) 58%, rgba(9,109,121,.1) 100%);
          &:nth-child(2){
            position: absolute;
            left: 15px;
            bottom: 40px;
          }
          &:nth-child(3) {
            position: absolute;
            left: 36%;
            bottom: 18px;
          }
          &:nth-child(4) {
            position: absolute;
            right: 20px;
            bottom: 40px;
          }
          span {
            display: inline-block;
            &:first-child {
              width: 37.5px;
              height: 37.5px;
              margin-top: 25px;
              margin-left: 10px;
              img {
                width: 100%;
                height: 100%;
              }
            }
            &:nth-child(2) {
              width: 60px;
              display: flex;
              flex-direction: column;
              font-size: 12px;
              color: #fff;
              margin-left: 42px;
              margin-top: -40px;
              line-height: 22px;
              i {
                text-align: center;
                &:first-child {
                  font-size: 20px;
                  font-weight: bold;
                }
              }
            }
          }
        }
      }
    }
    .invert-cost-check {
      width: 100%;
      height: 100%;
      display: flex;
      flex-direction: column;
      div {
        flex: 1;
        background: url('/static/image/b.png');
        background-size: 96% 100%;
        background-repeat: no-repeat;
        background-position: -5px -30px;
        position: relative;
        height: 160px;
        &:first-child {
          p {
            display: inline-block;
            width: 120px;
            text-align: left;
            span {
              display: inline-block;
              font-size: 12px;
              color: #fff;
              &:nth-child(2) {
                width: 52.5px;
                height: 52.5px;
              }
            }
            &:first-child {
              position: relative;
              left: 22.5px;
              top: 15px;
              span {
                &:first-child {
                  position: absolute;
                  top: 55%;
                  left: 17%;
                  transform: translate(-22%, -55%);
                  i {
                    font-size: 12px;
                    color: yellow;
                    &:first-child {
                      position: absolute;
                      top: 55%;
                      left: 22%;
                      transform: translate(-22%, -55%);
                    }
                    &:nth-child(2) {
                      position: absolute;
                      top: -13px;
                      left: 37px;
                    }
                  }
                }
                &:nth-child(2) {
                  position: relative;
                }
              }
            }
            &:nth-child(2) {
              position: relative;
              left: -144px;
              top: 40px;
              width: 120px;
              text-align: center;
              span {
                display: flex;
                flex-direction: column;
                height: 50px;
                i {
                  flex: 1;
                  font-size: 12px;
                  color: yellow;
                  // position: absolute;
                  // top: 20px;
                  // left: 10px;
                  text-align: center;
                }
              }
            }
            &:nth-child(3) {
              position: relative;
              left: -192px;
              bottom: -80px;
              span {
                display: flex;
                flex-direction: column;
                height: 50px;
                i {
                  flex: 1;
                  font-size: 12px;
                  color: yellow;
                  // position: absolute;
                  // top: 20px;
                  // left: 10px;
                  text-align: center;
                }
              }
            }
            &:nth-child(4) {
              position: relative;
              left: 203px;
              bottom: 12px;
              span {
                display: flex;
                flex-direction: column;
                height: 50px;
                i {
                  flex: 1;
                  font-size: 12px;
                  color: yellow;
                  // position: absolute;
                  // top: 20px;
                  // left: 10px;
                  text-align: center;
                }
              }
            }
            &:nth-child(5) {
              position: relative;
              top: -52px;
              right: -147px;
              width: 120px;
              span {
                display: flex;
                flex-direction: column;
                height: 50px;
                i {
                  flex: 1;
                  font-size: 12px;
                  color: yellow;
                  // position: absolute;
                  // top: 20px;
                  // left: 10px;
                  text-align: center;
                }
              }
            }
            &:nth-child(6) {
              position: relative;
              right: -27.5px;
              top: -74px;
              span {
                &:first-child {
                  position: absolute;
                  top: 55%;
                  left: 17%;
                  transform: translate(-22%, -55%);
                  i {
                    font-size: 12px;
                    color: yellow;
                    &:first-child {
                      position: absolute;
                      top: 55%;
                      left: 22%;
                      transform: translate(-22%, -55%);
                    }
                    &:nth-child(2) {
                      position: absolute;
                      top: -13px;
                      left: 37px;
                    }
                  }
                }
              }
            }
            &:nth-child(7) {
              position: relative;
              right: -150px;
              top: -158px;
              width: 180px;
              span {
                display: inline-block;
                &:first-child {
                  width: 180px;
                  display: flex;
                  flex-direction: column;
                  height: 50px;
                  margin-left: -50px;
                  text-align: center;
                  // position: absolute;
                  // top: 55%;
                  // left: 3%;
                  // transform: translate(-22%, -55%);
                  i {
                    flex: 1;
                    font-size: 13.5px;
                    color: yellow;
                    &:first-child {
                      display: inline-block;
                      width: 180px;
                      margin-bottom: 0;
                    }
                  }
                }
                &:nth-child(2) {
                  position: absolute;
                  top: 48px;
                  left: 13px;
                }
              }
            }
          }
        }
        &:nth-child(2) {
          width: 100%;
          position: relative;
          top: 30px;
          img {
            position: absolute;
            width: 150px;
            height: 50px;
            left: 45%;
            top: 45%;
            transform: translate(-45%, -45%);
          }
          p {
            width: 150px;
            height: 50px;
            display: flex;
            flex-direction: column;
            text-align: center;
            span {
              font-size: 12px;
              flex: 1;
              color: #fff;
              &:nth-child(2) {
                color: yellow;
                font-size: 16.5px;
              }
            }
            &:nth-child(2) {
              position: absolute;
              left: 29%;
              top: -21px;
            }
            &:nth-child(3) {
              position: absolute;
              right: 8px;
              top: 22px;
            }
            &:nth-child(4) {
              position: absolute;
              left: -7px;
              top: 22px;
            }
          }
        }
      }
    }
  }
}
</style>
