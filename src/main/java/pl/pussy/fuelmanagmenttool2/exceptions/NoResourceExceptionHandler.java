package pl.pussy.fuelmanagmenttool2.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class NoResourceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoResourceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleException(NoResourceException ex, WebRequest request) {
        ErrorResponse.ErrorDetails error = new ErrorResponse.ErrorDetails();
        error.setFieldName("value");
        error.setMessage(ex.getMessage());
        ErrorResponse response = new ErrorResponse();
        response.setErrors(Collections.singletonList(error));
        return response;
    }
}
