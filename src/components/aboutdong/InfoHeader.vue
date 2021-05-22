<template lang="">
  <v-app>
    <v-card>
      {{ this.$store.state.dongStore.Sidogugundong.dongName }}
      <v-system-bar></v-system-bar>
      <v-toolbar flat>
        <v-toolbar-title>
          <h1 class="display-3"></h1>
          <h1 class="display-3">{{ this.$store.state.dongStore.Sidogugundong.dongName }}</h1>
        </v-toolbar-title>
        <v-spacer></v-spacer>
      </v-toolbar>
      <v-banner single-line>
        We can't save your edits while you are in offline mode.
      </v-banner>

      <v-card-text class="grey lighten-4">
        <v-sheet max-width="800" height="300" class="mx-auto"></v-sheet>
      </v-card-text>
    </v-card>
  </v-app>
</template>
<script>
export default {};
</script>
<style lang=""></style>
