package com.example.vich_syst_rgr_back.modules.atm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class AtmTransferDto {
    @Min(1)
    private final Integer fromAccountId;
    @Min(1)
    private final Integer toAccountId;
    @Min(0)
    private final BigDecimal amount;
}
