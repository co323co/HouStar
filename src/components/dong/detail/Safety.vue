<template lang="">
  <div>
    <v-card class="mx-auto pa-3" elevation="5" shaped>
      <v-card-title>
        <h2 style="font-weight: bold">안전 등급</h2>
      </v-card-title>
      <v-card-subtitle class="ml-1" style="font-size: 1.2em">
        {{ currentDongInfo.sidoName }}
        {{ currentDongInfo.gugunName }}
        {{ currentDongInfo.dongName }}
        안전 등급 정보
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
          <div align="center">
            <BarChart
              style="width: 300px; height: 300px"
              v-if="datasets"
              :labels="labels"
              :datasets="datasets"
            />
          </div>
        </div>
      </v-expand-transition>
    </v-card>
  </div>
</template>
<script>
import { mapGetters } from 'vuex';
import http from '@/core/services/http-common';
import BarChart from '@/components/charts/BarChart.vue';
export default {
  components: {
    BarChart,
  },
  data() {
    return {
      data: [],
      labels: ['교통사고', '화재', '범죄', '생활안전', '자살', '감염병'],
      datasets: null,
      //카드뷰 확장 트리거
      show: false,
    };
  },
  computed: {
    ...mapGetters(['currentDongInfo']),
  },
  created() {
    //안전등급 dto
    console.log('안전등급찍을거야');
    // console.log('안전등급찍을거야');
    // console.log('안전등급찍을거야');
    http
      .get('/crimerank/' + this.currentDongInfo.sidoName + '/' + this.currentDongInfo.gugunName)
      .then((response) => {
        console.log(response.data);

        this.data.push(response.data.traffic_accident);
        this.data.push(response.data.fire);
        this.data.push(response.data.crime);
        this.data.push(response.data.lifesafety);
        this.data.push(response.data.suicid);
        this.data.push(response.data.epidemic);
        console.log('this.data 뽑을거임!!');
        console.log(this.data);
        this.datasets = [
          {
            label: '안전 등급 분류',
            backgroundColor: '#e481af',
            data: this.data,
            options: this.options,
          },
        ];
      });

    // console.log('this.datasets');
    //console.log(this.datasets);
  },
};
</script>
<style scoped></style>
