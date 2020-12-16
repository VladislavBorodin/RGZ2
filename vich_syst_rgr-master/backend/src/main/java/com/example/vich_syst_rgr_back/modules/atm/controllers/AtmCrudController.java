package com.example.vich_syst_rgr_back.modules.atm.controllers;

import com.example.vich_syst_rgr_back.modules.atm.domaint.Atm;
import com.example.vich_syst_rgr_back.modules.atm.repositories.AtmRepo;
import com.example.vich_syst_rgr_back.modules.atm.utilLayer.exceptions.AtmNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Контроллер, предоставляющий возможность инкасатору добовлять новые и убирать старые банкоматы
 * для доступа к контроллеру необходима роль "COLLECTOR"
 */

@RestController
@AllArgsConstructor
@RequestMapping("api/collector/atm")
@Validated
public class AtmCrudController {

    private final AtmRepo atmRepo;

    /**
     * принимает запрос без тела на создание нового банкомата
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createAtm() {
        var atm = new Atm(new BigDecimal("0").setScale(2, RoundingMode.HALF_DOWN));
        atmRepo.save(atm);
    }

    /**
     * принимает запрос без тела на удаление конкретного банкомата по id
     * @param id банкомата подлежащего удалению
     */
    @DeleteMapping("/{id}")
    public void deleteAtm(@Valid @PathVariable @Min(1) int id) {
        var atm = atmRepo.findByIdAndRemovedDateIsNull(id)
                .orElseThrow(AtmNotFoundException::new);
        atm.setRemovedDate(LocalDateTime.now());
        atmRepo.save(atm);
    }

    @GetMapping("allId")
    public Collection<Integer> allAtmId() {
        return atmRepo.findAllByRemovedDateIsNull()
                .stream().map(Atm::getId).collect(Collectors.toSet());
    }
}
