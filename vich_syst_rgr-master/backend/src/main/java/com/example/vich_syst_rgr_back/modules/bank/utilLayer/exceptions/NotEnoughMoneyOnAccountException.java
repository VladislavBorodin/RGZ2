package com.example.vich_syst_rgr_back.modules.bank.utilLayer.exceptions;

import com.example.vich_syst_rgr_back.core.utilLayerGlobal.exceptions.ThisAppException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotEnoughMoneyOnAccountException extends ThisAppException {
}
