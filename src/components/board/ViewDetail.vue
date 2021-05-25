<template>
  <div class="regist">
    <h4 class="underline">글정보</h4>
    <div class="regist_form">
      <label for="isbn">글번호</label>
      <div class="view">{{ board.id }}</div>
      <label for="isbn">조회수</label>
      <div class="view">{{ board.views }}</div>
      <label for="title">제목</label>
      <div class="view">{{ board.title }}</div>
      <label for="author">작성자</label>
      <div class="view">{{ board.userid }}</div>
      <label for="price">작성일</label>
      <div class="view">{{ board.regtime }}</div>
      <label for="content">내용</label>
      <div class="view" v-html="enterToBr(board.content)"></div>
      <div
        v-if="currentUser.userid == 'admin' || board.userid == currentUser.userid"
        style="padding-top: 15px"
      >
        <button @click="mvmodify">수정</button>
        <button @click="deleteBook" class="ml-2">삭제</button>
      </div>
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
.regist {
  padding: 10px;
}
.regist_form {
  text-align: left;
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}
input,
textarea,
.view {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  color: #787878;
  font-size: medium;
}
</style>
