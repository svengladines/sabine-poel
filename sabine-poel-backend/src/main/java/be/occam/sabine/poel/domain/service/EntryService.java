package be.occam.sabine.poel.domain.service;

import static be.occam.utils.javax.Utils.list;
import static be.occam.utils.spring.web.Controller.response;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import be.occam.sabine.poel.domain.object.Entry;
import be.occam.sabine.poel.domain.object.Person;
import be.occam.sabine.poel.domain.people.MailMan;
import be.occam.sabine.poel.domain.people.Secretary;
import be.occam.sabine.poel.web.dto.EntryDTO;
import be.occam.sabine.poel.web.dto.PersonDTO;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class EntryService {
	
	protected final Logger logger
		= LoggerFactory.getLogger( this.getClass() );
	
	@Resource
	protected JavaMailSender javaMailSender;
	
	@Resource
	protected MailMan mailMan;
	
	@Resource
	protected Secretary secretary;
	
	protected final String robotEmailAddress;
	protected final String clientEmailAddress;
	protected final String occamEmailAddress;
	
	public EntryService( String robotEmailAddress, String clientEmailAddress, String occamEmailAddress ) {
		this.robotEmailAddress = robotEmailAddress;
		this.clientEmailAddress = clientEmailAddress;
		this.occamEmailAddress = occamEmailAddress;
		
		logger.info( "entry service started, robot email address is [{}], client email address is [{}], occam email address = [{}]", this.robotEmailAddress, this.clientEmailAddress, this.occamEmailAddress );
	}
	
	public ResponseEntity<EntryDTO> accept( EntryDTO entryDTO) {
		
		logger.info( "accept [{}]", entryDTO );
		
		Entry entry
			= Entry.from( entryDTO );
		
		MimeMessage message
			= this.formatEntryReceivedMessage( entry, this.clientEmailAddress );
		
		this.mailMan.deliver( message );
		
		MimeMessage messageForSven
			= this.formatEntryReceivedForSvenMessage( entry, this.occamEmailAddress );
		
		this.mailMan.deliver( messageForSven );
		
		return response( entryDTO, HttpStatus.CREATED );
			
	}
	
	protected MimeMessage formatEntryReceivedMessage( Entry entry, String... recipients ) {
		
		MimeMessage message
			= null;
			
		Configuration cfg 
			= new Configuration();
		
		String templateID
			= "/templates/to-sabine/entry-received.tmpl";
		
		try {
			
			InputStream tis
				= this.getClass().getResourceAsStream( templateID );
			
			Template template 
				= new Template( templateID, new InputStreamReader( tis ), cfg );
			
			Map<String, Object> model = new HashMap<String, Object>();
					
			model.put( "entry", entry );
			
			StringWriter bodyWriter 
				= new StringWriter();
			
			template.process( model , bodyWriter );
			
			bodyWriter.flush();
				
			message = this.javaMailSender.createMimeMessage( );
			// SGL| GAE does not support multipart_mode_mixed_related (default, when flag true is set)
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_NO, "utf-8");
				
			helper.setFrom( this.robotEmailAddress );
			helper.setTo( recipients );
			
			StringBuilder subject
				= new StringBuilder( "Nieuwe aanmelding: ");
		
			for ( Person person : entry.getPersons() ) {
				subject.append( person.getName() );
				subject.append( "," );
			}
		
			helper.setSubject( subject.toString().substring( 0, subject.length() - 1 ) );
				
			String text
				= bodyWriter.toString();
				
			logger.info( "email text is [{}]", text );
				
			helper.setText(text, true);
			
		}
		catch( Exception e ) {
			logger.warn( "could not create e-mail", e );
			throw new RuntimeException( e );
		}
		
		return message;
    	
    }
	
	protected MimeMessage formatEntryReceivedForSvenMessage( Entry entry, String... recipients ) {
		
		MimeMessage message
			= null;
			
		Configuration cfg 
			= new Configuration();
		
		String templateID
			= "/templates/to-sven/entry-received.tmpl";
		
		try {
			
			InputStream tis
				= this.getClass().getResourceAsStream( templateID );
			
			Template template 
				= new Template( templateID, new InputStreamReader( tis ), cfg );
			
			Map<String, Object> model = new HashMap<String, Object>();
					
			model.put( "entry", entry );
			
			StringWriter bodyWriter 
				= new StringWriter();
			
			template.process( model , bodyWriter );
			
			bodyWriter.flush();
				
			message = this.javaMailSender.createMimeMessage( );
			// SGL| GAE does not support multipart_mode_mixed_related (default, when flag true is set)
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_NO, "utf-8");
				
			helper.setFrom( this.robotEmailAddress );
			helper.setTo( recipients );
			
			StringBuilder subject
				= new StringBuilder( "Nieuwe aanmelding: ");
			
			for ( Person person : entry.getPersons() ) {
				subject.append( person.getName().substring(0, Math.min(3, person.getName().length())) );
				subject.append( "," );
			}
			
			helper.setSubject( subject.toString().substring( 0, subject.length() - 1 ) );
				
			String text
				= bodyWriter.toString();
				
			logger.info( "email text is [{}]", text );
				
			helper.setText(text, true);
			
		}
		catch( Exception e ) {
			logger.warn( "could not create e-mail", e );
			throw new RuntimeException( e );
		}
		
		return message;
    	
    }

}
