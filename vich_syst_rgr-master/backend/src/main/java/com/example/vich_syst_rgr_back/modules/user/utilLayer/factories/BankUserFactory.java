package com.example.vich_syst_rgr_back.modules.user.utilLayer.factories;

import com.example.vich_syst_rgr_back.modules.user.domain.BankUser;
import com.example.vich_syst_rgr_back.core.domain.Role;
import com.example.vich_syst_rgr_back.modules.user.dtos.RegisterBankUserDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

/**
 * класс-фабрика для BankUser
 */

public class BankUserFactory {

    /**
     * Создает новую сущность BankUser по RegisterBankUserDto
     * @param dto данные для создания пользователя (содержит логин и пароль)
     * @return новую сущность BankUser с id==null (для сохранения в базу как новую сущность) и захешированным паролем
     */

    public static BankUser createBankUserByRegDtoWithHashedPass (RegisterBankUserDto dto) {
        var newBankUser = new BankUser();
        newBankUser.setUsername(dto.getUsername());
        //перед записью пароля в BankUser он хешируется
        newBankUser.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));
        newBankUser.setAuthorities(Set.of(new Role("BANK_USER")));
        newBankUser.setBankAccounts(new HashSet<>());
        return newBankUser;
    }
}
