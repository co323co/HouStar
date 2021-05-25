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
      color="pink"
      class="mt-9"
    >
      <KTQuickUser></KTQuickUser>
    </v-btn>
    <v-app-bar height="50px" class="myHeader" color="white">
      <v-container>
        <v-row align="center">
          <v-col>
            <v-icon x-large color="black">mdi-home-modern</v-icon>
            <router-link to="/">
              <h2>Houstar</h2>
            </router-link>
          </v-col>
          <v-col class="text-right">
            <router-link to="/home" v-slot="{ href, navigate, isActive, isExactActive }">
              <a :href="href" class="menu-link" @click="navigate">
                <span class="menu-text"> HOME </span>
              </a>
            </router-link>
            <router-link to="/notice" v-slot="{ href, navigate, isActive, isExactActive }">
              <a :href="href" @click="navigate">
                <span> 공지사항 </span>
              </a>
            </router-link>
            <router-link to="/qna" v-slot="{ href, navigate, isActive, isExactActive }">
              <a :href="href" @click="navigate">
                <span> QnA </span>
              </a>
            </router-link>
            <router-link to="/community" v-slot="{ href, navigate, isActive, isExactActive }">
              <a :href="href" class="menu-link" @click="navigate">
                <span class="menu-text"> 커뮤니티 </span>
              </a>
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
h2 {
  padding: 0;
  margin: 0;
  display: inline-block;
}
a span {
  margin-left: 45px;
  color: black;
  font-size: 1.2em;
}
</style>
