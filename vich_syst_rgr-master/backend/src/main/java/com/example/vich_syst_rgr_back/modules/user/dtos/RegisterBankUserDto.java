package com.example.vich_syst_rgr_back.modules.user.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;

/**
 * ДТО для регистрации пользователя банковской части приложения
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterBankUserDto {
    @Pattern(regexp = "[A-Za-z]+")
    private String username;
    private String password;
}
