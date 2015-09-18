package com.kws.simple.view.views.abstr;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.kws.simple.cross.annotations.TypeBinding;
import com.kws.simple.cross.events.TableSelectionEvent;
import com.kws.simple.cross.helper.IJpoDefinitionReader;
import com.kws.simple.cross.helper.MethodHelper;
import com.kws.simple.model.entities.interf.ITableEntity;
import com.kws.simple.view.components.JpoTable;
import com.kws.simple.view.definitions.MasterColumnDefinitionJson;
import com.kws.simple.view.definitions.interf.IMasterColumnDefinition;
import com.kws.simple.view.definitions.interf.IMasterDefinition;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@Dependent
public abstract class AMasterTableView<E extends ITableEntity> extends
		CustomComponent {

	private static final long serialVersionUID = -4964321023180028846L;
	final static boolean DEFAULT_COLUMN_REORDER = true;
	final static boolean DEFAULT_SELECTABLE = true;

	@Inject
	javax.enterprise.event.Event<TableSelectionEvent> tableSelectionEvent;

	@Inject
	protected Logger log;

	@Inject
	protected MethodHelper methodHelper;

	protected IMasterDefinition<E> masterDefinition;

	@Inject
	protected IJpoDefinitionReader<E> definitionReader;

	@Inject
	protected Collection<E> entities;

	protected JpoTable table;
	protected BeanItemContainer<E> beanItemContainer;
	protected VerticalLayout verticalLayout;
	protected Label titleLabel;

	@PostConstruct
	public void init() {
		log.info("class is :" + entities.toArray().getClass().getSimpleName());
		table = new JpoTable("");
		verticalLayout = new VerticalLayout();
		titleLabel = new Label("");
		verticalLayout.addComponent(titleLabel);
		verticalLayout.addComponent(table);

		setCompositionRoot(verticalLayout);
		setSizeFull();

		initializeBeanContainer();

		masterDefinition = definitionReader.loadMaster(getClazz());
		if (masterDefinition != null) {
			initTable();
		} else {
			log.info("no table definition");
		}
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
	private void initTable() {
		table.setContainerDataSource(beanItemContainer);
		titleLabel.setValue(masterDefinition.getTableName());
		;
		table.setColumnReorderingAllowed(masterDefinition != null ? masterDefinition
				.isColumnOrder() : DEFAULT_COLUMN_REORDER);
		table.setSelectable(masterDefinition != null ? masterDefinition
				.isSelectable() : DEFAULT_SELECTABLE);
		if (masterDefinition.getVisibleColumns() != null)
			table.setVisibleColumns((Object[]) masterDefinition
					.getVisibleColumns());
		if (masterDefinition != null)
			table.addValueChangeListener((event) -> tableSelectionEvent(event));
		List<MasterColumnDefinitionJson<E>> columnList = masterDefinition
				.getColumns();
		if (columnList != null) {
			for (IMasterColumnDefinition<E> c : columnList) {
				table.setColumnHeader(c.getProperty(), c.getCaption());
				if (c.getFormat() != null)
					table.setDateFormat(c.getProperty(), c.getFormat());
			}
		}
		/*
		 * read properties generically and set them for table
		 */
		Map<String, Object> properties = masterDefinition.getProperties();
		if (properties != null && properties.size() > 0) {
			for (String key : properties.keySet()) {
				log.info(table.getCaption() + "\t" + key + "\t"
						+ properties.get(key));
				methodHelper
						.setPropertyForName(table, key, properties.get(key));
			}
		}

	}

	/*
	 * promote event to CDI event
	 */
	@SuppressWarnings("unchecked")
	private void tableSelectionEvent(ValueChangeEvent event) {
		if (event.getProperty() != null
				&& event.getProperty().getValue() != null)
			tableSelectionEvent.select(
					getTypeBinding((ITableEntity) event.getProperty()
							.getValue())).fire(
					new TableSelectionEvent(
							(BeanItem<? extends ITableEntity>) table
									.getItem(event.getProperty().getValue())));
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

}
