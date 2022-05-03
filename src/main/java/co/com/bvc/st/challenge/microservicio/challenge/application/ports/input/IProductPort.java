package co.com.bvc.st.challenge.microservicio.challenge.application.ports.input;

//Interfaz que hace permite la entrada de la información
public interface IProductPort {

    boolean createProduct(String idProduct, String nameProduct, String typeProduct);
}
