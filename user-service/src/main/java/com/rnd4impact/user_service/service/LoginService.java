package com.rnd4impact.user_service.service;

import com.rnd4impact.user_service.entity.CredentialEntity;
import com.rnd4impact.user_service.entity.UserEntity;
import com.rnd4impact.user_service.repository.CredentialRepository;
import com.rnd4impact.user_service.repository.UserRepository;
import com.rnd4impact.user_service.service.imp.LoginServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements LoginServiceImp {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CredentialRepository credentialRepository;

    @Override
    public CredentialEntity checkLogin(String username, String password) {
        CredentialEntity credentialEntity = credentialRepository.findByUsername(username);
        if ((credentialEntity != null && passwordEncoder.matches(password, credentialEntity.getPassword()))) {
            return credentialEntity;
        }
        return null ;

    }
}
