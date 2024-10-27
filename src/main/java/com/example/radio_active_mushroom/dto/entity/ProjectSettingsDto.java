package com.example.radio_active_mushroom.dto.entity;

import com.example.radio_active_mushroom.enums.ProjectPermissionsEnum;
import com.example.radio_active_mushroom.models.entity.ProjectEntity;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link ProjectEntity}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProjectSettingsDto implements Serializable {
    @Size(max = 255)
    private String friendlyName;
    private String description;
    private ProjectPermissionsEnum canSee = ProjectPermissionsEnum.ONLY_OWNER;
    private ProjectPermissionsEnum canEditWorkspace = ProjectPermissionsEnum.ONLY_OWNER;
    private ProjectPermissionsEnum canExport = ProjectPermissionsEnum.ONLY_OWNER;
    private ProjectPermissionsEnum canConvert = ProjectPermissionsEnum.ONLY_OWNER;
    private ProjectPermissionsEnum canGenerate = ProjectPermissionsEnum.ONLY_OWNER;
    private ProjectPermissionsEnum canSeeWorkspace = ProjectPermissionsEnum.ONLY_OWNER;
}