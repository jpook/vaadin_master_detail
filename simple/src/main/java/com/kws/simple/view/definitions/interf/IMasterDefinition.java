package com.kws.simple.view.definitions.interf;

import java.util.List;
import java.util.Map;

import com.kws.simple.view.definitions.MasterColumnDefinitionJson;

public interface IMasterDefinition<E> {

	public String getTableName();

	public void setTableName(String tableName);

	public String getClassName();

	public void setClassName(String className);

	public List<MasterColumnDefinitionJson<E>> getColumns();

	public void setColumns(List<MasterColumnDefinitionJson<E>> columns);

	public boolean isColumnOrder();

	public void setColumnOrder(boolean columnOrder);

	public boolean isSelectable();

	public void setSelectable(boolean selectable);

	public String[] getVisibleColumns();

	public void setVisibleColumns(String[] visibleColumns);

	public Map<String, Object> getProperties();

	public void setProperties(Map<String, Object> properties);

}