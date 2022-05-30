import { dongNo } from "@/api/dong.js";

const dongStore = {
  namespaced: true,
  state: {
    dong: 0,
  },
  getters: {
    checkDong(state) {
      return state.dong;
    },
  },

  mutations: {
    SET_DONG: (state, dong) => {
      state.dong = dong;
    },
  },
  actions: {
    getDong({ commit }, locationName) {
      if (!locationName) {
        locationName = "서울특별시 성북구 종암동";
      }
      const SERVICE_KEY = process.env.VUE_APP_LOCATION_API_KEY;
      const params = {
        pageNo: 1,
        type: "json",
        locatadd_nm: locationName,
        serviceKey: decodeURIComponent(SERVICE_KEY),
      };
      let temp;
      return dongNo(
        params,
        (response) => {
          temp = response.data.StanReginCd[1].row[0].region_cd;
        },
        (error) => {
          console.log(error);
        }
      ).then(() => {
        commit("SET_DONG", temp);
        return temp;
      });
    },
  },
};
export default dongStore;
