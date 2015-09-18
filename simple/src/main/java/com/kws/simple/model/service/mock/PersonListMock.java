package com.kws.simple.model.service.mock;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.kws.simple.model.entities.Location;
import com.kws.simple.model.entities.Person;

@Singleton
public class PersonListMock extends ArrayList<Person> {

	private static final long serialVersionUID = -2744529053867025082L;

	@Inject
	public PersonListMock(LocationListMock locationList) {
		Location loc = null;

		if (locationList != null && locationList.size() > 0)
			loc = locationList.get(0);
		else
			System.out.println("no location list");
		Person p1 = new Person("Heinz", "Schuh", (new GregorianCalendar(1972,
				12, 24)).getTime(), loc);
		Person p2 = new Person("Horst", "Strunk", (new GregorianCalendar(1974,
				8, 16)).getTime(), null);
		Person p3 = new Person("Rüdiger", "Hoffmann", (new GregorianCalendar(
				1970, 4, 8)).getTime(), null);

		this.add(p1);
		this.add(p2);
		this.add(p3);
	}

}
