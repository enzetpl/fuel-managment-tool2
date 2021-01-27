package pl.pussy.fuelmanagmenttool2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NoResourceException extends RuntimeException {
    public NoResourceException(String message){
        super(message);
    }
}
