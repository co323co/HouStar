<template>
  <tr>
    <td>{{ id }}</td>
    <td>
      <router-link :to="$route.path + `/view/` + id">{{ title }}</router-link>
    </td>
    <td>{{ userid }}</td>
    <td>{{ regtime }}</td>
    <td>{{ views }}</td>
  </tr>
</template>

<script>
export default {
  name: 'listrow',
  props: {
    id: Number,
    title: String,
    userid: String,
    regtime: String,
    views: Number,
  },
  methods: {},
};
</script>
<style scope>
td {
  text-align: center;
  border-bottom: 1px solid #ddd;
  height: 50px;
}
</style>
