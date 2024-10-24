import {
    DISPLAY_SHOW,
    DISPLAY_HIDE
} from "./vars.js";

function toPixels(pixels) {
    return String(pixels).concat('px');
}

function setDisplay(element, display) {
    $(element).css("display", display);
}

export function showEl(element) {
    setDisplay(element, DISPLAY_SHOW);
}

export function hideEl(element) {
    setDisplay(element, DISPLAY_HIDE);
}

export function setPosition(element, x, y) {
    element.style.top = toPixels(y);
    element.style.left = toPixels(x);
}

export function clamp(value, min, max) {
    return Math.min(Math.max(value, min), max);
}

export function getRect(element) {
    const rect = element.getBoundingClientRect();
    return {
        x: rect.left + window.scrollX,
        y: rect.top + window.scrollY,
        width: rect.width,
        height: rect.height
    }
}

export function showErrors(form, errors){
    function getInputTR(tbody, inputName){
        let trs = $(tbody).find("tr");
        for (let i= 0; i < trs.length; i++){
            if ($(trs[i]).find("input").attr("name") === inputName){
                return trs[i];
            }
        }
        return null;
    }

    let tbody = form.getElementsByTagName("tbody").item(0);
    $.each(errors, function (key, value){
        let error = "<tr><td colspan='2' class='error-on-1'>" + value + "</td></tr>";
        $(error).insertAfter($(getInputTR(tbody, key)));
    });
}

export function clearErrors(form){
    let tbody = form.getElementsByTagName("tbody").item(0);
    let trs = $(tbody).find("tr");
    for (let i= 0; i < trs.length; i++){
        if ($(trs[i]).find(".error-on-1").length !== 0){
            trs[i].remove();
        }
    }
}