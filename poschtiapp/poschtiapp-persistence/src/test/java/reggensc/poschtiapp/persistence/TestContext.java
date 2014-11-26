package reggensc.poschtiapp.persistence;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;

@Configuration
@ComponentScan(basePackages = { "reggensc.poschtiapp" })
public class TestContext {

	@Bean
	public FactoryBean<EntityManagerFactory> entityManagerFactory() {
		return new LocalEntityManagerFactoryBean();
	}
}
