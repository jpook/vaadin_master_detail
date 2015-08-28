package com.kws.simple.events;

import com.kws.simple.model.interf.ITableEntity;
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
