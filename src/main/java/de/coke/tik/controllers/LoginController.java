package de.coke.tik.controllers;

import com.github.collinalpert.java2db.utilities.IoC;
import de.coke.tik.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * @author Collin Alpert
 */
@Controller
public class LoginController extends BaseController {


	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/login")
	public String login(@RequestParam String email, @RequestParam String password, Model model, HttpSession session) {
		var userOptional = IoC.resolveService(UserService.class).getUser(email, hash(password));
		if (!userOptional.isPresent()) {
			model.addAttribute("invalid", true);
			return "login";
		}

		session.setAttribute("user", userOptional.get());
		return "redirect:/";
	}
}