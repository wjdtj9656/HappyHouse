<template>
  <div id="signup-form">
    <b-form-group
      label="아이디"
      description="아이디는 6자 이상 16자 미만입니다."
      valid-feedback="사용 가능한 아이디입니다."
      :invalid-feedback="invalidFeedbackId"
      :state="stateId"
    >
      <b-form-input
        id="id"
        v-model="id"
        :state="stateId"
        @blur="checkId"
        required
        trim
      ></b-form-input>
    </b-form-group>
    <b-form-group
      label="이름"
      valid-feedback=""
      :invalid-feedback="invalidFeedbackName"
      :state="stateUserName"
    >
      <b-form-input
        id="username"
        v-model="username"
        :state="stateUserName"
        required
        trim
      ></b-form-input>
    </b-form-group>
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
        v-model="password"
        :state="statePwd"
        required
        trim
      ></b-form-input>
    </b-form-group>
    <b-form-group
      label="이메일"
      valid-feedback=""
      :invalid-feedback="invalidFeedbackEmail"
      :state="stateEmail"
    >
      <b-form-input id="email" v-model="email" :state="stateEmail" required trim></b-form-input>
    </b-form-group>
    <div class="submit-buttons">
      <b-button class="m-1" variant="light" to="/">취소</b-button>
      <b-button class="m-1" variant="dark" @click.prevent="registerMember">가입</b-button>
    </div>
  </div>
</template>

<script>
import http from "@/api/http";

export default {
  computed: {
    stateId() {
      return this.id.length >= 6 && this.id.length < 16 && this.availableId;
    },
    invalidFeedbackId() {
      if (this.id.length <= 0) {
        return "아이디를 입력해주세요.";
      } else if (this.id.length > 0 && this.id.length < 6) {
        return "6자 이상 입력해주세요.";
      } else {
        return "중복된 아이디입니다.";
      }
    },
    stateUserName() {
      return this.username.length > 0;
    },
    invalidFeedbackName() {
      return "이름을 입력해주세요.";
    },
    statePwd() {
      return this.password.length >= 6 && this.password.length < 20;
    },
    invalidFeedbackPwd() {
      if (this.password.length > 0) {
        return "6자 이상 입력해주세요.";
      }
      return "비밀번호를 입력해주세요.";
    },
    stateEmail() {
      return this.email.length > 0;
    },
    invalidFeedbackEmail() {
      return "이메일을 입력해주세요.";
    },
  },
  data() {
    return {
      id: "",
      username: "",
      password: "",
      email: "",
      availableId: true,
    };
  },
  methods: {
    registerMember() {
      console.log("call registerMember");

      const member = {
        id: this.id,
        username: this.username,
        password: this.password,
        email: this.email,
      };
      http
        .post(`/member/register`, member)
        .then(async (response) => {
          if (response.data == "success") {
            this.$swal("회원가입 완료");
            this.$router.push("/");
          } else {
            this.$swal("에러가 발생했습니다.");
          }
        })
        .catch((error) => {
          console.dir(error);
        });
    },
    checkId() {
      http
        .get(`/member/idcheck`, {
          params: {
            ckid: this.id,
          },
        })
        .then(async (response) => {
          console.log(response);
          if (response > 0) {
            this.availableId = false;
          } else {
            this.availableId = true;
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
#signup-form {
  width: 40%;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  padding: 50px;
  padding-bottom: 100px;
  margin: auto;
}
b-form-group {
  padding: 10px;
}

.submit-buttons {
  float: right;
}
</style>
