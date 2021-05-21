// 동 정보를 관리하기 위한 스토어
const dongStore = {
  state: {
    Sidogugundong: Object,
  },
  getters: {},
  mutations: {
    setSidogugundong(state, payload) {
      state.Sidogugundong = payload;
    },
  },
  actions: {},
};

export default dongStore;
