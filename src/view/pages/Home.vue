<template lang="">
  <v-container class="justify-center">
    <v-layout>
      <v-carousel
        v-ripple
        hide-delimiter-background
        delimiter-icon="mdi-minus"
        show-arrows-on-hover="true"
        height="500"
        cycle="cycle"
        interval="4000"
        tag="#tag"
      >
        <v-carousel-item
          v-for="(item, i) in banner"
          :key="i"
          :src="item.src"
          reverse-transition="fade-transition"
          transition="fade-transition"
        >
          <v-flex id="banner-text">
            <div style="height: 75%"></div>
            <h1>Houstar</h1>
            <h3>Looking for a place to love?</h3>
          </v-flex>
        </v-carousel-item>
      </v-carousel>
    </v-layout>
    <v-layout>
      <v-flex>
        <br />
        <h2 class="mb-5">동네 구경하기</h2>
        <DongSelectBar />
      </v-flex>
    </v-layout>
  </v-container>
</template>
<script>
import DongSelectBar from '@/components/aboutdong/DongSelectBar.vue';
export default {
  data() {
    return {
      banner: [
        {
          src: 'media/banner/house1.jpg',
        },
        {
          src: 'media/banner/house2.jpg',
        },
        {
          src: 'media/banner/house3.jpg',
        },
        {
          src: 'media/banner/house4.jpg',
        },
        {
          src: 'media/banner/house5.jpg',
        },
      ],
    };
  },
  components: {
    DongSelectBar,
  },
};
</script>
<style scoped>
h2 {
  text-align: center;
}
#banner-text {
  color: beige;
  margin: auto;
  text-align: center;
  height: 100%;
  text-shadow: -1px 0 black, 0 1px black, 1px 0 lightgrey, 0 -1px lightgrey;
}
</style>
