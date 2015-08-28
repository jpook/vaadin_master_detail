package com.kws.simple.ui.info;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.vaadin.data.Validator;

public class EntityInfoObject implements Serializable {

	private static final long serialVersionUID = -6578248174902731669L;
	Integer order;
	String label;
	String typeName;
	boolean editable;
	Set<Validator> validatorSet;

	public EntityInfoObject(Integer order, String label, String typeName) {
		super();
		this.order = order;
		this.label = label;
		this.typeName = typeName;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public Set<Validator> getValidatorSet() {
		return validatorSet;
	}

	public void setValidatorSet(Set<Validator> validatorSet) {
		this.validatorSet = validatorSet;
	}

	public void addValidator(Validator validator) {
		if (validatorSet == null)
			validatorSet = new HashSet<Validator>();
		validatorSet.add(validator);
	}

	public void removeValidator(Validator validator) {
		if (validatorSet != null && validatorSet.contains(validator))
			validatorSet.remove(validator);
	}

}
