<template>
  <ul class="menu-nav">
    <router-link to="/login" v-slot="{ href, navigate, isActive, isExactActive }">
      <li
        aria-haspopup="true"
        data-menu-toggle="hover"
        class="menu-item"
        :class="[isActive && 'menu-item-active', isExactActive && 'menu-item-active']"
      >
        <a :href="href" class="menu-link" @click="navigate">
          <span class="menu-text"> 로그인 </span>
        </a>
      </li>
    </router-link>

    <router-link to="/register" v-slot="{ href, navigate, isActive, isExactActive }">
      <li
        aria-haspopup="true"
        data-menu-toggle="hover"
        class="menu-item"
        :class="[isActive && 'menu-item-active', isExactActive && 'menu-item-active']"
      >
        <a :href="href" class="menu-link" @click="navigate">
          <span class="menu-text"> 회원가입 </span>
        </a>
      </li>
    </router-link>
  </ul>
</template>

<script>
export default {
  name: 'LoginMenu',
  methods: {
    hasActiveChildren(match) {
      return this.$route['path'].indexOf(match) !== -1;
    },
  },
};
</script>
