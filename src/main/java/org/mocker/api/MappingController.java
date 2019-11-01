package org.mocker.api;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

import java.net.URI;

import org.mocker.domain.Mapping;
import org.mocker.service.MappingService;
import org.mocker.validation.mapping.MappingValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gabrielpadurean
 */
@RequestMapping("/v1/api/")
@RestController
public class MappingController {
	@Autowired
	private MappingValidator mappingValidator;
	
	@Autowired
	private MappingService mappingService;
	
	
	@GetMapping("/mappings/{id}")
	public ResponseEntity<Mapping> getMapping(@PathVariable Long id) {
		return ok(mappingService.findById(id));
	}
	
	@DeleteMapping("/mappings/{id}")
	public ResponseEntity<Mapping> deleteMapping(@PathVariable Long id) {
		return ok(mappingService.deleteById(id));
	}
	
	@PostMapping("/mappings")
	public ResponseEntity<Mapping> createMapping(@RequestBody Mapping mapping) throws Exception {
		mappingValidator.validate(mapping);
		
		mappingService.save(mapping);
		
		return created(new URI("/v1/api/mappings/" + mapping.getId()))
				.body(mapping);
	}
	
	@PutMapping("/mappings")
	public ResponseEntity<Mapping> updateMapping(@RequestBody Mapping mapping) {
		mappingValidator.validate(mapping);
		
		mappingService.update(mapping);
		
		return ok(mapping);
	}
}