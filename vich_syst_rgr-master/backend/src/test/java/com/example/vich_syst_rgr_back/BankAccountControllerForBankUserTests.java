package com.example.vich_syst_rgr_back;

import com.example.vich_syst_rgr_back.core.servises.CurrentUserService;
import com.example.vich_syst_rgr_back.modules.bank.controllers.BankAccountControllerForBankUser;
import com.example.vich_syst_rgr_back.modules.bank.domain.BankAccount;
import com.example.vich_syst_rgr_back.modules.bank.repositories.BankAccountRepo;
import com.example.vich_syst_rgr_back.modules.user.repositories.BankUserRepo;
import com.example.vich_syst_rgr_back.modules.user.repositories.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BankAccountControllerForBankUser.class)
public class BankAccountControllerForBankUserTests {

    @MockBean
    private UserRepo userRepo;
    @MockBean
    private BankUserRepo bankUserRepo;
    @MockBean
    private BankAccountRepo bankAccountRepo;
    @MockBean
    private CurrentUserService currentUserService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(authorities = {"BANK_USER"})
    public void addNewBankAccountTestReturn201() throws Exception {
        mockMvc.perform(post("/api/bankUser/accounts/newAccount"))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(authorities = {"BANK_USER"}, username = "testUser")
    public void removeBankUserAccountTestReturn200() throws Exception {
        BDDMockito.given(currentUserService.getCurrentUsername()).willReturn("testUser");
        BDDMockito.given(bankAccountRepo.findByIdAndOwnerUsernameAndRemovedDateIsNull(anyInt(),anyString()))
                .willReturn(Optional.of(new BankAccount()));
        mockMvc.perform(delete("/api/bankUser/accounts/remove1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(authorities = "BANK_USER", username = "testUser")
    public void getHistoryForAccountTestReturn200() throws Exception {
        BDDMockito.given(currentUserService.getCurrentUsername()).willReturn("testUser");
        BDDMockito.given(bankAccountRepo.findByIdAndOwnerUsernameAndRemovedDateIsNull(anyInt(),anyString()))
                .willReturn(Optional.of(new BankAccount(null, null)));
        mockMvc.perform(get("/api/bankUser/accounts/historyForAccount1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(authorities = "BANK_USER", username = "testUser")
    public void getHistoryForAllTestReturn200() throws Exception {
        BDDMockito.given(currentUserService.getCurrentUsername()).willReturn("testUser");
        BDDMockito.given(bankAccountRepo.findByIdAndOwnerUsernameAndRemovedDateIsNull(anyInt(),anyString()))
                .willReturn(Optional.of(new BankAccount(null, null)));
        mockMvc.perform(get("/api/bankUser/accounts/historyForAll"))
                .andExpect(status().isOk());
    }

}
