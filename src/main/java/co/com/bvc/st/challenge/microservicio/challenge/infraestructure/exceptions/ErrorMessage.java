package co.com.bvc.st.challenge.microservicio.challenge.infraestructure.exceptions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorMessage {

    private String titleError;
    private String code;
    private String message;



    public ErrorMessage(String titleError, String code, String message){
        this.titleError = titleError;
        this.code = code;
        this.message = message;
    }
}
