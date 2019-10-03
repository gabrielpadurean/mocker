package org.mocker.domain;

/**
 * @author gabrielpadurean
 */
public class Mapping {
	private String name;
	private Request request;
	private Response response;

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}
}