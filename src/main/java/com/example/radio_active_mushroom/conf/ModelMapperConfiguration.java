package com.example.radio_active_mushroom.conf;

import com.example.radio_active_mushroom.dto.UserRegistrationDto;
import com.example.radio_active_mushroom.models.UserEntity;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.springframework.beans.factory.support.InstanceSupplier.using;

@Configuration
public class ModelMapperConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelmapper = new ModelMapper();
        modelmapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
        Converter<String, String> passwordEncode = src -> new BCryptPasswordEncoder().encode(src.getSource());
        modelmapper.createTypeMap(UserRegistrationDto.class, UserEntity.class)
                .addMappings(mapper -> mapper
                                .using(passwordEncode)
                                .map(UserRegistrationDto::getPassword, UserEntity::setPassword)
                );
        return modelmapper;
    }
}
