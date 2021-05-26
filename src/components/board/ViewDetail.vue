<template>
  <div class="regist">
    <!-- <h3 class="mt-1 mb-3 ml-2">게시글 상세 보기</h3> -->
    <div class="inputform">
      <v-row class="btitle px-5 py-5">{{ board.title }}</v-row>
      <v-divider></v-divider>

      <v-row class="pl-2">
        <v-col cols="12">
          <v-row no-gutters align="center">
            <p>{{ board.userid }}</p>
            <p class="ml-4">|</p>
            <p class="ml-4">{{ board.regtime }}</p>
            <p class="ml-4">|</p>
            <p class="ml-4">조회 {{ board.views }} views</p>
          </v-row>
        </v-col>
        <v-spacer></v-spacer>
      </v-row>

      <v-divider></v-divider>
      <v-row class="pa-5" style="min-height: 300px"> {{ enterToBr(board.content) }}</v-row>
      <v-divider></v-divider>
    </div>
    <div
      v-if="currentUser.userid == 'admin' || board.userid == currentUser.userid"
      style="padding-top: 15px"
    >
      <v-flex class="pt-4 mb-2 text-right">
        <v-btn @click="mvmodify">수정</v-btn>
        <v-btn @click="deleteBook" class="ml-2">삭제</v-btn>
      </v-flex>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
import http from '@/core/services/http-common';

export default {
  computed: {
    ...mapGetters(['board', 'currentUser']),
  },
  methods: {
    mvmodify() {
      this.$router.push(`/board/${this.board.gubun}/modify/${this.board.id}`);
    },
    deleteBook() {
      console.log(this.board.id);
      if (confirm('정말로 삭제하실 건가요?')) {
        http.delete(`/board/post/${this.board.id}`).then(({ data }) => {
          let msg = '삭제 처리시 문제가 발생했습니다.';
          if (data) {
            msg = '삭제가 완료되었습니다.';
          }
          alert(msg);
          this.$router.push(`/board/${this.board.gubun}`);
          // this.$router.push('/board');
        });
      }
    },
    enterToBr(str) {
      if (str) return str.replace(/(?:\r\n|\r|\n)/g, '<br />');
    },
  },
};
</script>

<style scoped>
.btitle {
  font-weight: bold;
  font-size: 1.2em;
}
h3 {
  font-family: BMHANNAPro;
}
.regist {
  padding: 5px;
  /* background-color: rgb(250, 242, 215); */
  /* border-radius: 10px; */
}
/* .inputform {
  background-color: white;
  border-radius: 10px;
  padding: 10px; 
}*/
.v-btn {
  font-size: 1.1em;
}
p {
  margin-bottom: 0;
}
</style>
