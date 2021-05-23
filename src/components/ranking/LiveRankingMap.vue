<template lang="">
  <v-layout class="pa-5">
    <v-row>
      <v-col>
        <div>
          <h4>🏆 실시간 랭킹</h4>
          <div class="pa-5">
            <div v-for="(dong, idx) in dong_list" :key="idx" @click="moveInfo(dong)">
              <mouse-over :msg="idx + 1 + `.  ` + dong.dongName" />
            </div>
          </div>
        </div>
      </v-col>
      <v-col>
        <div style="text-align: center">맵</div>
      </v-col>
    </v-row>
  </v-layout>
</template>
<script>
import http from '@/core/services/http-common';
import MouseOver from '@/components/ranking/MouseOverText.vue';
export default {
  components: {
    MouseOver,
  },
  data() {
    return {
      dong_list: [],
    };
  },

  mounted() {
    http.get('/dongreview/avg-rating').then(({ data }) => {
      //총 평점 높은 순으로 정렬
      data.sort(function (a, b) {
        return b.total - a.total;
      });
      //탑 5개까지 뽑음
      for (let i = 0; i < 5; i++) {
        //동코드로 SigugundongDto 찾기
        if (data.length <= i) return;
        http.get('/address/' + data[i].dongcode).then((dong_resonse) => {
          this.dong_list.push(dong_resonse.data);
        });
      }
    });
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
v-layout {
  background-color: white;
}
</style>
