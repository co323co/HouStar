<template lang="">
  <div>
    <v-card class="mx-auto pa-3" elevation="5" shaped>
      <v-card-title>
        <h2 style="font-weight: bold">안전 등급</h2>
      </v-card-title>
      <v-divider></v-divider>
      <v-card-subtitle class="ml-1" style="font-size: 1.2em">
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
            <LineChart
              style="width: 300px; height: 300px"
              v-if="chartDataForLineChart"
              :chart-data="chartDataForLineChart"
              :options="optionsForLineChart"
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
// import BarChart from '@/components/charts/BarChart.vue';
import LineChart from '@/components/charts/LineChart.vue';
export default {
  components: {
    // BarChart,
    LineChart,
  },
  data() {
    return {
      chartDataForLineChart: Object,
      optionsForLineChart: Object,
      data: [],
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

        this.chartDataForLineChart = {
          labels: ['교통사고', '화재', '범죄', '생활안전', '자살', '감염병'],
          datasets: [
            {
              label: '안전 등급 분류',
              backgroundColor: 'rgb(239, 83, 80)',
              pointBackgroundColor: 'rgb(239, 83, 80)',
              fill: false,
              borderColor: 'rgb(239, 83, 80)',
              tension: 0.1,
              data: this.data,
            },
          ],
        };
        this.optionsForLineChart = {
          responsive: true,
          scales: {
            yAxes: [
              {
                display: true,
                ticks: {
                  beginAtZero: true,
                  max: 5,
                  min: 0,
                  stepSize: 1,
                },
              },
            ],
            xAxes: [
              {
                gridLines: {
                  zeroLineColor: 'black',
                  zeroLineWidth: 2,
                },
                ticks: {
                  min: 0,
                  max: 6500,
                  stepSize: 1,
                },
                scaleLabel: {
                  display: true,
                  labelString: 'Density in kg/m3',
                },
              },
            ],
          },
        };
      });

    // console.log('this.datasets');
    //console.log(this.datasets);
  },
};
</script>
<style scoped></style>
