package be.occam.sabine.poel.application.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import be.occam.sabine.poel.domain.people.MailMan;
import be.occam.sabine.poel.domain.people.Secretary;
import be.occam.sabine.poel.domain.service.EntryService;
import be.occam.sabine.poel.web.util.DataGuard;
import be.occam.sabine.poel.web.util.NoopGuard;
import be.occam.utils.spring.configuration.ConfigurationProfiles;

@Configuration
public class InAcsiApplicationConfig {
	
	final static Logger logger
		= LoggerFactory.getLogger( InAcsiApplicationConfig.class );

	final static String BASE_PKG 
		= "be.occam.sabine.poel";
	
	static class propertiesConfigurer {
		
		@Bean
		@Scope("singleton")
		public static PropertySourcesPlaceholderConfigurer propertiesConfig() {
			return new PropertySourcesPlaceholderConfigurer();
		}
		
	}
	
	@Configuration
	@Profile({ConfigurationProfiles.PRODUCTION})
	static class DomainConfigForProduction {
		
		@Bean
		DataGuard dataGuard() {
			
			return new NoopGuard();
			
		}
		
		@Bean
		String acsiEmailAddress() {
			
			return "occamsoftware.be@gmail.com"; 
			
		}
		
		@Bean
		String acsiDigitaalEmailAddress() {
			
			return "occamsoftware.be@gmail.com"; 
			
		}
		
	}
	
	@Configuration
	public static class ServiceConfigShared {
		
		@Bean
		public JavaMailSender javaMailSender () {
			
			JavaMailSenderImpl sender
				= new JavaMailSenderImpl();
			return sender;
			
		}
		
		@Bean
		public EntryService entryService( String acsiDigitaalEmailAddress, String acsiEmailAddress ) {
			return new EntryService( acsiDigitaalEmailAddress, acsiEmailAddress );
		}
		
	}
	
	@Configuration
	public static class PeopleConfigShared {
		
		@Bean
		public MailMan mailMan() {
			return new MailMan();
		}
		
		@Bean
		public Secretary secretary() {
			return new Secretary();
		}
		
	}
	
	@Configuration
	@Profile({ConfigurationProfiles.PRODUCTION, ConfigurationProfiles.DEV})
	public static class DataConfig {
		
	}
	
		
}