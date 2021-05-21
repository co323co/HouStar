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

const state = {
  errors: null,
  user: {},
};

const getters = {
  currentUser(state) {
    return state.user;
  },
  isAuthenticated(state) {
    return true;
  },
};

const actions = {
  [LOGIN](context, credentials) {
    // return new Promise((resolve) => {
    //   ApiService.post('login', credentials)
    //     .then(({ data }) => {
    //       // console.log("Here what post returns", data);
    //       context.commit(SET_AUTH, data);
    //       resolve(data);
    //     })
    //     .catch(({ response }) => {
    //       context.commit(SET_ERROR, response.data.errors);
    //     });
    // });
  },
  [LOGOUT](context) {
    // context.commit(PURGE_AUTH);
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
    // state.isAuthenticated = true;
    // state.user = user;
    // state.errors = {};
    // JwtService.saveToken(state.user.token);
  },
  [SET_PASSWORD](state, password) {
    state.user.password = password;
  },
  [PURGE_AUTH](state) {
    // state.isAuthenticated = false;
    // state.user = {};
    // state.errors = {};
    // JwtService.destroyToken();
  },
};

export default {
  state,
  actions,
  mutations,
  getters,
};
