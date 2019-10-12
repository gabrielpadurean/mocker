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
	
	public Mapping save(Mapping mapping) {
		mappingRepository
			.findById(mapping.getId())
			.ifPresent(existingMapping -> {
				throw new AlreadyExistsException("Mapping with id " + existingMapping.getId() + " exists");
			});
		
		mappingRepository
			.findByEndpoint(mapping.getRequest().getEndpoint())
			.ifPresent(existingMapping -> {
				throw new AlreadyExistsException("Mapping with endpoint " + existingMapping.getRequest().getEndpoint() + " exists");
			});
		
		return mappingRepository.save(mapping);
	}
	
	public Mapping update(Mapping mapping) {
		return mappingRepository
				.findById(mapping.getId())
				.map(existingMapping -> mappingRepository.save(mapping))
				.orElseThrow(() -> new NotFoundException("Mapping with id " + mapping.getId() + " not found"));
	}
}