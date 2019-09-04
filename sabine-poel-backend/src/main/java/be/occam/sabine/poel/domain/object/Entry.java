package be.occam.sabine.poel.domain.object;

import static be.occam.utils.javax.Utils.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

import be.occam.sabine.poel.domain.object.Entry;
import be.occam.sabine.poel.web.dto.EntryDTO;
import be.occam.sabine.poel.web.dto.PersonDTO;
import be.occam.utils.timing.Timing;

public class Entry {
	
	String reason;
	String description;
	List<Person> persons;
	
	public Entry() {
		this.persons = list();
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}
	
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static EntryDTO dto( Entry from ) {
		
		EntryDTO to 
			= new EntryDTO();
		
		List<PersonDTO> persons
			= list();
		
		for ( Person p : from.getPersons() ) {
			persons.add( Person.dto( p ) );
		}
		
		to.setPersons( persons );
		
		to.setReason( from.getReason() );
		to.setDescription( from.getDescription() );
		
		return to;
		
	}
	
	public static Entry from( EntryDTO from ) {
		
		Entry to 
			= new Entry();
		
		List<Person> persons
		= list();
	
		for ( PersonDTO p : from.getPersons() ) {
			persons.add( Person.from( p ) );
		}
		
		to.setPersons( persons );
		to.setReason( from.getReason() );
		to.setDescription( from.getDescription() );
		
		return to;
		
	}
	
}
