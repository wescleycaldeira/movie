package br.com.ais.base.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class BusinessException extends WebApplicationException {

    public BusinessException(String message, Response.Status status) throws IllegalArgumentException {
        super(message, status);
    }
}
