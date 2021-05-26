<template> </template>
<script>
export default {
  data() {
    return {};
  },
  components: { ReviewListItem },

  computed: {
    // namespace true로 할 경우 state는 그냥 접근 가능.
    //해당 동에 대한 리뷰 : reviews
  },
  created() {
    //console.log('ReviewList의 reviews');
    //console.log(this.reviews);
    //created 될 때 보여줄 show_list 를 복사
    //console.log(this.show_list);
  },
};
</script>
<style lang=""></style>
