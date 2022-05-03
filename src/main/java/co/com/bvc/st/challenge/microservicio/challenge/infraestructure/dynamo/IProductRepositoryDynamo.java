package co.com.bvc.st.challenge.microservicio.challenge.infraestructure.dynamo;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.inject.Qualifier;

@EnableScan
@Repository
public interface IProductRepositoryDynamo extends CrudRepository<ProductDynamo,Integer> {
}
