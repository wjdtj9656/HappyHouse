<template>
  <div ref="commentItem">
    <b-card class="overflow-hidden" style="margin: 0 auto">
      <b-row no-gutters>
        <b-col md="11">
          <b-card-text style="margin: 0"
            ><b>{{ comment.writer }} ({{ comment.userid }})</b></b-card-text
          >
          <small>{{ comment.regdate }}</small>
          <b-card-text style="white-space: pre-wrap">{{ comment.content }}</b-card-text>
        </b-col>

        <b-col
          md="1"
          class="btn-group-vertical"
          v-if="comment.userid == userId"
          style="height: 100%"
        >
          <b-button variant="dark" @click.prevent="mvModifyComment" style="margin-bottom: 10%"
            >수정</b-button
          >
          <b-button variant="danger" @click.prevent="deleteComment">삭제</b-button>
        </b-col>
      </b-row>
    </b-card>
  </div>
</template>

<script>
import http from "@/api/http";

export default {
  name: "CommentListItem",
  props: {
    comment: Object,
    userId: String,
  },
  methods: {
    mvModifyComment() {
      this.$emit("mvModifyComment");
    },
    deleteComment() {
      http
        .delete(`/comment/${this.$props.comment.commentno}`)
        .then((response) => {
          if (response.data == "success") {
            this.$emit("deleteComment");
          } else {
            this.$swal("댓글 삭제 실패!");
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
