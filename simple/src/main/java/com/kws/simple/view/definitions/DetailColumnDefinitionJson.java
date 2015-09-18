package com.kws.simple.view.definitions;

import java.util.Map;

import javax.enterprise.context.Dependent;

import com.kws.simple.view.definitions.interf.IDetailColumnDefinition;

@Dependent
public class DetailColumnDefinitionJson<E> implements IDetailColumnDefinition<E> {

	private String className;
	private String property;
	private String caption;
	private Map<String, Object> properties;

	@Override
	public String getClassName() {
		return className;
	}

	@Override
	public void setClassName(String className) {
		this.className = className;
	}

	@Override
	public String getProperty() {
		return property;
	}

	@Override
	public void setProperty(String property) {
		this.property = property;
	}

	@Override
	public String getCaption() {
		return caption;
	}

	@Override
	public void setCaption(String caption) {
		this.caption = caption;
	}

	@Override
	public String toString() {
		return "Detail :\t" + property + "\t" + caption + "\t";
	}

	@Override
	public Map<String, Object> getProperties() {
		return properties;
	}

	@Override
	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

}
