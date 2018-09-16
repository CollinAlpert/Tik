package de.coke.tik.services;

import com.github.collinalpert.java2db.services.BaseService;
import de.coke.tik.entities.Data;

/**
 * @author Collin Alpert
 */
public class DataService extends BaseService<Data> {
	public DataService() {
		super(Data.class);
	}
}
