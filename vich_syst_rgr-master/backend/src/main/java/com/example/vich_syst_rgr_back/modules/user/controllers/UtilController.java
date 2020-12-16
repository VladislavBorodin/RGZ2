package com.example.vich_syst_rgr_back.modules.user.controllers;

import com.example.vich_syst_rgr_back.modules.user.dtos.BankUserView;
import com.example.vich_syst_rgr_back.modules.user.repositories.BankUserRepo;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/user/util")
public class UtilController {

    private final BankUserRepo userRepo;

    @GetMapping("allBankUsers")
    public List<BankUserView> getAllUsersId() {
        return userRepo.findAllByRemovedDateIsNull();
    }

}
