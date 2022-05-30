<template>
  <div class="boardDetail">
    <div>
      <div class="boardDetail-buttons">
        <b-button variant="outline-dark" id="boardList" to="/board/list"
          >목록</b-button
        ><b-button
          variant="danger"
          v-if="boardInfo.userid == userId"
          id="boardDelete"
          @click.prevent="deleteBoard"
          >삭제</b-button
        >
        <b-button
          variant="dark"
          v-if="boardInfo.userid == userId"
          id="boardModify"
          @click="moveModifyBoard"
          >수정</b-button
        >
      </div>
      <b-card style="margin: 20px auto">
        <b-list-group flush>
          <b-list-group-item
            ><h2 v-html="boardInfo.title"></h2
          ></b-list-group-item>
          <b-list-group-item
            ><b>제목</b> {{ boardInfo.title }}
          </b-list-group-item>
          <b-list-group-item
            ><b>작성자</b> {{ boardInfo.writer }} ({{
              boardInfo.userid
            }})</b-list-group-item
          >
          <b-list-group-item
            ><b>작성일</b> {{ boardInfo.regdate }}</b-list-group-item
          >
          <b-list-group-item style="white-space: pre-wrap"
            ><div style="margin-bottom: 10px"><b>내용</b></div>
            {{ boardInfo.content }}</b-list-group-item
          >
        </b-list-group>
      </b-card>
    </div>
    <comment-write @addComment="refreshCommentList()"></comment-write>
    <comment-list :userId="userId" :isRefresh="isRefresh"></comment-list>
  </div>
</template>

<script>
import http from "@/api/http.js";
import jwt_decode from "jwt-decode";
import CommentWrite from "../comment/CommentWrite.vue";
import CommentList from "../comment/CommentList.vue";

export default {
  components: {
    CommentList,
    CommentWrite,
  },
  created() {
    http.get(`/notice/${this.noticeno}`).then(async (response) => {
      this.boardInfo = response.data;
    });
    let jwtAuthToken = sessionStorage.getItem("access-token");
    this.userId = jwt_decode(jwtAuthToken).user;
  },
  data() {
    return {
      noticeno: this.$route.params.noticeno,
      boardInfo: {},
      isRefresh: false,
      userId: "",
    };
  },
  methods: {
    deleteBoard() {
      http
        .delete(`/notice/${this.noticeno}`)
        .then((response) => {
          if (response.data == "success") {
            this.$swal("삭제완료");
            this.$router.push("/board/list");
          } else {
            this.$swal("삭제 중 에러 발생!");
          }
        })
        .catch((error) => {
          console.dir(error);
        });
    },
    moveModifyBoard() {
      this.$router.push(`/board/modify/${this.noticeno}`);
    },
    refreshCommentList() {
      this.isRefresh = !this.isRefresh;
    },
  },
};
</script>

<style>
.boardDetail {
  width: 50%;
  margin: 5% auto;
}

#boardDelete,
#boardModify {
  float: right;
}

#boardModify {
  margin-right: 10px;
}
.row {
  text-align: left;
  white-space: pre;
}
</style>
