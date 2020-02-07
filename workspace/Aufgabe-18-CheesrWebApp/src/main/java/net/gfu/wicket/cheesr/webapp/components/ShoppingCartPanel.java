package net.gfu.wicket.cheesr.webapp.components;

import java.text.NumberFormat;
import java.util.List;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import net.gfu.wicket.backend.bo.Cart;
import net.gfu.wicket.backend.bo.Cheese;
import net.gfu.wicket.cheesr.webapp.PriceModel;

public class ShoppingCartPanel extends Panel {
	private static final long serialVersionUID = 1L;
	private final IModel<Cart> cart;
	
	public ShoppingCartPanel(String id, IModel<Cart> cart){
		super(id);
		this.cart = cart;
		
		PropertyModel<List<Cheese>> model = new PropertyModel<>(this, "cart.cheeses");
		add(new ListView<Cheese>("cart", model) {
			private static final long serialVersionUID = 1L;
			@Override
			protected void populateItem(ListItem<Cheese> item) {
				Cheese cheese = item.getModelObject();
				item.add(new Label("name",cheese.getName()));
				item.add(new Label("price",new PriceModel(cheese.getPrice())));
				item.add(new Link<Cheese>("remove",item.getModel()){
					private static final long serialVersionUID = 1L;
					@Override
					public void onClick() {
						Cheese selected = getModelObject();
						getCart().getCheeses().remove(selected);
					}
				});
			}
		});
		
		add(new Label("total", new Model<String>(){
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				NumberFormat nfs = NumberFormat.getCurrencyInstance(Session.get().getLocale());
				return nfs.format(getCart().getTotal());
			}
		}));
	}

	public Cart getCart() {
		return cart.getObject();
	}
	
}