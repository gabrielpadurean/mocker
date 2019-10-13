package org.mocker.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author gabrielpadurean
 */
@Service
public class ClientService {
	@Value("${clients.whitelisted}")
	private Collection<String> whitelistedClients;
	
	
	/**
	 * Verifies if the given client value is marked as whitelisted.
	 * If a client is not whitelisted, it shouldn't be handled by the application.
	 */
	public boolean isClientWhitelisted(String client) {
		return whitelistedClients.contains(client);
	}
}