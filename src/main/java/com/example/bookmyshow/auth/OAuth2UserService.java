package com.example.bookmyshow.auth;

import com.auth.modal.user.User;
import com.auth.security.oauth2.CustomOAuth2UserService;
import org.springframework.stereotype.Service;

@Service
public class OAuth2UserService extends CustomOAuth2UserService {
    @Override
    public void processOAuth2User(User user) {

    }
}
