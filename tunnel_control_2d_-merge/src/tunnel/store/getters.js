const getters = {
  sidebar: state => state.app.sidebar,
  device: state => state.app.device,
  token: state => state.user.token,
  avatar: state => state.user.avatar,
  name: state => state.user.name,
  getState: state => state.isShowFlag,
  getFlag: (state, getters) => (str) => state.isShowFlag[state.dataBase[str - 1]],
  getStartL: state => (state.start.lowerPile.split('+')[0].toUpperCase().split('K')[1] - 36) * 1000 + +state.start.lowerPile.split('+')[1],
  getStartR: state => (state.start.upperPile.split('+')[0].toUpperCase().split('K')[1] - 36) * 1000 + +state.start.upperPile.split('+')[1],
  getEvent: state => state.robot,
  getAuto: state => state.autoEvent,
  getEventForm: state => state.eventForm,
  getTips: state => state.eventTips
};
export default getters;
