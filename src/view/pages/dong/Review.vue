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

        <ChartName />
      </v-card>
    </v-container>
  </v-app>
</template>
<script>
import http from '@/core/services/http-common';
import StarRating from 'vue-star-rating';
//chart test
import ChartName from '@/components/aboutdong/ChartName.vue';

export default {
  data() {
    return {
      Rating: 0,
      reviews: [],
    };
  },
  components: {
    StarRating,
    ChartName,
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
<style lang=""></style>
