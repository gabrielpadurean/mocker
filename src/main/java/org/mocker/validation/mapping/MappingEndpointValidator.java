package org.mocker.validation.mapping;

import static org.slf4j.LoggerFactory.getLogger;

import org.mocker.domain.Mapping;
import org.mocker.exception.InvalidEndpointException;
import org.mocker.validation.Validator;
import org.slf4j.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author gabrielpadurean
 */
@Order(value = 4)
@Component
public class MappingEndpointValidator implements Validator<Mapping> {
    private static final Logger LOG = getLogger(MappingMethodValidator.class);

    
	@Override
	public void validate(Mapping mapping) {
		if (!mapping.getRequest().getEndpoint().startsWith("/")) {
			LOG.warn("Validation issue because the endpoint={} should start with /", mapping.getRequest().getEndpoint());
			
			throw new InvalidEndpointException("The endpoint=" + mapping.getRequest().getEndpoint() + " should start with /");
		}
		
		if (mapping.getRequest().getEndpoint().length() <= 1) {
			LOG.warn("Validation issue because the endpoint={} should contain at least one character", mapping.getRequest().getEndpoint());
			
			throw new InvalidEndpointException("The endpoint=" + mapping.getRequest().getEndpoint() + " should contain at least one character");
		}
	}
}
