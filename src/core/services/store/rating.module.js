import http from '@/core/services/http-common';
export default {
  state: {
    rating: Object,
    AvgRating: 0.0,
  },
  getters: {
    rating(state) {
      return state.rating;
    },
    AvgRating(state) {
      return state.AvgRating;
    },
  },
  mutations: {
    setRating(state, payload) {
      state.rating = payload;
    },
    setAvgRating(state, payload) {
      state.AvgRating = payload;
    },
  },
  actions: {
    getRating(context, payload) {
      http
        .get('/dongreview/avg-rating/' + payload)
        .then(({ data }) => {
          // Rating.module 에 있는 rating 객체에 얻어온 평균평점 객체 넣음
          context.commit('setRating', data);
          //context.commit('setAvgRating', data.total * 1.0);
          //this.$store.state.rate.rating = data;
          //this.totalRating = this.$store.state.rate.rating.total * 1.0;
        })
        .catch(({ response }) => {
          console.log(response);
        });
    },
  },
};
