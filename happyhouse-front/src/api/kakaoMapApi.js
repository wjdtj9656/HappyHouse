import { kakaoMapInstance } from "./index.js";

const kakaoMap = kakaoMapInstance();
function getXY(params, success, fail) {
  //kakaoMap.get(``, { params: params }).then(success).catch(fail);
  return kakaoMap({
    url: ``,
    method: "GET",
    params: params,
    headers: { Authorization: `KakaoAK ${process.env.VUE_APP_KAKAO_MAP_REST_API_KEY}` },
  })
    .then(success)
    .catch(fail);
}
export { getXY };
