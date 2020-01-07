package be.occam.acsi.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import be.occam.utils.spring.configuration.ConfigurationProfiles;

import com.google.appengine.tools.development.testing.LocalAppIdentityServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalMailServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

@Configuration
@Profile({ConfigurationProfiles.DEV})
public class InAcsiGoogleConfigForDev {
	
	@Configuration
	@Profile( ConfigurationProfiles.DEV )
	public static class LocalServiceConfig {
		
		@Bean
		public LocalServiceTestHelper helper() {
			
			LocalServiceTestHelper helper
			= new LocalServiceTestHelper( new LocalAppIdentityServiceTestConfig(), null, new LocalMailServiceTestConfig() );
			helper.setUp();
			
			return helper;
			
		}
		
		
	}
	
}
