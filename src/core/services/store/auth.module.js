// action types
export const VERIFY_AUTH = 'verifyAuth';
export const LOGIN = 'login';
export const LOGOUT = 'logout';
export const REGISTER = 'register';
export const UPDATE_PASSWORD = 'updateUser';

// mutation types
export const PURGE_AUTH = 'logOut';
export const SET_AUTH = 'setAuth';
export const SET_USER = 'setUser';
export const SET_PASSWORD = 'setPassword';
export const SET_ERROR = 'setError';

import http from '@/core/services/http-common';

const state = {
  errors: null,
  user: null,
  isLogin: null,
};

const getters = {
  currentUser(state) {
    return state.user;
  },
  isLogin(state) {
    return state.isLogin;
  },
};

const actions = {
  [VERIFY_AUTH]() {},
  [LOGIN](context, payload) {
    return new Promise((resolve, rejecet) => {
      http
        .post('/user/login', payload)
        .then(({ data }) => {
          //로그인 성공
          if (data == true) {
            http.get('/user/' + payload.id).then(({ data }) => {
              context.commit(SET_AUTH, true);
              context.commit(SET_USER, data);
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
    sessionStorage.clear();
    context.commit(SET_AUTH, false);
    context.commit(SET_USER, null);
  },
  [REGISTER](context, user) {
    // return new Promise((resolve) => {});
  },

  [UPDATE_PASSWORD](context, payload) {
    const password = payload;
  },
};

const mutations = {
  [SET_ERROR](state, error) {
    state.errors = error;
  },
  [SET_USER](state, user) {
    state.user = user;
    sessionStorage.setItem('currentUser', JSON.stringify(user));
  },
  [SET_AUTH](state, isLogin) {
    state.isLogin = isLogin;
  },
  [SET_PASSWORD](state, password) {
    state.user.password = password;
  },
  // [PURGE_AUTH](state) {
  //   state.isLogin = false;
  // },
};

export default {
  state,
  actions,
  mutations,
  getters,
};
