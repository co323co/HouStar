<template lang="">
  <v-container>
    <v-card>
      <v-toolbar class="mb-1" flat>
        <h1 class="display-5 mt-4">전체 리뷰 ( {{ totalReviewCount }} 명 )</h1>
      </v-toolbar>
      <v-divider></v-divider>
      <!-- Total 별점 평균 뿌리기 -->
      <v-flex>
        <star-rating
          v-if="AvgRating"
          :increment="0.01"
          :fixed-points="2"
          class="ml-5"
          :rating="AvgRating"
          read-only
          show-rating
        ></star-rating>
      </v-flex>
      <v-divider></v-divider>
      <!-- 개별 차트 평균 뿌리기 -->
      <div align="center">
        <HorizontalBar
          v-if="rating"
          :chart-data="datacollection"
          :class="size"
          :options="options"
        ></HorizontalBar>
      </div>
      <v-divider></v-divider>
      <!-- 리뷰 등록 -->

      <review-register />
      <v-divider></v-divider>

      <!-- 리뷰 셀렉트박스로 필터링하기 -->

      <v-row class=" justify-sm-center mt-5">
        <v-col class="mx-2">
          <v-select
            hint="선호 태그"
            label="ALL"
            v-model="tag_val"
            :items="tags"
            no-data-text="항목이 없습니다"
            solo
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
            solo
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
            solo
            @change="changeAge()"
          ></v-select>
        </v-col>
      </v-row>

      <!-- 리뷰 리스트 뿌리기 !!!!!!-->
    </v-card>
    <v-row v-if="isWrite">
      <v-container class="reviewback">
        <h1 align="center" class="mt-10">리뷰 목록 ✍ 총 {{ show_list.length }} 명</h1>

        <review-list-item v-for="(review, idx) in show_list" :key="idx" :review="review" />
      </v-container>
    </v-row>
    <v-flex v-else>
      <v-container class="reviewback">
        리뷰를 작성하지 않으면 글을 볼 수 없습니다.
      </v-container>
    </v-flex>
  </v-container>
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
            backgroundColor: 'pink',
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
      options: {
        responsive: true,
        maintainAspectRatio: false,
        scales: {
          xAxes: [
            {
              ticks: {
                beginAtZero: true,
              },
            },
          ],
        },
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
    console.log(this.rating);
    console.log(this.rating.environment);
    console.log('this.rating');
    // 해당 동에 대한 모든 리뷰 다 가져오기.
    this.$store.dispatch('getReviews', this.$store.state.dongStore.Sidogugundong.dongCode);
    // 해당 동에 대한 로그인한 유저의 모든 리뷰 가져오기
    this.$store.dispatch('getReviewsByUserId', this.currentUser.userid);
    if (this.reviewsbyuserid.length != 0) this.isWrite = true;
    this.show_list = [...this.reviews];
  },
};
</script>
<style scoped>
.firstClass {
  padding-top: 30px;
  display: block;
  height: 350px;
  width: 350px;
  margin-bottom: 50px;
}
.v-application--wrap {
  min-height: 0;
}
</style>
