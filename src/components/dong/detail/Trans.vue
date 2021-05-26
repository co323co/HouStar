<template lang="">
  <div>
    <v-card class="mx-auto pa-3" elevation="5" shaped>
      <v-card-title>
        <h2 style="font-weight: bold">대중교통</h2>
      </v-card-title>
      <v-divider></v-divider>
      <v-card-subtitle class="ml-1" style="font-size: 1.2em">
        {{ currentDongInfo.dongName }}
        지하철 정보
      </v-card-subtitle>
      <v-card-actions @click="show = !show">
        <v-btn color="orange lighten-2" text> <h6 style="font-weight: bold">더 보기</h6> </v-btn>
        <v-spacer></v-spacer>
        <v-btn icon>
          <v-icon>{{ show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
        </v-btn>
      </v-card-actions>
      <!-- 확장 부분 -->
      <v-expand-transition>
        <div v-if="show">
          <v-divider></v-divider>
          <v-flex class="mb-3">
            <kakao-map v-if="map_init_pos" :init_pos="map_init_pos" />
          </v-flex>
        </div>
      </v-expand-transition>
    </v-card>
  </div>
</template>
<script>
import { mapGetters } from 'vuex';
import http from '@/core/services/http-common';
import KakaoMap from '@/components/map/KakaoMapByCategory.vue';
export default {
  components: {
    KakaoMap,
  },
  data() {
    return {
      //카드뷰 확장 트리거
      show: false,
      map_init_pos: null,
    };
  },
  computed: {
    ...mapGetters(['currentDongInfo']),
  },
  created() {
    // console.log(this.currentDongInfo);
    // map props 설정
    console.log('trans created');
    http.get('/address/pos/dong/' + this.currentDongInfo.dongCode).then((response) => {
      let pos = response.data;
      // console.log('pos', pos);
      this.map_init_pos = { lat: pos.lat, lng: pos.lng };
      // console.log('map_init_pos', this.map_init_pos);
    });
  },
};
</script>
<style scoped></style>
