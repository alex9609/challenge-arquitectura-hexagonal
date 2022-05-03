package co.com.bvc.st.challenge.microservicio.challenge;

import co.com.bvc.st.challenge.microservicio.challenge.application.ports.Service.ProductPortImpl;
import co.com.bvc.st.challenge.microservicio.challenge.application.ports.input.IProductPort;
import co.com.bvc.st.challenge.microservicio.challenge.application.ports.output.IProductPersistencePort;
import co.com.bvc.st.challenge.microservicio.challenge.infraestructure.adapters.input.ProductController;
import co.com.bvc.st.challenge.microservicio.challenge.infraestructure.adapters.output.ProductPersistenceAdapter;
//import co.com.bvc.st.challenge.microservicio.challenge.infraestructure.adapters.output.ProductPersistenceAdapterDynamo;
import co.com.bvc.st.challenge.microservicio.challenge.infraestructure.adapters.output.ProductPersistenceAdapterDynamo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages={"co.com.bvc.st.challenge.microservicio.challenge"})
@EnableJpaRepositories(basePackages="co.com.bvc.st.challenge.microservicio.challenge.infraestructure.jpa")
@EnableTransactionManagement
@EntityScan(basePackages="co.com.bvc.st.challenge.microservicio.challenge.infraestructure.jpa")
public class MicroservicioChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioChallengeApplication.class, args);

		IProductPersistencePort productPersistencePort = new ProductPersistenceAdapter();
		IProductPort productCause = new ProductPortImpl(productPersistencePort);

		IProductPersistencePort productPersistencePortDynamo = new ProductPersistenceAdapterDynamo();
		IProductPort productCauseDynamo = new ProductPortImpl(productPersistencePort);

		ProductController productController = new ProductController(productCause,productPersistencePort,productCauseDynamo,productPersistencePortDynamo);
	}

}
