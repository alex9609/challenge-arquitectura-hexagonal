package co.com.bvc.st.challenge.microservicio.challenge.infraestructure.adapters.input;


import co.com.bvc.st.challenge.microservicio.challenge.application.ports.Service.ProductPortImpl;
import co.com.bvc.st.challenge.microservicio.challenge.application.ports.input.IProductPort;
import co.com.bvc.st.challenge.microservicio.challenge.application.ports.output.IProductPersistencePort;
import co.com.bvc.st.challenge.microservicio.challenge.infraestructure.adapters.output.ProductPersistenceAdapter;
import co.com.bvc.st.challenge.microservicio.challenge.infraestructure.adapters.output.ProductPersistenceAdapterDynamo;
import co.com.bvc.st.challenge.microservicio.challenge.infraestructure.dto.ProductDTO;
import co.com.bvc.st.challenge.microservicio.challenge.infraestructure.dto.ProductDTOPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "product")
@Validated
public class ProductController {

    private IProductPort iproduct;

    private IProductPersistencePort iProductPersistencePort;

    private IProductPort iproductDynamo;

    private IProductPersistencePort iProductPersistencePortDynamo;

    public ProductController(@Qualifier("productAdapter") IProductPort iproduct,@Qualifier("productAdapter")IProductPersistencePort productPersistencePort,@Qualifier("productDynamo") IProductPort iproductDynamo,@Qualifier("productDynamo") IProductPersistencePort iProductPersistencePortDynamo) {
        this.iproduct = iproduct;
        this.iProductPersistencePort = productPersistencePort;
        this.iproductDynamo = iproductDynamo;
        this.iProductPersistencePortDynamo = iProductPersistencePortDynamo;

    }

    @PostMapping("/createProduct")
    public ResponseEntity<Map<String, String>> postProduct(@Valid @RequestBody ProductDTO productdto) throws MethodArgumentNotValidException {
        Map<String, String> result = new HashMap<>();
        boolean created = this.iproduct.createProduct(productdto.getIdProduct(), productdto.getNameProduct(), productdto.getTypeProduct());
        if (created) {
            result.put("Id producto : ", productdto.getIdProduct());
            result.put("Name product : ", productdto.getNameProduct());
            result.put("Type product : ", productdto.getTypeProduct());
            result.put("Estado : ", "Creado con exito ");
        } else {
            result.put("Product con Id producto" + productdto.getIdProduct(), "No se pudo crear");
        }
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/consult")
    public ResponseEntity<List<ProductDTOPersistence>> getProduct() {
        List<ProductDTOPersistence> products = iProductPersistencePort.getListProducts();
        products.forEach(System.out::println);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
