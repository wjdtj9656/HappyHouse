<template>
  <div>
    <router-link to="/board"><h2 id="viewTitle">Q&A</h2></router-link>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: "BoardView",
};
</script>

<style>
#viewTitle {
  margin: 50px;
  text-align: center;
}
</style>
