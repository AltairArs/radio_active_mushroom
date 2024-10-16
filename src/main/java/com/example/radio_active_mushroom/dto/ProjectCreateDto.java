package com.example.radio_active_mushroom.dto;

import com.example.radio_active_mushroom.constraints.OnlyLettersAndNumbers;
import com.example.radio_active_mushroom.enums.ProjectPermissionsEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link com.example.radio_active_mushroom.models.ProjectEntity}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectCreateDto implements Serializable {
    @NotBlank
    @OnlyLettersAndNumbers
    @Size(max = 255)
    private String name;
    @Size(max = 255)
    private String friendly_name;
    private String description;
    @NotBlank
    private ProjectPermissionsEnum can_see = ProjectPermissionsEnum.ONLY_OWNER;
    @NotBlank
    private ProjectPermissionsEnum can_edit = ProjectPermissionsEnum.ONLY_OWNER;
    @NotBlank
    private ProjectPermissionsEnum can_download = ProjectPermissionsEnum.ONLY_OWNER;
}