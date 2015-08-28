package com.kws.simple.mock;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.inject.Singleton;

import com.kws.simple.model.Person;

@Singleton
public class PersonListMock extends ArrayList<Person>{

	private static final long serialVersionUID = -2744529053867025082L;

	public PersonListMock() {
		Person p1 = new Person("Heinz", "Schuh", (new GregorianCalendar(1972,
				12, 24)).getTime(), null);
		Person p2 = new Person("Horst", "Strunk", (new GregorianCalendar(1974,
				8, 16)).getTime(), null);
		Person p3 = new Person("Rüdiger", "Hoffmann", (new GregorianCalendar(
				1970, 4, 8)).getTime(), null);
		
		this.add(p1);
		this.add(p2);
		this.add(p3);
	}

}
