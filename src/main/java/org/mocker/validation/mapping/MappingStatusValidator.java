package org.mocker.validation.mapping;

import org.mocker.domain.Mapping;
import org.mocker.exception.InvalidStatusException;
import org.mocker.validation.Validator;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author gabrielpadurean
 */
@Order(value = 2)
@Component
public class MappingStatusValidator implements Validator<Mapping> {

	@Override
	public void validate(Mapping mapping) {
		if (mapping.getResponse().getStatus() < 100 || mapping.getResponse().getStatus() > 599) {
			throw new InvalidStatusException("Status code " + mapping.getResponse().getStatus() + " is not valid");
		}
	}
}
