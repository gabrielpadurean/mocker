package org.mocker.repository;

import java.util.List;
import java.util.Optional;

import org.mocker.domain.Mapping;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author gabrielpadurean
 */
@Repository
public interface MappingRepository extends PagingAndSortingRepository<Mapping, Long> {
	List<Mapping> findByNameContaining(String name, Pageable pageable);
	
	List<Mapping> findByRequestEndpointContaining(String endpoint, Pageable pageable);
	
	List<Mapping> findByNameContainingAndRequestEndpointContaining(String name, String endpoint, Pageable pageable);
	
	Optional<Mapping> findByRequestEndpoint(String endpoint);
}