<template lang="">
  <v-container>
    <h1>{{ boardname }}</h1>
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
<style lang=""></style>
