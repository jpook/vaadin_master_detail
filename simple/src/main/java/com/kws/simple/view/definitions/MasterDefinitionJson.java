package com.kws.simple.view.definitions;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.kws.simple.view.definitions.interf.IMasterDefinition;

public class MasterDefinitionJson<E> implements Serializable, IMasterDefinition<E> {

	private static final long serialVersionUID = -8205637231594443522L;

	private String tableName;
	private String className;
	private boolean columnOrder;
	private boolean selectable;

	private List<MasterColumnDefinitionJson<E>> columns;
	private String[] visibleColumns;
	private Map<String, Object> properties;

	@Override
	public String getTableName() {
		return tableName;
	}

	@Override
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@Override
	public String getClassName() {
		return className;
	}

	@Override
	public void setClassName(String className) {
		this.className = className;
	}

	@Override
	public List<MasterColumnDefinitionJson<E>> getColumns() {
		return columns;
	}

	@Override
	public void setColumns(List<MasterColumnDefinitionJson<E>> columns) {
		this.columns = columns;
	}

	@Override
	public boolean isColumnOrder() {
		return columnOrder;
	}

	@Override
	public void setColumnOrder(boolean columnOrder) {
		this.columnOrder = columnOrder;
	}

	@Override
	public boolean isSelectable() {
		return selectable;
	}

	@Override
	public void setSelectable(boolean selectable) {
		this.selectable = selectable;
	}

	@Override
	public String[] getVisibleColumns() {
		return visibleColumns;
	}

	@Override
	public void setVisibleColumns(String[] visibleColumns) {
		this.visibleColumns = visibleColumns;
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
