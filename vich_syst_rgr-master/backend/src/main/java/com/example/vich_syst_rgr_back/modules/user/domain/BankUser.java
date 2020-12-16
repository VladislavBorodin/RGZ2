package com.example.vich_syst_rgr_back.modules.user.domain;

import com.example.vich_syst_rgr_back.core.domain.User;
import com.example.vich_syst_rgr_back.modules.bank.domain.BankAccount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * класс-представление пользователя банковской части приложение
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "bank_users")
public class BankUser extends User {

    /**все аккаунты, которые принадлежат пользователю*/
    @OneToMany(mappedBy = "owner")
    Set<BankAccount> bankAccounts;
}
