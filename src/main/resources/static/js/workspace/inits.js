import {clamp, getRect, hideEl, setPosition, showEl} from "./functions.js";
import {clearErrors, clearFormInputs, setFormInputs, showErrors} from "./forms.js";
import {paintTables} from "./paint.js";
import {
    CHANGE_TABLE_POSITION_FORM_ID, DISPLAY_SHOW,
    MENU_TARGET_ATTRIBUTE_NAME, SHOW_DIALOG_ID_ATTRIBUTE_NAME,
    SHOW_MENU_OFFSET,
    WORKSPACE_CLASS,
    WORKSPACE_CONTENT_CLASS
} from "./vars.js";
import {getAllURL} from "./elements.js";
/*
INITIALIZE ALL MENU
 */
function initMenu(menu, content, workspace, formChangePosition, workspaceObject, targetSettings) {
    /*
    HIDE MENU IF MOUSE LEAVE
     */
    $(menu).mouseleave(function (event){
        hideEl(menu);
    })

    $.each($(menu).children("button"), function (index, value){
        let dialog = document.getElementById($(value).attr(SHOW_DIALOG_ID_ATTRIBUTE_NAME));
        /*
        SHOW MODAL DIALOG
         */
        $(value).click(function (event){
            dialog.showModal();
            hideEl(menu);
        })
        let submitButton = $(dialog).find("input[type='submit']")[0];
        let form = $(dialog).find("form")[0];
        /*
        SUBMIT FORM AJAX
         */
        $(submitButton).click(function (event){
            event.preventDefault();
            $.post(
                $(form).attr("action"),
                $(form).serializeArray()
            ).then(function (data){
                clearErrors(form);
                /*
                IF FORM HAS ERRORS
                 */
                if (data.errors) {
                    showErrors(form, data.errors);
                } else {
                    /*
                    IF SUCCESS
                     */
                    dialog.close();
                    clearFormInputs(form);
                    /*
                    PAINT ALL TABLES
                     */
                    paintTables(data.tables, content, workspace, formChangePosition, workspaceObject, targetSettings);
                }
            });
        });
    });
}
/*
INIT TARGETS FOR MENU
 */
export function initTargets(workspace, workspaceObject, targetSettings) {
    $.each($(workspace).children(".workspace-menu"), function (index, value){
        let menuTarget = $(value).attr(MENU_TARGET_ATTRIBUTE_NAME);
        /*
        TARGET ELEMENT
         */
        $.each($(menuTarget).toArray(), function (index, targetValue){
            targetValue.addEventListener("contextmenu", function (event){
                let showedMenu = 0;
                $.each($(".workspace-menu"), function (index, mValue){
                    if (mValue.style.display  === DISPLAY_SHOW){
                        showedMenu++;
                    }
                });
                if (showedMenu === 0){
                    showEl(value);
                }
                event.preventDefault();
                let rectWorkspace = getRect(workspace);
                let rectMenu = getRect(value);
                /*
                UPDATE GLOBAL PARAMETERS
                 */
                workspaceObject.x = clamp(event.pageX - SHOW_MENU_OFFSET, rectWorkspace.x, rectWorkspace.x + rectWorkspace.width - rectMenu.width);
                workspaceObject.y = clamp(event.pageY - SHOW_MENU_OFFSET * 1.5, rectWorkspace.y, rectWorkspace.y + rectWorkspace.height - rectMenu.height - SHOW_MENU_OFFSET);
                setPosition(
                    value,
                    workspaceObject.x,
                    workspaceObject.y
                );
                /*
                CUSTOM ACTIONS
                 */
                $.each(targetSettings, function (key, setValue){
                    if (key === menuTarget) {
                        setValue(targetValue, value, workspace);
                        return false; // break
                    }
                });
            });
        });
    });
}
/*
INITIALIZE WORKSPACE
 */
export function initWorkspace(workspaceObject, targetSettings) {
    let workspace = document.getElementsByClassName(WORKSPACE_CLASS).item(0);
    let content = $(workspace).find("." + WORKSPACE_CONTENT_CLASS);
    let formChangePosition = document.getElementById(CHANGE_TABLE_POSITION_FORM_ID);

    $(content).draggable();
    /*
    ZOOMING
     */
    $(workspace).bind("mousewheel", function (event){
        if (event.ctrlKey){
            event.preventDefault();
            workspaceObject.scale = clamp( workspaceObject.scale + event.originalEvent.wheelDelta / 1200.0, 0.4, 1);
            $(content).parent().css("transform", "scale(" + workspaceObject.scale + ")");
        }
    });
    /*
    INIT ALL MENU
     */
    $.each($(workspace).find(".workspace-menu"), function (index, value){
        initMenu(value, content, workspace, formChangePosition, workspaceObject, targetSettings);
    });
    /*
    FIRST PAINTING
     */
    $.get(
        getAllURL
    ).then(function (data){
        paintTables(data.tables, content, workspace, formChangePosition, workspaceObject, targetSettings);
    });
}