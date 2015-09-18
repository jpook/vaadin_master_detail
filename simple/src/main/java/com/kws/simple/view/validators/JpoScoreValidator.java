package com.kws.simple.view.validators;

public class JpoScoreValidator extends JpoIntegerRangeValidator {

	public JpoScoreValidator() {
		super("not a score value [1-9]", 1, 9);
	}

}
