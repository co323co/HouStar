<template lang="">
  <v-app>
    <v-card>
      <v-system-bar></v-system-bar>
      <v-spacer></v-spacer>
      info.vue페이지
      <v-toolbar flat>
        <div>
          {{ this.$store.state.dongStore.Sidogugundong.sidoName }}
          {{ this.$store.state.dongStore.Sidogugundong.gugunName }}
        </div>
      </v-toolbar>

      <v-toolbar flat class="mb-5">
        <v-toolbar-title>
          <h1 class="display-3">{{ this.$store.state.dongStore.Sidogugundong.dongName }}</h1>
        </v-toolbar-title>
      </v-toolbar>

      <v-divider class="mx-4"></v-divider>

      <v-card-actions class="ml-2 mb-2">
        <v-btn
          ><router-link class="routerlink" :to="{ name: 'detail' }">상세정보</router-link></v-btn
        >

        <v-btn class="ml-4">
          <router-link class="routerlink" :to="{ name: 'review' }">리뷰</router-link>
        </v-btn>
      </v-card-actions>

      <v-card-text class="grey lighten-4">
        <v-sheet max-width="1200" min-height="300" class="mx-auto">
          <!--라우터 뷰!!!! -->
          <router-view></router-view>
        </v-sheet>
      </v-card-text>
    </v-card>
  </v-app>
</template>
<script>
export default {
  components: {
    // InfoHeader,
  },
};
</script>
<style>
.v-application a {
  color: black;
}
</style>
