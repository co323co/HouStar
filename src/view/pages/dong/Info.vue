<template lang="">
  <div>
    info.vue페이지
    <InfoHeader />
    {{ this.$store.state.dongStore.Sidogugundong.dongCode }}
    {{ this.$store.state.dongStore.Sidogugundong.dongName }}
    {{ this.$store.state.dongStore.Sidogugundong.gugunCode }}
    {{ this.$store.state.dongStore.Sidogugundong.gugunName }}
    {{ this.$store.state.dongStore.Sidogugundong.sidoCode }}
    {{ this.$store.state.dongStore.Sidogugundong.sidoName }}
  </div>
</template>
<script>
// import DongSelectBar from '@/components/aboutdong/DongSelectBar.vue';
import InfoHeader from '@/components/aboutdong/InfoHeader.vue';
export default {
  components: {
    InfoHeader,
  },
};
</script>
<style lang=""></style>
