package com.rnd4impact.user_service.security;

import com.rnd4impact.user_service.entity.CredentialEntity;
import com.rnd4impact.user_service.service.imp.LoginServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    LoginServiceImp loginServiceImp;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        CredentialEntity credentialEntity = loginServiceImp.checkLogin(username, password);
        if(credentialEntity != null) {
            List<GrantedAuthority> listRoles = new ArrayList<>();
            SimpleGrantedAuthority role = new SimpleGrantedAuthority(credentialEntity.getRoles());
            listRoles.add(role);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken("","",listRoles);
            return authenticationToken ;
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
