package com.kws.simple.cross.events;

import com.kws.simple.model.entities.interf.ITableEntity;
import com.vaadin.data.util.BeanItem;

public class TableSelectionEvent {

	BeanItem<? extends ITableEntity> beanItem;

	public TableSelectionEvent(BeanItem<? extends ITableEntity> beanItem) {
		super();
		this.beanItem = beanItem;
	}

	public BeanItem<? extends ITableEntity> getBeanItem() {
		return beanItem;
	}

	public void setBeanItem(BeanItem<? extends ITableEntity> beanItem) {
		this.beanItem = beanItem;
	}

}
