<template>
  <div>
    <b-card style="margin-bottom: 3%">
      <b-card-text>
        <b>{{ userInfo.username }} ({{ userInfo.id }})</b></b-card-text
      >
      <div class="d-flex">
        <b-form-textarea
          style="width: 85%"
          id="textarea"
          v-model="content"
          placeholder="댓글"
          rows="3"
        ></b-form-textarea>
        <div class="d-flex flex-column" style="width: 12%; margin-left: 3%">
          <b-button variant="outline-dark" style="padding: 35% 0" @click.prevent="addComment"
            >등록</b-button
          >
        </div>
      </div>
    </b-card>
  </div>
</template>

<script>
import http from "@/api/http";

export default {
  name: "CommentWrite",
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
  data() {
    return {
      userInfo: {},
      content: "",
      noticeno: this.$route.params.noticeno,
    };
  },
  methods: {
    addComment() {
      const comment = {
        noticeno: this.noticeno,
        writer: this.userInfo.username,
        userid: this.userInfo.id,
        content: this.content,
      };
      console.log(comment);
      http
        .post("/comment", comment)
        .then(async (response) => {
          if (response.data == "success") {
            this.content = "";
            this.$emit("addComment");
          } else {
            this.$swal("댓글 등록 중 에러 발생!");
          }
        })
        .catch((error) => {
          console.dir(error);
        });
    },
  },
};
</script>

<style></style>
