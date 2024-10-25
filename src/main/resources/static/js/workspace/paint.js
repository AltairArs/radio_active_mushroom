import {getRect, setPosition} from "./functions.js";
import {setFormInputs} from "./forms.js";
import {initTargets} from "./inits.js";
/*
REMOVE TABLES
 */
function clearContent(content){
    $(content).empty();
}
/*
PAINT ONE TABLE
 */
function paintTable(table) {
    let name = "";
    if (table.fieldSet.friendlyName != null && table.fieldSet.friendlyName !== ""){
        name = table.fieldSet.friendlyName;
    } else {
        name = table.fieldSet.name;
    }
    return "<div id='" + table.fieldSet.name + "' class='workspace-table vertical-center'><img src='/img/project/table.png' class='icon'>" + name + "</div>"
}

export function paintTables(tables, content, workspace, formChangePosition, workspaceObject, targetSettings) {
    clearContent(content);
    /*
    ADD TABLES
     */
    $.each(tables, function (index, value){
        $(content).append($(paintTable(value)));
    });

    $.each($(content).find(".workspace-table"), function (index, value){
        setPosition(value, tables[index].position.x, tables[index].position.y);
        let rectWorkspace = getRect(workspace);
        let rectTable = getRect(value);
        /*
        ON TABLE DRAGGING
         */
        $(value).draggable({
            containment: [
                rectWorkspace.x, rectWorkspace.y,
                rectWorkspace.x + rectWorkspace.width - rectTable.width - 50, rectWorkspace.y + rectWorkspace.height - rectTable.height - 30
            ]
        });
        /*
        SEND REQUEST FOR CHANGE TABLE POSITION
         */
        $(value).mouseup(function (event){
            let rectTable = getRect(value);
            setFormInputs(
                formChangePosition, {
                    "x": rectTable.x - rectWorkspace.x,
                    "y": rectTable.y - rectWorkspace.y,
                    "tableName": $(value).attr("id")
                }
            );
            $.post(
                $(formChangePosition).attr("action"),
                $(formChangePosition).serializeArray()
            );
        });
    });
    /*
    INIT TARGETS
     */
    initTargets(workspace, workspaceObject, targetSettings);
}