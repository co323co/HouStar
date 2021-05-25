<template>
  <div class="home">
    <b-container class="bv-example-row">
      <div>
        <banner></banner>
      </div>
    </b-container>
    <button @click="click()">버튼</button>
  </div>
</template>

<script>
// @ is an alias to /src

// import Header from "@/components/include/header";
import Banner from '@/components/include/banner';
import http from '@/util/http-common';
export default {
  name: 'Home',
  components: {
    Banner,
    // Header
  },
  created() {
    console.log('home created');
  },
  methods: {
    click() {
      http
        .get('/address/sidos')
        .then(({ data }) => {
          console.log(data);
          // this.sidos = data;
        })
        .catch(({ response }) => {
          console.log('실패 : : ');
          console.log(response);
        });
    },
  },
};
</script>
<style>
img {
  width: 800px;
}
</style>
