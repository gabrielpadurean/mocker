package org.mocker.web;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author gabrielpadurean
 */
public class HomePageController {
	private static final String HOME_PAGE = "index.html";
	
	
	@GetMapping("/")
	public String getHomePage() {
		return HOME_PAGE;
	}
}