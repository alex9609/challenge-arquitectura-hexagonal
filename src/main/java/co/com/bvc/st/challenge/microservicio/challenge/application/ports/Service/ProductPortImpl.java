package co.com.bvc.st.challenge.microservicio.challenge.application.ports.Service;

import co.com.bvc.st.challenge.microservicio.challenge.application.ports.input.IProductPort;
import co.com.bvc.st.challenge.microservicio.challenge.application.ports.output.IProductPersistencePort;
import co.com.bvc.st.challenge.microservicio.challenge.domain.model.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ProductPortImpl implements IProductPort {


    private IProductPersistencePort productPersistence;

    public ProductPortImpl(@Qualifier("productAdapter") IProductPersistencePort productPersistence){
        this.productPersistence = productPersistence;
    }

    @Override
    public boolean createProduct(String idProduct, String nameProduct, String typeProduct) {
        Optional<Product> optionalProduct = Optional.ofNullable(new Product(idProduct, nameProduct, typeProduct));
        Product product = optionalProduct.orElseThrow(IllegalArgumentException::new);
        return productPersistence.createProduct(product);
    }
}
