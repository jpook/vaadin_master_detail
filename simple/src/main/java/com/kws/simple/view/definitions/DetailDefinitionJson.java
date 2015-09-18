package com.kws.simple.view.definitions;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;

import com.kws.simple.view.definitions.interf.IDetailDefinition;

@Dependent
public class DetailDefinitionJson<E> implements IDetailDefinition<E>, Serializable {

	private String caption;
	private List<DetailColumnDefinitionJson<E>> fields;

	@Override
	public String getCaption() {
		return caption;
	}

	@Override
	public void setCaption(String caption) {
		this.caption = caption;
	}

	@Override
	public List<DetailColumnDefinitionJson<E>> getFields() {
		return fields;
	}

	@Override
	public void setFields(List<DetailColumnDefinitionJson<E>> fields) {
		this.fields = fields;
	}

}
