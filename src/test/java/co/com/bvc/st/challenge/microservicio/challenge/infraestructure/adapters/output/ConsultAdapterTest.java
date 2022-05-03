package co.com.bvc.st.challenge.microservicio.challenge.infraestructure.adapters.output;

import co.com.bvc.st.challenge.microservicio.challenge.application.ports.output.IProductPersistencePort;
import co.com.bvc.st.challenge.microservicio.challenge.infraestructure.jpa.IProductRepository;
import co.com.bvc.st.challenge.microservicio.challenge.infraestructure.jpa.ProductJPA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ConsultAdapterTest {

    @Mock
    private IProductRepository productRepository;

    @InjectMocks
    private IProductPersistencePort productPersistencePort;

    private List<ProductJPA> products;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

    }

    @Test
    void getListProducts() {
        when(productRepository.findAll()).thenReturn(products);
        assertEquals(productPersistencePort.getListProducts(),products);
    }
}