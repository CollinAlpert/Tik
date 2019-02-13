package de.coke.tik.controllers;

import de.coke.tik.entities.User;

import javax.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Collin Alpert
 */
public class BaseController {

	protected String hash(String password) {
		try {
			var md = MessageDigest.getInstance("SHA-256");
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

	protected boolean isAuthenticated(HttpSession session) {
		return session.getAttribute("user") != null;
	}

	protected boolean isAdminLoggedIn(HttpSession session) {
		User user;
		return (user = (User) session.getAttribute("user")) != null && user.isAdmin();
	}
}
