<template lang="">
  <div>
    <b-input-group class="mt-3">
      <b-form-input v-model="word" placeholder="게시글 제목 검색"></b-form-input>
      <b-input-group-append>
        <b-button variant="outline-success" @click="search">검색</b-button>
      </b-input-group-append>
    </b-input-group>
    <div v-if="boards.length" align="center">
      <table class="table table-bordered table-condensed">
        <colgroup>
          <col :style="{ width: '5%' }" />
          <col :style="{ width: '50%' }" />
          <col :style="{ width: '10%' }" />
          <col :style="{ width: '25%' }" />
        </colgroup>
        <tr>
          <th>글번호</th>
          <th>제목</th>
          <th>작성자</th>
          <th>날짜</th>
          <th>조회수</th>
        </tr>
        <list-row
          v-for="(board, index) in boards"
          :key="`${index}_items`"
          :id="board.id"
          :title="board.title"
          :userid="board.userid"
          :regtime="board.regtime"
          :views="board.views"
        />
      </table>
    </div>
    <div v-else>글이 없습니다.</div>
    <div class="text-right">
      <button class="btn btn-primary" @click="movePage">등록</button>
    </div>
  </div>
</template>
<script>
import { mapGetters } from "vuex";
import ListRow from "@/components/board/ListRow.vue";

export default {
  data() {
    return {
      word: "",
    };
  },
  components: {
    ListRow,
  },
  computed: {
    ...mapGetters(["boards", "boardname"]),
  },
  created() {
    console.log(this.boardname);
    console.log("보드이름 : " + this.$store.state.boardname);
  },
  methods: {
    movePage() {
      console.log(this.$route.params.gubun);
      this.$router.push(`/boardpost/${this.$route.params.gubun}/create`);
    },
    search() {
      this.$store.dispatch("getBoards", `${this.$route.params.gubun}/${this.word}`);
    },
  },
};
</script>
<style>
th {
  text-align: center;
  height: 50px;
}
</style>
