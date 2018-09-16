package de.coke.tik.services;

import com.github.collinalpert.java2db.services.BaseCodeAndDescriptionService;
import de.coke.tik.entities.SystemParameter;

/**
 * @author Collin Alpert
 */
public class SystemParameterService extends BaseCodeAndDescriptionService<SystemParameter> {
	public SystemParameterService() {
		super(SystemParameter.class);
	}
}
