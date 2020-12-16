package com.example.vich_syst_rgr_back.modules.user.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import java.util.Set;

/**ДТО для универсального создания пользователя*/

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterUserDto {
    @Pattern(regexp = "[A-Za-z]+")
    private String username;
    private String password;
    private Set<String> authorities;
}
