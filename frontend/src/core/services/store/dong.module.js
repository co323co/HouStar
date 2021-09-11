// 동 정보를 관리하기 위한 스토어
const dongStore = {
  state: {
    Sidogugundong: Object,
  },
  getters: {
    currentDongInfo(state) {
      return state.Sidogugundong;
    },
  },
  mutations: {
    setSidogugundong(state, payload) {
      state.Sidogugundong = payload;
    },
  },
  // actions: {
  //   getTotalAvg()
  // },
};

export default dongStore;
