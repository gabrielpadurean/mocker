package org.mocker.interceptor;

import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;
import static org.slf4j.LoggerFactory.getLogger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mocker.service.ClientService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * Interceptor for identifying the client and check it the actual request is allowed.
 * 
 * @author gabrielpadurean
 */
@Component
public class ClientInterceptor implements HandlerInterceptor {
    private static final Logger LOG = getLogger(ClientInterceptor.class);

	private static final String X_APPLICATION_HEADER = "X-Application";
	
	@Autowired
	private ClientService clientService;
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String xApplication = request.getHeader(X_APPLICATION_HEADER);
		
		if (clientService.isClientWhitelisted(xApplication)) {
			return true;
		} else {
			LOG.warn("Validation issue because the X-Application={} header is not whitelisted", xApplication);
			
			response.setStatus(SC_UNAUTHORIZED);
			
			return false;
		}
	}
}