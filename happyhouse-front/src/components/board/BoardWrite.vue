<template>
  <div class="board-form">
    <b-form-input v-model="title" placeholder="제목"></b-form-input>
    <b-form-textarea
      id="board-content"
      placeholder="내용"
      v-model="content"
      rows="15"
    ></b-form-textarea>
    <div class="submit-buttons">
      <b-button class="m-1" variant="light" to="/board/list">취소</b-button>
      <b-button @click.prevent="registerNotice" class="m-1" variant="dark">등록</b-button>
    </div>
  </div>
</template>

<script>
import http from "@/api/http";

export default {
  data() {
    return {
      title: "",
      content: "",
      userInfo: {},
    };
  },
  created() {
    let jwtAuthToken = sessionStorage.getItem("access-token");
    this.$store
      .dispatch("getUserInfo", jwtAuthToken)
      .then(() => {
        this.userInfo = this.$store.state.userInfo;
      })
      .catch((error) => {
        console.log(error);
      });
  },
  methods: {
    registerNotice() {
      if (this.title.length <= 0) {
        this.$swal("제목을 입력하세요!");
        return;
      } else if (this.content.length <= 0) {
        this.$swal("내용을 입력하세요!");
        return;
      }

      const boardItem = {
        title: this.title,
        writer: this.userInfo.username,
        content: this.content,
        userid: this.userInfo.id,
      };

      console.log(boardItem);
      http
        .post("/notice", boardItem)
        .then((response) => {
          if (response.data == "success") {
            this.$swal("등록 성공!");
            this.$router.push("/board");
          } else {
            this.$swal("등록 실패!");
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
