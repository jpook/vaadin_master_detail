package com.kws.simple.view.components;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import com.vaadin.data.util.converter.StringToDateConverter;
import com.vaadin.ui.Table;

public class JpoTable extends Table implements Serializable {

	public JpoTable(String string) {
		super(string);
	}

	private static final long serialVersionUID = -4183046853260921326L;

	public void setDateFormat(String propertyId, String format) {
		
		this.setConverter(propertyId, new StringToDateConverter() {
			@Override
			public DateFormat getFormat(Locale locale) {
				return new SimpleDateFormat(format);
			}
		});
	}
}
