import Vue from "vue";
import Vuex from "vuex";
import http from "@/util/http-common";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    boards: [],
    board: {},
    comments: [],
    boardname: "",
  },
  getters: {
    boards(state) {
      return state.boards;
    },
    board(state) {
      return state.board;
    },
    boardname(state) {
      return state.boardname;
    },
    comments(state) {
      return state.comments;
    },
  },
  mutations: {
    setBoards(state, payload) {
      state.boards = payload;
    },
    setBoard(state, payload) {
      state.board = payload;
    },
    setBoardName(state, payload) {
      state.boardname = payload;
    },
    setComments(state, payload) {
      state.comments = payload;
    },
  },
  actions: {
    // 앞에 /board
    //게시판 구분(gubun)으로 해당 게시판 게시글 전부 찾기
    getBoards(context, payload) {
      http
        .get("/board/" + payload)
        .then(({ data }) => {
          context.commit("setBoards", data);
        })
        .catch(() => {
          alert("에러발생!");
        });
    },
    //게시글 id로 해당 게시글 찾기
    getBoard(context, payload) {
      http.get("/board/post/" + payload).then(({ data }) => {
        context.commit("setBoard", data);
      });
    },
    //게시판 gubun로 게시판이름 찾기
    getBoardName(context, payload) {
      http.get("/board/name/" + payload).then(({ data }) => {
        //  console.log(payload);
        context.commit("setBoardName", data);
      });
    },
    //게시글 id로 댓글들 찾기
    getComments(context, payload) {
      http.get("/comment/" + payload).then(({ data }) => {
        context.commit("setComments", data);
      });
    },
  },
});
