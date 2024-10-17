package com.example.radio_active_mushroom.dto;

import com.example.radio_active_mushroom.enums.ProjectPermissionsEnum;
import com.example.radio_active_mushroom.models.entity.ProjectEntity;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link ProjectEntity}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectSettingsDto implements Serializable {
    @Size(max = 255)
    private String friendly_name;
    private String description;
    private ProjectPermissionsEnum can_see = ProjectPermissionsEnum.ONLY_OWNER;
    private ProjectPermissionsEnum can_edit = ProjectPermissionsEnum.ONLY_OWNER;
    private ProjectPermissionsEnum can_download = ProjectPermissionsEnum.ONLY_OWNER;
}