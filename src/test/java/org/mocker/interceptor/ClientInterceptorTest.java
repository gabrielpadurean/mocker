package org.mocker.interceptor;

import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mocker.service.ClientService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

/**
 * @author gabrielpadurean
 */
public class ClientInterceptorTest {
	@Mock
	private HttpServletRequest httpServletRequest;
	
	@Mock
	private HttpServletResponse httpServletResponse;
	
	@Mock
	private ClientService clientService;
	
	@InjectMocks
	private ClientInterceptor victim;
	
	
	@Before
	public void setup() {
		initMocks(this);
	}
	
	@Test
	public void testWhitelistedClient() throws Exception {
		when(httpServletRequest.getHeader("X-Application")).thenReturn("test");
		when(clientService.isClientWhitelisted("test")).thenReturn(true);
		
		boolean result = victim.preHandle(httpServletRequest, httpServletResponse, null);
		
		assertEquals(true, result);
		
		verify(clientService).isClientWhitelisted("test");
		verify(httpServletRequest).getHeader("X-Application");
	}
	
	@Test
	public void testNotWhitelistedClient() throws Exception {
		when(httpServletRequest.getHeader("X-Application")).thenReturn("test");
		when(clientService.isClientWhitelisted("test")).thenReturn(false);
		
		boolean result = victim.preHandle(httpServletRequest, httpServletResponse, null);
		
		assertEquals(false, result);
		
		verify(clientService).isClientWhitelisted("test");
		verify(httpServletRequest).getHeader("X-Application");
		verify(httpServletResponse).setStatus(SC_UNAUTHORIZED);
	}
	
	@Test
	public void testMissingClient() throws Exception {
		when(httpServletRequest.getHeader("X-Application")).thenReturn(null);
		when(clientService.isClientWhitelisted(null)).thenReturn(false);
		
		boolean result = victim.preHandle(httpServletRequest, httpServletResponse, null);
		
		assertEquals(false, result);
		
		verify(clientService).isClientWhitelisted(null);
		verify(httpServletRequest).getHeader("X-Application");
		verify(httpServletResponse).setStatus(SC_UNAUTHORIZED);
	}
}
