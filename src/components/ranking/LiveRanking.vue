<template lang="">
  <div>
    <h4>🏆 실시간 전체 랭킹</h4>
    <div class="pa-5">
      <div v-for="(dong, idx) in dong_list" :key="idx" @click="moveInfo(dong)" v-if="dong">
        <mouse-over :msg="idx + 1 + `.  ` + dong.dongName" />
      </div>
    </div>
  </div>
</template>
<script>
import MouseOver from '@/components/ranking/MouseOverText.vue';
export default {
  props: ['dong_list'],
  components: {
    MouseOver,
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
</style>
