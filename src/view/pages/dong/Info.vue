<template lang="">
  <v-app>
    <v-card>
      <v-system-bar></v-system-bar>
      <v-spacer></v-spacer>
      info.vue페이지
      <v-toolbar flat>
        <span class="mr-2">
          {{ this.$store.state.dongStore.Sidogugundong.sidoName }}
        </span>
        |
        <span class="ml-2">
          {{ this.$store.state.dongStore.Sidogugundong.gugunName }}
        </span>
      </v-toolbar>

      <v-toolbar flat class="mb-5">
        <v-toolbar-title>
          <h1 class="display-3">{{ this.$store.state.dongStore.Sidogugundong.dongName }}</h1>
        </v-toolbar-title>
      </v-toolbar>

      <v-divider class="mx-4"></v-divider>

      <v-card-actions class="ml-2 mb-2">
        <router-link class="routerlink mr-3 ml-2" :to="{ name: 'detail' }">상세정보</router-link>

        | <router-link class="routerlink ml-3" :to="{ name: 'review' }">리뷰</router-link>
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
<style scoped>
.v-application a,
span {
  color: black;
  font-size: 20px;
}
</style>
