package org.mocker.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author gabrielpadurean
 */
@Controller
public class CreatePageController {
	private static final String CREATE_PAGE = "create.html";
	
	
	@GetMapping({"/", "/create"})
	public String getCreatePage() {
		return CREATE_PAGE;
	}
}