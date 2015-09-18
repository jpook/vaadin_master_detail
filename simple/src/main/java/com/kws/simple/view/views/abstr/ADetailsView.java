package com.kws.simple.view.views.abstr;

import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.kws.simple.cross.annotations.TypeBinding;
import com.kws.simple.cross.events.SaveEntityEvent;
import com.kws.simple.cross.events.TableSelectionEvent;
import com.kws.simple.cross.helper.JsonDefinitionReader;
import com.kws.simple.cross.helper.MethodHelper;
import com.kws.simple.model.entities.interf.ITableEntity;
import com.kws.simple.view.components.JpoFieldFactory;
import com.kws.simple.view.definitions.DetailColumnDefinitionJson;
import com.kws.simple.view.definitions.interf.IDetailColumnDefinition;
import com.kws.simple.view.definitions.interf.IDetailDefinition;
import com.vaadin.data.Property.ValueChangeNotifier;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.BindException;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.FieldEvents.TextChangeNotifier;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public abstract class ADetailsView<E extends ITableEntity> extends
		CustomComponent {

	private static final long serialVersionUID = 5331836278413759109L;

	@Inject
	Logger log;

	@Inject
	javax.enterprise.event.Event<SaveEntityEvent> saveEntityEvent;

	@Inject
	JpoFieldFactory fieldFactory;

	@Inject
	MethodHelper methodHelper;

	protected Layout layout;

	protected IDetailDefinition<E> detailDefinition;

	@Inject
	protected JsonDefinitionReader<E> jsonReader;

	BeanItem<E> item;
	protected FieldGroup fieldGroup;
	protected Map<String, AbstractField<?>> fieldMap;
	Button saveButton;
	Button cancelButton;
	Set<AbstractField<?>> dirtyFields = new HashSet<AbstractField<?>>();

	@PostConstruct
	public void init() {
		printTv();
		
		/*
		 * basic layout settings
		 */
		layout = new VerticalLayout();
		layout.setVisible(false);
		setCompositionRoot(layout);
		setSizeFull();

		/*
		 * load definition from json
		 */
		detailDefinition = jsonReader.loadDetail(getClazz());

		Label label = new Label(detailDefinition.getCaption());
		layout.addComponent(label);

		createFieldsForForm();
		bindFields();
		createButtonBar();

	}

	/*
	 * creates save and cancel button bar
	 */
	private void createButtonBar() {
		HorizontalLayout buttonLayout = new HorizontalLayout();
		/*
		 * create buttons and disable them
		 */
		saveButton = new Button("Save");
		cancelButton = new Button("Cancel");
		saveButton.setEnabled(false);
		cancelButton.setEnabled(false);
		
		/*
		 * add action on click
		 */
		saveButton.addClickListener(event -> save());
		cancelButton.addClickListener(event -> cancel());

		buttonLayout.addComponents(saveButton, cancelButton);
		layout.addComponent(buttonLayout);
	}

	private void createFieldsForForm() {
		/*
		 * get list of column definitions
		 */
		List<DetailColumnDefinitionJson<E>> fields = detailDefinition
				.getFields();
		if (fields == null)
			return;
		fieldMap = new HashMap<String, AbstractField<?>>();
		/*
		 * iterate through list and create fields
		 */
		for (IDetailColumnDefinition<E> d : fields) {
			AbstractField<?> field = createField(d);
			fieldMap.put(d.getProperty(), field);
		}
	}

	private AbstractField<?> createField(IDetailColumnDefinition<E> d) {
		/*
		 * create field from class name given in description object
		 */
		AbstractField<?> field = fieldFactory.createField(d.getClassName());
		field.setCaption(d.getCaption());
		/*
		 * notify, if field has changed
		 */
		if (field instanceof TextChangeNotifier) {
			((TextChangeNotifier) field)
					.addTextChangeListener(event -> formHasChanged(field));
		} else if (field instanceof ValueChangeNotifier)
			field.addValueChangeListener(event -> formHasChanged(field));
		/*
		 * add field to component
		 */
		layout.addComponent(field);
		return field;
	}

	private void formHasChanged(AbstractField<?> field) {
		/*
		 * enable cancel if form has changed
		 */
		cancelButton.setEnabled(true);
		/*
		 * keep overview over non valid fields
		 */
		if (field.isValid()) {
			if (dirtyFields.contains(field))
				dirtyFields.remove(field);
		} else {
			dirtyFields.add(field);
		}
		/*
		 * enable save button, if form has changed and form is valid
		 */
		saveButton.setEnabled(fieldGroup.isValid());
	}

	private void cancel() {
		fieldGroup.discard();
	}

	private void save() {
		try {
			/*
			 * bring data from form back to bean fire CDI event
			 */
			fieldGroup.commit();
			/*
			 * disallow save
			 */
			saveButton.setEnabled(false);
			/*
			 * fire CDI event
			 */
			fireSaveEntityEvent(item.getBean());
		} catch (CommitException e) {
			log.error("committing item failed in details view !"
					+ (item == null ? " no item"
							: (item.getBean() == null ? "item bean null" : item
									.getBean().getClass().getSimpleName())));
			e.printStackTrace();
		}
	}

	/*
	 * called from implementing class, if master selection changed sets the data
	 * source of the form
	 */
	protected void fillForm(BeanItem<E> beanItem) {
		item = beanItem;

		fieldGroup.setItemDataSource(item);
		layout.setVisible(true);

		saveButton.setEnabled(false);
		cancelButton.setEnabled(false);
	}

	protected void bindFields() {
		for (String key : fieldMap.keySet()) {
			fieldMap.get(key).setValidationVisible(true);
			fieldMap.get(key).setEnabled(true);
		}
		if (fieldMap != null) {
			fieldGroup = new FieldGroup();
			/*
			 * bind field manually due to individual settings
			 */
			for (String key : fieldMap.keySet()) {
				try {
					fieldGroup.bind(fieldMap.get(key), key);
				} catch (BindException e) {
					log.error("could not bind " + fieldMap.get(key) + " to "
							+ key);
				}
			}
		}

	}

	/*
	 * promote event to CDI event
	 */
	private void fireSaveEntityEvent(ITableEntity entity) {
		saveEntityEvent.select(getTypeBinding(entity)).fire(
				new SaveEntityEvent(entity));
	}

	private TypeBinding getTypeBinding(ITableEntity entity) {
		return new TypeBinding() {
			private static final long serialVersionUID = 3419449904722350133L;

			public Class<? extends ITableEntity> value() {
				return entity.getClass();
			}
		};
	}

	protected void printTv() {
		TypeVariable<?>[] tv = this.getClass().getTypeParameters();
		if (tv == null) {
			log.info("no type variables");
		} else {
			for (TypeVariable<?> t : tv) {
				log.info("Type variable type name : " + t.getTypeName());
			}
		}
	}

	protected abstract void fillForm(@Observes TableSelectionEvent event);

	protected abstract Class<E> getClazz();
}
