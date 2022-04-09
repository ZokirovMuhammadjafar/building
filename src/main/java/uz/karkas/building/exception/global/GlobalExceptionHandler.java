package uz.karkas.building.exception.global;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import uz.karkas.building.exception.validator.ValidationException;
import uz.karkas.building.response.ApiErrorDto;
import uz.karkas.building.response.Data;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<Data<ApiErrorDto>>valid(ValidationException ex, WebRequest request){

        ApiErrorDto path = ApiErrorDto.builder().developerMessage(ex.getStackTrace().toString()).message(ex.getMessage()).status(HttpStatus.CONFLICT.value()).path(request.getContextPath()).build();
        return new ResponseEntity<>(new Data<>(path),HttpStatus.OK);
    }
}
