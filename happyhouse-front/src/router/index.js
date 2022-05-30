import Vue from "vue";
import Vuex from "vuex";
import VueRouter from "vue-router";
import store from "@/store/modules/memberStore";

import MainView from "@/views/MainView.vue";
import VueSweetalert2 from "vue-sweetalert2";
import "sweetalert2/dist/sweetalert2.min.css";
Vue.use(VueSweetalert2);
Vue.use(Vuex);
Vue.use(VueRouter);

const requireAuth = () => (from, to, next) => {
  console.log("access-token: ", sessionStorage.getItem("access-token"));
  if (!store.state.jwtAuthToken) {
    Vue.swal("로그인이 필요합니다.");
    return next("/user/login");
  } else return next();
};

const routes = [
  {
    path: "/",
    name: "home",
    component: MainView,
  },
  {
    path: "/board",
    name: "board",
    redirect: "/board/list",
    component: () => import("@/views/BoardView"),
    children: [
      {
        path: "list",
        name: "list",
        component: () => import("@/components/board/BoardList"),
      },
      {
        path: "write",
        name: "write",
        beforeEnter: requireAuth(),
        component: () => import("@/components/board/BoardWrite"),
      },
      {
        path: "detail/:noticeno",
        name: "detail",
        beforeEnter: requireAuth(),
        component: () => import("@/components/board/BoardDetail"),
      },
      {
        path: "modify/:noticeno",
        name: "modify",
        beforeEnter: requireAuth(),
        component: () => import("@/components/board/BoardModify"),
      },
    ],
  },
  {
    path: "/map",
    name: "map",
    component: () => import("@/views/MapView"),
  },
  {
    path: "/news",
    name: "news",
    component: () => import("@/views/NewsView"),
  },
  {
    path: "/user",
    name: "user",
    component: () => import("@/views/MemberView"),
    children: [
      {
        path: "login",
        name: "login",
        component: () => import("@/components/member/MemberLogin"),
      },
      {
        path: "signup",
        name: "signup",
        component: () => import("@/components/member/MemberSignup"),
      },
      {
        path: "detail",
        name: "detail",
        beforeEnter: requireAuth(),
        component: () => import("@/components/member/MemberDetail"),
      },
      {
        path: "modify",
        name: "modify",
        beforeEnter: requireAuth(),
        component: () => import("@/components/member/MemberModify"),
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
