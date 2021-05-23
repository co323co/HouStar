<!-- 마우스를 갖다대면 텍스트 Color가 바뀌는 컴포넌트 -->
<template lang="">
  <div
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{ 'mouse-over-bgcolor': isColor }"
  >
    <h5>{{ msg }}</h5>
  </div>
</template>
<script>
export default {
  props: ['msg'],
  data() {
    return {
      isColor: false,
    };
  },
  methods: {
    colorChange(flag) {
      this.isColor = flag;
    },
  },
};
</script>
<style scoped>
.mouse-over-bgcolor {
  color: orange;
}
</style>
