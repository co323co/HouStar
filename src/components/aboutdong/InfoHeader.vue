<template lang="">
  <v-app>
    <v-card>
      <v-system-bar></v-system-bar>
      <v-spacer></v-spacer>
      <v-toolbar flat>
        <div>
          {{ this.$store.state.dongStore.Sidogugundong.sidoName }}
          {{ this.$store.state.dongStore.Sidogugundong.gugunName }}
        </div>
      </v-toolbar>

      <v-toolbar flat class="mb-5">
        <v-toolbar-title>
          <h1 class="display-3">{{ this.$store.state.dongStore.Sidogugundong.dongName }}</h1>
        </v-toolbar-title>
        <star-rating
          :increment="0.01"
          :fixed-points="2"
          class="ml-5"
          :rating="rating"
          read-only
          show-rating
          v-if="isAble"
        ></star-rating>
        <v-toolbar-title v-else>
          <h1 class="display-1 ml-4">에 대한 평점 정보가 없습니다.😥</h1>
        </v-toolbar-title>
      </v-toolbar>

      <v-divider class="mx-4"></v-divider>

      <v-card-actions class="ml-2 mb-2">
        <v-btn @click="mvinfo()">상세정보</v-btn>
        <v-btn class="ml-4" @click="mvreview()">리뷰</v-btn>
      </v-card-actions>

      <v-card-text class="grey lighten-4">
        <v-sheet max-width="1200" min-height="300" class="mx-auto">
          <BarChart />
        </v-sheet>
      </v-card-text>
    </v-card>
  </v-app>
</template>

<script>
import http from '@/core/services/http-common';
import StarRating from 'vue-star-rating';
import BarChart from '@/components/charts/Barchart.vue';

export default {
  components: {
    StarRating,
    BarChart,
  },
  data: () => ({
    rating: '',
    isAble: false,
  }),
  mounted() {
    if (this.$store.state.rate.rating) {
      this.isAble = true;
      this.rating = this.$store.state.rate.rating.total;
    } else this.isAble = false;
  },
  created() {
    // console.log('dong info created 실행');
    // http
    //   .get('/dongreview/avg-rating/' + this.$store.state.dongStore.Sidogugundong.dongCode)
    //   .then(({ data }) => {
    //     console.log(data);
    //     this.$store.state.rate.rating = data;
    //    // console.log(this.$store.state.rate.rating);
    //     this.rating = data.total;
    // if (this.$store.state.rate.rating) this.isAble = true;
    // else this.isAble = false;
    //   })
    //   .catch(({ response }) => {
    //     console.log('해당 동에 대한 평점 정보가 없습니다. ');
    //     console.log(response);
    //   });
  },
  methods: {
    mvinfo() {},
    mvreview() {},
  },
};
</script>
<style>
.vue-star-rating-rating-text {
  color: gray;
}
</style>
