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
    workspace,
    getCSRF
} from "./elements.js";
/*
SHOW AND HIDE WORKSPACE MENU
 */
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
/*
ADD EVENTS FOR DIALOG, BUTTONS AND FORMS
 */
$.each(workspace.menu.options, function (key, value){
    // SHOW DIALOG
    $(value.showButton).click(function (event){
        value.dialog.showModal();
    });
    // SENDING AJAX REQUESTS
    $(value.submitButton).click(function (event){
       event.preventDefault();
       let form = value.dialog.getElementsByTagName("form").item(0);
       $.post(
            $(form).attr("action"),
            // FORM DATA
            $(form).serializeArray()
       ).then(function (data){
           // WORK WITH REQUEST RESPONSE
           alert(data.name)
       });
    });
});
/*
ADD CSRF TOKEN TO HEADERS FOR AJAX REQUESTS
 */
$(document).ajaxSend(function (event, xhr, options){
    let csrf = getCSRF();
    xhr.setRequestHeader(csrf.header, csrf.token);
});
