import Vue from "vue";
import Vuex from "vuex";
import jwt_decode from "jwt-decode";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

import dongStore from "@/store/modules/dongStore.js";
import apartStore from "@/store/modules/apartStore.js";
import kakaoMapStore from "@/store/modules/kakaoMapStore.js";

import http from "@/api/http.js";
const store = new Vuex.Store({
  plugins: [
    createPersistedState({
      storage: window.sessionStorage,
    }),
  ],
  modules: {
    dongStore,
    apartStore,
    kakaoMapStore,
  },
  state: {
    jwtAuthToken: null,
    userInfo: null,
    isAdmin: false,
  },
  getters: {
    userInfo: (state) => state.userInfo,
  },
  mutations: {
    LOGIN(state, jwtAuthToken) {
      state.jwtAuthToken = jwtAuthToken;
    },
    LOGOUT(state) {
      state.jwtAuthToken = null;
      state.userInfo = null;
    },
    SET_USER_INFO(state, userInfo) {
      state.userInfo = userInfo;
    },
    SET_ADMIN(state) {
      state.isAdmin = !state.isAdmin;
    },
  },
  actions: {
    logIn({ commit }, { id, password }) {
      const member = {
        id: id,
        password: password,
      };
      return http.post("/member/login", member).then(({ data }) => {
        commit("LOGIN", data.jwtAuthToken);
        sessionStorage.setItem("access-token", data.jwtAuthToken);
      });
    },
    logOut({ commit }) {
      commit("LOGOUT");
      sessionStorage.removeItem("access-token");
    },
    async getUserInfo({ commit }, jwtAuthToken) {
      let decode_token = jwt_decode(jwtAuthToken);
      let id = decode_token.user;
      http.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
      await http.get(`/member/${id}`).then(({ data }) => {
        commit("SET_USER_INFO", data);
        if (data.userclass == 0) {
          commit("SET_ADMIN");
        }
      });
      return id;
    },
    setAdmin({ commit }) {
      commit("SET_ADMIN");
    },
  },
});
export default store;
