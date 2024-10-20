package com.example.radio_active_mushroom.dto.entity;

import com.example.radio_active_mushroom.constraints.OnlyLettersAndNumbers;
import com.example.radio_active_mushroom.enums.ProjectPermissionsEnum;
import com.example.radio_active_mushroom.models.entity.ProjectEntity;
import jakarta.validation.constraints.NotBlank;
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
public class ProjectCreateDto implements Serializable {
    @NotBlank
    @OnlyLettersAndNumbers
    @Size(max = 255)
    private String name;
    @Size(max = 255)
    private String friendlyName;
    private String description;

    private ProjectPermissionsEnum canSee = ProjectPermissionsEnum.ONLY_OWNER;
    private ProjectPermissionsEnum canEdit = ProjectPermissionsEnum.ONLY_OWNER;
    private ProjectPermissionsEnum canDownload = ProjectPermissionsEnum.ONLY_OWNER;
}