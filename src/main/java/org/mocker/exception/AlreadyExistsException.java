package org.mocker.exception;

import static org.springframework.http.HttpStatus.CONFLICT;

import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author gabrielpadurean
 */
@SuppressWarnings("serial")
@ResponseStatus(CONFLICT)
public class AlreadyExistsException extends RuntimeException {
	
	public AlreadyExistsException(String message) {
		super(message);
	}
}