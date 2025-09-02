package com.nisum.user_management.domain.model;

import com.nisum.user_management.infrastructure.adapter.security.model.enums.RoleEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
public class UserRequest {
    private UUID id;
    private String name;
    private String email;
    private String password;
    private List<Phone> phones;
    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime lastLogin;
    private boolean isactive;
    private Set<RoleEnum> roles;

    public UserRequest(String name, String email, String password, List<Phone> phones) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phones = phones;
    }
}
