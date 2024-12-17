package com.example.radio_active_mushroom.services.impl;

import com.example.radio_active_mushroom.dto.entity.UserRegistrationDto;
import com.example.radio_active_mushroom.models.entity.UserEntity;
import com.example.radio_active_mushroom.repo.entity.UserRepository;
import com.example.radio_active_mushroom.services.MailService;
import com.example.radio_active_mushroom.services.UserRegistrationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserRegistrationServiceImpl implements UserRegistrationService {

    private final UserRepository userRepository;

    private final MailService mailService;

    private final ModelMapper modelMapper;

    @Override
    public void registerNewUser(UserRegistrationDto userRegistrationDto) {
        UserEntity user = modelMapper.map(userRegistrationDto, UserEntity.class);
        user.setVerificationToken(UUID.randomUUID().toString());

        mailService.sendMail(
                userRegistrationDto.getEmail(),
                "Активация аккаунта Radio Active Mushroom",
                "Перейдите по ссылке ниже, чтобы активировать ваш аккаунт Radio Active Mushroom\n"
                        + ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString()
                        + "/accounts/verify/"
                        + user.getVerificationToken() + "/"
        );
        userRepository.save(user);
    }

    @Override
    public boolean validateVerificationToken(String verificationToken) {
        Optional<UserEntity> user = userRepository.findByVerificationToken(verificationToken);
        if (!user.isPresent() || verificationToken == null || verificationToken.isEmpty()) {
            return false;
        } else {
            user.get().setIsActive(true);
            user.get().setVerificationToken(null);
            userRepository.save(user.get());
            return true;
        }
    }
}
