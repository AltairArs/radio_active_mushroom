import {
    DISPLAY_SHOW,
    DISPLAY_HIDE
} from "./vars.js";

import {
    workspace
} from "./elements.js";

function toPixels(pixels) {
    return String(pixels).concat('px');
}

function setDisplay(element, display) {
    element.style.display = display;
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

export function addListenerDialogShowButton(dialog, button) {
    button.addEventListener("click", function (event){
        dialog.showModal();
    });
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