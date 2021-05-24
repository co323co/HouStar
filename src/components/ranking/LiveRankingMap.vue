<template lang="">
  <v-layout class="pa-5">
    <v-row style="text-align: center">
      <v-col class="rank">
        <div style="height: 30%"></div>
        <live-ranking :dong_list="dong_list.slice(0, 5)" />
      </v-col>
      <v-col cols="8">
        <v-row class="no-gutters">
          <v-spacer></v-spacer>
          <v-col class="mx-2">
            <v-select
              hint="가구 타입"
              label="ALL"
              v-model="familyType_val"
              :items="familyTypes"
              no-data-text="항목이 없습니다"
              dense
            ></v-select>
          </v-col>
          <v-col class="mx-2">
            <v-select
              hint="연령대"
              label="ALL"
              v-model="ageRange_val"
              :items="ageRanges"
              no-data-text="항목이 없습니다"
              dense
            ></v-select>
          </v-col>
        </v-row>
        <v-divider></v-divider>
        <kakao-map :init_pos="map_init_pos" />
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
      map_init_pos: { lat: '0', lng: '0' },
      rating_list: [],
      dong_list: [],
      //가구 형태 목록 (1선택)
      familyType_val: null,
      familyTypes: ['자취생', '직장인', '신혼부부', '일반가족'],
      //연령대 목록 (1선택)
      // ageRange_val: null,
      ageRange_val: null,
      ageRanges: [
        { text: '10대', value: 10 },
        { text: '20대', value: 20 },
        { text: '30대', value: 30 },
        { text: '40대', value: 40 },
        { text: '50대', value: 50 },
        { text: '그 이상', value: 'over' },
      ],
    };
  },
  mounted() {
    this.getList();
  },
  created() {
    let first_dong = this.dong_list[0];
    console.log(this.dong_list);
    // let list = this.dong_list;

    // console.log(list[0]);
    // this.map_init_pos = {}
  },
  methods: {
    async getList() {
      let response = await http.get('/dongreview/avg-rating');
      this.rating_list = response.data;
      // 총 평점 높은 순으로 정렬
      this.rating_list.sort(function (a, b) {
        return b.total - a.total;
      });
      // 동코드로 SigugundongDto 찾기
      for (let rating of this.rating_list) {
        response = await http.get('/address/' + rating.dongcode);
        let dong = response.data;
        this.dong_list.push(dong);
      }
    },
  },
};
</script>
<style scoped></style>
