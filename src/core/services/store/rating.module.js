import http from '@/core/services/http-common';
export default {
  state: {
    rating: Object,
    // AvgRating: 0.0,
  },
  getters: {
    rating(state) {
      return state.rating;
    },
    // AvgRating(state) {
    //   return state.AvgRating;
    // },
  },
  mutations: {
    setRating(state, payload) {
      state.rating = payload;
    },
    // setAvgRating(state, payload) {
    //   state.AvgRating = payload;
    // },
  },
  actions: {
    //동코드로 보냄
    getRating(context, payload) {
      console.log('서버에서 rating 받아옴');
      console.log('payload', payload);
      http.get('/dongreview/avg-rating/' + payload).then(({ data }) => {
        // Rating.module 에 있는 rating 객체에 얻어온 평균평점 객체 넣음
        console.log('서버 받은 data', data);
        // console.log(data);
        // if (data) {
        //   context.commit('setRating', 0);
        // }

        context.commit('setRating', data);
        //context.commit('setAvgRating', data.total * 1.0);
        //this.$store.state.rate.rating = data;
        //this.totalRating = this.$store.state.rate.rating.total * 1.0;
      });
      // .catch(({ response }) => {
      //   this.AvgRating = 0.0;
      //   console.log(response);
      // });
    },
  },
};
