package de.coke.tik.services;

import com.github.collinalpert.java2db.services.BaseService;
import com.github.collinalpert.java2db.utilities.EmptyResultSetException;
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
		try {
			return Optional.of(getSingle(user -> user.getEmail() == email && user.getPassword() == password));
		} catch (EmptyResultSetException e) {
			return Optional.empty();
		}
	}
}
