package com.example.vich_syst_rgr_back;

import com.example.vich_syst_rgr_back.core.servises.CurrentUserService;
import com.example.vich_syst_rgr_back.modules.atm.domaint.Atm;
import com.example.vich_syst_rgr_back.modules.atm.optional.AtmOptionalService;
import com.example.vich_syst_rgr_back.modules.atm.repositories.AtmRepo;
import com.example.vich_syst_rgr_back.modules.atm.utilLayer.exceptions.NotEnoughMoneyOnAtmException;
import com.example.vich_syst_rgr_back.modules.bank.domain.BankAccount;
import com.example.vich_syst_rgr_back.modules.bank.repositories.BankAccountRepo;
import com.example.vich_syst_rgr_back.modules.bank.utilLayer.exceptions.NotEnoughMoneyOnAccountException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(PowerMockRunner.class)
public class AtmOptionalLayerTests {

    @InjectMocks
    private AtmOptionalService atmOptionalService;

    @Mock
    private AtmRepo atmRepo;
    @Mock
    private BankAccountRepo accountRepo;
    @Mock
    private CurrentUserService currentUserService;

    AtmOptionalLayerTests() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void depositTest() {
        var atm = new Atm(new BigDecimal(100).setScale(2, RoundingMode.HALF_DOWN));
        var currentUserAccount = new BankAccount(
                null,
                new BigDecimal(250).setScale(2, RoundingMode.HALF_DOWN));
        var atmCashAddResult = new BigDecimal(150).setScale(2, RoundingMode.HALF_DOWN);
        var accountAmountAddResult = new BigDecimal(300).setScale(2, RoundingMode.HALF_DOWN);
        Mockito.when(accountRepo.findByIdAndOwnerUsernameAndRemovedDateIsNull(Mockito.anyInt(), Mockito.anyString()))
                .thenReturn(Optional.of(currentUserAccount));
        Mockito.when(atmRepo.findByIdAndRemovedDateIsNull(Mockito.anyInt())).thenReturn(Optional.of(atm));
        Mockito.when(currentUserService.getCurrentUsername()).thenReturn("username");
        atmOptionalService.deposit(1,1,50);
        assertEquals(atm.balance().compareTo(atmCashAddResult), 0);
        assertEquals(currentUserAccount.balance().compareTo(accountAmountAddResult), 0);
    }

    @Test
    public void withdrawTestWithoutException () {
        var atm = new Atm(new BigDecimal(100).setScale(2, RoundingMode.HALF_DOWN));
        var currentUserAccount = new BankAccount(
                null,
                new BigDecimal(250).setScale(2, RoundingMode.HALF_DOWN));
        var atmCashSubResult = new BigDecimal(50).setScale(2, RoundingMode.HALF_DOWN);
        var accountAmountSubResult = new BigDecimal(200).setScale(2, RoundingMode.HALF_DOWN);
        Mockito.when(accountRepo.findByIdAndOwnerUsernameAndRemovedDateIsNull(Mockito.anyInt(), Mockito.anyString()))
                .thenReturn(Optional.of(currentUserAccount));
        Mockito.when(atmRepo.findByIdAndRemovedDateIsNull(Mockito.anyInt())).thenReturn(Optional.of(atm));
        Mockito.when(currentUserService.getCurrentUsername()).thenReturn("username");
        atmOptionalService.withdraw(1,1,50);
        assertEquals(atm.balance().compareTo(atmCashSubResult), 0);
        assertEquals(currentUserAccount.balance().compareTo(accountAmountSubResult), 0);
    }

    @Test
    public void withdrawTestWithNotEnoughMoneyOnAtmException() {
        var atm = new Atm(new BigDecimal(30).setScale(2, RoundingMode.HALF_DOWN));
        var currentUserAccount = new BankAccount(
                null,
                new BigDecimal(250).setScale(2, RoundingMode.HALF_DOWN));
        Mockito.when(accountRepo.findByIdAndOwnerUsernameAndRemovedDateIsNull(Mockito.anyInt(), Mockito.anyString()))
                .thenReturn(Optional.of(currentUserAccount));
        Mockito.when(atmRepo.findByIdAndRemovedDateIsNull(Mockito.anyInt())).thenReturn(Optional.of(atm));
        Mockito.when(currentUserService.getCurrentUsername()).thenReturn("username");
        assertThrows(
                NotEnoughMoneyOnAtmException.class,
                () -> atmOptionalService.withdraw(1, 1, 50));
    }

    @Test
    public void withdrawTestWithNotEnoughMoneyOnAccountException() {
        var atm = new Atm(new BigDecimal(100).setScale(2, RoundingMode.HALF_DOWN));
        var currentUserAccount = new BankAccount(
                null,
                new BigDecimal(30).setScale(2, RoundingMode.HALF_DOWN));
        Mockito.when(accountRepo.findByIdAndOwnerUsernameAndRemovedDateIsNull(Mockito.anyInt(), Mockito.anyString()))
                .thenReturn(Optional.of(currentUserAccount));
        Mockito.when(atmRepo.findByIdAndRemovedDateIsNull(Mockito.anyInt())).thenReturn(Optional.of(atm));
        Mockito.when(currentUserService.getCurrentUsername()).thenReturn("username");
        assertThrows(
                NotEnoughMoneyOnAccountException.class,
                () -> atmOptionalService.withdraw(1, 1, 50));
    }

    @Test
    public void balanceTest() {
        var atm = new Atm(new BigDecimal(100).setScale(2, RoundingMode.HALF_DOWN));
        var currentUserAccount = new BankAccount(
                null,
                new BigDecimal(30).setScale(2, RoundingMode.HALF_DOWN));
        Mockito.when(accountRepo.findByIdAndOwnerUsernameAndRemovedDateIsNull(Mockito.anyInt(), Mockito.anyString()))
                .thenReturn(Optional.of(currentUserAccount));
        Mockito.when(atmRepo.findByIdAndRemovedDateIsNull(Mockito.anyInt())).thenReturn(Optional.of(atm));
        Mockito.when(currentUserService.getCurrentUsername()).thenReturn("username");

        assertEquals(atmOptionalService.balance(1).compareTo(currentUserAccount.balance()),0);
    }

}
