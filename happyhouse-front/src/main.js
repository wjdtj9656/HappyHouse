import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "@/store/modules/memberStore";
import VueSweetalert2 from "vue-sweetalert2";
import "sweetalert2/dist/sweetalert2.min.css";
import { BootstrapVue, BootstrapVueIcons } from "bootstrap-vue";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";

const options = {
  confirmButtonColor: "#212422",
};
Vue.use(BootstrapVue);
Vue.use(BootstrapVueIcons);
Vue.use(VueSweetalert2, options);
Vue.config.productionTip = false;
new Vue({
  router,
  store,
  VueSweetalert2,
  render: (h) => h(App),
}).$mount("#app");
