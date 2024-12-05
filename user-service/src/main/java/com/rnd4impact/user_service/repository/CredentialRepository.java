package com.rnd4impact.user_service.repository;

import com.rnd4impact.user_service.entity.CredentialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialRepository extends JpaRepository<CredentialEntity, Integer> {
    CredentialEntity findByUsername(String username);
}
