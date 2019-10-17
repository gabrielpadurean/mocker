package org.mocker.repository;

import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.mocker.domain.Mapping;
import org.springframework.stereotype.Repository;

/**
 * This is just a dummy implementation of an actual repository until the storage system is configured.
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
		return id != null ? ofNullable(mappings.get(id)) : empty();
	}
	
	public Optional<Mapping> findByEndpoint(String endpoint) {
		return endpoint != null ? ofNullable(mappingsCache.get(endpoint)) : empty();
	}

	public synchronized Mapping deleteById(String id) {
		Mapping mapping = mappings.remove(id);

		mappingsCache.remove(mapping.getRequest().getEndpoint());

		return mapping;
	}
	
	/**
	 * Performs saveOrUpdate based on the given id.
	 * If the id is missing it means this is a create action,
	 * otherwise this is an update action performed.
	 */
	public synchronized Mapping save(Mapping mapping) {
		if (mapping.getId() == null) {
			mapping.setId(UUID.randomUUID().toString());
		} else {
			Mapping oldMapping = mappings.get(mapping.getId());
			
			if (!oldMapping.getRequest().getEndpoint().equalsIgnoreCase(mapping.getRequest().getEndpoint())) {
				mappingsCache.remove(oldMapping.getRequest().getEndpoint());
			}
		}
		
		mappings.put(mapping.getId(), mapping);
		mappingsCache.put(mapping.getRequest().getEndpoint(), mapping);
		
		return mapping;
	}
}