package org.mocker.domain;

/**
 * @author gabrielpadurean
 */
public class Request {
	private String endpoint;
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