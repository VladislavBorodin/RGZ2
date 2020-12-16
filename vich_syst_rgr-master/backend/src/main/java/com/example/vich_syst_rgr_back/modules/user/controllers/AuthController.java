package com.example.vich_syst_rgr_back.modules.user.controllers;

import com.example.vich_syst_rgr_back.modules.user.dtos.RegisterBankUserDto;
import com.example.vich_syst_rgr_back.modules.user.optionalLayer.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * класс-контроллек содержащий методы для регистрации
 */
@RestController
@RequestMapping("/api/reg")
@AllArgsConstructor
public class AuthController {

    /**сервис регистрации опциоцального уровня*/
    private final RegistrationService registrationService;

    /**
     * метод для принятия запроса на регистрацию нового BankUser
     * @param dto ДТО класса @see RegisterBankUserDto , содержащее в себе логин и пароль для нового пользователя
     */
    //TODO этому контролеру тут не место, он должен быть в модуле банка
    @PostMapping("/bankUser")
    @ResponseStatus(HttpStatus.CREATED)
    public void registration(@Valid @RequestBody RegisterBankUserDto dto) {
        registrationService.registerBankUser(dto);
    }
}