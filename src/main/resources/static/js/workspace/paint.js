import {getRect, setFormInputs} from "./functions.js";
import {workspace} from "./elements.js";

export function clearContent(workspaceContent) {
    $(workspaceContent).empty();
}

export function paint(tables, workspaceContent) {
    clearContent(workspaceContent);
    $.each(tables, function (index, value) {
        let table = "<div id='" + value.fieldSet.name + "' class='table vertical-center'><img src='/img/project/table.png' class='icon'>" + value.fieldSet.name + "</div>";
        $(workspaceContent).append($(table));
    });
    $.each($(workspaceContent).find(".table"), function (index, value){
        $(value).css("top", tables[index].position.y).css("left", tables[index].position.x);
        let rect = getRect(workspace.this);
        let rect2 = getRect(value);
        $(value).draggable({
            containment: [
                rect.x, rect.y, rect.x + rect.width - rect2.width - 50, rect.y + rect.height - rect2.height - 30
            ]
        });
        value.addEventListener("mouseup", function (event){
            let form = workspace.changePositionForm;
            let rect = getRect(value);
            setFormInputs(form, {
                "x": rect.x,
                "y": rect.y,
                "tableName": $(value).attr("id")
            })
            $.post(
                $(form).attr("action"),
                $(form).serializeArray()
            )
        });
    });
}