<template lang="">
  <div class="justify-center">
    <!-- 배너 사진 띄우는 레이아웃 -->
    <banner />
    <!-- 동 셀렉스 박스 레이아웃 -->
    <dong-selectBar />
    <!-- 추천 동네 레이아웃 -->
    <suggest-dong />
    <v-divider></v-divider>
    <live-ranking-map />
  </div>
</template>
<script>
import Banner from '@/view/layout/Banner.vue';
import DongSelectBar from '@/components/dong/DongSelectBar.vue';
import SuggestDong from '@/components/ranking/SuggestDong.vue';
import LiveRankingMap from '@/components/ranking/LiveRankingMap.vue';
//
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
