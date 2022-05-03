package co.com.bvc.st.challenge.microservicio.challenge.application.ports.Service;

import co.com.bvc.st.challenge.microservicio.challenge.application.ports.output.IProductPersistencePort;
import co.com.bvc.st.challenge.microservicio.challenge.domain.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ProductPortImplTest {

    @Mock
    private IProductPersistencePort productPersistence;

    @InjectMocks
    private ProductPortImpl productPort;

    Product product;


    @BeforeEach
    void setUp() {
        //Inicializar mockito
        MockitoAnnotations.openMocks(this);
        product = new Product("12","bvc","Derivado");
    }

    @Test
    void createProduct() {
        when(productPersistence.createProduct(any())).thenReturn(true);
        assertTrue(productPort.createProduct("12","bvc","Derivado"));
    }
}