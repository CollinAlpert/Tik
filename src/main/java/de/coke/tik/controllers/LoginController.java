package de.coke.tik.controllers;

import com.github.collinalpert.java2db.utilities.IoC;
import de.coke.tik.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Collin Alpert
 */
@Controller
public class LoginController {

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/login")
	public String login(@RequestParam String email, @RequestParam String password, Model model) {
		var userOptional = IoC.resolveService(UserService.class).getUser(email, hash(password));
		if (!userOptional.isPresent()) {
			model.addAttribute("invalid", true);
			return "login";
		}
		model.addAttribute("user", userOptional.get());
		return "index";
	}

	private String hash(String password) {
		try {
			var md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] bytes = md.digest();
			var sb = new StringBuilder();
			for (byte aByte : bytes) {
				sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			return "";
		}
	}
}
