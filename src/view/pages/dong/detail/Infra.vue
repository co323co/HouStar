<template lang="">
  <div>
    <!-- <BarChart v-if="datasets" :labels="labels" :datasets="datasets" /> -->
    <BarChart />
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
      data: [0, 0, 0, 0, 0, 0, 0, 0],
      labels: [
        '관광/여가/오락',
        '부동산',
        '소매',
        '생활서비스',
        '숙박',
        '스포츠',
        '음식',
        '학문/교육',
      ],
      datasets: null,
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
      this.datasets = [
        {
          label: '상가 대분류',
          backgroundColor: '#e481af',
          data: this.data,
        },
      ];
    });
  },
};
</script>
<style scoped></style>
