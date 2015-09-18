package com.kws.simple.view.views.location;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

@CDIView("location")
public class LocationView extends VerticalLayout implements View {

	private static final long serialVersionUID = -8179300230225453292L;

	@Inject
	Logger log;

	@Inject
	LocationListView listView;

	@Inject
	LocationDetailsView detailsView;

	@PostConstruct
	public void init() {
		HorizontalLayout hl = new HorizontalLayout();
		hl.addComponents(listView, detailsView);
		addComponent(hl);
		Button button = new Button("Go to Persons");
		button.addClickListener(event -> getUI().getNavigator().navigateTo(
				"person"));
		addComponent(button);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}

}
