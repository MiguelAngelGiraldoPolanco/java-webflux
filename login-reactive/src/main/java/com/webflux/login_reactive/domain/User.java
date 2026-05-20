package com.webflux.login_reactive.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private Long id;
    private String email;
    private String name;
    @Column("password_hash")
    private String passwordHash;
    @CreatedDate
    @Column("created_at")
    private LocalDateTime createdAt;
    @LastModifiedDate
    Column("updated_at")
    private LocalDateTime updatedAt;
    @Column("is_admin")
    private Boolean isAdmin = false;
}