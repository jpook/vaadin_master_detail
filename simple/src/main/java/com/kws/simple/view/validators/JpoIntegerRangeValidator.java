package com.kws.simple.view.validators;

import com.vaadin.data.validator.IntegerRangeValidator;

public class JpoIntegerRangeValidator extends IntegerRangeValidator {

	public JpoIntegerRangeValidator(String errorMessage, Integer minValue,
			Integer maxValue) {
		super(errorMessage, minValue, maxValue);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = -63740432008109687L;

	@Override
	public void validate(Object value) {
//		System.out.println("value : " + value.toString());
		super.validate(value);
	}
}
