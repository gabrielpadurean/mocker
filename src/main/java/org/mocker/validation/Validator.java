package org.mocker.validation;

/**
 * @author gabrielpadurean
 */
public interface Validator<T> {

	void validate(T t);
}