<template lang="">
  <div>
    <h1>{{ boardname }}</h1>
    <router-view />
  </div>
</template>
<script>
import { mapGetters } from "vuex";

export default {
  created() {
    console.log("boardPost 생성");
    let gubun = this.$route.params.gubun;
    this.$store.dispatch("getBoards", gubun);
    this.$store.dispatch("getBoardName", gubun);
  },
  computed: {
    ...mapGetters(["boards"]),
    ...mapGetters(["boardname"]),
  },
};
</script>
<style lang=""></style>
