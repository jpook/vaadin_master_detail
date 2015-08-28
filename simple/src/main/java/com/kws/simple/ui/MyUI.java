package com.kws.simple.ui;

import javax.inject.Inject;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.cdi.CDIUI;
import com.vaadin.cdi.CDIViewProvider;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

/**
 *
 */
@Theme("mytheme")
@Widgetset("com.kws.simple.MyAppWidgetset")
@CDIUI("")
public class MyUI extends UI {

	private static final long serialVersionUID = -8797171170005847565L;
	@Inject
	CDIViewProvider cdiViewProvider;

	@Override
	protected void init(VaadinRequest vaadinRequest) {

		Navigator navigator = new Navigator(this, this);
		navigator.addProvider(cdiViewProvider);
		navigator.navigateTo("person");
	}

}
