package org.mocker.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * All the actions for viewing, updating and deleting happen from the same page.
 * 
 * @author gabrielpadurean
 */
@Controller
public class ViewPageController {
	private static final String VIEW_PAGE = "view.html";
	
	
	@GetMapping("/view")
	public String getViewPage() {
		return VIEW_PAGE;
	}
	
	@GetMapping("/update")
	public String getUpdatePage() {
		return VIEW_PAGE;
	}
	
	@GetMapping("/delete")
	public String getDeletePage() {
		return VIEW_PAGE;
	}
}