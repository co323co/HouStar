<template>
  <ul class="menu-nav">
    <router-link to="/home" v-slot="{ href, navigate, isActive, isExactActive }">
      <li
        aria-haspopup="true"
        data-menu-toggle="hover"
        class="menu-item"
        :class="[isActive && 'menu-item-active', isExactActive && 'menu-item-active']"
      >
        <a :href="href" class="menu-link" @click="navigate">
          <span class="menu-text"> HOME </span>
        </a>
      </li>
    </router-link>

    <router-link to="/notice" v-slot="{ href, navigate, isActive, isExactActive }">
      <li
        aria-haspopup="true"
        data-menu-toggle="hover"
        class="menu-item"
        :class="[isActive && 'menu-item-active', isExactActive && 'menu-item-active']"
      >
        <a :href="href" class="menu-link" @click="navigate">
          <span class="menu-text"> 공지사항 </span>
        </a>
      </li>
    </router-link>

    <router-link to="/qna" v-slot="{ href, navigate, isActive, isExactActive }">
      <li
        aria-haspopup="true"
        data-menu-toggle="hover"
        class="menu-item"
        :class="[isActive && 'menu-item-active', isExactActive && 'menu-item-active']"
      >
        <a :href="href" class="menu-link" @click="navigate">
          <span class="menu-text"> QnA </span>
        </a>
      </li>
    </router-link>

    <router-link to="/community" v-slot="{ href, navigate, isActive, isExactActive }">
      <li
        aria-haspopup="true"
        data-menu-toggle="hover"
        class="menu-item"
        :class="[isActive && 'menu-item-active', isExactActive && 'menu-item-active']"
      >
        <a :href="href" class="menu-link" @click="navigate">
          <span class="menu-text"> 커뮤니티 </span>
        </a>
      </li>
    </router-link>
  </ul>
</template>

<script>
export default {
  name: 'HSMenu',
  methods: {
    hasActiveChildren(match) {
      return this.$route['path'].indexOf(match) !== -1;
    },
  },
};
</script>
