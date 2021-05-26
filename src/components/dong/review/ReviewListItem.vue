<template>
  <!-- 보여주는부분 -->
  <div align="center" class="mt-5">
    <v-card v-if="isReadOnly" style="background-color : pink" max-width="1000">
      <v-card-title>
        <v-row v-if="review">
          <p v-if="review.userid">{{ review.userid }} |</p>
          <p v-if="review.age_range">{{ review.age_range }}대 |</p>
          <p v-if="review.family_type">{{ review.family_type }} |</p>
          <p v-if="review.tag">
            {{ review.tag | list }}
          </p>
        </v-row>
      </v-card-title>
      <v-row align="center">
        <v-col cols="3">
          <div class="text-center">
            <label>환경</label>
            <v-rating
              small
              dense="true"
              v-model="newReview.environment"
              background-color="purple lighten-3"
              color="purple"
              readonly
            ></v-rating>
            <label>건강</label>
            <v-rating
              small
              dense="true"
              v-model="newReview.health"
              background-color="pink lighten-3"
              color="pink"
              readonly
            ></v-rating>
            <label>인프라</label>
            <v-rating
              small
              dense="true"
              v-model="newReview.infra"
              background-color="orange lighten-3"
              color="orange"
              readonly
            ></v-rating>
            <label>안전</label>
            <v-rating
              small
              dense="true"
              v-model="newReview.safety"
              background-color="green lighten-3"
              color="green"
              rea
              readonly
            ></v-rating>
            <label>학군</label>
            <v-rating
              small
              dense="true"
              v-model="newReview.school"
              background-color="green lighten-3"
              color="green"
              readonly
            ></v-rating>
            <label>대중교통</label>
            <v-rating
              small
              dense="true"
              v-model="newReview.trans"
              background-color="indigo lighten-3"
              color="indigo"
              readonly
            ></v-rating></div
        ></v-col>
        <v-col cols="9">
          <!-- <v-textarea
            readonly
            solo
            name="input-7-4"
            label="Solo textarea"
            value="review.content"
          ></v-textarea> -->
          <v-card-text>
            {{ review.content }}
          </v-card-text>
        </v-col>
      </v-row>
      <v-card-text align="rignt" v-if="currentUser.userid == review.userid">
        <button @click="activeModify">수정</button>
        <button class="ml-3" @click="deleteReview">제거</button>
      </v-card-text>
    </v-card>
    <!-- 수정부분 -->
    <v-card class="mx-auto my-12" max-width="1000" v-else style="background-color : pink">
      <v-card-title>
        <v-row v-if="review">
          <p v-if="review.userid">{{ review.userid }} |</p>
          <p v-if="review.age_range">{{ review.age_range }}대 |</p>
          <p v-if="review.family_type">{{ review.family_type }} |</p>
          <p v-if="review.tag">
            {{ review.tag | list }}
          </p>
        </v-row>
      </v-card-title>
      <v-row align="center">
        <v-col cols="3">
          <div class="text-center">
            <label>환경</label>
            <v-rating
              small
              dense="true"
              v-model="newReview.environment"
              background-color="purple lighten-3"
              color="purple"
            ></v-rating>
            <label>건강</label>
            <v-rating
              small
              dense="true"
              v-model="newReview.health"
              background-color="pink lighten-3"
              color="pink"
            ></v-rating>
            <label>인프라</label>
            <v-rating
              small
              dense="true"
              v-model="newReview.infra"
              background-color="orange lighten-3"
              color="orange"
            ></v-rating>
            <label>안전</label>
            <v-rating
              small
              dense="true"
              v-model="newReview.safety"
              background-color="green lighten-3"
              color="green"
            ></v-rating>
            <label>학군</label>
            <v-rating
              small
              dense="true"
              v-model="newReview.school"
              background-color="green lighten-3"
              color="green"
            ></v-rating>
            <label>대중교통</label>
            <v-rating
              small
              dense="true"
              v-model="newReview.trans"
              background-color="indigo lighten-3"
              color="indigo"
            ></v-rating></div
        ></v-col>
        <v-col cols="9">
          <v-textarea
            outlined
            name="input-7-4"
            label="Review"
            v-model="newReview.content"
          ></v-textarea>
        </v-col>
      </v-row>
      <v-card-text align="rignt" v-if="currentUser.userid == newReview.userid">
        <button @click="modifyReview">수정</button>
        <button class="ml-3" @click="undoModify">취소</button>
      </v-card-text>
    </v-card>
  </div>
</template>

<script>
// import { mapActions, mapGetters } from 'vuex';
import { mapActions, mapGetters } from 'vuex';
import http from '@/core/services/http-common';
export default {
  computed: {
    ...mapGetters(['totalReviewCount', 'reviews', 'reviewsbyuserid', 'AvgRating', 'rating']),
    ...mapGetters(['currentUser']),
  },
  beforeMount() {
    this.convertTagToList();
  },
  data() {
    return {
      taglist: '',
      isReadOnly: true,
      newReview: '',
    };
  },
  created() {
    // console.log('this.review');
    // console.log(this.review);
    this.newReview = {
      content: this.review.content,
      dongcode: this.review.dongcode,
      environment: this.review.environment * 1,
      health: this.review.health * 1,
      infra: this.review.infra * 1,
      safety: this.review.safety * 1,
      school: this.review.school * 1,
      trans: this.review.trans * 1,
      userid: this.review.userid,
    };
    //  console.log(this.newReview);
  },
  props: ['review'],
  methods: {
    undoModify() {
      this.isReadOnly = true;
    },
    activeModify() {
      console.log('수정으로바껴라');
      this.isReadOnly = false;
    },
    modifyReview() {
      http
        .put('/dongreview', {
          content: this.newReview.content,
          dongcode: this.$store.state.dongStore.Sidogugundong.dongCode,
          environment: this.newReview.environment,
          health: this.newReview.health,
          infra: this.newReview.infra,
          safety: this.newReview.safety,
          school: this.newReview.school,
          trans: this.newReview.trans,
          userid: this.currentUser.userid,
        })
        .then(({ data }) => {
          let msg = '수정 처리시 문제가발생했습니다.';
          if (data == true) {
            msg = '수정이 완료되었습니다.';
          }
          alert(msg);

          // store에 있는거 갱신해주기
          this.$store.dispatch('getReviews', this.$store.state.dongStore.Sidogugundong.dongCode);
          // user가 새글을 입력했는지 갱신해주기
          this.$store.dispatch('getReviewsByUserId', this.currentUser.userid);
          //다시 읽기전용으로 바꿔주기
          this.isReadOnly = true;
        });
    },
    deleteReview() {
      console.log('삭제 버튼 누름스 ');
      http.delete('/dongreview/' + this.currentUser.userid).then(({ data }) => {
        let msg = '삭제 처리 시  문제가발생했습니다.';
        if (data == true) {
          msg = '삭제가 완료되었습니다.';
        }
        alert(msg);

        // store에 있는거 갱신해주기
        this.$store.dispatch('getReviews', this.$store.state.dongStore.Sidogugundong.dongCode);
        // user가 새글을 입력했는지 갱신해주기
        this.$store.dispatch('getReviewsByUserId', this.currentUser.userid);
        //다시 읽기전용으로 바꿔주기
        this.isReadOnly = true;
      });
    },
    convertTagToList() {
      //console.log('convertTagToList if문 밖에');

      if (this.review.tag) {
        this.taglist = this.review.tag.split(',');
        // console.log('convertTagToList');
        //  console.log(this.taglist);/
      }
    },
  },
  filters: {
    ageRange: function(ageRange) {
      if (!ageRange) return '';
      if (ageRange == 'over') return '60대 이상';
      else return ageRange + '대';
    },
    familyType: function(type) {
      if (!type) return '';
      else return type;
    },
    list: function(list) {
      if (!list) return '';
      let str = '';
      for (let i = 0; i < list.length - 1; i++) {
        str += list[i];
      }
      str += list[list.length - 1];
      str = str.replace(',', '     ·     ');
      return str;
    },
  },
};
</script>

<style scoped>
.completed {
  text-decoration: line-through;
}
</style>
