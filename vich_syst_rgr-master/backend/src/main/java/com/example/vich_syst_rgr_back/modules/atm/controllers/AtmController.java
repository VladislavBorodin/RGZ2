package com.example.vich_syst_rgr_back.modules.atm.controllers;

import com.example.vich_syst_rgr_back.modules.atm.dto.AtmDepositDto;
import com.example.vich_syst_rgr_back.modules.atm.dto.AtmTransferDto;
import com.example.vich_syst_rgr_back.modules.atm.dto.AtmWithdrawDto;
import com.example.vich_syst_rgr_back.modules.atm.optional.AtmOptionalService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

/**
 * класс-контроллер предоставляющий возможность работы со счетом аутентифицированного юзера через банкомат
 * Для доступа к контроллеру необходима роль "BANK_USER"
 */

@RestController
@AllArgsConstructor
@RequestMapping("api/bankUser/atm")
@Validated
public class AtmController {

    /**
     * опциональный сервис содержащий в себе бизнес-логику работы со счетом
     * аутентифицированного юзера через банкомат
     */
    private final AtmOptionalService atmService;

    /**
     * метод обрабатывающий запрос на вывод баланса конкретного счета аутентифицированного пользователя
     * @param accountId номер счета
     * @return HTTP-ответ с количеством денег на счету
     */
    //TODO придумать адекватную ссылку
    @GetMapping("balance{accountId}")
    public BigDecimal balance(@Valid @PathVariable @Min(1) int accountId) {
        return atmService.balance(accountId);
    }

    /**
     * метод обрабатывающий запрос на занасение денег аутентифицированным пользователем на свой определенный счет
     * посредством конкретного банкомата
     * @param dto преобразованное в объек класса @see AtmDepositDto JSON-тело запроса
     */
    @PostMapping("deposit")
    public void deposit(@Valid @RequestBody AtmDepositDto dto) {
        atmService.deposit(dto.getAtmId(),dto.getAccountId(),dto.getToAdd());
    }

    /**
     * метод обрабатывающий запрос на снятие денег аутентифицированным пользователем со своего конкретного счета
     * посредством конкретного банкомата
     * @param dto преобразованное в объек класса @see AtmWithdrawDto JSON-тело запроса
     */
    @PostMapping("withdraw")
    public void withdraw(@Valid @RequestBody AtmWithdrawDto dto) {
        atmService.withdraw(dto.getAtmId(), dto.getAccountId(), dto.getToSub());
    }

    @PostMapping("transfer")
    public void transfer(@Valid @RequestBody AtmTransferDto dto) {
        atmService.transfer(dto.getFromAccountId(), dto.getToAccountId(), dto.getAmount());
    }

}
