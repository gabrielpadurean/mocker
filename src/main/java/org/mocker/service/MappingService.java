package org.mocker.service;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.List;

import org.mocker.domain.Mapping;
import org.mocker.exception.AlreadyExistsException;
import org.mocker.exception.NotFoundException;
import org.mocker.repository.MappingRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
		LOG.info("Count mappings");
		
		return mappingRepository.count();
	}
	
	/**
	 * Will find the {@link Mapping} instance based on the id.
	 * If there is no existing mapping with the provided id an
	 * exception will be throw signaling a not found mapping.
	 * 
	 * @param id The id of the mapping to find.
	 * @return The mapping instance.
	 * @throws An exception if the given mapping is not found.
	 */
	public Mapping findById(Long id) {
		return (Mapping)mappingRepository
				.findById(id)
				.map(mapping -> {
					LOG.info("Find mapping with id={}", id);

					return mapping;
				})
				.orElseThrow(() -> {
					LOG.error("Cannot find mapping (not found) with id={}", id);

					return new NotFoundException("Mapping with id=" + id + " not found");
				});
	}
	
	/**
	 * Will find the {@link Mapping} instance based on the endpoint.
	 * If there is no existing mapping with the provided endpoint an
	 * exception will be throw signaling a not found mapping.
	 * 
	 * @param endpoint The endpoint of the mapping to find.
	 * @return The mapping instance.
	 * @throws An exception if the given mapping is not found.
	 */
	public Mapping findByEndpoint(String endpoint) {
		return (Mapping)mappingRepository
				.findByRequestEndpoint(endpoint)
				.map(mapping -> {
					LOG.info("Find mapping with endpoint={}", endpoint);
					
					return mapping;
				})
				.orElseThrow(() -> {
					LOG.error("Cannot find mapping (not found) with endpoint={}", endpoint);

					return new NotFoundException("Mapping with endpoint=" + endpoint + " not found");
				});
	}
	
	/**
	 * Will find the list of {@link Mapping} instances that contain the name parameter.
	 * If there is no existing mapping that contains the given parameter an empty list is returned.
	 * 
	 * @param name The name to filter by.
	 * @param pageable Encapsulates details about pagination and sorting.
	 * @return The list of mappings that contain the given name.
	 */
	public List<Mapping> findByNameContaining(String name, Pageable pageable) {
		LOG.info("Find mappings by name={} with page={} and size={}", name, pageable.getOffset(), pageable.getPageSize());

		return mappingRepository.findByNameContaining(name, pageable);
	}
	
	/**
	 * Will find the list of {@link Mapping} instances that contain the endpoint parameter.
	 * If there is no existing mapping that contains the given parameter an empty list is returned.
	 * 
	 * @param endpoint The endpoint to filter by.
	 * @param pageable Encapsulates details about pagination and sorting.
	 * @return The list of mappings that contain the given endpoint.
	 */
	public List<Mapping> findByEndpointContaining(String endpoint, Pageable pageable) {
		LOG.info("Find mappings by endpoint={} with page={} and size={}", endpoint, pageable.getOffset(), pageable.getPageSize());

		return mappingRepository.findByRequestEndpointContaining(endpoint, pageable);
	}
	
	/**
	 * Will find the list of {@link Mapping} instances that contain the name and endpoint parameters.
	 * If there is no existing mapping that contains the given parameters an empty list is returned.
	 * 
	 * @param name The name to filter by.
	 * @param endpoint The endpoint to filter by.
	 * @param pageable Encapsulates details about pagination and sorting.
	 * @return The list of mappings that contain the given name and endpoint.
	 */
	public List<Mapping> findByNameAndEndpointContaining(String name, String endpoint, Pageable pageable) {
		LOG.info("Find mappings by name={} and endpoint={} with page={} and size={}", name, endpoint, pageable.getOffset(), pageable.getPageSize());

		return mappingRepository.findByNameContainingAndRequestEndpointContaining(name, endpoint, pageable);
	}
	
	/**
	 * Will return all mappings based on the given pagination and sorting details.
	 * 
	 * @param pageable Encapsulates details about pagination and sorting.
	 * @return The list of mappings for the requested page.
	 */
	public List<Mapping> findAll(Pageable pageable) {
		LOG.info("Find mappings with page={} and size={}", pageable.getOffset(), pageable.getPageSize());

		return mappingRepository.findAll(pageable).getContent();
	}
	
	/**
	 * Will delete the {@link Mapping} instance based on the id.
	 * If there is no existing mapping with the provided id an
	 * exception will be throw signaling a not found mapping.
	 * 
	 * @param id The id of the mapping to delete.
	 * @return The deleted mapping instance.
	 * @throws An exception if the given mapping is not found.
	 */
	public Mapping deleteById(Long id) {
		return (Mapping)mappingRepository
				.findById(id)
				.map(mapping -> {
					LOG.info("Delete mapping with id={}, method={} and endpoint={}", mapping.getId(), mapping.getRequest().getMethod(), mapping.getRequest().getEndpoint());
					
					mappingRepository.delete(mapping);
					
					return mapping;
				})
				.orElseThrow(() -> {
					LOG.error("Cannot delete mapping (not found) with id={}", id);

					return new NotFoundException("Mapping with id=" + id + " not found");
				});
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
		return (Mapping)mappingRepository
				.findByRequestEndpoint(mapping.getRequest().getEndpoint())
				.filter(existingMapping -> existingMapping.getRequest().getMethod().equalsIgnoreCase(mapping.getRequest().getMethod()))
				.map(existingMapping -> {
					LOG.error("Cannot save mapping (already exists) with method={} and endpoint={}", existingMapping.getRequest().getMethod(), existingMapping.getRequest().getEndpoint());

					throw new AlreadyExistsException("Mapping for method=" + existingMapping.getRequest().getMethod() + " and endpoint=" + existingMapping.getRequest().getEndpoint() + " exists");
				})
				.orElseGet(() -> {
					LOG.info("Save mapping with method={} and endpoint={}", mapping.getRequest().getMethod(), mapping.getRequest().getEndpoint());

					return mappingRepository.save(mapping);
				});
	}
	
	/**
	 * Will update the {@link Mapping} instance based on the id.
	 * If there is no existing mapping with the provided id an
	 * exception will be throw signaling a not found mapping.
	 * 
	 * @param mapping The actual mapping object that will be updated.
	 * @return The updated mapping instance.
	 * @throws An exception if the given mapping is not found.
	 */
	public Mapping update(Mapping mapping) {
		return (Mapping)mappingRepository
				.findById(mapping.getId())
				.map(existingMapping -> {
					LOG.info("Update mapping with id={}, method={} and endpoint={}", mapping.getId(), mapping.getRequest().getMethod(), mapping.getRequest().getEndpoint());
					
					return mappingRepository.save(mapping);
				})
				.orElseThrow(() -> {
					LOG.error("Cannot update mapping (not found) with id={}", mapping.getId());

					return new NotFoundException("Mapping with id=" + mapping.getId() + " not found");
				});
	}
}