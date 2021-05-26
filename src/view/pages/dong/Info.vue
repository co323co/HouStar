<template lang="">
  <v-container class="content">
    <div>
      <v-row class="info_header">
        <h1 class="ml-3">
          {{ this.$store.state.dongStore.Sidogugundong.dongName }}
        </h1>
        <div class="ml-4" style="display: inline-block">
          <span>
            {{ this.$store.state.dongStore.Sidogugundong.sidoName }}
          </span>
          <span class="ml-1">
            {{ this.$store.state.dongStore.Sidogugundong.gugunName }}
          </span>
        </div>
      </v-row>
    </div>
    <v-divider></v-divider>
    <v-flex>
      <v-row class="info_nav pa-3" style="justify-content: center">
        <v-divider vertical></v-divider>
        <router-link :to="{ name: 'detail' }">상세정보</router-link>
        <v-divider vertical></v-divider>
        <router-link :to="{ name: 'review' }">리뷰</router-link>
        <v-divider vertical></v-divider>
      </v-row>
      <v-divider></v-divider>
    </v-flex>

    <v-row>
      <!--라우터 뷰!!!! -->
      <router-view :key="$route.fullPath"></router-view>
    </v-row>
  </v-container>
</template>
<script>
export default {};
</script>
<style scoped>
.info_header {
  display: block;
  font-family: BMHANNAPro;
}
.info_header h1 {
  display: inline-block;
  font-size: 3.5em;
}
a,
span {
  color: black;
  font-size: 20px;
}
.content {
  margin-top: 30px;
}
.info_nav {
  padding-left: 20px;
  font-size: 1.1em;
  font-weight: bold;
}
.info_nav .v-divider {
  margin: 0px 10px;
}
</style>
