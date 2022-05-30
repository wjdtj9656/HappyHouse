<template>
  <div>
    <div v-for="(comment, index) in this.commentList" :key="index">
      <comment-list-item
        v-if="index != modifyIdx"
        :comment="comment"
        :userId="userId"
        @mvModifyComment="showModifyComment(index)"
        @deleteComment="getCommentList()"
      ></comment-list-item>
      <comment-modify
        @mvCommentItem="showCommentItem()"
        v-else
        :comment="comment"
      ></comment-modify>
    </div>
  </div>
</template>

<script>
import http from "@/api/http.js";
import CommentListItem from "@/components/comment/CommentListItem.vue";
import CommentModify from "./CommentModify.vue";

export default {
  name: "CommentList",
  components: {
    CommentListItem,
    CommentModify,
  },
  created() {
    this.getCommentList();
  },
  data() {
    return {
      commentList: [],
      noticeno: this.$route.params.noticeno,
      currentComponent: "CommentListItem",
      modifyIdx: -1,
    };
  },
  props: {
    userId: String,
    isRefresh: Boolean,
  },
  watch: {
    isRefresh: function () {
      this.getCommentList();
    },
  },
  methods: {
    showModifyComment(index) {
      this.modifyIdx = index;
      console.log("modify: ", index);
    },
    showCommentItem() {
      this.modifyIdx = -1;
      this.getCommentList();
    },
    getCommentList() {
      http.get(`/comment/${this.noticeno}`).then((response) => {
        this.commentList = response.data;
        console.log("commentList: ", this.commentList);
      });
    },
  },
};
</script>

<style></style>
