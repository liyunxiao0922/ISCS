import Vue from 'vue';
import Vuex from 'vuex';
import getters from './getters';
import app from './modules/app';

Vue.use(Vuex);
const store = new Vuex.Store({
  state: {
    isShowFlag: {
      vehicleDetector: true,
      signalLamp: true,
      laneIndicator: true,
      lighting: true,
      brightnessDetector: true,
      illuminanceDetector: true,
      tunnelFan: true,
      anemoclinograph: true,
      informationBoard: true,
      coVIDetector: true,
      strengthenLighting: true,
      approachLighting: true,
      camera: true,
      phone: true
    },
    dataBase: ['vehicleDetector', 'signalLamp', 'laneIndicator', 'lighting', 'brightnessDetector', 'illuminanceDetector', 'tunnelFan', 'anemoclinograph', 'informationBoard', 'strengthenLighting', 'approachLighting', 'coVIDetector', 'camera', 'phone'],
    start: {lowerPile: 'k36+000', upperPile: 'k37+999'},
    step: 0,
    stepArr: [],
    robot: {
      robotEvent: 0,
      bourn: '',
      robotType: 9
    },
    autoEvent: {

    },
    eventForm: {

    },
    eventTips: {

    },
    eventList: []
  },
  modules: {
    app
  },
  getters,
  mutations: {
    setShow: (state, str) => {
      state.isShowFlag[state.dataBase[str]] = !state.isShowFlag[state.dataBase[str]];
    },
    setStart: (state, obj) => {
      state.start = obj;
    },
    /* getFlag: (state, str) => {
      let flag = state.isShowFlag[state.dataBase[str - 1]];
      return flag
    } */
    setStepArr: (state, arr) => {
      state.stepArr = arr;
    },
    setStep: (state, num) => {
      state.step = num;
    },
    setEvent: (state, num) => {
      state.robot.robotEvent = num.event;
      state.robot.bourn = num.formInline;
      state.robot.robotType = num.type;
      console.log(state);
    },
    setAuto: (state, obj) => {
      if (obj.key) {
        state.autoEvent[obj.key] = obj.value;
      } else {
        state.autoEvent = obj;
      }
    },
    setForm: (state, obj) => {
      console.log(obj);
      state.eventForm = obj;
    },
    setTips: (state, obj) => {
      console.log(obj);
      state.eventTips = obj;
    },
    setList: (state, arr) => {
      state.eventList = arr;
    }
  }
});

export default store;
