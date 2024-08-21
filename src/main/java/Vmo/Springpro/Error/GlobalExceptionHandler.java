package Vmo.Springpro.Error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import Vmo.Springpro.Dtorequest.ApiRespone;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ApiRespone<Void>> handleAppException(AppException ex, WebRequest request) {
        ApiRespone<Void> apiResponse = new ApiRespone<>(ex.getErrorClass().getCode(), ex.getErrorClass().getMessage(), null);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiRespone<Void>> handleRuntimeException(RuntimeException ex, WebRequest request) {
        ApiRespone<Void> apiResponse = new ApiRespone<>(500, "An unexpected error occurred: " + ex.getMessage(), null);
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiRespone<Void>> handleOtherExceptions(Exception ex, WebRequest request) {
        ApiRespone<Void> apiResponse = new ApiRespone<>(666, "Sai câu lệnh", null);
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
