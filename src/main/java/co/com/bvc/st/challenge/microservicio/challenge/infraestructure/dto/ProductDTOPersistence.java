package co.com.bvc.st.challenge.microservicio.challenge.infraestructure.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTOPersistence {

    protected Integer idProduct;

    protected String nameProduct;

    protected String typeProduct;

}
