<template>
  <div id="login-form">
    <b-form-group label="아이디">
      <b-form-input id="id" v-model="id" placeholder="아이디를 입력하세요." required></b-form-input>
    </b-form-group>
    <b-form-group label="비밀번호">
      <b-form-input
        id="password"
        type="password"
        v-model="password"
        placeholder="비밀번호를 입력하세요."
        required
      ></b-form-input>
    </b-form-group>
    <div class="submit-buttons">
      <b-button class="m-1" variant="light" to="/">취소</b-button>
      <b-button class="m-1" variant="dark" @click.prevent="loginMember">로그인</b-button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      id: "",
      password: "",
    };
  },
  methods: {
    loginMember() {
      const id = this.id;
      const password = this.password;

      this.$store
        .dispatch("logIn", { id, password })
        .then(() => this.redirect())
        .catch((response) => {
          console.log(response);
          this.$swal("아이디와 비밀번호를 확인하세요");
        });
    },

    redirect() {
      this.$router.push("/");
    },
  },
};
</script>

<style>
#login-form {
  height: 80vh;
  width: 40%;
  padding: 50px;
  padding-bottom: 100px;
  margin: auto;
}

.submit-buttons {
  float: right;
}
</style>
