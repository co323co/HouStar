<template>
  <v-container>
    <review-list-item v-for="(review, idx) in reviews" :key="idx" :review="review" />
  </v-container>
</template>
<script>
import ReviewListItem from './ReviewListItem.vue';
import { mapState } from 'vuex';
export default {
  components: { ReviewListItem },
  computed: {
    // namespace true로 할 경우 state는 그냥 접근 가능.
    ...mapState('review', ['reviews']),
  },
  created() {
    console.log('ReviewList의 reviews');
    console.log(this.reviews);
  },
};
</script>
<style lang=""></style>
