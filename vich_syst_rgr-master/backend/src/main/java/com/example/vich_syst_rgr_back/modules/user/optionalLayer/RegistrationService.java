package com.example.vich_syst_rgr_back.modules.user.optionalLayer;

import com.example.vich_syst_rgr_back.modules.user.domain.BankUser;
import com.example.vich_syst_rgr_back.modules.user.dtos.RegisterBankUserDto;
import com.example.vich_syst_rgr_back.modules.user.repositories.BankUserRepo;
import com.example.vich_syst_rgr_back.modules.user.repositories.UserRepo;
import com.example.vich_syst_rgr_back.modules.user.utilLayer.exceptions.UsernameIsUsedException;
import com.example.vich_syst_rgr_back.modules.user.utilLayer.factories.BankUserFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Класс-сервис опционального уровня
 * инкапсулирует в себе логику создания разных типов пользователей
 */

@Service
@AllArgsConstructor
public class RegistrationService {

    //TODO попробовать использовать только один тип репозиториев
    private final UserRepo userRepo;
    private final BankUserRepo bankUserRepo;

    /**
     * создает пользователя банковской части приложения
     * @param dto данные передаваемые в запросе на создание пользователя
     */

    //TODO подумать об транзакциях
    public void registerBankUser (RegisterBankUserDto dto) {
        //проверка логина на доступность (логины удаленных юзеров так же под запретом)
        if (userRepo.existsByUsername(dto.getUsername()))
            throw new UsernameIsUsedException("Username " + dto.getUsername() + " is used");
        //создание новой СУЩНОСТИ BankUser с хешированным паролем по ДТО
        BankUser bankUserFromDto = BankUserFactory.createBankUserByRegDtoWithHashedPass(dto);
        //создание СУЩНОСТИ в базе
        bankUserRepo.save(bankUserFromDto);
    }
}
