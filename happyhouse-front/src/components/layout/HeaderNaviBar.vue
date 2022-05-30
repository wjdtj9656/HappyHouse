<template>
  <div>
    <b-navbar
      toggleable="lg"
      class="text-light fixed-top"
      variant="dark"
      style="box-shadow: 0 0.25rem 0.25rem rgb(0 0 0 / 25%); padding: 10px"
    >
      <b-navbar-brand class="text-light" to="/">HappyHouse</b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item to="/board"><span class="text-light">Q&A</span></b-nav-item>
          <b-nav-item to="/map"><span class="text-light">Map</span></b-nav-item>
          <b-nav-item to="/news"><span class="text-light">News</span></b-nav-item>
        </b-navbar-nav>

        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto">
          <template v-if="this.$store.state.jwtAuthToken">
            <b-nav-item to="/user/detail"> <span class="text-light">My Page</span></b-nav-item>
            <b-nav-item @click="logout"><span class="text-light">Sign out</span></b-nav-item>
          </template>
          <template v-else>
            <b-nav-item to="/user/signup"><span class="text-light">Sign up</span></b-nav-item>
            <b-nav-item to="/user/login"><span class="text-light">Sign in</span></b-nav-item>
          </template>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
export default {
  methods: {
    logout() {
      this.$store.dispatch("logOut").then(() => this.$router.push("/"));
    },
  },
};
</script>

<style></style>
