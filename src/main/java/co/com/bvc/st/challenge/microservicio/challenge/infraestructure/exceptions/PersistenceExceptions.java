package co.com.bvc.st.challenge.microservicio.challenge.infraestructure.exceptions;

public class PersistenceExceptions extends RuntimeException {

    public PersistenceExceptions(String message, Throwable cause) {
        super(message, cause);
    }


}
