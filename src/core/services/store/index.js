import Vue from 'vue';
import Vuex from 'vuex';
import createPersistedState from 'vuex-persistedstate';
import auth from './auth.module';
import htmlClass from './htmlclass.module';
import config from './config.module';
import breadcrumbs from './breadcrumbs.module';
import profile from './profile.module';
import dongStore from './dong.module';
import rate from './rating.module';
import review from './review.module';

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    auth,
    htmlClass,
    config,
    breadcrumbs,
    profile,
    dongStore,
    rate,
    review,
  },
  plugins: [createPersistedState()],
});
