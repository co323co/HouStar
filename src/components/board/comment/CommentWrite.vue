<template>
  <div class="regist">
    <div v-if="modifyComment != null" class="regist_form">
      <div class="mb-2">댓글 수정 :: {{ modifyComment.userid }} : {{ modifyComment.regtime }}</div>
      <div>
        <v-textarea v-model="modifyComment.content" solo></v-textarea>
      </div>
      <div class="mb-5">
        <button class="small" @click="updateCommentCancel">취소</button>
        <button class="small" @click="updateComment">수정</button>
      </div>
    </div>
    <div v-else class="regist_form">
      <div>댓글 등록</div>
      <v-textarea v-model="content" solo></v-textarea>
      <button @click="registComment">등록</button>
    </div>
  </div>
</template>

<script>
import http from '@/core/services/http-common';
import { mapGetters } from 'vuex';

export default {
  name: 'commentwrite',
  props: ['bid', 'modifyComment'],
  data() {
    return {
      //댓글 등록용 content
      content: '',
    };
  },
  computed: {
    ...mapGetters(['currentUser']),
  },
  methods: {
    registComment() {
      if (this.content.length == 0) {
        alert('댓글 내용이 비어있어요!');
        return;
      }
      http
        .post('/comment/', {
          userid: this.currentUser.userid,
          bid: this.bid,
          content: this.content,
        })
        .then(({ data }) => {
          if (data == true) {
          } else {
            let msg = '댓글 등록에 실패했습니다';
            alert(msg);
          }

          // 댓글창 내용 지우기
          this.content = '';

          // 새로 삽입했으니 댓글 다시 얻기.
          this.$store.dispatch('getComments', this.bid);
        });
    },
    updateComment() {
      http
        .put(`/comment/`, {
          bid: this.modifyComment.bid,
          seq: this.modifyComment.seq,
          content: this.modifyComment.content,
        })
        .then(({ data }) => {
          if (!data) {
            let msg = '수정 처리시 문제가 발생했습니다.';
            alert(msg);
          }

          // 수정했으니 댓글 다시 얻기
          this.$store.dispatch('getComments', this.modifyComment.bid);
          this.$emit('modify-comment-cancel', false);
        });
    },
    updateCommentCancel() {
      this.$emit('modify-comment-cancel', false);
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
  background-color: #d6e7fa;
  padding: 20px;
}

textarea {
  width: 90%;
  padding: 10px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  color: #787878;
  font-size: large;
}

button {
  float: right;
}

button.small {
  width: 60px;
  font-size: small;
  font-weight: bold;
}
</style>
