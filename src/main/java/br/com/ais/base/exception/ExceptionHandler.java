package br.com.ais.base.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.time.LocalDateTime;

@Provider
public class ExceptionHandler implements ExceptionMapper<WebApplicationException> {
    @Override
    public Response toResponse(WebApplicationException exception) {
        ErrorDTO errorDTO = new ErrorDTO();

        errorDTO.setDescription(exception.getMessage());
        errorDTO.setCode(exception.getResponse().getStatus());
        errorDTO.setMomentError(LocalDateTime.now());

        return Response
                .status(exception.getResponse().getStatus())
                .entity(errorDTO)
                .build();
    }
}
