package com.example.vich_syst_rgr_back;

import com.example.vich_syst_rgr_back.modules.bank.domain.BankAccount;
import com.example.vich_syst_rgr_back.modules.bank.utilLayer.exceptions.NotEnoughMoneyOnAccountException;
import com.example.vich_syst_rgr_back.modules.user.domain.BankUser;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTests {
    @Test
    public void bankAccountTest() {
        var testUser = new BankUser();
        var amount = new BigDecimal("111");
        var toAdd = new BigDecimal("9");
        var toSub = new BigDecimal("20");
        var result1 = new BigDecimal("120");
        var result2 = new BigDecimal("100");

        var bankAccount = new BankAccount(testUser, amount);
        assertEquals(bankAccount.balance().compareTo(amount), 0);
        assertEquals(bankAccount.deposit(toAdd).compareTo(result1), 0);
        assertEquals(bankAccount.withdraw(toSub).compareTo(result2), 0);
        assertThrows(NotEnoughMoneyOnAccountException.class, ()->bankAccount.withdraw(result1));
    }

}
