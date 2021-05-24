import http from '@/core/services/http-common';

const state = {
  // headerText: 'TODO it!',
  reviews: [],
  reviewsbyuserid: [],
};
const getters = {
  totalReviewCount(state) {
    return state.reviews.length;
  },
  reviews(state) {
    return state.reviews;
  },
  reviewsbyuserid(state) {
    return state.reviewsbyuserid;
  },
};
const mutations = {
  setReviews(state, payload) {
    state.reviews = payload;
  },
  setReviewsByUserId(state, payload) {
    state.reviewsbyuserid = payload;
  },
};
const actions = {
  getReviews({ commit }, payload) {
    http.get('/dongreview/dong/' + payload).then(({ data }) => {
      console.log('dongcode로 해당 동에 대한 리뷰 다 가져오기 성공');
      commit('setReviews', data);
    });
  },
  getReviewsByUserId({ commit }, payload) {
    http.get('/dongreview/user/' + payload).then(({ data }) => {
      //console.log('userid로 해당 유저가 쓴 모든 리뷰 가져오기 성공');
      // console.log(data);
      commit('setReviewsByUserId', data);
    });
  },
};

export default {
  namespaced: true,
  state,
  actions,
  mutations,
  getters,
};
