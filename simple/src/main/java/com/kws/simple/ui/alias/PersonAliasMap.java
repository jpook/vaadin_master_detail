package com.kws.simple.ui.alias;

import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;

import com.kws.simple.ui.info.TableInfoObject;

@Singleton
public class PersonAliasMap extends HashMap<String,TableInfoObject> {

	 private static final long serialVersionUID = 4734003084733193710L;

	@PostConstruct 
	 public void init() {
		 this.put("lastName", new TableInfoObject(5,"Last Name"));
		 this.put("firstName", new TableInfoObject(5,"First Name"));
		 this.put("location", new TableInfoObject(5,"Location"));
		 this.put("birthDate", new TableInfoObject(5,"Date of Birth"));
	 }
}
