<template lang="">
  <div class="comment">
    <div class="head">{{ comment.userid }} : {{ comment.regtime }}</div>
    <div class="content" v-html="enterToBr(comment.content)"></div>
    <div class="cbtn">
      <label @click="modifyCommentView">수정</label> | <label @click="deleteComment">삭제</label>
    </div>
  </div>
</template>
<script>
import http from '@/util/http-common';
export default {
  props: ['comment'],
  methods: {
    modifyCommentView() {
      //부모한테 현재 댓글 수정작업 해달라고 알림
      this.$emit('modify-comment', {
        bid: this.comment.bid,
        seq: this.comment.seq,
        userid: this.comment.userid,
        regtime: this.comment.regtime,
        content: this.comment.content,
      });
    },
    deleteComment() {
      if (confirm('정말로 삭제할까요?')) {
        http.delete(`/comment/${this.comment.bid}/${this.comment.seq}`).then(({ data }) => {
          let msg = '삭제 처리시 문제가 발생했습니다.';
          if (data == true) {
            msg = '삭제가 완료되었습니다.';
          }
          alert(msg);
          // 삭제했으니 보여줄 댓글들 다시 얻기.
          this.$store.dispatch('getComments', this.comment.bid);
        });
      }
    },
    enterToBr(str) {
      if (str) return str.replace(/(?:\r\n|\r|\n)/g, '<br />');
    },
  },
};
</script>

<style>
.comment {
  text-align: left;
  border-radius: 5px;
  background-color: #d6e7fa;
  padding: 10px 20px;
  margin: 10px;
}
.head {
  font-weight: bold;
  margin-bottom: 5px;
}
.content {
  padding: 5px;
}
.cbtn {
  text-align: right;
  color: steelblue;
  margin: 5px 0px;
}
</style>
