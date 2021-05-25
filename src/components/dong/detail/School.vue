<template lang="">
  <div>
    <v-card class="mx-auto pa-3" elevation="5" shaped>
      <v-card-title>
        <h2 style="font-weight: bold">학군 정보</h2>
      </v-card-title>
      <v-card-subtitle class="ml-1" style="font-size: 1.2em">
        {{ currentDongInfo.sidoName }}
        {{ currentDongInfo.gugunName }}
        {{ currentDongInfo.dongName }}
        학군 정보
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
          <div v-if="isZero" class="zeroData">😥 해당 동에 데이터가 없습니다!</div>
          <v-flex>
            <!-- <BarChart
              style="width: 300px; height: 300px"
              v-if="!isZero && datasets"
              :labels="labels"
              :datasets="datasets"
            /> -->
          </v-flex>
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
      isZero: true,
      data: [0, 0, 0, 0],
      labels: ['대기배출', '폐수배출', '기타수질원', '유목물판매'],
      datasets: null,
      //카드뷰 확장 트리거
      show: false,
    };
  },
  computed: {
    ...mapGetters(['currentDongInfo']),
  },
  created() {
    //상가정보 리스트 얻어오기
    const SERVICE_URL1 =
      'http://www.career.go.kr/cnet/openapi/getOpenApi?apiKey=cb19980074f55576221ec7641e4cb5a2&svcType=api&svcCode=SCHOOL&contentType=xml&gubun=elem_list';

    const SERVICE_URL2 =
      'http://www.career.go.kr/cnet/openapi/getOpenApi?apiKey=cb19980074f55576221ec7641e4cb5a2&svcType=api&svcCode=SCHOOL&contentType=json&gubun=elem_list';

    http.get(SERVICE_URL2).then((response) => {
      let envInfoList = response.data;
      console.log('envInfoList');
      console.log(envInfoList.dataSearch.content);

      // //모든 업소 타입을 조사해서 타입별 숫자를 샌다 (this.data)
      // for (let envInfo of envInfoList) {
      //   //얻어온 업소 정보의 타입을 찾아서 cnt 숫자 증가
      //   if (envInfo.type.indexOf('대기') != -1) {
      //     this.data[0]++;
      //   } else if (envInfo.type.indexOf('폐수') != -1) {
      //     this.data[1]++;
      //   } else if (envInfo.type.indexOf('수질') != -1) {
      //     this.data[2]++;
      //   } else if (envInfo.type.indexOf('유독물') != -1) {
      //     this.data[3]++;
      //   }
      //   if (this.isZero) this.isZero = false;
      // }
      // this.datasets = [
      //   {
      //     label: '업소 분류',
      //     backgroundColor: '#974e90',
      //     data: this.data,
      //   },
      // ];
    });
  },
};
</script>
<style scoped>
.zeroData {
  padding: 30px;
  text-align: center;
  font-size: 1.5em;
  font-weight: bold;
  color: #974e90;
}
</style>
