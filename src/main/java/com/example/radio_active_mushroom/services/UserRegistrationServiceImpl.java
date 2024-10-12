package com.example.radio_active_mushroom.services;

import com.example.radio_active_mushroom.dto.UserRegistrationDto;
import com.example.radio_active_mushroom.models.UserEntity;
import com.example.radio_active_mushroom.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
    private JavaMailSender mailSender;

    @Override
    public UserRegistrationDto GetUserRegistrationForm() {
        return new UserRegistrationDto();
    }

    @Override
    public UserEntity RegisterNewUser(UserRegistrationDto userRegistrationDto) {
        UserEntity user = new UserEntity();
        user.setUsername(userRegistrationDto.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(userRegistrationDto.getPassword()));
        user.setEmail(userRegistrationDto.getEmail());
        user.setFirst_name(userRegistrationDto.getFirst_name());
        user.setLast_name(userRegistrationDto.getLast_name());
        user.setVerification_token(UUID.randomUUID().toString());
        userRepository.save(user);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(userRegistrationDto.getEmail());
        message.setSubject("Активация аккаунта Radio Active Mushroom");
        message.setFrom("serzh.radchenko.2003@gmail.com");
        message.setText("Перейдите по ссылке ниже, чтобы активировать ваш аккаунт Radio Active Mushroom\n"
                + ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString()
                + "/accounts/verify/"
                + user.getVerification_token() + "/");
        mailSender.send(message);
        return user;
    }

    @Override
    public boolean ValidateVerificationToken(String verificationToken) {
        Optional<UserEntity> user = userRepository.findByVerification_token(verificationToken);
        if (!user.isPresent()) {
            return false;
        } else {
            user.get().setIs_active(true);
            userRepository.save(user.get());
            return true;
        }
    }
}
