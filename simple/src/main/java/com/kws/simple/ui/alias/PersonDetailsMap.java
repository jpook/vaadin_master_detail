package com.kws.simple.ui.alias;

import java.util.HashMap;

import javax.annotation.PostConstruct;

import com.kws.simple.ui.info.EntityInfoObject;
import com.vaadin.data.validator.IntegerRangeValidator;

/*
 * aufpassen, kann in Mehrnutzerumgebung anders sein bzw muss aus Properties bzw
 * json geladen werden
 */
public class PersonDetailsMap extends HashMap<String, EntityInfoObject> {
	private static final long serialVersionUID = -3778501881399722033L;

	@PostConstruct
	public void init() {
		this.put("id", new EntityInfoObject(1, "Identifier", "TextField"));
		this.get("id").addValidator(new IntegerRangeValidator("not in range", 1, 9));
		this.put("lastName", new EntityInfoObject(1, "Last name", "TextField"));
		this.put("firstName",
				new EntityInfoObject(2, "First name", "TextField"));
		this.put("birthDate", new EntityInfoObject(3, "Birthday",
				"PopupDateField"));
	}
}
