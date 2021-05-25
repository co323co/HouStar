<template>
  <div class="px-2 py-7">
    <v-flex>
      <v-divider></v-divider>
      <v-row class="btitle px-5 py-1">{{ board.title }}</v-row>
      <v-divider></v-divider>
      <v-row>
        <v-col cols="3">
          <v-row no-gutters>
            <v-col>
              {{ board.userid }}
            </v-col>
            <v-col>
              {{ board.regtime }}
            </v-col>
            <v-col> 조회 {{ board.views }} </v-col>
          </v-row>
        </v-col>
        <v-spacer></v-spacer>
      </v-row>
      <v-divider></v-divider>
      <v-row class="pa-5"> {{ enterToBr(board.content) }}</v-row>
      <v-divider></v-divider>
    </v-flex>
    <div
      v-if="currentUser.userid == 'admin' || board.userid == currentUser.userid"
      style="padding-top: 15px"
    >
      <button @click="mvmodify">수정</button>
      <button @click="deleteBook" class="ml-2">삭제</button>
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
</style>
