export function clearContent(workspaceContent) {
    $(workspaceContent).empty();
}

export function paint(tables, workspaceContent) {
    clearContent(workspaceContent);
    $.each(tables, function (index, value) {
        let table = "<div class='table vertical-center'><img src='/img/project/table.png' class='icon'>" + value.fieldSet.name + "</div>";
        $(workspaceContent).append($(table));
        $(table).css("top", value.position.y).css("left", value.position.x);
    });
    $.each($(workspaceContent).find(".table"), function (index, value){
        $(value).css("top", tables[index].position.y).css("left", tables[index].position.x);
    });
}