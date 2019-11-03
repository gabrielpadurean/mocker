package org.mocker.api;

import static org.springframework.http.ResponseEntity.status;
import static org.springframework.util.StringUtils.isEmpty;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.mocker.domain.Criteria;
import org.mocker.domain.Header;
import org.mocker.domain.Mapping;
import org.mocker.service.MockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gabrielpadurean
 */
@RequestMapping("/v1/api/")
@RestController
public class MockController {
	@Autowired
	private MockService mockService;

	
	@RequestMapping("/mocks/*")
	public ResponseEntity<String> handleMocks(HttpServletRequest httpServletRequest) {
		String uri = httpServletRequest.getRequestURI();
		String queryString = httpServletRequest.getQueryString();
		String endpoint = uri.replace("/v1/api/mocks", "") + (isEmpty(queryString) ? "" : "?" + queryString);
		String method = httpServletRequest.getMethod();
		
		Criteria criteria = new Criteria(method, endpoint);
		
		Mapping mapping = mockService.findByCriteria(criteria);
		
		return status(mapping.getResponse().getStatus())
				.headers(buildResponseHeaders(mapping.getResponse().getHeaders()))
				.body(mapping.getResponse().getBody());
	}
	
	private HttpHeaders buildResponseHeaders(Collection<Header> headers) {
		HttpHeaders httpHeaders = new HttpHeaders();

		if (headers != null) {
			headers.forEach(header -> {
				httpHeaders.add(header.getName(), header.getValue());
			});
		}

		return httpHeaders;
	}
}