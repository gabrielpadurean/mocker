package org.mocker.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author gabrielpadurean
 */
@SuppressWarnings("serial")
@ResponseStatus(INTERNAL_SERVER_ERROR)
public class ServerException extends RuntimeException {

	public ServerException(String message) {
		super(message);
	}
}