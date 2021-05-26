<template>
  <div v-if="isLogin">
    <div>
      <KTHeader></KTHeader>
      <!-- 메인 화면 -->
      <div class="flex-row-fluid" background-color="white" style="min-height: 580px">
        <transition name="fade-in-up">
          <router-view :key="$route.fullPath" />
        </transition>
      </div>
      <KTFooter></KTFooter>
    </div>
    <KTScrollTop></KTScrollTop>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
import KTHeader from '@/view/layout/header/MyHeader.vue';
import KTHeaderMobile from '@/view/layout/header/HeaderMobile.vue';
import KTFooter from '@/view/layout/footer/Footer.vue';
import HtmlClass from '@/core/services/htmlclass.service';
import KTScrollTop from '@/view/layout/extras/ScrollTop';
import KTQuickUser from '@/view/layout/extras/offcanvas/QuickUser.vue';

import {
  ADD_BODY_CLASSNAME,
  REMOVE_BODY_CLASSNAME,
} from '@/core/services/store/htmlclass.module.js';

export default {
  name: 'Layout',
  components: {
    KTHeader,
    // KTSubheader,
    KTFooter,
    // KTStickyToolbar,
    KTScrollTop,
    // KTQuickUser,
  },
  beforeMount() {
    // show page loading
    this.$store.dispatch(ADD_BODY_CLASSNAME, 'page-loading');

    // initialize html element classes
    HtmlClass.init(this.layoutConfig());
  },
  mounted() {
    // 로그인 안되어있으면 로그인 페이지로 넘어감
    if (!this.isLogin) {
      this.$router.push({ name: 'login' });
    }

    // Simulate the delay page loading
    setTimeout(() => {
      // Remove page loader after some time
      this.$store.dispatch(REMOVE_BODY_CLASSNAME, 'page-loading');
    }, 1000);
  },
  methods: {
    footerLayout(type) {
      return this.layoutConfig('footer.layout') === type;
    },
  },
  computed: {
    ...mapGetters(['isLogin', 'breadcrumbs', 'pageTitle', 'layoutConfig']),

    /**
     * Check if the page loader is enabled
     * @returns {boolean}
     */
    loaderEnabled() {
      return !/false/.test(this.layoutConfig('loader.type'));
    },

    /**
     * Check if container width is fluid
     * @returns {boolean}
     */
    contentFluid() {
      return this.layoutConfig('content.width') === 'fluid';
    },

    /**
     * Page loader logo image using require() function
     * @returns {string}
     */
    loaderLogo() {
      return process.env.BASE_URL + this.layoutConfig('loader.logo');
    },

    /**
     * Check if the left aside menu is enabled
     * @returns {boolean}
     */
    asideEnabled() {
      return !!this.layoutConfig('aside.self.display');
    },

    /**
     * Set the right toolbar display
     * @returns {boolean}
     */
    toolbarDisplay() {
      // return !!this.layoutConfig("toolbar.display");
      return true;
    },

    /**
     * Set the subheader display
     * @returns {boolean}
     */
    subheaderDisplay() {
      return !!this.layoutConfig('subheader.display');
    },

    /**
     * Set the subheader display on dashboard page
     * @returns {boolean}
     */
    displaySubheaderOnDashboard() {
      return this.$route.name !== 'dashboard';
    },
  },
};
</script>
