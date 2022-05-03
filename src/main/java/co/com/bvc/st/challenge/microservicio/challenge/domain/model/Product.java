package co.com.bvc.st.challenge.microservicio.challenge.domain.model;

import co.com.bvc.st.challenge.microservicio.challenge.domain.exceptions.ProductException;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Product {

    private int idProduct;
    private String nameProduct;
    private String typeProduct;

    public Product (String idProductStr, String nameProduct, String typeProduct){
        validateId(idProductStr);
        validateTypeProduct(typeProduct);
        this.nameProduct = nameProduct;
        this.typeProduct = typeProduct;
    }


    public void validateId(String idProductString){
        try{
            int idProduct = Integer.parseInt(idProductString);
            if(idProduct < 1 || idProduct > 500){
                throw new ProductException("Id product has a range invalid value");
            }
            setIdProduct(idProduct);
        }catch(NumberFormatException e){
            throw new NumberFormatException("Id product is not a number, please type a number value");
        }
    }

    public void validateTypeProduct(String typeProduct){
        if(!(typeProduct.equals("Renta fija") || typeProduct.equals("Renta variable") || typeProduct.equals("Derivado"))){
            throw new ProductException("Type of product give is invalid");
        }
    }

}
