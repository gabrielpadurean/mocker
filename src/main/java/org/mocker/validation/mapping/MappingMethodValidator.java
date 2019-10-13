package org.mocker.validation.mapping;

import org.mocker.domain.Mapping;
import org.mocker.exception.InvalidMethodException;
import org.mocker.validation.Validator;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

/**
 * @author gabrielpadurean
 */
@Order(value = 3)
@Component
public class MappingMethodValidator implements Validator<Mapping> {

	@Override
	public void validate(Mapping mapping) {
		if (HttpMethod.resolve(mapping.getRequest().getMethod()) == null) {
			throw new InvalidMethodException("The method=" + mapping.getRequest().getMethod() + " is not valid");
		}
	}
}
