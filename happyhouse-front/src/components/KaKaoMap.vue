<template>
  <div class="mapParent">
    <div id="map">
      <loading-spinner v-if="isLoading"></loading-spinner>
      <ul id="category">
        <li id="BK9" data-order="0" ref="BK" @click="bk">
          <span class="category_bg bank"></span>
          은행
        </li>
        <li id="MT1" data-order="1" ref="MT" @click="mt">
          <span class="category_bg mart"></span>
          마트
        </li>
        <li id="PM9" data-order="2" ref="PM" @click="pm">
          <span class="category_bg pharmacy"></span>
          약국
        </li>
        <li id="OL7" data-order="3" ref="OL" @click="ol">
          <span class="category_bg oil"></span>
          주유소
        </li>
        <li id="CE7" data-order="4" ref="CE" @click="ce">
          <span class="category_bg cafe"></span>
          카페
        </li>
        <li id="CS2" data-order="5" ref="CS" @click="cs">
          <span class="category_bg store"></span>
          편의점
        </li>
      </ul>
      <!-- <b-form-select class="category1" v-model="currCategory" :options="categorys"></b-form-select> -->
      <!-- <div v-if="isListShow">
        <div class="map__list">
          <div class="map__list__box">
            <ul class="map__ul">
              <div class="search__cancel" @click="showListBtnChange">
                <button class="search__cancel__btn">X</button>
              </div>
              <h1 class="search__title">search list</h1>
              <li v-for="(item, i) in aparts" :key="i">
                <apart-item :apartInfo="item" :map="map" :marker="marker[i]" v-if="isMapLoad" />
              </li>
            </ul>
          </div>
        </div>
      </div> -->
      <!-- <b-button v-b-toggle.sidebar-border>Toggle Sidebar</b-button> -->
      <b-sidebar
        width="18vw"
        id="sidebar-border"
        sidebar-class="border-right border-danger"
        v-model="isListShow"
      >
        <div class="map__list__box">
          <ul class="map__ul">
            <h1 class="search__title">search list</h1>
            <li v-for="(item, i) in aparts" :key="i">
              <apart-item
                :apartInfo="item"
                :map="map"
                :marker="marker[i]"
                :aptIndex="i"
                v-if="isListShow"
              />
            </li>
          </ul>
        </div>
      </b-sidebar>
      <!-- <div v-if="isLikeShow">
        <div class="map__list">
          <div class="map__list__box">
            <ul class="map__ul">
              <div class="search__cancel" @click="showLikeBtnChange">
                <button class="search__cancel__btn">X</button>
              </div>
              <h1 class="search__title">My like list</h1>
              <li v-for="(item, i) in myLikesApt" :key="i">
                <like-apart-item
                  :apartInfo="item"
                  :map="map"
                  :marker="marker[i]"
                  v-if="isMapLoad"
                />
              </li>
              <div v-if="!userId" class="myaptNotLogin">
                GO LOGIN~!<b-button variant="success" class="goLoginBtn" to="user/login"
                  >Button</b-button
                >
              </div>
            </ul>
          </div>
        </div>
      </div> -->
      <b-sidebar
        width="18vw"
        id="sidebar-border"
        sidebar-class="border-right border-danger"
        v-model="isLikeShow"
      >
        <div class="map__list__box">
          <ul class="map__ul">
            <h1 class="search__title">My like list</h1>
            <li v-for="(item, i) in myLikesApt" :key="i">
              <like-apart-item
                :apartInfo="item"
                :aparts="aparts"
                :map="map"
                :aptIndex="i"
                v-if="isLikeShow"
              />
            </li>
            <div v-if="!userId" class="myaptNotLogin">
              GO LOGIN~!<b-button variant="success" class="goLoginBtn" to="user/login"
                >Button</b-button
              >
            </div>
          </ul>
        </div>
      </b-sidebar>
      <div v-if="!isLikeShow && !isListShow">
        <!-- <button class="map__list__showbtn" @click="showListBtnChange">Show Search Apt</button>
        <button class="map__like__showbtn" @click="showLikeBtnChange">Show My Apt</button> -->
        <!-- <b-button v-b-toggle.collapse-2 class="m-1 map__list__showbtn" @click="showListBtnChange"
          >Show List</b-button
        > -->
        <b-button v-b-toggle.sidebar-border class="map__list__showbtn" @click="showListBtnChange"
          >Show List</b-button
        >
        <b-button v-b-toggle.sidebar-border class="map__like__showbtn" @click="showLikeBtnChange"
          >Show My List</b-button
        >
      </div>
      <b-button class="backBtn" to="/">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          width="30"
          height="30"
          fill="currentColor"
          class="bi bi-arrow-90deg-left"
          viewBox="0 0 16 16"
        >
          <path
            fill-rule="evenodd"
            d="M1.146 4.854a.5.5 0 0 1 0-.708l4-4a.5.5 0 1 1 .708.708L2.707 4H12.5A2.5 2.5 0 0 1 15 6.5v8a.5.5 0 0 1-1 0v-8A1.5 1.5 0 0 0 12.5 5H2.707l3.147 3.146a.5.5 0 1 1-.708.708l-4-4z"
          /></svg
      ></b-button>
      <!-- <button class="back__btn"></button> -->
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { mapActions, mapGetters, mapMutations } from "vuex";
import apartItem from "./apart/apartItem.vue";
import likeApartItem from "./apart/likeApartItem.vue";
import LoadingSpinner from "@/components/layout/LoadingSpinner.vue";
// import axios from "axios";
export default {
  components: { apartItem, likeApartItem, LoadingSpinner },
  name: "KakaoMap",
  data() {
    return {
      marker: [],
      markers: [],
      infowindow: null,
      coords: [],
      isMapLoad: false,
      isListShow: false,
      isLikeShow: false,
      aparts: [],
      myLikesApt: [],
      map: {},
      userId: "",
      currCategory: "",
      ps: {},
      isLoading: true,
      aptindex: -1,
    };
  },
  mounted() {
    this.isLoading = true;
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "`//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${process.env.VUE_APP_KAKAO_MAP_API_KEY}&libraries=services,clusterer,drawing`";
      document.head.appendChild(script);
    }
    //map 가져오기
    let pattern1 = /[0-9]/;
    if (pattern1.test(this.checkDong())) {
      //console.log("숫자포함!");
      this.searchName();
    } else {
      //console.log("숫자포함x");
      this.search();
    }
    this.categoryMap();
  },
  created() {
    this.$store
      .dispatch("getUserInfo", this.$store.state.jwtAuthToken)
      .then((data) => {
        this.userId = data;
      })
      .then(() => {
        this.getMyApts();
      });
  },
  methods: {
    ...mapGetters("dongStore", ["checkDong"]),
    ...mapGetters("apartStore", [
      "checkDongName",
      "checkDongNum",
      "checkApartName",
      "checkApartPrice",
      "checkTradeDate",
      "checkFloor",
      "checkIsLiked",
    ]),
    ...mapGetters("kakaoMapStore", ["checkX", "checkY"]),
    ...mapActions("dongStore", ["getDong"]),
    ...mapActions("apartStore", ["getIden"]),
    ...mapActions("kakaoMapStore", ["getAptInfo"]),
    ...mapMutations("dongStore", ["SET_DONG"]),
    ...mapMutations("apartStore", [
      "SET_DONG_NAME",
      "SET_DONG_NUM",
      "SET_APART_NAME",
      "SET_APART_PRICE",
      "SET_ISLIKED2",
    ]),
    ...mapMutations("kakaoMapStore", ["SET_MAPX", "SET_MAPY"]),

    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 5,
      };

      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(container, options);
    },
    search() {
      this.searchdong();
    },
    //지역 => 동코드로 바꾸기
    searchdong() {
      const a = this.getDong(this.checkDong());
      a.then(() => {
        this.searchName();
      });
    },
    //동코드 => 아파트 거래내역 받아오기
    async searchName() {
      const p1 = this.getIden({ dongCode: this.checkDong(), year: 202205 });
      const p2 = this.getIden({ dongCode: this.checkDong(), year: 202204 });
      const p3 = this.getIden({ dongCode: this.checkDong(), year: 202203 });
      const p4 = this.getIden({ dongCode: this.checkDong(), year: 202202 });
      const p5 = this.getIden({ dongCode: this.checkDong(), year: 202201 });
      const p6 = this.getIden({ dongCode: this.checkDong(), year: 202112 });
      const p7 = this.getIden({ dongCode: this.checkDong(), year: 202111 });
      const p8 = this.getIden({ dongCode: this.checkDong(), year: 202110 });
      const p9 = this.getIden({ dongCode: this.checkDong(), year: 202109 });
      const p10 = this.getIden({ dongCode: this.checkDong(), year: 202108 });
      const p11 = this.getIden({ dongCode: this.checkDong(), year: 202107 });
      const p12 = this.getIden({ dongCode: this.checkDong(), year: 202106 });
      const p13 = this.getIden({ dongCode: this.checkDong(), year: 202105 });
      let years = [p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13];
      await Promise.all(years);
      if (this.checkDongName().length === 0) {
        // alert("최근 매물이 없습니다.");
        this.$swal("최근 매물이 없습니다.");
        this.$router.push({ name: "home" });
      }
      await this.getNameNum();
      // });
    },
    //아파트 거래내역 => 좌표로 뽑아내기
    async getNameNum() {
      let length = this.checkDongName().length;
      //let index = -1;
      // let imageSrc = "apart.png";
      let imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
      let imageSize = new kakao.maps.Size(34, 39);
      let markerImg = new kakao.maps.MarkerImage(imageSrc, imageSize);
      for (let i = 0; i < length; i++) {
        const a = await this.getAptInfo(`${this.checkDongName()[i]} ${this.checkDongNum()[i]}`);
        if (a.length !== 0) {
          this.aptindex++;
          this.coords.push({
            latlng: new kakao.maps.LatLng(Number(a[0].y), Number(a[0].x)),
          });
        } else {
          this.aptindex++;
          continue;
        }
        for (let apt of this.myLikesApt) {
          let nowDate = this.checkTradeDate()[this.aptindex];
          if (apt.info.date == `${nowDate.year}-${nowDate.month}-${nowDate.day}`) {
            if (apt.info.isliked) {
              continue;
            }
            if (apt.info.apartname == this.checkApartName()[this.aptindex]) {
              this.SET_ISLIKED2({ isliked: true, index: this.aptindex });
            }
          }
        }
        this.aparts.push({
          apartName: this.checkApartName()[this.aptindex],
          apartDong: this.checkDongNum()[this.aptindex],
          apartDongName: this.checkDongName()[this.aptindex],
          apartPrice: this.checkApartPrice()[this.aptindex],
          apartXY: { x: a[0].x, y: a[0].y },
          TradeDate: this.checkTradeDate()[this.aptindex],
          apartFloor: this.checkFloor()[this.aptindex],
          apartliked: this.checkIsLiked()[this.aptindex],
        });
        this.marker.push({
          marker: new kakao.maps.Marker({
            map: this.map,
            position: new kakao.maps.LatLng(a[0].y, a[0].x),
            image: markerImg,
            zIndex: 1,
          }),
        });
        //마커위 컨텐츠
        var content =
          '<div class="customoverlay">' +
          `    <span class="title">${this.checkApartName()[this.aptindex]}</span>` +
          "</div>";
        new kakao.maps.CustomOverlay({
          map: this.map,
          position: new kakao.maps.LatLng(a[0].y, a[0].x),
          content: content,
          yAnchor: 1,
          zIndex: -2,
        });
      }
      //맵이동
      this.map.setCenter(new kakao.maps.LatLng(this.coords[0].latlng.Ma, this.coords[0].latlng.La));
      this.isMapLoad = true;
      this.isLoading = false;
    },
    showListBtnChange() {
      this.isListShow = !this.isListShow;
    },
    showLikeBtnChange() {
      this.isLikeShow = !this.isLikeShow;
      if (this.userId) {
        this.getMyApts();
      }
      if (!this.isListShow) {
        this.myLikesApt = [];
      }
    },
    getMyApts() {
      axios({
        method: "GET",
        url: `http://localhost:8080/interest/${this.userId}`,
        params: {
          userid: `${this.userId}`,
        },
      }).then((data) => {
        for (let info of data.data) {
          this.myLikesApt.push({ info });
        }
      });
    },
    categoryMap() {
      this.markers = [];
      this.ps = new kakao.maps.services.Places(this.map);
      kakao.maps.event.addListener(this.map, "idle", this.searchPlaces);
      this.addCategoryClickEvent();
    },
    addCategoryClickEvent() {
      var category = document.getElementById("category"),
        children = category.children;

      for (var i = 0; i < children.length; i++) {
        children[i].onclick = this.onClickCategory;
      }
    },
    onClickCategory() {
      // let id = this.id;
      let id = this.currCategory;
      if (id == "BK9") {
        if (this.$refs.BK.classList.length == 0) {
          this.removeMarker(this.markers);
          this.$refs.BK.classList.add("on");
          this.$refs.MT.classList.value = "";
          this.$refs.PM.classList.value = "";
          this.$refs.OL.classList.value = "";
          this.$refs.CE.classList.value = "";
          this.$refs.CS.classList.value = "";
          this.currCategory = "BK9";
        } else {
          this.$refs.BK.classList.value = "";
          this.removeMarker(this.markers);
          return;
        }
      } else if (id == "MT1") {
        if (this.$refs.MT.classList.length == 0) {
          this.removeMarker(this.markers);
          this.$refs.MT.classList.add("on");
          this.$refs.BK.classList.value = "";
          this.$refs.PM.classList.value = "";
          this.$refs.OL.classList.value = "";
          this.$refs.CE.classList.value = "";
          this.$refs.CS.classList.value = "";
          this.currCategory = "MT1";
        } else {
          this.$refs.MT.classList.value = "";
          this.removeMarker(this.markers);
          return;
        }
      } else if (id == "PM9") {
        if (this.$refs.PM.classList.length == 0) {
          this.removeMarker(this.markers);
          this.$refs.PM.classList.add("on");
          this.$refs.BK.classList.value = "";
          this.$refs.MT.classList.value = "";
          this.$refs.OL.classList.value = "";
          this.$refs.CE.classList.value = "";
          this.$refs.CS.classList.value = "";
          this.currCategory = "PM9";
        } else {
          this.$refs.PM.classList.value = "";
          this.removeMarker(this.markers);
          return;
        }
      } else if (id == "OL7") {
        if (this.$refs.OL.classList.length == 0) {
          this.removeMarker(this.markers);
          this.$refs.OL.classList.add("on");
          this.$refs.BK.classList.value = "";
          this.$refs.MT.classList.value = "";
          this.$refs.PM.classList.value = "";
          this.$refs.CE.classList.value = "";
          this.$refs.CS.classList.value = "";
          this.currCategory = "OL7";
        } else {
          this.$refs.OL.classList.value = "";
          this.removeMarker(this.markers);
          return;
        }
      } else if (id == "CE7") {
        if (this.$refs.CE.classList.length == 0) {
          this.removeMarker(this.markers);
          this.$refs.CE.classList.add("on");
          this.$refs.BK.classList.value = "";
          this.$refs.MT.classList.value = "";
          this.$refs.PM.classList.value = "";
          this.$refs.OL.classList.value = "";
          this.$refs.CS.classList.value = "";
          this.currCategory = "CE7";
        } else {
          this.$refs.CE.classList.value = "";
          this.removeMarker(this.markers);
          return;
        }
      } else if (id == "CS2") {
        if (this.$refs.CS.classList.length == 0) {
          this.removeMarker(this.markers);
          this.$refs.CS.classList.add("on");
          this.$refs.BK.classList.value = "";
          this.$refs.MT.classList.value = "";
          this.$refs.PM.classList.value = "";
          this.$refs.OL.classList.value = "";
          this.$refs.CE.classList.value = "";
          this.currCategory = "CS2";
        } else {
          this.$refs.CS.classList.value = "";
          this.removeMarker(this.markers);
          return;
        }
      }
      this.searchPlaces();
    },
    searchPlaces() {
      // console.log("hahaha!");
      if (!this.currCategory) {
        return;
      }
      this.removeMarker(this.markers);
      this.ps.categorySearch(this.currCategory, this.placesSearchCB, { useMapBounds: true });
    },
    placesSearchCB(data, status) {
      if (status === kakao.maps.services.Status.OK) {
        // 정상적으로 검색이 완료됐으면 지도에 마커를 표출합니다
        this.displayPlaces(data);
      } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
        // 검색결과가 없는경우 해야할 처리가 있다면 이곳에 작성해 주세요
      } else if (status === kakao.maps.services.Status.ERROR) {
        // 에러로 인해 검색결과가 나오지 않은 경우 해야할 처리가 있다면 이곳에 작성해 주세요
      }
    },
    displayPlaces(places) {
      // 몇번째 카테고리가 선택되어 있는지 얻어옵니다
      // 이 순서는 스프라이트 이미지에서의 위치를 계산하는데 사용됩니다
      var order = document.getElementById(this.currCategory).getAttribute("data-order");

      for (var i = 0; i < places.length; i++) {
        // 마커를 생성하고 지도에 표시합니다
        this.addMarker(new kakao.maps.LatLng(places[i].y, places[i].x), order);
      }
    },
    addMarker(position, order) {
      let imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png", // 마커 이미지 url, 스프라이트 이미지를 씁니다
        imageSize = new kakao.maps.Size(27, 28), // 마커 이미지의 크기
        imgOptions = {
          spriteSize: new kakao.maps.Size(72, 208), // 스프라이트 이미지의 크기
          spriteOrigin: new kakao.maps.Point(46, order * 36), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
          offset: new kakao.maps.Point(11, 28), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
        },
        markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
        marker = new kakao.maps.Marker({
          position: position, // 마커의 위치
          image: markerImage,
        });
      marker.setMap(this.map); // 지도 위에 마커를 표출합니다
      this.markers.push(marker); // 배열에 생성된 마커를 추가합니다

      return marker;
    },
    removeMarker(markers) {
      for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(null);
      }
      markers = [];
    },
    bk() {
      this.currCategory = "BK9";
    },
    mt() {
      this.currCategory = "MT1";
    },
    pm() {
      this.currCategory = "PM9";
    },
    ol() {
      this.currCategory = "OL7";
    },
    ce() {
      this.currCategory = "CE7";
    },
    cs() {
      this.currCategory = "CS2";
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
#map {
  width: 100%;
  height: 100%;
}
.button-group {
  margin: 10px 0px;
}
.mapParent {
  height: 90vh;
  width: 100vw;
}
button {
  margin: 0 3px;
}
.search__title {
  display: flex;
  justify-content: center;
  font-size: 2vw;
  border-bottom: 1px solid rgb(231, 231, 231);
  color: rgb(199, 187, 187);
  padding: 30px 0px 70px;
}
.search__cancel {
  text-align: right;
  font-size: 30px;
}
.search__cancel__btn {
  position: relative;
  /* top: 140px;
  left: 650px; */
  border-radius: 10%;
  border: none;
  border-color: rgb(199, 187, 187);
  margin: 0;
  width: 50px;
  height: 50px;
}
.orderBtn {
  display: flex;
  justify-content: center;
}
/* map list */
.map__ul {
  list-style: none;
  padding-left: 0px;
}
.map__list__box {
  width: 100%;
}
.map__list {
  position: absolute;
  display: inline-flex;
  left: 3vw;
  top: 5vh;
  width: 20vw;
  height: 80vh;
  background-color: white;
  border-radius: 20px;
  overflow-y: scroll;
  scrollbar-width: none;
  z-index: 99;
}
.map__list::-webkit-scrollbar {
  display: none;
}
.map__list__showbtn {
  position: absolute;
  left: 2vw;
  top: 10px;
  width: 15vw;
  min-height: 40px;
  height: 5vh;
  border: none;
  border-radius: 30px;
  font-size: 1vw;
  z-index: 50;
}
.map__like__showbtn {
  position: absolute;
  left: 2vw;
  top: 60px;
  width: 15vw;
  min-height: 40px;
  height: 5vh;
  border: none;
  border-radius: 30px;
  font-size: 1vw;
  z-index: 50;
}
.myaptNotLogin {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  padding-top: 30px;
  font-size: 3vw;
}
.goLoginBtn {
  margin-top: 10%;
  width: 60%;
  height: 30%;
  font-size: 1vw;
}
.backBtn {
  position: absolute;
  right: 1vw;
  bottom: 2vh;
  width: 100px;
  height: 100px;
  border: none;
  border-radius: 100%;
  padding-top: 35px;
  z-index: 99;
}
/* custom overlay */
.customoverlay {
  position: relative;
  bottom: 50px;
  border-radius: 6px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  float: left;
}
.customoverlay .title {
  display: block;
  text-align: center;
  background: #fff;
  padding: 7px 10px;
  font-size: 10px;
  font-weight: bold;
}
.customoverlay:after {
  content: "";
  position: absolute;
  border: none;
  margin-left: -12px;
  left: 50%;
  bottom: -12px;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
#category {
  position: absolute;
  top: 10px;
  right: 1vw;
  border-radius: 5px;
  padding: 0 0;
  border: 1px solid #909090;
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.4);
  background: #fff;
  overflow: hidden;
  z-index: 2;
}
.category1 {
  position: absolute;
  top: 50px;
  right: 10px;
  width: 10%;
  border-radius: 5px;
  border: 1px solid #909090;
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.4);
  background: #fff;
  overflow: hidden;
  z-index: 2;
}

#category li {
  display: flex;
  flex-direction: column;
  justify-content: center;
  float: left;
  list-style: none;
  width: 50px;
  border-right: 1px solid #acacac;
  padding: 6px 0;
  text-align: center;
  cursor: pointer;
}

#category li.on {
  background: #eee;
}

#category li:hover {
  background: #ffe6e6;
  border-left: 1px solid #acacac;
  margin-left: -1px;
}

#category li:last-child {
  margin-right: 0;
  border-right: 0;
}

#category li span {
  display: flex;
  justify-content: center;
  /* margin: 0 auto 3px; */
  margin: 0 10px 3px 13px;
  width: 27px;
  height: 28px;
}

#category li .category_bg {
  background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png)
    no-repeat;
}

#category li .bank {
  background-position: -10px 0;
}

#category li .mart {
  background-position: -10px -36px;
}

#category li .pharmacy {
  background-position: -10px -72px;
}

#category li .oil {
  background-position: -10px -108px;
}

#category li .cafe {
  background-position: -10px -144px;
}

#category li .store {
  background-position: -10px -180px;
}

#category li.on .category_bg {
  background-position-x: -46px;
}
</style>
