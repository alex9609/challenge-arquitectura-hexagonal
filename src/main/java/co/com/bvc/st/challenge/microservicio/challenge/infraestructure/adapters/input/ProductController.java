package co.com.bvc.st.challenge.microservicio.challenge.infraestructure.adapters.input;


import co.com.bvc.st.challenge.microservicio.challenge.application.ports.input.IProductPort;
import co.com.bvc.st.challenge.microservicio.challenge.application.ports.output.IProductPersistencePort;
import co.com.bvc.st.challenge.microservicio.challenge.infraestructure.dto.ProductDTO;
import co.com.bvc.st.challenge.microservicio.challenge.infraestructure.dto.ProductDTOPersistence;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    public ProductController(IProductPort iproduct,IProductPersistencePort productPersistencePort) {
        this.iproduct = iproduct;
        this.iProductPersistencePort = productPersistencePort;
    }
    @PostMapping("/createProduct")
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
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
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public ResponseEntity<List<ProductDTOPersistence>> getProduct() {
        List<ProductDTOPersistence> products = iProductPersistencePort.getListProducts();
        products.forEach(System.out::println);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
