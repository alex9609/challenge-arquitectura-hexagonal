package co.com.bvc.st.challenge.microservicio.challenge.infraestructure.exceptions;

public class ProductExistingException extends  RuntimeException{
    public ProductExistingException(String message) {
        super(message);
    }
}
