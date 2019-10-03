package org.mocker.domain;

import org.springframework.http.HttpMethod;

/**
 * @author gabrielpadurean
 */
public class Request {
	private String endpoint;
	private HttpMethod method;

	
	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public HttpMethod getMethod() {
		return method;
	}

	public void setMethod(HttpMethod method) {
		this.method = method;
	}
}