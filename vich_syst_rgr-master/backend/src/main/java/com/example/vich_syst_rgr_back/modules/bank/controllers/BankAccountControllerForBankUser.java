package com.example.vich_syst_rgr_back.modules.bank.controllers;

import com.example.vich_syst_rgr_back.core.servises.CurrentUserService;
import com.example.vich_syst_rgr_back.modules.bank.domain.BankAccount;
import com.example.vich_syst_rgr_back.modules.bank.domain.HistoryLine;
import com.example.vich_syst_rgr_back.modules.bank.repositories.BankAccountRepo;
import com.example.vich_syst_rgr_back.modules.bank.utilLayer.exceptions.BankAccountNotFoundException;
import com.example.vich_syst_rgr_back.modules.user.domain.BankUser;
import com.example.vich_syst_rgr_back.modules.user.repositories.BankUserRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bankUser/accounts")
@AllArgsConstructor
public class BankAccountControllerForBankUser {

    private final BankUserRepo bankUserRepo;
    private final BankAccountRepo bankAccountRepo;
    private final CurrentUserService currentUserService;

    //TODO разбить логику и инкапсулировать в другое место во всех методах

    /**
     * создает новый настроенный счет для текущего пользователя
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/newAccount")
    public void addNewBankAccount() {
        var currentBankUser = getCurrentBankUser();
        var newBankAccount = new BankAccount(
                currentBankUser,
                //Scale устанавливает 2 знака после запятой и округлять в большую сторону при третьем знаке > 5
                new BigDecimal("0").setScale(2, RoundingMode.HALF_DOWN));
        bankAccountRepo.save(newBankAccount);
    }

    /**
     * удаляет счет с определенным id, если он принадлежит текущему пользователю
     * @param id id счета
     */
    @DeleteMapping("remove{id}")
    public void removeBankAccount(@Valid @PathVariable @Min(1) Integer id) {
        var currentUsername = currentUserService.getCurrentUsername();
        var removedAccount = bankAccountRepo.findByIdAndOwnerUsernameAndRemovedDateIsNull(id, currentUsername)
                .orElseThrow(BankAccountNotFoundException::new);
        removedAccount.setRemovedDate(LocalDateTime.now());
        bankAccountRepo.save(removedAccount);
    }

    /**
     * возвращает JSON с историей действий над конкретным счетом текущего пользователя
     * @param accountId id счета
     * @return отсортированный по дате список строк истории действий над счетом
     */
    @GetMapping("historyForAccount{accountId}")
    public List<HistoryLine> historyForAccount(@Valid @PathVariable @Min(1) Integer accountId){
        var currentUsername = currentUserService.getCurrentUsername();
        //достаем из бд счет
        return bankAccountRepo.findByIdAndOwnerUsernameAndRemovedDateIsNull(accountId, currentUsername)
                .orElseThrow(BankAccountNotFoundException::new)
                //достаем из него историю
                .getHistoryLines()
                //сортируем по дате
                .stream().sorted(Comparator.comparing(HistoryLine::getDate))
                //собираем в лист
                .collect(Collectors.toList());
    }

    @GetMapping("historyForAll")
    public List<HistoryLine> historyForAll() {
        var currentUsername = currentUserService.getCurrentUsername();
        //тело ответа
        List<HistoryLine> response = new ArrayList<>();
        //все счета пользователя
        bankAccountRepo.findAllByOwnerUsernameAndRemovedDateIsNull(currentUsername)
                //перенос истории из счетов в тело ответа
                .forEach(a->response.addAll(a.getHistoryLines()));
        //сортировка по дате
        response.sort(Comparator.comparing(HistoryLine::getDate));
        return response;
    }

    // метод возврата текущего аутентифицированного BankUser
    private BankUser getCurrentBankUser() {
        //достает из контекста ник успешноаутентифицированного пользователя
        var currentUsername = currentUserService.getCurrentUsername();
        //возвращает BankUser из бд по нику
        return bankUserRepo.findByUsername(currentUsername);
    }

    @GetMapping("allId")
    public Collection<Integer> bankAccountsOfCurrentUser() {
        var currentUsername = currentUserService.getCurrentUsername();
        return bankAccountRepo.findAllByOwnerUsernameAndRemovedDateIsNull(currentUsername)
                .stream().map(BankAccount::getId).collect(Collectors.toSet());
    }
}
