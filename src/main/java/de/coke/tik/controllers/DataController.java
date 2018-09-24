package de.coke.tik.controllers;

import com.github.collinalpert.java2db.utilities.IoC;
import de.coke.tik.entities.Data;
import de.coke.tik.entities.User;
import de.coke.tik.enums.EntityDisplayTypes;
import de.coke.tik.services.DataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

/**
 * @author Collin Alpert
 */
@Controller
public class DataController {

	@GetMapping("/data/add")
	public String addData(Model model) {
		model.addAttribute("data", new Data());
		model.addAttribute("entityDisplayType", EntityDisplayTypes.CREATE);
		return "dataTemplate";
	}

	@GetMapping("/data/view/{id}")
	public String viewData(@PathVariable long id, Model model, HttpSession session) {
		return handleData(id, model, session, EntityDisplayTypes.VIEW);
	}

	@GetMapping("/data/edit/{id}")
	public String editData(@PathVariable long id, Model model, HttpSession session) {
		return handleData(id, model, session, EntityDisplayTypes.EDIT);
	}

	@PostMapping("/data/delete/{id}")
	public String deleteData(@PathVariable long id, HttpSession session) {
		return handleData(id, null, session, EntityDisplayTypes.DELETE);
	}

	private String handleData(long id, Model model, HttpSession session, EntityDisplayTypes displayType) {
		if (session.getAttribute("user") == null) {
			return "redirect:/login";
		}
		var user = (User) session.getAttribute("user");
		var service = IoC.resolveService(DataService.class);
		var data = service.getById(id);
		if (!data.isPresent() || (data.get().getUserId() != user.getId() && !user.isAdmin())) {
			return "redirect:/";
		}
		if (displayType == EntityDisplayTypes.DELETE) {
			service.delete(data.get());
			return "redirect:/";
		}
		model.addAttribute("data", data);
		model.addAttribute("entityDisplayType", displayType);
		return "dataTemplate";
	}

	@PostMapping("save")
	public String save(@ModelAttribute("data") Data data) {
		var service = IoC.resolveService(DataService.class);
		try {
			if (data.getId() == 0) {
				service.create(data);
			} else {
				service.update(data);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "redirect:/";
	}
}
