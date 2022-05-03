package co.com.bvc.st.challenge.microservicio.challenge;


import co.com.bvc.st.challenge.microservicio.challenge.domain.model.Product;
import co.com.bvc.st.challenge.microservicio.challenge.infraestructure.jpa.IProductRepository;
import co.com.bvc.st.challenge.microservicio.challenge.infraestructure.jpa.ProductJPA;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class MicroservicioChallengeApplicationTests {

	@Test
	void contextLoads() {
		System.out.println("hola mnudo");
	}

	@Test
	void testProductModel(){
		Product prod = new Product("1","tarjetaCredito","Renta fija");
		System.out.println(prod.toString());
	}

	@Autowired
	private IProductRepository productRepository;

	@Test
	void testListProducts(){
		ProductJPA producta = new ProductJPA(12,"lex","derivados");
		ProductJPA productb = new ProductJPA(13,"ad","renta fija");
		ProductJPA productc = new ProductJPA(16,"ed","renta variable");
		ProductJPA productd = new ProductJPA(50,"bvc","credito");

		List<ProductJPA> products = new ArrayList<>();
		products.add(producta);
		products.add(productb);
		products.add(productc);
		products.add(productd);

		boolean valor = products.stream().filter(x-> x.getIdProduct()==60).findAny().isPresent();
		System.out.println(valor);

	}

}
