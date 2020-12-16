package com.example.vich_syst_rgr_back.modules.atm.repositories;

import com.example.vich_syst_rgr_back.modules.atm.domaint.Atm;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface AtmRepo extends CrudRepository<Atm, Integer> {
    Optional<Atm> findByIdAndRemovedDateIsNull(Integer integer);

    Collection<Atm> findAllByRemovedDateIsNull();
}
