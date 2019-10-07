package org.mocker.validation.mapping;

import java.util.Collection;

import org.mocker.domain.Mapping;
import org.mocker.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Validation applied to a {@link Mapping} instance and the action uses a collection
 * of specific {@link Validator} to determine if the {@link Mapping} is valid or not.
 * 
 * For something invalid, an specific {@link RuntimeException} is thrown that is mapped
 * to a HTTP status code and will also contain a message about the cause of the fail.
 * 
 * @author gabrielpadurean
 */
@Component
public class MappingValidator {
	@Autowired
	private Collection<Validator<Mapping>> mappingValidators;
	
	
	public void validate(Mapping mapping) throws RuntimeException {
		mappingValidators.forEach(validator -> validator.validate(mapping));
	}
}