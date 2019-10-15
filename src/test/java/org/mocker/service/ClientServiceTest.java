package org.mocker.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

/**
 * @author gabrielpadurean
 */
public class ClientServiceTest {
	@Mock
	private Collection<String> whitelistedClients;
	
	@InjectMocks
	private ClientService victim = new ClientService();
	
	
	@Before
	public void setup() {
		initMocks(this);
		
		when(whitelistedClients.contains("web")).thenReturn(true);
		when(whitelistedClients.contains("desktop")).thenReturn(false);
	}
	
	@Test
	public void testClientWhitelisted() {
		assertEquals(true, victim.isClientWhitelisted("web"));
	}
	
	@Test
	public void testClientNotWhitelisted() {
		assertEquals(false, victim.isClientWhitelisted("desktop"));
	}
}