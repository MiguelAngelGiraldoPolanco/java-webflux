package com.webflux.loginreactive.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcAuditing;

@Configuration
@EnableR2dbcAuditing
public class AuditConfig {
    // Configuración de auditoría reactiva
}