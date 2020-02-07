package net.gfu.wicket.cheesr.webapp.components;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import net.gfu.wicket.backend.bo.Cheese;

public class DetailPanel extends Panel {

	private static final long serialVersionUID = 1L;

	public DetailPanel(String id, IModel<Cheese> cheese) {
		super(id, new CompoundPropertyModel<>(cheese));

		add(new Label("name"));
		add(new Label("description"));
		add(new Label("price"));
	}
}