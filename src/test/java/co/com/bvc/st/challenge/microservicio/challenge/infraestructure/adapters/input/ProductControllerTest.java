package co.com.bvc.st.challenge.microservicio.challenge.infraestructure.adapters.input;

import co.com.bvc.st.challenge.microservicio.challenge.application.ports.input.IProductPort;
import co.com.bvc.st.challenge.microservicio.challenge.application.ports.output.IProductPersistencePort;
import co.com.bvc.st.challenge.microservicio.challenge.infraestructure.dto.ProductDTO;
import co.com.bvc.st.challenge.microservicio.challenge.infraestructure.dto.ProductDTOPersistence;
import co.com.bvc.st.challenge.microservicio.challenge.infraestructure.jpa.ProductJPA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class ProductControllerTest {

    @Mock
    private IProductPort iproduct;

    @Mock
    private IProductPersistencePort iProductPersistencePort;

    @InjectMocks
    private ProductController productController;

    private ProductDTO productDTO;

    private List<ProductDTOPersistence> products;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void postProduct() throws MethodArgumentNotValidException {
        when(iproduct.createProduct(anyString(),anyString(),anyString())).thenReturn(true);

        productDTO = new ProductDTO("12","bvd","Renta fija");

        Map<String, String> result = new HashMap<>();
            result.put("Id producto : ", productDTO.getIdProduct());
            result.put("Name product : ", productDTO.getNameProduct());
            result.put("Type product : ", productDTO.getTypeProduct());
            result.put("Estado : ", "Creado con exito ");
        ResponseEntity respond = new ResponseEntity<> (result, HttpStatus.CREATED);


        assertEquals(respond,productController.postProduct(productDTO));
    }

    @Test
    void getProduct() {
        products = new ArrayList<>();
        when(iProductPersistencePort.getListProducts()).thenReturn((products));
        ResponseEntity respond = new ResponseEntity<> (products, HttpStatus.OK);
        assertEquals(respond,productController.getProduct());
    }
}