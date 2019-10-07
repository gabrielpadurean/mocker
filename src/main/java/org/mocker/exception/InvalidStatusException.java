package org.mocker.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author gabrielpadurean
 */
@SuppressWarnings("serial")
@ResponseStatus(BAD_REQUEST)
public class InvalidStatusException extends RuntimeException {
	
	public InvalidStatusException(String message) {
		super(message);
	}
}