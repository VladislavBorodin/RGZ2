package com.example.vich_syst_rgr_back.modules.atm.controllers;

import com.example.vich_syst_rgr_back.modules.atm.dto.AddCashToAtmDto;
import com.example.vich_syst_rgr_back.modules.atm.repositories.AtmRepo;
import com.example.vich_syst_rgr_back.modules.atm.utilLayer.exceptions.AtmNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * класс-контроллер предоставляющий пользователю - инкассатору возможность взаимодейтсвовать с банкоматом
 */

@RestController
@AllArgsConstructor
@RequestMapping("api/collector/atmCash")
@Validated
public class AtmControllerForCollector {

    private final AtmRepo atmRepo;

    /**
     * метод принимает запрос на добавление инкассатором конкретной суммы в конкретный банкомат
     * @param dto преобразованное в объек класса @see AddCashToAtmDto JSON-тело запроса
     */

    @PostMapping("add")
    public void addCashToAtm(@Valid @RequestBody AddCashToAtmDto dto) {
        var atm = atmRepo.findByIdAndRemovedDateIsNull(dto.getAtmId())
                .orElseThrow(AtmNotFoundException::new);
        var toAdd = BigDecimal.valueOf(dto.getToAdd()).setScale(2, RoundingMode.HALF_DOWN);
        atm.addCash(toAdd);
        atmRepo.save(atm);
    }

}
