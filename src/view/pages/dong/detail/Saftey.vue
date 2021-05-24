<template lang="">
  <div></div>
</template>
<script>
import { mapGetters } from 'vuex';
export default {
  computed: {
    ...mapGetters(['currentDongInfo']),
  },
  created() {
    // console.log(this.currentDongInfo);
  },
};
</script>
<style scoped></style>
