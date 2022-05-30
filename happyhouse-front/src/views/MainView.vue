<template>
  <div id="back">
    <div class="h-100">
      <div class="input-group search-size searchbar">
        <input
          type="text"
          class="input-box form-control"
          placeholder="관심 지역을 입력하세요."
          aria-label="Recipient's username"
          aria-describedby="basic-addon2"
          @keydown.enter="goSearch"
          v-model="location"
        />
        <b-button variant="dark" class="text-light btn-size" @click="goSearch">▶</b-button>
      </div>
      <div class="selectSearch select-size">
        <b-form-select
          class="sido"
          v-model="selected1"
          :options="optionsSido"
          @change="changeSsg"
        ></b-form-select>
        <b-form-select
          class="ssg"
          v-model="selected2"
          :options="optionsSsg"
          @change="changeDong"
        ></b-form-select>
        <b-form-select
          class="umd"
          v-model="selected3"
          :options="optionsumd"
          @change="goSelectSearch"
        ></b-form-select>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters, mapMutations } from "vuex";
import { hangjungdong } from "../config/hangjungdong";
export default {
  data() {
    return {
      location: null,
      selected1: null,
      selected2: null,
      selected3: null,
      optionsSido: hangjungdong.sido,
      optionsSsg: [{ value: null, text: "시/구/군을 선택하세요" }],
      optionsumd: [{ value: null, text: "읍/면/동을 선택하세요" }],
    };
  },
  mounted() {
    this.clearHouseData();
  },
  methods: {
    ...mapGetters("dongStore", ["checkDong"]),
    ...mapActions("dongStore", ["getDong"]),
    ...mapMutations("dongStore", ["SET_DONG"]),
    ...mapMutations("apartStore", ["CLEAR_APART"]),
    ...mapMutations("kakaoMapStore", ["CLEAR_MAP"]),
    goSearch() {
      // this.location = this.location.replace("서울", "서울특별시");
      // this.location = this.location.replace("서울시", "서울특별시");
      console.log(this.location);
      this.SET_DONG(this.location);
      this.$router.push({ name: "map" });
    },
    goSelectSearch() {
      // this.SET_DONG(this.location);
      console.log(`${this.selected1}${this.selected2}${this.selected3}`);
      let dongCode = `${this.selected1}${this.selected2}${this.selected3}`;
      this.SET_DONG(dongCode);
      this.clearSelectData();
      this.$router.push({ name: "map" });
    },
    clearHouseData() {
      this.SET_DONG(null);
      this.CLEAR_APART();
      this.CLEAR_MAP();
    },
    clearSelectData() {
      this.selected1 = null;
      this.selected2 = null;
      this.selected3 = null;
      this.optionsSido = null;
      this.optionsSsg = null;
      this.optionsumd = null;
    },
    changeSsg() {
      this.optionsSsg = [{ value: null, text: "시/구/군을 선택하세요" }];
      for (let ssg of hangjungdong.sigugun) {
        if (ssg.sido === this.selected1) {
          this.optionsSsg.push(ssg);
        }
      }
    },
    changeDong() {
      this.optionsumd = [{ value: null, text: "읍/면/동을 선택하세요" }];
      for (let umd of hangjungdong.dong) {
        if (umd.sigugun === this.selected2 && umd.sido === this.selected1) {
          this.optionsumd.push(umd);
        }
      }
    },
  },
};
</script>

<style scoped>
.container {
  height: 100%;
}
#back {
  opacity: 0.5;
  background-image: url("@/assets/house.png");
  background-size: cover;
  height: 100vh;
}
.searchbar {
  width: 30%;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}
.selectSearch {
  display: flex;
  position: absolute;
  top: 60%;
  left: 50%;
  transform: translate(-50%, -50%);
}
.sido {
  /* width: 10%;
  position: absolute;
  top: 60%;
  left: 35%;
  transform: translate(-50%, -50%); */
}
.ssg {
  /* width: 10%;
  position: absolute;
  top: 60%;
  left: 50%;
  transform: translate(-50%, -50%); */
}
.umd {
  /* width: 10%;
  position: absolute;
  top: 60%;
  left: 65%;
  transform: translate(-50%, -50%); */
}
@media (min-width: 576px) {
  .search-size {
    width: 300px;
    height: 100px;
  }
  .input-box {
    height: 100px;
  }
  .btn-size {
    width: 70px;
  }
  .select-size {
    width: 300px;
  }
}
@media (min-width: 768px) {
  .search-size {
    width: 300px;
    height: 100px;
  }
  .input-box {
    height: 100px;
  }
  .btn-size {
    width: 70px;
  }
  .select-size {
    width: 300px;
  }
}
@media (min-width: 992px) {
  .search-size {
    width: 600px;
    height: 70px;
  }
  .input-box {
    font-size: 25px;
    height: 70px;
  }
  .btn-size {
    width: 60px;
  }
  .select-size {
    width: 600px;
  }
}
@media (min-width: 2000px) {
  .search-size {
    width: 1100px;
    height: 200px;
  }
  .input-box {
    font-size: 45px;
    height: 200px;
  }
  .btn-size {
    width: 200px;
  }
  .select-size {
    width: 1100px;
  }
}
.sido {
  margin: 5px;
  margin-left: 0px;
}
.ssg {
  margin: 5px;
}
.umd {
  margin: 5px;
  margin-right: 0px;
}
</style>
