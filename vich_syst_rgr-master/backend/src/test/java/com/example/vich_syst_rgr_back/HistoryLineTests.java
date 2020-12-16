package com.example.vich_syst_rgr_back;

import com.example.vich_syst_rgr_back.modules.bank.domain.HistoryLine;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HistoryLineTests {

    @Test
    public void historyLineTest(){
        var historyLine = new HistoryLine();
        var date = LocalDateTime.now();
        var commandType = "test";
        var money = BigDecimal.valueOf(55);
        historyLine.setDate(date);
        historyLine.setCommandType(commandType);
        historyLine.setMoney(money);
        assertEquals(historyLine.getCommandType(), commandType);
        assertEquals(historyLine.getDate(), date);
        assertEquals(historyLine.getMoney(), money);
    }

}
