package com.kws.simple.events;

import com.kws.simple.model.interf.ITableEntity;

public class SaveEntityEvent {

	ITableEntity entity;

	public SaveEntityEvent(ITableEntity entity) {
		super();
		this.entity = entity;
	}

	public ITableEntity getEntity() {
		return entity;
	}

	public void setEntity(ITableEntity entity) {
		this.entity = entity;
	}
	
	
}
