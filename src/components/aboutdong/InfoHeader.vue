<template lang="">
  <!-- <v-app> -->
  <v-card> </v-card>
  <!-- </v-app> -->
</template>

<script>
import http from '@/core/services/http-common';
// import StarRating from 'vue-star-rating';
// import BarChart from '@/components/charts/Barchart.vue';

export default {
  components: {
    // StarRating,
    // BarChart,
  },
  data: () => ({
    Rating: 0,
    isAble: false,
  }),
  mounted() {
    this.$nextTick(function () {
      if (this.$store.state.rate.rating) {
        console.log('info header가 mounted');
        this.Rating = this.$store.state.rate.rating.total;
        console.log(this.Rating + ' ' + this.$store.state.rate.rating.total);
        // 0              4.00000000
        this.isAble = true;
      } else this.isAble = false;
    });
  },
  methods: {
    mvinfo() {},
    mvreview() {},
  },
};
</script>
<style scoped>
.vue-star-rating-rating-text {
  color: gray;
}
</style>
