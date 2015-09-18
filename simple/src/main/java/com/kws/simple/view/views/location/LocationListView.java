package com.kws.simple.view.views.location;

import com.kws.simple.model.entities.Location;
import com.kws.simple.view.views.abstr.AMasterTableView;

public class LocationListView extends AMasterTableView<Location> {

	private static final long serialVersionUID = -8108451201101329177L;

	@Override
	public Class<? extends Location> getClazz() {
		return Location.class;
	}

}
