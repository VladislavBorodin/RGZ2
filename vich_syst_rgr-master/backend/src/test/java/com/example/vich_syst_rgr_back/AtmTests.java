package com.example.vich_syst_rgr_back;


import com.example.vich_syst_rgr_back.modules.atm.domaint.Atm;
import com.example.vich_syst_rgr_back.modules.atm.utilLayer.exceptions.NotEnoughMoneyOnAtmException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AtmTests {

    private final Atm atm = new Atm(new BigDecimal(100).setScale(2, RoundingMode.HALF_DOWN));
    private final BigDecimal toAdd = new BigDecimal(50).setScale(2, RoundingMode.HALF_DOWN);
    private final BigDecimal addResult = new BigDecimal(150).setScale(2, RoundingMode.HALF_DOWN);
    private final BigDecimal toSub = new BigDecimal(20).setScale(2, RoundingMode.HALF_DOWN);
    private final BigDecimal subResult = new BigDecimal(130).setScale(2, RoundingMode.HALF_DOWN);
    private final BigDecimal toSubBigValueForException = new BigDecimal(130000).setScale(2, RoundingMode.HALF_DOWN);

    @Test
    public void atmMethodsTest () {
        atm.addCash(toAdd);
        assertEquals(atm.balance().compareTo(addResult),0);
        atm.subCash(toSub);
        assertEquals(atm.balance().compareTo(subResult),0);
        assertThrows(NotEnoughMoneyOnAtmException.class, ()->atm.subCash(toSubBigValueForException));
        assertEquals(atm.balance().compareTo(subResult), 0);
    }

}
