package com.example.vich_syst_rgr_back;

import com.example.vich_syst_rgr_back.modules.user.dtos.RegisterUserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


@JsonTest
public class RegisterUserDtoTests {

    //помечается как ошибка, но работает
    //скорее всего глюк препроцессора аннотаций
    @Autowired
    private JacksonTester<RegisterUserDto> json;

    String userName = "testUser";

    String password = "testPass";
    String auth1 = "TEST1";

    String auth2 = "TEST2";

    Set<String> authorities = Set.of(auth1,auth2);

    RegisterUserDto dto = new RegisterUserDto(userName, password, authorities);

    @Test
    //в целом не знаю где такое покрытие может пригодиться, но в копилку опыта норм
    public void jsonToDtoTest () throws IOException {
        File file = new File("src/test/resources/user-test-json.json");
        RegisterUserDto dto = this.json.read(file).getObject();
        assertEquals(dto.getUsername(), userName);
        assertEquals(dto.getPassword(), password);
        assertEquals(dto.getAuthorities(),authorities);
    }



}
