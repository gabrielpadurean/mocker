package org.mocker.validation.mapping;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mocker.domain.Mapping;
import org.mocker.domain.Request;
import org.mocker.domain.Response;
import org.mocker.exception.MissingFieldException;

/**
 * @author gabrielpadurean
 */
public class MappingFieldValidatorTest {
	private MappingFieldValidator victim = new MappingFieldValidator();
	
	
	@Test
	public void testValidMapping() throws Exception {
		Mapping mapping = new Mapping();
		Request request = new Request();
		Response response = new Response();

		request.setEndpoint("/test");
		request.setMethod("GET");
		
		response.setStatus(200);
		response.setBody("Test");
		
		mapping.setRequest(request);
		mapping.setResponse(response);
		
		victim.validate(mapping);
	}
	
	@Test
	public void testInvalidMapping() {
		testRequestRequired();
		testRequestEndpointRequired();
		testRequestMethodRequired();
		testResponseRequired();
		testResponseStatusRequired();
		testResponseBodyRequired();
	}
	
	private void testRequestRequired() {
		try {
			victim.validate(new Mapping());
		} catch (MissingFieldException e) {
			assertEquals("Request is required", e.getMessage());
		}
	}
	
	private void testRequestEndpointRequired() {
		try {
			Mapping mapping = new Mapping();
			Request request = new Request();
			
			mapping.setRequest(request);
			
			victim.validate(mapping);
		} catch (MissingFieldException e) {
			assertEquals("Request endpoint is required", e.getMessage());
		}
	}
	
	private void testRequestMethodRequired() {
		try {
			Mapping mapping = new Mapping();
			Request request = new Request();
			
			request.setEndpoint("/test");
			
			mapping.setRequest(request);
			
			victim.validate(mapping);
		} catch (MissingFieldException e) {
			assertEquals("Request method is required", e.getMessage());
		}
	}
	
	private void testResponseRequired() {
		try {
			Mapping mapping = new Mapping();
			Request request = new Request();
			
			request.setEndpoint("/test");
			request.setMethod("GET");
			
			mapping.setRequest(request);
			
			victim.validate(mapping);
		} catch (MissingFieldException e) {
			assertEquals("Response is required", e.getMessage());
		}
	}
	
	private void testResponseStatusRequired() {
		try {
			Mapping mapping = new Mapping();
			Request request = new Request();
			Response response = new Response();
			
			request.setEndpoint("/test");
			request.setMethod("GET");
			
			mapping.setRequest(request);
			mapping.setResponse(response);
			
			victim.validate(mapping);
		} catch (MissingFieldException e) {
			assertEquals("Response status is required", e.getMessage());
		}
	}
	
	private void testResponseBodyRequired() {
		try {
			Mapping mapping = new Mapping();
			Request request = new Request();
			Response response = new Response();
			
			request.setEndpoint("/test");
			request.setMethod("GET");
			
			response.setStatus(200);
			
			mapping.setRequest(request);
			mapping.setResponse(response);
			
			victim.validate(mapping);
		} catch (MissingFieldException e) {
			assertEquals("Response body is required", e.getMessage());
		}
	}
}