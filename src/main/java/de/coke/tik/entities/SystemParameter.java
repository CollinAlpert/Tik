package de.coke.tik.entities;

import com.github.collinalpert.java2db.annotations.TableName;
import com.github.collinalpert.java2db.entities.BaseCodeAndDescriptionEntity;

/**
 * @author Collin Alpert
 */
@TableName("systemParameter")
public class SystemParameter extends BaseCodeAndDescriptionEntity {

	private String value;

	public String getValue() {
		return value;
	}
}
