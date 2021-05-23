<template lang="">
  <v-app>
    <v-container>
      <v-card flat>
        <v-toolbar flat class="mb-1">
          <v-toolbar-title>
            <h1 class="display-5 mt-4">전체 리뷰 ( {{ reviews.length }} 명 ), 총 별점, 차트</h1>
          </v-toolbar-title>
        </v-toolbar>

        <v-flex>
          <star-rating
            :increment="0.01"
            :fixed-points="2"
            class="ml-5"
            :rating="Rating"
            read-only
            show-rating
          ></star-rating>
        </v-flex>
        <v-flex>
          <HorizontalBar :chart-data="datacollection" :class="size"></HorizontalBar>
        </v-flex>
        <!-- <div id="chart">
          <apexchart type="bar" height="350" :options="chartOptions" :series="series"></apexchart>
        </div> -->
      </v-card>
    </v-container>
  </v-app>
</template>
<script>
import http from '@/core/services/http-common';
import StarRating from 'vue-star-rating';
//chart test
// import BarChart from '@/components/aboutdong/Barchart.vue';
import HorizontalBar from '@/core/services/HorizontalBarChart.js';
// import ApexCharts from 'apexcharts';
export default {
  data() {
    return {
      Rating: 0,
      reviews: [],
      size: 'firstClass',
      datacollection: {
        labels: ['환경🌎', '건강💊', '인프라🍙', '안전🚔', '학군🎒', '교통🚦'],
        datasets: [
          {
            label: '전체 리뷰 통계',
            backgroundColor: 'gray',
            data: [
              this.$store.state.rate.rating.environment,
              this.$store.state.rate.rating.health,
              this.$store.state.rate.rating.infra,
              this.$store.state.rate.rating.safety,
              this.$store.state.rate.rating.school,
              this.$store.state.rate.rating.trans,
            ],
          },
        ],
      },
    };
  },
  components: {
    StarRating,
    // apexchart: ApexCharts,
    HorizontalBar,
    // BarChart,
  },
  mounted() {
    console.log('mounted');
    // 해당 동의 평균 별점정보를가져옴
    http
      .get('/dongreview/avg-rating/' + this.$store.state.dongStore.Sidogugundong.dongCode)
      .then(({ data }) => {
        console.log(data);
        this.$store.state.rate.rating = data;
        this.Rating = this.$store.state.rate.rating.total;
        console.log('총! 평균! :      ' + this.$store.state.rate.rating.total);
      })
      .catch(({ response }) => {
        console.log(response);
      });
    //해당 동의 모든 리뷰 가져옴
    http
      .get('/dongreview/dong/' + this.$store.state.dongStore.Sidogugundong.dongCode)
      .then(({ data }) => {
        console.log(data);
        this.reviews = data;
        //this.$store.state.rate.rating = data;
        //this.Rating = this.$store.state.rate.rating.total;
        //console.log('총! 평균! :      ' + this.$store.state.rate.rating.total);
      })
      .catch(({ response }) => {
        console.log(response);
      });
  },
};
</script>
<style scope>
.firstClass {
  padding-top: 30px;
  height: 300px;
  width: 300px;
  margin-bottom: 50px;
}
.v-application--wrap {
  min-height: 1vh;
}
</style>
