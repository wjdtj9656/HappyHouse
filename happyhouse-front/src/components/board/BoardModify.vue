<template>
  <div class="board-form">
    <b-form-input v-model="title" placeholder="제목"></b-form-input>
    <b-form-textarea id="board-content" v-model="content" rows="15"></b-form-textarea>
    <div class="submit-buttons">
      <b-button class="m-1" variant="light" to="/board/list">취소</b-button>
      <b-button @click.prevent="modifyBoard" class="m-1" variant="dark">수정</b-button>
    </div>
  </div>
</template>

<script>
import http from "@/api/http";
import jwt_decode from "jwt-decode";

export default {
  created() {
    // 사용자 정보 불러오기
    let jwtAuthToken = sessionStorage.getItem("access-token");
    this.userId = jwt_decode(jwtAuthToken);

    // 게시물 정보 불러오기
    http
      .get(`/notice/${this.noticeno}`)
      .then(async (response) => {
        const boardInfo = response.data;
        this.title = boardInfo.title;
        this.content = boardInfo.content;
      })
      .catch((error) => {
        console.dir(error);
      });
  },
  data() {
    return {
      noticeno: this.$route.params.noticeno,
      userId: "",
      title: "",
      content: "",
    };
  },
  methods: {
    modifyBoard() {
      if (this.title.length <= 0) {
        this.$swal("제목을 입력하세요!");
        return;
      } else if (this.content.length <= 0) {
        this.$swal("내용을 입력하세요!");
        return;
      }

      const board = {
        title: this.title,
        content: this.content,
        noticeno: this.noticeno,
      };
      console.log(board);
      http
        .put(`/notice/${this.noticeno}`, board)
        .then((response) => {
          if (response.data == "success") {
            this.$swal("수정 완료!");
            this.$router.push(`/board/detail/${this.noticeno}`);
          } else {
            this.$swal("수정 중 에러 발생!");
          }
        })
        .catch((error) => {
          console.dir(error);
        });
    },
  },
};
</script>

<style>
#board-content {
  margin: 20px 0px;
}
.board-form {
  width: 40%;
  margin: auto;
  padding-bottom: 70px;
}
</style>
