package com.example.radio_active_mushroom.services;

import com.example.radio_active_mushroom.dto.UserRegistrationDto;
import com.example.radio_active_mushroom.models.UserEntity;
import com.example.radio_active_mushroom.repo.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailService mailService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserRegistrationDto GetUserRegistrationForm() {
        return new UserRegistrationDto();
    }

    @Override
    public void RegisterNewUser(UserRegistrationDto userRegistrationDto) {
        UserEntity user = modelMapper.map(userRegistrationDto, UserEntity.class);
        user.setVerification_token(UUID.randomUUID().toString());

        mailService.sendMail(
                userRegistrationDto.getEmail(),
                "Активация аккаунта Radio Active Mushroom",
                "Перейдите по ссылке ниже, чтобы активировать ваш аккаунт Radio Active Mushroom\n"
                        + ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString()
                        + "/accounts/verify/"
                        + user.getVerification_token() + "/"
        );
        userRepository.save(user);
    }

    @Override
    public boolean ValidateVerificationToken(String verificationToken) {
        Optional<UserEntity> user = userRepository.findByVerification_token(verificationToken);
        if (!user.isPresent()) {
            return false;
        } else {
            user.get().setIs_active(true);
            user.get().setVerification_token(null);
            userRepository.save(user.get());
            return true;
        }
    }
}
