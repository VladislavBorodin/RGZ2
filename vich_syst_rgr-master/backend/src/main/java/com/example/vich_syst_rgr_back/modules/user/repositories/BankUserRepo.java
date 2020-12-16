package com.example.vich_syst_rgr_back.modules.user.repositories;

import com.example.vich_syst_rgr_back.modules.user.domain.BankUser;
import com.example.vich_syst_rgr_back.modules.user.dtos.BankUserView;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BankUserRepo extends CrudRepository<BankUser, Integer> {

    BankUser findByUsername(String username);

    List<BankUserView> findAllByRemovedDateIsNull();

}
