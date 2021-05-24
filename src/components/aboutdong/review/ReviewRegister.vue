<template>
  <v-card :loading="loading" class="mx-auto my-12" max-width="1000">
    <v-row align="center">
      <v-col>
        <div class="text-center">
          <label>환경</label>
          <v-rating
            v-model="environment"
            background-color="purple lighten-3"
            color="purple"
          ></v-rating>
          <label>건강</label>
          <v-rating v-model="health" background-color="pink lighten-3" color="pink"></v-rating>
          <label>인프라</label>
          <v-rating v-model="infra" background-color="orange lighten-3" color="orange"></v-rating>
          <label>안전</label>
          <v-rating v-model="safety" background-color="green lighten-3" color="green"></v-rating>
          <label>학군</label>
          <v-rating v-model="school" background-color="green lighten-3" color="green"></v-rating>
          <label>교통</label>
          <v-rating
            v-model="trans"
            background-color="indigo lighten-3"
            color="indigo"
          ></v-rating></div
      ></v-col>
      <v-col>
        <v-card-text align="rignt">
          <v-textarea solo name="input-7-4" label="Solo textarea" v-model="content"></v-textarea>
          <button @click="register">등록</button>
        </v-card-text>
      </v-col>
    </v-row>
  </v-card>
</template>

<script>
import http from '@/core/services/http-common';
import { mapState } from 'vuex';
import { mapGetters } from 'vuex';
export default {
  computed: {
    ...mapGetters(['currentUser']),
  },
  methods: {
    register() {
      // console.log(this.show_list);
      console.log('등록버튼누름');
      console.log(this.currentUser.userid);
      console.log(this.$store.state.dongStore.Sidogugundong.dongCode);
      http
        .post('/dongreview', {
          content: this.content,
          dongcode: this.$store.state.dongStore.Sidogugundong.dongCode,
          environment: this.environment,
          health: this.health,
          infra: this.infra,
          safety: this.safety,
          school: this.school,
          trans: this.trans,
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
          // this.$router.push({ name: 'review' });
        });
    },
  },
  data() {
    return {
      content: '',
      dongcode: '',
      environment: '',
      health: '',
      infra: '',
      safety: '',
      school: '',
      trans: '',
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
