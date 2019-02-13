package de.coke.tik.modules;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * @author Collin Alpert
 */
public class EntityValidationModule<T> {

	private String[] propertiesToValidate;

	public EntityValidationModule(String... propertiesToValidate) {
		this.propertiesToValidate = Objects.requireNonNullElse(propertiesToValidate, new String[0]);
	}

	public boolean isValid(T entity) {
		var fields = entity.getClass().getDeclaredFields();
		if (propertiesToValidate.length == 0) {
			for (Field field : fields) {
				try {
					if (field.get(entity) == null) {
						return false;
					}
				} catch (IllegalAccessException e) {
					e.printStackTrace();
					return false;
				}
			}

			return true;
		}

		for (String validationProperty : propertiesToValidate) {
			for (Field field : fields) {
				try {
					if (field.getName().equals(validationProperty) && field.get(entity) == null) {
						return false;
					}
				} catch (IllegalAccessException e) {
					e.printStackTrace();
					return false;
				}
			}
		}

		return true;
	}
}
