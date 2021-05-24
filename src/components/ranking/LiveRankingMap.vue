<template lang="">
  <v-layout class="pa-5">
    <v-row style="text-align: center">
      <v-col class="rank">
        <div style="height: 30%"></div>
        <h4>🏆 실시간 랭킹</h4>
        <div class="pa-5">
          <div v-for="(dong, idx) in dong_list" :key="idx" @click="moveInfo(dong)">
            <mouse-over :msg="idx + 1 + `.  ` + dong.dongName" />
          </div>
        </div>
      </v-col>
      <v-col cols="8">
        <v-row class="no-gutters">
          <v-spacer></v-spacer>
          <v-col class="mx-1">
            <v-select no-data-text="항목이 없습니다" outlined dense></v-select>
          </v-col>
          <v-col class="mx-1">
            <v-select no-data-text="항목이 없습니다" outlined dense></v-select>
          </v-col>
          <v-col class="mx-1">
            <v-select no-data-text="항목이 없습니다" outlined dense></v-select>
          </v-col>
        </v-row>
        <v-divider></v-divider>
        <kakao-map />
      </v-col>
    </v-row>
  </v-layout>
</template>
<script>
import http from '@/core/services/http-common';
import MouseOver from '@/components/ranking/MouseOverText.vue';
import KakaoMap from '@/components/KakaoMap.vue';
export default {
  components: {
    MouseOver,
    KakaoMap,
  },
  data() {
    return {
      dong_list: [],
    };
  },

  mounted() {
    http.get('/dongreview/avg-rating').then(({ data }) => {
      //총 평점 높은 순으로 정렬
      data.sort(function (a, b) {
        return b.total - a.total;
      });
      //탑 5개까지 뽑음
      for (let i = 0; i < 5; i++) {
        //동코드로 SigugundongDto 찾기
        if (data.length <= i) return;
        http.get('/address/' + data[i].dongcode).then((dong_resonse) => {
          this.dong_list.push(dong_resonse.data);
        });
      }
    });
  },
  methods: {
    moveInfo(dongDto) {
      //   console.log(this.$store.state.dongStore.Sidogugundong);
      this.$store.state.dongStore.Sidogugundong = dongDto;
      //   console.log(this.$store.state.dongStore.Sidogugundong.dongName);
      this.$router.push('dong-info');
    },
  },
};
</script>
<style scoped>
.rank h4 {
  color: coral;
  font-weight: bold;
}
</style>
