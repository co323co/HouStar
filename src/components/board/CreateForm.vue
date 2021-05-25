<template lang="">
  <div class="regist">
    <h1 class="underline">등록</h1>
    <div class="regist_form">
      <label for="title">제목</label>
      <input type="text" id="title" name="title" v-model="title" ref="title" /><br />
      <label for="content">내용</label>
      <textarea
        id="content"
        name="content"
        v-model="content"
        ref="content"
        cols="35"
        rows="5"
      ></textarea
      ><br />
      <button @click="checkValue">등록</button>
      <button @click="moveList">목록</button>
    </div>
  </div>
</template>
<script>
import http from "@/util/http-common";
export default {
  data() {
    return {
      id: "",
      title: "",
      content: "",
      userid: "",
      views: "",
      regtime: "",
      gubun: this.$route.params.gubun,
    };
  },
  methods: {
    checkValue() {
      let err = true;
      let msg = "";
      !this.title && ((msg = "title 입력해주세요"), (err = false), this.$refs.title.focus());
      err &&
        !this.content &&
        ((msg = "내용 입력해주세요"), (err = false), this.$refs.content.focus());
      if (!err) alert(msg);
      else this.registBoard();
    },
    registBoard() {
      console.log("this.title" + this.title);
      console.log("this.content" + this.content);
      console.log("this.userid" + this.userid);
      http
        .post(`/board/post/${this.gubun}`, {
          id: this.id,
          title: this.title,
          content: this.content,
          // userid: this.userid,
          // views: this.views,
          // regtime: this.regtime,
          gubun: this.gubun,
        })
        .then(({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data) {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        });
    },
    moveList() {
      console.log(this.$route.params.gubun);
      this.$router.push(`/boardpost/${this.gubun}`);
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
