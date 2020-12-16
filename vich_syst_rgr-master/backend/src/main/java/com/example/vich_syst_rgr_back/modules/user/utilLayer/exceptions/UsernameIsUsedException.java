package com.example.vich_syst_rgr_back.modules.user.utilLayer.exceptions;

import com.example.vich_syst_rgr_back.core.utilLayerGlobal.exceptions.ThisAppException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UsernameIsUsedException extends ThisAppException {
    public UsernameIsUsedException() {
        super();
    }

    public UsernameIsUsedException(String message) {
        super(message);
    }
}
