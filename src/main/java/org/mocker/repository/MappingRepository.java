package org.mocker.repository;

import java.util.Optional;

import org.mocker.domain.Mapping;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author gabrielpadurean
 */
@Repository
public interface MappingRepository extends PagingAndSortingRepository<Mapping, Long> {
	Optional<Mapping> findMappingByRequestEndpoint(String endpoint);
}