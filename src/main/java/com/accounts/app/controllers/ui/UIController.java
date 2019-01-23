package com.accounts.app.controllers.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UIController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	/**
	 * Redirecting to root for consistency
	 * @return
	 */
	@RequestMapping({"home", "index", "index.html", "index.jsp"})
	public String home() {
		return "redirect:/";
	}
}
