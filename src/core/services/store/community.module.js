import http from '@/core/services/http-common';

export default {
  state: {
    hotposts: [],
    hotcommunitys: [],
    communitys: [],
  },
  getters: {
    hotposts(state) {
      return state.hotposts;
    },
    hotcommunitys(state) {
      return state.hotcommunitys;
    },
    communitys(state) {
      return state.communitys;
    },
  },
  mutations: {
    setHotposts(state, payload) {
      state.hotposts = payload;
    },
    setHotcommunitys(state, payload) {
      state.hotcommunitys = payload;
    },
    setCommunitys(state, payload) {
      state.communitys = payload;
    },
  },
  actions: {
    // 인기 게시글 상위 5개를 조회수로 반환한다.
    getHotposts(context) {
      http.get('/board/hotpost').then(({ data }) => {
        context.commit('setHotposts', data);
      });
    },
    // 인기 커뮤니티 상위 5개를 게시된 글 수의 총합으로 계산하여 반환한다.
    getHotcommunitys(context) {
      http.get('/board/hotcommunity').then(({ data }) => {
        //  console.log(payload);
        context.commit('setHotcommunitys', data);
      });
    },
    //모든 커뮤니티를 반환한다.
    getCommunitys(context) {
      http.get('/board/community').then(({ data }) => {
        console.log('가져오기성공ㅇ');
        context.commit('setCommunitys', data);
      });
    },
    // 커뮤니티 등록  ( payload : 객체)
    addCommunity(context, payload) {
      http.post('/board/community', payload).then(({ data }) => {
        console.log('등록성공');
        http.get('/board/community').then(({ data }) => {
          console.log('가져오기성공ㅇ');
          context.commit('setCommunitys', data);
        });
      });
    },
    // 커뮤니티 수정 ( payload : 객체)
    modifyCommunity(context, payload) {
      http.put('/board/community', payload).then(({ data }) => {
        console.log('수정성공');
      });
    },
    // 커뮤니티 삭제
    deleteCommunity(context, payload) {
      http.delete('/board/community' + payload).then(({ data }) => {
        console.log('삭제성공');
      });
    },
  },
};
