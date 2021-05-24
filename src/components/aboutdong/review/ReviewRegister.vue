<template>
  <v-card :loading="loading" class="mx-auto my-12" max-width="1000">
    <v-row align="center">
      <v-col>
        <div class="text-center">
          <label>환경</label>
          <v-rating
            v-model="ienvironment"
            background-color="purple lighten-3"
            color="purple"
          ></v-rating>
          <label>건강</label>
          <v-rating v-model="ihealth" background-color="pink lighten-3" color="pink"></v-rating>
          <label>인프라</label>
          <v-rating v-model="iinfra" background-color="orange lighten-3" color="orange"></v-rating>
          <label>안전</label>
          <v-rating v-model="isafety" background-color="green lighten-3" color="green"></v-rating>
          <label>학군</label>
          <v-rating v-model="ischool" background-color="green lighten-3" color="green"></v-rating>
          <label>교통</label>
          <v-rating
            v-model="itrans"
            background-color="indigo lighten-3"
            color="indigo"
          ></v-rating></div
      ></v-col>
      <v-col>
        <v-card-text align="rignt">
          <v-textarea solo name="input-7-4" label="Solo textarea" v-model="icontent"></v-textarea>
          <button @click="register">등록</button>
        </v-card-text>
      </v-col>
    </v-row>
  </v-card>
</template>

<script>
// import { mapActions } from 'vuex';
export default {
  methods: {
    register() {},
  },
  data() {
    return {
      ienvironment: '',
      ihealth: '',
      iinfra: '',
      isafety: '',
      ischool: '',
      itrans: '',
    };
  },
};
</script>

<style scoped>
.completed {
  text-decoration: line-through;
}
</style>
