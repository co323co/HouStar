<template lang="">
  <v-container>
    <v-card class="rounded-card">
      <div class="ml-9 pl-20 pt-3">
        <v-card-title>
          <h3>전체 리뷰 통계( {{ totalReviewCount }}명 )</h3>
        </v-card-title>

        <v-card-subtitle>
          <v-row align="center" class="ml-1"
            ><span class="display-1 pa-0 mr-1"
              ><b> {{ this.AvgRating.toFixed(1) }}</b>
            </span>
            <star-rating
              :star-size="20"
              :inline="true"
              v-if="AvgRating"
              :increment="0.01"
              :fixed-points="2"
              :rating="AvgRating"
              read-only
              :show-rating="false"
            ></star-rating
          ></v-row>
        </v-card-subtitle>
      </div>
      <!-- 개별 차트 평균 뿌리기 -->
      <div align="center">
        <v-row class="pa-3">
          <v-col cols="6">
            <HorizontalBar
              v-if="rating"
              :chart-data="chartDataForBarChart"
              :class="size"
              :options="optionsForBarChart"
            ></HorizontalBar> </v-col
          ><v-divider vertical></v-divider>
          <v-col cols="6">
            <RadarChart v-if="rating" :chart-data="chartDataForRadarChart" :class="size">
            </RadarChart> </v-col
        ></v-row>
      </div>
    </v-card>
    <!-- <v-divider></v-divider> -->
    <!-- 리뷰 등록 -->
    <v-card class="mt-10 rounded-card">
      <review-register />
      <v-divider></v-divider>
    </v-card>
    <!-- 리뷰 셀렉트박스로 필터링하기 -->
    <div align="center" class="mt-10">
      <p class="display-1 mr-1">
        <b>모아보기 ( 총 {{ show_list.length }} 명 )</b>
      </p>
    </div>
    <v-row class="justify-sm-center mt-2 px-2">
      <v-col class="mx-2">
        <v-select
          hide-details="true"
          hint="선호 태그"
          label="선호 태그"
          v-model="tag_val"
          :items="tags"
          no-data-text="항목이 없습니다"
          solo
          @change="changeTag()"
        ></v-select>
      </v-col>
      <v-col class="mx-2">
        <v-select
          hide-details="true"
          hint="가구 타입"
          label="가구 타입"
          v-model="familyType_val"
          :items="familyTypes"
          no-data-text="항목이 없습니다"
          solo
          @change="changeFamilyType()"
        ></v-select>
      </v-col>
      <v-col class="mx-2">
        <v-select
          hide-details="true"
          hint="연령대"
          label="연령대"
          v-model="ageRange_val"
          :items="ageRanges"
          no-data-text="항목이 없습니다"
          solo
          @change="changeAge()"
        ></v-select>
      </v-col>
      <v-col cols="1" class="text-center">
        <v-btn @click="reset" color="teal lighten-2" fab style="color: white">
          <v-icon> mdi-eraser </v-icon>
        </v-btn>
      </v-col>
    </v-row>

    <!-- 리뷰 리스트 뿌리기 !!!!!!-->

    <v-row v-if="isWrite">
      <v-container class="reviewback rounded-card">
        <!-- <h1 align="center" class="">리뷰 목록 ✍</h1> -->

        <review-list-item v-for="(review, idx) in show_list" :key="idx" :review="review" />
      </v-container>
    </v-row>
    <v-flex v-else>
      <v-container class="reviewback rounded-card text-center mt-5">
        <div class="mt-14"></div>
        <div class="no">첫 리뷰를 작성해야 볼 수 있습니다!</div>
      </v-container>
    </v-flex>
  </v-container>
</template>
<script>
import { mapGetters } from 'vuex';
import http from '@/core/services/http-common';
import StarRating from 'vue-star-rating';
import HorizontalBar from '@/core/services/HorizontalBarChart.js';
import RadarChart from '@/components/charts/RadarChart.vue';
import ReviewListItem from '@/components/dong/review/ReviewListItem.vue';
import ReviewRegister from '@/components/dong/review/ReviewRegister.vue';

export default {
  methods: {
    reset() {
      this.show_list = this.reviews;
      this.tag_val = null;
      this.familyType_val = null;
      this.ageRange_val = null;
    },
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

      chartDataForBarChart: {
        labels: ['환경🌎', '건강💊', '인프라🍙', '안전🚔', '학군🎒', '대중교통🚦'],
        datasets: [
          {
            label: '카테고리별 통계',
            backgroundColor: '#66BB6A',
            barThickness: 7,
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
      // radar chart에 넣을 데이터
      chartDataForRadarChart: {
        labels: ['환경', '건강', '인프라', '안전', '학군', '대중교통'],
        datasets: [
          {
            label: '카테고리별 통계',
            backgroundColor: 'rgba(27, 197, 189, 0.2)',
            borderColor: '#1bc5bd',
            // pointBackgroundColor: 'rgba(179,181,198,1)',
            pointBorderColor: '#fff',
            // pointHoverBackgroundColor: '#fff',
            // pointHoverBorderColor: 'rgba(179,181,198,1)',

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
      optionsForBarChart: {
        legend: {
          display: false,
        },
        elements: {
          bar: {
            borderWidth: 1,
          },
        },
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
    RadarChart,
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
    // console.log('this.reviewsbyuserid.length');
    // console.log(this.reviewsbyuserid.length);
    if (this.reviewsbyuserid.length != 0) this.isWrite = true;
    this.show_list = [...this.reviews];
  },
};
</script>
<style scoped>
.firstClass {
  display: block;
  height: 200px;
  width: 400px;
  margin-bottom: 10px;
  margin-top: 10px;
}
.v-application--wrap {
  min-height: 0;
  color: orange;
}
.rounded-card {
  border-radius: 50px;
}
.no {
  vertical-align: middle;
  font-weight: bold;
  font-size: 1.4em;
}
</style>
