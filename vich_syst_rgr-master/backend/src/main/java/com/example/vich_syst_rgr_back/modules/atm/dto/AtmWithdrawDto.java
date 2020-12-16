package com.example.vich_syst_rgr_back.modules.atm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AtmWithdrawDto {
    @Min(1)
    private int atmId;
    @Min(1)
    private int accountId;
    @Min(0)
    private BigDecimal toSub;
}