package org.mocker.service;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.Optional;

import org.mocker.domain.Mapping;
import org.mocker.exception.AlreadyExistsException;
import org.mocker.exception.NotFoundException;
import org.mocker.repository.MappingRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gabrielpadurean
 */
@Service
public class MappingService {
    private static final Logger LOG = getLogger(MappingService.class);

	@Autowired
	private MappingRepository mappingRepository;
	
	
	public long count() {
		LOG.info("Counting mappings");
		
		return mappingRepository.count();
	}
	
	public Optional<Mapping> findById(String id) {
		LOG.info("Finding mapping with id={}", id);

		return mappingRepository.findById(id);
	}
	
	public Optional<Mapping> findByEndpoint(String endpoint) {
		LOG.info("Finding mapping with endpoint={}", endpoint);
		
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
	 * @throws An exception if the mapping already exists.
	 */
	public Mapping save(Mapping mapping) {
		LOG.info("Saving mapping with method={} and endpoint={}", mapping.getRequest().getMethod(), mapping.getRequest().getEndpoint());
		
		mapping.setId(null);
		
		mappingRepository
			.findByEndpoint(mapping.getRequest().getEndpoint())
			.filter(existingMapping -> existingMapping.getRequest().getMethod().equalsIgnoreCase(mapping.getRequest().getMethod()))
			.ifPresent(existingMapping -> {
				throw new AlreadyExistsException("Mapping for method=" + existingMapping.getRequest().getMethod() + " and endpoint=" + existingMapping.getRequest().getEndpoint() + " exists");
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
	 * @throws An exception if the given mapping is not found.
	 */
	public Mapping update(Mapping mapping) {
		LOG.info("Updating mapping with id={}, method={} and endpoint={}", mapping.getId(), mapping.getRequest().getMethod(), mapping.getRequest().getEndpoint());
		
		return mappingRepository
				.findById(mapping.getId())
				.map(existingMapping -> mappingRepository.save(mapping))
				.orElseThrow(() -> new NotFoundException("Mapping with id=" + mapping.getId() + " not found"));
	}
}