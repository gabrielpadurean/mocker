package org.mocker.validation.mapping;

import static java.util.Objects.requireNonNull;

import org.mocker.domain.Mapping;
import org.mocker.exception.MissingFieldException;
import org.mocker.validation.Validator;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author gabrielpadurean
 */
@Order(value = 1)
@Component
public class MappingFieldValidator implements Validator<Mapping> {

	@Override
	public void validate(Mapping mapping) {
		try {			
			requireNonNull(mapping.getRequest(), "Request is required");
			requireNonNull(mapping.getRequest().getEndpoint(), "Request endpoint is required");
			requireNonNull(mapping.getRequest().getMethod(), "Request method is required");
			
			requireNonNull(mapping.getResponse(), "Response is required");
			requireNonNull(mapping.getResponse().getStatus(), "Response status is required");
			requireNonNull(mapping.getResponse().getBody(), "Response body is required");
		} catch (NullPointerException e) {
			throw new MissingFieldException(e.getMessage());
		}
	}
}
