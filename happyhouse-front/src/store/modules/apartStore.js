import { apartIden } from "@/api/apart.js";
const apart = {
  namespaced: true,
  state: {
    dongName: [],
    dongNum: [],
    apartName: [],
    apartPrice: [],
    tradeDate: [],
    floor: [],
    isliked: [],
  },
  getters: {
    checkDongName(state) {
      return state.dongName;
    },
    checkDongNum(state) {
      return state.dongNum;
    },
    checkApartName(state) {
      return state.apartName;
    },
    checkApartPrice(state) {
      return state.apartPrice;
    },
    checkTradeDate(state) {
      return state.tradeDate;
    },
    checkFloor(state) {
      return state.floor;
    },
    checkIsLiked(state) {
      return state.isliked;
    },
  },
  mutations: {
    SET_DONG_NAME(state, dongName) {
      state.dongName.push(dongName);
    },
    SET_DONG_NUM(state, dongNum) {
      state.dongNum.push(dongNum);
    },
    SET_APART_NAME(state, apartName) {
      state.apartName.push(apartName);
    },
    SET_APART_PRICE(state, apartPrice) {
      state.apartPrice.push(apartPrice);
    },
    SET_TRADE_DATE(state, date) {
      state.tradeDate.push(date);
    },
    SET_FLOOR(state, floor) {
      state.floor.push(floor);
    },
    SET_ISLIKED1(state, isliked) {
      state.isliked.push(isliked);
    },
    SET_ISLIKED2(state, { isliked, index }) {
      // state.isliked.push(isliked);
      // state.isliked[index] = isliked;
      state.isliked.splice(index, 1, isliked);
    },
    CLEAR_APART(state) {
      state.dongName = [];
      state.dongNum = [];
      state.apartName = [];
      state.apartPrice = [];
      state.tradeDate = [];
      state.floor = [];
      state.isliked = [];
    },
  },
  actions: {
    getIden({ commit }, { dongCode, year }) {
      const SERVICE_KEY = process.env.VUE_APP_APART_API_KEY;
      const params = {
        pageNo: 1,
        numOfRows: 50,
        LAWD_CD: dongCode.slice(0, 5),
        DEAL_YMD: year,
        serviceKey: decodeURIComponent(SERVICE_KEY),
      };
      let temp;
      return apartIden(
        params,
        (response) => {
          temp = response.data.response.body.items.item;
        },
        (err) => {
          console.log(err);
        }
      ).then(() => {
        temp.map((data) => {
          let price = data.거래금액.replace(",", "").trim();
          if (dongCode.slice(5, 10) === "00000") {
            commit("SET_DONG_NAME", data.법정동);
            commit("SET_DONG_NUM", data.지번);
            commit("SET_APART_NAME", data.아파트);
            commit("SET_APART_PRICE", price);
            commit("SET_TRADE_DATE", { year: data.년, month: data.월, day: data.일 });
            commit("SET_FLOOR", data.층);
            commit("SET_ISLIKED1", false);
          } else if (data.법정동읍면동코드 == dongCode.slice(5, 10)) {
            commit("SET_DONG_NAME", data.법정동);
            commit("SET_DONG_NUM", data.지번);
            commit("SET_APART_NAME", data.아파트);
            commit("SET_APART_PRICE", price);
            commit("SET_TRADE_DATE", { year: data.년, month: data.월, day: data.일 });
            commit("SET_FLOOR", data.층);
            commit("SET_ISLIKED1", false);
          }
        });
      });
    },
  },
};
export default apart;
