package de.coke.tik.services;

import com.github.collinalpert.java2db.services.BaseService;
import de.coke.tik.entities.User;

import java.util.Optional;

/**
 * @author Collin Alpert
 */
public class UserService extends BaseService<User> {
	public UserService() {
		super(User.class);
	}

	public Optional<User> getUser(String email, String password) {
		return getSingle(user -> user.getEmail() == email && user.getPassword() == password);
	}
}