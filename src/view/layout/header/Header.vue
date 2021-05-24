<template>
  <div id="kt_header" ref="kt_header" class="header flex-column header-fixed">
    <div class="header-top">
      <div class="container pa-0">
        <div class="d-none d-lg-flex align-items-center mr-3">
          <!--begin::Logo-->
          <div class="mr-20">
            <router-link to="/">
              <img alt="Logo" :src="layoutConfig('self.logo.default')" class="max-h-35px" />
            </router-link>
          </div>
          <!--end::Logo-->
          <ul class="header-tabs nav align-self-end font-size-lg" role="tablist">
            <li class="nav-item">
              <a
                class="nav-link py-4 px-6 active"
                @click="setActiveTab"
                data-tab="0"
                data-toggle="tab"
                href="#"
                role="tab"
                aria-selected="true"
                >하우스타</a
              >
            </li>
          </ul>
        </div>
        <KTTopbar></KTTopbar>
      </div>
    </div>
    <div class="header-bottom">
      <div class="container">
        <div class="header-navs header-navs-left" id="kt_header_navs" ref="kt_header_navs">
          <ul class="header-tabs p-5 p-lg-0 d-flex d-lg-none nav nav-bold nav-tabs" role="tablist">
            <li class="nav-item mr-2">
              <a
                href="#"
                class="nav-link btn btn-clean active"
                @click="setActiveTab"
                data-tab="0"
                data-toggle="tab"
                data-target="#kt_header_tab_1"
                role="tab"
                >하우스타</a
              >
            </li>
          </ul>
          <b-tabs class="hide-tabs" v-model="tabIndex">
            <!-- 하우스타 메뉴에 띄울 하단메뉴 -->
            <b-tab>
              <div class="tab-pane p-5 show active">
                <!--begin::Menu-->
                <div
                  id="kt_header_menu"
                  ref="kt_header_menu"
                  class="header-menu header-menu-mobile header-menu-layout-default"
                >
                  <HSMenu></HSMenu>
                </div>
                <!--end::Menu-->
              </div>
            </b-tab>
          </b-tabs>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
import KTTopbar from '@/view/layout/header/Topbar.vue';
import KTLayoutHeader from '@/assets/js/layout/base/header.js';
import KTLayoutHeaderMenu from '@/assets/js/layout/base/header-menu.js';
import HSMenu from '@/view/layout/header/HouStarMenu.vue';

export default {
  name: 'KTHeader',
  data() {
    return {
      tabIndex: 0,
    };
  },
  components: {
    KTTopbar,
    HSMenu,
  },
  mounted() {
    // Init Desktop & Mobile Headers
    KTLayoutHeader.init(this.$refs['kt_header'], this.$refs['kt_header_mobile']);

    // Init Header Menu
    KTLayoutHeaderMenu.init(this.$refs['kt_header_menu'], this.$refs['kt_header_navs']);

    const headerRef = this.$refs['kt_header'];

    headerRef.querySelectorAll("a[class='menu-link']").forEach((item) => {
      item.addEventListener('click', () => {
        KTLayoutHeaderMenu.getOffcanvas().hide();
      });
    });
  },
  computed: {
    ...mapGetters(['layoutConfig', 'getClasses']),

    /**
     * Check if the header menu is enabled
     * @returns {boolean}
     */
    headerMenuEnabled() {
      return !!this.layoutConfig('header.menu.self.display');
    },

    /**
     * Get extra classes for header based on the options
     * @returns {null|*}
     */
    headerClasses() {
      const classes = this.getClasses('header');
      if (typeof classes !== 'undefined') {
        return classes.join(' ');
      }
      return null;
    },

    /**
     * Get extra classes for header menu based on the options
     * @returns {null|*}
     */
    headerMenuClasses() {
      const classes = this.getClasses('header_menu');
      if (typeof classes !== 'undefined') {
        return classes.join(' ');
      }
      return null;
    },

    /**
     * Check if header container is fluid
     */
    widthFluid() {
      return this.layoutConfig('header.self.width') === 'fluid';
    },
  },
  methods: {
    /**
     * Set current active on click
     * @param event
     */
    setActiveTab(event) {
      let target = event.target;
      if (!event.target.classList.contains('nav-link')) {
        target = event.target.closest('.nav-link');
      }

      const tab = target.closest('[role="tablist"]');
      const links = tab.querySelectorAll('.nav-link');
      // remove active tab links
      for (let i = 0; i < links.length; i++) {
        links[i].classList.remove('active');
      }

      // set clicked tab index to bootstrap tab
      this.tabIndex = parseInt(target.getAttribute('data-tab'));

      // set current active tab
      target.classList.add('active');
    },
  },
};
</script>
