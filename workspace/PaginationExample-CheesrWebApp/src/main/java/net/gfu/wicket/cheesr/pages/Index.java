package net.gfu.wicket.cheesr.pages;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import net.gfu.wicket.cheesr.bo.Cheese;
import net.gfu.wicket.cheesr.components.ShoppingCartPanel;

public class Index extends CheesrPage {
	private static final long serialVersionUID = 1L;

	
	public Index(final PageParameters parameters) {
		super();
		add(new ShoppingCartPanel("shoppingcart", Model.of(getCart())));

		PageableListView<Cheese> cheeses = new PageableListView<Cheese>("cheeses", getCheeses(),5) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<Cheese> item) {
				Cheese cheese = item.getModelObject();
				item.add(new Label("name",cheese.getName()));
				item.add(new Label("description",cheese.getDescription()));
				item.add(new Label("price","$ " + cheese.getPrice()));
				item.add(new Link<Cheese>("add",item.getModel()){
					private static final long serialVersionUID = 1L;
					@Override
					public void onClick() {
						Cheese selected = getModelObject();
						getCart().getCheeses().add(selected);
					}
					
				});
					 				
			}
		};
		add(cheeses);
		add(new PagingNavigator("navigator", cheeses));
		

		add(new Link<String>("checkout"){
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new Checkout());
				
			}

			@Override
			public boolean isVisible() {
				return !getCart().getCheeses().isEmpty();
			}
			
			
		});
	}
}