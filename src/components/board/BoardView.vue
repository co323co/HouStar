<template>
  <div>
    <view-detail />
    <comment-write :bid="this.id" />
    <comment-write
      v-if="isModifyShow && modifyComment != null"
      :modifyComment="modifyComment"
      @modify-comment-cancel="onModifyCommentCancel"
    />
    <comment
      v-for="(comment, index) in comments"
      :key="index"
      :comment="comment"
      @modify-comment="onModifyComment"
    />
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
      id: '',
      isModifyShow: false,
      modifyComment: null,
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
  methods: {
    onModifyComment(comment) {
      this.modifyComment = comment;
      this.isModifyShow = true;
    },
    onModifyCommentCancel(isShow) {
      this.isModifyShow = isShow;
    },
  },
};
</script>
