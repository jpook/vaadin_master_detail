package com.kws.simple.view.definitions.interf;

import java.util.List;

import com.kws.simple.view.definitions.DetailColumnDefinitionJson;

public interface IDetailDefinition<E> {

	public abstract String getCaption();

	public abstract void setCaption(String caption);

	public abstract List<DetailColumnDefinitionJson<E>> getFields();

	public abstract void setFields(List<DetailColumnDefinitionJson<E>> fields);

}