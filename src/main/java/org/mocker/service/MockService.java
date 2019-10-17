package org.mocker.service;

import static java.util.Optional.ofNullable;
import static org.slf4j.LoggerFactory.getLogger;

import org.mocker.domain.Criteria;
import org.mocker.domain.Mapping;
import org.mocker.exception.NotFoundException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gabrielpadurean
 */
@Service
public class MockService {
    private static final Logger LOG = getLogger(MockService.class);

	@Autowired
	private MappingService mappingService;
	
	
	/**
	 * Finds the mock for the given {@link Criteria} instance.
	 * 
	 * @param criteria The criteria used to find the mapping.
	 * @return The actual mapping.
	 * @throws An exception if the mapping is not found.
	 */
	public Mapping findByCriteria(Criteria criteria) {
		return ofNullable(mappingService.findByEndpoint(criteria.getEndpoint()))
				.filter(mapping -> mapping.getRequest().getMethod().equalsIgnoreCase(criteria.getMethod()))
				.map(mapping -> {
					LOG.info("Find mapping with method={} and endpoint={}", criteria.getMethod(), criteria.getEndpoint());
					
					return mapping;
				})
				.orElseThrow(() -> {
					LOG.error("Cannot find mapping (not found) with method={} and endpoint={}", criteria.getMethod(), criteria.getEndpoint());

					return new NotFoundException("Mapping with method=" + criteria.getMethod() + " and endpoint=" + criteria.getEndpoint() + " not found");
				});
	}
}