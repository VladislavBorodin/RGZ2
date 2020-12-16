package com.example.vich_syst_rgr_back.modules.bank.domain;

import com.example.vich_syst_rgr_back.modules.bank.utilLayer.exceptions.NotEnoughMoneyOnAccountException;
import com.example.vich_syst_rgr_back.modules.user.domain.BankUser;
import com.example.vich_syst_rgr_back.core.utilLayerGlobal.entitiesUtil.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * класс-сущность банковского счета
 */

@Entity
@NoArgsConstructor
@Table(name = "bank_accounts")
public class BankAccount extends BaseEntity {

    public BankAccount(BankUser owner, BigDecimal amount) {
        this.owner = owner;
        this.amount = amount;
        this.historyLines = new HashSet<>();
    }

    /**владелец счета*/
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private BankUser owner;

    @Getter
    @ElementCollection
    @JoinTable(name = "history_lines", joinColumns = @JoinColumn(name = "account_id"))
    private Set<HistoryLine> historyLines;

    /**сума на счету*/
    @Column(name = "amount")
    BigDecimal amount;


    /**
     * метод возвращения баланса счета
     * @return возвращает сумму на счету
     */
    public BigDecimal balance() {
        return amount;
    }

    /**
     * Увеличивает значение суммы на счету
     * @param toAdd сумма, которую нужно бребавить к счету
     * @return новое значение текущего счета
     */
    public BigDecimal deposit(BigDecimal toAdd) {
        this.amount = this.amount.add(toAdd);
        return this.amount;
    }

    /**
     * Уменьшает значение на счету с защитой от ухода в отрицательные значения
     * @param toSub сумма, на которую нужно уменьшить значение на счету
     * @return новое значение текущего счета
     * @throws NotEnoughMoneyOnAccountException в том случае когда нужно снять со счета больше чем на нем есть
     */
    public BigDecimal withdraw(BigDecimal toSub) {
        if (this.amount.subtract(toSub).compareTo(new BigDecimal("0")) < 0)
            throw new NotEnoughMoneyOnAccountException();
        this.amount = this.amount.subtract(toSub);
        return this.amount;
    }

    public void addHistoryLine(HistoryLine historyLine) {
        this.historyLines.add(historyLine);
    }
}
