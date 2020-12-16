package com.example.vich_syst_rgr_back.modules.atm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddCashToAtmDto {
    @Min(1)
    private int atmId;
    @Min(0)
    private double toAdd;
}
