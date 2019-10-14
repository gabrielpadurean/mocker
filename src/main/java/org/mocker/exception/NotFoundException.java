package org.mocker.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author gabrielpadurean
 */
@SuppressWarnings("serial")
@ResponseStatus(NOT_FOUND)
public class NotFoundException extends RuntimeException {
	
	public NotFoundException(String message) {
		super(message);
	}
}