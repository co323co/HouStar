<!-- 상세정보 페이지 -->
<template>
  <v-container class="pa-3">
    <kakao-map-category v-if="map_init_pos" :init_pos="map_init_pos" />

    <!-- 첫번째 행 -->
    <v-row style="margin-top: 80px">
      <!-- 1열 -->
      <v-col>
        <infra />
      </v-col>
      <!-- 2열 -->
      <v-col>
        <trans />
      </v-col>
      <!-- 3열 -->
      <v-col>
        <safety />
      </v-col>
    </v-row>
    <!-- 두번째 행 -->
    <v-row>
      <!-- 1열 -->
      <v-col>
        <health />
      </v-col>
      <!-- 2열 -->
      <v-col>
        <school />
      </v-col>
      <!-- 3열 -->
      <v-col>
        <environment />
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
import http from '@/core/services/http-common';

import Infra from '@/components/dong/detail/Infra.vue';
import Trans from '@/components/dong/detail/Trans.vue';
import Safety from '@/components/dong/detail/Safety.vue';
import Health from '@/components/dong//detail/Health.vue';
import School from '@/components/dong//detail/School.vue';
import Environment from '@/components/dong/detail/Environment.vue';
import KakaoMapCategory from '@/components/map/KakaoMapShowCategory.vue';
//현재페이지를 무슨 동을 클릭해서 들어왔는지 정보 담겨있음
import { mapGetters } from 'vuex';
export default {
  components: {
    Infra,
    Trans,
    Safety,
    Health,
    School,
    Environment,
    KakaoMapCategory,
  },
  data() {
    return {
      map_init_pos: null,
    };
  },
  computed: {
    ...mapGetters(['currentDongInfo']),
  },
  created() {
    // console.log('info', this.currentDongInfo);
    // map props 설정
    http.get('/address/pos/dong/' + this.currentDongInfo.dongCode).then(({ data }) => {
      let pos = data;
      this.map_init_pos = { lat: pos.lat, lng: pos.lng };
    });
  },
  methods: {},
};
</script>
<style scoped></style>
