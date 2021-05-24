<template>
  <v-card class="mx-auto my-12" max-width="1000">
    <v-card-title>
      <v-row v-if="review">
        <p v-if="review.userid">{{ review.userid }} |</p>
        <p v-if="review.age_range">{{ review.age_range }}대 |</p>
        <p v-if="review.family_type">{{ review.family_type }} |</p>
        <p v-if="review.tag">
          {{ review.tag | list }}
        </p>
      </v-row>
    </v-card-title>
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
          <label>대중교통</label>
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
  beforeMount() {
    this.convertTagToList();
    //  console.log('mounted rrrrrr');
    //  console.log(this.review.tag);
  },
  data() {
    return {
      taglist: '',
    };
  },
  props: ['review'],
  methods: {
    deleteTodo() {},
    convertTagToList() {
      //console.log('convertTagToList if문 밖에');

      if (this.review.tag) {
        this.taglist = this.review.tag.split(',');
        // console.log('convertTagToList');
        //  console.log(this.taglist);/
      }
    },
  },
  filters: {
    ageRange: function(ageRange) {
      if (!ageRange) return '';
      if (ageRange == 'over') return '60대 이상';
      else return ageRange + '대';
    },
    familyType: function(type) {
      if (!type) return '';
      else return type;
    },
    list: function(list) {
      if (!list) return '';
      let str = '';
      for (let i = 0; i < list.length - 1; i++) {
        str += list[i];
      }
      str += list[list.length - 1];
      return str;
    },
  },
};
</script>

<style scoped>
.completed {
  text-decoration: line-through;
}
</style>
