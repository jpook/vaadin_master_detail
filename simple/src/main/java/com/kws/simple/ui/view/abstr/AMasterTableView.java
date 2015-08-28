package com.kws.simple.ui.view.abstr;

import java.util.Collection;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;

import com.kws.simple.annotations.TypeBinding;
import com.kws.simple.events.TableSelectionEvent;
import com.kws.simple.helper.MethodHelper;
import com.kws.simple.model.interf.ITableEntity;
import com.kws.simple.ui.info.TableInfoObject;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public abstract class AMasterTableView<E extends ITableEntity> extends
		CustomComponent {

	private static final long serialVersionUID = -4964321023180028846L;

	@Inject
	javax.enterprise.event.Event<TableSelectionEvent> tableSelectionEvent;

	@Inject
	Logger log;

	protected Collection<E> entities;
	protected Table table = new Table("Table");
	protected BeanItemContainer<E> beanItemContainer;
	protected VerticalLayout verticalLayout;

	public void init() {
		verticalLayout = new VerticalLayout();
		Label label = new Label("Master View");
		verticalLayout.addComponent(label);
		verticalLayout.addComponent(table);

		setCompositionRoot(verticalLayout);
		setSizeFull();

		initializeBeanContainer();
		initializeTable();
	}

	@SuppressWarnings("unchecked")
	private void initializeBeanContainer() {
		beanItemContainer = new BeanItemContainer<E>(
				(Class<? super E>) getClazz());

		if (entities != null)
			beanItemContainer.addAll(entities);
	}

	/*
	 * this should come from json and/or naming convention
	 */
	private void initializeTable() {
		table.setContainerDataSource(beanItemContainer);

		Map<String, TableInfoObject> aliasMap = getAliasMap();

		if (aliasMap != null) {
			for (String key : aliasMap.keySet()) {
				String value = aliasMap.get(key).getHeader();
				if (MethodHelper.getterForPropertyExists(getClazz(), key)
						&& table.getColumnHeader(key) != null) {
					table.setColumnHeader(key, value);
				}
			}

			table.setVisibleColumns(aliasMap.keySet().toArray());
		}
		table.setColumnReorderingAllowed(true);
		table.setSelectable(true);

		table.addValueChangeListener((event) -> tableSelectionEvent(event));

	}

	/*
	 * promote event to CDI event
	 */
	@SuppressWarnings("unchecked")
	private void tableSelectionEvent(ValueChangeEvent event) {
		tableSelectionEvent.select(
				getTypeBinding((ITableEntity) event.getProperty().getValue()))
				.fire(new TableSelectionEvent(
						(BeanItem<? extends ITableEntity>) table.getItem(event
								.getProperty().getValue())));
	}

	private TypeBinding getTypeBinding(ITableEntity entity) {
		return new TypeBinding() {
			private static final long serialVersionUID = 3419449904722350133L;

			public Class<? extends ITableEntity> value() {
				return entity.getClass();
			}
		};
	}

	public Collection<E> getEntities() {
		return entities;
	}

	public void setEntities(Collection<E> entities) {
		this.entities = entities;
	}

	public abstract Class<? extends E> getClazz();

	public abstract Map<String, TableInfoObject> getAliasMap();

}
