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
            :rating="AvgRating"
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
              @change="changeFamilyType()"
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
              @change="changeAge()"
            ></v-select>
          </v-col>
        </v-row>

        <!-- 리뷰 리스트 뿌리기 -->
        <v-flex v-if="isWrite">
          <v-container>
            <review-list-item v-for="(review, idx) in show_list" :key="idx" :review="review" />
          </v-container>
        </v-flex>
        <v-flex v-else>
          <v-container> 리뷰를 작성하지 않으면 글을 볼 수 없습니다. </v-container>
        </v-flex>
      </v-card>
    </v-container>
  </v-app>
</template>
<script>
import { mapGetters } from 'vuex';
import http from '@/core/services/http-common';
import StarRating from 'vue-star-rating';
import HorizontalBar from '@/core/services/HorizontalBarChart.js';
import ReviewListItem from '@/components/dong/review/ReviewListItem.vue';
import ReviewRegister from '@/components/dong/review/ReviewRegister.vue';

export default {
  methods: {
    checkBar() {
      // 선호태그 감지
      if (this.tag_val) {
        console.log(this.reviews);
        this.show_list = this.reviews.filter((reviews) => {
          return reviews.tag.includes(this.tag_val);
        });
      }
      // 가구타입 감지
      if (this.familyType_val) {
        console.log(this.familyType_val);
        this.show_list = this.show_list.filter((reviews) => {
          if (this.familyType_val == reviews.family_type) return reviews;
        });
      }
      // 연령대 감지
      if (this.ageRange_val) {
        console.log(this.ageRange_val);
        this.show_list = this.show_list.filter((reviews) => {
          if (this.ageRange_val == reviews.age_range) return reviews;
        });
      }
    },
    //1 선호태그 변경시
    changeTag() {
      this.checkBar();
    },
    //2 가구형태 변경시
    changeFamilyType() {
      this.checkBar();
    },
    //3 나이대 변경시
    changeAge() {
      this.checkBar();
    },
  },
  watch: {
    reviews(val) {
      this.show_list = val;
      this.checkBar();
    },
    reviewsbyuserid(val) {
      if (val.length != 0) this.isWrite = true;
    },
  },
  computed: {
    ...mapGetters(['totalReviewCount', 'reviews', 'reviewsbyuserid', 'AvgRating', 'rating']),
    ...mapGetters(['currentUser']),
  },
  data() {
    return {
      isWrite: false,
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
    };
  },
  components: {
    StarRating,
    HorizontalBar,
    ReviewRegister,
    ReviewListItem,
  },
  created() {
    // 해당 동에 대한 평균별점정보 가져오기
    this.$store.dispatch('getRating', this.$store.state.dongStore.Sidogugundong.dongCode);
    // 해당 동에 대한 모든 리뷰 다 가져오기.
    this.$store.dispatch('getReviews', this.$store.state.dongStore.Sidogugundong.dongCode);
    // 해당 동에 대한 로그인한 유저의 모든 리뷰 가져오기
    this.$store.dispatch('getReviewsByUserId', this.currentUser.userid);
    if (this.reviewsbyuserid.length != 0) this.isWrite = true;
    this.show_list = [...this.reviews];
  },
  // mounted() {
  //   // 해당 동에 대한 평균별점정보 가져오기
  //   this.$store.dispatch('getRating', this.$store.state.dongStore.Sidogugundong.dongCode);
  //   // 해당 동에 대한 모든 리뷰 다 가져오기.
  //   this.$store.dispatch('getReviews', this.$store.state.dongStore.Sidogugundong.dongCode);
  //   // 해당 동에 대한 로그인한 유저의 모든 리뷰 가져오기
  //   this.$store.dispatch('getReviewsByUserId', this.currentUser.userid);
  //   if (this.reviewsbyuserid.length != 0) this.isWrite = true;
  //   this.show_list = [...this.reviews];
  // },
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
