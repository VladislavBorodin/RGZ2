package com.example.vich_syst_rgr_back;

import com.example.vich_syst_rgr_back.modules.atm.controllers.AtmControllerForCollector;
import com.example.vich_syst_rgr_back.modules.atm.domaint.Atm;
import com.example.vich_syst_rgr_back.modules.atm.repositories.AtmRepo;
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
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AtmControllerForCollector.class)
public class AtmControllerForCollectorTests {

    @MockBean
    private AtmRepo atmRepo;
    @MockBean
    private UserRepo userRepo;

    @Autowired
    private MockMvc mockMvc;

    @WithMockUser(authorities = {"COLLECTOR"})
    @Test
    public void addCashToAtmTestReturn200() throws Exception {
        var atm = new Atm(BigDecimal.valueOf(0));
        BDDMockito.given(atmRepo.findByIdAndRemovedDateIsNull(anyInt())).willReturn(Optional.of(atm));
        mockMvc.perform(post("/api/collector/atmCash/add")
                .content("{\"atmId\":1, \"toAdd\":55.55}")
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk());
    }

}
