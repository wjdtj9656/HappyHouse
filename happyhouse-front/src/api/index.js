import axios from "axios";
import { API_APART_URL, API_BASE_URL, API_DONG_URL, API_KAKAOMAP_URL } from "@/config";

function apiInstance() {
  const instance = axios.create({
    baseURL: API_BASE_URL,
    Headers: {
      "content-type": "application/json",
    },
  });
  return instance;
}
function dongInstance() {
  const instance = axios.create({
    baseURL: API_DONG_URL,
    Headers: {
      "content-type": "application/json",
    },
  });
  return instance;
}
function apartInstance() {
  const instance = axios.create({
    baseURL: API_APART_URL,
    Headers: {
      "content-type": "application/json",
    },
  });
  return instance;
}
function kakaoMapInstance() {
  const instance = axios.create({
    baseURL: API_KAKAOMAP_URL,
    Headers: {
      // Authorization: `KakaoAK ${process.env.VUE_APP_KAKAO_MAP_REST_API_KEY}`,
      "content-type": "application/json",
    },
  });
  return instance;
}

export { apiInstance, dongInstance, apartInstance, kakaoMapInstance };
