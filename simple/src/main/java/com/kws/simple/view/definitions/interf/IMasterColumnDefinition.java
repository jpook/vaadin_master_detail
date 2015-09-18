package com.kws.simple.view.definitions.interf;

import java.util.Map;

public interface IMasterColumnDefinition<E> {

	public String getClassName();

	public void setClassName(String className);

	public String getProperty();

	public void setProperty(String property);

	public String getCaption();

	public void setCaption(String caption);

	public String toString();

	public Map<String, Object> getProperties();

	public void setProperties(Map<String, Object> properties);

	public String getFormat();

	public void setFormat(String format);

}