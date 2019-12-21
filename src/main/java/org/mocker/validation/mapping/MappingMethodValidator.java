package org.mocker.validation.mapping;

import static org.slf4j.LoggerFactory.getLogger;

import org.mocker.domain.Mapping;
import org.mocker.exception.InvalidMethodException;
import org.mocker.validation.Validator;
import org.slf4j.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

/**
 * @author gabrielpadurean
 */
@Order(value = 3)
@Component
public class MappingMethodValidator implements Validator<Mapping> {
    private static final Logger LOG = getLogger(MappingMethodValidator.class);


	@Override
	public void validate(Mapping mapping) {
		if (HttpMethod.resolve(mapping.getRequest().getMethod().toUpperCase()) == null) {
			LOG.warn("Validation issue because the method={} is not valid", mapping.getRequest().getMethod());

			throw new InvalidMethodException("The method=" + mapping.getRequest().getMethod() + " is not valid");
		}
	}
}
