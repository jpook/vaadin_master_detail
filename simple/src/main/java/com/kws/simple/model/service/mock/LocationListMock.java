package com.kws.simple.model.service.mock;

import java.util.ArrayList;

import javax.inject.Singleton;

import com.kws.simple.model.entities.Location;

@Singleton
public class LocationListMock extends ArrayList<Location> {

	private static final long serialVersionUID = 4111377144527827015L;

	public LocationListMock() {
		Location l1 = new Location(37619, "Bodenwerder", "Große Straße", 41, "mitten in der Stadt");
		Location l2 = new Location(37574, "Einbeck", "Grimsehlstrasse", 31, "KWS");
		Location l3 = new Location(37603, "Holzminden", "Mühlenfeldstrasse", 1, "da stinkts");
		this.add(l1);
		this.add(l2);
		this.add(l3);
	}
}
