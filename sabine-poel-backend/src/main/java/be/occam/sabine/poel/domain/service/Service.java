package be.occam.sabine.poel.domain.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.occam.sabine.poel.web.util.DataGuard;

public abstract class Service<T extends Service> {
	
	protected final Logger logger 
		= LoggerFactory.getLogger( this.getClass() );
	
	@Resource
	protected DataGuard dataGuard; 
	
	public T guard() {
		
		this.dataGuard.guard();
		
		return (T) this;
		
	}

}
