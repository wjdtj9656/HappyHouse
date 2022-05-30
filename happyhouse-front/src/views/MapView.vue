<template>
  <div class="map__main">
    <!-- <div class="map__list"><h1>haha</h1></div> -->
    <kakao-map />
  </div>
</template>

<script>
import KakaoMap from "@/components/KaKaoMap.vue";
export default {
  components: {
    KakaoMap,
  },
};
</script>

<style>
.map__main {
  display: flex;
}
.map__content {
  margin: 100px 100px;
  width: 100%;
  height: 100%;
}
</style>
