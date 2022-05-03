package co.com.bvc.st.challenge.microservicio.challenge.infraestructure.exceptions;

//El controller advice es el encargado de capturar cualquier exception

import co.com.bvc.st.challenge.microservicio.challenge.domain.exceptions.ProductException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = ProductException.class)
    public ResponseEntity<ErrorMessage> runtimeProductException(ProductException exception) {
        ErrorMessage error = ErrorMessage.builder().titleError("PRODUCT DATA ERROR").message(exception.getMessage()).code("202").build();
        return new ResponseEntity<ErrorMessage>(error, HttpStatus.ACCEPTED);
    }

    @ExceptionHandler(value = NumberFormatException.class)
    public ResponseEntity<ErrorMessage> numberFormatException(NumberFormatException exception) {
        ErrorMessage error = ErrorMessage.builder().titleError("PRODUCT DATA ERROR").message(exception.getMessage()).code("400").build();
        return new ResponseEntity<ErrorMessage>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = PersistenceExceptions.class)
    public ResponseEntity<ErrorMessage> persistenceExceptions(PersistenceExceptions exception) {
        ErrorMessage error = ErrorMessage.builder().titleError("CONEXION ERROR").message(exception.getMessage()).code("500").build();
        return new ResponseEntity<ErrorMessage>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = ProductExistingException.class)
    public ResponseEntity<ErrorMessage> productExistingException(ProductExistingException exception) {
        ErrorMessage error = ErrorMessage.builder().titleError("PRODUCT EXISTS").message(exception.getMessage()).code("202").build();
        return new ResponseEntity<ErrorMessage>(error, HttpStatus.ACCEPTED);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> MethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String, String> validationErrors = new HashMap<String, String>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldNameProduct = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            validationErrors.put(fieldNameProduct, errorMessage);
        });
        return new ResponseEntity<Map<String, String>>(validationErrors, HttpStatus.BAD_REQUEST);
    }
}
