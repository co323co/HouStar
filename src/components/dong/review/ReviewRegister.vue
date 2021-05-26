<template>
  <!-- 시작 -->

  <!-- 끝 -->

  <v-row align="center">
    <v-col cols="3">
      <div class="text-center">
        <span>환경</span>
        <v-rating
          small
          dense="true"
          v-model="environment"
          background-color="purple lighten-3"
          color="purple"
        ></v-rating>
        <label>건강</label>
        <v-rating
          small
          v-model="health"
          background-color="pink lighten-3"
          color="pink"
          dense="true"
        ></v-rating>
        <label>인프라</label>
        <v-rating
          small
          v-model="infra"
          background-color="orange lighten-3"
          color="orange"
          dense="true"
        ></v-rating>
        <label>안전</label>
        <v-rating
          small
          v-model="safety"
          background-color="green lighten-3"
          color="green"
          dense="true"
        ></v-rating>
        <label>학군</label>
        <v-rating
          small
          v-model="school"
          background-color="green lighten-3"
          color="green"
          dense="true"
        ></v-rating>
        <label>교통</label>
        <v-rating
          small
          v-model="trans"
          background-color="indigo lighten-3"
          color="indigo"
          dense="true"
        ></v-rating></div
    ></v-col>
    <v-col cols="9">
      <v-card-text>
        <h1>후기를 남겨주세요 ✍</h1>
        <v-textarea
          height="150px"
          solo
          name="input-7-4"
          label="입력하세요"
          v-model="content"
        ></v-textarea>
        <div align="center">
          <v-btn
            :loading="loading3"
            :disabled="loading3"
            color="amber darken-1"
            class="white--text"
            @click="register"
          >
            등록
            <v-icon right dark>
              mdi-cloud-upload
            </v-icon>
          </v-btn>
        </div>
      </v-card-text>
    </v-col>
  </v-row>
</template>

<script>
import http from '@/core/services/http-common';
// import { mapState } from 'vuex';
import { mapGetters } from 'vuex';
export default {
  computed: {
    ...mapGetters(['currentUser']),
  },
  methods: {
    register() {
      console.log('등록버튼누름');
      http
        .post('/dongreview', {
          content: this.content,
          dongcode: this.$store.state.dongStore.Sidogugundong.dongCode,
          environment: this.environment + '',
          health: this.health + '',
          infra: this.infra + '',
          safety: this.safety + '',
          school: this.school + '',
          trans: this.trans + '',
          userid: this.currentUser.userid,
        })
        .then(({ data }) => {
          let msg = '등록 처리시 문제가 발생했습니다.';
          if (data == true) {
            msg = '등록이 완료되었습니다.';
          }
          alert(msg);
          // 작성글 지우기
          this.content = '';
          this.environment = '';
          this.health = '';
          this.infra = '';
          this.safety = '';
          this.school = '';
          this.trans = '';
          // store에 있는거 갱신해주기
          this.$store.dispatch('getReviews', this.$store.state.dongStore.Sidogugundong.dongCode);
          // user가 새글을 입력했는지 갱신해주기
          this.$store.dispatch('getReviewsByUserId', this.currentUser.userid);
        })
        .catch((error) => {
          console.log(error.response);
          alert('리뷰는 한번만 등록가능합니다');
        });
    },
  },
  data() {
    return {
      content: '',
      dongcode: this.$store.state.dongStore.Sidogugundong.dongCode,
      environment: null,
      health: null,
      infra: null,
      safety: null,
      school: null,
      trans: null,
      userid: '',
    };
  },
};
</script>

<style scoped>
.completed {
  text-decoration: line-through;
}
</style>
