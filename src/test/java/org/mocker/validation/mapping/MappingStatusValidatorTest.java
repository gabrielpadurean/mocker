package org.mocker.validation.mapping;

import org.junit.Test;
import org.mocker.domain.Mapping;
import org.mocker.domain.Response;
import org.mocker.exception.InvalidStatusException;

/**
 * @author gabrielpadurean
 */
public class MappingStatusValidatorTest {
	private MappingStatusValidator victim = new MappingStatusValidator();
	
	
	@Test
	public void testValidStatus() {
		Mapping mapping = new Mapping();
		Response response = new Response();
		
		response.setStatus(200);
		mapping.setResponse(response);
		
		victim.validate(mapping);
	}
	
	@Test(expected = InvalidStatusException.class)
	public void testInvalidStatus() {
		Mapping mapping = new Mapping();
		Response response = new Response();
		
		response.setStatus(1);
		mapping.setResponse(response);
		
		victim.validate(mapping);
	}
}