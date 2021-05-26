<template>
  <v-container class="pa-5">
    <h1 class="mt-3">{{ boardname }}</h1>
    <v-divider class="mb-5"></v-divider>
    <router-view />
  </v-container>
</template>
<script>
import { mapGetters } from 'vuex';

export default {
  created() {
    let gubun = this.$route.params.gubun;
    this.$store.dispatch('getBoards', gubun);
    this.$store.dispatch('getBoardName', gubun);
  },
  computed: {
    ...mapGetters(['boards']),
    ...mapGetters(['boardname']),
  },
};
</script>
<style scoped>
h1 {
  font-family: BMHANNAPro !important;
}
</style>
