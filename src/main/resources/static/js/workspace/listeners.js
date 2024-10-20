import * as els from "./elements.js";

import {
    SHOW_MENU_OFFSET
} from "./vars.js";

import {
    setPosition,
    showEl,
    hideEl,
    addListenerDialogShowButton,
    getWorkspaceWidth,
    getWorkspaceHeight,
    clamp
} from "./functions.js";

els.workspace.addEventListener("contextmenu", function(event){
    event.preventDefault();
    showEl(els.workspaceMenu);
    setPosition(
        els.workspaceMenu,
        clamp(event.x - SHOW_MENU_OFFSET, 0, getWorkspaceWidth() - els.workspaceMenu.offsetWidth),
        clamp(event.y - SHOW_MENU_OFFSET * 1.5, 0, getWorkspaceHeight() - els.workspaceMenu.offsetHeight)
    )
});

els.workspaceMenu.addEventListener("mouseleave", function (event){
    hideEl(els.workspaceMenu);
})

addListenerDialogShowButton(els.workspaceAddTableDialog, els.workspaceAddTableButton);
addListenerDialogShowButton(els.workspaceConnectTablesDialog, els.workspaceConnectTablesButton);