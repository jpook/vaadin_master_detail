package com.kws.simple.view.views.person;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.kws.simple.cross.annotations.EntityTypeQualifier;
import com.kws.simple.cross.events.TableSelectionEvent;
import com.kws.simple.model.entities.Location;
import com.kws.simple.model.entities.Person;
import com.kws.simple.model.service.mock.LocationListMock;
import com.kws.simple.view.components.JpoTextField;
import com.kws.simple.view.validators.JpoScoreValidator;
import com.kws.simple.view.views.abstr.ADetailsView;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;

@SessionScoped
public class PersonDetailsView extends ADetailsView<Person> implements
		Serializable {

	private static final long serialVersionUID = 2211136995898770619L;

	@Inject
	LocationListMock locationList;

	@Inject
	Logger log;

	public PersonDetailsView() {
	}

	@PostConstruct
	public void init() {
		super.init();
		ComboBox cb = (ComboBox)fieldMap.get("location");
		BeanItemContainer<Location> bic = new BeanItemContainer<Location>(Location.class);
		bic.addAll(locationList);
		cb.setContainerDataSource(bic);
		JpoTextField id = (JpoTextField) fieldMap.get("id");
		id.addValidator(new JpoScoreValidator());
		Button b = new Button("mark as dirty");
		layout.addComponent(b);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void fillForm(
			@Observes @EntityTypeQualifier(Person.class) TableSelectionEvent event) {
		super.fillForm((BeanItem<Person>) event.getBeanItem());
	}

	@Override
	protected Class<Person> getClazz() {
		return Person.class;
	}

	public LocationListMock getLocationList() {
		return locationList;
	}

	public void setLocationList(LocationListMock locationList) {
		this.locationList = locationList;
	}

}
