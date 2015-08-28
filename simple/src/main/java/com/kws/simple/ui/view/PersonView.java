package com.kws.simple.ui.view;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.HorizontalLayout;

@CDIView("person")
public class PersonView extends HorizontalLayout implements View {

	private static final long serialVersionUID = 6499378846821521117L;

	@Inject
	PersonListView listView;

	@Inject
	PersonDetailsView detailsView;

	@PostConstruct
	public void init() {
		addComponents(listView, detailsView);
	}

	@Override
	public void enter(ViewChangeEvent event) {
	}

}
