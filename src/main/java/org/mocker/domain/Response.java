package org.mocker.domain;

import java.util.Collection;

/**
 * @author gabrielpadurean
 */
public class Response {
	private int status;
	private String body;
	private Collection<Header> headers;

	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Collection<Header> getHeaders() {
		return headers;
	}

	public void setHeaders(Collection<Header> headers) {
		this.headers = headers;
	}
}