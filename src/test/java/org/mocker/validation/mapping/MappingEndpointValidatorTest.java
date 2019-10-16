package org.mocker.validation.mapping;

import org.junit.Test;
import org.mocker.domain.Mapping;
import org.mocker.domain.Request;
import org.mocker.exception.InvalidEndpointException;

/**
 * @author gabrielpadurean
 */
public class MappingEndpointValidatorTest {
	private MappingEndpointValidator victim = new MappingEndpointValidator();
	
	
	@Test
	public void testValidEndpoint() {
		Mapping mapping = new Mapping();
		Request request = new Request();
		
		request.setEndpoint("/test");
		mapping.setRequest(request);
		
		victim.validate(mapping);
	}
	
	@Test(expected = InvalidEndpointException.class)
	public void testInvalidEndpoint() {
		Mapping mapping = new Mapping();
		Request request = new Request();
		
		request.setEndpoint("test");
		mapping.setRequest(request);
		
		victim.validate(mapping);
	}
}