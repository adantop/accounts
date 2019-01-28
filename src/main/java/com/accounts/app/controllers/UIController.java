package com.accounts.app.controllers;

import java.text.ParseException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.accounts.app.entities.Account;
import com.accounts.app.services.IAccountService;
import com.accounts.app.services.IRestService;

/**
 * The controller for the front page and a couple of extra websites to easily
 * view the information for the accounts. This is not complete, the controllers
 * should be connecting to the RestService (use the API) instead of going directly
 * to the AccountService
 * 
 * @author adantop
 *
 */
@Controller
public class UIController {

	@Autowired
	private IAccountService accountSvc;
	
	@Autowired
	private IRestService restSvc;

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	/**
	 * Redirecting to root for consistency
	 * 
	 * @return
	 */
	@RequestMapping({ "home", "index", "index.html", "index.jsp" })
	public String home() {
		return "redirect:/";
	}

	@RequestMapping("/accounts")
	public String accounts(Model model) {

		model.addAttribute("accounts", accountSvc.findAll());
		return "/accounts";
	}

	@PostMapping("/save")
	public String saveAccount(Account account) throws ParseException {

		accountSvc.save(account);
		return "redirect:/accounts";
	}

	@GetMapping("/new")
	public String newAccount(Model model) {

		model.addAttribute("account", new Account());
		return "/account_details";
	}

	@GetMapping("/edit/{id}")
	public String editAccount(@PathVariable(value = "id") Long id, Model model) {

		Optional<Account> account = accountSvc.findOne(id);

		if (account.isPresent()) {
			model.addAttribute("account", account.get());
			return "/account_details";
		} else
			return "redirect:/accounts";
	}

	@GetMapping("/delete/{id}")
	public String deleteAccount(@PathVariable(value = "id") Long id) {

		if (id > 0) {
			accountSvc.delete(id);
		}
		return "redirect:/accounts";
	}
	
	/**
	 * Testing method to implement the Rest Service
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/test")
	public String test(Model model) {
		
		Account account = restSvc.findOne();
		model.addAttribute("account", account);
		
		return "/account_details";
	}
	
	@GetMapping("/api")
	public String swagger() {
		
		return "redirect:/swagger-ui.html";
	}
}
