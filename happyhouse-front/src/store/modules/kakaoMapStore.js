import { getXY } from "@/api/kakaoMapApi";

const kakaoMapStore = {
  namespaced: true,
  state: {
    x: [],
    y: [],
  },
  getters: {
    checkX(state) {
      return state.x;
    },
    checkY(state) {
      return state.y;
    },
  },
  mutations: {
    SET_MAPX(state, x) {
      state.x.push(x);
    },
    SET_MAPY(state, y) {
      state.y.push(y);
    },
    CLEAR_MAP(state) {
      state.x = [];
      state.y = [];
    },
  },
  actions: {
    getAptInfo({ commit }, apartInfo) {
      const params = {
        analyze_type: "similar",
        query: apartInfo,
        page: 1,
        size: 1,
      };
      let temp;
      return getXY(
        params,
        (response) => {
          temp = response.data.documents;
        },
        (error) => console.log(error)
      ).then(() => {
        temp.map((data) => {
          commit("SET_MAPX", data.address.x);
          commit("SET_MAPY", data.address.y);
        });
        return temp;
      });
    },
  },
};
export default kakaoMapStore;
