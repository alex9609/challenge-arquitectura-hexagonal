package co.com.bvc.st.challenge.microservicio.challenge.infraestructure.adapters.output;

import co.com.bvc.st.challenge.microservicio.challenge.application.ports.output.IProductPersistencePort;
import co.com.bvc.st.challenge.microservicio.challenge.domain.model.Product;
import co.com.bvc.st.challenge.microservicio.challenge.infraestructure.dto.ProductDTOPersistence;
import co.com.bvc.st.challenge.microservicio.challenge.infraestructure.exceptions.ProductExistingException;
import co.com.bvc.st.challenge.microservicio.challenge.infraestructure.dynamo.IProductRepositoryDynamo;
import co.com.bvc.st.challenge.microservicio.challenge.infraestructure.dynamo.ProductDynamo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Qualifier("productDynamo")
public class ProductPersistenceAdapterDynamo implements IProductPersistencePort {

    @Autowired
    private IProductRepositoryDynamo productRepositoryDynamo;


    @Override
    public boolean createProduct(Product product) {
            Optional<Product> productOptional = Optional.ofNullable(product);
            Product productValidated = productOptional.orElseThrow(IllegalArgumentException::new);

            boolean existsProduct = true;
                    //validateExistingProducts(productValidated.getIdProduct());
            if (!existsProduct) {
                ProductDynamo productDynamo = new ProductDynamo();
                productDynamo.setId_product(productValidated.getIdProduct());
                productDynamo.setNameProduct(productValidated.getNameProduct());
                productDynamo.setTypeProduct(productValidated.getTypeProduct());
                productRepositoryDynamo.save(productDynamo);
            }else{
                throw new ProductExistingException("Product with id : " + product.getIdProduct() + "already exists, please try with another Id");
            }
       return true;
    }

    @Override
    public List<ProductDTOPersistence> getListProducts() {
        return null;
    }

    private boolean validateExistingProducts(int IdProduct) {
        List<ProductDynamo> products = (List<ProductDynamo>) productRepositoryDynamo.findAll();
        return products.stream().filter(x -> x.getId_product() == IdProduct).findAny().isPresent();
    }

}

