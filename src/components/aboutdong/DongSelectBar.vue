<template lang="">
  <div>
    <!-- 시군구 select -->
    <div class="bg-secondary section pt-2 pb-2">
      <div class="sorting-filters text-center mt-10 d-flex justify-content-center">
        <div class="form-group mr-2">
          <v-container>
            <v-row dense>
              <v-col cols="4">
                <v-autocomplete
                  v-model="sido"
                  :items="sidos"
                  item-text="sidoName"
                  item-value="sidoCode"
                  label="시 선택"
                  dense
                  solo
                  @change="getGugun()"
                ></v-autocomplete>
              </v-col>
              <v-col cols="4">
                <v-autocomplete
                  v-model="gugun"
                  :items="guguns"
                  item-text="gugunName"
                  item-value="gugunCode"
                  label="구군 선택"
                  dense
                  solo
                  @change="getDong()"
                  return-object
                ></v-autocomplete>
              </v-col>
              <v-col cols="4">
                <v-autocomplete
                  v-model="dong"
                  :items="dongs"
                  item-text="dongName"
                  item-value="dongCode"
                  label="동 선택"
                  dense
                  solo
                  @change="moveInfo()"
                  return-object
                ></v-autocomplete>
              </v-col>
            </v-row>
          </v-container>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import http from '@/core/services/http-common';
// import Vuetify from 'vuetify';
export default {
  data() {
    return {
      sido: '',
      sidos: [],
      gugun: '',
      guguns: [],
      dong: '',
      dongs: [],
    };
  },
  created() {
    http
      .get('/address/sidos')
      .then(({ data }) => {
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
        .get('/address/guguns/' + this.sido)
        .then(({ data }) => {
          console.log(data);
          this.guguns = data;
        })
        .catch(({ response }) => {
          console.log('실패 : : ');
          console.log(response);
        });
    },
    getDong() {
      // console.log(this.)
      // console.log(this.gugun);
      http
        .get('/address/dongs/' + this.gugun.gugunCode + '/' + this.gugun.gugunName)
        .then(({ data }) => {
          console.log('성공 : :');
          console.log(data);
          this.dongs = data;
        })
        .catch(({ response }) => {
          console.log('실패 : : ');
          console.log(response);
        });
    },
    moveInfo() {
      console.log(this.dong);
      //$store.commit('dongStore/setSidogugundong', this.dong);
      console.log(this.$store.state.dongStore.Sidogugundong);
      this.$store.state.dongStore.Sidogugundong = this.dong;
      console.log(this.$store.state.dongStore.Sidogugundong.dongName);
      this.$router.push('dong-info');
      // this.$router.push('dong-info');
    },
  },
};
</script>
x`
<style>
.v-list {
  background-color: white;
}
</style>
