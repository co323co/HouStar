<template>
  <div class="regist">
    <div class="head">댓글 등록</div>
    <v-textarea v-model="content" solo></v-textarea>
    <v-row class="text-right">
      <v-spacer></v-spacer>
      <v-col class="cbtn mb-3 mr-1" cols="1">
        <button @click="regist">등록</button>
      </v-col>
    </v-row>
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
    regist() {
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
          if (data != true) {
            let msg = '댓글 등록에 실패했습니다';
            alert(msg);
          }
          // 댓글창 내용 지우기
          this.content = '';

          // 새로 삽입했으니 댓글 다시 얻기.
          this.$store.dispatch('getComments', this.bid);
        });
    },
  },
};
</script>

<style scoped>
.regist {
  text-align: left;
  border-radius: 5px;
  background-color: #e2e2e28e;
  padding: 10px 20px;
  margin-top: 10px;
}
.head {
  font-weight: bold;
  margin-bottom: 5px;
}
.cbtn {
  color: steelblue;
}
</style>
