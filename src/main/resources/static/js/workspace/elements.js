import * as vars from "./vars.js";

export let getAllURL = document.getElementById(vars.WORKSPACE_GET_ALL_URL_ID).value;

export function getCSRF() {
    try {
        return {
            "token": document.getElementsByName(vars.CSRF_TOKEN_ID).item(0).value,
            "header": document.getElementsByName(vars.CSRF_HEADER_NAME_ID).item(0).value,
            "has": true
        };
    } catch (e) {
        return {
            "has": false
        };
    }
}
