package com.rnd4impact.user_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;

import java.security.Timestamp;
import java.time.LocalDateTime;

@Data
@Entity(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "image_url", length = 255)
    private String imageUrl;

    @Column(name = "email",unique = true)
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

   @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private CredentialEntity credential;

/*    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Address> addresses;*/

}
