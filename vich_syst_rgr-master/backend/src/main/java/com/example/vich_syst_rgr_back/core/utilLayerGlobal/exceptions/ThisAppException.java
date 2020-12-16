package com.example.vich_syst_rgr_back.core.utilLayerGlobal.exceptions;

public class ThisAppException extends RuntimeException {
    public ThisAppException() {
        super();
    }

    public ThisAppException(String message) {
        super(message);
    }

    public ThisAppException(String message, Throwable cause) {
        super(message, cause);
    }

    public ThisAppException(Throwable cause) {
        super(cause);
    }

    protected ThisAppException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
