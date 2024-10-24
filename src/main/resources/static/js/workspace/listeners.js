import {
    SHOW_MENU_OFFSET
} from "./vars.js";

import {
    setPosition,
    showEl,
    hideEl,
    clamp,
    getRect,
    showErrors, clearErrors, setFormInputs, clearFormInputs
} from "./functions.js";

import {
    workspace,
    getCSRF,
    getAllURL
} from "./elements.js";
import {paint} from "./paint.js";

let position = {
    "x": 0,
    "y": 0
};
let scale = 1;

try {
    /*
    SHOW AND HIDE WORKSPACE MENU
     */
    workspace.this.addEventListener("contextmenu", function (event){
        event.preventDefault();
        showEl(workspace.menu.this);
        let rect = getRect(workspace.this);
        position.x = clamp(event.x - SHOW_MENU_OFFSET, rect.x, rect.x + rect.width - getRect(workspace.menu.this).width);
        position.y = clamp(event.y - SHOW_MENU_OFFSET * 1.5, rect.y, rect.y + rect.height - getRect(workspace.menu.this).height - 15);
        setPosition(
            workspace.menu.this,
            position.x,
            position.y
        );
        // SET POSITION FOR NEW TABLE
        setFormInputs(workspace.menu.options.addTable.dialog.getElementsByTagName("form").item(0), {
            "position_x": position.x,
            "position_y": position.y
        });
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
                clearErrors(form);
                // IF IT HAS ERRORS
                if (data.errors){
                    showErrors(form, data.errors);
                } else {
                    value.dialog.close();
                    clearFormInputs(form);
                    paint(data.tables, workspace.content);
                }
            });
        });
    });
    // ON DRAGGING FOR WORKSPACE CONTENT
    $(workspace.content).draggable();
    // GET TABLES
    $.get(
        getAllURL
    ).then(function (data){
        paint(data.tables, workspace.content);
    });
    // ZOOM
    $(workspace.this).bind("mousewheel", function (event){
        if (event.ctrlKey){
            event.preventDefault();
            scale = clamp(scale + event.originalEvent.wheelDelta / 1200.0, 0.4, 1);
            $(workspace.content).parent().css("transform", "scale(" + scale + ")");
        }
    });
} catch (e) {

}
/*
ADD CSRF TOKEN TO HEADERS FOR AJAX REQUESTS
 */
$(document).ajaxSend(function (event, xhr, options){
    let csrf = getCSRF();
    if (csrf.has) {
        xhr.setRequestHeader(csrf.header, csrf.token);
    }
});
