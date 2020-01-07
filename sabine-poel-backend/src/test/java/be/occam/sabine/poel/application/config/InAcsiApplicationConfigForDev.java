package be.occam.acsi.application.config;

import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceProvider;

import org.datanucleus.api.jpa.PersistenceProviderImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import be.occam.acsi.web.util.DevGuard;
import be.occam.sabine.poel.web.util.DataGuard;
import be.occam.utils.spring.configuration.ConfigurationProfiles;

import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.apphosting.api.ApiProxy;

@Configuration
public class InAcsiApplicationConfigForDev {
	
	@Profile( { ConfigurationProfiles.DEV } )
	public static class ConfigForDev {
		
		@Bean
		String acsiEmailAddress() {
			
			return "sven.gladines@gmail.com"; 
			
		}
		
	}
	
	@Configuration
	@Profile( { ConfigurationProfiles.DEV } ) 
	public static class ConfigForDevelopment {
		
		@Bean
		DataGuard dataGuard( LocalServiceTestHelper helper ) {
			
			return new DevGuard( ApiProxy.getCurrentEnvironment() );
			
		}
		
	}
	
}