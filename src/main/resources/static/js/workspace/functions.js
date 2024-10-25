import {
    DISPLAY_SHOW,
    DISPLAY_HIDE
} from "./vars.js";

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
    function toPixels(px){
        return String(px).concat("px");
    }
    $(element).css("top", toPixels(y)).css("left", toPixels(x));
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