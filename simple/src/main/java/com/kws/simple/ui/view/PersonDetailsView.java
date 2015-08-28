package com.kws.simple.ui.view;

import java.util.Map;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import com.kws.simple.annotations.EntityTypeQualifier;
import com.kws.simple.events.TableSelectionEvent;
import com.kws.simple.model.Person;
import com.kws.simple.ui.alias.PersonDetailsMap;
import com.kws.simple.ui.info.EntityInfoObject;
import com.kws.simple.ui.view.abstr.ADetailsView;
import com.vaadin.data.util.BeanItem;

public class PersonDetailsView extends ADetailsView<Person> {

	private static final long serialVersionUID = 2211136995898770619L;
	@Inject
	PersonDetailsMap personDetailsMap;

	@SuppressWarnings("unchecked")
	@Override
	protected void fillForm(
			@Observes @EntityTypeQualifier(Person.class) TableSelectionEvent event) {
		super.fillForm((BeanItem<Person>) event.getBeanItem());
	}

	@Override
	protected Map<String, EntityInfoObject> getEntityMap() {
		return personDetailsMap;
	}

}
