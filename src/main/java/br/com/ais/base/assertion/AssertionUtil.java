package br.com.ais.base.assertion;

import br.com.ais.base.exception.BusinessException;

import javax.ws.rs.core.Response;

public class AssertionUtil {

    public static void isTrue(Boolean condition, String message){
        if(condition) throw new BusinessException(message, Response.Status.BAD_GATEWAY);
    }

    public static void isTrue(Boolean condition, String message, Response.Status status){
        if(condition) throw new BusinessException(message, status);
    }

    public static void isFalse(Boolean condition, String message){
        if(!condition) throw new BusinessException(message, Response.Status.BAD_GATEWAY);
    }

    public static void isFalse(Boolean condition, String message, Response.Status status){
        if(!condition) throw new BusinessException(message, status);
    }

}
