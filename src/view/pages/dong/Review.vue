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

        <!-- 리뷰 셀렉트박스로 필터링하기 -->

        <v-row class="no-gutters justify-sm-center">
          <v-col class="mx-2">
            <v-select
              hint="선호 태그"
              label="ALL"
              v-model="tag_val"
              :items="tags"
              no-data-text="항목이 없습니다"
              dense
              @change="changeTag()"
            ></v-select>
          </v-col>
          <v-col class="mx-2">
            <v-select
              hint="가구 타입"
              label="ALL"
              v-model="familyType_val"
              :items="familyTypes"
              no-data-text="항목이 없습니다"
              dense
            ></v-select>
          </v-col>
          <v-col class="mx-2">
            <v-select
              hint="연령대"
              label="ALL"
              v-model="ageRange_val"
              :items="ageRanges"
              no-data-text="항목이 없습니다"
              dense
            ></v-select>
          </v-col>
        </v-row>

        <!-- 리뷰 리스트 뿌리기 -->
        <v-flex>
          <v-container>
            <review-list-item v-for="(review, idx) in show_list" :key="idx" :review="review" />
          </v-container>
        </v-flex>
      </v-card>
    </v-container>
  </v-app>
</template>
<script>
import { mapState } from 'vuex';
import { mapActions, mapGetters } from 'vuex';
import http from '@/core/services/http-common';
import StarRating from 'vue-star-rating';
import HorizontalBar from '@/core/services/HorizontalBarChart.js';
// import ReviewList from '@/components/aboutdong/review/ReviewList.vue';
import ReviewListItem from '@/components/aboutdong/review/ReviewListItem.vue';
import ReviewRegister from '@/components/aboutdong/review/ReviewRegister.vue';

export default {
  methods: {
    //1 선호태그 변경시
    changeTag() {
      if (this.tag_val) {
        // console.log('tag_val에 값이 있다.');
        // console.log(this.show_list);
        console.log(this.reviews);
        this.show_list = this.reviews.filter((reviews) => {
          // console.log('들어옴');
          // console.log(reviews);

          return reviews.tag.includes(this.tag_val);
        });
        console.log('filter 거친 this.show_list ');

        console.log(this.show_list);
      }
      //2 가구형태 변경시
      //3 나이대 변경시
    },
  },
  computed: {
    // namespace true로 할 경우 module명 / getter 이름
    // 다른 모듈이니까 분리해서 써주기 review만 namespace true.
    ...mapGetters('review', ['totalReviewCount']),
    ...mapGetters(['currentUser']),
    ...mapState('review', ['reviews']),
  },
  data() {
    return {
      //선호 태그 목록 (1선택)
      tag_val: null,
      tags: ['인프라', '대중교통', '안전', '건강', '학군', '환경'],
      //가구 형태 목록 (1선택)
      familyType_val: null,
      familyTypes: ['자취생', '직장인', '신혼부부', '일반가족'],
      //연령대 목록 (1선택)
      // ageRange_val: null,
      ageRange_val: null,
      ageRanges: [
        { text: '10대', value: 10 },
        { text: '20대', value: 20 },
        { text: '30대', value: 30 },
        { text: '40대', value: 40 },
        { text: '50대', value: 50 },
        { text: '그 이상', value: 'over' },
      ],

      show_list: '',
      totalRating: 0,
      size: 'firstClass',

      datacollection: {
        labels: ['환경🌎', '건강💊', '인프라🍙', '안전🚔', '학군🎒', '대중교통🚦'],
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
    // ReviewList,
    ReviewRegister,
    ReviewListItem,
  },
  created() {
    // 해당 동에 대한 모든 리뷰 다 가져오기.
    // namespace true로 할경우 모듈명 / action 명
    this.$store.dispatch('review/getReviews', this.$store.state.dongStore.Sidogugundong.dongCode);
    // 해당 동에 대한 로그인한 유저의 모든 리뷰 가져오기
    // console.log('this.currentUser.userid', this.reviews);
    this.$store.dispatch('review/getReviewsByUserId', this.currentUser.userid);
    //created 될 때 보여줄 show_list 를 복사
    this.show_list = [...this.reviews];
    console.log(this.show_list);
  },
  mounted() {
    //console.log('mounted');
    //console.log(this.$store.state.dongStore.Sidogugundong.dongCode);
    // 해당 동의 평균 별점정보를가져옴/////ㅁㅁㅁㅁㅁㅁ
    http
      .get('/dongreview/avg-rating/' + this.$store.state.dongStore.Sidogugundong.dongCode)
      .then(({ data }) => {
        //console.log('평균뵬점정보 받아오기 실행 ');
        //console.log(data);
        // Rating.module 에 있는 rating 객체에 얻어온 평균평점 객체 넣음
        this.$store.state.rate.rating = data;
        this.totalRating = this.$store.state.rate.rating.total;
        //console.log('여기별점', this.totalRating);
        //console.log('스토어별점', this.$store.state.rate.rating.total);
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
