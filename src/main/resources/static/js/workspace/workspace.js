import {
    getCSRF
} from "./elements.js";

import {
    initWorkspace
} from "./inits.js";
import {setFormInputs} from "./forms.js";
import {getRect} from "./functions.js";

/*
CLASS WITH GLOBAL PARAMETERS
 */
class WorkspaceObject {
    constructor(x, y, scale) {
        this.x = x;
        this.y = y;
        this.scale = scale;
    }
}

$(document).ajaxSend(function (event, xhr, options){
    let csrf = getCSRF();
    if (csrf.has) {
        xhr.setRequestHeader(csrf.header, csrf.token);
    }
});

let workspaceObject = new WorkspaceObject(0, 0, 1);

initWorkspace(workspaceObject, {
    ".workspace-frame": function (menuTarget, menu, workspace){
        let rect = getRect(workspace);
        setFormInputs(
            $(document.getElementById("addTableDialog")).find("form"), {
                "position_x": workspaceObject.x - rect.x,
                "position_y": workspaceObject.y - rect.y
            }
        );
    },
    ".workspace-table": function (menuTarget, menu, workspace){
        setFormInputs(
            $(document.getElementById("deleteTableDialog")).find("form"), {
                "tableName": $(menuTarget).attr("id")
            }
        );
    }
});