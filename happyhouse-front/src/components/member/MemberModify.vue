<template>
  <div class="parent">
    <div class="card" style="width: 40%">
      <div class="card-body">
        <h5 class="card-title">회원정보</h5>
        <ul class="list-group list-group-flush">
          <li class="list-group-item">아이디: {{ userInfo.id }}</li>
          <li class="list-group-item">
            <b-form-group
              label="이름"
              valid-feedback=""
              :invalid-feedback="invalidFeedbackName"
              :state="stateUserName"
            >
              <b-form-input
                id="username"
                v-model="userInfo.username"
                :state="stateUserName"
                required
                trim
              ></b-form-input>
            </b-form-group>
          </li>
          <li class="list-group-item">
            <b-form-group
              label="비밀번호"
              description="비밀번호는 6자 이상 20자 미만입니다."
              valid-feedback=""
              :invalid-feedback="invalidFeedbackPwd"
              :state="statePwd"
            >
              <b-form-input
                id="password"
                type="password"
                v-model="userInfo.password"
                :state="statePwd"
                required
                trim
              ></b-form-input>
            </b-form-group>
          </li>
          <li class="list-group-item">
            <b-form-group
              label="이메일"
              valid-feedback=""
              :invalid-feedback="invalidFeedbackEmail"
              :state="stateEmail"
            >
              <b-form-input
                id="email"
                v-model="userInfo.email"
                :state="stateEmail"
                required
                trim
              ></b-form-input>
            </b-form-group>
          </li>
          <li class="list-group-item">회원등급: {{ userClasses[userInfo.userclass] }}</li>
        </ul>
        <div class="submit-buttons">
          <b-button class="m-1" variant="light" to="/member/detail">취소</b-button>
          <b-button @click.prevent="modifyInfo" class="m-1" variant="dark">확인</b-button>
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
      userInfo: {
        id: "",
        password: "",
        userclass: "",
        username: "",
        email: "",
      },
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
  computed: {
    stateUserName() {
      return this.userInfo.username.length > 0;
    },
    invalidFeedbackName() {
      return "이름을 입력해주세요.";
    },
    statePwd() {
      return this.userInfo.password.length >= 6 && this.userInfo.password.length < 20;
    },
    invalidFeedbackPwd() {
      if (this.userInfo.password.length > 0) {
        return "6자 이상 입력해주세요.";
      }
      return "비밀번호를 입력해주세요.";
    },
    stateEmail() {
      return this.userInfo.email.length > 0;
    },
    invalidFeedbackEmail() {
      return "이메일을 입력해주세요.";
    },
  },
  methods: {
    modifyInfo() {
      const member = this.userInfo;
      console.log("member: ", member);
      http.put(`/member/${member.id}`, member).then(async (response) => {
        if (response.data == "success") {
          this.$swal("수정 성공");
          this.$router.push("/user/detail");
        } else {
          this.$swal("수정 실패");
        }
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
</style>
