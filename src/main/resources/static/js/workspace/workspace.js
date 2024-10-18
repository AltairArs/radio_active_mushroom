const SHOW_MENU_OFFSET = 20;

var workspace = document.getElementsByClassName('workspace').item(0);
var workspace_menu = document.getElementsByClassName('workspace-menu').item(0);
var workspace_menu_items = document.getElementsByClassName('workspace-menu-item');

function get_pixels(pixels) {
    return String(pixels).concat('px');
}

workspace.addEventListener('contextmenu', function(event) {
    event.preventDefault();
    workspace_menu.style.top = get_pixels(event.y - SHOW_MENU_OFFSET * 1.5);
    workspace_menu.style.left = get_pixels(event.x - SHOW_MENU_OFFSET);
    workspace_menu.style.display = "block";
});

workspace_menu.addEventListener('mouseleave', function(event) {
    event.target.style.display = "none";
});