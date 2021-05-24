<template lang="">
  <v-layout class="pa-5">
    <v-row style="text-align: center">
      <v-col class="rank">
        <div style="height: 30%"></div>
        <live-ranking :dong_list="dong_list.slice(0, 5)" />
      </v-col>
      <v-col cols="8" class="first_dong_text">
        <h2 class="mr-4">🥇 현재 1등</h2>
        <h1 v-if="dong_list[0]">{{ dong_list[0].dongName }}</h1>
        <v-divider></v-divider>
        <!-- 부모가 데이터 서버에서 다 받고 나서야 넘겨줌 -->
        <kakao-map
          v-if="map_init_pos && map_marker_list"
          :init_pos="map_init_pos"
          :marker_list="map_marker_list"
        />
      </v-col>
    </v-row>
  </v-layout>
</template>
<script>
import http from '@/core/services/http-common';
import LiveRanking from '@/components/ranking/LiveRanking.vue';
import KakaoMap from '@/components/KakaoMap.vue';

export default {
  components: {
    LiveRanking,
    KakaoMap,
  },
  data() {
    return {
      map_init_pos: null,
      map_marker_list: null,
      rating_list: [],
      dong_list: [],
    };
  },
  created() {
    this.setPropsByHttp();
  },
  methods: {
    async setPropsByHttp() {
      //rating_list 설정
      let response = await http.get('/dongreview/avg-rating');
      this.rating_list = response.data;
      // 총 평점 높은 순으로 정렬
      this.rating_list.sort(function (a, b) {
        return b.total - a.total;
      });

      //dong_list 설정
      // 동코드로 SigugundongDto 찾기
      for (let rating of this.rating_list) {
        response = await http.get('/address/' + rating.dongcode);
        let dong = response.data;
        this.dong_list.push(dong);
      }
      let first_rating = this.rating_list[0];

      // map props 설정
      response = await http.get('/address/pos/dong/' + first_rating.dongcode);
      let pos = response.data;
      this.map_init_pos = { lat: pos.lat, lng: pos.lng };
      this.map_marker_list = [{ position: this.map_init_pos, text: '⭐' + first_rating.total }];
    },
  },
};
</script>
<style scoped>
.first_dong_text h1,
h2 {
  display: inline-block;
}
.first_dong_text h1 {
  color: darkcyan;
  font-weight: bold;
}
</style>
