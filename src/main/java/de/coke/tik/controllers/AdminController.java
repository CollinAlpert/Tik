package de.coke.tik.controllers;

import com.github.collinalpert.java2db.utilities.IoC;
import de.coke.tik.entities.User;
import de.coke.tik.modules.EntityValidationModule;
import de.coke.tik.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

/**
 * @author Collin Alpert
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {

	@GetMapping("/")
	public String getUsers(Model model, HttpSession session) {
		if (!isAdminLoggedIn(session)) {
			return "redirect:/login";
		}

		model.addAttribute("users", IoC.resolveService(UserService.class).getAll());
		return "admin/index";
	}

	@GetMapping("/createUser")
	public String create(HttpSession session) {
		if (!isAdminLoggedIn(session)) {
			return "redirect:/login";
		}

		return "admin/createUser";
	}

	@PostMapping("/createUser")
	public String createUser(@RequestParam User user, Model model, HttpSession session) {
		if (!isAdminLoggedIn(session)) {
			return "redirect:/login";
		}

		user.setAdmin(false);
		var validation = new EntityValidationModule<User>();
		if (!validation.isValid(user)) {
			model.addAttribute("invalid", true);
			return "admin/createUser";
		}

		try {
			IoC.resolveService(UserService.class).create(user);
		} catch (SQLException e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", e.getMessage());
			return "error";
		}

		return "redirect:/admin/";
	}

}
