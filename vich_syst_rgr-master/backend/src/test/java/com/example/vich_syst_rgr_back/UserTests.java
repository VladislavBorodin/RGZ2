package com.example.vich_syst_rgr_back;

import com.example.vich_syst_rgr_back.core.domain.Role;
import com.example.vich_syst_rgr_back.core.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class UserTests {
    String userName = "testUser";
    String password = "testPass";

    Role role1 = new Role("TEST1");
    Role role2 = new Role("TEST2");
    Set<Role> roles = Set.of(role1, role2);

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    String passwordHash = encoder.encode(password);
    User user = new User(userName, passwordHash, roles);

    @Test
    public void notUsedDetailServiceMethodsCheck() {
        assertTrue(user.isEnabled());
        assertTrue(user.isAccountNonExpired());
        assertTrue(user.isAccountNonLocked());
        assertTrue(user.isCredentialsNonExpired());
    }

    @Test
    public void userAccessorsTest() {
        assertEquals(user.getAuthorities(), roles);
        assertEquals(user.getPassword(), passwordHash);
        assertEquals(user.getUsername(), userName);

        User newUser = new User();
        newUser.setPassword(passwordHash);
        newUser.setUsername(userName);
        newUser.setAuthorities(roles);

        //TODO даты пока не трогаю, ибо там автозаплонение при записи в базу,
        // и с их тестированием надо еще подумать что делать
        assertEquals(newUser.getAuthorities(), roles);
        assertEquals(newUser.getPassword(), passwordHash);
        assertEquals(newUser.getUsername(), userName);
    }

}
