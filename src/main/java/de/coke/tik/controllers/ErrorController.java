package de.coke.tik.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Collin Alpert
 */
@Controller
public class ErrorController {

	@GetMapping("/error")
	public String error() {
		return "error";
	}
}