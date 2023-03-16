package edu.spring.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes("user")
public class HelloController {
	
	@GetMapping("/")
	@ResponseBody
	public String helloAction(HttpSession session) {
		if(session.getAttribute("user") != null) {
			return "Bonjour " + session.getAttribute("user");
		}
		return "Hello World !";
	}
	
	@GetMapping("/msg/{msg}")
	@ResponseBody
	public String msgAction(@PathVariable("msg") String msg) {
		return "messge : " + msg;
	}

	@GetMapping("/view/{message}")
	public String withViewAction(
			@PathVariable("message") String msg,
			ModelMap model) {
		String titre = "Connexion";
		model.addAttribute("titre", titre);
		return"/helloView";
	}
	
	@PostMapping("/submit")
	@ResponseBody
	public String submitAction(@ModelAttribute("login")
								String login,
								HttpSession session
			) {
		session.setAttribute("user", login);
		return "Vous êtes connecté sous le login : " + login;
	}
	
	@GetMapping("/logout")
	@ResponseBody
	public String logoutAction(HttpSession session) {
		
		session.invalidate();
		return "Vous êtes déconnecté";
	}
	
}
