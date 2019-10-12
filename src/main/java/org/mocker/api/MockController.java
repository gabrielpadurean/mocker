package org.mocker.api;

import org.mocker.service.MockService;
import org.springframework.beans.factory.annotation.Autowired;
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

	
	@RequestMapping("/mocks")
	public ResponseEntity<Object> handleMocks() {
		return null;
	}
}