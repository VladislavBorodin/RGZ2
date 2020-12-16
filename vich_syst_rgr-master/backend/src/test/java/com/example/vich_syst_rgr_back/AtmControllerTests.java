package com.example.vich_syst_rgr_back;

import com.example.vich_syst_rgr_back.modules.atm.controllers.AtmController;
import com.example.vich_syst_rgr_back.modules.atm.optional.AtmOptionalService;
import com.example.vich_syst_rgr_back.modules.user.repositories.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AtmController.class)
@RunWith(SpringRunner.class)
public class AtmControllerTests {

    @MockBean
    private AtmOptionalService atmService;
    @MockBean
    private UserRepo userRepo;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(authorities = "BANK_USER")
    public void balanceTestReturn200 () throws Exception {
        var balance = new BigDecimal(666);
        BDDMockito.given(atmService.balance(anyInt())).willReturn(balance);
        mockMvc.perform(get("/api/bankUser/atm/balance1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(authorities = "BANK_USER")
    public void depositTestReturn200 () throws Exception {
        mockMvc.perform(post("/api/bankUser/atm/deposit")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"atmId\":1, \"accountId\":1, \"toAdd\":55.56}")
        )
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(authorities = "BANK_USER")
    public void withdrawTestReturn200 () throws Exception {
        mockMvc.perform(post("/api/bankUser/atm/withdraw")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"atmId\":1, \"accountId\":1, \"toSub\":55.56}")
        )
                .andExpect(status().isOk());
    }

}
