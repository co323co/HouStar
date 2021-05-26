<template lang="">
  <div>
    <v-card class="rank ma-auto">
      <!-- <v-card class="mx-auto" max-width="400"> -->
      <v-img class="white--text align-end" height="225px" src="media/banner/old/house3.jpg">
        <!-- src="https://cdn.vuetifyjs.com/images/cards/docks.jpg" -->
        <v-card-title>Top 5 실시간 </v-card-title>
      </v-img>

      <v-card-subtitle class="subtitle pb-0" style="text-align: left">
        🏆 실시간 종합 랭킹
      </v-card-subtitle>

      <v-card-text class="text--primary" style="text-align: left">
        <div class="mx-3 mt-8" style="vertical-align: middle">
          <div v-for="(dong, idx) in dong_list" :key="idx" @click="moveInfo(dong)" v-if="dong">
            <mouse-over :msg="icon[idx] + ' ' + dong.dongName" class="mt-4" />
          </div>
        </div>
      </v-card-text>
    </v-card>
  </div>
</template>
<script>
import MouseOver from '@/components/ranking/MouseOverText.vue';
export default {
  props: ['dong_list'],
  components: {
    MouseOver,
  },
  data() {
    return {
      icon: ['🥇', '🥈', '🥉', '🏅', '🏅'],
    };
  },
  methods: {
    moveInfo(dongDto) {
      //   console.log(this.$store.state.dongStore.Sidogugundong);
      this.$store.state.dongStore.Sidogugundong = dongDto;
      //   console.log(this.$store.state.dongStore.Sidogugundong.dongName);
      this.$router.push('dong-info');
    },
  },
};
</script>
<style scoped>
.rank h4 {
  color: coral;
  font-weight: bold;
}
.rank {
  height: 100% !important;
}
.subtitle {
  font-size: 1.2em;
}
</style>
