package com.kws.simple.view.components;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.vaadin.ui.AbstractField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;

@Dependent
public class JpoFieldFactory implements Serializable {

	private static final long serialVersionUID = -4692296925050315548L;
	@Inject
	Logger log;

	public AbstractField<?> createField(String typeName) {
		switch (typeName) {
		case ("JpoTextField"):
			return new JpoTextField();
		case ("TextField"):
			return new TextField();
		case ("PopupDateField"):
			PopupDateField pdf = new PopupDateField();
			pdf.setDateFormat("dd.MM.yyyy");
			return pdf;
		case("ComboBox"):
			return new ComboBox();
		default:
			return null;
		}

	}
}
