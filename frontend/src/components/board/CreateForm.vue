<template lang="">
  <div>
    <!-- <h3 class="mt-1 mb-3">새 글 쓰기</h3> -->
    <div class="inputform">
      <v-text-field
        type="text"
        id="title"
        name="title"
        v-model="title"
        ref="title"
        placeholder="제목"
        color="cyan lighten-3"
        background-color="white"
      />
      <v-textarea
        rows="15"
        placeholder="내용을 써주세요!"
        id="content"
        name="content"
        v-model="content"
        ref="content"
        color="cyan lighten-3"
        background-color="white"
      />
    </div>
    <v-flex class="pt-4 mb-2 text-right">
      <v-btn text @click="checkValue">등록</v-btn>
      <v-btn text @click="moveList" class="ml-2">목록</v-btn>
    </v-flex>
  </div>
</template>
<script>
import http from '@/core/services/http-common';
import { mapGetters } from 'vuex';
export default {
  data() {
    return {
      id: '',
      title: '',
      content: '',
      userid: null,
      views: '',
      regtime: '',
      gubun: this.$route.params.gubun,
    };
  },
  computed: {
    ...mapGetters(['currentUser']),
  },
  created() {
    this.userid = this.currentUser.userid;
  },
  methods: {
    checkValue() {
      let err = true;
      let msg = '';
      !this.title && ((msg = 'title 입력해주세요'), (err = false), this.$refs.title.focus());
      err &&
        !this.content &&
        ((msg = '내용 입력해주세요'), (err = false), this.$refs.content.focus());
      if (!err) alert(msg);
      else this.registBoard();
    },
    registBoard() {
      console.log('this.title' + this.title);
      console.log('this.content' + this.content);
      console.log('this.userid' + this.userid);
      http
        .post(`/board/post/${this.gubun}`, {
          id: this.id,
          title: this.title,
          content: this.content,
          userid: this.userid,
          // views: this.views,
          // regtime: this.regtime,
          gubun: this.gubun,
        })
        .then(({ data }) => {
          if (!data) {
            let msg = '등록 처리시 문제가 발생했습니다.';
            alert(msg);
          } else this.$router.push(`/board/${this.gubun}`);
        });
    },
    moveList() {
      let res = confirm('\n작성 중인 내용이 사라집니다!\n목록으로 이동하시겠습니까?\n');
      if (res) {
        this.$router.push(`/board/${this.gubun}`);
      }
    },
  },
};
</script>
<style scoped>
h3 {
  font-family: BMHANNAPro;
}

.inputform {
  background-color: white;
}
.v-btn {
  font-size: 1.1em;
}
</style>
