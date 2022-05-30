import { dongInstance } from "./index.js";

//const api = apiInstance();
const dong = dongInstance();

function dongNo(params, success, fail) {
  return dong.get(``, { params: params }).then(success).catch(fail);
}

export { dongNo };
