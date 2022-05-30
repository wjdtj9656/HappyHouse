<template>
  <div class="newsList">
    <b-list-group v-for="(news, index) in newsList" :key="index">
      <b-list-group-item @click="moveToNews(news)">
        <div
          class="center-block align-middle"
          style="display: inline-block; margin-right: 20px"
        >
          <img class="img-thumbnail" :src="news.imgsrc" />
        </div>
        <div class="align-middle" style="width: 80%; display: inline-block">
          <h5 class="mb-1">{{ news.title }}</h5>
          <small class="text-muted">{{ news.writing }}</small>

          <p class="mb-1">{{ news.preview }}</p>
        </div>
      </b-list-group-item>
    </b-list-group>
  </div>
</template>

<script>
import http from "@/api/http.js";
export default {
  created() {
    http
      .get("/news")
      .then((response) => {
        this.newsList = response.data;
        console.log(this.newsList[0]);
      })
      .catch((error) => {
        console.dir(error);
      });
  },
  data() {
    return {
      newsList: [],
      fields: [
        { key: "title", label: "제목", class: "w30" },
        { key: "writing", label: "언론사", class: "w25" },
        { key: "preview", label: "미리보기", class: "w50" },
      ],
    };
  },
  methods: {
    moveToNews(record) {
      window.open(record.url, "_blank");
    },
  },
};
</script>

<style>
.newsList {
  width: 60%;
  margin: auto;
}
</style>
