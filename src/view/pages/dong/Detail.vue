<!-- 상세정보 페이지 -->
<template>
  <div class="pa-6">
    <!-- 첫번째 행 -->
    <v-row>
      <!-- 1열 -->
      <v-col>
        <infra />
      </v-col>
      <!-- 2열 -->
      <v-col>
        <trans />
      </v-col>
      <!-- 3열 -->
      <v-col>
        <safety />
      </v-col>
    </v-row>
    <!-- 두번째 행 -->
    <v-row>
      <!-- 1열 -->
      <v-col>
        <health />
      </v-col>
      <!-- 2열 -->
      <v-col>
        <school />
      </v-col>
      <!-- 3열 -->
      <v-col>
        <environment />
      </v-col>
    </v-row>
  </div>
</template>
<script>
import Infra from '@/components/dong/detail/Infra.vue';
import Trans from '@/components/dong/detail/Trans.vue';
import Safety from '@/components/dong/detail/Safety.vue';
import Health from '@/components/dong//detail/Health.vue';
import School from '@/components/dong//detail/School.vue';
import Environment from '@/components/dong/detail/Environment.vue';
//현재페이지를 무슨 동을 클릭해서 들어왔는지 정보 담겨있음
import { mapGetters } from 'vuex';
export default {
  components: {
    Infra,
    Trans,
    Safety,
    Health,
    School,
    Environment,
  },

  computed: {
    ...mapGetters(['currentDongInfo']),
  },
  created() {
    console.log(this.currentDongInfo);
  },
};
</script>
<style scoped></style>
