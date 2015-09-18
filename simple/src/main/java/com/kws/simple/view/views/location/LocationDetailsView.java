package com.kws.simple.view.views.location;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;

import com.kws.simple.cross.annotations.EntityTypeQualifier;
import com.kws.simple.cross.events.TableSelectionEvent;
import com.kws.simple.model.entities.Location;
import com.kws.simple.view.views.abstr.ADetailsView;
import com.vaadin.data.util.BeanItem;

@SessionScoped
public class LocationDetailsView extends ADetailsView<Location> implements
		Serializable {

	private static final long serialVersionUID = 2426729372649148032L;

	public LocationDetailsView() {
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void fillForm(
			@Observes @EntityTypeQualifier(Location.class) TableSelectionEvent event) {
		super.fillForm((BeanItem<Location>) event.getBeanItem());
	}

	@Override
	protected Class<Location> getClazz() {
		return Location.class;
	}

}
