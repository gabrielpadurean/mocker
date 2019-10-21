package org.mocker.api;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mocker.domain.Mapping;
import org.mocker.domain.Request;
import org.mocker.domain.Response;
import org.mocker.exception.InvalidEndpointException;
import org.mocker.service.MappingService;
import org.mocker.validation.mapping.MappingValidator;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

/**
 * @author gabrielpadurean
 */
public class MappingControllerTest {
	@Mock
	private MappingValidator mappingValidator;
	
	@Mock
	private MappingService mappingService;
	
	@InjectMocks
	private MappingController victim;
	
	
	@Before
	public void setup() {
		initMocks(this);
	}
	
	@Test
	public void testGetMapping() throws Exception {
		Mapping mapping = createMapping();
		
		when(mappingService.findById("abc123")).thenReturn(mapping);
		
		ResponseEntity<Mapping> responseEntity = victim.getMapping("abc123");
		
		assertEquals(200, responseEntity.getStatusCodeValue());
		assertEquals("abc123", responseEntity.getBody().getId());
		verify(mappingService).findById("abc123");
	}
	
	@Test
	public void testDeleteMapping() throws Exception {
		Mapping mapping = createMapping();
		
		when(mappingService.deleteById("abc123")).thenReturn(mapping);
		
		ResponseEntity<Mapping> responseEntity = victim.deleteMapping("abc123");
		
		assertEquals(200, responseEntity.getStatusCodeValue());
		assertEquals("abc123", responseEntity.getBody().getId());
		verify(mappingService).deleteById("abc123");
	}
	
	@Test
	public void testCreateMapping() throws Exception {
		Mapping mapping = createMapping();
		
		doNothing().when(mappingValidator).validate(mapping);
		when(mappingService.save(mapping)).thenReturn(mapping);
		
		ResponseEntity<Mapping> responseEntity = victim.createMapping(mapping);
		
		assertEquals(201, responseEntity.getStatusCodeValue());
		assertEquals("abc123", responseEntity.getBody().getId());
		assertEquals("/v1/api/mappings/abc123", responseEntity.getHeaders().getLocation().toString());
		verify(mappingValidator).validate(mapping);
		verify(mappingService).save(mapping);
	}
	
	@Test(expected = InvalidEndpointException.class)
	public void testCreateInvalidMapping() throws Exception {
		Mapping mapping = createMapping();
		
		doThrow(InvalidEndpointException.class).when(mappingValidator).validate(mapping);
		
		victim.createMapping(mapping);
	}
	
	@Test
	public void testUpdateMapping() {
		Mapping mapping = createMapping();
		
		doNothing().when(mappingValidator).validate(mapping);
		when(mappingService.update(mapping)).thenReturn(mapping);
		
		ResponseEntity<Mapping> responseEntity = victim.updateMapping(mapping);
		
		assertEquals(200, responseEntity.getStatusCodeValue());
		assertEquals("abc123", responseEntity.getBody().getId());
		verify(mappingValidator).validate(mapping);
		verify(mappingService).update(mapping);
	}
	
	@Test(expected = InvalidEndpointException.class)
	public void testUpdateInvalidMapping() {
		Mapping mapping = createMapping();
		
		doThrow(InvalidEndpointException.class).when(mappingValidator).validate(mapping);
		
		victim.updateMapping(mapping);
	}
	
	private Mapping createMapping() {
		Mapping mapping = new Mapping();
		Request request = new Request();
		Response response = new Response();
		
		mapping.setId("abc123");
		mapping.setName("test");
		mapping.setDescription("test");
		
		request.setEndpoint("/test");
		request.setMethod("GET");
		
		response.setStatus(200);
		response.setBody("Something");
		
		mapping.setRequest(request);
		mapping.setResponse(response);
		
		return mapping;
	}
}