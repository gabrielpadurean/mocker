package org.mocker.domain;

import javax.persistence.Column;

/**
 * @author gabrielpadurean
 */
public class Request {
	@Column(length = 5000)
	private String endpoint;

	@Column
	private String method;

	
	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
}