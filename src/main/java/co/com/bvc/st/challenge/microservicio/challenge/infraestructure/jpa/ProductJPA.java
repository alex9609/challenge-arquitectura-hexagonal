package co.com.bvc.st.challenge.microservicio.challenge.infraestructure.jpa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "producto", catalog = "challenge", schema = "")
public class ProductJPA {

    @Id
    @Column(name = "id_producto")
    private Integer idProduct;

    @Column(name = "nombre")
    private String nameProduct;

    @Column(name="tipo_producto")
    private String typeProduct;
}
