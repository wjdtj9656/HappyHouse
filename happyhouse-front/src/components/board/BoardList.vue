<template>
  <div class="boardList">
    <b-table
      hover
      :items="boardItems"
      :fields="fields"
      @row-clicked="showBoardDetail"
    ></b-table>
    <div class="buttons">
      <b-button to="/board/write" class="m-1" variant="dark">글쓰기</b-button>
    </div>
  </div>
</template>

<script>
import http from "@/api/http";

export default {
  created() {
    http
      .get("/notice")
      .then(async (response) => {
        this.boardItems = response.data;
      })
      .catch((error) => {
        console.dir(error);
      });
  },
  data() {
    return {
      fields: [
        { key: "noticeno", label: "글번호", class: "w10" },
        { key: "title", label: "제목", class: "w60" },
        { key: "writer", label: "작성자", class: "w15" },
        { key: "regdate", label: "작성일자", class: "w15" },
      ],
      boardItems: [],
      userInfo: {},
    };
  },
  methods: {
    showBoardDetail(record) {
      let noticeno = record.noticeno;
      this.$router.push(`/board/detail/${noticeno}`);
    },
  },
};
</script>

<style>
.buttons {
  float: right;
}
.w10 {
  width: 10%;
  text-align: center;
}
.w15 {
  width: 15%;
  text-align: center;
}
.w60 {
  width: 60%;
}
.boardList {
  width: 70%;
  padding: 5%;
  margin: auto;
}
</style>
