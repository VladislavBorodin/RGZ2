package com.example.vich_syst_rgr_back.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;

/**
 * Класс представление роли для авторизации
 */
@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users_roles")
public class Role implements GrantedAuthority {
    @Column(name = "role")
    private String Authority;
}
