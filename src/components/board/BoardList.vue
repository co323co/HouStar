<template>
  <div>
    <!-- 관리자가 아니면 공지사항게시판은 등록 못함 -->
    <div v-if="gubun != 1 || currentUser.userid == `admin`" class="text-right">
      <v-btn color="red lighten-3" class="ma-2" fab dark small @click="mvRegist">
        <v-icon>mdi-plus</v-icon>
      </v-btn>
    </div>
    <v-card class="pa-5">
      <!-- 검색 bar -->
      <v-row>
        <v-spacer></v-spacer>
        <v-col cols="4">
          <v-text-field
            v-model="search"
            prepend-icon="mdi-magnify"
            label="Search"
            single-line
            hide-details
          ></v-text-field>
        </v-col>
      </v-row>
      <v-card-title> </v-card-title>
      <!-- 게시글 리스트 -->
      <v-data-table
        class="board"
        :headers="headers"
        :items="boards"
        :search="search"
        @click:row="mvDetail"
        no-data-text="게시글이 없습니다"
        no-results-text="검색 결과가 없습니다"
      ></v-data-table>
    </v-card>
  </div>
</template>
<script>
import { mapGetters } from 'vuex';

export default {
  data() {
    return {
      word: '',
      gubun: null, //게시판 구분
      search: '',
      headers: [
        {
          text: '글번호',
          align: 'start',
          value: 'id',
          sortable: false,
        },
        { text: '제목', value: 'title' },
        { text: '작성자', value: 'userid' },
        { text: '날짜', value: 'regtime' },
        { text: '조회수', value: 'views' },
      ],
    };
  },
  computed: {
    ...mapGetters(['boards', 'boardname', 'currentUser']),
  },
  created() {
    console.log(this.boardname);
    this.gubun = this.$route.params.gubun;
  },
  methods: {
    mvRegist() {
      console.log(this.$route.params.gubun);
      this.$router.push(`/board/${this.gubun}/create`);
    },
    mvDetail(row) {
      this.$router.push(`/board/${row.gubun}/view/${row.id}`);
    },
  },
};
</script>
<style></style>
