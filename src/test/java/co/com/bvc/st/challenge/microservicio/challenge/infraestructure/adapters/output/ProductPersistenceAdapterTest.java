package co.com.bvc.st.challenge.microservicio.challenge.infraestructure.adapters.output;

import co.com.bvc.st.challenge.microservicio.challenge.domain.model.Product;
import co.com.bvc.st.challenge.microservicio.challenge.infraestructure.exceptions.ProductExistingException;
import co.com.bvc.st.challenge.microservicio.challenge.infraestructure.jpa.IProductRepository;
import co.com.bvc.st.challenge.microservicio.challenge.infraestructure.jpa.ProductJPA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ProductPersistenceAdapterTest {

    @Mock
    private IProductRepository productRepository;

    @InjectMocks
    private ProductPersistenceAdapter productPersistenceAdapter;

    private ProductJPA productJPA;

    private Product product;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createProduct() {
        when(productRepository.save(any())).thenReturn(new ProductJPA());
        product = new Product("12","bvc","Derivado");
        assertTrue(productPersistenceAdapter.createProduct(product));

    }

}