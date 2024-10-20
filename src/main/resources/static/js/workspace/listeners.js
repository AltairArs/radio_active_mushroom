import * as els from "./elements.js";

import {
    SHOW_MENU_OFFSET
} from "./vars.js";

import {
    setPosition,
    showEl,
    hideEl,
    addListenerDialogShowButton,
    clamp,
    getRect
} from "./functions.js";
import {workspace} from "./elements.js";

els.workspace.addEventListener("contextmenu", function(event){
    event.preventDefault();
    showEl(els.workspaceMenu);
    setPosition(
        els.workspaceMenu,
        clamp(event.x - SHOW_MENU_OFFSET, getRect(workspace).x, getRect(workspace).x + getRect(workspace).width - getRect(els.workspaceMenu).width),
        clamp(event.y - SHOW_MENU_OFFSET * 1.5, getRect(workspace).y, getRect(workspace).y + getRect(workspace).height - getRect(els.workspaceMenu).height - 15)
    )
});

els.workspaceMenu.addEventListener("mouseleave", function (event){
    hideEl(els.workspaceMenu);
})

addListenerDialogShowButton(els.workspaceAddTableDialog, els.workspaceAddTableButton);
addListenerDialogShowButton(els.workspaceConnectTablesDialog, els.workspaceConnectTablesButton);