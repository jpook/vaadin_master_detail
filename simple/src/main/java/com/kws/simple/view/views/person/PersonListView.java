package com.kws.simple.view.views.person;

import com.kws.simple.model.entities.Person;
import com.kws.simple.view.views.abstr.AMasterTableView;

public class PersonListView extends AMasterTableView<Person> {

	private static final long serialVersionUID = -2551139460188718944L;

	@Override
	public Class<? extends Person> getClazz() {
		return Person.class;
	}

}
