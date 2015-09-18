package com.kws.simple.view.views.person;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

@CDIView("person")
public class PersonView extends VerticalLayout implements View {

	private static final long serialVersionUID = 6499378846821521117L;

	@Inject
	PersonListView listView;

	@Inject
	PersonDetailsView detailsView;

	@PostConstruct
	public void init() {
		HorizontalLayout hl = new HorizontalLayout();
		hl.addComponents(listView, detailsView);
		hl.setSizeFull();
		addComponent(hl);
		Button button = new Button("Go to Locations");
		button.addClickListener(event -> getUI().getNavigator().navigateTo(
				"location"));
		addComponent(button);
	}

	@Override
	public void enter(ViewChangeEvent event) {
	}

}
