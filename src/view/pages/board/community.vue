<template lang="">
  <v-container>
    <v-row>
      <v-col cols="6">
        <!-- table1, table2 -->

        <!-- <v-card> -->
        <v-card-title style="height:80px" class="cardtitle">
          🔥 인기 게시글 🔥
        </v-card-title>

        <v-data-table
          :headers="headers"
          :items="hotposts"
          :items-per-page="5"
          @click:row="mvDetail"
          class="elevation-1 com"
          :hide-default-footer="true"
        >
          <template v-slot:item.views="{ item }">
            <v-chip :color="getColor(item.views)" dark>{{ item.views }}</v-chip>
          </template>
        </v-data-table>

        <!-- </v-card> -->

        <!-- end card -->

        <!-- <v-card> -->
        <v-card-title style="height:80px" class="cardtitle">
          🔥 인기 게시판 🔥
        </v-card-title>

        <v-data-table
          :headers="headers3"
          :items="hotcommunitys"
          :items-per-page="5"
          class="elevation-1 com"
          @click:row="mvList"
          :hide-default-footer="true"
        >
          <template v-slot:item.count="{ item }">
            <v-chip :color="getColor(item.count)" dark>{{ item.count }}</v-chip>
          </template>
        </v-data-table>

        <!-- </v-card> -->
      </v-col>

      <v-col cols="6" align="center">
        <!-- table3 -->

        <v-card-title style="height:80px" class="cardtitle">
          🧑 게시판 목록 👩
          <v-spacer></v-spacer>
          <v-text-field
            v-model="search"
            append-icon="mdi-magnify"
            label="검색"
            single-line
            hide-details
          ></v-text-field>
        </v-card-title>
        <!-- select box 포함한 테이블 -->
        <v-data-table
          :headers="headers3"
          :items="communitys"
          :items-per-page="10"
          :search="search"
          class="elevation-1 com"
          @click:row="mvList"
          no-data-text="게시글이 없습니다"
          no-results-text="검색 결과가 없습니다"
        >
          <template v-slot:item.count="{ item }">
            <v-chip :color="getColor(item.count)" dark>{{ item.count }}</v-chip>
          </template>
        </v-data-table>

        <div>
          <!-- 버튼모달창 시작 -->

          <v-dialog v-model="dialog" width="500">
            <template v-slot:activator="{ on, attrs }">
              <v-btn color="amber darken-1 mt-4" dark v-bind="attrs" v-on="on">
                게시판 등록
              </v-btn>
            </template>

            <v-card>
              <v-card-title>
                <span class="headline">새 커뮤니티를 만들어 보세요! ✍</span>
              </v-card-title>

              <v-card-text>
                <v-container>
                  <v-row>
                    <v-col>
                      <v-text-field
                        placeholder="게시판 이름을 입력해주세요"
                        :rules="[v => !!v || '빈 이름의 게시판은 만들 수 없습니다!']"
                        v-model="communityName"
                      ></v-text-field>
                    </v-col>
                  </v-row>
                </v-container>
                <small style="font-size:11px"> * 부적절한 게시판은 차후 삭제될 수 있습니다. </small>
              </v-card-text>

              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="dialog = false">
                  취소
                </v-btn>
                <v-btn color="blue darken-1" text @click="[Close(), RegisterCommunity()]">
                  등록
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>

          <!-- 버튼모달창 끝 -->
        </div>
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
import { mapGetters } from 'vuex';
export default {
  data() {
    return {
      // 관리자일 때만 삭제
      // singleSelect: false,
      selected: [],
      // 커뮤니티 이름 등록
      dialog: false,
      communityName: '',
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
      headers3: [
        {
          text: '게시판 이름',
          align: 'start',
          value: 'name',
          sortable: false,
        },
        { text: '글 갯수', value: 'count' },
      ],
    };
  },
  //인기게시글, 인기게시판, 게시판목록

  created() {
    this.$store.dispatch('getHotposts');
    this.$store.dispatch('getHotcommunitys');
    this.$store.dispatch('getCommunitys');
    console.log(this.hotposts);
    console.log(this.currentUser);
    console.log(this.communitys);
    // console.log(this.communitys[0]);
  },
  computed: {
    ...mapGetters(['hotposts', 'hotcommunitys', 'communitys', 'currentUser']),
  },
  methods: {
    mvList(row) {
      console.log(this.communityName);

      this.$router.push(`/board/${row.gubun}`);
    },
    mvDetail(row) {
      this.$router.push(`/board/${row.gubun}/view/${row.id}`);
    },
    getColor(cnt) {
      if (cnt > 10) return 'red';
      else if (cnt > 5) return 'orange';
      else return 'green';
    },
    myMethod() {
      console('this.selected 지우려고 골랐음');

      console(this.selected);
    },
    // 커뮤니티 등록
    RegisterCommunity() {
      let val = {
        name: this.communityName,
      };
      this.$store.dispatch('addCommunity', val);
      console.log('this.communityName 등록하고옴');
      this.communityName="";
      // 다시가져와줌
      //  this.$store.dispatch('getCommunitys');
      // console.log('this.communityName 다시가져옴');
    },
    // 모달 닫기
    Close() {
      this.dialog = false;
    },
    rowClicked(val) {
      console.log(val);
    },
  },
};
</script>
<style scoped>

</style>
