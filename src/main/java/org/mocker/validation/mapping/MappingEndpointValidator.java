package org.mocker.validation.mapping;

import org.mocker.domain.Mapping;
import org.mocker.exception.InvalidEndpointException;
import org.mocker.validation.Validator;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author gabrielpadurean
 */
@Order(value = 4)
@Component
public class MappingEndpointValidator implements Validator<Mapping> {

	@Override
	public void validate(Mapping mapping) {
		if (!mapping.getRequest().getEndpoint().startsWith("/")) {
			throw new InvalidEndpointException("The endpoint=" + mapping.getRequest().getEndpoint() + " should start with /");
		}
	}
}
