package com.kws.simple.ui.view;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.kws.simple.mock.PersonListMock;
import com.kws.simple.model.Person;
import com.kws.simple.ui.alias.PersonAliasMap;
import com.kws.simple.ui.info.TableInfoObject;
import com.kws.simple.ui.view.abstr.AMasterTableView;

public class PersonListView extends AMasterTableView<Person> {

	private static final long serialVersionUID = -2551139460188718944L;

	@Inject
	PersonListMock mock;

	@Inject
	PersonAliasMap aliasMap;
	
	@PostConstruct
	public void init() {
		setEntities(mock);
		super.init();
	}

	@Override
	public Class<? extends Person> getClazz() {
		return Person.class;
	}

	@Override
	public Map<String, TableInfoObject> getAliasMap() {
		return aliasMap;
	}

}
