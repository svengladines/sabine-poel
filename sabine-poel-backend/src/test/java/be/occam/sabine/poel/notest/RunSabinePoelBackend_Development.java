package be.occam.acsi.application.run;

import org.junit.Test;

import be.occam.test.jtest.JTest;
import be.occam.utils.spring.configuration.ConfigurationProfiles;

public class RunSabinePoelBackend_Development extends JTest {
	
	public RunSabinePoelBackend_Development() {
		super( "/", 9001, ConfigurationProfiles.DEV );
	}
	
	@Test
	public void doesItSmoke() throws Exception {
		
		System.setSecurityManager( null );
		Thread.sleep( 10000000 );
		
	}

}
