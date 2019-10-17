package org.mocker.validation.mapping;

import static java.util.Objects.requireNonNull;
import static org.slf4j.LoggerFactory.getLogger;

import org.mocker.domain.Mapping;
import org.mocker.exception.MissingFieldException;
import org.mocker.validation.Validator;
import org.slf4j.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author gabrielpadurean
 */
@Order(value = 1)
@Component
public class MappingFieldValidator implements Validator<Mapping> {
    private static final Logger LOG = getLogger(MappingMethodValidator.class);

    
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
			LOG.warn("Validation issue because the {}", e.getMessage().toLowerCase());
			
			throw new MissingFieldException(e.getMessage());
		}
	}
}
