import * as vars from "./vars.js";

export let workspace = document.getElementById(vars.WORKSPACE_ID);
export let workspaceMenu = document.getElementById(vars.WORKSPACE_MENU_ID);
export let workspaceContent = document.getElementById(vars.WORKSPACE_CONTENT_ID);

export let workspaceAddTableButton = document.getElementById(vars.WORKSPACE_ADD_TABLE_BUTTON_ID);
export let workspaceAddTableDialog = document.getElementById(vars.WORKSPACE_ADD_TABLE_DIALOG_ID);

export let workspaceConnectTablesButton = document.getElementById(vars.WORKSPACE_CONNECT_TABLES_BUTTON_ID);
export let workspaceConnectTablesDialog = document.getElementById(vars.WORKSPACE_CONNECT_TABLES_DIALOG_ID);

export let projectOwnerUsername = document.getElementById(vars.PROJECT_OWNER_USERNAME_ID).textContent
export let projectName = document.getElementById(vars.PROJECT_NAME_ID).textContent

export let workspaceAddTableButtonSubmit = document.getElementById(vars.WORKSPACE_ADD_TABLE_BUTTON_SUBMIT_ID);