<template>
  <!-- 기존거 시작 -->
  <div class="regist">
    <h1 class="underline">수정</h1>
    <div class="regist_form">
      <label for="isbn">글번호</label>
      <div class="view">{{ id }}</div>
      <label for="isbn">조회수</label>
      <div class="view">{{ views }}</div>
      <label for="title">제목</label>
      <input type="text" id="title" name="title" v-model="title" ref="title" /><br />
      <label for="author">작성자</label>
      <div class="view">{{ userid }}</div>
      <label for="price">작성일</label>
      <div class="view">{{ regtime }}</div>
      <label for="content">내용</label>
      <v-textarea v-model="content"></v-textarea><br />
      <div v-if="userid == currentUser.userid">
        <!-- <a href="#" class="btn" @click="checkValue">수정</a> -->
        <button @click="checkValue">수정</button>
        <!-- <router-link to="/board/:gubun" class="btn">목록</router-link> -->
        <button @click="moveList">목록</button>
      </div>
    </div>
  </div>
  <!-- 기존거끝 -->
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
      userid: '',
      views: '',
      regtime: '',
      gubun: '',
    };
  },
  computed: {
    ...mapGetters(['currentUser']),
  },
  created() {
    //게시글 id로 해달게시글주는거
    http.get(`/board/post/${this.$route.params.id}`).then(({ data }) => {
      this.id = data.id;
      this.title = data.title;
      this.content = data.content;
      this.userid = data.userid;
      this.views = data.views;
      this.regtime = data.regtime;
      this.gubun = data.gubun;
    });
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
      else this.modifyBook();
    },

    modifyBook() {
      http
        .put(`/board/post/${this.id}`, {
          id: this.id,
          title: this.title,
          content: this.content,
          userid: this.userid,
          views: this.views,
          regtime: this.regtime,
          gubun: this.gubun,
        })
        .then(({ data }) => {
          console.log('수정완료');
          let msg = '수정 처리시 문제가 발생했습니다.';
          if (data) {
            msg = '수정이 완료되었습니다.';
          }
          alert(msg);
          // this.$router.push(`/board/${this.gubun}`);
          this.$router.push(`/board/${this.gubun}/view/${this.id}`);
        });
    },
    moveList() {
      this.$router.push(`/board/${this.gubun}`);
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
