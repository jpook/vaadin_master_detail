package com.kws.simple.ui.view.abstr;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.kws.simple.annotations.TypeBinding;
import com.kws.simple.events.SaveEntityEvent;
import com.kws.simple.events.TableSelectionEvent;
import com.kws.simple.helper.MethodHelper;
import com.kws.simple.model.interf.ITableEntity;
import com.kws.simple.ui.info.EntityInfoObject;
import com.vaadin.data.Validator;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SessionScoped
public abstract class ADetailsView<E extends ITableEntity> extends
		VerticalLayout {

	private static final long serialVersionUID = 5331836278413759109L;

	@Inject
	Logger log;

	@Inject
	javax.enterprise.event.Event<SaveEntityEvent> saveEntityEvent;

	BeanItem<E> item;
	protected FieldGroup fieldGroup;
	Map<String, AbstractField<?>> fieldMap;
	Button saveButton = new Button("Save");
	Button cancelButton = new Button("Cancel");
	Set<AbstractField<?>> dirtyFields = new HashSet<AbstractField<?>>();

	@PostConstruct
	public void init() {

		Label label = new Label("Details View");
		addComponent(label);
		Map<String, EntityInfoObject> map = getEntityMap();
		if (map == null)
			return;
		fieldMap = new HashMap<String, AbstractField<?>>();
		for (String key : map.keySet()) {
			EntityInfoObject currentObject = map.get(key);
			AbstractField<?> field = MethodHelper.getInstance(currentObject
					.getTypeName());
			field.setCaption(currentObject.getLabel());
			field.setVisible(true);
			field.setImmediate(true);
			field.setEnabled(false);
			if (currentObject.getValidatorSet() != null) {
				for (Validator v : currentObject.getValidatorSet()) {
					field.addValidator(v);
					field.setValidationVisible(false);
				}
			}
			field.addValueChangeListener(event -> {
				if (field.isValid()) {
					if (dirtyFields.contains(field))
						dirtyFields.remove(field);
				} else {
					dirtyFields.add(field);
				}
			});
			fieldMap.put(key, field);
			addComponent(field);
		}

		HorizontalLayout buttonLayout = new HorizontalLayout();
		saveButton.addClickListener(event -> save());
		cancelButton.addClickListener(event -> cancel());
		buttonLayout.addComponents(saveButton, cancelButton);

		addComponent(buttonLayout);
		setSizeFull();
	}

	private void cancel() {
		fieldGroup.discard();
	}

	private void save() {
		try {
			fieldGroup.commit();
			fireSaveEntityEvent(item.getBean());
		} catch (CommitException e) {
			log.error("committing item failed in details view !"
					+ (item == null ? " no item"
							: (item.getBean() == null ? "item bean null" : item
									.getBean().getClass().getSimpleName())));
			e.printStackTrace();
		}// gohfeld0157 73299732
	}

	/*
	 * called from implementing class, if master selection changed
	 */
	protected void fillForm(BeanItem<E> beanItem) {
		item = beanItem;
		for (String key : fieldMap.keySet()) {
			fieldMap.get(key).setValidationVisible(true);
			fieldMap.get(key).setEnabled(true);
		}
		if (fieldMap != null) {
			fieldGroup = new FieldGroup(item);
			/*
			 * bind field manually due to individual settings
			 */
			for (String key : fieldMap.keySet()) {
				fieldGroup.bind(fieldMap.get(key), key);
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

	protected abstract void fillForm(@Observes TableSelectionEvent event);

	protected abstract Map<String, EntityInfoObject> getEntityMap();

}
