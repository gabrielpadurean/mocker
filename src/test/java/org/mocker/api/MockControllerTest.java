package org.mocker.api;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.mocker.domain.Criteria;
import org.mocker.domain.Mapping;
import org.mocker.domain.Request;
import org.mocker.domain.Response;
import org.mocker.service.MockService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

/**
 * @author gabrielpadurean
 */
public class MockControllerTest {
	@Mock
	private HttpServletRequest httpServletRequest;
	
	@Mock
	private MockService mockService;
	
	@InjectMocks
	private MockController victim;
	
	
	@Before
	public void setup() {
		initMocks(this);
	}
	
	@Test
	public void testGetRequest() {
		Mapping mapping = createMapping("GET", "/test?param=test", 200, "test");
				
		when(httpServletRequest.getRequestURI()).thenReturn("/test");
		when(httpServletRequest.getQueryString()).thenReturn("param=test");
		when(httpServletRequest.getMethod()).thenReturn("GET");
		when(mockService.findByCriteria(any(Criteria.class))).thenReturn(mapping);
		
		ResponseEntity<String> responseEntity = victim.handleMocks(httpServletRequest);
		
		assertEquals(200, responseEntity.getStatusCodeValue());
		assertEquals("test", responseEntity.getBody());
	}
	
	@Test
	public void testPostRequest() {
		Mapping mapping = createMapping("POST", "/test", 201, "test");
				
		when(httpServletRequest.getRequestURI()).thenReturn("/test");
		when(httpServletRequest.getQueryString()).thenReturn(null);
		when(httpServletRequest.getMethod()).thenReturn("POST");
		when(mockService.findByCriteria(any(Criteria.class))).thenReturn(mapping);
		
		ResponseEntity<String> responseEntity = victim.handleMocks(httpServletRequest);
		
		assertEquals(201, responseEntity.getStatusCodeValue());
		assertEquals("test", responseEntity.getBody());
	}
	
	private Mapping createMapping(String method, String endpoint, Integer status, String body) {
		Mapping mapping = new Mapping();
		Request request = new Request();
		Response response = new Response();
		
		mapping.setId("abc123");
		mapping.setName("test");
		mapping.setDescription("test");
		
		request.setEndpoint(endpoint);
		request.setMethod(method);
		
		response.setStatus(status);
		response.setBody(body);
		
		mapping.setRequest(request);
		mapping.setResponse(response);
		
		return mapping;
	}
}