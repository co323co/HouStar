<!-- 상세정보 페이지 -->
<template>
  <div class="pa-7">
    <v-card class="mx-auto pa-3" elevation="5" shaped>
      <v-card-title>
        <h2 style="font-weight: bold">인프라</h2>
      </v-card-title>
      <v-card-subtitle class="ml-1" style="font-size: 1.2em">
        {{ currentDongInfo.sidoName }}
        {{ currentDongInfo.gugunName }}
        {{ currentDongInfo.dongName }}
        상가 정보
      </v-card-subtitle>
      <v-card-actions @click="show = !show">
        <v-btn color="orange lighten-2" text> <h6>더 보기</h6> </v-btn>
        <v-spacer></v-spacer>
        <v-btn icon>
          <v-icon>{{ show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
        </v-btn>
      </v-card-actions>
      <!-- 확장 부분 -->
      <v-expand-transition>
        <div v-show="show" class="pa-2">
          <v-divider></v-divider>
          <infra />
        </div>
      </v-expand-transition>
    </v-card>
    <trans />
    <saftey />
    <health />
    <school />
    <environment />
  </div>
</template>
<script>
import Infra from './detail/Infra.vue';
import Trans from './detail/Trans.vue';
import Saftey from './detail/Health.vue';
import Health from './detail/Health.vue';
import School from './detail/School.vue';
import Environment from './detail/Environment.vue';
//현재페이지를 무슨 동을 클릭해서 들어왔는지 정보 담겨있음
import { mapGetters } from 'vuex';
export default {
  components: {
    Infra,
    Trans,
    Saftey,
    Health,
    School,
    Environment,
  },
  data: () => ({
    show: false,
  }),
  computed: {
    ...mapGetters(['currentDongInfo']),
  },
  created() {
    console.log(this.currentDongInfo);
  },
};
</script>
<style scoped></style>
