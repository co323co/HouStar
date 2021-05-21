<template lang="">
  <div>
    <!-- 시군구 select -->
    <div class="bg-dark section pt-4 pb-4">
      <div class="sorting-filters text-center mb-20 d-flex justify-content-center">
        <div class="form-group mr-2">
          <select
            class="form-control bgPink"
            name="sido"
            id="sido"
            @change="getGugun()"
            v-model="sido"
          >
            <option value="" disabled selected hidden>선택</option>
            <option v-for="(s, idx) in sidos" :key="idx" :value="s">{{ s.sidoName }}</option>
          </select>
        </div>
        <div class="form-group md-5">
          <select class="form-control" name="gugun" id="gugun" v-model="gugun" @change="getDong()">
            <option value="" disabled selected hidden>선택</option>
            <option v-for="(g, idx) in guguns" :key="idx" :value="g">{{ g.gugunName }}</option>
          </select>
        </div>
        <div class="form-group ml-2">
          <select class="form-control" name="dong" id="dong">
            <option value="0">선택</option>
          </select>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import http from '@/core/services/http-common';

export default {
  data() {
    return {
      sido: '',
      sidos: [],
      gugun: '',
      guguns: [],
    };
  },
  created() {
    http
      .get('/address/sidos')
      .then(({ data }) => {
        //   console.log('성공 : :');
        //    console.log(data);
        this.sidos = data;
      })
      .catch(({ response }) => {
        console.log('실패 : : ');
        console.log(response);
      });
  },
  methods: {
    getGugun() {
      // console.log(this.sido);
      http
        .get('/address/guguns/' + this.sido.sidoCode)
        .then(({ data }) => {
          //    console.log('성공 : :');
          console.log(data);
          this.guguns = data;
        })
        .catch(({ response }) => {
          console.log('실패 : : ');
          console.log(response);
        });
    },
    getDong() {
      // const = {
      // };
      // http
      //   .get('/address/guguns/' + )
      //   .then(({ data }) => {
      //     //    console.log('성공 : :');
      //     //      console.log(data);
      //     this.guguns = data;
      //   })
      //   .catch(({ response }) => {
      //     console.log('실패 : : ');
      //     console.log(response);
      //   });
    },
  },
};
</script>
<style lang=""></style>
