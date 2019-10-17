package org.mocker.domain;

/**
 * @author gabrielpadurean
 */
public class Criteria {
	private String method;
	private String endpoint;
	
	
	public Criteria(String method, String endpoint) {
		this.method = method;
		this.endpoint = endpoint;
	}

	public String getMethod() {
		return method;
	}


	public void setMethod(String method) {
		this.method = method;
	}


	public String getEndpoint() {
		return endpoint;
	}


	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
}