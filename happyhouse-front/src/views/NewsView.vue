<template>
  <div>
    <router-link to="/news"><h2 id="viewTitle">NEWS</h2></router-link>
    <news-list></news-list>
  </div>
</template>

<script>
import NewsList from "@/components/news/NewsList.vue";
export default {
  components: {
    NewsList,
  },
};
</script>

<style>
#viewTitle {
  margin: 50px;
  text-align: center;
}
</style>
