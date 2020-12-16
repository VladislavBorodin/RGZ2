package com.example.vich_syst_rgr_back.modules.user.repositories;

import com.example.vich_syst_rgr_back.core.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends CrudRepository<User, Integer> {
    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);


}
