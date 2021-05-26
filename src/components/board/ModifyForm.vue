<template>
  <div>
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
        class="pa-1 inputs"
        hide-details
      />
      <v-divider></v-divider>
      <v-row class="pl-2">
        <v-col cols="12">
          <v-row no-gutters align="center">
            <p>{{ id }}</p>
            <p class="ml-4">|</p>
            <p class="ml-4">{{ regtime }}</p>
            <p class="ml-4">|</p>
            <p class="ml-4">조회 {{ views }} views</p>
          </v-row>
        </v-col>
        <v-spacer></v-spacer>
      </v-row>
      <v-divider></v-divider>
      <div>
        <v-textarea
          rows="15"
          placeholder="내용을 써주세요!"
          id="content"
          name="content"
          v-model="content"
          ref="content"
          color="cyan lighten-3"
          background-color="white"
          class="pa-1 inputs"
          hide-details
        />
      </div>
    </div>

    <v-flex class="pt-4 mb-2 text-right">
      <div v-if="userid == currentUser.userid || currentUser.userid == `admin`">
        <v-btn text @click="checkValue">수정</v-btn>
        <v-btn text @click="moveList" class="ml-2">목록</v-btn>
      </div>
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
      !this.title && ((msg = '제목을 입력해주세요'), (err = false), this.$refs.title.focus());
      err &&
        !this.content &&
        ((msg = '내용을 입력해주세요'), (err = false), this.$refs.content.focus());
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
          if (!data) {
            let msg = '수정 처리시 문제가 발생했습니다.';
            alert(msg);
          }
          // this.$router.push(`/board/${this.gubun}`);
          else this.$router.push(`/board/${this.gubun}/view/${this.id}`);
        });
    },
    moveList() {
      let res = confirm('\n수정 중인 내용이 사라집니다!\n목록으로 이동하시겠습니까?\n');
      if (res) {
        this.$router.push(`/board/${this.gubun}`);
      }
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

.inputform {
  background-color: white;
  border-radius: 10px;
  /* padding: 10px 10px; */
}
.v-btn {
  font-size: 1.1em;
}
p {
  margin-bottom: 0;
}

.v-textarea textarea {
  height: 500px !important;
}

#content {
  height: 500px !important;
}
/* .inputs {
  background-color: palegreen;
} */
</style>
