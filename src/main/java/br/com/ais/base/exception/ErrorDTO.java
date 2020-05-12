package br.com.ais.base.exception;

import java.time.LocalDateTime;

public class ErrorDTO {

    private String description;
    private Integer code;
    private LocalDateTime momentError;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public LocalDateTime getMomentError() {
        return momentError;
    }

    public void setMomentError(LocalDateTime momentError) {
        this.momentError = momentError;
    }
}
