package com.example.radio_active_mushroom.authentication;

import com.example.radio_active_mushroom.models.UserEntity;
import com.example.radio_active_mushroom.repo.ThemeRepository;
import com.example.radio_active_mushroom.repo.UserRepository;
import com.example.radio_active_mushroom.services.UserProfileService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
public class CustomAuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserProfileService userProfileService;

    public void Login(String username) {
        Optional<UserEntity> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            UserEntity userEntity = user.get();
            userEntity.setLast_login(LocalDateTime.now());
            userRepository.save(userEntity);
        }
    }

    private PasswordEncoder passwordEncoder() {return new BCryptPasswordEncoder(5);};

    public CustomAuthenticationToken authenticate(CustomAuthenticationToken authentication) {
        Optional<UserEntity> user = userRepository.findByEmail(authentication.getEmail());
        if (!user.isPresent()) {
            user = userRepository.findByUsername(authentication.getUsername());
        }
        if (user.isPresent()) {
            log.info("[AUTHENTICATION] User found");
            if (passwordEncoder().matches(authentication.getPassword(), user.get().getPassword()) && user.get().getIs_active()) {
                log.info("[AUTHENTICATION] Password confirmed");
                CustomUserDetails userDetails = modelMapper.map(user.get(), CustomUserDetails.class);
                userDetails.setUserProfileService(userProfileService);
                return new CustomAuthenticationToken(userDetails);
            }
            log.error("[AUTHENTICATION] Wrong password");
        }
        log.error("[AUTHENTICATION] User not found");
        return authentication;
    }

    public UserEntity GetCurrentUser(Authentication authentication) {
        try {
            CustomAuthenticationToken auth = (CustomAuthenticationToken) authentication;
            Optional<UserEntity> user = userRepository.findByUsername(auth.getName());
            return user.orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
