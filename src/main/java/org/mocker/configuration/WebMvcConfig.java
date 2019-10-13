package org.mocker.configuration;

import org.mocker.interceptor.ClientInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author gabrielpadurean
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	@Autowired
	private ClientInterceptor clientInterceptor;
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(clientInterceptor);
	}
}