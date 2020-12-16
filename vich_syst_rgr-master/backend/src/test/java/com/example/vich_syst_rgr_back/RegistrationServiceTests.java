package com.example.vich_syst_rgr_back;

import com.example.vich_syst_rgr_back.modules.user.domain.BankUser;
import com.example.vich_syst_rgr_back.modules.user.dtos.RegisterBankUserDto;
import com.example.vich_syst_rgr_back.modules.user.optionalLayer.RegistrationService;
import com.example.vich_syst_rgr_back.modules.user.repositories.BankUserRepo;
import com.example.vich_syst_rgr_back.modules.user.repositories.UserRepo;
import com.example.vich_syst_rgr_back.modules.user.utilLayer.exceptions.UsernameIsUsedException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationServiceTests {

    @InjectMocks
    private RegistrationService registrationService;

    @Mock
    private UserRepo userRepo;
    @Mock
    private BankUserRepo bankUserRepo;
    @Spy
    private PasswordEncoder encoder;

    public RegistrationServiceTests () {
        MockitoAnnotations.initMocks(this);
    }

    private final BankUser bankUser = new BankUser();

    private final String username = "username";
    private final String password = "password";
    private final RegisterBankUserDto dto = new RegisterBankUserDto(username,password);

    @Test
    public void registerBankUserTestWithoutExceptions () {
        Mockito.when(userRepo.existsByUsername(username)).thenReturn(false);
        assertDoesNotThrow(()->registrationService.registerBankUser(dto));
    }

    @Test
    public void registerBankUserTestThrowUsernameIsUsedExceptions () {
        Mockito.when(userRepo.existsByUsername(username)).thenReturn(true);
        assertThrows(UsernameIsUsedException.class, ()->registrationService.registerBankUser(dto));
    }

}
