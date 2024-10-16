package com.example.radio_active_mushroom.models.documents;

import com.example.radio_active_mushroom.enums.DBSupportedEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Entity
@Document("db_drawer_settings_entity")
public class DB_DrawerSettings {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "database")
    private DBSupportedEnum database;

    @Column(name = "version")
    private String version;
}