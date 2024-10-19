import {
    DISPLAY_SHOW,
    DISPLAY_HIDE
} from "./vars.js";

function to_pixels(pixels) {
    return String(pixels).concat('px');
}

function set_display(element, display) {
    element.style.display = display;
}

export function show_el(element) {
    set_display(element, DISPLAY_SHOW);
}

export function hide_el(element) {
    set_display(element, DISPLAY_HIDE);
}

export function set_position(element, x, y) {
    element.style.top = to_pixels(y);
    element.style.left = to_pixels(x);
}

export function add_listener_dialog_show_button(dialog, button) {
    button.addEventListener("click", function (event){
        dialog.showModal();
    });
}