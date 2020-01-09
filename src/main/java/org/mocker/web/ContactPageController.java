package org.mocker.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author gabrielpadurean
 */
@Controller
public class ContactPageController {
	private static final String CONTACT_PAGE = "contact.html";
	
	
	@GetMapping("/contact")
	public String getContactPage() {
		return CONTACT_PAGE;
	}
}