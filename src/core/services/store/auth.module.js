// action types
export const VERIFY_AUTH = 'verifyAuth';
export const LOGIN = 'login';
export const LOGOUT = 'logout';
export const REGISTER = 'register';
export const UPDATE_PASSWORD = 'updateUser';

// mutation types
export const PURGE_AUTH = 'logOut';
export const SET_AUTH = 'setUser';
export const SET_PASSWORD = 'setPassword';
export const SET_ERROR = 'setError';

import http from '@/core/services/http-common';

const state = {
  errors: null,
};

const getters = {
  currentUser(state) {
    let string_user = sessionStorage.getItem('currentUser');
    return JSON.parse(string_user);
  },
};

const actions = {
  isLogin() {
    console.log('isLogin getter 실행');
    console.dir(sessionStorage.getItem('currentUser'));
    if (sessionStorage.getItem('currentUser')) return (state.isLogin = true);
    else return (state.isLogin = false);
  },

  [LOGIN](context, payload) {
    return new Promise((resolve, rejecet) => {
      http
        .post('/user/login', payload)
        .then(({ data }) => {
          //로그인 성공
          if (data == true) {
            http.get('/user/' + payload.id).then(({ data }) => {
              context.commit(SET_AUTH, data);
              resolve();
            });
          }
          //로그인 실패
          else rejecet();
        })
        //서버 에러
        .catch(({ response }) => {
          rejecet(response);
        });
    });
  },
  [LOGOUT](context) {
    context.commit(PURGE_AUTH);
  },
  [REGISTER](context, user) {
    // return new Promise((resolve) => {});
  },
  [VERIFY_AUTH](context) {
    // if (JwtService.getToken()) {
    //   ApiService.setHeader();
    //   ApiService.get('verify')
    //     .then(({ data }) => {
    //       context.commit(SET_AUTH, data);
    //     })
    //     .catch(({ response }) => {
    //       context.commit(SET_ERROR, response.data.errors);
    //     });
    // } else {
    //   context.commit(PURGE_AUTH);
    // }
  },
  [UPDATE_PASSWORD](context, payload) {
    const password = payload;
  },
};

const mutations = {
  [SET_ERROR](state, error) {
    state.errors = error;
  },
  [SET_AUTH](state, user) {
    let string_user = JSON.stringify(user);
    sessionStorage.setItem('currentUser', string_user);
  },
  [SET_PASSWORD](state, password) {
    state.user.password = password;
  },
  [PURGE_AUTH](state) {
    sessionStorage.clear();
  },
};

export default {
  state,
  actions,
  mutations,
  getters,
};
