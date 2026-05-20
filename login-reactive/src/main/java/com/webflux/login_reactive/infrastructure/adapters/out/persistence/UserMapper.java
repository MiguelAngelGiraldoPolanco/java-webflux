package com.webflux.login_reactive.infrastructure.adapters.out.persistence;

import com.webflux.login_reactive.domain.User;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("users")
public class UserEntity {
    @Column("id")
    private Long id;
    @Column("email")
    private String email;
    @Column("password")
    private String password;
    @Column("created_at")
    private LocalDateTime createdAt;
    @Column("updated_at")
    private LocalDateTime updatedAt;
    @Column("is_admin")
    private Boolean isAdmin;

    // Getters and Setters
}

public class UserMapper {
    public static UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        entity.setCreatedAt(user.getCreatedAt());
        entity.setUpdatedAt(user.getUpdatedAt());
        entity.setIsAdmin(user.isAdmin());
        return entity;
    }

    public static User toDomain(UserEntity entity) {
        return User.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .isAdmin(entity.isIsAdmin())
                .build();
    }
}