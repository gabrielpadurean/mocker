package org.mocker.service;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mocker.domain.Mapping;
import org.mocker.domain.Request;
import org.mocker.domain.Response;
import org.mocker.exception.AlreadyExistsException;
import org.mocker.exception.NotFoundException;
import org.mocker.repository.MappingRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;

/**
 * @author gabrielpadurean
 */
public class MappingServiceTest {
	@Mock
	private MappingRepository mappingRepository;
	
	@InjectMocks
	private MappingService victim;
	
	
	@Before
	public void setup() {
		initMocks(this);
	}
	
	@Test
	public void testCount() {
		when(mappingRepository.count()).thenReturn(10L);
		
		long result = victim.count();
		
		assertEquals(10, result);
		verify(mappingRepository).count();
	}
	
	@Test
	public void testFindByIdExistingMapping() {
		when(mappingRepository.findById(123L)).thenReturn(of(createMapping()));
		
		Mapping mapping = victim.findById(123L);
		
		assertEquals(Long.valueOf(123), mapping.getId());
		verify(mappingRepository).findById(123L);
	}
	
	@Test(expected = NotFoundException.class)
	public void testFindByIdNonExistingMapping() {
		when(mappingRepository.findById(123L)).thenReturn(empty());
		
		victim.findById(123L);
	}
	
	@Test
	public void testFindByEndpointExistingMapping() {
		when(mappingRepository.findMappingByRequestEndpoint("/test")).thenReturn(of(createMapping()));

		Mapping mapping = victim.findByEndpoint("/test");

		assertEquals(Long.valueOf(123), mapping.getId());
		assertEquals("/test", mapping.getRequest().getEndpoint());
		verify(mappingRepository).findMappingByRequestEndpoint("/test");
	}
	
	@Test(expected = NotFoundException.class)
	public void testFindByEndpointNonExistingMapping() {
		when(mappingRepository.findMappingByRequestEndpoint("/test")).thenReturn(empty());

		victim.findByEndpoint("/test");
	}
	
	@Test
	public void testDeleteByIdExistingMapping() {
		Mapping mapping = createMapping();
		
		when(mappingRepository.findById(123L)).thenReturn(of(mapping));
		doNothing().when(mappingRepository).deleteById(123L);
		
		Mapping deletedMapping = victim.deleteById(123L);
		
		assertEquals(Long.valueOf(123), deletedMapping.getId());
		assertEquals("/test", deletedMapping.getRequest().getEndpoint());
		verify(mappingRepository).findById(123L);
		verify(mappingRepository).delete(any());
	}
	
	@Test(expected = NotFoundException.class)
	public void testDeleteByIdNonExistingMapping() {
		when(mappingRepository.findById(123L)).thenReturn(empty());
		
		victim.deleteById(123L);
	}
	
	@Test
	public void testSaveNewMapping() {
		Mapping mapping = createMapping();
		
		when(mappingRepository.findMappingByRequestEndpoint("/test")).thenReturn(empty());
		when(mappingRepository.save(mapping)).thenReturn(mapping);
		
		Mapping createdMapping = victim.save(mapping);
		
		assertEquals("test", createdMapping.getName());
		assertEquals("test", createdMapping.getDescription());
		verify(mappingRepository).findMappingByRequestEndpoint("/test");
		verify(mappingRepository).save(mapping);
	}
	
	@Test
	public void testSaveExistingMapping() {
		Mapping mapping = createMapping();
		
		when(mappingRepository.findMappingByRequestEndpoint("/test")).thenReturn(of(mapping));
		
		try {
			victim.save(mapping);			
		} catch (AlreadyExistsException e) {
			assertTrue(true);
		}
		
		verify(mappingRepository).findMappingByRequestEndpoint("/test");
		verify(mappingRepository, never()).save(mapping);
	}
	
	@Test
	public void testSaveNewMappingForEndpoint() {
		Mapping mapping = createMapping();
		Mapping similarMapping = createMapping();
		similarMapping.setName("similarTest");
		similarMapping.setDescription("similarDescription");
		similarMapping.getRequest().setMethod("POST");
		
		when(mappingRepository.findMappingByRequestEndpoint("/test")).thenReturn(of(similarMapping));
		when(mappingRepository.save(mapping)).thenReturn(mapping);

		Mapping createdMapping = victim.save(mapping);
		
		assertEquals("test", createdMapping.getName());
		assertEquals("test", createdMapping.getDescription());
		verify(mappingRepository).findMappingByRequestEndpoint("/test");
		verify(mappingRepository).save(mapping);
	}
	
	@Test
	public void testUpdateMapping() {
		Mapping mapping = createMapping();
		
		when(mappingRepository.findById(123L)).thenReturn(of(mapping));
		when(mappingRepository.save(mapping)).thenReturn(mapping);
		
		Mapping updatedMapping = victim.update(mapping);
		
		assertEquals(Long.valueOf(123), updatedMapping.getId());
		verify(mappingRepository).findById(123L);
		verify(mappingRepository).save(mapping);
	}
	
	@Test
	public void testUpdateNonExistingMapping() {
		Mapping mapping = createMapping();
		
		when(mappingRepository.findById(123L)).thenReturn(empty());
		
		try {
			victim.update(mapping);
		} catch (NotFoundException e) {
			assertTrue(true);
		}
		
		verify(mappingRepository).findById(123L);
		verify(mappingRepository, never()).save(mapping);
	}
	
	private Mapping createMapping() {
		Mapping mapping = new Mapping();
		Request request = new Request();
		Response response = new Response();
		
		mapping.setId(123L);
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