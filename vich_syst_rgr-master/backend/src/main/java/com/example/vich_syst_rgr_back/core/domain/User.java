package com.example.vich_syst_rgr_back.core.domain;

import com.example.vich_syst_rgr_back.core.utilLayerGlobal.entitiesUtil.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Set;

/**
 * класс-представление пользователя в системе
 * используется для авторизации/аутентификации
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User extends BaseEntity implements UserDetails {

    /**логин пользователя*/
    @Column(name = "username")
    private String username;
    /**захешированный пароль пользователя*/
    @Column(name = "password_hash")
    private String password;

    /**Список ролей для авторизации*/
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"))
    private Set<Role> authorities;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}