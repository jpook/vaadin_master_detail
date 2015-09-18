package com.kws.simple.view;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.kws.simple.cross.annotations.EntityTypeQualifier;
import com.kws.simple.cross.events.TableSelectionEvent;
import com.kws.simple.model.entities.Location;
import com.kws.simple.model.entities.Person;

public class TestObserver {

	@Inject 
	Logger log;
	
	public void observePerson(
			@Observes @EntityTypeQualifier(Person.class) TableSelectionEvent event) {
//		log.info("Person observed : " + event.getBeanItem().toString());
	}

	public void observeLocation(
			@Observes @EntityTypeQualifier(Location.class) TableSelectionEvent event) {
		log.info("Location observed");
	}
}
