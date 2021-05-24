<template lang="">
  <v-container class="justify-center">
    <!-- 배너 사진 띄우는 레이아웃 -->
    <banner />
    <!-- 추천 동네 레이아웃 -->
    <suggest-dong />
    <!-- 동 셀렉스 박스 레이아웃 -->
    <v-layout class="mb-5">
      <v-flex>
        <h2 class="my-5">동네 구경하기</h2>
        <dong-selectBar />
      </v-flex>
    </v-layout>
    <v-divider></v-divider>
    <v-flex>
      <live-ranking-map />
    </v-flex>
  </v-container>
</template>
<script>
import Banner from '@/view/layout/Banner.vue';
import DongSelectBar from '@/components/aboutdong/DongSelectBar.vue';
import SuggestDong from '@/components/ranking/SuggestDong.vue';
import LiveRankingMap from '@/components/ranking/LiveRankingMap.vue';

export default {
  components: {
    Banner,
    SuggestDong,
    DongSelectBar,
    LiveRankingMap,
  },
};
</script>
<style scoped>
h2 {
  text-align: center;
}
</style>
