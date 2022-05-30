<template>
  <b-list-group-item href="#" class="flex-column align-items-start" @click="showMarker">
    <div class="justify-content-between">
      <div>
        <b class="mb-1">{{ apartInfo.apartName }}</b>
        <br />
        <small class="text-muted"
          >최근거래일: {{ apartInfo.TradeDate.year }}-{{ apartInfo.TradeDate.month }}-{{
            apartInfo.TradeDate.day
          }}</small
        >

        <p class="mb-1">{{ apartInfo.apartPrice }} 만원</p>
        <p class="mb-1">{{ apartInfo.apartDongName }} {{ apartInfo.apartDong }}번지</p>
        <p class="mb-1">{{ apartInfo.apartFloor }}층</p>
      </div>
      <svg
        style="float: right; display: inline-block; position: absolute; bottom: 10px; right: 10px"
        xmlns="http://www.w3.org/2000/svg"
        width="40"
        height="40"
        fill="currentColor"
        :class="heartClass"
        viewBox="0 0 16 16"
        @click.stop="like"
      >
        <path
          fill-rule="evenodd"
          d="M2.854 15.854A.5.5 0 0 1 2 15.5V14H.5a.5.5 0 0 1-.354-.854l1.5-1.5A.5.5 0 0 1 2 11.5h1.793l3.103-3.104a.5.5 0 1 1 .708.708L4.5 12.207V14a.5.5 0 0 1-.146.354l-1.5 1.5ZM16 3.5a.5.5 0 0 1-.854.354L14 2.707l-1.006 1.006c.236.248.44.531.6.845.562 1.096.585 2.517-.213 4.092-.793 1.563-2.395 3.288-5.105 5.08L8 13.912l-.276-.182A23.825 23.825 0 0 1 5.8 12.323L8.31 9.81a1.5 1.5 0 0 0-2.122-2.122L3.657 10.22a8.827 8.827 0 0 1-1.039-1.57c-.798-1.576-.775-2.997-.213-4.093C3.426 2.565 6.18 1.809 8 3.233c1.25-.98 2.944-.928 4.212-.152L13.292 2 12.147.854A.5.5 0 0 1 12.5 0h3a.5.5 0 0 1 .5.5v3Z"
        />
      </svg>
    </div>
  </b-list-group-item>
</template>

<script>
import axios from "axios";
import { mapGetters, mapMutations } from "vuex";

export default {
  data() {
    return {
      isOpened: false,
      infowindow: null,
      heartClass: "bi bi-arrow-through-heart-fill apart__heart",
      isLiked: this.checkIsLiked()[this.aptIndex],
      userId: "",
    };
  },
  props: { apartInfo: Object, map: Object, marker: Object, aptIndex: Number },
  mounted() {
    if (this.isLiked) {
      this.heartClass = "bi bi-arrow-through-heart-fill apart__heart__liked";
    } else {
      this.heartClass = "bi bi-arrow-through-heart-fill apart__heart";
    }
    var contentDetail = document.createElement("div");
    contentDetail.className = "overlayboxParent";
    contentDetail.innerHTML =
      '<div class="overlaybox" >' +
      '    <div class="boxtitle">아파트 상세 정보</div>' +
      '    <div class="first">' +
      `        <div class="apttitle text">${this.apartInfo.apartName}</div>` +
      "    </div>" +
      "    <ul>" +
      '        <li class="up">' +
      `           <span class="title">가격 : ${this.apartInfo.apartPrice} 만원</span>` +
      "        </li>" +
      "        <li>" +
      `            <span class="title">지역 : ${this.apartInfo.apartDongName} ${this.apartInfo.apartDong} 번지 ${this.apartInfo.apartFloor}층</span>` +
      "        </li>" +
      "        <li>" +
      `            <span class="title">거래날짜 : ${this.apartInfo.TradeDate.year}-${this.apartInfo.TradeDate.month}-${this.apartInfo.TradeDate.day}</span>` +
      "        </li>" +
      "    </ul>" +
      "</div>";
    contentDetail.addEventListener("click", () => {
      this.infowindow.close();
      this.isOpened = false;
    });
    // eslint-disable-next-line no-undef
    this.infowindow = new kakao.maps.InfoWindow({
      content: contentDetail, // 인포윈도우에 표시할 내용
      zIndex: 3,
    });
  },
  destroyed() {
    this.infowindow.close();
  },
  updated() {
    console.log("bb");
  },
  methods: {
    ...mapGetters("apartStore", ["checkIsLiked"]),
    ...mapMutations("apartStore", ["SET_ISLIKED2"]),
    // ...mapGetters("userStore", ["getUserInfo"]),
    showMarker() {
      this.map.setLevel(1);
      this.map.setCenter(
        // eslint-disable-next-line no-undef
        new kakao.maps.LatLng(this.apartInfo.apartXY.y, this.apartInfo.apartXY.x)
      );
      // eslint-disable-next-line no-undef
      if (!this.isOpened) {
        this.infowindow.open(this.map, this.marker.marker);
        this.isOpened = true;
      } else {
        this.infowindow.close();
        this.isOpened = false;
      }
    },
    async like() {
      if (!this.$store.state.jwtAuthToken) {
        this.$swal("해당 기능은 로그인이 필요합니다.");
        return;
      }
      if (!this.isLiked) {
        this.heartClass = "bi bi-arrow-through-heart-fill apart__heart__liked";
        this.isLiked = true;
        await this.$store.dispatch("getUserInfo", this.$store.state.jwtAuthToken).then((data) => {
          // console.log(data);
          this.userId = data;
        });
        await axios({
          method: "POST",
          url: `http://localhost:8080/interest/{id}`,
          params: {
            address: `${this.apartInfo.apartDongName} ${this.apartInfo.apartDong} 번지 ${this.apartInfo.apartFloor}층`,
            apartname: `${this.apartInfo.apartName}`,
            userid: `${this.userId}`,
            date: `${this.apartInfo.TradeDate.year}-${this.apartInfo.TradeDate.month}-${this.apartInfo.TradeDate.day}`,
            x: `${this.apartInfo.apartXY.x}`,
            y: `${this.apartInfo.apartXY.y}`,
            price: `${this.apartInfo.apartPrice}`,
          },
        });
        this.SET_ISLIKED2({ isliked: true, index: this.aptIndex });
      } else {
        await this.$store.dispatch("getUserInfo", this.$store.state.jwtAuthToken).then((data) => {
          // console.log(data);
          this.userId = data;
        });
        this.heartClass = "bi bi-arrow-through-heart-fill apart__heart";
        this.isLiked = false;
        this.SET_ISLIKED2(false, this.aptIndex);
        await axios({
          method: "GET",
          url: `http://localhost:8080/interest/${this.userId}`,
        }).then((data) => {
          for (let item of data.data) {
            if (item.apartname == this.apartInfo.apartName) {
              if (item.price == this.apartInfo.apartPrice) {
                axios({
                  method: "DELETE",
                  url: `http://localhost:8080/interest/{id}`,
                  params: {
                    interestno: `${item.interestno}`,
                  },
                });
                break;
              }
            }
          }
        });
      }
    },
  },
};
</script>

<style>
.item-list {
  display: flex;
  justify-content: center;
  padding-top: 3vh;
  padding-bottom: 3vh;
  border-bottom: 1px solid rgb(231, 231, 231);
}
.item-btn {
  color: white;
  background-color: black;
  border-radius: 30px;
  width: 90%;
  height: 100%;
  -webkit-box-shadow: 18px 17px 5px -8px rgba(0, 0, 0, 0.75);
  -moz-box-shadow: 18px 17px 5px -8px rgba(0, 0, 0, 0.75);
  box-shadow: 18px 17px 5px -8px rgba(0, 0, 0, 0.75);
}
.item-btn:hover {
  background-color: rgb(171, 163, 162);
}
.apart__price {
  font-size: 0.8vw;
}
.apart__name {
  font-size: 1.3vw;
}
.apart__address {
  font-family: "Courier New", Courier, monospace;
  font-size: 0.8vw;
}
.apart__heart {
  color: lightgray;
}
.apart__heart:hover {
  color: red;
}
.apart__heart__liked {
  color: red;
}
.apart__heart__liked:hover {
  color: lightgray;
}
.overlaybox {
  position: relative;
  width: 265px;
  height: 290px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/box_movie.png")
    no-repeat;
  padding: 15px 10px;
}
.overlaybox div,
ul {
  overflow: hidden;
  margin: 0;
  padding: 0;
}
.overlaybox li {
  list-style: none;
}
.overlaybox .boxtitle {
  color: #fff;
  font-size: 16px;
  font-weight: bold;
  /* background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png")
    no-repeat right 12px center; */
  margin-bottom: 8px;
}
.overlaybox .first {
  position: relative;
  width: 247px;
  height: 136px;
  background: url("@/assets/overlayApt.png") no-repeat;
  margin-bottom: 8px;
}
.first .text {
  color: #fff;
  font-weight: bold;
}
.first .apttitle {
  position: absolute;
  width: 100%;
  bottom: 0;
  background: rgba(0, 0, 0, 0.4);
  padding: 7px 15px;
  font-size: 14px;
}
.overlaybox ul {
  width: 247px;
}
.overlaybox li {
  position: relative;
  margin-bottom: 2px;
  background: #2b2d36;
  padding: 5px 10px;
  color: #aaabaf;
  line-height: 1;
}
.overlaybox li span {
  display: inline-block;
}
.overlaybox li .number {
  font-size: 16px;
  font-weight: bold;
}
.overlaybox li .title {
  font-size: 13px;
}
.overlaybox ul .arrow {
  position: absolute;
  margin-top: 8px;
  right: 25px;
  width: 5px;
  height: 3px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/updown.png") no-repeat;
}
.overlaybox li .up {
  background-position: 0 -40px;
}
.overlaybox li .down {
  background-position: 0 -60px;
}
.overlaybox li .count {
  position: absolute;
  margin-top: 5px;
  right: 15px;
  font-size: 10px;
}
.overlaybox li:hover {
  color: #fff;
  background: #d24545;
}
.overlaybox li:hover .up {
  background-position: 0 0px;
}
.overlaybox li:hover .down {
  background-position: 0 -20px;
}
</style>
