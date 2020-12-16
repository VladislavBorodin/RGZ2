package com.example.vich_syst_rgr_back;

import com.example.vich_syst_rgr_back.core.domain.Role;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RoleTests {
    String roleString = "TEST";
    Role role1 = new Role(roleString);
    Role role2 = new Role();

    @Test
    public void roleAccessorsTest() {
        assertEquals(role1.getAuthority(), roleString);
        role2.setAuthority(roleString);
        assertEquals(role2.getAuthority(), roleString);
    }

}
