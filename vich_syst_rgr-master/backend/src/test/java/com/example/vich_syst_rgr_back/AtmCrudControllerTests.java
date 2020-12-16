package com.example.vich_syst_rgr_back;

import com.example.vich_syst_rgr_back.modules.atm.controllers.AtmCrudController;
import com.example.vich_syst_rgr_back.modules.atm.domaint.Atm;
import com.example.vich_syst_rgr_back.modules.atm.repositories.AtmRepo;
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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AtmCrudController.class)
public class AtmCrudControllerTests {

    @MockBean
    private AtmRepo atmRepo;
    @MockBean
    private UserRepo userRepo;

    @Autowired
    private MockMvc mockMvc;


    @WithMockUser(authorities = {"COLLECTOR"})
    @Test
    public void createAtmReturn201 () throws Exception {
        mockMvc.perform(post("/api/collector/atm"))
                .andExpect(status().isCreated()).andReturn();
    }

    @WithMockUser(authorities = {"COLLECTOR"})
    @Test
    public void deleteAtmReturn200 () throws Exception {
        var atm = new Atm();
        assertNull(atm.getRemovedDate());
        BDDMockito.given(atmRepo.findByIdAndRemovedDateIsNull(any())).willReturn(Optional.of(atm));
        mockMvc.perform(delete("/api/collector/atm/1"))
                .andExpect(status().isOk());
        assertNotNull(atm.getRemovedDate());
    }
}
