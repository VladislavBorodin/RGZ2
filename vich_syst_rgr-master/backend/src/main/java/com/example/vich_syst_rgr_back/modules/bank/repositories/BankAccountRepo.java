package com.example.vich_syst_rgr_back.modules.bank.repositories;

import com.example.vich_syst_rgr_back.modules.bank.domain.BankAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankAccountRepo extends CrudRepository<BankAccount, Integer> {

    Optional<BankAccount> findByIdAndOwnerUsernameAndRemovedDateIsNull(Integer id, String ownerUsername);

    Optional<BankAccount> findByIdAndRemovedDateIsNull(Integer id);

    List<BankAccount> findAllByOwnerUsernameAndRemovedDateIsNull(String owner_username);
}
