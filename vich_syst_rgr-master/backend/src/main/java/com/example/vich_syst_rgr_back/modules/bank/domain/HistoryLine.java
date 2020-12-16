package com.example.vich_syst_rgr_back.modules.bank.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "history_lines")
@Setter
@Getter
public class HistoryLine {

    @Column(name = "command_type")
    private String commandType;
    @Column(name = "date")
    private LocalDateTime date;
    @Column(name = "money")
    private BigDecimal money;

}
