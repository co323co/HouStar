<template lang="">
  <div>
    <v-card class="mx-auto pa-3" elevation="5" shaped>
      <v-card-title>
        <h2 style="font-weight: bold">
          <div class="head">인프라</div>
        </h2>
      </v-card-title>
      <v-divider></v-divider>
      <v-card-subtitle class="ml-1" style="font-size: 1.2em">
        {{ currentDongInfo.dongName }}
        상가 정보
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
            <PieChart
              style="width: 300px; height: 330px"
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
// import BarChart from '@/components/charts/BarChart.vue';
import PieChart from '@/components/charts/PieChart.vue';
export default {
  components: {
    PieChart,
  },
  data() {
    return {
      data: [0, 0, 0, 0, 0, 0, 0, 0],
      labels: [
        '관광/여가/오락🎠',
        '부동산💼',
        '소매🍜',
        '생활서비스✅',
        '숙박🏰',
        '스포츠🚴‍♀️',
        '음식🍒',
        '학문/교육📙',
      ],
      datasets: null,
      //카드뷰 확장 트리거
      show: false,
    };
  },
  computed: {
    ...mapGetters(['currentDongInfo']),
  },
  created() {
    console.log(this.currentDongInfo);
    //상가정보 리스트 얻어오기
    http.get('/store/' + this.currentDongInfo.dongCode).then((response) => {
      let storeList = response.data;
      //모든 상점의 타입을 조사해서 타입별 숫자를 샌다 (this.data)
      for (let store of storeList) {
        //얻어온 상점 정보의 타입을 찾아서 cnt 숫자 증가
        for (let i in this.labels) {
          if (store.type == this.labels[i]) {
            this.data[i]++;
            break;
          }
        }
      }
      console.log(this.data);
      console.log('이거 위에 상가정보임');
      this.datasets = [
        {
          hoverOffset: 4,

          label: '상가 대분류',
          backgroundColor: [
            'rgb(255, 99, 132)',
            'rgb(54, 162, 235)',
            'rgb(255, 205, 86)',
            '#AAD8FF',
            '#AAD8FF',
            '#AAD8FF',
            '#FFD8FF',
            '#00D8FF',
          ],
          data: this.data,
        },
      ];
    });
  },
};
</script>
<style scoped>
.head {
  display: inline-block;
  height: 100%;
}
</style>
