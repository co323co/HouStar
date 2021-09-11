<template>
  <div>
    <view-detail />
    <!-- 댓글 작성하는 부분 -->
    <comment-write :bid="this.id" />
    <!-- 댓글 보여주는 부분 -->
    <div v-for="(comment, index) in comments" :key="index">
      <v-divider></v-divider>
      <comment :comment="comment" />
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
import ViewDetail from '@/components/board/ViewDetail.vue';
import CommentWrite from './comment/CommentWrite.vue';
import Comment from './comment/Comment.vue';

export default {
  data() {
    return {
      id: null,
    };
  },
  computed: {
    ...mapGetters(['comments']),
  },
  components: {
    ViewDetail,
    CommentWrite,
    Comment,
  },
  created() {
    this.id = this.$route.params.id;
    this.$store.dispatch('getBoard', this.id);
    this.$store.dispatch('getComments', this.id);
  },
  methods: {},
};
</script>
