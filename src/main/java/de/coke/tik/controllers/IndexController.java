package de.coke.tik.controllers;

import de.coke.tik.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

/**
 * @author Collin Alpert
 */
@Controller
public class IndexController {

	@GetMapping("/")
	public String index(HttpSession session, Model model) {
		if (session.getAttribute("user") == null) {
			return "redirect:/login";
		}

		var user = (User) session.getAttribute("user");
		model.addAttribute("user", user);

		return "index";
	}
}