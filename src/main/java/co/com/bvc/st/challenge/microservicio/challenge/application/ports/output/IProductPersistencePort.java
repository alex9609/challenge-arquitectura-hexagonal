package co.com.bvc.st.challenge.microservicio.challenge.application.ports.output;

import co.com.bvc.st.challenge.microservicio.challenge.domain.model.Product;
import co.com.bvc.st.challenge.microservicio.challenge.infraestructure.dto.ProductDTOPersistence;
import java.util.List;

//Interfaz que sirve de puente entre la capa application y la capa de persistencia
public interface IProductPersistencePort {

    boolean createProduct(Product product);

    List<ProductDTOPersistence> getListProducts();
}
