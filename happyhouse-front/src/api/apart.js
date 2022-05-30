import { apartInstance } from "./index.js";

const apart = apartInstance();

function apartIden(params, success, fail) {
  return apart.get(``, { params: params }).then(success).catch(fail);
}
export { apartIden };
