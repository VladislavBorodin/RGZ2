package com.example.vich_syst_rgr_back;

import com.example.vich_syst_rgr_back.modules.user.controllers.AuthController;
import com.example.vich_syst_rgr_back.modules.user.optionalLayer.RegistrationService;
import com.example.vich_syst_rgr_back.modules.user.repositories.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AuthController.class)
public class AuthControllerTests {

    @MockBean
    private RegistrationService registrationService;
    @MockBean
    private UserRepo userRepo;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void registrationReturn201 () throws Exception {
        mockMvc.perform(post("/api/reg/bankUser")
                .content("{\"username\":\"testUser\", \"password\":\"testPass\", \"authorities\":[\"TEST1\", \"TEST2\"]}")
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated());
    }
}
