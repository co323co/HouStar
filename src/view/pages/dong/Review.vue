<template lang="">
  <v-app>
    <v-container>
      <v-card flat>
        <v-toolbar flat class="mb-1">
          <v-toolbar-title>
            <h1 class="display-5 mt-4">전체 리뷰 ( {{ totalReviewCount }} 명 )</h1>
          </v-toolbar-title>
        </v-toolbar>
        <!-- Total 별점 평균 뿌리기 -->
        <v-flex>
          <star-rating
            :increment="0.01"
            :fixed-points="2"
            class="ml-5"
            :rating="totalRating"
            read-only
            show-rating
          ></star-rating>
        </v-flex>
        <!-- 개별 차트 평균 뿌리기 -->
        <v-flex>
          <HorizontalBar :chart-data="datacollection" :class="size"></HorizontalBar>
        </v-flex>
        <!-- 리뷰 등록 -->
        <v-flex>
          <review-register />
        </v-flex>
        <!-- 리뷰 리스트 뿌리기 -->
        <v-flex>
          <review-list />
        </v-flex>
      </v-card>
    </v-container>
  </v-app>
</template>
<script>
import { mapActions, mapGetters } from 'vuex';
import http from '@/core/services/http-common';
import StarRating from 'vue-star-rating';
import HorizontalBar from '@/core/services/HorizontalBarChart.js';
import ReviewList from '@/components/aboutdong/review/ReviewList.vue';
import ReviewRegister from '@/components/aboutdong/review/ReviewRegister.vue';

export default {
  computed: {
    // namespace true로 할 경우 module명 / getter 이름
    // 다른 모듈이니까 분리해서 써주기 review만 namespace true.
    ...mapGetters('review', ['totalReviewCount']),
    ...mapGetters(['currentUser']),
  },
  data() {
    return {
      totalRating: 0,
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
      // options 바인딩하는거더찾아보기 ㅜ
    };
  },
  components: {
    StarRating,
    HorizontalBar,
    ReviewList,
    ReviewRegister,
  },
  created() {
    // 해당 동에 대한 모든 리뷰 다 가져오기.
    // namespace true로 할경우 모듈명 / action 명
    this.$store.dispatch('review/getReviews', this.$store.state.dongStore.Sidogugundong.dongCode);
    // 해당 동에 대한 로그인한 유저의 모든 리뷰 가져오기
    //console.log(this.currentUser.userid);
    this.$store.dispatch('review/getReviewsByUserId', this.currentUser.userid);
  },
  mounted() {
    console.log('mounted');
    console.log(this.$store.state.dongStore.Sidogugundong.dongCode);
    // 해당 동의 평균 별점정보를가져옴/////
    http
      .get('/dongreview/avg-rating/' + this.$store.state.dongStore.Sidogugundong.dongCode)
      .then(({ data }) => {
        console.log('평균뵬점정보 받아오기 실행 ');
        console.log(data);
        // Rating.module 에 있는 rating 객체에 얻어온 평균평점 객체 넣음
        this.$store.state.rate.rating = data;
        this.totalRating = this.$store.state.rate.rating.total;
        console.log('여기별점', this.totalRating);
        console.log('스토어별점', this.$store.state.rate.rating.total);
      })
      .catch(({ response }) => {
        console.log(response);
      });
  },
};
</script>
<style scoped>
.firstClass {
  padding-top: 30px;
  display: block;
  height: 300px;
  width: 300px;
  margin-bottom: 50px;
}
.v-application--wrap {
  min-height: 0;
}
</style>
