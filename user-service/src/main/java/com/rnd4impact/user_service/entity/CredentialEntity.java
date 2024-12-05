package com.rnd4impact.user_service.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.security.Timestamp;
import java.time.LocalDateTime;

@Data
@Entity(name = "credentials")
public class CredentialEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "credential_id")
    private int credentialId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "roles", length = 50)
    private String roles;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

/*    @OneToOne(mappedBy = "credential", cascade = CascadeType.ALL)
    private VerificationToken verificationToken;*/
}
