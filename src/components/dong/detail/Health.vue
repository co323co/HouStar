<template lang="">
  <div>
    <v-card class="mx-auto pa-3" elevation="5" shaped>
      <v-card-title>
        <h2 style="font-weight: bold">건강 관련 정보</h2>
      </v-card-title>
      <v-card-subtitle class="ml-1" style="font-size: 1.2em">
        {{ currentDongInfo.sidoName }}
        {{ currentDongInfo.gugunName }}
        {{ currentDongInfo.dongName }}
        건강 관련 정보
      </v-card-subtitle>
      <v-card-actions @click="show = !show">
        <v-btn color="orange lighten-2" text> <h6 style="font-weight: bold">더 보기</h6> </v-btn>
        <v-spacer></v-spacer>
        <v-btn icon class="d-inline-flex pa-2">
          <v-icon>{{ show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
        </v-btn>
      </v-card-actions>
      <!-- 확장 부분 -->
      <v-expand-transition>
        <div v-if="show">
          <v-divider></v-divider>
          <!-- 아이콘으로 병원, 코로나 선별진료소 갯수 뿌리기 -->
          <v-card class="d-flex flex-row" flat tile>
            <v-btn fab dark small color="pink">
              <v-icon dark>favorite</v-icon>
            </v-btn>

            <!-- <h1 v-if="this.hospitals.length != 0">안심 병원 {{ hospitals.length }}</h1> -->
            <v-card-title v-if="this.hospitals.length != 0">
              안심 병원 {{ hospitals.length }} 개
            </v-card-title>
          </v-card>

          <v-card class="d-flex flex-row" flat tile>
            <v-btn fab dark small color="pink">
              <v-icon dark>favorite</v-icon>
            </v-btn>

            <!-- <h1 v-if="this.hospitals.length != 0">안심 병원 {{ hospitals.length }}</h1> -->
            <v-card-title v-if="this.hospitals.coronas != 0">
              코로나 선별 진료소 {{ coronas.length }} 개
            </v-card-title>
          </v-card>
        </div>
      </v-expand-transition>
    </v-card>
  </div>
</template>
<script>
import { mapGetters } from 'vuex';
import http from '@/core/services/http-common';
// import BarChart from '@/components/charts/BarChart.vue';
export default {
  components: {
    // BarChart,
  },
  data() {
    return {
      coronas: [],
      hospitals: [],
      data: [],
      labels: ['안심 병원', '코로나 선별 진료소'],
      datasets: null,
      //카드뷰 확장 트리거
      show: false,
    };
  },
  computed: {
    ...mapGetters(['currentDongInfo']),
  },
  created() {
    console.log(this.currentDongInfo.gugunCode);

    http.get('/health/hospitals/' + this.currentDongInfo.gugunCode).then((response) => {
      this.hospitals = response.data;

      http.get('/health/coronas/' + this.currentDongInfo.gugunCode).then((response) => {
        console.log('response.data coronas');
        console.log(response.data);

        this.coronas = response.data;

        this.data.push(this.hospitals.length);
        this.data.push(this.coronas.length);
        console.log('this.hospitals.length');
        console.log(this.hospitals.length);
        // this.datasets = [
        //   {
        //     label: '안전 등급 분류',
        //     backgroundColor: '#e481af',
        //     data: this.data,
        //     options: this.options,
        //   },
        // ];
      });
    });
  },
};
</script>
<style scoped>
h1 {
  /* margin-bottom: 0; */
}
</style>
