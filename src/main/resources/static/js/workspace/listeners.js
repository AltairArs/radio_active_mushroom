import * as els from "./elements.js";

import {
    SHOW_MENU_OFFSET
} from "./vars.js";

import {
    set_position,
    show_el,
    hide_el,
    add_listener_dialog_show_button
} from "./functions.js";

els.workspace.addEventListener("contextmenu", function(event){
    event.preventDefault();
    set_position(
        els.workspace_menu,
        event.x - SHOW_MENU_OFFSET,
        event.y - SHOW_MENU_OFFSET * 1.5
    )
    show_el(els.workspace_menu);
});

els.workspace_menu.addEventListener("mouseleave", function (event){
    hide_el(els.workspace_menu);
})

add_listener_dialog_show_button(els.workspace_add_table_dialog, els.workspace_add_table_button);
add_listener_dialog_show_button(els.workspace_connect_tables_dialog, els.workspace_connect_tables_button);