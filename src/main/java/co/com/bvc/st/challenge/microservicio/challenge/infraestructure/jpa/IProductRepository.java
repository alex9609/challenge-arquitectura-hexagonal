package co.com.bvc.st.challenge.microservicio.challenge.infraestructure.jpa;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends CrudRepository<ProductJPA,Integer> {

}
