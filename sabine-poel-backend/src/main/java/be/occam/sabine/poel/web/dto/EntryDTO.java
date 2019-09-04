package be.occam.sabine.poel.web.dto;

import java.util.Date;
import java.util.List;
import java.util.Set;

import be.occam.sabine.poel.domain.object.Entry;
import be.occam.utils.timing.Timing;

public class EntryDTO {
	
	String reason;
	String description;
	
	List<PersonDTO> persons;

	public List<PersonDTO> getPersons() {
		return persons;
	}

	public void setPersons(List<PersonDTO> persons) {
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
	
}
