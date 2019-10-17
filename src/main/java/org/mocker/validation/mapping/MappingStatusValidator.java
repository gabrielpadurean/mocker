package org.mocker.validation.mapping;

import static org.slf4j.LoggerFactory.getLogger;

import org.mocker.domain.Mapping;
import org.mocker.exception.InvalidStatusException;
import org.mocker.validation.Validator;
import org.slf4j.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author gabrielpadurean
 */
@Order(value = 2)
@Component
public class MappingStatusValidator implements Validator<Mapping> {
    private static final Logger LOG = getLogger(MappingStatusValidator.class);

    
	@Override
	public void validate(Mapping mapping) {
		if (mapping.getResponse().getStatus() < 100 || mapping.getResponse().getStatus() > 599) {
			LOG.warn("Validation issue because the statusCode={} is not valid", mapping.getResponse().getStatus());
			
			throw new InvalidStatusException("The statusCode=" + mapping.getResponse().getStatus() + " is not valid");
		}
	}
}
