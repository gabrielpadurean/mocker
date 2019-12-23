package org.mocker.domain;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;

/**
 * @author gabrielpadurean
 */
public class Response {
	@Column
	private Integer status;
	
	@Column(length = 50000)
	private String body;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private Collection<Header> headers;

	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
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