package com.rnd4impact.user_service.service.imp;

import com.rnd4impact.user_service.entity.CredentialEntity;

public interface LoginServiceImp {
    CredentialEntity checkLogin(String username, String password);
}
