package org.mocker.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author gabrielpadurean
 */
@Controller
public class AboutPageController {
	private static final String ABOUT_PAGE = "about.html";
	
	
	@GetMapping("/about")
	public String getAboutPage() {
		return ABOUT_PAGE;
	}
}