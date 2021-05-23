<template lang="">
  <v-container class="justify-center">
    <!-- 배너 사진 띄우는 레이아웃 -->
    <Banner />
    <!-- 추천 동네 레이아웃 -->
    <SuggestDong />
    <!-- 동 셀렉스 박스 레이아웃 -->
    <v-layout>
      <v-flex>
        <h2 class="my-5">동네 구경하기</h2>
        <DongSelectBar />
      </v-flex>
    </v-layout>
  </v-container>
</template>
<script>
import DongSelectBar from '@/components/aboutdong/DongSelectBar.vue';
import SuggestDong from '@/components/ranking/SuggestDong.vue';
import Banner from '@/view/layout/Banner.vue';

export default {
  components: {
    Banner,
    SuggestDong,
    DongSelectBar,
  },
};
</script>
<style scoped>
h2 {
  text-align: center;
}
</style>
