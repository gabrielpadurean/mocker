package org.mocker.api;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.noContent;

import java.net.URI;
import java.util.Collection;

import org.mocker.domain.Mapping;
import org.mocker.service.MappingService;
import org.mocker.validation.mapping.MappingValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gabrielpadurean
 */
@RestController
public class MappingController {
	@Autowired
	private MappingValidator mappingValidator;
	
	@Autowired
	private MappingService mappingService;
	
	
	/**
	 * Create a new mapping based on the given details, request is idempotent.
	 */
	@PostMapping("/mappings")
	public ResponseEntity<Mapping> createMapping(@RequestBody Mapping mapping) throws Exception {
		mappingValidator.validate(mapping);
		
		return created(new URI(""))
				.body(mapping);
	}
	
	/**
	 * Returns a list of all mappings in this mock server using pagination.
	 */
	@GetMapping("/mappings")
	public ResponseEntity<Collection<Mapping>> getMappings() {
		return noContent()
				.build();
	}
}