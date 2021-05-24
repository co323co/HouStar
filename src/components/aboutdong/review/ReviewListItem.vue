<template>
  <v-card class="mx-auto my-12" max-width="1000">
    <v-card-title>
      {{ review.userid }} | {{ review.age_range }}대 | {{ review.family_type }}</v-card-title
    >
    <v-row align="center">
      <v-col>
        <div class="text-center">
          <label>환경</label>
          <v-rating
            v-model="review.environment"
            background-color="purple lighten-3"
            color="purple"
          ></v-rating>
          <label>건강</label>
          <v-rating
            v-model="review.health"
            background-color="pink lighten-3"
            color="pink"
          ></v-rating>
          <label>인프라</label>
          <v-rating
            v-model="review.infra"
            background-color="orange lighten-3"
            color="orange"
          ></v-rating>
          <label>안전</label>
          <v-rating
            v-model="review.safety"
            background-color="green lighten-3"
            color="green"
          ></v-rating>
          <label>학군</label>
          <v-rating
            v-model="review.school"
            background-color="green lighten-3"
            color="green"
          ></v-rating>
          <label>교통</label>
          <v-rating
            v-model="review.trans"
            background-color="indigo lighten-3"
            color="indigo"
          ></v-rating></div
      ></v-col>
      <v-col>
        <v-card-text>
          {{ review.content }}
        </v-card-text>
      </v-col>
    </v-row>
    <v-card-text align="rignt">
      <button @click="deleteTodo">수정</button>
      <button @click="deleteTodo">제거</button>
    </v-card-text>
  </v-card>
</template>

<script>
// import { mapActions } from 'vuex';
export default {
  props: ['review'],
  methods: { deleteTodo() {} },
  data() {
    return {};
  },
};
</script>

<style scoped>
.completed {
  text-decoration: line-through;
}
</style>
