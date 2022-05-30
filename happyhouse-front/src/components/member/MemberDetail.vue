<template>
  <div class="parent">
    <div class="card" style="width: 40%">
      <div class="card-body">
        <h5 class="card-title">회원정보</h5>
        <ul class="list-group list-group-flush">
          <li class="list-group-item">아이디: {{ userInfo.id }}</li>
          <li class="list-group-item">이름: {{ userInfo.username }}</li>
          <li class="list-group-item">이메일: {{ userInfo.email }}</li>
          <li class="list-group-item">
            회원등급: {{ userClasses[userInfo.userclass] }}
          </li>
        </ul>
        <div class="submit-buttons">
          <b-button class="m-1" variant="outline-dark" to="/">홈으로</b-button>
          <b-button variant="warning" @click.prevent="deleteUser"
            >탈퇴</b-button
          >
          <b-button to="/user/modify" class="m-1" variant="dark">수정</b-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/api/http";

export default {
  data() {
    return {
      userInfo: {},
      userClasses: ["관리자", "일반회원"],
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
    deleteUser() {
      let id = this.userInfo.id;
      http
        .delete(`/member/${id}`)
        .then(async (response) => {
          if (response.data == "success") {
            this.$swal("탈퇴 완료!");
            this.$store.dispatch("logOut").then(() => this.$router.push("/"));
          } else {
            this.$swal("탈퇴 중 에러 발생!");
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
.card {
  margin: 10% auto;
  float: none;
}
.submit-buttons {
  float: right;
}
</style>
