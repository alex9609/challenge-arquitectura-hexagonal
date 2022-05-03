package co.com.bvc.st.challenge.microservicio.challenge.infraestructure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class ProductDTO {

    @NotBlank
    @Size(min=1,max = 3)
    private String idProduct;

    @NotBlank
    @Size(min= 2, max = 50)
    private String nameProduct;


    @NotBlank
    private String typeProduct;

}
