<template>
  <div id="app">
    <header-navi-bar />
    <router-view />
    <footer-bar />
  </div>
</template>
<script>
import HeaderNaviBar from "@/components/layout/HeaderNaviBar.vue";
import FooterBar from "@/components/layout/FooterBar.vue";
export default {
  name: "App",
  components: {
    HeaderNaviBar,
    FooterBar,
  },
};
</script>
<style>
body {
  padding-top: 60px;
}
</style>
