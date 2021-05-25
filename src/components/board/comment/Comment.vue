<template lang="">
  <div>
    <!-- 댓글 보여주는 form -->
    <div v-if="!isModifyShow" class="comment">
      <div class="head">{{ c_cmt.userid }}</div>
      <div class="content" v-html="enterToBr(c_cmt.content)"></div>
      <v-row class="mt-3">
        <v-col d-inline-block class="text-muted"> {{ c_cmt.regtime }} </v-col>
        <v-col cols="1" v-if="c_cmt.userid == currentUser.userid" class="cbtn text-right">
          <label @click="isModifyShow = true">수정</label> |
          <label @click="remove">삭제</label>
        </v-col>
      </v-row>
    </div>
    <!-- 댓글 수정하는 form -->
    <div v-if="isModifyShow" class="comment">
      <div class="head">{{ c_cmt.userid }}</div>
      <div class="content">
        <v-textarea v-model="c_cmt.content" solo></v-textarea>
      </div>
      <v-row class="mb-3" offset-lg11>
        <v-col class="text-muted">{{ c_cmt.regtime }}</v-col>
        <v-col cols="1" class="cbtn text-right">
          <button @click="modify">확인</button> |
          <button @click="isModifyShow = false">취소</button>
        </v-col>
      </v-row>
    </div>
  </div>
</template>
<script>
import http from '@/core/services/http-common';
import { mapGetters } from 'vuex';

export default {
  props: ['comment'],
  computed: {
    ...mapGetters(['currentUser']),
  },
  data() {
    return {
      //현재 댓글이 보기 상태인지 수정 상태인지를 나타낸다
      isModifyShow: false,
      //copy_comment의 약자, props가 변경되는 걸 막기 위해 현재 .vue에서 사용할 comment 복사
      c_cmt: this.copy_comment(),
    };
  },
  methods: {
    // 댓글 수정
    modify() {
      this.isModifyShow = true;
      // 서버 통신
      http
        .put(`/comment/`, {
          bid: this.c_cmt.bid,
          seq: this.c_cmt.seq,
          content: this.c_cmt.content,
        })
        .then(({ data }) => {
          if (!data) {
            let msg = '수정 처리시 문제가 발생했습니다.';
            alert(msg);
          }
          // 수정했으니 댓글 다시 얻기
          this.$store.dispatch('getComments', this.c_cmt.bid);

          //수정 폼 다시 되돌리기
          this.isModifyShow = false;
        });
    },
    remove() {
      if (confirm('정말로 삭제할까요?')) {
        http.delete(`/comment/${this.comment.bid}/${this.comment.seq}`).then(({ data }) => {
          if (!data) {
            let msg = '삭제 처리시 문제가 발생했습니다.';
            alert(msg);
          }
          // 삭제했으니 보여줄 댓글들 다시 얻기.
          this.$store.dispatch('getComments', this.comment.bid);
        });
      }
    },
    enterToBr(str) {
      if (str) return str.replace(/(?:\r\n|\r|\n)/g, '<br />');
    },
    copy_comment(comment) {
      return {
        bid: this.comment.bid,
        seq: this.comment.seq,
        content: this.comment.content,
        regtime: this.comment.regtime,
        userid: this.comment.userid,
      };
    },
  },
  watch: {
    comment: function (newVal) {
      this.c_cmt = this.copy_comment(this.comment);
    },
  },
};
</script>

<style>
.comment {
  text-align: left;
  border-radius: 5px;
  background-color: #ffffff;
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
  color: steelblue;
}
</style>
