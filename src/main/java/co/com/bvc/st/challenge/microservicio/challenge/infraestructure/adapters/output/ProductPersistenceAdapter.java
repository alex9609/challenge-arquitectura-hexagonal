package co.com.bvc.st.challenge.microservicio.challenge.infraestructure.adapters.output;

import co.com.bvc.st.challenge.microservicio.challenge.application.ports.output.IProductPersistencePort;
import co.com.bvc.st.challenge.microservicio.challenge.domain.model.Product;
import co.com.bvc.st.challenge.microservicio.challenge.infraestructure.dto.ProductDTO;
import co.com.bvc.st.challenge.microservicio.challenge.infraestructure.dto.ProductDTOPersistence;
import co.com.bvc.st.challenge.microservicio.challenge.infraestructure.exceptions.ProductExistingException;
import co.com.bvc.st.challenge.microservicio.challenge.infraestructure.jpa.IProductRepository;
import co.com.bvc.st.challenge.microservicio.challenge.infraestructure.jpa.ProductJPA;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProductPersistenceAdapter implements IProductPersistencePort {

    @Autowired
    private IProductRepository productRepository;

    @Override
    public boolean createProduct(Product product) {

            Optional<Product> productOptional = Optional.ofNullable(product);
            Product productValidated = productOptional.orElseThrow(IllegalArgumentException::new);

            boolean existsProduct = true;
                   validateExistingProducts(productValidated.getIdProduct());
            if (!existsProduct) {
                ProductJPA productJPA = new ProductJPA();
                productJPA.setIdProduct(productValidated.getIdProduct());
                productJPA.setNameProduct(productValidated.getNameProduct());
                productJPA.setTypeProduct(productValidated.getTypeProduct());
                productRepository.save(productJPA);
            }else{
                throw new ProductExistingException("Product with id : " + product.getIdProduct() + "already exists, please try with another Id");
            }
        return true;
    }

    @Override
    public List<ProductDTOPersistence> getListProducts() {
        List<ProductDTOPersistence> listProducts = new ArrayList<>();
        List<ProductJPA> values = (List<ProductJPA>)productRepository.findAll();
        values.forEach(x -> listProducts.add(new ProductDTOPersistence(x.getIdProduct(),x.getNameProduct(),x.getTypeProduct())));
        return listProducts;
    }

    private boolean validateExistingProducts(int IdProduct) {
        List<ProductJPA> products = (List<ProductJPA>) productRepository.findAll();
        return products.stream().filter(x -> x.getIdProduct() == IdProduct).findAny().isPresent();
    }
}
