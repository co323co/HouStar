<template lang="">
  <!-- 배너 사진 띄우는 레이아웃 -->
  <!-- cycle="cycle" -->
  <v-layout>
    <v-carousel
      v-ripple
      hide-delimiter-background
      delimiter-icon="mdi-minus"
      :show-arrows-on-hover="true"
      interval="4000"
      tag="#tag"
    >
      <!-- 이미지 반복 -->
      <v-carousel-item
        v-for="(img, i) in banner_img"
        :key="i"
        :src="img"
        reverse-transition="fade-transition"
        transition="fade-transition"
      >
        <v-flex id="banner-text">
          <div style="height: 75%"></div>
          <h1>Houstar</h1>
          <h3>Looking for a place to love?</h3>
        </v-flex>
      </v-carousel-item>
    </v-carousel>
  </v-layout>
</template>
<script>
export default {
  data() {
    return {
      banner_img: [],
    };
  },
  mounted() {
    // 배너에 달아줄 이미지 추가
    let list = this.banner_img;
    for (let i = 1; i <= 5; i++) {
      list.push('media/banner/house' + i + '.jpg');
    }
  },
};
</script>
<style scoped>
#banner-text {
  color: beige;
  margin: auto;
  text-align: center;
  height: 100%;
  text-shadow: -1px 0 black, 0 1px black, 1px 0 lightgrey, 0 -1px lightgrey;
}
</style>
