package com.kws.simple.view.components;

import com.kws.simple.view.components.interf.IJpoTextField;
import com.vaadin.ui.TextField;

public class JpoTextField extends TextField implements IJpoTextField {

	private static final long serialVersionUID = 3364101872815486069L;

	// Logger log;

	TextField label = new TextField();

	public JpoTextField() {
		// this.log = log;
		setImmediate(true);
		setVisible(true);
		setEnabled(false);
		setNullSettingAllowed(true);
		setNullRepresentation("");
		addTextChangeListener(event -> setValue(event.getText()));
	}

	// public boolean checkForLogger() {
	// return log != null;
	// }

}
