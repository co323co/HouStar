<!-- 상세정보 페이지 -->
<template>
  <div class="pa-7">
    <infra />
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

  computed: {
    ...mapGetters(['currentDongInfo']),
  },
  created() {
    console.log(this.currentDongInfo);
  },
};
</script>
<style scoped></style>
