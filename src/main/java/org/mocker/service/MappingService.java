package org.mocker.service;

import java.util.Optional;

import org.mocker.domain.Mapping;
import org.mocker.exception.AlreadyExistsException;
import org.mocker.exception.NotFoundException;
import org.mocker.repository.MappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gabrielpadurean
 */
@Service
public class MappingService {
	@Autowired
	private MappingRepository mappingRepository;
	
	
	public long count() {
		return mappingRepository.count();
	}
	
	public Optional<Mapping> findById(String id) {
		return mappingRepository.findById(id);
	}
	
	public Optional<Mapping> findByEndpoint(String endpoint) {
		return mappingRepository.findByEndpoint(endpoint);
	}
	
	/**
	 * Will save the {@link Mapping} instance, but will throw an exception
	 * if a mapping for the same combination (endpoint, method etc) already exists.
	 * If an id is provided for the mapping, will be ignored, since the ids
	 * are handled internally and should not be provided by the client.
	 * 
	 * @param mapping The actual mapping object that will be saved.
	 * @return The saved mapping instance.
	 */
	public Mapping save(Mapping mapping) {
		mapping.setId(null);
		
		mappingRepository
			.findByEndpoint(mapping.getRequest().getEndpoint())
			.filter(existingMapping -> existingMapping.getRequest().getMethod().equalsIgnoreCase(mapping.getRequest().getMethod()))
			.ifPresent(existingMapping -> {
				throw new AlreadyExistsException("Mapping with endpoint " + existingMapping.getRequest().getEndpoint() + " exists");
			});
		
		return mappingRepository.save(mapping);
	}
	
	/**
	 * Will update the {@link Mapping} instance based on the id.
	 * If there is no existing mapping with the provided id an
	 * exception will be throw signaling a not found mapping.
	 * 
	 * 
	 * @param mapping The actual mapping object that will be updated.
	 * @return The updated mapping instance.
	 */
	public Mapping update(Mapping mapping) {
		return mappingRepository
				.findById(mapping.getId())
				.map(existingMapping -> mappingRepository.save(mapping))
				.orElseThrow(() -> new NotFoundException("Mapping with id " + mapping.getId() + " not found"));
	}
}