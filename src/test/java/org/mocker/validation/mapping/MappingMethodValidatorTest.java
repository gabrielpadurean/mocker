package org.mocker.validation.mapping;

import org.junit.Test;
import org.mocker.domain.Mapping;
import org.mocker.domain.Request;
import org.mocker.exception.InvalidMethodException;

/**
 * @author gabrielpadurean
 */
public class MappingMethodValidatorTest {
	private MappingMethodValidator victim = new MappingMethodValidator();
	
	
	@Test
	public void testValidMethod() {
		Mapping mapping = new Mapping();
		Request request = new Request();
		
		request.setMethod("GET");
		mapping.setRequest(request);
		
		victim.validate(mapping);
	}
	
	@Test(expected = InvalidMethodException.class)
	public void testInvalidMethod() {
		Mapping mapping = new Mapping();
		Request request = new Request();
		
		request.setMethod("NOTHING");
		mapping.setRequest(request);
		
		victim.validate(mapping);
	}
}