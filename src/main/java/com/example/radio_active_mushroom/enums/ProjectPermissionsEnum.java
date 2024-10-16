package com.example.radio_active_mushroom.enums;

public enum ProjectPermissionsEnum {
    ALL, ONLY_MEMBERS, ONLY_OWNER;

    public String getPermissionName() {
        return switch (this) {
            case ALL -> "Все";
            case ONLY_MEMBERS -> "Только участники";
            case ONLY_OWNER -> "Только владелец";
        };
    }
}
