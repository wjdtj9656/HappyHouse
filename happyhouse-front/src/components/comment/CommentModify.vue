<template>
  <div>
    <b-card style="margin-bottom: 3%">
      <b-card-text>
        <b>{{ comment.writer }} ({{ comment.userid }})</b></b-card-text
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
          <b-button variant="outline-dark" style="padding: 35% 0" @click.prevent="modifyComment"
            >수정</b-button
          >
        </div>
      </div>
    </b-card>
  </div>
</template>

<script>
import http from "@/api/http.js";

export default {
  name: "CommentModify",
  created() {
    console.log(this.$props.content);
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
      content: this.comment.content,
      noticeno: this.$route.params.noticeno,
    };
  },
  props: {
    comment: Object,
  },
  methods: {
    modifyComment() {
      const comment = {
        content: this.content,
        commentno: this.comment.commentno,
      };
      http
        .put("/comment", comment)
        .then((response) => {
          if (response.data == "success") {
            this.$emit("mvCommentItem");
          } else {
            this.$swal("댓글 수정 실패!");
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
