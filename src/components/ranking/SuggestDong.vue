<template>
  <v-layout justify-center>
    <v-card class="pa-9 mt-7 mb-3" elevation="2" shaped width="80%">
      <h2>추천 동네</h2>
      <!-- 사용자 정보 -->
      <div class="user_info mt-2">
        <h5>{{ currentUser.tag | list }}</h5>
        <h5 v-if="currentUser.age_range" class="mx-2">|</h5>
        <h5>{{ currentUser.age_range | ageRange }}</h5>
        <h5 v-if="currentUser.family_type" class="mx-2">|</h5>
        <h5>{{ currentUser.family_type | familyType }}</h5>
      </div>
      <v-divider></v-divider>
      <!-- 동 랭킹 -->
      <v-row class="rank mt-1 mx-1">
        <v-col v-if="suggest_dong.age_range && currentUser.age_range">
          <h5 class="mb-3">{{ currentUser.age_range | ageRange }} 취향저격 순위</h5>
          <div v-for="(dong, idx) in suggest_dong.age_range" :key="idx" @click="moveInfo(dong)">
            <mouse-over :msg="icon_rank[idx] + ' ' + dong.dongName" />
          </div>
        </v-col>
        <v-col v-if="suggest_dong.family_type && currentUser.fmaily_type">
          <h5 class="mb-3">{{ currentUser.family_type | familyType }} 취향저격 순위</h5>
          <div v-for="(dong, idx) in suggest_dong.family_type" :key="idx" @click="moveInfo(dong)">
            <mouse-over :msg="icon_rank[idx] + ' ' + dong.dongName" />
          </div>
        </v-col>
        <v-col v-if="suggest_dong.tag && currentUser.tag">
          <h5 class="mb-3">{{ currentUser.tag | list }} 취향저격 순위</h5>
          <div v-for="(dong, idx) in suggest_dong.tag" :key="idx" @click="moveInfo(dong)">
            <mouse-over :msg="icon_rank[idx] + ' ' + dong.dongName" />
          </div>
        </v-col>
      </v-row>
    </v-card>
  </v-layout>
</template>

<script>
import http from '@/core/services/http-common';
import { mapGetters } from 'vuex';
import MouseOver from '@/components/ranking/MouseOverText.vue';

export default {
  components: {
    MouseOver,
  },
  data() {
    return {
      suggest_dong: {
        age_range: null,
        family_type: null,
        tag: null,
      },
      icon_rank: ['🥇', '🥈', '🥉'],
    };
  },
  mounted() {
    this.setSuggestDongs();
  },
  computed: {
    ...mapGetters(['currentUser']),
  },
  filters: {
    ageRange: function (ageRange) {
      if (!ageRange) return '';
      if (ageRange == 'over') return '60대 이상';
      else return ageRange + '대';
    },
    familyType: function (type) {
      if (!type) return '';
      else return type;
    },
    list: function (list) {
      if (!list) return '';
      let str = '';
      for (let i = 0; i < list.length - 1; i++) {
        str += list[i] + '     ·     ';
      }
      str += list[list.length - 1];
      return str;
    },
  },
  methods: {
    setSuggestDongs() {
      //로그인 유저의 연령대가 높게 평가한 동네 TOP3를 suggest_dong.age_range에 설정

      //로그인 유저의 연령대가 평가한 동 평점 리스트 받아오기
      http
        .get(`/dongreview/avg-rating/type?age_range=${this.currentUser.age_range}`)
        .then((rating_response) => {
          let rating_list = rating_response.data;
          // total 점수를 기준으로 내림차순 정렬
          rating_list.sort(function (a, b) {
            return b.total - a.total;
          });
          //   console.log('age_range : ', rating_list);
          this.suggest_dong.age_range = [];
          //탑 3개까지 뽑음
          for (let i = 0; i < 3; i++) {
            //동코드로 SigugundongDto 찾아서 suggest_dong.age_range에 넣음, (사용자의 연령대로 찾은 추천 동 목록)
            if (rating_list.length <= i) return;
            http.get('/address/' + rating_list[i].dongcode).then((dong_resonse) => {
              this.suggest_dong.age_range.push(dong_resonse.data);
            });
          }
        });

      //로그인 유저와 같은 가구타입이  높게 평가한 동네 TOP3를 suggest_dong.family_type에 설정

      //로그인 유저의 가구타입이 평가한 동 평점 리스트 받아오기
      http
        .get(`/dongreview/avg-rating/type?family_type=${this.currentUser.family_type}`)
        .then((rating_response) => {
          let rating_list = rating_response.data;
          // total 점수를 기준으로 내림차순 정렬
          rating_list.sort(function (a, b) {
            return b.total - a.total;
          });
          //   console.log('family_type : ', rating_list);
          this.suggest_dong.family_type = [];
          //탑 3개까지 뽑음
          for (let i = 0; i < 3; i++) {
            //동코드로 SigugundongDto 찾아서 suggest_dong.family_type 넣음, (사용자의 가구타입으로 찾은 추천 동 목록)
            if (rating_list.length <= i) return;
            http.get('/address/' + rating_list[i].dongcode).then((dong_resonse) => {
              this.suggest_dong.family_type.push(dong_resonse.data);
            });
          }
        });

      //로그인 유저가 선호하는 '타입들의 총합'이 높은 TOP3를  suggest_dong.tag에 넣음 (사용자의 선호태그로 찾은 추천 동 목록)

      //모든 동의 평점 정보 리스트 받아오기
      http.get('/dongreview/avg-rating').then((rating_response) => {
        let rating_list = rating_response.data;
        // '유저의 선호태그들'만으로 '평점 합'을 구하고, 그걸 기준으로 내림차순 정렬
        let user_tag = this.currentUser.tag;
        rating_list.sort(function (a, b) {
          let a_sum = 0;
          let b_sum = 0;
          //   해당 동의 태그들의 총 평점 합 구하기
          for (let tag of user_tag) {
            if (tag == '인프라') {
              a_sum += a.infra * 1.0;
              b_sum += b.infra * 1.0;
            } else if (tag == '대중교통') {
              a_sum += a.trans * 1.0;
              b_sum += b.trans * 1.0;
            } else if (tag == '안전') {
              a_sum += a.safety * 1.0;
              b_sum += b.safety * 1.0;
            } else if (tag == '건강') {
              a_sum += a.health * 1.0;
              b_sum += b.health * 1.0;
            } else if (tag == '학군') {
              a_sum += a.school * 1.0;
              b_sum += b.school * 1.0;
            } else if (tag == '환경') {
              a_sum += a.environment * 1.0;
              b_sum += b.environment * 1.0;
            }
          }
          return b_sum - a_sum;
        });
        // console.log('tag : ', rating_list);
        this.suggest_dong.tag = [];
        //탑 3개까지 뽑음
        for (let i = 0; i < 3; i++) {
          //동코드로 SigugundongDto 찾아서 suggest_dong.tag 넣음, (사용자의 선호타입으로 찾은 추천 동 목록)
          if (rating_list.length <= i) return;
          http.get('/address/' + rating_list[i].dongcode).then((dong_resonse) => {
            this.suggest_dong.tag.push(dong_resonse.data);
          });
        }
      });
    },
    moveInfo(dongDto) {
      //   console.log(this.$store.state.dongStore.Sidogugundong);
      this.$store.state.dongStore.Sidogugundong = dongDto;
      //   console.log(this.$store.state.dongStore.Sidogugundong.dongName);
      this.$router.push('dong-info');
    },
  },
};
</script>
<style scoped>
.user_info h5 {
  display: inline-block;
}
.rank h5 {
  color: grey;
}
</style>
