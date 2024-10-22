import {
    SHOW_MENU_OFFSET
} from "./vars.js";

import {
    setPosition,
    showEl,
    hideEl,
    clamp,
    getRect
} from "./functions.js";

import {
    workspace
} from "./elements.js";



workspace.this.addEventListener("contextmenu", function (event){
    event.preventDefault();
    showEl(workspace.menu.this);
    let rect = getRect(workspace.this);
    setPosition(
        workspace.menu.this,
        clamp(event.x - SHOW_MENU_OFFSET, rect.x, rect.x + rect.width - getRect(workspace.menu.this).width),
        clamp(event.y - SHOW_MENU_OFFSET * 1.5, rect.y, rect.y + rect.height - getRect(workspace.menu.this).height - 15)
    );
});

$(workspace.menu.this).mouseleave(function (event){
    hideEl(workspace.menu.this);
});

$.each(workspace.menu.options, function (key, value){
    $(value.showButton).click(function (event){
        value.dialog.showModal();
    });
});