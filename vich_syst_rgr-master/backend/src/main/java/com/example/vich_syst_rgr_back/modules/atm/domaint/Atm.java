package com.example.vich_syst_rgr_back.modules.atm.domaint;

import com.example.vich_syst_rgr_back.core.utilLayerGlobal.entitiesUtil.BaseEntity;
import com.example.vich_syst_rgr_back.modules.atm.utilLayer.exceptions.NotEnoughMoneyOnAtmException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Класс-представление банкомата
 */

@Entity
@Table(name = "atms")
@AllArgsConstructor
@NoArgsConstructor
public class Atm extends BaseEntity {

    /**деньги в хранилище банкомата*/
    @Column(name = "cash")
    private BigDecimal cash;

    public BigDecimal balance () {
        return cash;
    }

    public BigDecimal addCash (BigDecimal toAdd) {
        cash = cash.add(toAdd);
        return cash;
    }

    public BigDecimal subCash (BigDecimal toSub) {
        var temp = cash.subtract(toSub);
        if (temp.compareTo(new BigDecimal("0")) < 0)
            throw new NotEnoughMoneyOnAtmException();
        cash = temp;
        return cash;
    }

}
