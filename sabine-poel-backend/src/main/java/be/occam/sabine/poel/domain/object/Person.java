package be.occam.sabine.poel.domain.object;

import java.util.Date;
import java.util.List;
import java.util.Set;

import be.occam.sabine.poel.domain.object.Entry;
import be.occam.sabine.poel.web.dto.PersonDTO;
import be.occam.utils.timing.Timing;

public class Person {
	
	String name;
	String telephone;
	String email;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public static PersonDTO dto( Person from ) {
		
		PersonDTO to 
			= new PersonDTO();
		
		to.setName( from.getName() );
		to.setEmail( from.getEmail() );
		to.setTelephone( from.getTelephone() );
		
		return to;
		
	}
	
	public static Person from( PersonDTO from ) {
		
		Person to 
			= new Person();
		
		to.setName( from.getName() );
		to.setEmail( from.getEmail() );
		to.setTelephone( from.getTelephone() );
		
		return to;
		
	}
	
}
