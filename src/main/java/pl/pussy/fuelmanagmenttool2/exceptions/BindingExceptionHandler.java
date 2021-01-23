package pl.pussy.fuelmanagmenttool2.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class BindingExceptionHandler {
    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleException(BindException ex) {
        List<FieldError> errors = ex.getBindingResult().getFieldErrors();
        List<ErrorResponse.ErrorDetails> errorDetails = new ArrayList<>();
        for(FieldError fieldError: errors) {
            ErrorResponse.ErrorDetails error = new ErrorResponse.ErrorDetails();
            error.setFieldName(fieldError.getField());
            error.setMessage(fieldError.getDefaultMessage());
            errorDetails.add(error);
        }

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrors(errorDetails);
        return errorResponse;
    }

}
