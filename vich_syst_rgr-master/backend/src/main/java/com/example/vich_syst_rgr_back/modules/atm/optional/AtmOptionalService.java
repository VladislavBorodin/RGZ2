package com.example.vich_syst_rgr_back.modules.atm.optional;

import com.example.vich_syst_rgr_back.core.servises.CurrentUserService;
import com.example.vich_syst_rgr_back.modules.atm.repositories.AtmRepo;
import com.example.vich_syst_rgr_back.modules.atm.utilLayer.exceptions.AtmNotFoundException;
import com.example.vich_syst_rgr_back.modules.bank.domain.BankAccount;
import com.example.vich_syst_rgr_back.modules.bank.domain.HistoryLine;
import com.example.vich_syst_rgr_back.modules.bank.repositories.BankAccountRepo;
import com.example.vich_syst_rgr_back.modules.bank.utilLayer.exceptions.BankAccountNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Класс-сервис опционального уровня
 * содержит в себе логику работы со счетами через банкомат
 */

@Service
@AllArgsConstructor
public class AtmOptionalService {

    private final AtmRepo atmRepo;
    private final BankAccountRepo accountRepo;
    private final CurrentUserService currentUserService;

    /**
     * метод, позволяющий положить деньги на счет текущего пользователя через банкомат
     * @param atmId id банкомата, через который проводиться операция
     * @param accountId id счета, над которым проводиться операция
     * @param amount сумма, на которую необходимо изменить состояние счета
     */
    public void deposit (int atmId, int accountId, BigDecimal amount) {
        var atm = atmRepo.findByIdAndRemovedDateIsNull(atmId)
                .orElseThrow(AtmNotFoundException::new);
        var account = getAccountForCurrentUserByAccountId(accountId);
        atm.addCash(amount);
        account.deposit(amount);
        account.addHistoryLine(new HistoryLine("deposit", LocalDateTime.now(),amount));
        atmRepo.save(atm);
        accountRepo.save(account);
    }

    /**
     * метод, позволяющий снять деньги со счета текущего пользователя через банкомат
     * @param atmId id банкомата, через который проводиться операция
     * @param accountId id счета, над которым проводиться операция
     * @param amount сумма, на которую необходимо изменить состояние счета
     */
    public void withdraw (int atmId, int accountId, BigDecimal amount) {
        var atm = atmRepo.findByIdAndRemovedDateIsNull(atmId)
                .orElseThrow(AtmNotFoundException::new);
        var account = getAccountForCurrentUserByAccountId(accountId);
        atm.subCash(amount);
        account.withdraw(amount);
        account.addHistoryLine(new HistoryLine("withdraw", LocalDateTime.now(),amount));
        atmRepo.save(atm);
        accountRepo.save(account);
    }

    /**
     * метод, через который можно получить баланс счета конкретного счета текущего пользователя
     * @param accountId id счета, баланс которого надо узнать
     * @return баланс счета
     */
    public BigDecimal balance (int accountId) {
        var account = getAccountForCurrentUserByAccountId(accountId);
        return account.balance();
    }

    public void transfer(Integer fromAccountId, Integer toAccountId, BigDecimal amount) {
        var username = currentUserService.getCurrentUsername();
        var fromAccount = accountRepo
                .findByIdAndOwnerUsernameAndRemovedDateIsNull(fromAccountId, username)
                .orElseThrow(BankAccountNotFoundException::new);
        var toAccount = accountRepo.findByIdAndRemovedDateIsNull(toAccountId)
                .orElseThrow(BankAccountNotFoundException::new);
        fromAccount.withdraw(amount);
        fromAccount.addHistoryLine(new HistoryLine("sent a transfer", LocalDateTime.now(),amount));
        toAccount.deposit(amount);
        toAccount.addHistoryLine(new HistoryLine("received a transfer", LocalDateTime.now(),amount));
        accountRepo.saveAll(Set.of(toAccount, fromAccount));
    }

    private BankAccount getAccountForCurrentUserByAccountId(int accountId) {
        //достает из контекста ник успешно аутентифицированного пользователя
        var currentUsername = currentUserService.getCurrentUsername();
        return accountRepo.findByIdAndOwnerUsernameAndRemovedDateIsNull(accountId, currentUsername)
                .orElseThrow(BankAccountNotFoundException::new);
    }
}
