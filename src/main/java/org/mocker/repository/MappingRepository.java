package org.mocker.repository;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.mocker.domain.Mapping;
import org.springframework.stereotype.Repository;

/**
 * This is just a dummy implementation of an actual repository, until the storage system is configured.
 * 
 * @author gabrielpadurean
 */
@Repository
public class MappingRepository {
	/**
	 * Storage by id value.
	 */
	private Map<String, Mapping> mappings = new ConcurrentHashMap<>();
	
	/**
	 * Storage by endpoint value.
	 */
	private Map<String, Mapping> mappingsCache = new ConcurrentHashMap<>();
	
	
	public long count() {
		return mappings.size();
	}
	
	public Optional<Mapping> findById(String id) {
		return id != null ? Optional.ofNullable(mappings.get(id)) : Optional.empty();
	}
	
	public Optional<Mapping> findByEndpoint(String endpoint) {
		return endpoint != null ? Optional.ofNullable(mappingsCache.get(endpoint)) : Optional.empty();
	}
	
	public synchronized Mapping save(Mapping mapping) {
		if (mapping.getId() == null) {
			mapping.setId(UUID.randomUUID().toString());
		}
		
		mappings.put(mapping.getId(), mapping);
		mappingsCache.put(mapping.getRequest().getEndpoint(), mapping);
		
		return mapping;
	}
}