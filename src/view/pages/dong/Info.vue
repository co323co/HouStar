<template lang="">
  <div class="content pa-10">
    <v-row class="info_header">
      <h1 class="mt-3">{{ this.$store.state.dongStore.Sidogugundong.dongName }}</h1>
      <div class="ml-3" style="display: inline-block">
        <span>
          {{ this.$store.state.dongStore.Sidogugundong.sidoName }}
        </span>
        <span class="ml-2">
          {{ this.$store.state.dongStore.Sidogugundong.gugunName }}
        </span>
      </div>
    </v-row>
    <v-divider></v-divider>
    <v-row>
      <router-link class="routerlink mr-3 ml-2" :to="{ name: 'detail' }">상세정보</router-link>
      | <router-link class="routerlink ml-3" :to="{ name: 'review' }">리뷰</router-link>
    </v-row>
    <v-divider></v-divider>
    <v-row class="mx-auto">
      <!--라우터 뷰!!!! -->
      <router-view :key="$route.fullPath"></router-view>
    </v-row>
  </div>
</template>
<script>
export default {};
</script>
<style scoped>
.info_header {
  display: block;
}
.info_header h1 {
  display: inline-block;
  font-weight: bold;
  font-size: 4em;
}
a,
span {
  color: black;
  font-size: 20px;
}
.content {
  background-color: #ffffff;
}
</style>
