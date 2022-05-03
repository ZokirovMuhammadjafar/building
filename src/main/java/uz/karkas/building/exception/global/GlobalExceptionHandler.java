package uz.karkas.building.exception.global;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import uz.karkas.building.exception.NotFoundException;
import uz.karkas.building.exception.UserAlreadyTaken;
import uz.karkas.building.exception.UserNotFoundException;
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

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<Data<ApiErrorDto>> notFound(NotFoundException ex, WebRequest request){
        ApiErrorDto path = ApiErrorDto.builder().developerMessage(ex.getMessage()).status(HttpStatus.NOT_FOUND.value()).path(request.getContextPath()).build();
        return new ResponseEntity<>(new Data<>(path), HttpStatus.OK);
    }
    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Data<ApiErrorDto>>run(RuntimeException e,WebRequest request){
            ApiErrorDto path = ApiErrorDto.builder().developerMessage(e.getMessage()).status(HttpStatus.NOT_FOUND.value()).path(request.getContextPath()).build();
        return new ResponseEntity<>(new Data<>(path), HttpStatus.OK);
    }
    @ExceptionHandler({UserAlreadyTaken.class})
    public ResponseEntity<Data<ApiErrorDto>>run(UserAlreadyTaken e,WebRequest request){
        ApiErrorDto path = ApiErrorDto.builder().developerMessage(e.getMessage()).status(HttpStatus.NOT_FOUND.value()).path(request.getContextPath()).build();
        return new ResponseEntity<>(new Data<>(path), HttpStatus.OK);
    }
    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<Data<ApiErrorDto>>run(UserNotFoundException e,WebRequest request){
            ApiErrorDto path = ApiErrorDto.builder().developerMessage(e.getMessage()).status(HttpStatus.NOT_FOUND.value()).path(request.getContextPath()).build();
        return new ResponseEntity<>(new Data<>(path), HttpStatus.OK);
    }

}
