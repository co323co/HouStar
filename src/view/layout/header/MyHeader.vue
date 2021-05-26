<template>
  <div absolute>
    <!-- 앱 바 -->
    <v-btn
      width="40px"
      height="40px"
      top
      fixed
      bottom
      absolute
      dark
      left
      fab
      color="green"
      class="mt-8"
    >
      <KTQuickUser></KTQuickUser>
    </v-btn>
    <v-app-bar height="50px" class="myHeader" color="white">
      <v-container>
        <v-row align="center">
          <v-col>
            <router-link to="/" class="header_menu text-hover-primary align-middle">
              <v-icon x-large color="black" style="vertical-align: middle">mdi-home-modern</v-icon>
              <h2 style="vertical-align: middle">Houstar</h2>
            </router-link>
          </v-col>
          <v-col class="text-right">
            <router-link to="/home" class="header_menu text-hover-success">HOME </router-link>
            <router-link to="/notice" class="header_menu text-hover-success">
              공지사항
            </router-link>
            <router-link to="/qna" class="header_menu text-hover-success"> QnA </router-link>
            <router-link to="/community" class="header_menu text-hover-success">
              커뮤니티
            </router-link>
          </v-col>
        </v-row>
      </v-container>
    </v-app-bar>
  </div>
</template>
<script>
import KTLayoutQuickUser from '@/assets/js/layout/extended/quick-user.js';
import KTQuickUser from '@/view/layout/extras/offcanvas/QuickUser.vue';

export default {
  data() {
    return {};
  },
  mounted() {
    KTLayoutQuickUser.init(this.$refs['kt_quick_user']);
  },
  components: {
    KTQuickUser,
  },
};
</script>
<style scoped>
.myHeader {
  color: rgb(255, 255, 255);
}
h1,
h2,
h3,
h4,
h5 {
  padding: 0;
  margin: 0;
  display: inline-block;
  color: darkcyan;
}
.header_menu {
  margin-left: 45px;
  color: black;
  font-size: 1.2em;
  font-family: BMHANNAPro !important;
}
</style>
